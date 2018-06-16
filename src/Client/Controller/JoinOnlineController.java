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
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class JoinOnlineController implements Initializable {

    MiddleTier obj = new MiddleTier();
    private ArrayList<String> listOfGroups = new ArrayList<String>();

    @FXML
    private AnchorPane anchorJoinGame;

    @FXML
    private ComboBox cmbGroup;

    @FXML
    private Button btnJoin;

    @FXML
    private Button btnLeave;

    NavigationService navigationService;

    public JoinOnlineController() {
    }

    @FXML
    void btnJoinClicked(ActionEvent event) {
        ConstantElement.GroupName = cmbGroup.getValue().toString();
        ConstantElement.isJoin = true;
        navigationService = new NavigationService("/UI/GroupView.fxml");
        navigationService.OneDropNavigation(event);
    }

    @FXML
    void btnLeaveClicked(ActionEvent event) {
        ConstantElement.isPopedUp = false;
        ConstantElement.isDisableBtnPlay = false;
        Stage stage = (Stage) btnLeave.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getUsers();
        service.start();
    }

    private void getUsers() {
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
                    //Background work                       
                    final CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                for (String groups : listOfGroups) {
                                    cmbGroup.getItems().add(groups);
                                }
                            } finally {
                                latch.countDown();
                            }
                        }
                    });
                    latch.await();
                    //Keep with the background work
                    return null;
                }
            };
        }
    };

}
