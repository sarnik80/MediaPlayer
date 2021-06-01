package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MediaPlayerController implements Initializable, EventHandler<KeyEvent> {

  @FXML
  private BorderPane mainPage;


    @FXML
    private JFXSlider soundSlider;

    @FXML
    private JFXRadioButton loopBTN;

    @FXML
    private ComboBox<String> speed;

    @FXML
    private ListView<File> playList;

    @FXML
    private MenuItem playM;

    @FXML
    private JFXButton nextThirty ,thirtyP , pause,stop , nextBT, play,previous;


    @FXML
    private JFXButton randBTN;

    @FXML
    private JFXButton fullBTN;


    @FXML
    private MenuItem deleteM;

    @FXML
    private MediaView mediaview;


    @FXML
    private MenuBar menuBar;

    @FXML
    private JFXSlider timeS;

   @FXML
   private ToggleButton mute;

    @FXML
    private Menu fileM;
    @FXML
    private MenuItem equalizer;


    @FXML
    private RadioMenuItem settingM;

    @FXML
    private Label timeLBL , soundLBL;

   private  int indexOfCurrentFile ;

   private final int[] sp ={25,50,75,100,125,150,175,200};

   private MediaPlayer mediaPl ;
  public static  ArrayList<File> mediaArrayList = new ArrayList<>();





    private void setSoundOfMedia(){

       soundSlider.setValue(mediaPl.getVolume()*100);

       soundSlider.valueProperty().addListener(new InvalidationListener() {
           @Override
            public void invalidated(Observable observable) {
               mediaPl.setVolume(soundSlider.getValue()/100);
               soundLBL.setText((int)soundSlider.getValue()+ "/ 100" );

          }
       });


    }

    @FXML
    private void full(ActionEvent event){
        Stage stage = (Stage) mainPage.getScene().getWindow();

        if (stage.isFullScreen()){
            stage.setFullScreen(false);

        }else {
            stage.setFullScreen(true);

        }


    }

    private void sLBL(){

        soundLBL.setText((int)mediaPl.getVolume()*100+ " / 100" );

    }


    //play mediaPlayer :)
    @FXML private  void pl(ActionEvent event){
       if (mediaPl!=null){
           mediaPl.play();
           mediaPl.setRate(1);
       }
    }


    @FXML // pause the mediaPlayer
    private void pauseFu(ActionEvent event){
       if (mediaPl!=null) {
           mediaPl.pause();
       }
    }


     @FXML // stop media
    private void stop(ActionEvent event){

      if (mediaPl!=null)
       mediaPl.stop();
    }




    @FXML
   private void randomF(ActionEvent event){

       Random r = new Random();

       if (mediaPl==null) {
           if (mediaArrayList.size() != 0) {
               int index = r.nextInt(mediaArrayList.size());
               File file = mediaArrayList.get(index);

               if (file.toURI().toString().contains("mp3") ||
                       file.toURI().toString().contains("mp4")) {
                   Media media = new Media(file.toURI().toString());


                   mediaPl = new MediaPlayer(media);

                   mediaview.setMediaPlayer(mediaPl);
                   setSoundOfMedia();
                   timeSlider();
                   mediaPl.play();
                   tLBL();
                   sLBL();


               }
           }
       }else {

             mediaPl.pause();
           if (mediaArrayList.size() != 0) {
               int index = r.nextInt(mediaArrayList.size());
               File file = mediaArrayList.get(index);

               if (file.toURI().toString().contains("mp3") ||
                       file.toURI().toString().contains("mp4")) {
                   Media media = new Media(file.toURI().toString());


                   mediaPl = new MediaPlayer(media);

                   mediaview.setMediaPlayer(mediaPl);
                   setSoundOfMedia();
                   timeSlider();
                   mediaPl.play();
                   tLBL();
                   sLBL();
               }

           }
       }


   }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMediaToList();
        speedBox();



    }




    private void speedBox(){

        for (int i = 0; i <sp.length ; i++) {

            speed.getItems().add(Integer.toString(sp[i]));


        }


        speed.setOnAction(this::speedA);



    }

    private void speedA(ActionEvent event){

        if (mediaPl!=null)
        mediaPl.setRate(Integer.parseInt(speed.getValue())*0.01);
    }


    @FXML
    private  void  mouseE(MouseEvent event){
       if (mediaPl!=null) {
           if (event.getClickCount() == 1) {
               mediaPl.pause();
           }
           if (event.getClickCount()==2){
               mediaPl.play();
           }
       }


    }

    private void  addMediaToList(){
        for (int i=0;i<mediaArrayList.size() ; i++){
         playList.getItems().add(mediaArrayList.get(i));
       }


    }

    @FXML
    private void setNextThirty(ActionEvent event){
       if (mediaPl!=null)
        mediaPl.seek(mediaPl.getCurrentTime().add(Duration.seconds(+30)));

    }

    @FXML
    private void setThirtyP(ActionEvent event){

       if (mediaPl!=null)
        mediaPl.seek(mediaPl.getCurrentTime().add(Duration.seconds(-30)));

    }



    @FXML
    private void  playL(ActionEvent event){
       if (mediaPl==null) {
           if (mediaArrayList.size()!=0&&playList.getItems()!=null) {
               File file = playList.getSelectionModel().getSelectedItem();
               if (file.toURI().toString().contains("mp3") ||
                       file.toURI().toString().contains("mp4")) {
                   Media media = new Media(file.toURI().toString());
                   indexOfCurrentFile = search(file);
                   mediaPl = new MediaPlayer(media);
                   mediaview.setMediaPlayer(mediaPl);
                   setSoundOfMedia();
                   timeSlider();
                   mediaPl.play();
                   tLBL();
                   sLBL();

               }
           }
       }else {

           mediaPl.stop();
           File file = playList.getSelectionModel().getSelectedItem();
          if(file.toURI().toString().contains("mp3")||
                  file.toURI().toString().contains("mp4")) {
              Media media = new Media(file.toURI().toString());
              indexOfCurrentFile = search(file);
              mediaPl = new MediaPlayer(media);
              mediaview.setMediaPlayer(mediaPl);
              setSoundOfMedia();
              timeSlider();
              mediaPl.play();
              tLBL();
              sLBL();
          }
       }

    }

    private void timeSlider(){
        mediaPl.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<?
                    extends Duration> observable, Duration oldValue, Duration newValue) {
                timeS.setValue(newValue.toSeconds());
            }
        });

        mediaPl.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration d = mediaPl.getMedia().getDuration();
               timeS.setMax(d.toSeconds());
            }
        });
    }



    private void tLBL(){

        mediaPl.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
               timeLBL.setText((int)newValue.toMinutes()+ " / " + (int)mediaPl.getMedia().getDuration().toMinutes());
            }
        });



    }
   @FXML
    private void mouseCS(MouseEvent event){
       if (mediaPl!=null)
        mediaPl.seek(Duration.seconds(timeS.getValue()));
    }

    @FXML
    private void mouseD(MouseEvent event){
       if(mediaPl!=null){
          mediaPl.seek(Duration.seconds(timeS.getValue()));
       }
    }
    @FXML
    private void  addFileToList(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File f  = fileChooser.showOpenDialog(null);

        if (f!=null){
            playList.getItems().add(f);
            mediaArrayList.add(f);
        }
    }

   private int search(File file){

       for (int i = 0; i < mediaArrayList.size(); i++) {

           if (mediaArrayList.get(i).toURI().equals(file.toURI())){
               return i;
           }

       }

       return -1;

   }


    @Override
    public void handle(KeyEvent event) {

    if (event.getCode()== KeyCode.RIGHT){
        if (mediaPl!=null) {
            mediaPl.seek(mediaPl.getCurrentTime().add(Duration.seconds(+30)));
        }
    }

    if (event.getCode() == KeyCode.LEFT){
        if (mediaPl!=null) {
            mediaPl.seek(mediaPl.getCurrentTime().add(Duration.seconds(-30)));
        }
    }

    if (event.getCode()==KeyCode.D){
        if (mediaPl!=null) {
            mediaPl.seek(mediaPl.getCurrentTime().add(Duration.seconds(+30)));
        }
    }


        if (event.getCode()==KeyCode.A){
            if (mediaPl!=null) {
                mediaPl.seek(mediaPl.getCurrentTime().add(Duration.seconds(-30)));
            }
        }

        if (event.getCode() == KeyCode.P){
        if (mediaPl!=null){
            if (mediaPl.getStatus() == MediaPlayer.Status.PAUSED) {
                mediaPl.play();
            } else if (mediaPl.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPl.pause();
            }
        }
    }
        if (event.getCode() == KeyCode.N){
            if (mediaPl!=null){
                mediaPl.stop();
            }
        }

        if (event.getCode() == KeyCode.R){
            if (mediaPl!=null){
                mediaPl.seek(Duration.seconds(0));
            }
        }


        if(event.getCode() == KeyCode.UP){
            if (mediaPl!=null) {
                 mediaPl.setVolume(mediaPl.getVolume()+0.05);
                setSoundOfMedia();
            }
        }


        if(event.getCode() == KeyCode.DOWN){
            if (mediaPl!=null) {
                mediaPl.setVolume(mediaPl.getVolume()-0.05);
                setSoundOfMedia();
            }
        }




        if(event.getCode() == KeyCode.S){
            if (mediaPl!=null) {
                mediaPl.setVolume(mediaPl.getVolume()-0.05);
                setSoundOfMedia();
            }
        }

        if(event.getCode() == KeyCode.W){
            if (mediaPl!=null) {
                mediaPl.setVolume(mediaPl.getVolume()+0.05);
                setSoundOfMedia();
            }
        }

      if (event.getCode() == KeyCode.V){
          if (mediaPl!=null){

              if (mediaPl.isMute()){
                  mediaPl.setMute(false);
              }else {
                  mediaPl.setMute(true);
              }


          }
      }

    }

    @FXML
    private void muteMedia(ActionEvent event){
       if (mediaPl!=null){
           if (!mediaPl.isMute()){

               mediaPl.setMute(true);
           }else {
               mediaPl.setMute(false);
           }
       }
    }

    @FXML
    private void setting(ActionEvent event){

       if (settingM.isSelected()){
           mainPage.setStyle(
                   "-fx-background-color: linear-gradient(to top, #000000, #222222, #3e3e3e, #5d5d5d, #7d7d7d);");
           borderColor("#cccccc");

       }else {
           mainPage.setStyle(
                   "-fx-background-color:  linear-gradient(to top, #2f0537, #49284e, #634a65, #7d6e7e, #979397)");

           borderColor("black");
       }



    }



    private void borderColor(String color){
        play.setStyle("-fx-border-color: "+color);
        stop.setStyle("-fx-border-color: "+color);
        previous.setStyle("-fx-border-color: "+color);
        nextBT.setStyle("-fx-border-color: "+color);
        pause.setStyle("-fx-border-color: "+color);
        thirtyP.setStyle("-fx-border-color: "+color);
        nextThirty.setStyle("-fx-border-color: "+color);
        speed.setStyle("-fx-border-color: "+color);
        randBTN.setStyle("-fx-border-color: "+color);
        fullBTN.setStyle("-fx-border-color: "+color);

    }
    @FXML
    private void nextM(ActionEvent event){
       if (mediaPl!=null) {
           if (indexOfCurrentFile < mediaArrayList.size() - 1) {
                  mediaPl.pause();
                   timeLBL.setText("");
                  File file = mediaArrayList.get(indexOfCurrentFile+1);
                  Media media = new Media(file.toURI().toString());
                  mediaPl = new MediaPlayer(media);
                  mediaview.setMediaPlayer(mediaPl);
               setSoundOfMedia();
               timeSlider();
               tLBL();
                mediaPl.play();
                  indexOfCurrentFile+=1;


           }

       }

    }


    @FXML
    private void beM(ActionEvent event){
        if (mediaPl!=null&&mediaArrayList.size()!=0) {
            if (indexOfCurrentFile >0) {
                mediaPl.pause();
               timeLBL.setText("");
                File file = mediaArrayList.get(indexOfCurrentFile-1);
                Media media = new Media(file.toURI().toString());
                mediaPl = new MediaPlayer(media);
                mediaview.setMediaPlayer(mediaPl);
                setSoundOfMedia();
                timeSlider();
                tLBL();
                mediaPl.play();
                indexOfCurrentFile-=1;

            }

        }

    }

    @FXML
     private void ml(MouseEvent event){

      menuBar.setStyle("-fx-background-color: gray");

    }

    @FXML
    private void mE(MouseEvent event){


        menuBar.setStyle("-fx-background-color:#cccccc ");

    }
       @FXML
    private void deleteFile(ActionEvent event){

       File f = playList.getSelectionModel().getSelectedItem();

       if (f!=null){

           playList.getItems().remove(f);
           mediaArrayList.remove(f);


       }
    }



    @FXML
    private void equ(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Equalizer.fxml"));
        fxmlLoader.load();
        Parent  root = fxmlLoader.getRoot();

       EqualizerController equalizerController = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setResizable(false);

        stage.setTitle("Equalizer");
        Image icon = new Image("Images/windows-media-player (1).png");
        stage.getIcons().add(icon);
        stage.show();
    }

    @FXML
    private void loopF(ActionEvent e){

        if (mediaPl!=null) {
            if (loopBTN.isSelected()) {
                mediaPl.setCycleCount(MediaPlayer.INDEFINITE);
            } else {
                mediaPl.setCycleCount(0);
            }
        }

    }








}
