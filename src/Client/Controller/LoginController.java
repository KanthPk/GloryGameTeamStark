/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import animation.TransitionService;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class LoginController implements Initializable {

    @FXML
    AnchorPane root;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private CheckBox chkCheckBox;

    @FXML
    private Label lblSignUp;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private ImageView lblTeamLogo;
    
    TransitionService service;

    /**
     * Initializes the controller class.
     */
    
    public LoginController() {
        service = new TransitionService();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblForgotPassword.setOnMouseClicked(event -> {
            this.route("ForgotPassword", "/UI/ForgotPassword.fxml");
        });

        this.lblUserName.setOnMouseClicked(event -> {
            this.route("ForgotUserName", "/UI/ForgotUserName.fxml");
        });

        this.lblSignUp.setOnMouseClicked(event -> {
            this.route("RegisterUser", "/UI/RegisterUser.fxml");
        });
    }
    @FXML
    private void btnLoginClicked(ActionEvent event) throws IOException {
        // get user name and password from here
        // validate the user and then navidate to the home
        // TocheckAuthentication
        String Access = "Access";
        String Denied = "Denied";
        try {
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/login.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            ps.print("&username=" + txtUserName.getText());
            ps.print("&password=" + pwdPassword.getText());
            con.getInputStream();
            ps.close();
            DataInputStream inStream = new DataInputStream(con.getInputStream());
            String inputLine = inStream.readLine();
            System.out.println("Pk Testing    " + inputLine);
            if (inputLine.equals(Access)) {
                Parent parentHome = FXMLLoader.load(getClass().getResource("/UI/Home.fxml"));
                Scene home_page_scene = new Scene(parentHome);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }
            if (inputLine.equals(Denied)) {
                //dummy messagebox need to change it
                JOptionPane.showMessageDialog(null, "That password/username is incorrect. Try again.", "InfoBox: " + "InValid Login", JOptionPane.INFORMATION_MESSAGE);
            }
            inStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void route(String caseID, String path) {
        AnchorPane layout;
        Stage stage;

        try {
            switch (caseID) {
                case "RegisterUser": {
                    layout = null;
                    stage = null;
                    layout = FXMLLoader.load(getClass().getResource(path));
                    stage = new Stage();
                    stage.setScene(new Scene(layout));
                    //if need it take it
                    //service.makeFadeOut(root).play();
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    break;
                }
                case "ForgotPassword": {
                    layout = null;
                    stage = null;
                    layout = FXMLLoader.load(getClass().getResource(path));
                    stage = new Stage();
                    stage.setScene(new Scene(layout));
                    //if need it take it
                    //service.makeFadeOut(root).play();
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    break;
                }
                case "ForgotUserName": {
                    layout = null;
                    stage = null;
                    layout = FXMLLoader.load(getClass().getResource(path));
                    stage = new Stage();
                    stage.setScene(new Scene(layout));
                    //if need it take it
                    //service.makeFadeOut(root).play();
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    break;
                }
                default:
                    throw new Exception("Invalid case ID");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    @FXML
    private void closeApplication() {
        Platform.exit();
        System.exit(0);
    }
    @FXML
    private void imgMinimizeApplication() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }
}
