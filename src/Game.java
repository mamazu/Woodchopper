import GameObjects.GameObject;
import GameObjects.Tree;
import GameObjects.Upgrades;
import Input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Thread thread;
    private JFrame frame;
    private boolean running;
    private Mouse mouse = new Mouse();
    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public Game() {
        setupScreen();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        Rectangle rect = new Rectangle(0, 0, WIDTH / 2, HEIGHT);
        Tree tree = new Tree(rect);
        tree.loadImage();

        Upgrades upgrades = new Upgrades();
        upgrades.setXOffset(WIDTH / 2);
        gameObjects.add(tree);
        gameObjects.add(upgrades);
    }

    private void setupScreen() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Wood Chopper");
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        handleEvents();
    }

    private void handleEvents() {
        if (mouse.leftMouseButtonDown()) {
            for (GameObject g : gameObjects) {
                if (g.click(mouse.getPosition())) {
                    break;
                }
            }
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }
        Graphics grap = bs.getDrawGraphics();
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(grap);
        }
        grap.dispose();
        bs.show();
    }

    @Override
    public void run() {
        while (running) {
            update();
            render();
        }
        stop();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }
}
