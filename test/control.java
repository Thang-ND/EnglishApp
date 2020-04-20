package test;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class control implements Initializable {
    @FXML
    private MediaView movie;

    @FXML
    private Button TimeCurrent;

    @FXML
    private Slider time;


    @FXML
    private TextField check;

    @FXML
    private Button TotalTime;

    @FXML
    private Button reset;

    @FXML
    private Button Play;

    @FXML
    private Slider sliderVolume;

    String path="src\\video\\communication1.mp4";
    String video=new File(path).getAbsolutePath();
    Media media=new Media(new File(video).toURI().toString());
    MediaPlayer mediaPlayer=new MediaPlayer(media);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MediaPlayer.Status status=mediaPlayer.getStatus();
                if(status== MediaPlayer.Status.PLAYING){
                    if(mediaPlayer.getCurrentTime().greaterThanOrEqualTo(mediaPlayer.getTotalDuration())){
                        mediaPlayer.seek(mediaPlayer.getStartTime());
                        mediaPlayer.play();
                    }
                    else{
                        mediaPlayer.pause();
                        File input=new File("src/photo/pause.png");
                        try {
                            String url=input.toURI().toURL().toString();
                            ImageView pauseIcon=new ImageView(url);
                            pauseIcon.setFitHeight(25);
                            pauseIcon.setFitWidth(31);
                            Play.setGraphic(pauseIcon);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(status== MediaPlayer.Status.PAUSED||status== MediaPlayer.Status.STOPPED){
                    mediaPlayer.play();
                    File input=new File("src/photo/play2.png");
                    try {
                        String url=input.toURI().toURL().toString();
                        ImageView playIcon=new ImageView(url);
                        playIcon.setFitHeight(25);
                        playIcon.setFitWidth(31);
                        Play.setGraphic(playIcon);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        // replay video
        File resetPhoto=new File("src/photo/replay.png");
        try{
            String url1=resetPhoto.toURI().toURL().toString();
            ImageView resetIcon=new ImageView(url1);
            resetIcon.setFitWidth(31);
            resetIcon.setFitHeight(25);
            reset.setGraphic(resetIcon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(0));

            }
        });


        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateValues();
                int a=(int)Math.round(mediaPlayer.getTotalDuration().toSeconds());
                int b=a/60;
                TotalTime.textProperty().set(Integer.toString(b)+":"+Integer.toString(a-60*b));
            }
        });




        //set default volume
        sliderVolume.setMin(0);
        sliderVolume.setMax(100);
        sliderVolume.setValue(100);//the value default
        //change volume audio
        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(time.isPressed()) {
                    mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(time.getValue() / 100));

                }
            }
        });
        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(sliderVolume.isPressed()){
                    mediaPlayer.setVolume(sliderVolume.getValue()/100);
                }
            }
        });
        movie.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void updateValues(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double a=mediaPlayer.getCurrentTime().toMillis()/mediaPlayer.getTotalDuration().toMillis()*100;
                time.setValue(a);
                int totalSecond=(int)Math.round(mediaPlayer.getCurrentTime().toSeconds());
                int minute=totalSecond/60;
                int second=totalSecond-minute*60;
                TimeCurrent.textProperty().set(Integer.toString(minute)+":"+Integer.toString(second));
            }
        });
    }



}
