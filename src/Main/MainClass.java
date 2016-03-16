package Main;

import javax.swing.JFrame;

/**
 * Prosta gra jako przyklad aplikacji interaktywnej
 * 
 * @author Jakub Ziemann
 *
 */
public class MainClass {
	/**
	 * Szerokosc pola graficznego gry
	 */
	public static final int WIDTH = 1024;
	/**
	 * Wysokosc pola graficznego gry
	 */
	public static final int HEIGHT = 768;
	/**
	 * Tytul ramki
	 */
	public static final String TITLE = "Latajacy dywan";

	/**
	 * Metoda uruchamia gre. Ustawia parametry ramki.
	 * 
	 */
	public static void main(String[] args) {
		// Ustaw tytul
		JFrame frame = new JFrame(TITLE);
		// Ustaw pole rozmiary ramki
		frame.setSize(WIDTH, HEIGHT);
		// Nie pozwol na zmiane rozmiarow
		frame.setResizable(false);
		// Domyslne zamkniecie programu
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Wysrodkuj ramke wzgledem ekranu
		frame.setLocationRelativeTo(null);
		// Dodaj komponent klasy Game
		frame.add(new Game());
		// Wyswietl
		frame.setVisible(true);

	}// koniec main()
}// koniec class MainClass()
