package game.Engine.components;

import game.Engine.FrameWork;
import game.Engine.Renderer;

public abstract class Component {
	public abstract void update(FrameWork fw, GameObject object, float dt);
	public abstract void render(FrameWork fw, Renderer r);
	/*identifier for what component it is*/
	public abstract String getType();
}
