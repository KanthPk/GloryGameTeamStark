/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import glory_schema.ConstantElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

/**
 * FXML Controller class
 *
 * @author Suba
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
            FileOutputStream outputStream
                    = new FileOutputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "GloryGameFiles" + File.separator + "UserSettings.txt");

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
            FileOutputStream outputStream
                    = new FileOutputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "GloryGameFiles" + File.separator + "UserSettings.txt");

            outputStream.write(buffer);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing file '"
                    + "UserSettings" + "'");

        }
    }

    @FXML
    void imgViewTheme1Clicked(MouseEvent event) {
        String bytes = "home1";
        byte[] buffer = bytes.getBytes();

        try {
            FileOutputStream outputStream
                    = new FileOutputStream("UserTheme.txt");

            outputStream.write(buffer);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing file '"
                    + "UserTheme" + "'");
        }
    }

    @FXML
    void imgViewTheme2Clicked(MouseEvent event) {
        String bytes = "home2";
        byte[] buffer = bytes.getBytes();

        try {
            FileOutputStream outputStream
                    = new FileOutputStream("UserTheme.txt");

            outputStream.write(buffer);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing file '"
                    + "UserTheme" + "'");
        }
    }

    @FXML
    void imgViewTheme3Clicked(MouseEvent event) {
        String bytes = "home3";
        byte[] buffer = bytes.getBytes();

        try {
            FileOutputStream outputStream
                    = new FileOutputStream("UserTheme.txt");

            outputStream.write(buffer);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing file '"
                    + "UserTheme" + "'");
        }
    }

}
