package io.github.mimoguz.layeredfonticon.demo;

import io.github.mimoguz.layeredfonticon.Layer;
import io.github.mimoguz.layeredfonticon.LayeredFontIcon;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Frame extends JFrame {
    private final Font font;

    public Frame() {
        try {
            font = Font.createFont(
                            Font.TRUETYPE_FONT,
                            Objects.requireNonNull(getClass().getResourceAsStream("/Layers.ttf"))
                    )
                    .deriveFont(48f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final var contents = new JPanel(new GridLayout(2, 2, 4, 4));
        contents.add(makeButton(Symbol.BORDER));
        contents.add(
                makeButton(
                        Layer.of(Symbol.BORDER.text()),
                        Layer.of(Symbol.TOP.text(), Color.GREEN),
                        Layer.of(Symbol.TOP.text(), Color.RED)
                )
        );
        contents.add(
                makeToggleButton(
                        Layer.of(Symbol.BORDER.text()),
                        Layer.of(Symbol.TOP.text(), Color.BLUE, false, -6, 8),
                        Layer.of(Symbol.BOTTOM.text(), null, false, 6, -8)
                )
        );

        final var toggle = makeToggleButton(
                Layer.of(Symbol.BORDER.text()),
                Layer.of(Symbol.TOP.text(), Color.BLUE),
                Layer.of(Symbol.BOTTOM.text(), Color.RED, true)
        );
        toggle.setForeground(Color.MAGENTA);
        contents.add(toggle);

        setContentPane(contents);
        pack();
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JButton makeButton(Symbol symbol) {
        return new JButton(LayeredFontIcon.of(font, symbol.text()));
    }

    private JButton makeButton(Layer... layers) {
        return new JButton(LayeredFontIcon.of(font, layers));
    }

    private JToggleButton makeToggleButton(Layer... layers) {
        return new JToggleButton(LayeredFontIcon.of(font, layers));
    }
}
