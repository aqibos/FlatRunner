package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.GameMain;

public class scoreState extends State {
	private String playerScore;
	private Font font;

	public scoreState() {
		font = new Font("SansSerif", Font.BOLD, 25);
		playerScore = "You won!";
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		g.drawString("CONGRATULATIONS!", 500, 175);
		g.drawString(playerScore, 560, 250);
		g.drawString("Press any key.", 540, 350);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		setCurrentState(new MenuState());
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}