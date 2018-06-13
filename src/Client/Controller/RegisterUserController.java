/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import glory_services.MessageService;
import glory_services.SendEmailService;
import glory_services.ValidatorService;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class RegisterUserController implements Initializable {

    //Global Variable,begin   
    MiddleTier ServerCall = new MiddleTier();
    ConstantElement userData = new ConstantElement();
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

                       validatorService.getValidaterMessage("MAIL CONFIRMATION", "Please enter your confirmation code to verify your email", true, true, false);
                       String usarMail = txtEmail.getText().toString().trim();
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
