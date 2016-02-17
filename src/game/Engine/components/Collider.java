package game.Engine.components;

import game.Engine.FrameWork;
import game.Engine.Renderer;


public class Collider extends Component{
	float x, y, halfWidth, halfHeight, xOffset=0, yOffset=0, wOffset=0, hOffset = 0;
	private GameObject owner;

	/* creates a collider around the gameobject*/
	public Collider(GameObject object) {
		this.owner = object;
		x = owner.getX();
		y = owner.getY();
		Physics.addCollider(this);
	}
	
	/* secondary constructor to create a collider that doesn't surround the image/sprite */
	public Collider (GameObject object, int x, int y, int w, int h) {
		this.owner = object;
		xOffset = x;
		yOffset = y;
		wOffset = w;
		hOffset = h;
		Physics.addCollider(this);
	}

	@Override
	public void update(FrameWork fw, GameObject object, float dt)
	{
		x = owner.getX()+xOffset;
		y = owner.getY()+yOffset;
		halfWidth = owner.getW()/2+wOffset;
		halfHeight = owner.getH()/2+hOffset;
	}


	@Override
	public void render(FrameWork fw, Renderer r) {
		//Render bounding box
		
	}
	
	/* when a collision occurs*/
	public void collision(GameObject object) {
		object.componentEvent(this);
	}

	@Override
	public String getType() {
		return "Collider";
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	/* the gameobject of which the collider is bound to*/
	public GameObject getObject() {
		return owner;
	}

	public void setObject(GameObject object) {
		this.owner = object;
	}

	public float gethalfWidth() {
		return halfWidth;
	}

	public float gethalfHeight() {
		return halfHeight;
	}
}