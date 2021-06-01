package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;


import java.net.URL;
import java.util.ResourceBundle;


public class EqualizerController implements Initializable {



    @FXML
    private Slider shesh;

    @FXML
    private Slider hasht;

    @FXML
    private Slider asli;

    @FXML
    private Slider noh;

    @FXML
    private RadioButton enable;

    @FXML
    private Slider seh;
    @FXML
    private Slider yek;
    @FXML
    private Slider dovom;

    @FXML
    private Slider haft;

    @FXML
    private Slider panj;

    @FXML
    private Slider sefr;

    @FXML
    private Slider chahar;







    @FXML
    private void enableF(ActionEvent event){
  if (enable.isSelected()) {
      asli.setDisable(true);
      sefr.setDisable(true);
      yek.setDisable(true);
      dovom.setDisable(true);
      seh.setDisable(true);
      chahar.setDisable(true);
      panj.setDisable(true);
      shesh.setDisable(true);
      haft.setDisable(true);
      hasht.setDisable(true);
      noh.setDisable(true);

  }else {
      asli.setDisable(false);
      sefr.setDisable(false);
      yek.setDisable(false);
      dovom.setDisable(false);
      seh.setDisable(false);
      chahar.setDisable(false);
      panj.setDisable(false);
      shesh.setDisable(false);
      haft.setDisable(false);
      hasht.setDisable(false);
      noh.setDisable(false);
  }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
