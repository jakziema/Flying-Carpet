package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Main.Game.STATE;

/**
 * Klasa odpowiadajaca za tworzenie MENU
 * 
 * @author Jakub Ziemann
 *
 */
public class Menu {
	/**
	 * zmienna prechowujaca aktualny wybor stanu gry
	 */
	private int currentChoice = 0;
	/**
	 * Tablica przechowujaca zmienne typu String z mozliwymi wyborami stanow gry
	 */
	private String[] options = { "Start", "Statystyki", "Pomoc", "Wyjście" };

	/**
	 * Inicjalizacja obiektu typu Background
	 */
	private Background bg;

	/**
	 * Funkcja odpowiadająca za rysowanie zawartości Menu
	 * 
	 * @param g
	 */
	public void render(Graphics g) {

		// Ustaw kolor
		g.setColor(new Color(128, 0, 0));
		// Ustaw czcionke
		g.setFont(new Font("Helvetica", Font.PLAIN, 50));
		// Utworz obiekt typu Background z tlem
		bg = new Background("/BackgroundImages/tlo2.png");
		// Ustaw tlo
		bg.draw(g);
		// Odrysuj tytul
		g.drawString("Latajacy dywan", MainClass.HEIGHT / 2, 300);
		// Ustaw czcionke zawartosci
		g.setFont(new Font("Helvetica", Font.PLAIN, 40));
		// petla odpowiadajaca za wyrysowanie mozliwych wyborow MENU
		for (int i = 0; i < options.length; i++) {
			// jezeli aktualny wybor
			if (i == currentChoice) {
				// zmien kolor na czerwony
				g.setColor(Color.RED);
			} else {
				// reszte wyborow ustaw na czarny
				g.setColor(Color.BLACK);
			}
			// odrysuj wybory
			g.drawString(options[i], 450, 768 / 2 + i * 40);
			System.out.println("Odrysuj MENU");
		}

	}

	/**
	 * Funckja odpowiadajca za obsluge wcisniecia klawisza
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		// Pobierz kod klawisza
		int k = e.getKeyCode();
		if (Game.state == STATE.MENU) {
			// Jesli wcisnieto ENTER
			if (k == KeyEvent.VK_ENTER) {
				// Wybierz akutalna opcje z MENU
				select();

				System.out.println("VK_ENTER");
			}
			// Jesli wcisnieto klawisz strzalki "w gore"
			if (k == KeyEvent.VK_UP) {

				currentChoice--;
				if (currentChoice == -1) {
					// Wybierz ostatnia opcje
					currentChoice = options.length - 1;
				}

				System.out.println("VK_UP");
			}

			if (k == KeyEvent.VK_DOWN) {
				currentChoice++;
				if (currentChoice == options.length) {
					// Wroc do pierwszej opcji
					currentChoice = 0;
				}
				System.out.println("VK_DOWN");
			}

			if (k == KeyEvent.VK_ESCAPE) {
				System.out.println("ESCAPE");
			}
		}

	}

	/**
	 * Funkcja odpowiadajaca za obsluge puszczenia klawisza
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Funkcja odpowiadająca za wybory z MENU, zmiane stanu gry
	 */
	private void select() {
		if (currentChoice == 0) {
			// start
			Game.state = Game.STATE.GAME;
		}

		if (currentChoice == 1) {
			// stats
			Game.state = STATE.STATS;
		}

		if (currentChoice == 2) {
			// help
			Game.state = STATE.HELP;
		}

		if (currentChoice == 3) {
			// quit
			System.exit(0);
		}
	}
}
