/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_services.ValidatorService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private ValidatorService validatorService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //inject validator service
        validatorService = new ValidatorService();

        txtEmail.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!txtEmail.getText().isEmpty()) {
                if (!newV) {
                    try {
                        validatorService.validateConditionErrors("MAIL CONFIRMATION", "Please enter your confirmation code to verify your email", true, true, false, false);
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
