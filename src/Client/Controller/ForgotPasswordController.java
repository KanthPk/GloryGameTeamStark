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



import glory_services.MessageService;
import glory_services.SendEmailService;

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
    @FXML
    private TextField txtEmail;

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
    private Button btnSendCode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnSendCode.setVisible(true);
        //txtNewPassword.setVisible(false);
       // txtConfirmpassword.setVisible(false);
        


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
