package state;

import model.Player;
import model.Saw;
import util.RandomNumberGenerator;
import main.GameMain;
import main.Resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayState extends State{
	//Constants
	public static final int PLAYER_HEIGHT = 100;
	public static final int PLAYER_WIDTH = 50;
	public static final int PLATFORM_HEIGHT = 50;
	public static final int PLATFORM_WIDTH = 100;
	public static final int SAW_WIDTH = 50;
	public static final int SAW_HEIGHT = 50;
	public static final int NUM_SAWS = 1;
	public static final int SAW_SPEED = 100;
	public static final int SCROLL_SPEED = 700;
	
	//Primitive Var's
	private int playerStartX = 100;
	private int playerStartY = 300;
	private int offsetY, offsetX;
	private int sawY;
	private float delta;
	private boolean isMovingLeft, isMovingRight;
	private int score, maxScore;
	private Font scoreFont;
	private Rectangle flagRect;
	
	//Objects
	private Player player;
	private Saw [][] allSaws; 

	@Override
	public void init() {
		//scrollSpeed = 0;
		offsetY = 0;
		offsetX = 0;
		sawY = 450;
		delta = 0;
		score = 0;
		maxScore = 0;
		isMovingLeft = false;
		isMovingRight = false;
		scoreFont = new Font("SansSerif", Font.BOLD, 25);
		flagRect = new Rectangle(GameMain.GAME_WIDTH - 75, 300, 50, 100);
		player = new Player(playerStartX, playerStartY, PLAYER_WIDTH, PLAYER_HEIGHT);
		allSaws = new Saw [20][NUM_SAWS];
		
		for (int i = 0; i < allSaws.length; i++) {
			for (int j = 0; j < NUM_SAWS; j++) {
				allSaws[i][j] = new Saw(200 + offsetX, RandomNumberGenerator.getRandIntBetween(0, 400), SAW_WIDTH, SAW_HEIGHT);
				//sawY -= 250;
			}
			//sawY = 450;
			//offsetY += 50;
			offsetX +=50;
		}
	}

	@Override
	public void update(float delta) {
		this.delta = delta;
	    for (int i = 0; i < allSaws.length; i++) {
			for (int j = 0; j < NUM_SAWS; j++) {
				allSaws[i][j].update(delta);
			}
		}
		
		Resources.sawAnim.update(delta);
		Resources.runAnim.update(delta);
		player.update(delta, false);
		
		 for (int i = 0; i < allSaws.length; i++) {
				for (int j = 0; j < NUM_SAWS; j++) {
					if (allSaws[i][j].getRect().intersects(player.getRect())) {
						player.reset(playerStartX, playerStartY);
					}
				}
			}
		
		 score = (player.getX() - 100) / 50;
		 if (score > maxScore) {
			 maxScore = score;
		 }
		 
		 if (player.getRect().intersects(flagRect)) {
			 setCurrentState(new scoreState());
		 }
	}

	@Override
	public void render(Graphics g) {
		//Draw Sky
		g.setColor(Resources.skyBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		
		//Draw Ground
				for(int x = 0; x < GameMain.GAME_WIDTH; x += 100){
					g.drawImage(Resources.ground, x, 400, null);
				}
		
		//Draw Saws
		for (int i = 0; i < allSaws.length; i++) {
			for (int j = 0; j < NUM_SAWS; j++) {
				Resources.sawAnim.render(g, allSaws[i][j].getX(), allSaws[i][j].getY(), SAW_WIDTH, SAW_HEIGHT);
			}
		}
		
		//Draw Player
		if (isMovingLeft || isMovingRight) {
			Resources.runAnim.render(g, (int) player.getX(),
				(int) player.getY(), PLAYER_WIDTH, PLAYER_HEIGHT);
		} else {
			g.drawImage(Resources.run1, player.getX(), player.getY(), null);
		}
		
		g.setFont(scoreFont);
		g.setColor(Color.GRAY);
		g.drawString("SCORE: " + maxScore, 15, 45); 
		
		//Draw Flag
		g.drawImage(Resources.flag, GameMain.GAME_WIDTH - 75, 300, null);
		
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			isMovingRight = true;
			player.moveRight(delta);
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
			isMovingLeft = true;
			player.moveLeft(delta);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.jump();
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
			stoppedMoving();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			
		}
	}

	public void stoppedMoving() {
		isMovingLeft = false;
		isMovingRight = false;
		player.stopMoving();
	}
}
