package io.github.mimoguz.layeredfonticon;

import org.jspecify.annotations.Nullable;

import java.awt.Color;

public class LayerColors {

    private LayerColors() {
    }

    /**
     * Convenience method to create a {@link LayerColor.Set} instance from possibly null color.
     *
     * @param color The color of the layer.
     * @return A LayerColor.Set instance if the color is not null, or LayerColor.Unset if it is.
     */
    public static LayerColor ofColor(@Nullable Color color) {
        return color == null ? LayerColor.Unset.instance() : new LayerColor.Set(color);
    }

    /**
     * Convenience method to create a {@link LayerColor.FromKey} instance from possibly null key.
     *
     * @param key The key value for lookup.
     * @return A LayerColor.FromKey instance if the key is not null, or LayerColor.Unset if it is.
     */
    public static LayerColor ofKey(@Nullable String key) {
        return key == null ? LayerColor.Unset.instance() : new LayerColor.FromKey(key);
    }

}
