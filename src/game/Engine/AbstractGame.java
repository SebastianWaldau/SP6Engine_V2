package game.Engine;

public abstract class AbstractGame {
	public abstract void update(FrameWork fw, float dt);
	public abstract void render(FrameWork fw, Renderer r);
}
