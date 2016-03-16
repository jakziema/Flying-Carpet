package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Glowny obszar graficzny gry Klasa dziedziczaca po klasie JPanel i
 * implementujaca interfejs ActionListener
 * 
 * @author Jakub Ziemann
 *
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {

	/**
	 * Typ wyliczeniowy reprezentujacy aktualny stan gry
	 *
	 */
	public enum STATE {
		MENU, 
		GAME, 
		HELP, 
		STATS;
	}

	/**
	 * Poczatkowy stan gry
	 */
	public static STATE state = STATE.MENU;
	/**
	 * inicjalizacja obiektu typu Menu
	 */
	private Menu menu;
	/**
	 * Inicjalizacja obiektu typu Help
	 */
	private Help help;
	/**
	 * Inicjalizacja obiektu typu Stats
	 */
	private Stats stats;
	/**
	 * Inicjalizacja obiektu typu Background
	 */
	private Background bg;

	/**
	 * Glowna petla gry
	 */
	private Timer gamelooptimer;
	// Inicjalizacja obiektu klasy Player
	private Player player;

	/**
	 * Konstruktor klasy pola graficznego gry. Początkowe ustawienia Dodanie
	 * obsługi zdarzeń.
	 */
	public Game() {
		// komponent posiada focus
		setFocusable(true);

		// utworz licznik odswiezajacy okno gry co 5 ms
		gamelooptimer = new Timer(5, this);
		
		// start licznika
		gamelooptimer.start();
		// utworz postac
		player = new Player();
		// ustaw pozycje postaci na osi y
		player.setY(250);
		// utworz obiekt klasy Menu
		menu = new Menu();
		// utworz obiekt klasy Help
		help = new Help();
		// utworz obiekt klasy Stats
		stats = new Stats();
		// Dodaj obsluge zdarzen zdefiniowana w klasie KeyInput
		addKeyListener(new KeyInput(player, menu, help));
		addKeyListener(stats);

		

	}

	/**
	 * Zaleznie od stanu odrysuj pole graficzne
	 */
	public void paint(Graphics g) {
		// wywolaj konstruktor klasy nadrzednej
		super.paint(g);

		// jesli rozpoczeto Gre
		if (state == STATE.GAME) {

			// Stworz obiekt klasy Background
			bg = new Background("/BackgroundImages/tloMenu.png");
			// Odrysuj tlo
			bg.draw(g);
			// Odrysuj postac
			player.draw(g);
			// Ustaw kolor
			g.setColor(new Color(128, 0, 0));
			// Ustaw czcionke
			g.setFont(new Font("Helvetica", Font.PLAIN, 70));
			// Odrysuj liczbe punktow
			g.drawString(Integer.toString(player.getPunkty()), 250, MainClass.HEIGHT - 30);
			player.update();
			// Jesli wybrano Menu
		} else if (state == STATE.MENU) {
			// Odrysuj menu
			menu.render(g);
			
			// Przywroc domyslna pozycje postaci
			player.y = 250;
			// Jesli wyrano Pomoc
		} else if (state == STATE.HELP) {
			// Odrysuj Pomoc
			help.render(g);
			// Jesli wybrano Statystki
		} else if (state == STATE.STATS) {
			// Odrysuj Statystyki
			stats.render(g);
			
		}

	}

	/**
	 * Metoda ta jest wywolywana , kiedy zostanie wygenerowane zdarzenie na
	 * obiekcie powiaznaym z danym sluchaczem
	 */
	public void actionPerformed(ActionEvent e) {
		// jesli wybrano Gre
//		if (state == STATE.GAME) {
//			// Aktualizuj pozycje gracza
//			player.update();
//			
//			
//
//		}
		// Odrysuj
		repaint();
		
		
		

	}
	
	
}
