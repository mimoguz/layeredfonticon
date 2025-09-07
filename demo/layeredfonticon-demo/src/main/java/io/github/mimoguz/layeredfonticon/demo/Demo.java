package io.github.mimoguz.layeredfonticon.demo;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Objects;

import io.github.mimoguz.layeredfonticon.Layer;
import io.github.mimoguz.layeredfonticon.LayerColor;
import io.github.mimoguz.layeredfonticon.LayeredFontIcon;

public class Demo extends JFrame {

    private static final String ACCENT_COLOR_KEY = "accent-color";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // pass
            }
            UIManager.put(ACCENT_COLOR_KEY, Color.RED);
            new Demo().setVisible(true);
        });
    }

    public Demo() {
        super("layeredfonticon.core Demo");

        final var iconFont = loadFont();
        final var contents = new JPanel(new GridLayout(2, 2, 4, 4));

        contents.add(
            new JButton(LayeredFontIcon.of(
                iconFont,
                Layer.of(Symbol.BORDER.value()),
                Layer.of(Symbol.TOP.value(), Color.GREEN),
                Layer.of(Symbol.BOTTOM.value(), ACCENT_COLOR_KEY)
            ))
        );

        contents.add(
            new JToggleButton(LayeredFontIcon.of(
                iconFont,
                Layer.of(Symbol.BORDER.value()),
                new Layer(Symbol.TOP.value(), new LayerColor.Set(Color.GREEN), false, -6, 8),
                new Layer(Symbol.BOTTOM.value(), LayerColor.Unset.instance(), false, 6, -8)
            ))
        );

        final var toggle = new JToggleButton(LayeredFontIcon.of(
            iconFont,
            Layer.of(Symbol.BORDER.value()),
            Layer.of(Symbol.TOP.value(), Color.GREEN),
            Layer.of(Symbol.BOTTOM.value(), ACCENT_COLOR_KEY, true)
        ));
        toggle.setForeground(Color.ORANGE);
        contents.add(toggle);

        final var disabledButton = new JButton(LayeredFontIcon.of(iconFont, Symbol.BORDER.value()));
        disabledButton.setEnabled(false);
        contents.add(disabledButton);

        contents.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setContentPane(contents);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static Font loadFont() {
        try {
            return Font.createFont(
                    Font.TRUETYPE_FONT,
                    Objects.requireNonNull(Demo.class.getResourceAsStream("/Layers.ttf"))
                )
                .deriveFont(48f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
