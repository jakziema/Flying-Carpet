package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiadajaca za zdarzenia zwiazane z obsluga klawiszy dziedziczaca po
 * klasie KeyAdapter
 * 
 * @author Jakub Ziemann
 *
 */
public class KeyInput extends KeyAdapter {
	Player p;
	Menu m;
	Help h;
	Stats s;

	public KeyInput(Player p, Menu m, Help h) {
		this.p = p;
		this.m = m;
		this.h = h;
//		this.s = s;

	}

	public void keyPressed(KeyEvent e) {
		
		p.keyPressed(e);
		m.keyPressed(e);
		h.keyPressed(e);
//		s.keyPressed(e);

	}

	public void keyReleased(KeyEvent e) {
		p.keyReleased(e);
		m.keyReleased(e);
		h.keyReleased(e);
//		s.keyReleased(e);
	}
}
