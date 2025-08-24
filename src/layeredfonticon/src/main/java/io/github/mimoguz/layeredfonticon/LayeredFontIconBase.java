package io.github.mimoguz.layeredfonticon;

import javax.swing.Icon;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A custom Swing icon implementation that uses an icon font to render multiple layers.
 */
public abstract class LayeredFontIconBase implements Icon {

    private final Font font;
    private final Layer[] layers;
    private final int width;
    private final int height;

    public LayeredFontIconBase(Font font, Layer[] layers) {
        this.font = font;
        this.layers = layers;
        final var context = new FontRenderContext(null, true, true);
        var w = 0.0;
        var h = 0.0;
        for (final var layer : layers) {
            final var bounds = font.getStringBounds(layer.symbol(), context);
            w = Math.max(w, bounds.getWidth() + layer.xOffset());
            h = Math.max(h, bounds.getHeight() + layer.yOffset());
        }
        width = (int) Math.ceil(w);
        height = (int) Math.ceil(h);
    }

    /**
     * Gets the color to use for a disabled icon.
     * <p>
     * Subclasses must provide an implementation of this method.
     * <p>
     *
     * @param foreground The foreground color of the parent component.
     * @return The color to use for a disabled icon.
     */
    protected abstract Color getDisabledColor(Color foreground);

    /**
     * Gets the color to use for a selected toggle button or tab.
     * <p>
     * Subclasses must provide an implementation of this method.
     * <p>
     *
     * @param component    The parent component.
     * @param defaultColor A supplier that returns the default color to use if no selected color info is present.
     * @return Selected foreground color or the default color.
     */
    protected abstract Color getSelectedForegroundColor(Component component, Supplier<Color> defaultColor);

    @Override
    public final int getIconWidth() {
        return width;
    }

    @Override
    public final int getIconHeight() {
        return height;
    }

    @Override
    public final void paintIcon(Component component, Graphics g, int x, int y) {
        final var g2d = (Graphics2D) g.create();
        final var stColor = findForegroundColor(component);
        if (component.isEnabled()) {
            paintEnabled(x, y, g2d, stColor.state(), stColor.color());
        } else {
            paintDisabled(x, y, g2d, stColor.state(), stColor.color());
        }
        g2d.dispose();
    }

    private void paintEnabled(int x, int y, Graphics2D g2d, ComponentState state, Color foreground) {
        paintState(x, y, g2d, state, foreground, Function.identity());
    }

    private void paintDisabled(int x, int y, Graphics2D g2d, ComponentState state, Color foreground) {
        paintState(x, y, g2d, state, foreground, this::getDisabledColor);
    }

    private void paintState(
        int x,
        int y,
        Graphics2D g2d,
        ComponentState state,
        Color foreground,
        Function<Color, Color> transform
    ) {
        final var context = new FontRenderContext(null, true, true);
        for (final var layer : layers) {
            final var color = switch (state) {
                case SELECTED, NON_SELECTABLE -> layer.color().getOrDefault(foreground);
                case NOT_SELECTED -> layer.forceColor() ? layer.color().getOrDefault(foreground) : foreground;
            };
            g2d.setColor(transform.apply(color));
            final var layout = new TextLayout(layer.symbol(), font, context);
            layout.draw(g2d, x + layer.xOffset(), y + layer.yOffset() + layout.getAscent());
        }
    }

    private StateColor findForegroundColor(Component component) {
        if (component instanceof JTabbedPane pane) {
            return getTabbedPaneForeground(pane);
        }
        if (component instanceof JToggleButton button) {
            return getToggleButtonForeground(button);
        }
        return new StateColor(ComponentState.NON_SELECTABLE, component.getForeground());
    }

    private StateColor getTabbedPaneForeground(JTabbedPane pane) {
        final var index = pane.indexOfTab(this);
        return (index == pane.getSelectedIndex()
            ? new StateColor(ComponentState.SELECTED, getSelectedForegroundColor(pane, pane::getForeground))
            : new StateColor(ComponentState.NOT_SELECTED, pane.getForegroundAt(index)));
    }

    private StateColor getToggleButtonForeground(JToggleButton toggle) {
        return (toggle.isSelected()
            ? new StateColor(ComponentState.SELECTED, getSelectedForegroundColor(toggle, toggle::getForeground))
            : new StateColor(ComponentState.NOT_SELECTED, toggle.getForeground()));
    }

    private record StateColor(ComponentState state, Color color) {

    }

    private enum ComponentState {
        SELECTED, NOT_SELECTED, NON_SELECTABLE
    }

}
