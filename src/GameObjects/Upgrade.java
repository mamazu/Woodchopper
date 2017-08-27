package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Upgrade implements GameObject {
    private static Image backgroundImage;

    private final Font font = new Font(null, Font.PLAIN, 20);
    private final Font levelFont = new Font(null, Font.PLAIN, 15);
    Rectangle dimension;

    private int level = 0;
    private double output = 1;
    private double price = 1;
    private String name = "Test Product";

    Upgrade(String name) {
        this(name, 0, 0);
    }

    Upgrade(String name, int width) {
        this(name, width, 0);
    }

    Upgrade(String name, int width, int startLevel) {
        dimension = new Rectangle(0, 0, width, 60);
        this.name = name;
        this.level = startLevel;
        updatePrice();
    }

    public void setBackgroundImage() {
        String filePath = "/home/mamazu/IdeaProjects/WoodChopper/resources/upgrade_background.png";
        try {
            BufferedImage bi = ImageIO.read(new File(filePath));
            backgroundImage = bi.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.print("Could not find file");
        }
    }

    int getLevel() {
        return level;
    }

    double getPrice() {
        return price;
    }

    double getOutput() {
        return output;
    }

    @Override
    public boolean click(Point p) {
        return dimension.contains(p);
    }

    private void updatePrice() {
        price = Math.pow(2, level);
        output = level * 2;
    }

    @Override
    public void draw(Graphics g) {
        draw(g, new Point(0, 0));
    }

    public void draw(Graphics g, Point location) {
        location.translate(dimension.x, dimension.y);
        if (backgroundImage == null) {
            g.setColor(Color.RED);
            g.fillRect(location.x, location.y, dimension.width, dimension.height);
        } else {
            g.drawImage(backgroundImage, location.x, location.y, null);
        }

        // Drawing the name
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(name, 5 + location.x, location.y + font.getSize());

        // Price
        String priceString = "$ " + price;
        int width = g.getFontMetrics().stringWidth(priceString);
        g.drawString(priceString, location.x + dimension.width - width - 5, location.y + font.getSize() * 2);

        // Price
        g.setFont(levelFont);
        g.drawString("Level: " + level + "(" + getOutput() + ")", location.x + 5, location.y + font.getSize() * 3 - 2);
    }

    void upgrade() {
        level++;
        updatePrice();
    }
}
