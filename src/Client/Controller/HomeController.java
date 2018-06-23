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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class HomeController implements Initializable {

    ConstantElement Const;
    MiddleTier ServerCall = new MiddleTier();
    MiddleTier obj;
    ArrayList<String> users;
    private int returnedNoOfUsers = 0;
    TimerTask timerDataRetrieval = null;
    Task progressThread;
    private ArrayList<String> listOfGroups = new ArrayList<String>();
    private ArrayList<String> noOfPlayers = new ArrayList<String>();
    private String[] randomGenCharacters;
    Timeline timeline = null;
    int[] PlayersArray = new int[100];

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
    private Label lblGroupViewSubHeading;

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

    @FXML
    private AnchorPane anchorGroupAboutToLoad;

    @FXML
    private Label lblGroupNameAboutToLive;

    @FXML
    private ListView listViewAboutToLoad;

    @FXML
    private ProgressBar progressGameLoader;

    @FXML
    private ListView userList;

    @FXML
    private Label onlineCountLabel;

    private Timeline livePlayersTimeLine;

    private Bag bag;

    public HomeController() {
        obj = new MiddleTier();
        Const = new ConstantElement();
        bag = new Bag();
        randomGenCharacters = new String[4];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*for (int i = 0; i < 10; i++) {
            Label lbl = new Label("User" + i);
            lbl.setAlignment(Pos.CENTER);
            ImageView imgView = new ImageView(new Image("/resources/default.png"));
            imgView.setFitHeight(40);
            imgView.setFitWidth(40);
            lbl.setGraphic(imgView);
            userList.getItems().add(lbl);
        }
*/      getOnlineUsers();    
        userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + userList.getSelectionModel().getSelectedItem());
            }
        });

        btnProceed.setDisable(true);
        btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());
        btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());
        txtNoOfPlayers.setTextFormatter(new TextFormatter<>((TextFormatter.Change t) -> {
            t.setText(t.getText().replaceAll(".*[^0-9].*", "").toUpperCase());
            return t;
        }));

        //Initialize Music      
        ConstantElement.player();
        ConstantElement.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        Scanner file = null;
        try {
            file = new Scanner(new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "GloryGameFiles" + File.separator + "UserSettings.txt"));
        } catch (FileNotFoundException ex) {

        }
        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (line.compareTo("on") == 0) {
                ConstantElement.mediaPlayer.play();
            } else if (line.compareTo("off") == 0) {
                ConstantElement.mediaPlayer.stop();
            } else {
                // StartMusicByDefault
                String bytes = "on";
                byte[] buffer = bytes.getBytes();
                try {
                    FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "GloryGameFiles" + File.separator + "UserSettings.txt");
                    outputStream.write(buffer);
                } catch (IOException ex) {
                    System.out.println("Error writing file '" + "UserSettings" + "'");
                } catch (Exception e) {
                    System.out.println("Error Connection ");
                }
                ConstantElement.mediaPlayer.play();

            }
        }
        if (!ConstantElement.isFinished) {
            livePlayerService.start();
        }
    }

    public void getObject(ConstantElement login) {
        Const = login;
    }

    @FXML
    void btnPlayClicked(ActionEvent event) throws IOException {
        livePlayerService.cancel();
        livePlayersTimeLine.stop();
        commonBehaviour("Group", null);
    }

    @FXML
    private void closeApplication() {
        if (!ConstantElement.isDisableBtnPlay && !ConstantElement.isPopedUp) {
            Platform.exit();
            ServerCall.Logout(ConstantElement.GlobalUserName, ConstantElement.GroupName);
            ServerCall.leaveGroup(ConstantElement.GroupName, ConstantElement.GlobalUserName);
            //ServerCall.deleteLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName);

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
    void btnSettingsClicked(MouseEvent event) {

        try {
            AnchorPane layout;
            Stage stage;
            layout = null;
            stage = null;
            layout = FXMLLoader.load(getClass().getResource("/UI/UserSettings.fxml"));
            stage = new Stage();
            stage.setScene(new Scene(layout));
            //if need it take it
            //service.makeFadeOut(root).play();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
        }

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
            obj.setGroup(txtGroupName.getText(), ConstantElement.GlobalUserName, txtNoOfPlayers.getText());
            ConstantElement.GroupName = txtGroupName.getText();
            ConstantElement.no_of_players = Integer.parseInt(txtNoOfPlayers.getText());
            //obj.setGroupUSer(ConstantElement.GroupName, ConstantElement.GlobalUserName);           
            commonBehaviour("ViewGroup", null);
            setGroups();
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveClicked(ActionEvent event) {
        try {
            livePlayerService.start();
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
                ConstantElement.mediaPlayer.stop();
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveGroupViewClicked(ActionEvent e) throws IOException {
        livePlayerService.start();
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
        ConstantElement.no_of_players = PlayersArray[cmbGroup.getSelectionModel().getSelectedIndex()];
        ConstantElement.isJoin = true;
        if (!ConstantElement.GroupName.isEmpty()) {
            commonBehaviour("ViewGroup", null);
            obj.setGroupUSer(ConstantElement.GroupName, ConstantElement.GlobalUserName);
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

                for (int i = 0; i < ConstantElement.no_of_players; i++) {
                    if (!users.get(i).isEmpty() && users.get(i) != null) {
                        System.out.println("" + users.get(i));
                        items.add(users.get(i));
                    }
                }
                for (int j = 1; j < 4; j++) {
                    randomGenCharacters[j] = Character.toString(bag.randomGen());
                }
                listViewAboutToLoad.setItems(items);
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
            JSONArray array = obj.getUserGroup(ConstantElement.GroupName, ConstantElement.GlobalUserName);
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
                                    //should be removed
                                    //String val = ServerCall.getCheckPlayer(ConstantElement.GroupName, ConstantElement.GlobalUserName);
                                    if (users.size() == ConstantElement.no_of_players || (users.size() >= ConstantElement.no_of_players) && (users.size() < ConstantElement.no_of_players)) {
                                        btnProceed.setDisable(false);
                                    } else if (users.size() < ConstantElement.no_of_players || users.size() == ConstantElement.no_of_players) {
                                        btnProceed.setDisable(true);
                                        lblGroupViewSubHeading.setText("Please wait for other players");
                                    }
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
            int a = 0;
            JSONArray array = obj.getGroup();
            int n = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    JSONObject jo = (JSONObject) array.get(i);
                    String GroupName = (String) jo.get("GroupName");
                    String playersCount = (String) jo.get("Players");
                    noOfPlayers.add(playersCount);
                    if (!GroupName.isEmpty() && !listOfGroups.contains(GroupName)) {
                        listOfGroups.add(GroupName);
                    }
                }
                cmbGroup.getItems().clear();
                for (String groups : listOfGroups) {
                    cmbGroup.getItems().add(groups);
                }

                for (String noPlayers : noOfPlayers) {
                    PlayersArray[a] = Integer.parseInt(noPlayers);
                    a++;
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
                            System.out.println(ConstantElement.GroupName + ConstantElement.GlobalUserName + "" + ConstantElement.prepareToSave + randomGenCharacters[1] + randomGenCharacters[2] + randomGenCharacters[3]);
                            ServerCall.setInitialLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName,
                                    randomGenCharacters[1], randomGenCharacters[2],
                                    randomGenCharacters[3]);
                            Thread.sleep(5000);
                            ConstantElement.prepareToSave = false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (ConstantElement.isFinished) {
                        service.cancel();
                        timeline.stop();
                        livePlayerService.cancel();
                        livePlayersTimeLine.stop();
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
                    Thread.sleep(200);
                    updateMessage(i + "%");
                    updateProgress(i + 1, 100);
                }
                ConstantElement.isFinished = true;
                return true;
            }
        };
    }

    Service<Void> livePlayerService = new Service<Void>() {
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
                                livePlayersTimeLine = new Timeline(new KeyFrame(Duration.minutes(1), ev -> {
                                    //after server call
                                    System.out.println("Calm Thread in home UI ->>>>");
                                    getOnlineUsers();                                    
                                }));
                                livePlayersTimeLine.setCycleCount(Animation.INDEFINITE);
                                livePlayersTimeLine.play();
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
    private void getOnlineUsers() {
        users = new ArrayList<String>();
        try {
            JSONArray array = obj.onlineUsers();
            int a = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < a; i++) {
                    JSONObject userJsonObjects = (JSONObject) array.get(i);
                    String UserName = (String) userJsonObjects.get("UserName");
                    if (!UserName.isEmpty()) {
                        users.add(UserName);
                    }
                }
                //listGroupViewMembers.getItems().clear();
                userList.getItems().clear();
                int d=0;
                for (String userStringObject : users) {
                    d++;
                    onlineCountLabel.setText(Integer.toString(d));
                    Label lbl = new Label(userStringObject);
                    lbl.setAlignment(Pos.CENTER);
                    ImageView imgView = new ImageView(new Image("/resources/default.png"));
                    imgView.setFitHeight(40);
                    imgView.setFitWidth(40);
                    lbl.setGraphic(imgView);
                    userList.getItems().add(lbl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
