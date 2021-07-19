package design_patterns;

/**
 * @Author: Rita
 * @Date:5/26/2021 10:28 PM
 */
public class AdapterPattern {
    public static void main(String[] args) {
        MyPlayer myPlayer = new MyPlayer();

        myPlayer.play("mp3", "h.mp3");
        myPlayer.play("avi", "me.avi");
    }
    interface Player {
        public void play(String type, String fileName);
    }
    interface AudioPlayer {
        public void playAudio(String fileName);
    }
    interface VideoPlayer {
        public void playVideo(String fileName);
    }
    static class MyAudioPlayer implements AudioPlayer {
        @Override
        public void playAudio(String fileName) {
            System.out.println("Playing. Name: "+ fileName);
        }
    }
    static class MyVideoPlayer implements VideoPlayer {
        @Override
        public void playVideo(String fileName) {
            System.out.println("Playing. Name: "+ fileName);
        }
    }

    static class MyPlayer implements Player {

        AudioPlayer audioPlayer = new MyAudioPlayer();
        VideoPlayer videoPlayer = new MyVideoPlayer();

        public MyPlayer(){
        }
        @Override
        public void play(String audioType, String fileName) {
            if(audioType.equalsIgnoreCase("avi")){
                videoPlayer.playVideo(fileName);
            }else if(audioType.equalsIgnoreCase("mp3")){
                audioPlayer.playAudio(fileName);
            }
        }
    }

}
