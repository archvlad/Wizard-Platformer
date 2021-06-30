import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player {

	public double x = 0;
	public double y = 0;
	public static int size = 32;

	public boolean right, left, jump;

	public double g = 0.5;
	public double v = 0;
	public double jumpv = -8;
	public int speed = 2;

	Image image = new Image(Tile.getTile(0, 4, 32));

	Map map;
	Background background;

	public Player(Map map, Background back) {
		this.map = map;
		this.background = back;
		setOffsets();
	}

	public void tick() {
		processInput();
		if (jump) {
			jump();
			jump = false;
		}
		setLeftSide();
		if (left) {
			moveLeft();
		}
		if (x <= leftSide) {
			x = leftSide + 1;
			setOffsets();
		}
		setRightSide();
		if (right) {
			moveRight();
		}
		if (x + size - 1 >= rightSide) {
			x = rightSide - size;
			setOffsets();
		}
		setDownSide();
		setUpSide();
		gravity();
		if (y <= upSide) {
			y = upSide + 1;
			v = 0;
		}
		if (y + size - 1 >= downSide) {
			y = downSide - size;
			canJump = true;
			v = 0;
		} else  {
			canJump = false;
		}
		setOffsets();
	}

	public void render(Graphics graphics) {
		graphics.drawImage(image.image, GameContainer.width / 2 - size / 2, map.yOffset + (int) y, null);
	}

	public void setOffsets() {
		map.xOffset = GameContainer.width / 2 - size / 2 - (int) x;
		background.setOffset((int) x);
	}

	boolean canJump = true;

	public void processInput() {
		left = false;
		right = false;
		if (Keyboard.keys[KeyEvent.VK_A]) {
			left = true;

		} if (Keyboard.keys[KeyEvent.VK_D]) {
			right = true;
		}
		if (canJump) {
			if (Keyboard.keys[KeyEvent.VK_W]) {
				jump = true;
				canJump = false;
			}
		}
	}

	int leftSide, rightSide, upSide, downSide = 0;

	public void setLeftSide() {
		if (getColisionLeft((int) y) > getColisionLeft((int) y + size - 1)) {
			leftSide = getColisionLeft((int) y);
		} else {
			leftSide = getColisionLeft((int) y + size - 1);
		}
	}

	public void setRightSide() {
		if (getColisionRight((int) y) < getColisionRight((int) y + size - 1)) {
			rightSide = getColisionRight((int) y);
		} else {
			rightSide = getColisionRight((int) y + size - 1);
		}
	}

	public void setUpSide() {
		if (getColisionUp((int) x) > getColisionUp((int) x + size - 1)) {
			upSide = getColisionUp((int) x);
		} else {
			upSide = getColisionUp((int) x + size - 1);
		}
	}

	public void setDownSide() {
		if (getColisionDown((int) x) < getColisionDown((int) x + size - 1)) {
			downSide = getColisionDown((int) x);
		} else {
			downSide = getColisionDown((int) x + size - 1);
		}
	}

	public void showColisionLine(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.drawLine(GameContainer.width / 2 - size / 2, map.yOffset + (int) y,
				map.xOffset + getColisionRight((int) y), map.yOffset + (int) y);
		graphics.drawLine(GameContainer.width / 2 - size / 2, map.yOffset + (int) y + size - 1,
				map.xOffset + getColisionRight((int) y + size - 1), map.yOffset + (int) y + size - 1);
		graphics.setColor(Color.BLUE);
		graphics.drawLine(GameContainer.width / 2 - size / 2, map.yOffset + (int) y,
				map.xOffset + getColisionLeft((int) y), map.yOffset + (int) y);
		graphics.drawLine(GameContainer.width / 2 - size / 2, map.yOffset + (int) y + size - 1,
				map.xOffset + getColisionLeft((int) y + size - 1), map.yOffset + (int) y + size - 1);

		graphics.setColor(Color.MAGENTA);
		graphics.drawLine(GameContainer.width / 2 - size / 2, map.yOffset + (int) y, GameContainer.width / 2 - size / 2,
				map.yOffset + getColisionUp((int) x));
		graphics.drawLine(GameContainer.width / 2 - size / 2 + size - 1, map.yOffset + (int) y,
				GameContainer.width / 2 - size / 2 + size - 1, map.yOffset + getColisionUp((int) x + size - 1));
		graphics.setColor(Color.PINK);
		graphics.drawLine(GameContainer.width / 2 - size / 2, map.yOffset + (int) y, GameContainer.width / 2 - size / 2,
				map.yOffset + getColisionDown((int) x));
		graphics.drawLine(GameContainer.width / 2 - size / 2 + size - 1, map.yOffset + (int) y,
				GameContainer.width / 2 - size / 2 + size - 1, map.yOffset + getColisionDown((int) x + size - 1));
	}

	public void jump() {
		v = jumpv;
	}

	public void moveRight() {
		x += speed;
	}

	public void moveLeft() {
		x -= speed;
	}

	public int getColisionUp(int x) {
		int yStart = (int) Math.floor((y) / map.size) * map.size - 1;
		int yStop = yStart;
		while (true) {
			if (yStop < 0 || yStop >= map.height * map.size || x >= map.width * map.size || x < 0) {
				break;
			}
			if (Map.map[(int) (x / map.size) + (int) (yStop / map.size) * map.width] != 0) {
				break;
			}
			yStop -= map.size;
		}
		return yStop;
	}

	public int getColisionRight(int y) {
		int xStart = (int) Math.floor((x + size - 1) / map.size) * map.size + map.size;
		int xStop = xStart;
		while (true) {
			if (xStop < 0 || xStop >= map.width * map.size || y >= map.height * map.size || y < 0) {
				break;
			}
			if (Map.map[(int) (xStop / map.size) + (int) (y / map.size) * map.width] != 0) {
				break;
			}
			xStop += map.size;
		}
		return xStop;
	}

	public int getColisionLeft(int y) {
		int xStart = (int) Math.floor((x) / map.size) * map.size - 1;
		int xStop = xStart;
		while (true) {
			if (xStop < 0 || xStop >= map.width * map.size || y >= map.height * map.size || y < 0) {
				break;
			}
			if (Map.map[(int) (xStop / map.size) + (int) (y / map.size) * map.width] != 0) {
				break;
			}
			xStop -= map.size;
		}
		return xStop;
	}

	public int getColisionDown(int x) {
		int yStart = (int) Math.floor((y + size - 1) / map.size) * map.size + map.size;
		int yStop = yStart;
		while (true) {
			if (yStop < 0 || yStop >= map.height * map.size || x >= map.width * map.size || x < 0) {
				break;
			}
			if (Map.map[(int) (x / map.size) + (int) (yStop / map.size) * map.width] != 0) {
				break;
			}
			yStop += map.size;
		}
		return yStop;
	}

	public void gravity() {
		v += g;
		y += v;
	}

}