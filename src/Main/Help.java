package Main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Main.Game.STATE;

/**
 * Klasa odpowiadajaca za utworzenie opcji POMOC z MENU
 * 
 * @author Jakub Ziemann
 *
 */
public class Help {

	private Background bg;

	public void render(Graphics g) {

		bg = new Background("/BackgroundImages/Help.png");
		bg.draw(g);

	}

	/**
	 * Jesli wcisnieto klawisz
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// pobierz kod klawisza
		int k = e.getKeyCode();

		if (Game.state == STATE.HELP) {
			// Jesli wcisnieto ESCAPE
			if (k == KeyEvent.VK_ESCAPE) {
				// Ustaw MENU
				Game.state = Game.STATE.MENU;
			}
		}

	}

	/**
	 * Jesli puszczono klawisz
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
