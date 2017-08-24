package GameObjects;

import java.awt.*;

public class Upgrades implements GameObject{
    private Upgrade[] upgradeArray = new Upgrade[1];
    private int yOffset = 0;

    public Upgrades(){
        upgradeArray[0] = new Upgrade();
    }

    public void setXOffset(int xOffset){
        for (Upgrade upgrade : upgradeArray){
            upgrade.dimensions.x = xOffset;
            upgrade.dimensions.width = xOffset;
        }
    }

    @Override
    public boolean click(Point p) {
        return false;
    }

    @Override
    public void draw(Graphics g) {
        for (Upgrade upgrade : upgradeArray){
            upgrade.draw(g);
        }
    }
}
