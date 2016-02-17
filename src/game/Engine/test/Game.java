package game.Engine.test;

import game.Engine.AbstractGame;
import game.Engine.FrameWork;
import game.Engine.Renderer;
import game.Engine.components.GOManager;

public class Game extends AbstractGame {

	public GOManager manager;

	public Game() {
		manager = new GOManager();
		manager.addObject(new Player());
		manager.addObject(new Player2());
		manager.addObject(new Player3());
	}

	public static void main(String args[]) {
		FrameWork fw = new FrameWork(new Game(), "Cool game!");
		fw.setWidth(200);
		fw.setHeight(160);
		fw.setScale(2.0f);
		fw.start();
	}

	static float GRAVITY_X = 0;
	static float GRAVITY_Y = 5;

	@Override
	public void update(FrameWork fw, float dt) {
		manager.updateObjects(fw, dt);

	}

	@Override
	public void render(FrameWork fw, Renderer r) {
		manager.renderObjects(fw, r);

	}
}
