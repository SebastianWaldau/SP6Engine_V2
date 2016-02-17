package game.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener{
		
	/* Boolean Array: allows multiple keys to be pressed*/
	private static boolean[] keys = new boolean[256];				//if key was pressed this frame (for holding down keys)
	private static boolean[] keysLast = new boolean[256];			//keys from the last frame (see if it's still being pressed)
			
	public Input(FrameWork fw){		
		fw.getWindow().getCanvas().addKeyListener(this);
	}
	
	public void update(){
		keysLast = keys.clone();
		
	}
	
	/**/
	public static InputState getKeyState(int keyCode){
		if(keysLast[keyCode])
		{
			if (keys[keyCode]){
				return InputState.StillPressed;
			}
			else
				return InputState.JustReleased;
		}
		
		if(keys[keyCode]){
			return InputState.JustPressed;
		}
		else
			return InputState.StillReleased;

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;		
	}


	@Override
	public void keyTyped(KeyEvent arg0){}
}