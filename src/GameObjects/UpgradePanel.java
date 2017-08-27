package GameObjects;

import java.awt.*;

public class UpgradePanel implements GameObject {
    private Upgrade[] upgradeArray = new Upgrade[2];
    private Rectangle dimension;
    private long wood;
    private Font scoreFont = new Font("default", Font.BOLD, 20);
    private final int upgradeHeight;

    public UpgradePanel(Rectangle dimension) {
        this.dimension = dimension;
        upgradeArray[0] = new Upgrade("Mouse Upgrade", dimension.width, 1);
        upgradeArray[1] = new Upgrade("Test U2", dimension.width);
        upgradeHeight = upgradeArray[0].dimension.height + 5;
        for (Upgrade upgrade : upgradeArray) {
            upgrade.setBackgroundImage();
        }
    }

    public boolean click(Point p) {
        p.translate(-dimension.x, -dimension.y - scoreFont.getSize() - 8);
        if (p.x < 0 || p.y < 0) return false;
        for (Upgrade upgrade : upgradeArray) {
            double price = upgrade.getPrice();
            if (upgrade.click(p) && price <= wood) {
                wood -= (long) price;
                upgrade.upgrade();
                return true;
            }
            p.translate(0, -upgradeHeight);
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(scoreFont);
        g.setColor(new Color(66, 16, 16));
        g.drawString(String.valueOf(wood), dimension.x + 5, scoreFont.getSize() + 2);

        for (int i = 0; i < upgradeArray.length; i++) {
            Upgrade upgrade = upgradeArray[i];
            Point location = dimension.getLocation();
            location.translate(0, scoreFont.getSize() + 8 + upgradeHeight * i);
            upgrade.draw(g, location);
        }
    }

    public void mouseClick() {
        wood += upgradeArray[0].getOutput();
    }

    public void update() {
        for (int i = 0; i < upgradeArray.length; i++) {
            if (i == 0) continue;

            wood += upgradeArray[i].getOutput();
        }
    }

}
