package game.Engine.components;

import java.util.LinkedList;
import java.util.List;

import game.Engine.FrameWork;
import game.Engine.Renderer;

public class GOManager {

	private List<GameObject> gameObjects = new LinkedList<GameObject>();

	/* updates all gameObjects in the linked list*/
	public void updateObjects(FrameWork fw, float dt) {
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).update(fw, dt);

			if (gameObjects.get(i).isObsolete())
				gameObjects.remove(i);
		}
	}
	
	/* renders all objects in the linked lists*/
	public void renderObjects(FrameWork fw, Renderer r)
	{
		for(int i = 0; i < gameObjects.size(); i++)
		{
			gameObjects.get(i).render(fw, r);
		}
	}
	
	/* add new ndoe to the linked list*/
	public void addObject(GameObject object)
	{
		gameObjects.add(object);
	}
	
}
