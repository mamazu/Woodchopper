package GameObjects;

import java.awt.*;

public class Upgrade implements GameObject {
    public static final int UPGRADE_HEIGHT = 50;
    Rectangle dimensions;

    private int amount = 0;
    private double price = 0;
    private String name = "Test Product";

    public Upgrade(){
        this(0);
    }

    public Upgrade(int index){
        dimensions = new Rectangle(0, index*UPGRADE_HEIGHT, 200, UPGRADE_HEIGHT);
    }

    @Override
    public boolean click(Point p) {
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(dimensions.x, dimensions.y, dimensions.width, dimensions.height);

        // Drawing the text
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.PLAIN, 12));
        g.drawString(name, 100, 100);
    }
}
