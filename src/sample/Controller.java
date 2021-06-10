package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public  class Controller {
    private String fileaddress;
    private MediaPlayer mp;
    @FXML
    private ListView<File> mediaListView;

    @FXML
    private MediaView mv;
    @FXML
    private Slider slider;
    @FXML
    private Slider sldvo;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Button pl;
    @FXML
    private Button loop;
    @FXML
    private MenuItem playlist;
    @FXML
    private Pane pane;

    private Stage myStage;
    @FXML
    private  ListView<Media> list=new ListView<>();

    public void openvideo(ActionEvent event){

        FileChooser fc=new FileChooser();
        File f=fc.showOpenDialog(null);
        fileaddress=f.toURI().toString();

        if (fileaddress !=null){
            Media media=new Media(fileaddress);
            mp=new MediaPlayer(media);
            mv.setMediaPlayer(mp);
            mp.play();


            //size video in fullScreen
            DoubleProperty wi= mv.fitWidthProperty();
            wi.bind(Bindings.selectDouble(mv.sceneProperty() , "width"));
            DoubleProperty hei= mv.fitHeightProperty();
            hei.bind(Bindings.selectDouble(mv.sceneProperty() , "height"));

            mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    slider.setValue(newValue.toSeconds());
                }
            });



            slider.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mp.seek(Duration.seconds(slider.getValue()));
                }
            });
            slider.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mp.seek(Duration.seconds(slider.getValue()));
                }
            });
            mp.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration du=media.getDuration();
                    slider.setMax(du.toSeconds());
                }
            });
            sldvo.setValue(mp.getVolume()*100);
            sldvo.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mp.setVolume(sldvo.getValue()/100);

                }
            });

        }
        label.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mp.getCurrentTime());
            }
        }, mp.currentTimeProperty()));


        loop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mp.setCycleCount(mp.INDEFINITE);

            }
        });













    }


    //show time (hours and minute and second)
    public String getTime(Duration time){
        int hours= (int) time.toHours();
        int minute=(int) time.toMinutes();
        int second=(int) time.toSeconds();
        if(second>59){
            second=second%60;
        }
        if(minute > 59){
            minute=minute%60;
        }
        if(hours >59){
            hours=hours%60;
        }
        if(hours >0){
            return String.format("%d:%02d:%02d",  hours,minute,second);
        }
        else return String.format("%02d:%02d" , minute , second);
    }

    public void play(ActionEvent event){
        mp.play();
        mp.setRate(1);

    }
    public void pause(ActionEvent event){
        mp.pause();

    }
    public void stop(ActionEvent event){
        mp.stop();

    }
    public void slow(ActionEvent event){
        mp.setRate(0.5);

    }
    public void fast(ActionEvent event){
        mp.setRate(1.5);

    }
    public void next(ActionEvent event){
        mp.seek(mp.getCurrentTime().add(Duration.seconds(+30)));

    }
    public void back(ActionEvent event){
        mp.seek(mp.getCurrentTime().add(Duration.seconds(-30)));

    }
    @FXML
    private void fileDragged(DragEvent event) {
        Dragboard db=event.getDragboard();
        if(db.hasFiles()){
            event.acceptTransferModes(TransferMode.COPY);
        }
    }







}
