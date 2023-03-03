/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author fill
 */
public class Plane {
    private int x;
    private int y;
    private int width;
    private int height;
    private int velocity;

    public Plane(int x, int y, int width, int height, int velocity) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }

    public void move() {
        y += velocity;
    }

    public void jump() {
        velocity = -10; // Change this value to adjust the jump height
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

