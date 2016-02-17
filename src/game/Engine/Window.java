package game.Engine;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private Graphics graphics;
	private BufferStrategy bs;
	
	private FrameWork fw;
	public Window(FrameWork fw){
		image = new BufferedImage(fw.getWidth(), fw.getHeight(), BufferedImage.TYPE_INT_RGB);	
		this.fw = fw;
		
		Dimension dim = new Dimension((int)(fw.getWidth() * fw.getScale()),(int)(fw.getHeight() * fw.getScale()));
		canvas = new Canvas();		
		canvas.setPreferredSize(dim);
		canvas.setMinimumSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setFocusable(true);
		canvas.requestFocusInWindow();
		
		frame = new JFrame(fw.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();	
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);		
		bs = canvas.getBufferStrategy();
		graphics = bs.getDrawGraphics();		
	}
	
	public void update(){
		graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bs.show();
		frame.setTitle(fw.getTitle());
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public BufferedImage getImage() {
		return image;
	}
}
