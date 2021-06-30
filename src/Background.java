import java.awt.Graphics;

public class Background {

	Image image1 = new Image("back1.png");
	Image image21 = new Image("back21.png");
	Image image22 = new Image("back22.png");
	Image image3 = new Image("back3.png");

	public int yOffset21 = 0;
	public int yOffset22 = 0;
	public int yOffset3 = 0;

	public Background() {

	}

	public void tick() {

	}

	public void render(Graphics graphics) {
		graphics.drawImage(image1.image, 0, 0, null);
		graphics.drawImage(image21.image, yOffset21, 0, null);
		if (yOffset21 <= 0) {
			graphics.drawImage(image21.image, GameContainer.width + yOffset21, 0, null);
		} else {
			graphics.drawImage(image21.image, -(GameContainer.width - yOffset21), 0, null);
		}
		graphics.drawImage(image22.image, yOffset22, 0, null);
		if (yOffset22 <= 0) {
			graphics.drawImage(image22.image, GameContainer.width + yOffset22, 0, null);
		} else {
			graphics.drawImage(image22.image, -(GameContainer.width - yOffset22), 0, null);
		}
		graphics.drawImage(image3.image, yOffset3, 0, null);
		if (yOffset3 <= 0) {
			graphics.drawImage(image3.image, GameContainer.width + yOffset3, 0, null);
		} else {
			graphics.drawImage(image3.image, -(GameContainer.width - yOffset3), 0, null);
		}

	}

	public void setOffset(int x) {
		if (x >= 0) {
			yOffset21 = GameContainer.width / 2 - Player.size / 2 - (int) ((x / 200) % GameContainer.width);
		} else {
			yOffset21 = GameContainer.width / 2 - Player.size / 2
					- (GameContainer.width - (int) ((Math.abs(x) / 200) % GameContainer.width));
		}
		if (x >= 0) {
			yOffset22 = GameContainer.width / 2 - Player.size / 2 - (int) ((x / 120) % GameContainer.width);
		} else {
			yOffset22 = GameContainer.width / 2 - Player.size / 2
					- (GameContainer.width - (int) ((Math.abs(x) / 120) % GameContainer.width));
		}
		if (x >= 0) {
			yOffset3 = GameContainer.width / 2 - Player.size / 2 - (int) ((x / 10) % GameContainer.width);
		} else {
			yOffset3 = GameContainer.width / 2 - Player.size / 2
					- (GameContainer.width - (int) ((Math.abs(x) / 10) % GameContainer.width));
		}
	}

}