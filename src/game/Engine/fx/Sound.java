package game.Engine.fx;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound
{
	private Clip clip;
	private FloatControl gainControl;
	
	public Sound(String path)
	{		
		try {			
			if (Files.probeContentType(Paths.get(path)).equals("audio/wav") || Files.probeContentType(Paths.get(path)).equals("audio/mpeg3")){			
				try
				{
					InputStream audioSrc = getClass().getResourceAsStream(path);
					InputStream bufferedIn = new BufferedInputStream(audioSrc);
					AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
					AudioFormat baseFormat = ais.getFormat();
					AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), 
															   baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
					AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);				
					clip = AudioSystem.getClip();
					clip.open(dais);				
					gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);				
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				System.err.println("Sound file was not a .wav or .mpeg3 file!");
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void play(){
		if (clip == null){
			return;
		}
		stop();
		clip.setFramePosition(0);
		while (!clip.isRunning()){
			clip.start();
		}
	}

	/* stops the sound*/
	public void stop(){
		if (clip.isRunning()){
			clip.stop();
		}
	}

	/* makes sure sound is stopped when close is called */
	public void close() {
		stop();
		clip.drain();
		clip.close();
	}

	/* loops the soundfile */
	public void loop(){
		if (clip == null){
			return;
		}
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		while (!clip.isRunning()) {
			clip.start();
		}
	}

	/* value to control audio-level*/
	public void setVolume(float value) {
		gainControl.setValue(value);
	}

	public boolean isRunning() {
		return clip.isRunning();
	}
}
