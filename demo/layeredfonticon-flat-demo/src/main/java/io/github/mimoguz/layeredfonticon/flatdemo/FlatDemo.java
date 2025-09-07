package io.github.mimoguz.layeredfonticon.flatdemo;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;

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
import io.github.mimoguz.layeredfonticon.flat.LayeredFontIconFlat;

public class FlatDemo extends JFrame {

    private static final String ACCENT_COLOR_KEY = "my-accent-color";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.setup();
            UIManager.put(ACCENT_COLOR_KEY, Color.RED);
            new FlatDemo().setVisible(true);
        });
    }

    private boolean isDarkLaf = false;

    public FlatDemo() {
        super("layeredfonticon.core Demo");

        final var iconFont = loadFont();
        final var contents = new JPanel(new GridLayout(2, 3, 4, 4));

        contents.add(
            new JButton(LayeredFontIconFlat.of(
                iconFont,
                Layer.of(Symbol.BORDER.value()),
                Layer.of(Symbol.TOP.value(), Color.GREEN),
                Layer.of(Symbol.BOTTOM.value(), ACCENT_COLOR_KEY)
            ))
        );

        contents.add(
            new JToggleButton(LayeredFontIconFlat.of(
                iconFont,
                Layer.of(Symbol.BORDER.value()),
                new Layer(Symbol.TOP.value(), new LayerColor.Set(Color.GREEN), false, -6, 8),
                new Layer(Symbol.BOTTOM.value(), LayerColor.Unset.instance(), false, 6, -8)
            ))
        );

        final var toggle = new JToggleButton(LayeredFontIconFlat.of(
            iconFont,
            Layer.of(Symbol.BORDER.value()),
            Layer.of(Symbol.TOP.value(), Color.GREEN),
            Layer.of(Symbol.BOTTOM.value(), ACCENT_COLOR_KEY, true)
        ));
        toggle.setForeground(Color.ORANGE);
        contents.add(toggle);

        final var disabledButton = new JButton(LayeredFontIconFlat.of(iconFont, Symbol.BORDER.value()));
        disabledButton.setEnabled(false);
        contents.add(disabledButton);

        final var switchThemeButton = new JButton("Switch Theme");
        switchThemeButton.addActionListener(e -> {
            isDarkLaf = !isDarkLaf;

            if (isDarkLaf) {
                FlatDarculaLaf.setup();
            } else {
                FlatIntelliJLaf.setup();
            }
            FlatLaf.updateUI();
        });
        contents.add(switchThemeButton);

        contents.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setContentPane(contents);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static Font loadFont() {
        try {
            return Font.createFont(
                    Font.TRUETYPE_FONT,
                    Objects.requireNonNull(FlatDemo.class.getResourceAsStream("/Layers.ttf"))
                )
                .deriveFont(48f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
