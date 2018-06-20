/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;


import Server.Controller.MiddleTier;
import animation.TransitionService;
import glory_schema.ConstantElement;
import glory_services.ValidatorService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import java.io.File;
import javax.annotation.Resources;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class LoginController implements Initializable {

    //Global Variable,begin
    ConstantElement Const;
    MiddleTier ServerCall = new MiddleTier();
    //Global Variable,end
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

    private ValidatorService serviceValidater;

    /**
     * Initializes the controller class.
     */
    public LoginController() {
        service = new TransitionService();
        serviceValidater = new ValidatorService();
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
        
        
        
String path = System.getProperty("user.home") + File.separator + "Documents";
path += File.separator + "GloryGameFiles";
File customDir = new File(path);

if (customDir.exists()) {
    System.out.println(customDir + " already exists");
} else if (customDir.mkdirs()) {
    System.out.println(customDir + " was created");
} else {
    System.out.println(customDir + " was not created");
}

copyfiles();

    }
    
    private void copyfiles()
    {
    File source = new File(System.getProperty("user.home")+ File.separator + "Documents"+File.separator+"New folder"+File.separator+"MyLittleAdventure.mp3");
		// Destination file.
		File destination = new File(System.getProperty("user.home") + File.separator + "Documents"+File.separator + "GloryGameFiles"+File.separator+"MyLittleAdventure.mp3");
		
		try {
			copyFile(source, destination);
		} catch (IOException e) {
			System.out.println("Problem occurs when copying files");
			e.printStackTrace();
		}
    
    }
    
    	public static void copyFile(File source, File destination) throws IOException {
		FileUtils.copyFile(source, destination);
	}

    @FXML
    private void btnLoginClicked(ActionEvent event) throws IOException {
        //get user name and password from here
        //validate the user and then navidate to the home
        //TocheckAuthentication
        //guid
        String Access = "Access";
        String Denied = "Denied";
        String Check = null;

        try {
            // open a connection to the site           
            if (!txtUserName.getText().isEmpty() && !pwdPassword.getText().isEmpty()) {
                Check = ServerCall.Login(txtUserName.getText(), pwdPassword.getText());
                if (Check.equals(Access)) {
                    ConstantElement.GlobalUserName = txtUserName.getText();//setGlobalUserValue
                    ConstantElement.GlobalPassowrd = pwdPassword.getText();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/Home.fxml"));
                    Parent parentHome = (Parent) fxmlLoader.load();
                    HomeController controller = fxmlLoader.<HomeController>getController();
                    ConstantElement s = new ConstantElement();
                    s.set_password(pwdPassword.getText());
                    s.set_userId(txtUserName.getText());
                    controller.getObject(s);
                    Scene home_page_scene = new Scene(parentHome);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                } else if (Check.equals(Denied)) {
                    serviceValidater.validateConditionErrors("AUTHENTICATION FAILED", "Please recheck your credentials", false, false, true, false, false);
                }
            } else if (txtUserName.getText().isEmpty() && pwdPassword.getText().isEmpty()) {
                serviceValidater.validateConditionErrors("CHECK INPUTS", "Please check your inputs", false, false, true, false, false);
            } else if (!txtUserName.getText().isEmpty() && pwdPassword.getText().isEmpty()) {
                serviceValidater.validateConditionErrors("CHECK INPUTS", "Please enter your password", false, false, true, false, false);
            } else if (txtUserName.getText().isEmpty() && !pwdPassword.getText().isEmpty()) {
                serviceValidater.validateConditionErrors("CHECK INPUTS", "Please enter your user name", false, false, true, false, false);
            }
        } catch (SecurityException e) {
            serviceValidater.validateLiveError("CONNECTION FAILED", "Something wrong with the server, Please try again", false, false, true, false, "Live", false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            serviceValidater.validateLiveError("CONNECTION FAILED", "Something wrong with the server, Please try again", false, false, true, false, "Live", false);
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
