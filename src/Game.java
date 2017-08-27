import GameObjects.Tree;
import GameObjects.UpgradePanel;
import Input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static java.lang.Thread.sleep;

public class Game extends Canvas implements Runnable {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Thread thread;
    private boolean running;
    private Mouse mouse = new Mouse();

    private Tree tree;
    private UpgradePanel upgrades;

    public Game() {
        setupScreen();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        Rectangle rect = new Rectangle(0, 0, WIDTH / 2, HEIGHT);
        tree = new Tree(rect);
        tree.loadImage();

        upgrades = new UpgradePanel(WIDTH /2);
    }

    private void setupScreen() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Wood Chopper");
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    private void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        if (mouse.getLastPushedButton() == 1) {
            Point p = mouse.getPosition();
            if (tree.click(p)) {
                upgrades.mouseClick();
            }
            upgrades.click(p);
        }
        upgrades.update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.clearRect(0, 0, WIDTH, HEIGHT);

        tree.draw(graphics);
        upgrades.draw(graphics);

        graphics.dispose();
        bs.show();
    }

    @Override
    public void run() {
        while (running) {
            update();
            render();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }
}
