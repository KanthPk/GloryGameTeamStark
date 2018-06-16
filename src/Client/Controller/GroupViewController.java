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
import java.util.List;
import java.util.ResourceBundle;
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

    //global variable,begin
    MiddleTier obj = new MiddleTier();
    ConstantElement Const = new ConstantElement();
    //end

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

    //protected List<String> users = new ArrayList<>();
    ArrayList<String> users = new ArrayList<String>();

    public GroupViewController() {

    }

    @FXML
    void btnLeaveClicked(ActionEvent event) {
        try {
            ConstantElement.isPopedUp = false;
            ConstantElement.isDisableBtnPlay = false;
            Stage stage = (Stage) btnLeave.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    @FXML
    void btnProceedClicked(ActionEvent event) {
        try {
            navigationService.OneDropNavigation(event);
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        if (!ConstantElement.GroupName.isEmpty() && ConstantElement.no_of_players != 0) {
            users.add(ConstantElement.GlobalUserName);
            getUsers();
            service.start();
        }
    }

    private void getUsers() {
        try {
            JSONArray array = obj.getGroup();
            int n = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                    JSONObject jo = (JSONObject) array.get(i);
//                String GroupName = (String) jo.get("GroupName");
                    String UserName = (String) jo.get("UserName");
                    if (!UserName.isEmpty()) {
                        users.add(UserName);
                    }
                    //String Players = (String) jo.get("Players");
                    //System.out.println("list of details" + GroupName + UserName + Players);

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
                    //Background work                       
                    final CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                lblGroupName.setText(ConstantElement.GroupName);
                                for (String users : users) {
                                    listGroupMembers.getItems().add(users);
                                }
//                                listGroupMembers.getItems().add(ConstantElement.GlobalUserName);
                                //System.out.println("This is you user name " + ConstantElement.GlobalUserName);
                                //System.out.println("This is async part other side wait for us");
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
