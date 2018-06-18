/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.Bag;
import glory_schema.ConstantElement;
import glory_services.NavigationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
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
    TimerTask timerDataRetrieval = null;
    Task progressThread;
    private ArrayList<String> listOfGroups = new ArrayList<String>();
    private String[] randomGenCharacters;
    Timeline timeline = null;
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

    //Group Loader
    @FXML
    private AnchorPane anchorGroupAboutToLoad;

    @FXML
    private Label lblGroupNameAboutToLive;

    @FXML
    private ListView listViewAboutToLoad;

    @FXML
    private ProgressBar progressGameLoader;

    NavigationService navigationService;
    private Bag bag;

    public HomeController() {
        obj = new MiddleTier();
        Const = new ConstantElement();
        bag = new Bag();
        randomGenCharacters = new String[4];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());
        btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());
        txtNoOfPlayers.setTextFormatter(new TextFormatter<>((TextFormatter.Change t) -> {
            t.setText(t.getText().replaceAll(".*[^0-9].*", "").toUpperCase());
            return t;
        }));
    }

    public void getObject(ConstantElement login) {
        Const = login;
    }

    @FXML
    void btnPlayClicked(ActionEvent event) throws IOException {
        commonBehaviour("Group", null);
    }

    @FXML
    private void closeApplication() {
        if (!ConstantElement.isDisableBtnPlay && !ConstantElement.isPopedUp) {
            Boolean output;
            Platform.exit();
            output = ServerCall.Logout(ConstantElement.GlobalUserName, ConstantElement.GlobalPassowrd);
            ServerCall.leaveGroup(ConstantElement.GroupName, ConstantElement.GlobalUserName);
            ServerCall.deleteLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName);
            System.exit(0);
        }
    }

    @FXML
    private void imgMinimizeApplication() {
        Stage stage = (Stage) homeRoot.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void imgSettingsOnPress(MouseEvent event) {
        System.out.println("Hello");
    }

    @FXML
    private void btnCreateGroupClicked(ActionEvent event) {
        try {
            commonBehaviour("CreateGroup", null);
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnJoinGroupClicked(ActionEvent event) {
        try {
            commonBehaviour("JoinGroup", null);
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnCreateClicked(ActionEvent event) {
        try {
            obj.setGroup(txtGroupName.getText(), Const.GlobalUserName, txtNoOfPlayers.getText());
            ConstantElement.GroupName = txtGroupName.getText();
            ConstantElement.no_of_players = Integer.parseInt(txtNoOfPlayers.getText());
            commonBehaviour("ViewGroup", null);
            setGroups();
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveClicked(ActionEvent event) {
        try {
            ConstantElement.isPopedUp = false;
            ConstantElement.isDisableBtnPlay = false;
            commonBehaviour("MakeAllInvicible", null);
            ServerCall.leaveGroup(ConstantElement.GroupName, ConstantElement.GlobalUserName);
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnProceedClicked(ActionEvent event) {
        try {
            if (!users.isEmpty()) {
                commonBehaviour("PrepareForGame", event);
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveGroupViewClicked(ActionEvent e) throws IOException {
        service.cancel();
        ConstantElement.isPopedUp = false;
        ConstantElement.isDisableBtnPlay = false;
        commonBehaviour("MakeAllInvicible", null);
    }

    @FXML
    private void btnJoinGroupLeaveClicked(ActionEvent e) throws IOException {
        ConstantElement.isPopedUp = false;
        ConstantElement.isDisableBtnPlay = false;
        commonBehaviour("MakeAllInvicible", null);
    }

    @FXML
    private void btnJoinToLIveClicked(ActionEvent e) throws IOException {
        ConstantElement.GroupName = cmbGroup.getValue().toString();
        ConstantElement.isJoin = true;
        if (!ConstantElement.GroupName.isEmpty()) {
            commonBehaviour("ViewGroup", null);
            obj.setGroupUSer(ConstantElement.GroupName, ConstantElement.GlobalUserName);
            //System.out.println("     " + ConstantElement.GroupName + ConstantElement.GlobalUserName);
            setGroups();
        }
    }

    private void commonBehaviour(String id, ActionEvent event) throws IOException {
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
            case "PrepareForGame":
                anchorGroupAboutToLoad.setVisible(true);
                GroupAncher.setVisible(false);
                AnchrcreateGroup.setVisible(false);
                ancherGroupView.setVisible(false);
                AnchorForJoinLive.setVisible(false);
                lblGroupNameAboutToLive.setText(ConstantElement.GroupName);

                ObservableList items = FXCollections.observableArrayList();
                /////USER VALIDATION
                for (int i = 0; i < 2; i++) {                    
                    ConstantElement.userArray[i] = users.get(i);
                    items.add(ConstantElement.userArray[i]);
                }
                listViewAboutToLoad.setItems(items);
                for (int i = 1; i < 4; i++) {
                    randomGenCharacters[i] = Character.toString(bag.randomGen());
                }
                setProgress(event);

                break;
            case "MakeAllInvicible":
                GroupAncher.setVisible(false);
                AnchrcreateGroup.setVisible(false);
                ancherGroupView.setVisible(false);
                AnchorForJoinLive.setVisible(false);
                anchorGroupAboutToLoad.setVisible(false);
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
        users = new ArrayList<String>();
        try {
            JSONArray array = obj.getUserGroup();
            returnedNoOfUsers = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < returnedNoOfUsers; i++) {
                    JSONObject userJsonObjects = (JSONObject) array.get(i);
                    String UserName = (String) userJsonObjects.get("UserName");
                    if (!UserName.isEmpty()) {
                        users.add(UserName);
                    }
                }
                listGroupViewMembers.getItems().clear();
                for (String userStringObject : users) {
                    listGroupViewMembers.getItems().add(userStringObject);
                }
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
                                timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                                    getUsers();
                                }));
                                timeline.setCycleCount(Animation.INDEFINITE);
                                timeline.play();
                            } catch (Exception e) {
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
            JSONArray array = obj.getUserGroup();
            int n = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    JSONObject jo = (JSONObject) array.get(i);
                    String GroupName = (String) jo.get("GroupName");
                    if (!GroupName.isEmpty() && !listOfGroups.contains(GroupName)) {
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

    private void setProgress(ActionEvent event) {
        try {
            progressThread = createWorker();
            progressGameLoader.progressProperty().bind(progressThread.progressProperty());
            progressThread.messageProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {                    
                    if (ConstantElement.prepareToSave) {
                        try {
                            System.out.println("" + ConstantElement.prepareToSave);
                            ServerCall.setInitialLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName,
                                    randomGenCharacters[1], randomGenCharacters[2],
                                    randomGenCharacters[3]);
                            Thread.sleep(5000);
                            ConstantElement.prepareToSave = false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (ConstantElement.isFinished) {
                        timeline.stop();
                        Stage app_stage = (Stage) progressGameLoader.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/Game.fxml"));
                        Parent parentHome = null;
                        try {
                            parentHome = (Parent) fxmlLoader.load();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene home_page_scene = new Scene(parentHome);
                        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        app_stage.setScene(home_page_scene);
                        app_stage.show();
                    }
                }
            });
            new Thread(progressThread).start();
        } catch (Exception e) {
        }
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i == 60) {
                        ConstantElement.prepareToSave = true;
                    }
                    Thread.sleep(2000);
                    updateMessage(i + "%");
                    updateProgress(i + 1, 100);
                }
                ConstantElement.isFinished = true;
                return true;
            }
        };
    }

}
