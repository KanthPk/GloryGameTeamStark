/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author AshanPerera
 */
public class ValidatorService {

    public void getValidaterMessage(String heading,String value,boolean confirmation,boolean textVisi,boolean force) {
        try {
            AnchorPane layout;
            Stage stage;
            String header = heading;
            String body = value;
            MessageService.forceToClose = force;
            MessageService.forEmailConfirmation = confirmation;            
            MessageService.visiblityForTextField = textVisi;
            MessageService.setMakeMessageUI("Error", header, body);
            layout = FXMLLoader.load(getClass().getResource("/UI/CommenMessage.fxml"));
            stage = new Stage();
            stage.centerOnScreen();
            stage.setScene(new Scene(layout));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (Exception e) {
        }
    }
}
