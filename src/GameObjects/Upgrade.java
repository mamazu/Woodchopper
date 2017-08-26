package GameObjects;

import java.awt.*;

public class Upgrade implements GameObject {
    public static final int UPGRADE_HEIGHT = 60;
    private final Font font = new Font(null, Font.PLAIN, 20);
    private final Font levelFont = new Font(null, Font.PLAIN, 15);
    Rectangle dimension;

    private int level = 0;
    private double output = 1;
    private double price = 1;
    private String name = "Test Product";

    public Upgrade(int index, String name) {
        this(index, name, 0);
    }

    public Upgrade(int index, String name, int startLevel) {
        dimension = new Rectangle(0, index * (UPGRADE_HEIGHT + 5), 0, UPGRADE_HEIGHT);
        this.name = name;
        this.level = startLevel;
        updatePrice();
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
        g.setColor(Color.RED);
        g.fillRect(dimension.x, dimension.y, dimension.width, dimension.height);

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

    public void upgrade() {
        level++;
        updatePrice();
    }
}
