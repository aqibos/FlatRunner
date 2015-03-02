package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.GameMain;
import main.Resources;

public class MenuState extends State {

	@Override
	public void init() {
		//System.out.println("Entered MenuState");
	}

	@Override
	public void update(float delta) {
		// Do Nothing
	}

	@Override
	public void render(Graphics g) {
		//Draw Sky
		g.setColor(Resources.skyBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.drawImage(Resources.startButton, 600, 300, null);
		g.drawImage(Resources.logo, 500, 50, null);
		for(int x = 0; x < GameMain.GAME_WIDTH; x += 100){
			g.drawImage(Resources.ground, x, 400, null);
		}
	}

	@Override
	public void onClick(MouseEvent e) {
		//System.out.println("On Click!");
		int x = e.getX();
		int y = e.getY();
		if (x >= 500 && x <= 700 && y >= 300 && y <= 350) {
			setCurrentState(new PlayState());
		}
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			setCurrentState(new PlayState());
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		//System.out.println("On KeyRelease!");
	}

}
