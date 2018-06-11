/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import animation.TransitionService;
import glory_schema.Bag;
import glory_schema.ConstantElement;
import glory_services.MessageService;
import glory_services.RoundScoreService;
import glory_services.ValidatorService;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class GameController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnPause;

    @FXML
    private TextField txt_1;

    @FXML
    private TextField txt_2;

    @FXML
    private TextField txt_3;

    @FXML
    private TextField txt_4;

    @FXML
    private TextField txt_5;

    @FXML
    private TextField txt_6;

    @FXML
    private TextField txt_7;

    @FXML
    private TextField txt_8;

    @FXML
    private TextField txtWordFIeld;

    @FXML
    private Button btnCreate;

    @FXML
    public Button btnHome;

    @FXML
    private TextField txtRandom_1;

    @FXML
    private TextField txtRandom_2;

    @FXML
    private TextField txtRandom_3;

    @FXML
    private AnchorPane anchQuestion;

    @FXML
    private RadioButton rBtnVowel;

    @FXML
    private RadioButton rBtnconsonent;

    @FXML
    private ImageView imgBagView;

    @FXML
    private TextField txtScore;

    @FXML
    private Label lblMsgDialog;

    private Bag bag;
    public boolean verifyInputFromBagForVovel;
    public boolean verifyInputFromBagForConsonent;
    final ToggleGroup group = new ToggleGroup();
    private TransitionService transitionService;
    private String[] characters;
    private RoundScoreService roundScoreService;
    private ValidatorService serviceValidater;

    /**
     * Initializes the controller class.
     */
    public GameController() {
        bag = new Bag();
        serviceValidater = new ValidatorService();
        characters = new String[11];
        transitionService = new TransitionService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rBtnVowel.setToggleGroup(group);
        rBtnconsonent.setToggleGroup(group);
        anchQuestion.setVisible(false);
        roundScoreService = new RoundScoreService();

        for (int i = 1; i <= 3; i++) {
            //add to the array latter and validate it
            //0
            //1
            //2
            txtRandom_1.setText(Character.toString(bag.randomGen()));
            txtRandom_2.setText(Character.toString(bag.randomGen()));
            txtRandom_3.setText(Character.toString(bag.randomGen()));
        }

        rBtnconsonent.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                triggerForConsonent(newValue);
            }
        });
        
        rBtnVowel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                triggerForVowel(newValue);
            }
        });
        
        
    }

    @FXML
    void closeApplication(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void imgBagView(MouseEvent event) {
        try {
            FadeTransition transServ;
            transServ = transitionService.MakeFadeIn(anchQuestion);
            transServ.play();
            anchQuestion.setDisable(false);
            anchQuestion.setVisible(true);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void triggerForVowel(boolean liveValue) {
        try {
            if (liveValue) {
                (transitionService.MakeFadeOut(anchQuestion)).play();
                anchQuestion.setDisable(true);
                rBtnVowel.setSelected(true);
                validate("V");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void triggerForConsonent(boolean liveValue) {
        try {
            if (liveValue) {
                (transitionService.MakeFadeOut(anchQuestion)).play();
                anchQuestion.setDisable(true);
                rBtnconsonent.setSelected(true);
                validate("C");
            }
        } catch (Exception e) {
        }
    }

    private void validate(String id) {
        switch (id) {
            case "V":
                if (rBtnVowel.isSelected()) {
                    if (txt_1.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[0] = characterValue;
                        txt_1.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                    } else if (txt_2.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[1] = characterValue;
                        txt_2.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                    } else if (txt_3.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[2] = characterValue;
                        txt_3.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                    } else if (txt_4.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[3] = characterValue;
                        txt_4.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                    } else if (txt_5.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[4] = characterValue;
                        txt_5.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                    } else if (txt_6.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[5] = characterValue;
                        txt_6.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                    } else if (txt_7.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[6] = characterValue;
                        txt_7.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                    } else if (txt_8.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[7] = characterValue;
                        txt_8.setText(characterValue);
                        validateVowelChar(characters, characterValue);
                        rBtnVowel.setSelected(false);
                        imgBagView.setDisable(true);
                    }
                }
                break;
            case "C":
                if (rBtnconsonent.isSelected()) {
                    if (txt_1.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[0] = characterValue;
                        txt_1.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    } else if (!txt_1.getText().isEmpty() && txt_2.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[1] = characterValue;
                        txt_2.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    } else if (!txt_2.getText().isEmpty() && txt_3.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[2] = characterValue;
                        txt_3.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    } else if (!txt_3.getText().isEmpty() && txt_4.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[3] = characterValue;
                        txt_4.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    } else if (!txt_4.getText().isEmpty() && txt_5.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[4] = characterValue;
                        txt_5.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    } else if (!txt_5.getText().isEmpty() && txt_6.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[5] = characterValue;
                        txt_6.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    } else if (!txt_6.getText().isEmpty() && txt_7.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[6] = characterValue;
                        txt_7.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    } else if (!txt_7.getText().isEmpty() && txt_8.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        imgBagView.setDisable(true);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[7] = characterValue;
                        System.out.println("" + Arrays.toString(characters));
                        txt_8.setText(characterValue);
                        validateConsonentChar(characters, characterValue);
                    }
                }
                break;
            default:
                throw new Error("Error");
        }
    }

    private void validateVowelChar(String[] charArray, String perticularChar) {
        String[] arrOfChars = charArray;
        String vowelOfStrings = bag.takeVowelString();
        int index = vowelOfStrings.indexOf(perticularChar);
        int noOfTimes = 0;
        for (int i = 0; i < arrOfChars.length; i++) {
            if (arrOfChars[i] != null && arrOfChars.length > 0) {
                if (Character.toString(vowelOfStrings.charAt(index)).equals(arrOfChars[i])) {
                    noOfTimes += 1;
                    if (noOfTimes == 2) {
                        bag.setNewVowelString(arrOfChars[i]);
                        System.out.println("this item is being removed " + bag.takeFilterString());
                    }
                }
            }
        }
    }

    private void validateConsonentChar(String[] charArray, String perticularChar) {
        String[] arrOfChars = charArray;
        String consonentStrings = bag.takeConsonentString();
        int index = consonentStrings.indexOf(perticularChar);
        int noOfTimes = 0;
        for (int i = 0; i < arrOfChars.length; i++) {
            if (arrOfChars[i] != null && arrOfChars.length > 0) {
                if (Character.toString(consonentStrings.charAt(index)).equals(arrOfChars[i])) {
                    noOfTimes += 1;
                    if (noOfTimes == 2) {
                        bag.setNewConsonent(arrOfChars[i]);
                        System.out.println("" + bag.takeConsonentString());
                    }
                }
            }
        }
    }

    @FXML
    void mousePressedOnCharFields(MouseEvent event) {
        if (txtRandom_1.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txtRandom_1.getText());
        } else if (txtRandom_2.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txtRandom_2.getText());
        } else if (txtRandom_3.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txtRandom_3.getText());
        } else if (!txt_1.getText().isEmpty() && txt_1.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_1.getText());
        } else if (!txt_2.getText().isEmpty() && txt_2.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_2.getText());
        } else if (!txt_3.getText().isEmpty() && txt_3.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_3.getText());
        } else if (!txt_4.getText().isEmpty() && txt_4.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_4.getText());
        } else if (!txt_5.getText().isEmpty() && txt_5.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_5.getText());
        } else if (!txt_6.getText().isEmpty() && txt_6.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_6.getText());
        } else if (!txt_7.getText().isEmpty() && txt_7.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_7.getText());
        } else if (!txt_8.getText().isEmpty() && txt_8.isPressed()) {
            txtWordFIeld.setText(txtWordFIeld.getText() + txt_8.getText());
        }
    }

    @FXML
    void btnCreateClicked(ActionEvent event) {
        try {
            //basties code goes here
            //just return a boolean and validate it

            //my codes goes after basties validation
            //1 Para : round
            //2 para : ture validate worded with proper validation
            int test = roundScoreService.getScoreFromEachRound(1, txtWordFIeld.getText());
            txtScore.setText(Integer.toString(test));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnHomeClicked(ActionEvent event) {
        try {
            //remove the game UI
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    void btnPauseClicked(ActionEvent event) {
//        try {
//            //serviceValidater.getPauseMessage("WARNING", "DO YOU WANT TO PAUSE THE GAME", false, false, true);
//            //            if (btnPause.isPressed()) {
//            //                (transitionService.MakeFadeIn(anchQuestion)).play();
//            //            }
//            FadeTransition obj = transitionService.MakeFadeIn(anchQuestion);
//            obj.play();
//            //lblMsgDialog.setText("DO YOU WANT TO PAUSE THE GAME");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
