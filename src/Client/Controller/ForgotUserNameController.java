/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Random;
import glory_schema.ConstantElement;
import glory_services.SendEmailService;
import glory_services.ValidatorService;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class ForgotUserNameController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ConstantElement userData = new ConstantElement();
    MiddleTier servercall = new MiddleTier();
    @FXML
    private Button btnCreate;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtConfirmusername;
    private ValidatorService validatorService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //inject validator service
        validatorService = new ValidatorService();

        txtEmail.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!txtEmail.getText().isEmpty()) {
                if (!newV) {
                    try {
                        validatorService.getMailMessageBox("MAIL CONFIRMATION", "Please enter your confirmation code to verify your User name", true, true, true, true, "mail", true);
                        String usarMail = txtEmail.getText();
                        //Set the current genarated code
                        //setUserRecievedCode(id); 
                        //Genarate randome number and send email
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
    void btnChangeClicked(ActionEvent event) {

        try {
            if (!txtEmail.getText().isEmpty() && !txtConfirmusername.getText().isEmpty() && !txtUsername.getText().isEmpty()) {
                //save the data
                servercall.updateUsername(txtEmail.getText().toString(), txtConfirmusername.getText().toString());
                Stage stage = (Stage) btnBack.getScene().getWindow();
                stage.close();
            } else {
                validatorService.validateConditionErrors("CHECK INPUTS", "Please check your inputs", false, false, true, false, false);
            }
        } catch (Exception e) {
        }

    }
}
