/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import glory_services.MessageService;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class RegisterUserController implements Initializable {
    //Global Variable,begin   
    MiddleTier ServerCall = new MiddleTier();
    //Global Variable,end
    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtEmail.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!txtEmail.getText().isEmpty()) {
                if (!newV) {
                    try {
                        AnchorPane layout;
                        Stage stage;
                        String header = "MAIL CONFIRMATION";
                        String body = "Please enter your confirmation code to verify your email";
                        MessageService.forEmailConfirmation = true;
                        MessageService.visiblityForRadioButton = false;
                        MessageService.visiblityForTextField = true;
                        MessageService.setMakeMessageUI("mail", header, body);
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
        });

    }

    @FXML
    void btnBackClicked(ActionEvent event) {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnSaveClcked(ActionEvent event) {
        ServerCall.registerUser(txtUserName.getText(), txtEmail.getText(), txtPassword.getText(), txtConfirmPassword.getText());
    }

    @FXML
    void txtEmailPressed(MouseEvent event) {
        /// no needed
    }

}
