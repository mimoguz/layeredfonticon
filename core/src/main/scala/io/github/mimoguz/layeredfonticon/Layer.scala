package io.github.mimoguz.layeredfonticon

import java.awt.Color

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
    val color: Option[Color] = None,
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
    Layer(symbol, Option(color), forceColor, xOffset, yOffset)

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
    Layer(symbol, Option(color), forceColor, 0f, 0f)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @param color
    *   Optional color. You can pass null to use component's foreground color.
    * @return
    */
  def of(symbol: String, color: Color): Layer =
    Layer(symbol, Option(color), false, 0f, 0f)

  /** A Java friendly factory method to create a layer.
    *
    * @param symbol
    *   The string that will be used to draw this layer.
    * @return
    */
  def of(symbol: String): Layer =
    Layer(symbol, None, false, 0f, 0f)
end Layer
