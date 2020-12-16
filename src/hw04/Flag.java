package hw04;

import java.awt.image.BufferedImage;

public class Flag {
    private final String name;
    private final BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public String toString() {
        return name;
    }

    public Flag(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }
}
