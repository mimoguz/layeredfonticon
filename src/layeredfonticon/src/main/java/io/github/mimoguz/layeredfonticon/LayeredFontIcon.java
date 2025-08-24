package io.github.mimoguz.layeredfonticon;

import javax.swing.GrayFilter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.function.Supplier;

/**
 * Basic implementation of {@link LayeredFontIconBase}.
 *
 * @see LayeredFontIconBase
 */
public class LayeredFontIcon extends LayeredFontIconBase {

    public LayeredFontIcon(Font font, Layer[] layers) {
        super(font, layers);
    }

    @Override
    protected Color getDisabledColor(Color foreground) {
        return new Color(grayFilter.filterRGB(0, 0, foreground.getRGB()));
    }

    @Override
    protected Color getSelectedForegroundColor(Component component, Supplier<Color> defaultColor) {
        return defaultColor.get();
    }

    private static GrayFilter grayFilter = new GrayFilter(true, 50);

    /**
     * Sets the shared gray filter used to gray out the icon when it is disabled.
     *
     * @param filter The gray filter.
     */
    public static void setGrayFilter(GrayFilter filter) {
        grayFilter = filter;
    }

    /**
     * A convenience method to create a {@link LayeredFontIcon} instance from layers given as varargs.
     *
     * @param font   The font to use.
     * @param layers The layers of the icon.
     * @return A new {@link LayeredFontIcon} instance.
     */
    public static LayeredFontIcon of(Font font, Layer... layers) {
        return new LayeredFontIcon(font, layers);
    }

    /**
     * A convenience method to create a basic, single-layer {@link LayeredFontIcon} instance.
     *
     * @param font   The font to use.
     * @param symbol The symbol string.
     * @return A new {@link LayeredFontIcon} instance.
     */
    public static LayeredFontIcon of(Font font, String symbol) {
        return LayeredFontIcon.of(font, Layer.of(symbol));
    }

}
