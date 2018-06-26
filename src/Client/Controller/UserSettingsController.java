/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class UserSettingsController implements Initializable {

    @FXML
    private Button btnMusicOn;

    @FXML
    private Button btnMusicOff;

    @FXML
    private ImageView imgViewTheme1;

    @FXML
    private ImageView imgViewTheme2;

    @FXML
    private ImageView imgViewTheme3;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea howtoPlaytxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Scanner file = null;
        try {
            file = new Scanner(new File("HowToPlay.txt"));
        } catch (FileNotFoundException ex) {

        }
        String line = "";
        while (file.hasNextLine()) {
            line = line + "\n" + file.nextLine();

        }
        howtoPlaytxt.setText(line);
    }

    @FXML
    void btnCancelClicked(MouseEvent event) {
        try {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    @FXML
    void btnMusicOffClicked(MouseEvent event) {

        ConstantElement.mediaPlayer.stop();
        String bytes = "off";
        byte[] buffer = bytes.getBytes();

        try {
            
            FileOutputStream outputStream = new FileOutputStream( "UserSettings.txt");

            outputStream.write(buffer);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing file '"
                    + "UserSettings" + "'");

        }
    }

    @FXML
    void btnMusicOnClicked(MouseEvent event) throws IOException {
        ConstantElement.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        ConstantElement.mediaPlayer.play();
        String bytes = "on";
        byte[] buffer = bytes.getBytes();

        try {
  
            FileOutputStream outputStream = new FileOutputStream("UserSettings.txt");

            outputStream.write(buffer);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing file '"
                    + "UserSettings" + "'");

        }
    }
}
