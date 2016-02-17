package game.Engine;

import game.Engine.components.Physics;


public class FrameWork implements Runnable {
	private Thread thread;
	private AbstractGame game;
	private Window window;
	private Renderer ren;
	private Input input;
	private Physics physics;

	private boolean isRunning = false;
	private double frameCap = 1.0 / 60.0;
	private int width;
	private int height;
	private float scale;	
	private String fpsCounter = "";
	private String title = "Game Engine - Training";
	
	/* delta time */
	private double now = 0;
	private double lastLoopTime = System.currentTimeMillis() / 1000.0; 
	private double updateTime = 0;
	private double delta = 0;
	
	/* used inside run to lock / unlock framecap*/
	boolean render;


	public FrameWork(AbstractGame game, String title) {
		this.game = game;
		this.title = title;
	}


	public synchronized void start() {
		if (isRunning)
			return;

		// initialize engine components
		window = new Window(this);
		ren = new Renderer(this);
		input = new Input(this);
		physics = new Physics();

		thread = new Thread(this);
		thread.run();
	}

	public synchronized void stop() {
		if (!isRunning)
			return;
		isRunning = false;
	}

	public void run() {
		isRunning = true;
		
		/* fps counter */
		double frameTime = 0;
		int frames = 0;

		while (isRunning) {
			/* true = uncapped framerate, false = cap*/
			render = true;

			now = System.currentTimeMillis() / 1000.0;
			updateTime = now - lastLoopTime;
			lastLoopTime = now;
			delta += updateTime;
			
			/*for FPS-display*/
			frameTime += updateTime;

			while (delta > frameCap) {
				game.update(this, (float) frameCap);
				physics.update();
				input.update();
				delta -= frameCap;
				render = true;

				if (frameTime >= 1) {
					frameTime = 0;
					fpsCounter = "" + frames;
					frames = 0;
				}
			}

			if(render){
				ren.clear();
				game.render(this, ren); //render game
				window.update();
				frames++;
			}
			else{
				try{
					Thread.sleep(1);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title+ " - FPS: " + fpsCounter;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window getWindow() {
		return window;
	}
}
