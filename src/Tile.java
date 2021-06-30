import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	
	private static int size = 32;
	private static int tileW;
	private static int tileH;
	
	private static Tile tile = new Tile("tile.png");

	private BufferedImage image;

	public Tile(String path) {
		try {
			image = ImageIO.read(Image.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileW = image.getWidth() / size;
		tileH = image.getHeight() / size;
		image.flush();
	}

	public static BufferedImage getTile(int x, int y, int size) {
		return tile.image.getSubimage(x * size, y * size, size, size);
	}

	public static BufferedImage getTile(int n, int size) {
		int x = n % tileW;
		int y = (n - x) / tileH;
		return tile.image.getSubimage(x * size, y * size, size, size);
	}

}