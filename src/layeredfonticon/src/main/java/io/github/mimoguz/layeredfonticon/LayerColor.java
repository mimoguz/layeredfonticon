package io.github.mimoguz.layeredfonticon;

import javax.swing.UIManager;
import java.awt.Color;
import java.util.function.Supplier;

/**
 * Layer color
 */
public sealed interface LayerColor {

    Color getOrDefault(Color def);

    Color getOrElse(Supplier<Color> def);

    /**
     * Gets layer color from UIManager.
     *
     * @param key The key value for lookup.
     */
    record FromKey(String key) implements LayerColor {

        @Override
        public Color getOrDefault(Color def) {
            final var color = UIManager.getColor(key);
            return color == null ? def : color;
        }

        @Override
        public Color getOrElse(Supplier<Color> def) {
            final var color = UIManager.getColor(key);
            return color == null ? def.get() : color;
        }

    }

    /**
     * A fixed color.
     *
     * @param color The color of the layer.
     */
    record Set(Color color) implements LayerColor {

        @Override
        public Color getOrDefault(Color def) {
            return color;
        }

        @Override
        public Color getOrElse(Supplier<Color> def) {
            return color;
        }

    }

    /**
     * Uses the default color. Since it doesn't carry a value and thus can be shared, it's a singleton.
     */
    final class Unset implements LayerColor {

        private Unset() {
        }

        private static final Unset instance = new Unset();

        /**
         * Returns the single instance of this class.
         */
        public static Unset instance() {
            return instance;
        }

        @Override
        public Color getOrDefault(Color def) {
            return def;
        }

        @Override
        public Color getOrElse(Supplier<Color> def) {
            return def.get();
        }

    }

}
