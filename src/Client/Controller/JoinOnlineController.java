/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class JoinOnlineController implements Initializable {

    @FXML
    private AnchorPane anchorJoinGame;

    @FXML
    private ComboBox<?> cmbGroup;

    @FXML
    private Button btnJoin;

    @FXML
    private Button btnLeave;

    @FXML
    void btnJoinClicked(ActionEvent event) {

    }

    @FXML
    void btnLeaveClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
