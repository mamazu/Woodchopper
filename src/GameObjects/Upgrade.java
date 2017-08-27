package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Upgrade implements GameObject {
    private static Image backgroundImage;
    private static int index = 0;

    private final Font font = new Font(null, Font.PLAIN, 20);
    private final Font levelFont = new Font(null, Font.PLAIN, 15);
    Rectangle dimension;

    private int level = 0;
    private double output = 1;
    private double price = 1;
    private String name = "Test Product";

    Upgrade(String name) {
        this(name, 0);
    }

    Upgrade(String name, int startLevel) {
        dimension = new Rectangle(0, index++ * (60 + 5), 0, 60);
        this.name = name;
        this.level = startLevel;
        updatePrice();
    }

    public void setBackgroundImage(){
        try {
            BufferedImage bi = ImageIO.read(new File("/home/mamazu/IdeaProjects/WoodChopper/resources/upgrade_background.png"));
            backgroundImage = bi.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.print("Could not find file");
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
        return p.x >= dimension.x && p.x <= dimension.x + dimension.width && p.y >= dimension.y && p.y <= dimension.y + dimension.height;
    }

    private void updatePrice() {
        price = Math.pow(2, level);
        output = level * 2;
    }

    @Override
    public void draw(Graphics g) {
        if(backgroundImage == null){
            g.setColor(Color.RED);
            g.fillRect(dimension.x, dimension.y, dimension.width, dimension.height);
        }else{
            g.drawImage(backgroundImage, dimension.x, dimension.y, null);
        }

        // Drawing the name
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(name, 5 + dimension.x, dimension.y + font.getSize());

        // Price
        String priceString = "$ " + price;
        int width = g.getFontMetrics().stringWidth(priceString);
        g.drawString(priceString, dimension.x + dimension.width - width - 5, dimension.y + font.getSize() * 2);

        // Price
        g.setFont(levelFont);
        g.drawString("Level: " + level + "(" + getOutput() + ")", dimension.x + 5, dimension.y + font.getSize() * 3 - 2);
    }

    void upgrade() {
        level++;
        updatePrice();
    }
}
