package io.github.mimoguz.layeredfonticon

import java.awt.{Color, Component, Font}
import javax.swing.{GrayFilter, Icon, JComponent, UIManager}

class LayeredFontIcon(_font: Font, _layers: Seq[Layer]) extends LayeredFontIconBase(_font, _layers):
  override protected def disabledColorFunction: Color => Color =
    val grayFilter = GrayFilter(false, 50) // TODO: Default gray filter?
    c => Color(grayFilter.filterRGB(0, 0, c.getRGB))

  override protected def selectedForeground(component: Component, default: => Color): Color = default

object LayeredFontIcon:
  def apply(font: Font, layers: Layer*): LayeredFontIcon = new LayeredFontIcon(font, layers)

  def apply(
      font: Font,
      symbol: String,
      color: LayerColor = LayerColor.Unset,
      forceColor: Boolean = false,
      xOffset: Float = 0f,
      yOffset: Float = 0f,
  ): LayeredFontIcon = new LayeredFontIcon(font, Seq(Layer(symbol, color, forceColor, xOffset, yOffset)))

  def of(font: Font, symbol: String): LayeredFontIcon = apply(font, Layer(symbol))
  def of(font: Font, layers: Array[Layer]): LayeredFontIcon = apply(font, layers*)
end LayeredFontIcon
