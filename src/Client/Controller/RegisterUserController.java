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
import javafx.stage.Stage;
import java.util.Random;
import glory_schema.ConstantElement;
import glory_services.SendEmailService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class RegisterUserController implements Initializable {

    //Global Variable,begin   
    ConstantElement userData = new ConstantElement();
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
    public RegisterUserController() {
        //inject validator service
        validatorService = new ValidatorService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtEmail.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!txtEmail.getText().isEmpty()) {

                if (!newV) {
                    if (EmailValidator.getInstance().isValid(txtEmail.getText())) {
                        try {
                            validatorService.getMailMessageBox("MAIL CONFIRMATION", "Please enter your confirmation code to verify your email", true, true, true, true, "mail", true);
                            String usarMail = txtEmail.getText().toString().trim();
                            Random random = new Random();
                            String id = String.format("%04d", random.nextInt(10000));
                            SendEmailService sc = new SendEmailService();
                            sc.sendVerificationCode(id, usarMail);
                            userData.RandomeNo = id;
                            userData.UserMail = usarMail;
                        } catch (Exception e) {
                        }
                    } else {
                        validatorService.validateConditionErrors("INVALID EMAIL", "Please enter a valid email", false, false, true, false, false);
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
        if (!txtUserName.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtConfirmPassword.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            if (txtPassword.getText().equals(txtConfirmPassword.getText()) && txtPassword.getText().length() == txtConfirmPassword.getText().length()) {
                String bytes = "on";
                byte[] buffer = bytes.getBytes();
                try {
                    if (ConstantElement.isVerified) {
                        ServerCall.registerUser(txtUserName.getText(), txtEmail.getText(), txtPassword.getText(), txtConfirmPassword.getText());                      
                        FileOutputStream outputStream = new FileOutputStream("UserSettings.txt");
                        outputStream.write(buffer);
                        Stage stage = (Stage) btnBack.getScene().getWindow();
                        stage.close();
                    } else if (!ConstantElement.isVerified) {
                        validatorService.validateConditionErrors("INVALID VERIFICATION", "Invalid verification", false, false, true, false, false);
                    }
                } catch (IOException ex) {
                    System.out.println("Error writing file '" + "UserSettings" + "'");
                } catch (Exception e) {
                    System.out.println("Error Connection ");
                }
            } else {
                validatorService.validateConditionErrors("PASSWORD MISS MATCH", "Please check your confirmation password again", false, false, true, false, false);
            }
        } else {
            validatorService.validateConditionErrors("CHECK INPUTS", "Please check your inputs", false, false, true, false, false);
        }
    }
}
