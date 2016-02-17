package game.Engine;

import game.Engine.fx.Image;
import game.Engine.fx.Sound;

import java.util.HashMap;
import java.util.Map;

public class Loader {

	public static Loader loader = null;
	private Map<String, Image> images = new HashMap<String, Image>();
	private Map<String, Sound> sounds = new HashMap<String, Sound>();

	protected Loader() {
	}

	/* Singelton  */
	public static Loader getInstance() {
		if (loader == null) {
			loader = new Loader();
		}
		return loader;
	}

	/* returns an image, 
	 * if the image doesn't exist in it's map:
	 *  make a new image*/
	public Image getImage(String path) {
		if(path.isEmpty())		
			return null;
		if (images.containsKey(path)) {
			return images.get(path);
		} else {
			Image image = new Image(path);
			images.put(path, image);
			return image;
		}
	}

	/* returns a sound,
	 * if sound doesn't exists in it's map:
	 *  make a new sound*/
	public Sound getSound(String path) {
		if (sounds.containsKey(path)) {
			return sounds.get(path);
		} else {
			Sound sound = new Sound(path);
			sounds.put(path, sound);
			return sound;
		}
	}
}
