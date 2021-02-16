package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {

    public void playMusic (String location){
        try{
            File musicPath = new File(location);

            if(musicPath.exists()){

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

            }else {
                System.out.println("Cant find the audio file");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
