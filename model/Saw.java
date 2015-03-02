package model;

import java.awt.Rectangle;

import util.RandomNumberGenerator;

public class Saw {
	private int x, y, width, height, velY;
	private Rectangle rect;
	public static int MOVE_SPEED = 150;
	
	public Saw(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		velY =0;
		rect = new Rectangle(x, y, width, height);
	}
	
	/** Setter method for x */
	public void setX(int x) {
		this.x = x;
	}
	
	/** Setter method for y */
	public void setY(int y) {
		this.y = y;
	}
	
	/** Getter method for x */
	public int getX() {
		return x;
	}
	
	/** Getter method for y */
	public int getY() {
		return y;
	}
	
	/** Getter method for width */
	public int getWidth() {
		return width;
	}
	
	/** Getter method for height */
	public int getHeight() {
		return height;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void update(float delta) {
		this.velY += MOVE_SPEED * delta;
		y += this.velY * delta;
		if (y >= 450) {
			y = RandomNumberGenerator.getRandIntBetween(-400, -50);
			velY = RandomNumberGenerator.getRandIntBetween(100, 400);
		}
		updateRect();
	}
	
	public void scrollLeft(float delta, int velX) {
		x -= velX * delta;
		updateRect();
	}
	
	public void scrollRight(float delta, int velX) {
		x += velX * delta;
	}
	
	private void updateRect() {
		rect.setBounds(x, y, width, height);
	}
}