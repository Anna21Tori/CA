package elements;

import javax.swing.*;
import java.awt.*;

public class Slider extends JSlider {
    public Slider(int min, int max, int value, int majorTickSpacing) {
        super(min, max, value);
        this.setPaintTrack(true);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMajorTickSpacing(majorTickSpacing);
        this.setMinorTickSpacing(10);
        this.setFont(new Font("Serif", Font.PLAIN, 10));
    }
}
