/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import glory_services.NavigationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class WinnerScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnHome;

    @FXML
    private Label lbl_Gllobal_User;

    @FXML
    private ImageView userImageView1112;

    @FXML
    private Label lbl_Gllobal_User1;

    @FXML
    private Label lbl_Gllobal_User2;

    @FXML
    private ImageView userImageView11121;

    @FXML
    private Label lbl_Gllobal_User21;

    @FXML
    private ImageView userImageView111211;

    @FXML
    private Label lbl_Gllobal_User211;

    @FXML
    private ImageView userImageView1112111;

    @FXML
    private Label firstPlace;

    @FXML
    private Label secondPlace;

    @FXML
    private Label thirdPlace;

    @FXML
    private Label fourthPlace;

    @FXML
    private Label firstPlaceScore;

    @FXML
    private Label thirdPlaceScore;

    @FXML
    private Label secondPlaceScore;

    @FXML
    private Label fourthPlaceScore;
    NavigationService navigationService;

    public WinnerScreenController() {
        navigationService = new NavigationService("/UI/Home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        btnHome.setOnAction(event -> {
            //navigate
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Home.fxml"));
            Stage stage = (Stage) secondPlaceScore.getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException ex) {
                Logger.getLogger(WinnerScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setFullScreen(true);
            stage.setScene(scene);       
            stage.show(); 
            //tage.fullScreenProperty();          
              
        });
    }
}
