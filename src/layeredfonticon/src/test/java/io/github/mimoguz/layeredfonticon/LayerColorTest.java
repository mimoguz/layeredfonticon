package io.github.mimoguz.layeredfonticon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.UIManager;
import java.awt.Color;

public class LayerColorTest {

    @Test
    public void keyTest() {
        UIManager.put("blue", Color.BLUE);
        UIManager.put("green", Color.GREEN);
        final var blue = new LayerColor.FromKey("blue");
        final var green = LayerColors.ofKey("green");
        final var yellow = LayerColors.ofKey("yellow");

        Assertions.assertEquals(Color.BLUE, blue.getOrDefault(Color.RED));
        Assertions.assertEquals(Color.GREEN, green.getOrDefault(Color.RED));
        // Should return default color:
        Assertions.assertEquals(Color.RED, yellow.getOrDefault(Color.RED));
    }

}
