import java.awt.Color;
import java.awt.Graphics;

public class GameManager {

	Player player;
	Map map;
	Background background;

	public GameManager() {
		map = new Map();
		background = new Background();
		player = new Player(map, background);
	}
	
	public void tick() {
		player.tick();
		background.tick();
	}

	public void render(Graphics graphics) {
		clearScreen(graphics);
		background.render(graphics);
		map.render(graphics);
		player.render(graphics);
	}

	public void clearScreen(Graphics graphics) {
		graphics.setColor(new Color(0, 0, 0));
		graphics.fillRect(0, 0, 854, 640);
	}

	public static void main(String[] args) {
		GameContainer gameContainer = new GameContainer();
		gameContainer.start();
	}

}