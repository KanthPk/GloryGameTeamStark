package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import glory_services.NavigationService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class WinnerScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ArrayList<String> users;
    MiddleTier ServerCall = new MiddleTier();
    ;
    @FXML
    private Button btnHome;

    @FXML
    private Label lbl_Gllobal_User;

    @FXML
    private ImageView userImageView1;

    @FXML
    private Label lbl_Gllobal_User1;

    @FXML
    private Label lbl_Gllobal_User2;

    @FXML
    private ImageView userImageView2;

    @FXML
    private Label lbl_Global_User3;

    @FXML
    private ImageView userImageView3;

    @FXML
    private Label lbl_Global_User4;

    @FXML
    private ImageView userImageView4;

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
        getTotalScore();
        btnHome.setOnAction(event -> {
            ConstantElement.isFinished = false;
            ConstantElement.mediaPlayer.stop();
            //navigate
            try {                
                ServerCall.leaveGroup(ConstantElement.GroupName, ConstantElement.GlobalUserName);
                ServerCall.deleteLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName);
                ServerCall.deleteRound(ConstantElement.GroupName, ConstantElement.GlobalUserName);
                Stage stageGame = (Stage) btnHome.getScene().getWindow();
                stageGame.close();

                AnchorPane layout = null;
                Stage stage = null;
                layout = FXMLLoader.load(getClass().getResource("/UI/Home.fxml"));
                stage = new Stage();
                stage.setScene(new Scene(layout));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (Exception e) {
            }
        });
    }

    public void getTotalScore() {
        try {            
            lbl_Gllobal_User.setText("");
            lbl_Gllobal_User2.setText("");
            lbl_Global_User3.setText("");
            lbl_Global_User4.setText("");
            users = new ArrayList<String>();
            JSONArray array = ServerCall.getFinalScore();
            int n = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    JSONObject userJsonObjects = (JSONObject) array.get(i);
                    String user = (String) userJsonObjects.get("User");
                    String Score = (String) userJsonObjects.get("Score");

                    if (lbl_Gllobal_User.getText().isEmpty()) {
                        lbl_Gllobal_User.setText(user);
                        firstPlaceScore.setText(Score);
                    } else if (lbl_Gllobal_User2.getText().isEmpty()) {
                        lbl_Gllobal_User2.setText(user);
                        secondPlaceScore.setText(Score);

                    } else if (lbl_Global_User3.getText().isEmpty()) {
                        lbl_Global_User3.setText(user);
                        thirdPlaceScore.setText(Score);
                    } else if (lbl_Global_User4.getText().isEmpty()) {
                        lbl_Global_User4.setText(user);
                        fourthPlaceScore.setText(Score);
                    }
                }
            }
        } catch (Exception s) {
        }
    }

}
