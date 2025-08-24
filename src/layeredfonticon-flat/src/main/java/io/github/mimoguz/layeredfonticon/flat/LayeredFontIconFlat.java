package io.github.mimoguz.layeredfonticon.flat;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.GrayFilter;
import org.jspecify.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.function.Supplier;

import io.github.mimoguz.layeredfonticon.Layer;
import io.github.mimoguz.layeredfonticon.LayeredFontIconBase;

/**
 * FlatLaF specific implementation of {@link LayeredFontIconBase}.
 *
 * @see LayeredFontIconBase
 */
public class LayeredFontIconFlat extends LayeredFontIconBase {

    public LayeredFontIconFlat(Font font, Layer[] layers) {
        super(font, layers);
    }

    @Override
    protected Color getDisabledColor(Color foreground) {
        var currentFilter = UIManager.get("Component.grayFilter");
        if (!(currentFilter instanceof GrayFilter)) {
            currentFilter = getSharedGrayFilter();
        }
        return new Color(((GrayFilter) currentFilter).filterRGB(0, 0, foreground.getRGB()));
    }

    @Override
    protected Color getSelectedForegroundColor(Component component, Supplier<Color> defaultColor) {
        if (component instanceof JComponent jc
            && jc.getUI() instanceof FlatStylingSupport.StyleableUI ui
            && ui.getStyleableValue(jc, "selectedForeground") instanceof Color color) {
            return color;
        }
        return defaultColor.get();
    }

    private static @Nullable GrayFilter sharedGrayFilter;
    private static boolean isSharedGrayFilterDark = false;

    private static GrayFilter getSharedGrayFilter() {
        final var isDarkLaf = UIManager.getLookAndFeel() instanceof FlatLaf laf && laf.isDark();
        if (sharedGrayFilter == null || isSharedGrayFilterDark != isDarkLaf) {
            sharedGrayFilter = GrayFilter.createDisabledIconFilter(isDarkLaf);
            isSharedGrayFilterDark = isDarkLaf;
        }
        return sharedGrayFilter;
    }

    /**
     * A convenience method to create a {@link LayeredFontIconFlat} instance from layers given as varargs.
     *
     * @param font   The font to use.
     * @param layers The layers of the icon.
     * @return A new {@link LayeredFontIconFlat} instance.
     */
    public static LayeredFontIconFlat of(Font font, Layer... layers) {
        return new LayeredFontIconFlat(font, layers);
    }

    /**
     * A convenience method to create a basic, single-layer {@link LayeredFontIconFlat} instance.
     *
     * @param font   The font to use.
     * @param symbol The symbol string.
     * @return A new {@link LayeredFontIconFlat} instance.
     */
    public static LayeredFontIconFlat of(Font font, String symbol) {
        return LayeredFontIconFlat.of(font, Layer.of(symbol));
    }

}
