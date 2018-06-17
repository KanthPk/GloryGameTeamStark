/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import glory_services.NavigationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class HomeController implements Initializable {

    //Global Variable,begin
    ConstantElement Const;
    MiddleTier ServerCall = new MiddleTier();
    MiddleTier obj;
    ArrayList<String> users;
    private int returnedNoOfUsers = 0;

    private ArrayList<String> listOfGroups = new ArrayList<String>();
    //Global Variable,end

    @FXML
    private AnchorPane homeRoot;

    @FXML
    private Button btnPlay;

    @FXML
    private ImageView imgSettings;

    @FXML
    private ImageView imgMinimize;

    @FXML
    private AnchorPane ancherGroupLoader;

    @FXML
    private ProgressBar progressGameLoader;

    @FXML
    private ListView<?> listGroupMembers;

    @FXML
    private Label lblGroupName;

    @FXML
    private AnchorPane GroupAncher;

    @FXML
    private Button btnCreateGroup;

    @FXML
    private Button btnJoinGroup;

    @FXML
    private AnchorPane AnchrcreateGroup;

    @FXML
    private TextField txtGroupName;

    @FXML
    private TextField txtNoOfPlayers;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnLeave;

    @FXML
    private Label lblGroupViewHeading;
    @FXML
    private ListView listGroupViewMembers;

    @FXML
    private Button btnProceed;

    @FXML
    private Button btnLeaveGroupView;

    @FXML
    private AnchorPane ancherGroupView;

    @FXML
    private AnchorPane AnchorForJoinLive;

    @FXML
    private ComboBox cmbGroup;

    @FXML
    private Button btnJoinGroupLeave;

    @FXML
    private Button btnJoinToLIve;

    NavigationService navigationService;

    public HomeController() {
        //navigationService = new NavigationService(""); //needed if
        obj = new MiddleTier();
        Const = new ConstantElement();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> users = new ArrayList<String>();
        btnPlay.setDisable(ConstantElement.isDisableBtnPlay);
        String[] user = null;
        user = ServerCall.onlineUsers();
        int userIndex = 1;
        for (String user1 : users) {
            System.out.println(userIndex + ". " + user1);
            userIndex++;
        }

        btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());
        btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());

        txtNoOfPlayers.setTextFormatter(new TextFormatter<>((TextFormatter.Change t) -> {
            t.setText(t.getText().replaceAll(".*[^0-9].*", "").toUpperCase());
            return t;
        }));

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

    }

    public void getObject(ConstantElement login) {
        Const = login;
    }

    @FXML
    void btnPlayClicked(ActionEvent event) throws IOException {
        commonBehaviour("Group");
    }

    @FXML
    private void closeApplication() {
        if (!ConstantElement.isDisableBtnPlay && !ConstantElement.isPopedUp) {
            Boolean output;//needed for further implement      
            Platform.exit();
            //serverCallToLogout
            output = ServerCall.Logout(ConstantElement.GlobalUserName, ConstantElement.GlobalPassowrd);
            System.exit(0);
        }
    }

    @FXML
    private void imgMinimizeApplication() {
        Stage stage = (Stage) homeRoot.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void imgSettingsOnPress(MouseEvent event) {
        System.out.println("Hello");
    }

    @FXML
    void btnCreateGroupClicked(ActionEvent event) {
        try {
            commonBehaviour("CreateGroup");
        } catch (Exception e) {
        }
    }

    @FXML
    void btnJoinGroupClicked(ActionEvent event) {
        try {
            commonBehaviour("JoinGroup");
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnCreateClicked(ActionEvent event) {
        try {
            obj.setGroup(txtGroupName.getText(), Const.GlobalUserName, txtNoOfPlayers.getText());
            ConstantElement.GroupName = txtGroupName.getText();
            ConstantElement.no_of_players = Integer.parseInt(txtNoOfPlayers.getText());
            commonBehaviour("ViewGroup");
            setGroups();
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveClicked(ActionEvent event) {
        try {
            ConstantElement.isPopedUp = false;
            ConstantElement.isDisableBtnPlay = false;
            commonBehaviour("MakeAllInvicible");
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnProceedClicked(ActionEvent event) {
        try {
            service.cancel();
            if (!users.isEmpty()) {
                for (int i = 0; i < 3; i++) {
                    ConstantElement.userArray[i] = users.get(i);
                }
                ConstantElement.userArray[3] = ConstantElement.GlobalUserName;
                System.out.println("" + Arrays.toString(ConstantElement.userArray));
            }
            System.out.println("Hello world");
            //navigationService.OneDropNavigation(event);
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveGroupViewClicked(ActionEvent e) {
        service.cancel();
        ConstantElement.isPopedUp = false;
        ConstantElement.isDisableBtnPlay = false;
        commonBehaviour("MakeAllInvicible");
    }

    @FXML
    private void btnJoinGroupLeaveClicked(ActionEvent e) {
        ConstantElement.isPopedUp = false;
        ConstantElement.isDisableBtnPlay = false;
        commonBehaviour("MakeAllInvicible");
    }

    @FXML
    private void btnJoinToLIveClicked(ActionEvent e) {
        ConstantElement.GroupName = cmbGroup.getValue().toString();
        ConstantElement.isJoin = true;
        if (!ConstantElement.GroupName.isEmpty()) {
            commonBehaviour("ViewGroup");
            obj.setGroup(txtGroupName.getText(), Const.GlobalUserName, "");
            setGroups();
        }
    }

    private void route(String id, String path) throws IOException {
        AnchorPane layout;
        Stage stage;
        switch (id) {
            case "group":
                layout = null;
                stage = null;
                layout = FXMLLoader.load(getClass().getResource(path));
                stage = new Stage();
                stage.centerOnScreen();
                stage.setScene(new Scene(layout));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                break;
        }
    }

    private void commonBehaviour(String id) {
        switch (id) {
            case "Group":
                GroupAncher.setVisible(true);
                break;
            case "CreateGroup":
                GroupAncher.setVisible(false);
                AnchrcreateGroup.setVisible(true);
                break;
            case "ViewGroup":
                GroupAncher.setVisible(false);
                AnchrcreateGroup.setVisible(false);
                AnchorForJoinLive.setVisible(false);
                lblGroupViewHeading.setText(ConstantElement.GroupName);
                ancherGroupView.setVisible(true);
                break;
            case "JoinGroup":
                GroupAncher.setVisible(false);
                AnchrcreateGroup.setVisible(false);
                ancherGroupView.setVisible(false);
                AnchorForJoinLive.setVisible(true);
                loadComboValues();
                break;
            case "MakeAllInvicible":
                GroupAncher.setVisible(false);
                AnchrcreateGroup.setVisible(false);
                ancherGroupView.setVisible(false);
                AnchorForJoinLive.setVisible(false);
                break;
            default:
            //all invicible
        }
    }

    private void setGroups() {
        if ((!ConstantElement.GroupName.isEmpty() && ConstantElement.no_of_players != 0) || (!ConstantElement.GroupName.isEmpty() && ConstantElement.isJoin)) {
            getUsers();
            service.start();
        }
    }

    private void getUsers() {
        //JSONObject userJsonObjects = null;
        //JSONArray array = null;
        users = new ArrayList<String>();
        try {
            JSONArray array = obj.getGroup();
            returnedNoOfUsers = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < returnedNoOfUsers; i++) {
                    JSONObject userJsonObjects = (JSONObject) array.get(i);
                    String UserName = (String) userJsonObjects.get("UserName");
                    if (!UserName.isEmpty()) {
                        //currUser = UserName;
                        users.add(UserName);
                    }
                }
                listGroupViewMembers.getItems().clear(); 
                for (String userStringObject : users) {
                    listGroupViewMembers.getItems().add(userStringObject);
                }
                System.out.println("" + Arrays.toString(users.toArray()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Service<Void> service = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    final CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                TimerTask timerDataRetrieval = new TimerTask() {
                                    @Override
                                    public void run() {
                                        //array.clear();
                                        //users.clear();
                                        getUsers();
                                    }
                                };
                                Timer retrievalTimer = new Timer();
                                retrievalTimer.scheduleAtFixedRate(timerDataRetrieval, 10, 10000);
                            } finally {
                                latch.countDown();
                            }
                        }
                    });
                    latch.await();
                    return null;
                }
            };
        }
    };

    private void loadComboValues() {
        try {
            JSONArray array = obj.getGroup();
            int n = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    JSONObject jo = (JSONObject) array.get(i);
                    String GroupName = (String) jo.get("GroupName");
                    if (!GroupName.isEmpty()) {
                        listOfGroups.add(GroupName);
                    }
                }
                cmbGroup.getItems().clear();
                for (String groups : listOfGroups) {
                    cmbGroup.getItems().add(groups);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
