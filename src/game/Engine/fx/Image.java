package game.Engine.fx;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Image{

	public int width, height;
	public int[] pixels;

	public Image(String path) {
		
		try {			
			if (Files.probeContentType(Paths.get(path)).equals("image/png")) {
				
				BufferedImage image = null;
				try {
					image = ImageIO.read(Image.class.getResourceAsStream(path));
				} catch (IOException e) {
					e.printStackTrace();
				}
				width = image.getWidth();
				height = image.getHeight();
				pixels = image.getRGB(0, 0, width, height, null, 0, width);

				image.flush(); // remove it from ram, java should do this by auto?
			}
			else{
				System.err.println("Image file was not a .png!");			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

}
