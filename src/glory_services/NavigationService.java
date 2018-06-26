/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author TeamStark
 */
public class NavigationService {

    private FXMLLoader fxmlLoader;
    private Parent parentHome;
    private Scene page_scene;
    private Stage app_stage;

    public NavigationService(String path) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(path));
            parentHome = (Parent) fxmlLoader.load();
            page_scene = new Scene(parentHome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void StackNavigation() {

    }

    public void OneDropNavigation(ActionEvent event) {
        try {
            app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(page_scene);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
