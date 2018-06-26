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
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class CommenMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ConstantElement ce = new ConstantElement();

    @FXML
    private Button btnResend;

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

    //To get the verification code genarated
    private String value;

    public String genaratedCode;
    public String Recievermail;

    public CommenMessageController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initiating behaviours
        imgMsgType.setImage(MessageService.getMakeMessageUI());
        txtInfo.setVisible(ConstantElement.visiblityForTextField);
        txtInfo.setEditable(ConstantElement.visiblityForTextField);
        lblMsgHeader.setText(MessageService.headerName);
        lblMsgBody.setText(MessageService.msgValue);      
        if (!ConstantElement.isNeedBtnOK) {
            btnCancel.setText("OK");
        } else if (ConstantElement.isNeedBtnOK) {
            btnOK.setText("OK");
            btnCancel.setText("Cancel");
        }
        if (ConstantElement.isNeedResendButton) {
            btnResend.setText("Resend");
        }
        btnOK.setVisible(ConstantElement.isNeedBtnOK);
        btnCancel.setVisible(ConstantElement.isNeedBtnCancel);
        btnResend.setVisible(ConstantElement.isNeedResendButton);

    }

    void setGenaratedCode(String Code) {
        genaratedCode = Code;
    }

    void setRecievermail(String mail) {
        Recievermail = mail;

    }

    void getCode(String UserVerificationCode) {
        genaratedCode = UserVerificationCode;
    }

    @FXML
    private void btnCancelClicked(ActionEvent event) {
        try {           
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnOKClicked(ActionEvent event) {
        try {
            if (ConstantElement.forEmailConfirmation) {
                try {
                    value = txtInfo.getText();
                    if (!value.isEmpty()) {
                        txtInfo.setStyle("-fx-border-color: BLACK;");
                        if (value.equals(ce.RandomeNo)) {                                
                            ConstantElement.isVerified = true;
                            Stage stage = (Stage) btnCancel.getScene().getWindow();
                            stage.close();
                        } else {
                            ConstantElement.isVerified = false;
                            txtInfo.clear();                             
                        }

                    } else {
                        txtInfo.setStyle("-fx-border-color: RED;");
                    }
                } catch (Exception e) {
                } catch (Throwable ex) {
                    Logger.getLogger(CommenMessageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (ConstantElement.forCahangePassword) {
                try {
                    value = txtInfo.getText();
                    if (!value.isEmpty()) {
                        txtInfo.setStyle("-fx-border-color: BLACK;");
                        if (value.equals(ce.RandomeNo)) {
                            Stage stage = (Stage) btnCancel.getScene().getWindow();
                            stage.close();
                        } else {
                            txtInfo.clear();
                        }
                    } else {
                        txtInfo.setStyle("-fx-border-color: RED;");
                    }
                } catch (Exception e) {
                } catch (Throwable ex) {
                    Logger.getLogger(CommenMessageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (ConstantElement.forCahangeUserName) {
                try {
                    value = txtInfo.getText();
                    if (!value.isEmpty()) {
                        txtInfo.setStyle("-fx-border-color: BLACK;");
                        if (value.equals(ce.RandomeNo)) {
                            Stage stage = (Stage) btnCancel.getScene().getWindow();
                            stage.close();
                        } else {
                            txtInfo.clear();
                        }
                    } else {
                        txtInfo.setStyle("-fx-border-color: RED;");
                    }
                } catch (Exception e) {
                } catch (Throwable ex) {
                    Logger.getLogger(CommenMessageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {          
        }
    }

    @FXML
    private void btnSendmailClicked(ActionEvent event) {
        try {
            Random random = new Random();
            String id = String.format("%04d", random.nextInt(10000));
            SendEmailService sc = new SendEmailService();
            sc.sendVerificationCode(id, ce.UserMail);
            setGenaratedCode(id);
        } catch (Exception e) {
        }
    }
}
