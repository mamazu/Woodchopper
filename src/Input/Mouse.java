package Input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
    private boolean[] mouseButtons = new boolean[4];
    private Point position;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mouseButtons[mouseEvent.getButton()] = true;
        System.out.println("Pressed: " + mouseEvent.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mouseButtons[mouseEvent.getButton()] = false;
        System.out.println("Released: " + mouseEvent.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    public boolean leftMouseButtonDown(){
        return mouseButtons[1];
    }
    public boolean middleMouseButtonDown(){
        return mouseButtons[2];
    }
    public boolean rightMouseButtonDown(){
        return mouseButtons[3];
    }
    public Point getPosition(){
        return position;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        position = mouseEvent.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        position = mouseEvent.getPoint();
    }
}
