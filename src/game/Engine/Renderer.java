package game.Engine;


import java.awt.image.DataBufferInt;

import game.Engine.fx.Image;

public class Renderer {
	private int width, height;
	private int[] pixels;
	public Renderer(FrameWork fw){
		width = fw.getWidth();
		height = fw.getHeight();
		pixels = ((DataBufferInt)fw.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	
	public void setPixel(int x, int y, int color){
		if (x<0 || x >= width || y < 0 || y >= height){	//|| a == 0
			return;
		}
		pixels[x+y*width] = color;
	}
	
	
	public void clear() {
		for(int x = 0; x <width; x++){
			for(int y = 0; y<height; y++){
				setPixel(x, y, 0xff000000);		//black, ff = alpha
			}
		}
	}
	
	public void drawImage(Image image, int offX, int offY){
		for(int x = 0; x <image.width; x++){
			for(int y = 0; y<image.height; y++){
				setPixel(x + offX, y+offY, image.pixels[x + y * image.width]);
			}
		}
	}
}
