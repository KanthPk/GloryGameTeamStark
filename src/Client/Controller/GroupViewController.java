/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import glory_services.NavigationService;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class GroupViewController implements Initializable {

    private MiddleTier obj = new MiddleTier();
    private ConstantElement Const;
    private JSONArray array = null;
    private int returnedNoOfUsers = 0;
    private JSONObject userJsonObjects = null;
    ArrayList<String> users = new ArrayList<String>();

    @FXML
    private AnchorPane ancherGropuView;

    @FXML
    private ListView listGroupMembers;

    @FXML
    private Button btnProceed;

    @FXML
    private Button btnLeave;

    @FXML
    private Label lblGroupName;

    private NavigationService navigationService;

    public GroupViewController() {
        Const = new ConstantElement();
    }

    @FXML
    void btnLeaveClicked(ActionEvent event) {
        try {
            service.cancel();
            ConstantElement.isPopedUp = false;
            ConstantElement.isDisableBtnPlay = false;
            Stage stage = (Stage) btnLeave.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnProceedClicked(ActionEvent event) {
        try {
            service.cancel();
            if (!users.isEmpty()) {
                for (int i = 0; i < 3; i++) {
                    ConstantElement.userArray[i] = users.get(i);
                }
                ConstantElement.userArray[3] = ConstantElement.GlobalUserName;
                System.out.println("" + Arrays.toString(ConstantElement.userArray));
            }
            //navigationService.OneDropNavigation(event);
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((!ConstantElement.GroupName.isEmpty() && ConstantElement.no_of_players != 0) || (!ConstantElement.GroupName.isEmpty() && ConstantElement.isJoin)) {
            listGroupMembers.getItems().add(ConstantElement.GlobalUserName);
            getUsers();
            service.start();
        }
    }
    
    private void getUsers() {
        try {
            listGroupMembers.getItems().clear();
            array = obj.getGroup();
            returnedNoOfUsers = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < returnedNoOfUsers; i++) {
                    userJsonObjects = (JSONObject) array.get(i);
                    String UserName = (String) userJsonObjects.get("UserName");
                    if (!UserName.isEmpty()) {
                        //currUser = UserName;
                        users.add(UserName);
                    }
                }
                Thread.sleep(1000);
                lblGroupName.setText(ConstantElement.GroupName);
                for (String users : users) {
                    if (!listGroupMembers.equals(users) || listGroupMembers.equals(null)) {
                        listGroupMembers.getItems().add(users);
                    }
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
                                TimerTask timerDataRetrieval = new TimerTask() {
                                    @Override
                                    public void run() {
                                        getUsers();
                                    }
                                };
                                Timer retrievalTimer = new Timer();
                                retrievalTimer.scheduleAtFixedRate(timerDataRetrieval, 10, 1000);
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

}
