package GameObjects;

import java.awt.*;

public class UpgradePanel implements GameObject {
    private Upgrade[] upgradeArray = new Upgrade[2];
    private long wood;
    private int xOffset = 0;
    private Font scoreFont = new Font("default", Font.BOLD, 20);

    public UpgradePanel(int xOffset) {
        upgradeArray[0] = new Upgrade("Mouse Upgrade", 1);
        upgradeArray[1] = new Upgrade("Test U2");
        this.xOffset = xOffset;
        for(Upgrade upgrade : upgradeArray){
            upgrade.dimension.y += 30;
            upgrade.dimension.x += xOffset;
            upgrade.dimension.width = xOffset;
            upgrade.setBackgroundImage();
        }
    }

    public boolean click(Point p) {
        for (Upgrade upgrade : upgradeArray) {
            double price = upgrade.getPrice();
            if (upgrade.click(p) && price <= wood) {
                System.out.println(price + " " + wood);
                wood -= (long) price;
                upgrade.upgrade();
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(scoreFont);
        g.setColor(new Color(66,16,16));
        g.drawString(String.valueOf(wood),  xOffset + 5, 25);

        for (Upgrade upgrade : upgradeArray) {
            upgrade.draw(g);
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
