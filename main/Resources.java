package main;

//import java.applet.Applet;
//import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.net.URL;

import javax.imageio.ImageIO;

import animation.Animation;
import animation.Frame;

public class Resources {
		public static BufferedImage ground, flag, cloud1, cloud2, run1, run2, run3, run4, run5, run6, run7, startButton, startButtonPressed, logo;
		public static BufferedImage saw1, saw2, saw3;
		public static Animation runAnim, sawAnim;
		public static Color skyBlue;

	public static void load() {
		ground = loadImage("ground.png");
		flag = loadImage("flag.png");
		startButton = loadImage("startButton.png");
		logo = loadImage("Logo.png");
		run1 = loadImage("run_anim1.png");
		run2 = loadImage("run_anim2.png");
		run3 = loadImage("run_anim3.png");
		run4 = loadImage("run_anim4.png");
		run5 = loadImage("run_anim5.4.png");
		run6 = loadImage("run_anim5.5.png");
		run7 = loadImage("run_anim5.png");
		saw1 = loadImage("Saw.png");
		saw2 = loadImage("Saw2.png");
		saw3 = loadImage("Saw3.png");
		
		Frame f1 = new Frame(run1, .075f);
		Frame f2 = new Frame(run2, .075f);
		Frame f3 = new Frame(run3, .075f);
		Frame f4 = new Frame(run4, .075f);
		Frame f5 = new Frame(run5, .075f);
		Frame f6 = new Frame(run6, .075f);
		Frame f7 = new Frame(run7, .075f);
		runAnim = new Animation(f1, f2, f3, f4, f5, f6, f7);
		
		skyBlue = new Color(208, 244, 247);
		
		Frame f8 = new Frame(saw1, .05f);
		Frame f9 = new Frame(saw2, .05f);
		Frame f10 = new Frame(saw3, .05f);
		sawAnim = new Animation(f8, f9, f10);
		
	}

	/*private static AudioClip loadSound(String filename) {
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL);
	}*/

	private static BufferedImage loadImage( String filename ) {
		
		BufferedImage img = null;
		
		try {
			
			img = ImageIO.read( Resources.class.getResourceAsStream( "/resources/" + filename ) );
			
		} catch ( Exception e ) {
			
			System.out.println( "Error while reading: "+ filename );
			
			e.printStackTrace();
			
		}
		
		return img;
		
	}
}