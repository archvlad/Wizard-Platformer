import java.awt.Graphics;

public class Map {
	
	public int size = 32;
	public int width = 35;
	public int height = 8;
	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public static int[] map = {0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0, 0, 0,0,0, 0, 0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0, 0, 0,0,0, 0, 0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0, 0, 0,0,0, 0, 0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 0,1,3, 0, 0,0,0, 0, 0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0, 0,16,17,18, 0, 6, 7, 8, 0, 0,6,8, 0, 0,1,3, 0, 0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,1,3, 0, 0, 0, 0, 0, 6, 7, 8, 0, 0,6,8, 0, 0,6,8, 0, 0,1,3,0,0,0,0,0,0,0,
							   0,0,0,0,1,4,5,8,14,14,14,14,14, 6, 7, 8,14,14,6,8,14,14,6,8,14,14,6,8,0,0,0,0,0,0,0,
							   1,2,2,4,5,7,7,9,10, 2, 2, 2, 4, 5, 7, 9,10, 4,5,9,10,4,5,9,10, 4,5,9,10,2,2,2,2,2,3};

	public void render(Graphics graphics) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[x + y * width] > 0) {
					graphics.drawImage(Tile.getTile(map[x + y * width] - 1, 32), xOffset + (x * size), yOffset + (y * size), null);
					//graphics.setColor(new Color(0, 128, 0));
					//graphics.fillRect(xOffset + (x * size), yOffset + (y * size), size, size);
				}
			}
		}
	}

}