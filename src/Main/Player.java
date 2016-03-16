package Main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Main.Game.STATE;

/**
 * Klasa modelujaca postac Alladyna
 * 
 * @author Jakub Ziemann
 *
 */

public class Player {
	/**
	 * Zdjecie obiektu
	 */
	public BufferedImage image;
	/**
	 * Sciezka do zdjecia
	 */
	public String imagePath = "/PlayerImages/alladin.png";
	/**
	 * Sciezka do dzwieku przy podnoszeniu postaci
	 */
	public String soundPathUp = "Resources/Sounds/slideWhistle.WAV";
	/**trtrt
	 * Sciezka do dzwieku przy opadaniu postaci
	 */
	private String soundPathDown = "Resources/Sounds/slideWhistleDown.WAV";
	/**
	 * Wspolrzedna x obiektu
	 */
	public int x;
	/**
	 * Wspolrzedna y obiektu
	 */
	public int y;
	/**
	 * Szybkosc przesuniecia obiektu na osi y
	 */
	public int velY = 0;
	/**
	 * Ilosc punktow
	 */
	public int punkty;
	/**
	 * Czas wcisniecia klawisza Spacji
	 */
	public long startTime;
	/**
	 * Czas puszczenia klawisza Spacji
	 */
	public long endTime;
	/**
	 * Roznica pomiedzy czasem puszczenia a wcisnieciem klawisza Spacji
	 */
	public long deltaTime;
	/**
	 * Zamiana roznicy na sekundy
	 */
	public double elapsedSeconds;

	/**
	 * Czy wcisnieto klawisz Spacji
	 */

	public Boolean keyPressed = false;

	/*
	 * Funkcja odpowiadajaca za uaktualnianie pozycji postaci
	 */
	public void update() {
		// Do akutalnej wspolrzednej y dodaj predkosc przemieszczania sie
		y += velY;
		//
		if (y < 150) {
			y = 150;

		}
		// Jesli wcisnieto klawisz
		if (keyPressed) {
			// Zwieksz punkty
			punkty++;
		}
		// Jesli osiagnieto gorny pulap
		if (y > 250) {
			y = 250;
		}

	}

	/**
	 * Ustaw wspolrzedna y postaci
	 * 
	 * @param i
	 */
	public void setY(int i) {
		y = i;
	}

	/**
	 * Ustaw wspolrzedna x postaci
	 * 
	 * @param i
	 */
	public void setX(int i) {
		x = i;
	}

	/**
	 * Pobierz wspolrzedna y postaci
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Pobierz ilosc punktow
	 * 
	 * @return
	 */
	public int getPunkty() {
		return punkty;
	}

	/**
	 * Ustaw ilosc punktow
	 * 
	 * @param i
	 */
	public void setPunkty(int i) {
		punkty = i;
	}

	/*
	 * Odrysuj postac
	 */
	public void draw(Graphics g) {

		g.drawImage(getPlayerImage(imagePath), 350, y, null);

	}

	/**
	 * Funkcja pobierania obiektu klasy BufferedImage na podstawie sciezki
	 * dostepu podanej jako String
	 * 
	 * @param s
	 * @return
	 */
	public BufferedImage getPlayerImage(String s) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	public void keyPressed(KeyEvent e) {
		// pobierz kod wcisnietego klawisza
		int key = e.getKeyCode();
		// jesli wybrano Gre
		if (Game.state == STATE.GAME) {
			if (keyPressed == false) {
				if (key == KeyEvent.VK_SPACE) {
					// Unos postac
					velY = -4;
					// Pobierz czas wcisniecia klawisza
					startTime = System.currentTimeMillis();

					// System.out.println("Start Time: " + startTime + " ms.");
					// Wcisnieto klawisz
					keyPressed = true;
					// Odtworz dźwiek
					playSound(new File(soundPathUp));

				}
			}
			// Jesli wcisnieto ESCAPE
			if (key == KeyEvent.VK_ESCAPE) {
				// Wroc do MENU
				Game.state = STATE.MENU;
				
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (Game.state == STATE.GAME) {
			if (keyPressed == true) {
				if (key == KeyEvent.VK_SPACE) {
					// Imitacja "grawitacji"
					velY = 4;
					// Odtworz dzwiek opadania
					playSound(new File(soundPathDown));
					// Pobierz czas pusczenia klawisza
					endTime = System.currentTimeMillis();
					// Roznica pomiedzy czasem puszczenia a wcisniecia klawisza
					deltaTime = endTime - startTime;
					// Zamien na sekundy
					elapsedSeconds = deltaTime / 1000.0;

					// System.out.println("End time : " + endTime + " ms.");
					// System.out.println("Delta time: " + deltaTime + " ms.");
					// System.out.println("ElapsedSeconds: " + elapsedSeconds +
					// " s.");
					// Klawisz puszczono
					keyPressed = false;
					// Wywolaj frame
					JFrame nameInputFrame = new JFrame("IMIE");
					// Popup z mozliwoscia wpisania nazwy uztykownika
					String name = JOptionPane.showInputDialog(nameInputFrame, "Podaj swoje imię");
					// Ustaw format daty
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
					// Pobierz date
					Date date = new Date();
					// Utworz String do zapisu
					String stringToWrite = "Gracz:" + name + "---Punkty:" + Integer.toString(getPunkty()) + "---Czas:"
							+ elapsedSeconds + "s---Data:" + dateFormat.format(date);

					File scoreFile = new File("scores.txt");
					// Jesli plik nie istnieje
					if (!scoreFile.exists()) {
						try {
							// Stworz plik
							scoreFile.createNewFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;

					try {
						fileWriter = new FileWriter(scoreFile, true);
						bufferedWriter = new BufferedWriter(fileWriter);
						// zapisz String do pliku txt
						bufferedWriter.write(stringToWrite);
						// Utworz nowa linie
						bufferedWriter.newLine();

					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						// Jesli koniec
						if (bufferedWriter != null)
							try {
								// Zakmnij strumien
								bufferedWriter.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					// zeruj czas wcisniecia i pusczenia klawisza
					resetTime();
					// ustaw punkty na 0
					setPunkty(0);

				}
			}

		}

	}

	/**
	 * Pobierz czas wcisniecia klawisza SPACJI
	 * 
	 * @return czas
	 */
	public double getTime() {
		return elapsedSeconds;
	}

	/**
	 * Funkcja zerujaca liczniki potrzebne do zmierzenia dlugosci wcisniecia
	 * klawisza spacji
	 */
	public void resetTime() {
		startTime = 0;
		endTime = 0;
		deltaTime = 0;
		elapsedSeconds = 0;
	}

	/**
	 * Funkcja odtwarzania dzwieku z pliku
	 * 
	 * @param f
	 *            - obiekt klasy File reprezentujacy sciezke do pliku w formacie
	 *            WAV
	 */
	public static synchronized void playSound(final File f) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

}
