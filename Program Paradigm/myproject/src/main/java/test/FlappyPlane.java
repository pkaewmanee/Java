/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

/**
 *
 * @author fill
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlappyPlane extends JPanel implements KeyListener, Runnable {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private Plane plane;
    private List<PlaneObstacle> obstacles;
    private boolean gameOver;
    private int score;
    private int frameCount;

    public FlappyPlane() {
        plane = new Plane(50, HEIGHT / 2 - 25, 50, 50, 0);
        obstacles = new ArrayList<>();
        gameOver = false;
        score = 0;
        frameCount = 0;
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread thread = new Thread(this);
        thread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(plane.getX(), plane.getY(), plane.getWidth(), plane.getHeight());
        for (PlaneObstacle obstacle : obstacles) {
            g.setColor(Color.GREEN);
            g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        }
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
        if (gameOver) {
            g.drawString("Game Over!", WIDTH / 2 - 40, HEIGHT / 2 - 20);
        }
    }

    public void run() {
        while (true) {
            if (!gameOver) {
                frameCount++;
                if (frameCount % 100 == 0) {
                    int obstacleY = (int) (Math.random() * (HEIGHT - 200) + 100);
                    obstacles.add(new PlaneObstacle(WIDTH, obstacleY, 50, 300));
                }
                for (int i = 0; i < obstacles.size(); i++) {
                    PlaneObstacle obstacle = obstacles.get(i);
                    obstacle.move();
                    if (plane.getX() < obstacle.getX() + obstacle.getWidth()
                            && plane.getX() + plane.getWidth() > obstacle.getX()
                            && plane.getY() < obstacle.getY() + obstacle.getHeight()
                            && plane.getY() + plane.getHeight() > obstacle.getY()) {
                        gameOver = true;
                    }
                    if (obstacle.getX() + obstacle.getWidth() < 0) {
                        obstacles.remove(i);
                        score++;
                        i--;
                    }
                }
                plane.move();
                if (plane.getY() < 0 || plane.getY() > HEIGHT) {
                    gameOver = true;
                }
                repaint();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            plane.jump();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Plane");
        FlappyPlane flappyPlane = new FlappyPlane();
        frame.add(flappyPlane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
