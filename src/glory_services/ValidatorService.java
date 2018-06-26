/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

import glory_schema.ConstantElement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author TeamStark
 */
public class ValidatorService {

    AnchorPane layout;
    Stage stage;

    public ValidatorService() {
    }

    public void validateConditionErrors(String heading, String value, boolean confirmation, boolean textVisibility, boolean isNeedBtnCancel, boolean btnOK, boolean isNeedResendButton) {
        try {
            layout = null;
            stage = null;
            String header = heading;
            String body = value;
            ConstantElement.forEmailConfirmation = confirmation;
            ConstantElement.visiblityForTextField = textVisibility;
            ConstantElement.isNeedBtnOK = btnOK;
            ConstantElement.isNeedBtnCancel = isNeedBtnCancel;
            ConstantElement.isNeedResendButton = isNeedResendButton;
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

    public void getMailMessageBox(String heading, String value, boolean confirmation, boolean textVisibility, boolean isNeedOkButton, boolean isNeedCancelButton, String iconField, boolean isNeedResendButton) {
        try {
            layout = null;
            stage = null;
            String header = heading;
            String body = value;
            ConstantElement.forEmailConfirmation = confirmation;
            ConstantElement.visiblityForTextField = textVisibility;
            ConstantElement.isNeedBtnOK = isNeedOkButton;
            ConstantElement.isNeedBtnCancel = isNeedCancelButton;
            ConstantElement.isNeedResendButton = isNeedResendButton;
            MessageService.setMakeMessageUI(iconField, header, body);
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

    public void validateLiveError(String heading, String value, boolean confirmation, boolean textVisibility, boolean isNeedCancelButton, boolean isNeedOkButton, String iconField, boolean isNeedResendButton) {
        try {
            layout = null;
            stage = null;
            String header = heading;
            String body = value;
            ConstantElement.forEmailConfirmation = confirmation;
            ConstantElement.visiblityForTextField = textVisibility;
            ConstantElement.isNeedBtnOK = isNeedOkButton;
            ConstantElement.isNeedBtnCancel = isNeedCancelButton;
            ConstantElement.isNeedResendButton = isNeedResendButton;
            MessageService.setMakeMessageUI(iconField, header, body);
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
