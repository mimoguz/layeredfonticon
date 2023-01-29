package io.github.mimoguz.layeredfonticon

import java.awt.font.{FontRenderContext, TextLayout}
import java.awt.{Color, Component, Font, Graphics, Graphics2D}
import javax.swing.{Icon, JTabbedPane, JToggleButton}

abstract class LayeredFontIconBase(val font: Font, val layers: Seq[Layer]) extends Icon:
  import LayeredFontIconBase.ComponentState

  protected def disabledColorFunction: Color => Color
  protected def selectedForeground(component: Component, default: => Color): Color

  private val (width, height) =
    val ctx = FontRenderContext(null, true, true)
    layers.foldLeft((0, 0)) { case ((wMax, hMax), layer) =>
      val baseBounds = font.getStringBounds(layer.symbol, ctx)
      val w = (baseBounds.getWidth + layer.xOffset).ceil.toInt max wMax
      val h = (baseBounds.getHeight + layer.yOffset).ceil.toInt max hMax
      (w, h)
    }

  override def paintIcon(component: Component, graphics: Graphics, x: Int, y: Int): Unit =
    val g2d = graphics.create().asInstanceOf[Graphics2D]
    val (state, foreground) = findForegroundColor(component)
    if !component.isEnabled
    then paintDisabled(x, y, g2d, state, foreground)
    else paintEnabled(x, y, g2d, state, foreground)
    g2d.dispose()

  override def getIconWidth: Int = width

  override def getIconHeight: Int = height

  private def paintEnabled(x: Int, y: Int, g2d: Graphics2D, state: ComponentState, foreground: Color): Unit =
    paintSt(x, y, g2d, state, foreground)(identity)

  private def paintDisabled(x: Int, y: Int, g2d: Graphics2D, state: ComponentState, foreground: Color): Unit =
    paintSt(x, y, g2d, state, foreground)(disabledColorFunction)

  private def paintSt(x: Int, y: Int, g2d: Graphics2D, state: ComponentState, foreground: Color)(
      colorTransform: Color => Color = identity,
  ): Unit =
    val ctx = FontRenderContext(null, true, true)
    layers.foreach { layer =>
      val layout = TextLayout(layer.symbol, font, ctx)
      val color = state match
        case ComponentState.Selected | ComponentState.NotSelectable => layer.color.getOrElse(foreground)
        case ComponentState.NotSelected if layer.forceColor         => layer.color.getOrElse(foreground)
        case ComponentState.NotSelected                             => foreground

      g2d.setColor(colorTransform(color))
      layout.draw(g2d, x + layer.xOffset, y + layout.getAscent + layer.yOffset)
    }
  end paintSt

  private def findForegroundColor(component: Component): (ComponentState, Color) =
    component match
      case toggle: JToggleButton => toggleButtonForeground(toggle)
      case pane: JTabbedPane     => tabbedPaneForeground(pane)
      case _                     => (ComponentState.NotSelectable, component.getForeground)

  private def tabbedPaneForeground(pane: JTabbedPane): (ComponentState, Color) =
    val index = pane.indexOfTab(this)
    if index == pane.getSelectedIndex
    then (ComponentState.Selected, selectedForeground(pane, pane.getForegroundAt(index)))
    else (ComponentState.NotSelected, pane.getForegroundAt(index))

  private def toggleButtonForeground(toggle: JToggleButton): (ComponentState, Color) =
    if toggle.isSelected
    then (ComponentState.Selected, selectedForeground(toggle, toggle.getForeground))
    else (ComponentState.NotSelected, toggle.getForeground)
end LayeredFontIconBase

object LayeredFontIconBase:
  private enum ComponentState:
    case Selected, NotSelected, NotSelectable
