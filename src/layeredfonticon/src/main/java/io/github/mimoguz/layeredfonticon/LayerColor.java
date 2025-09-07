package io.github.mimoguz.layeredfonticon;

import org.jspecify.annotations.Nullable;

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

    /**
     * Convenience method to create a {@link LayerColor.Set} instance from possibly null color.
     *
     * @param color The color of the layer.
     * @return A LayerColor.Set instance if the color is not null, or LayerColor.Unset if it is.
     */
    static LayerColor ofColor(@Nullable Color color) {
        return color == null ? LayerColor.Unset.instance() : new LayerColor.Set(color);
    }

    /**
     * Convenience method to create a {@link LayerColor.FromKey} instance from possibly null key.
     *
     * @param key The key value for lookup.
     * @return A LayerColor.FromKey instance if the key is not null, or LayerColor.Unset if it is.
     */
    static LayerColor ofKey(@Nullable String key) {
        return key == null ? LayerColor.Unset.instance() : new LayerColor.FromKey(key);
    }

    /**
     * Convenience method to get {@link LayerColor.FromKey} instance.
     */
    static LayerColor unset() {
        return LayerColor.Unset.instance();
    }

}
