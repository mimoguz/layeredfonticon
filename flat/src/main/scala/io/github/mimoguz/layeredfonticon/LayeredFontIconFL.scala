package io.github.mimoguz.layeredfonticon

import com.formdev.flatlaf.FlatLaf
import com.formdev.flatlaf.ui.FlatStylingSupport
import com.formdev.flatlaf.util.GrayFilter

import java.awt.{Color, Component, Font}
import javax.swing.{Icon, JComponent, UIManager}
import scala.util.Try

class LayeredFontIconFL(_font: Font, _layers: Seq[Layer]) extends LayeredFontIconBase(_font, _layers):
  override protected def disabledColorFunction: Color => Color =
    val currentFilter = Option(UIManager.get("Component.grayFilter").asInstanceOf[GrayFilter])
    val grayFilter = currentFilter.getOrElse(
      GrayFilter.createDisabledIconFilter(
        UIManager.getLookAndFeel match
          case laf: FlatLaf => laf.isDark
          case _            => false,
      ),
    )
    c => Color(grayFilter.filterRGB(0, 0, c.getRGB))

  override protected def selectedForeground(component: Component, default: => Color): Color =
    component match
      case c: JComponent =>
        c.getUI match
          case ui: FlatStylingSupport.StyleableUI =>
            Try(ui.getStyleableValue(c, "selectedForeground").nn.asInstanceOf[Color]).getOrElse(default)
          case _ => c.getForeground
      case c => c.getForeground
end LayeredFontIconFL

object LayeredFontIconFL:
  def apply(font: Font, layers: Layer*): LayeredFontIconFL = new LayeredFontIconFL(font, layers)

  def apply(
      font: Font,
      symbol: String,
      color: Option[Color] = None,
      forceColor: Boolean = false,
      xOffset: Float = 0f,
      yOffset: Float = 0f,
  ): LayeredFontIconFL = new LayeredFontIconFL(font, Seq(Layer(symbol, color, forceColor, xOffset, yOffset)))

  def of(font: Font, symbol: String): LayeredFontIconFL = apply(font, Layer(symbol))
  def of(font: Font, layers: Array[Layer]): LayeredFontIconFL = apply(font, layers*)
end LayeredFontIconFL
