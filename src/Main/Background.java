package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Klasa odpowiadajaca za tworzenie tla
 * 
 * @author Jakub Ziemann
 *
 */
public class Background {
	/**
	 * Zdjecie tla
	 */
	public BufferedImage image;
	/**
	 * Wspolrzedna x tla
	 */
	public double x;
	/**
	 * Wspolrzedna y tla
	 */
	public double y;

	/**
	 * Konstruktory odpowiadajcy za ustawienie zdjecia tla
	 * 
	 * @param s
	 */
	public Background(String s) {

		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Odrysuj tlo
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {

		g.drawImage(image, (int) x, (int) y, null);

	}
}
