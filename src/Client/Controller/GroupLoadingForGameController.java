/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class GroupLoadingForGameController implements Initializable {

    Task copyWorker;
    public boolean isFinished = false;

    @FXML
    private ProgressBar progressGameLoader;

    @FXML
    private ListView<?> listGroupMembers;

    @FXML
    private AnchorPane ancherGroupLoader;

    public GroupLoadingForGameController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        progressGameLoader.setProgress(0);
        copyWorker = createWorker();

        progressGameLoader.progressProperty().unbind();
        progressGameLoader.progressProperty().bind(copyWorker.progressProperty());

        progressGameLoader.addEventHandler(EventType.ROOT, event -> {
            try {
                //if (isFinished) {
                //copyWorker.cancel(true);                    
                Stage app_stage = (Stage) progressGameLoader.getScene().getWindow();
                app_stage.close();
                app_stage = null;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/Game.fxml"));
                Parent parentHome = (Parent) fxmlLoader.load();
                Scene home_page_scene = new Scene(parentHome);
                app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(home_page_scene);
                app_stage.show();
                // }
            } catch (Exception e) {
            }
        });

        if (!isFinished) {
            pumpDataToServer();
            new Thread(copyWorker).start();
        }
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 1; i <= 10; i++) {
                    if (i == 10) {
                        isFinished = true;
                    } else {
                        Thread.sleep(400);
                        updateMessage("400 milliseconds");
                        updateProgress(i + 1, 10);
                    }
                }
                return true;
            }
        };
    }

    private void pumpDataToServer() {
        System.out.println("I am puping data to server =>>>>");
        //praveens part
    }

}
