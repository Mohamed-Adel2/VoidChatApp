package iti.jets.app.client.controllers;

import iti.jets.app.shared.DTOs.MessageDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageReceiveController implements Initializable {
    @FXML
    public ImageView img;

    @FXML
    public Text txt;


    @FXML
    public TextFlow txtFlow;

    public void setData(MessageDto msg) {
        //Image image = new Image(getClass().getResourceAsStream(user.getImgSrc()));
        txt.setText(msg.getMessageContent());
        //connectionStatus.setFill(user.getStatus() == StatusEnum.ONLINE ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
        }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
