/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import glory_services.SendEmailService;
import glory_services.ValidatorService;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class ForgotPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    MiddleTier serverCall = new MiddleTier();
    ConstantElement userData = new ConstantElement();
    ArrayList<String> users;
    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private PasswordField txtConfirmpassword;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnBack;

    @FXML
    private ValidatorService validatorService;

    public ForgotPasswordController() {
        //inject validator service
        validatorService = new ValidatorService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUserName.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!txtUserName.getText().isEmpty()) {
                if (!newV) {
                    try {
                        validatorService.getMailMessageBox("MAIL CONFIRMATION", "Please enter your confirmation code to verify your User name", true, true, true, true, "mail", true);
                        String usarMail = serverCall.checkEmail(txtUserName.getText());
                        txtEmail.setText(usarMail);
                        Random random = new Random();
                        String id = String.format("%04d", random.nextInt(10000));
                        SendEmailService sc = new SendEmailService();
                        sc.sendVerificationCode(id, usarMail);
                        userData.RandomeNo = id;
                        userData.UserMail = usarMail;

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
    void btnSaveClick(ActionEvent event) {
        try {
            if (!txtUserName.getText().isEmpty() && !txtConfirmpassword.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtNewPassword.getText().isEmpty()) {
                if (txtNewPassword.getText().length() == txtConfirmpassword.getText().length() && txtConfirmpassword.getText().equals(txtNewPassword.getText())) {
                    if (ConstantElement.isVerified) {
                        serverCall.updatePassword(txtUserName.getText(), txtNewPassword.getText(), txtConfirmpassword.getText());
                        Stage stage = (Stage) btnBack.getScene().getWindow();
                        stage.close();
                    } else {
                        validatorService.validateConditionErrors("INVALID VERIFICATION", "Invalid verification", false, false, true, false, false);
                    }
                } else {
                    validatorService.validateConditionErrors("PASSWORD MISSMATCH", "Pleasecheck your confirmation password again", false, false, true, false, false);
                }
            } else {
                validatorService.validateConditionErrors("CHECK INPUTS", "Please check your inputs", false, false, true, false, false);
            }
        } catch (Exception e) {
        }
    }
}
