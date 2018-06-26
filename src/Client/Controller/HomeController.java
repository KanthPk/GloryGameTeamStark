package Client.Controller;

import Server.Controller.MiddleTier;
import Threads.ChatThread;
import glory_schema.ConstantElement;
import glory_schema.LetterValueElement;
import glory_services.ValidatorService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
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
    int usercount = 0;
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

    @FXML
    private Label globalUser;
    @FXML
    private TextArea txtmessage;

    @FXML
    private Button btnSend;

    @FXML
    private ComboBox cmbNoOfplayers;

    private Timeline livePlayersTimeLine;
    private String Chatreciver;
    ValidatorService messageService;
    private LetterValueElement letterElement;

    ChatThread chatSavingProcessThread;
    Thread chatThreadInject;

    public HomeController() {
        messageService = new ValidatorService();
        obj = new MiddleTier();
        Const = new ConstantElement();
        letterElement = new LetterValueElement();
        randomGenCharacters = new String[4];
        chatSavingProcessThread = new ChatThread();
        chatThreadInject = new Thread(chatSavingProcessThread);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnSend.setOnAction((event) -> {
            try {
                if (!txtmessage.getText().isEmpty()) {
                    ConstantElement.message = txtmessage.getText();
                    ConstantElement.chatReciever = Chatreciver;                   
                    //chatThreadInject.start();                   
                    ServerCall.sendMessage(ConstantElement.GlobalUserName, ConstantElement.message, ConstantElement.chatReciever);
                    ConstantElement.message = "";
                    ConstantElement.chatReciever = "";
                    // chatThreadInject.stop();                    
                }
            } catch (Exception ex) {
            }
        });

        for (int i = 2; i <= 4; i++) {
            cmbNoOfplayers.getItems().add(i);
        }

        //accountUser.setText(ConstantElement.GlobalUserName);
        globalUser.setText(ConstantElement.GlobalUserName);
        getOnlineUsers();
        btnProceed.setDisable(true);
        //Initialize Music      
        ConstantElement.player();
        ConstantElement.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        //writeFileStart if not exsist
        
        File f = new File("UserSettings.txt");
        if (!f.exists()) {
            String bytes = "on";
            byte[] buffer = bytes.getBytes();
            try {
               
               FileOutputStream outputStream = new FileOutputStream("UserSettings.txt");
                outputStream.write(buffer);
            } catch (IOException ex) {
                System.out.println("Error writing file '" + "UserSettings" + "'");
            } catch (Exception e) {
                System.out.println("Error Connection ");
            }
        }
        //writeFileEnd

        Scanner file = null;
        try {
            
        
        file = new Scanner(new File("UserSettings.txt"));
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
                String stat = "on";
                byte[] statbuffer = stat.getBytes();
                try {                   
                    FileOutputStream outputStream = new FileOutputStream("UserSettings.txt");
                    outputStream.write(statbuffer);
                } catch (IOException ex) {
                    System.out.println("Error writing file '" + "UserSettings" + "'");
                } catch (Exception e) {
                    System.out.println("Error Connection ");
                }
                ConstantElement.mediaPlayer.play();

            }
        }
        if (!ConstantElement.isFinished) {
            try {
                livePlayerService.start();
            } catch (Exception e) {
            }
        }
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
        System.out.println("imgSettingsOnPress");
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
            obj.setGroup(txtGroupName.getText(), ConstantElement.GlobalUserName, cmbNoOfplayers.getValue().toString());
            ConstantElement.GroupName = txtGroupName.getText();
            ConstantElement.no_of_players = Integer.parseInt(cmbNoOfplayers.getValue().toString());
            obj.setGroupUSer(ConstantElement.GroupName, ConstantElement.GlobalUserName);
            commonBehaviour("ViewGroup", null);
            setGroups();
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveClicked(ActionEvent event) {
        try {
            commonBehaviour("MakeAllInvicible", null);
            ConstantElement.isPopedUp = false;
            ConstantElement.isDisableBtnPlay = false;
            ServerCall.leaveGroup(ConstantElement.GroupName, ConstantElement.GlobalUserName);
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnProceedClicked(ActionEvent event) {
        try {
            if (!users.isEmpty()) {
                //commonBehaviour("PrepareForGame", event);
                ConstantElement.mediaPlayer.stop();
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveGroupViewClicked(ActionEvent e) {
        ConstantElement.isPopedUp = false;
        ConstantElement.isDisableBtnPlay = false;
        try {
            commonBehaviour("MakeAllInvicible", null);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnJoinGroupLeaveClicked(ActionEvent e) {
        ConstantElement.isPopedUp = false;
        ConstantElement.isDisableBtnPlay = false;
        try {
            commonBehaviour("MakeAllInvicible", null);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnJoinToLIveClicked(ActionEvent event) {
        try {
            ConstantElement.GroupName = cmbGroup.getValue().toString();
            livePlayerService.cancel();
            livePlayersTimeLine.stop();
            if (!ConstantElement.GroupName.isEmpty()) {
                ConstantElement.no_of_players = PlayersArray[cmbGroup.getSelectionModel().getSelectedIndex()];
                ConstantElement.isJoin = true;
                if (!ConstantElement.GroupName.isEmpty()) {
                    try {
                        commonBehaviour("ViewGroup", null);
                    } catch (IOException ex) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obj.setGroupUSer(ConstantElement.GroupName, ConstantElement.GlobalUserName);
                    setGroups();
                }
            }
        } catch (Exception ex) {
            messageService.validateConditionErrors("CHECK INPUTS", "Please select your group", false, false, true, false, false);

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
                        items.add(users.get(i));
                    }
                }
                try {
                    for (int j = 1; j < 4; j++) {
                        randomGenCharacters[j] = Character.toString(letterElement.randomGen());
                    }
                    listViewAboutToLoad.setItems(items);
                    setProgress(event);
                } catch (Exception e) {
                }
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
            try {
                getUsers();
                service.start();
            } catch (Exception e) {
            }
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
                                    if (users.size() == ConstantElement.no_of_players || (users.size() >= ConstantElement.no_of_players) && (users.size() < ConstantElement.no_of_players)) {
                                        try {
                                            timeline.stop();
                                            btnProceed.setDisable(false);
                                            commonBehaviour("PrepareForGame", ev);
                                        } catch (IOException ex) {
                                        }
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
            int count = 0;
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
                    PlayersArray[count] = Integer.parseInt(noPlayers);
                    count++;
                }
            }
        } catch (Exception e) {
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
                            ServerCall.setInitialLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName,
                                    randomGenCharacters[1], randomGenCharacters[2],
                                    randomGenCharacters[3]);
                            Thread.sleep(1000);
                            ConstantElement.prepareToSave = false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (ConstantElement.isFinished) {
                        service.cancel();
                        timeline.stop();
                        livePlayerService.cancel();
                        livePlayersTimeLine.stop();
                        try {
                            FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("/UI/Game.fxml"));
                            Parent window = (Pane) fmxlLoader.load();
                            Scene home_page_scene = new Scene(window);
                            Stage app_stage = (Stage) progressGameLoader.getScene().getWindow();
                            app_stage = (Stage) txtGroupName.getScene().getWindow();
                            app_stage.setScene(home_page_scene);
                            app_stage.centerOnScreen();
                            app_stage.show();
                        } catch (Exception e) {

                        }
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
                    Thread.sleep(550);
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
                    try {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    livePlayersTimeLine = new Timeline(new KeyFrame(Duration.seconds(18), ev -> {
                                        //after server call
                                        System.out.println("Calm Thread in home UI ->>>>");
                                        usercount = 0;
                                        getOnlineUsers();
                                        getChatMessage();

                                    }));
                                    livePlayersTimeLine.setCycleCount(Animation.INDEFINITE);
                                    livePlayersTimeLine.play();
                                } catch (Exception e) {
                                    messageService.validateLiveError("CONNECTION FAILED", "Something wrong with the server, Please try again", false, false, true, false, "Live", false);
                                }
                            }
                        });
                        latch.await();

                    } catch (Exception e) {
                        messageService.validateLiveError("CONNECTION FAILED", "Something wrong with the server, Please try again", false, false, true, false, "Live", false);
                    }
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
                int usercount = 0;
                for (String userStringObject : users) {
                    if (!userStringObject.equals(ConstantElement.GlobalUserName)) {
                        usercount++;
                        onlineCountLabel.setText(Integer.toString(usercount));
                        Label lbl = new Label(userStringObject);
                        lbl.setAlignment(Pos.CENTER);
                        ImageView imgView = new ImageView(new Image("/resources/default.png"));
                        imgView.setFitHeight(40);
                        imgView.setFitWidth(40);
                        lbl.setGraphic(imgView);
                        userList.getItems().add(lbl);
                    }
                    userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                String value = userList.getSelectionModel().getSelectedItems().toString();
                                value = value.substring(value.indexOf("'"));
                                value = value.replaceAll("']", "'");
                                value = value.replaceFirst("'", "");
                                value = value.replaceFirst("'", "");
                                Chatreciver = value;
                            } catch (Exception e) {
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            messageService.validateLiveError("CONNECTION FAILED", "Please check your connection", false, false, true, false, "Live", false);
        }
    }

    private void getChatMessage() {
        users = new ArrayList<String>();
        try {
            JSONArray array = ServerCall.getChatMessage(ConstantElement.GlobalUserName);
            int cou = array.size();
            txtmessage.clear();
            if (!array.isEmpty()) {
                for (int i = 0; i < cou; i++) {
                    JSONObject userJsonObjects = (JSONObject) array.get(i);
                    String Message = (String) userJsonObjects.get("Message");
                    String sender = (String) userJsonObjects.get("Sender");
                    String Receiver = (String) userJsonObjects.get("Receiver");
                    if (!Message.isEmpty() && Receiver.equals(ConstantElement.GlobalUserName)) {
                        txtmessage.setText(sender + ".." + Message);
                    }
                }
            }
        } catch (Exception e) {
            messageService.validateLiveError("CONNECTION FAILED", "Please check your connection", false, false, true, false, "Live", false);
        }
    }

}
