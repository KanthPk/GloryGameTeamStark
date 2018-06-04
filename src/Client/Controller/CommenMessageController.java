/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import glory_services.MessageService;
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
 * @author AshanPerera
 */
public class CommenMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            imgMsgType.setImage(MessageService.getMakeMessageUI());
            txtInfo.setVisible(true);
            txtInfo.setEditable(true);
            lblMsgHeader.setText(MessageService.headerName);
            lblMsgBody.setText(MessageService.msgValue);
            btnOK.setText("Ok");
            btnCancel.setText("Cancel");
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
    void btnOKClicked(ActionEvent event) {
        try {
            //if needed
            value = txtInfo.getText();
            System.out.println("" + value);
            if (!value.isEmpty()) {
                txtInfo.setStyle("-fx-border-color: BLACK;");
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            } else {
                txtInfo.setStyle("-fx-border-color: RED;");
            }
        } catch (Exception e) {
        }
    }
}
