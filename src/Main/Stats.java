package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Main.Game.STATE;

/**
 * Klasa odpowiadajaca za utworzenie opcji STATYSTYKI z MENU
 * 
 * @author Jakub Ziemann
 *
 */
public class Stats extends KeyAdapter{

	/**
	 * Inicjalizacja typu Background
	 */
	private Background bg;
	/**
	 * Utworz obiekty typu File ze sciezka dostepu do pliku txt
	 */
	private File fileWithScores = new File("scores.txt");
	/**
	 * Interfejs list przechowujacy tekst pobrany z pliku txt
	 */
	public ArrayList<String> scoresList;

	/**
	 * Funkcja odpowiadajaca za rysowanie zawartosci Statystyk
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		// Ustaw tlo
		bg = new Background("/BackgroundImages/Stats.png");
		// Odrysuj tlo
		bg.draw(g);
		// Ustaw kolor
		g.setColor(new Color(128, 0, 0));
		// Ustaw czcionke
		g.setFont(new Font("Helvetica", Font.PLAIN, 30));

		g.drawString("STATYSTYKI", 768 / 2, 40);
		// ustaw czcionke do wyrysowania zawartosci pliku txt
		g.setFont(new Font("Helvetica", Font.PLAIN, 20));
		// Jesli plik istnieje
		if (fileWithScores.exists()) {
			// Pobierz zawartosc pliku txt
			getScoresFromTxt();

			for (int i = 0; i < scoresList.size(); i++) {
				// Odrysuj zawartosc pliku txt - wyniki
				g.drawString(Integer.toString(i + 1) + "." + scoresList.get(i), 150, 100 + i * 40);

			}
		} else {
			// Jesli plik nie istnieje
			g.drawString("Brak ostatnich wyników. ", 150, 100);
		}

	}

	/**
	 * Funkcja odpowiadajaca za obsluge wcisniecia klawisza
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		// pobierz kod klawisza
		int k = e.getKeyCode();

		if (Game.state == STATE.STATS) {
			// jesli wcisnieto klawisz ESCAPE
			if (k == KeyEvent.VK_ESCAPE) {
				// wroc do menu
				Game.state = Game.STATE.MENU;

			}
		}

	}

	/**
	 * Funkcja odpowiadajaca za obsluge puszczenia klawisza
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));

	}

	/**
	 * Funkcja odpowiadajca za czytanie zawartosci pliku txt
	 */
	public void getScoresFromTxt() {

		Scanner scanner;
		try {
			scanner = new Scanner(fileWithScores);

			scoresList = new ArrayList<String>();
			while (scanner.hasNext()) {

				scoresList.add(scanner.next());
			}
			scanner.close();

		} catch (FileNotFoundException e) {

			System.err.println(e.getMessage());

		}

	}

}
