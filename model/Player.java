package model;

import main.GameMain;
import java.awt.Rectangle;

public class Player {
	private int x, y;
	private int width, height;
	private int velY, velX;
	private Rectangle rect;
	private boolean isMovingRight, isMovingLeft;
	
	private static final int JUMP_VELOCITY = -600;
	private static final int ACCEL_GRAVITY = 1800;
	private static final int MOVE_SPEED = 800;
	
	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		velY = 0;
		velX =0;
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
	
	public void reset(int x, int y) {
		this.x = x;
		this.y = y;
		velX = 0;
	}
	
	public void update(float delta, boolean bool) {
		if(!bool) {
			if (!isGrounded()) {
				velY += ACCEL_GRAVITY * delta;
			}
			else {
				y = 300;
				velY = 0;
			}
			if (isMovingRight) {
				velX += MOVE_SPEED * delta;
			} else if (isMovingLeft){
				velX -= MOVE_SPEED * delta;
			} else {
				velX = 0;
			}
			x += velX * delta;
			y += velY * delta;
			updateRect();
		}
		if (x < 0) {
                    x = 0;
		}
                if (x > GameMain.GAME_WIDTH - 50) {
                    x = GameMain.GAME_WIDTH - 50;
                }
	}
	
	private void updateRect() {
		rect.setBounds(x, y, width, height);
	}
	
	public void jump() {
		if (isGrounded()) {
			y -= 10;
			velY = JUMP_VELOCITY;
		}
	}
	
	public void moveRight(float delta) {
		isMovingRight = true;
	}
	
	public void moveLeft(float delta) {
		isMovingLeft = true;
	}
	
	public void stopMoving() {
		isMovingLeft = false;
		isMovingRight = false;
	}
	
	private boolean isGrounded() {
		return y >= 300;
	}

}
