import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	public BufferedImage image;
	public int width;
	public int height;

	public Image(String path) {
		try {
			image = ImageIO.read(Image.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		image.flush();
	}
	
	public Image(BufferedImage image) {
		this.image = image;
	}

}