import Controller.MediaPlayerController;
import Model.MediaFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


   private static MediaFile mediaFile = new MediaFile();

   static {

       try {
           MediaPlayerController.mediaArrayList = mediaFile.readVector("Media.sam");
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }


   }

    public static void main(String[] args) throws IOException {
        launch(args);
        mediaFile.writeVector(MediaPlayerController.mediaArrayList , "Media.sam");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader load = new  FXMLLoader(getClass().getResource("View/M5.fxml"));
        load.load();
        Parent root = load.getRoot();
        MediaPlayerController mediaPlayerController = load.getController();
        primaryStage.setTitle("SarPlayer");

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(mediaPlayerController);
        Image icon  = new Image("Images/windows-media-player (1).png");

         primaryStage.getIcons().setAll(icon);

         primaryStage.setScene(scene);
         primaryStage.show();
         sceneController(scene, primaryStage);

    }



    private void sceneController(Scene scene , Stage stage){


        scene.setOnMouseClicked(event -> {
            if (event.getClickCount()==2){
                stage.setFullScreen(true);
            }
        });


    }




}
