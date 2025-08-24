package io.github.mimoguz.layeredfonticon;

import java.awt.Color;

/**
 * Icon layer
 *
 * @param symbol     The string that will be used to draw this layer.
 * @param color      Optional color. If it's <em>Unset</em>, the foreground color of the parent component will be used.
 * @param forceColor Force color for <em>not selected</em> toggle buttons or tabs.
 *                   If false, the foreground color will be used.
 * @param xOffset    X shift.
 * @param yOffset    Y shift.
 */
public record Layer(String symbol, LayerColor color, boolean forceColor, int xOffset, int yOffset) {

    /**
     * A factory method to create a layer.
     * Functionally equivalent to the record constructor, provided for consistency.
     *
     * @param symbol     The string that will be used to draw this layer.
     * @param color      Layer color.
     * @param forceColor Force color for <em>not selected</em> toggle buttons or tabs.
     *                   If false, the foreground color will be used.
     * @return Layer
     */
    public static Layer of(String symbol, Color color, boolean forceColor, int xOffset, int yOffset) {
        return new Layer(symbol, new LayerColor.Set(color), forceColor, xOffset, yOffset);
    }

    /**
     * A factory method to create a layer.
     *
     * @param symbol     The string that will be used to draw this layer.
     * @param color      Layer color.
     * @param forceColor Force color for <em>not selected</em> toggle buttons or tabs.
     *                   If false, the foreground color will be used.
     * @return Layer
     */
    public static Layer of(String symbol, Color color, boolean forceColor) {
        return new Layer(symbol, new LayerColor.Set(color), forceColor, 0, 0);
    }

    /**
     * A factory method to create a layer.
     *
     * @param symbol     The string that will be used to draw this layer.
     * @param colorKey   Key value to retrieve layer color from UIManager.
     * @param forceColor Force color for <em>not selected</em> toggle buttons or tabs.
     *                   If false, the foreground color will be used.
     * @return The new layer.
     */
    public static Layer of(String symbol, String colorKey, boolean forceColor) {
        return new Layer(symbol, new LayerColor.FromKey(colorKey), forceColor, 0, 0);
    }

    /**
     * A factory method to create a layer.
     *
     * @param symbol The string that will be used to draw this layer.
     * @param color  Layer color.
     * @return The new layer.
     */
    public static Layer of(String symbol, Color color) {
        return new Layer(symbol, new LayerColor.Set(color), false, 0, 0);
    }

    /**
     * A factory method to create a layer.
     *
     * @param symbol   The string that will be used to draw this layer.
     * @param colorKey Key value to retrieve layer color from UIManager.
     * @return The new layer.
     */
    public static Layer of(String symbol, String colorKey) {
        return new Layer(symbol, new LayerColor.FromKey(colorKey), false, 0, 0);
    }

    /**
     * A factory method to create a layer.
     *
     * @param symbol The string that will be used to draw this layer.
     * @return The new layer.
     */
    public static Layer of(String symbol) {
        return new Layer(symbol, LayerColor.Unset.instance(), false, 0, 0);
    }

}
