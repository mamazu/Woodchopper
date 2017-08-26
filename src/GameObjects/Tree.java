package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Tree implements GameObject {
    private Rectangle dimension = new Rectangle(0, 0, 100, 200);
    private Image image;

    public Tree() {
    }

    public Tree(Rectangle rect) {
        dimension = rect;
    }

    public void loadImage() {
        try {
            BufferedImage bi = ImageIO.read(new File("/home/mamazu/IdeaProjects/WoodChopper/resources/tree.png"));
            image = bi.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.print("Could not find file");
            return;
        }
    }

    @Override
    public boolean click(Point p) {
        if (p.x < dimension.x || p.x > dimension.x + dimension.width) {
            return false;
        }
        return p.y >= dimension.y && p.y <= dimension.y + dimension.height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(173, 216, 230));
        g.fillRect(dimension.x, dimension.y, dimension.width, dimension.height);
        if (image != null)
            g.drawImage(image, dimension.x, dimension.y, null);
    }
}
