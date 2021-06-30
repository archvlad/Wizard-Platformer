import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class GameContainer {

	public static int width = 320;
	public static int height = 240;
	public static int scale = 3;
	public static String title = "Game";

	GameManager gameManager;
	Window window;

	Graphics2D graphics;
	BufferStrategy bufferStrategy;

	public GameContainer() {
		window = new Window(title, width * scale, height * scale);
		gameManager = new GameManager();
	}

	public void start() {
		window.canvas.requestFocus();
		long previous = System.currentTimeMillis();
		long current = 0;
		long elapsed = 0;
		double timePerTick = 1000.0 / 60.0;
		while (true) {
			current = System.currentTimeMillis();
			elapsed += current - previous;
			previous = current;
			while (elapsed >= timePerTick) {
				tick();
				elapsed -= timePerTick;
			}
			render();
		}
	}

	public void tick() {
		gameManager.tick();
	}

	public void render() {
		bufferStrategy = window.canvas.getBufferStrategy();
		if (bufferStrategy == null) {
			window.canvas.createBufferStrategy(2);
			return;
		}
		graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		graphics.scale(scale, scale);
		gameManager.render(graphics);
		graphics.dispose();
		bufferStrategy.show();
	}

}