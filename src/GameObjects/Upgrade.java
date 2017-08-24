package GameObjects;

import java.awt.*;

public class Upgrade implements GameObject {
    public static final int UPGRADE_HEIGHT = 60;
    private final Font font = new Font(null, Font.PLAIN, 20);
    Rectangle dimension;

    private int amount = 0;
    private double price = 0;
    private String name = "Test Product";

    private int index;

    public Upgrade(){
        this(0);
    }

    public Upgrade(int index){
        this(index, "");
    }

    public Upgrade(int index, String name){
        this.index = index;
        dimension = new Rectangle(0, index*(UPGRADE_HEIGHT + 5), 200, UPGRADE_HEIGHT);
        this.name = name;
    }

    @Override
    public boolean click(Point p) {
        if (p.x < dimension.x || p.x > dimension.x + dimension.width) {
            return false;
        }
        boolean hit = p.y >= dimension.y && p.y <= dimension.y + dimension.height;
        if (hit) {
            price++;
            System.out.println("Hit Upgrade: " + index);
        }
        return hit;
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
        g.drawString(String.valueOf(price), 5 + dimension.x, dimension.y + font.getSize() * 2);
    }
}
