package Input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public class Mouse implements MouseMotionListener, MouseListener {
    private Point position;
    private LinkedList<Integer> mouseClicks = new LinkedList<Integer>();

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int button = mouseEvent.getButton();
        if (mouseClicks.size() < 1) {
            mouseClicks.addLast(button);
        } else if (button != mouseClicks.getLast()) {
            mouseClicks.addLast(button);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    public Integer getLastPushedButton() {
        if (mouseClicks.size() > 0) {
            Integer last = mouseClicks.getLast();
            mouseClicks.removeLast();
            return last;
        }
        return -1;
    }

    public Point getPosition() {
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
