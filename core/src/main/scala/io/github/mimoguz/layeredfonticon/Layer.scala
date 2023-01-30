package io.github.mimoguz.layeredfonticon

import io.github.mimoguz.layeredfonticon

import java.awt.Color
import javax.swing.UIManager

/** Icon layer
  *
  * @param symbol
  *   The string that will be used to draw this layer.
  * @param color
  *   Optional color. If None, the foreground color will be used.
  * @param forceColor
  *   Force color for __not selected__ toggle buttons or tabs. If false (default), the foreground color will be used.
  * @param xOffset
  *   X shift.
  * @param yOffset
  *   Y shift.
  */
class Layer(
    val symbol: String,
    val color: LayerColor = LayerColor.Unset,
    val forceColor: Boolean = false,
    val xOffset: Float = 0f,
    val yOffset: Float = 0f,
)

object Layer:
  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @param color
    *   Optional color. You can pass null to use component's foreground color.
    * @param forceColor
    *   Force color for __not selected__ toggle buttons or tabs. If false (default), the foreground color will be used.
    * @param xOffset
    *   X shift.
    * @param yOffset
    *   Y shift.
    * @return
    */
  def of(symbol: String, color: Color, forceColor: Boolean, xOffset: Float, yOffset: Float): Layer =
    Layer(symbol, LayerColor(color), forceColor, xOffset, yOffset)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @param colorKey
    *   Key value to retrieve layer's color from UIManager. You can pass null to use component's foreground color.
    * @param forceColor
    *   Force color for __not selected__ toggle buttons or tabs. If false (default), the foreground color will be used.
    * @param xOffset
    *   X shift.
    * @param yOffset
    *   Y shift.
    * @return
    */
  def ofKey(symbol: String, colorKey: String, forceColor: Boolean, xOffset: Float, yOffset: Float): Layer =
    Layer(symbol, LayerColor(colorKey), forceColor, xOffset, yOffset)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @param color
    *   Optional color. You can pass null to use component's foreground color.
    * @param forceColor
    *   Force color for __not selected__ toggle buttons or tabs. If false (default), the foreground color will be used.
    * @return
    */
  def of(symbol: String, color: Color, forceColor: Boolean): Layer =
    Layer(symbol, LayerColor(color), forceColor, 0f, 0f)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @param colorKey
    *   Key value to retrieve layer's color from UIManager. You can pass null to use component's foreground color.
    * @param forceColor
    *   Force color for __not selected__ toggle buttons or tabs. If false (default), the foreground color will be used.
    * @return
    */
  def ofKey(symbol: String, colorKey: String, forceColor: Boolean): Layer =
    Layer(symbol, LayerColor(colorKey), forceColor, 0f, 0f)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @param color
    *   Optional color. You can pass null to use component's foreground color.
    * @return
    */
  def of(symbol: String, color: Color): Layer =
    Layer(symbol, LayerColor(color), false, 0f, 0f)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @param colorKey
    *   Key value to retrieve layer's color from UIManager. You can pass null to use component's foreground color.
    * @return
    */
  def ofKey(symbol: String, colorKey: String): Layer =
    Layer(symbol, LayerColor(colorKey), false, 0f, 0f)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @return
    */
  def of(symbol: String): Layer =
    Layer(symbol, LayerColor.Unset, false, 0f, 0f)
end Layer

enum LayerColor:
  case FromKey(key: String)
  case Set(color: Color)
  case Unset

  def getOrElse(default: => Color): Color =
    this match
      case FromKey(key) =>
        val color = UIManager.getColor(key)
        if color != null then color else default
      case Set(color) => color
      case Unset      => default

object LayerColor:
  def apply(color: Color): LayerColor = if color != null then LayerColor.Set(color) else LayerColor.Unset
  def apply(key: String): LayerColor = if key != null then LayerColor.FromKey(key) else LayerColor.Unset
