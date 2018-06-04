/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import glory_schema.Bag;
import glory_services.MessageService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class GameController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnPause;

    @FXML
    private TextField txt_1;

    @FXML
    private TextField txt_2;

    @FXML
    private TextField txt_3;

    @FXML
    private TextField txt_4;

    @FXML
    private TextField txt_5;

    @FXML
    private TextField txt_6;

    @FXML
    private TextField txt_7;

    @FXML
    private TextField txt_8;

    @FXML
    private TextField txtWordFIeld;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnHome;

    @FXML
    private TextField txtRandom_1;

    @FXML
    private TextField txtRandom_2;

    @FXML
    private TextField txtRandom_3;

    private Bag bag;
    public boolean verifyInputFromBagForVovel;
    public boolean verifyInputFromBagForConsonent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //value initialization  
        //inject bag object
        bag = new Bag();
        for (int i = 1; i <= 3; i++) {
            txtRandom_1.setText(Character.toString(bag.randomGen()));
            txtRandom_2.setText(Character.toString(bag.randomGen()));
            txtRandom_3.setText(Character.toString(bag.randomGen()));
        }

        //processSelectBagOperation("V");
    }

    @FXML
    void closeApplication(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void imgBagView(MouseEvent event) {
        try {
            AnchorPane layout;
            Stage stage;
            String header = "CHARACTER SELECTION";
            String body = "Do you need consonant or vovel ?";
            MessageService.forRandomSelectionFromTheBag = true;
            MessageService.visiblityForRadioButton = true;
            MessageService.visiblityForTextField = false;
            MessageService.setMakeMessageUI("question", header, body);
            layout = FXMLLoader.load(getClass().getResource("/UI/CommenMessage.fxml"));
            stage = new Stage();
            stage.setScene(new Scene(layout));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (Exception e) {
        }
    }

    public void processSelectBagOperation(String caseID, TextField txtref) {
        try {
            switch (caseID) {
                case "V":
                    if (verifyInputFromBagForVovel) {
                        //logic later  
                        //should have to create a pattern for it
                        txt_3 = new TextField();
                        txt_3.setText(txtref.getText());
                        System.out.println("" + txt_3.getText());
                        System.out.println("For vovel selection");
                    }
                    break;
                case "C":
                    if (verifyInputFromBagForConsonent) {
                        //logic later         
                        //should have to create a pattern for it
                        txt_3 = new TextField();
                        txt_3.setText(txtref.getText());
                        System.out.println("" + txt_3.getText());
                        System.out.println("For Consonent selection");
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
