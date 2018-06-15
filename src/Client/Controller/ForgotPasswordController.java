/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;


import glory_schema.ConstantElement;
import glory_services.MessageService;
import glory_services.SendEmailService;
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

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class ForgotPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    ConstantElement userData = new ConstantElement();
    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtOldpassword;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      

//inject validator service
        validatorService = new ValidatorService();
        
        txtUserName.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!txtUserName.getText().isEmpty()) {
                if (!newV) {
                    try {  

                       validatorService.getValidaterMessage("MAIL CONFIRMATION", "Please enter your confirmation code to verify your User name", true, true, false);
                       String usarMail = "maduperera106@gmail.com";
                        //Set the current genarated code
                        //setUserRecievedCode(id); 
                        //Genarate randome number and send email
                    Random random = new Random();
                    String id = String.format("%04d", random.nextInt(10000));
                    SendEmailService sc = new SendEmailService();            
                    sc.sendVerificationCode(id,usarMail);
                    userData.RandomeNo=id;
                    userData.UserMail= usarMail;
                    
                                 
                    } catch (Exception e) {
                    }
                }
            }
        });

        


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

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
            //save the data
        } catch (Exception e) {
        }
    }
}
