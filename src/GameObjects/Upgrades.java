package GameObjects;

import java.awt.*;

public class Upgrades implements GameObject{
    private Upgrade[] upgradeArray = new Upgrade[2];
    private int yOffset = 0;

    public Upgrades(){
        upgradeArray[0] = new Upgrade(0, "Test U1");
        upgradeArray[1] = new Upgrade(1, "Test U2");

    }

    public void setXOffset(int xOffset){
        for (Upgrade upgrade : upgradeArray){
            upgrade.dimension.x = xOffset;
            upgrade.dimension.width = xOffset;
        }
    }

    @Override
    public boolean click(Point p) {
        for (Upgrade upgrade : upgradeArray){
            if(upgrade.click(p)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        for (Upgrade upgrade : upgradeArray){
            upgrade.draw(g);
        }
    }
}
