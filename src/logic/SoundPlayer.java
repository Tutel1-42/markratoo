package logic;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
	private static Media music = new Media(ClassLoader.getSystemResource("BGM.mp3").toString());
	private static MediaPlayer bgm = new MediaPlayer(music);
	private static AudioClip boiling = new AudioClip(ClassLoader.getSystemResource("BoilSFX.mp3").toString());
	private static AudioClip grilling = new AudioClip(ClassLoader.getSystemResource("GrillSFX.mp3").toString());

	public static void startBGM() {
		bgm.setCycleCount(MediaPlayer.INDEFINITE);
		bgm.play();
	}

	public static void stopBGM() {
		bgm.stop();

	}

	public static void startSFX(AudioClip sfx) {
		sfx.play();

	}

	public static void stopSFX(AudioClip sfx) {
		sfx.stop();

	}

	public static AudioClip getBoilingSFX() {
		return boiling;
	}

	public static AudioClip getGrilingSFX() {
		return grilling;
	}

}
