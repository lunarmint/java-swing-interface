package hw05;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Canvas extends JPanel {

    private static final int diameter = 10;
    private int flagX, flagY, crossHairX, crossHairY;
    private int flagWidth, flagHeight;
    private int sliderX, sliderY;
    private final Frame frame;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        flagWidth = getWidth() / 2;
        flagHeight = getHeight() / 2;
        calculateX();
        calculateY();

        Flag f = frame.getCurrentFlag();
        if(f == null) {
            g.drawLine(crossHairX, 0, crossHairX, getHeight()); //Vertical line
            g.drawLine(0, crossHairY, getWidth(), crossHairY); //Horizontal line
            g.drawOval(crossHairX - diameter / 2, crossHairY - diameter / 2, diameter, diameter);
        }
        else if(flagWidth > 0 && flagHeight > 0) {
            Image scaledImage = f.getImage().getScaledInstance(flagWidth, flagHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, flagX, flagY, null);
        }
    }

    public void calculateX() {
        double percent = sliderX / 100.0;
        crossHairX = (int)Math.round(getWidth() * percent) + ((sliderX > 35) ? (int)(percent * 100 / 9): (int)(percent * 100 / -6));
        flagX = crossHairX - (flagWidth / 2);
    }

    public void calculateY() {
        double percent = Math.abs(sliderY - 100) / 100.0;
        crossHairY = (int)Math.round(getHeight() * percent) + 10;
        flagY = crossHairY - (flagHeight / 2);
    }

    public void setsliderX(int i) {
        sliderX = i;
    }

    public void setSliderY(int i) {
        sliderY = i;
    }

    public Canvas(Frame frame) {
        this.frame = frame;
    }
}
