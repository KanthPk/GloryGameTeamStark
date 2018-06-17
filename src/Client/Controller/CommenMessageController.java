/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import glory_schema.ConstantElement;
import glory_services.MessageService;
import glory_services.SendEmailService;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class CommenMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
     ConstantElement ce = new ConstantElement();
    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    @FXML
    private ImageView imgMsgType;

    @FXML
    private Label lblMsgHeader;

    @FXML
    private Label lblMsgBody;

    @FXML
    private TextField txtInfo;

    private String value;
    
    //To get the verification code genarated
    public String genaratedCode;
    public String Recievermail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            imgMsgType.setImage(MessageService.getMakeMessageUI());
            //add to the group         
            txtInfo.setVisible(MessageService.visiblityForTextField);
            txtInfo.setEditable(MessageService.visiblityForTextField);
            lblMsgHeader.setText(MessageService.headerName);
            lblMsgBody.setText(MessageService.msgValue);
            //btnOK.setDisable(MessageService.isNeedBtnOK);
           // if (!MessageService.isNeedBtnOK) {
           //     btnCancel.setText("OK");
           // } else if (MessageService.isNeedBtnOK) {
          //      btnCancel.setText("Cancel");
          //  }
          //  btnOK.setVisible(MessageService.isNeedBtnOK);
         //   btnOK.setText("Ok");

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    void btnCancelClicked(ActionEvent event) {
        try {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }
    
    @FXML
    void setGenaratedCode( String Code){
        genaratedCode = Code;
        
    }
    
    void setRecievermail( String mail){
        Recievermail = mail;
        
    }
    
    void getCode(String UserVerificationCode)
 {
     genaratedCode =  UserVerificationCode;  
 }

    

    @FXML
    void btnOKClicked(ActionEvent event) {
        try {         
            if (MessageService.forEmailConfirmation) {    
                System.out.println(""+MessageService.forEmailConfirmation);
               try {
          value = txtInfo.getText();
            if (!value.isEmpty()) {
                txtInfo.setStyle("-fx-border-color: BLACK;");
                if(value.equals(ce.RandomeNo) )
                {
                JOptionPane.showMessageDialog(null, "Email successfully verified" , "InfoBox: " + "Email verification", JOptionPane.INFORMATION_MESSAGE);
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
                }
                else{
                    
                txtInfo.clear();
                JOptionPane.showMessageDialog(null, "Email verification code is incorrect please resend the code" , "Error: " + "Email verification", JOptionPane.ERROR_MESSAGE);              
                }
               
            } else {
                txtInfo.setStyle("-fx-border-color: RED;");
            }
        }
        catch (Exception e) {
        }
        catch (Throwable ex) {
            Logger.getLogger(CommenMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            } else if (MessageService.forRandomSelectionFromTheBag) {
                TextField txtRef = new TextField();
                if (MessageService.forceToClose) {
                    Stage stage = (Stage) btnOK.getScene().getWindow();
                    stage.close();
                }
            }
               else if(MessageService.forCahangePassword){
            try {
          value = txtInfo.getText();
            if (!value.isEmpty()) {
                txtInfo.setStyle("-fx-border-color: BLACK;");
                if(value.equals(ce.RandomeNo) )
                {
                JOptionPane.showMessageDialog(null, "Email successfully verified" , "InfoBox: " + "Email verification", JOptionPane.INFORMATION_MESSAGE);
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
                }
                else{
                    
                txtInfo.clear();
               
                }
               
            } else {
                txtInfo.setStyle("-fx-border-color: RED;");
            }
        }
        catch (Exception e) {
        }
        catch (Throwable ex) {
            Logger.getLogger(CommenMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }
             else if(MessageService.forCahangeUserName)
        {
            
                        try {
          value = txtInfo.getText();
            if (!value.isEmpty()) {
                txtInfo.setStyle("-fx-border-color: BLACK;");
                if(value.equals(ce.RandomeNo) )
                {
                JOptionPane.showMessageDialog(null, "Email successfully verified" , "InfoBox: " + "Email verification", JOptionPane.INFORMATION_MESSAGE);
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
                }
                else{
                    
                txtInfo.clear();
               
                }
               
            } else {
                txtInfo.setStyle("-fx-border-color: RED;");
            }
        }
        catch (Exception e) {
        }
        catch (Throwable ex) {
            Logger.getLogger(CommenMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }
                  
        } catch (Exception e) {
        }
    }
        @FXML
    void btnSendmailClicked(ActionEvent event) {
        try {
           
                    Random random = new Random();
                    String id = String.format("%04d", random.nextInt(10000));
                    SendEmailService sc = new SendEmailService();            
                    sc.sendVerificationCode(id,ce.UserMail);
                    setGenaratedCode(id);
        }
        
             
         catch (Exception e) 
        {}
            
       
    }
    
}
