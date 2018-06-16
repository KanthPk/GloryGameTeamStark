/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import animation.TransitionService;
import glory_schema.Bag;
import glory_schema.ConstantElement;
import glory_schema.WordElement;
import glory_services.RoundScoreService;
import glory_services.ValidatorService;
import glory_services.WordAutoGenerate;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class GameController implements Initializable {

    ConstantElement Const;
    MiddleTier ServerCall = new MiddleTier();
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
    @FXML
    private CheckBox checkBoxForPuaseSwap;

    @FXML
    private AnchorPane anchorEditBack;

    @FXML
    private TextField txt_111;
    @FXML
    private TextField txt_211;
    @FXML
    private TextField txt_311;

    @FXML
    private AnchorPane subCheckBoxAncher;

    @FXML
    private CheckBox chkEdit;
    @FXML
    private ImageView imgSearch;

    @FXML
    private AnchorPane ancherPause;

    @FXML
    private Label lblTimer;

    StringBuffer globalSubChars;
    private Bag bag;
    public boolean verifyInputFromBagForVovel;
    public boolean verifyInputFromBagForConsonent;
    final ToggleGroup group = new ToggleGroup();
    private TransitionService transitionService;
    private String[] characters;
    private RoundScoreService roundScoreService;
    private ValidatorService serviceValidater;
    private int minute;
    private int hour;
    private int second;

    /**
     * Initializes the controller class.
     */
    public GameController() {
        bag = new Bag();
        serviceValidater = new ValidatorService();
        characters = new String[11];
        transitionService = new TransitionService();
        globalSubChars = new StringBuffer();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rBtnVowel.setToggleGroup(group);
        rBtnconsonent.setToggleGroup(group);
        anchQuestion.setVisible(false);
        roundScoreService = new RoundScoreService();

        //praveen
        //Save the Initial Number into Databse
        ServerCall.DisplayInitialLetter(ConstantElement.GlobalUserName, txtRandom_1.getText(), txtRandom_2.getText(), txtRandom_3.getText());
        System.out.println("                         " + ConstantElement.GlobalUserName);

        String[] users1 = null;
        users1 = ServerCall.getLetter();
        int userIndex = 1;
        for (String user : users1) {
            System.out.println(userIndex + ". " + user);
            if (userIndex == 1) {
                txt_111.setText(user);
                txt_211.setText(user);
                txt_311.setText(user);
            }
            userIndex++;
        }

        liveTime();

        characters[0] = Character.toString(bag.randomGen());
        characters[1] = Character.toString(bag.randomGen());
        characters[2] = Character.toString(bag.randomGen());

        globalSubChars.append(characters[0]);
        globalSubChars.append(characters[1]);
        globalSubChars.append(characters[2]);

        txtRandom_1.setText(characters[0]);
        txtRandom_2.setText(characters[1]);
        txtRandom_3.setText(characters[2]);

        rBtnconsonent.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            triggerForConsonent(newValue);
        });
        rBtnVowel.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            triggerForVowel(newValue);
        });
        btnCreate.disableProperty().bind(txtWordFIeld.textProperty().isEmpty());

        txtWordFIeld.setTextFormatter(new TextFormatter<>((TextFormatter.Change t) -> {
            t.setText(t.getText().replaceAll(".*[^a-zA-Z].*", "").toUpperCase());
            return t;
        }));

        if (txt_1.getText().isEmpty() && txt_2.getText().isEmpty() && txt_3.getText().isEmpty() && txt_4.getText().isEmpty() && txt_5.getText().isEmpty() && txt_6.getText().isEmpty() && txt_7.getText().isEmpty() && txt_8.getText().isEmpty()) {
            txtWordFIeld.setDisable(true);
        }

        txtWordFIeld.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                enableAllFields();
            } else if (!newValue.isEmpty()) {
                if (newValue.length() == 11) {
                    globalSubChars.append(newValue);
                    System.out.println("" + globalSubChars);
                    txtWordFIeld.setEditable(false);
                    transitionService.MakeFadeIn(anchorEditBack).play();
                    chkEdit.setSelected(false);
                    anchorEditBack.setVisible(true);
                }
            }
        });

        txtWordFIeld.setOnKeyPressed(e -> {
            try {
                char pressed = e.getText().toUpperCase().charAt(0);
                if (txtRandom_1.getText().contains(Character.toString(pressed))
                        || txtRandom_2.getText().contains(Character.toString(pressed))
                        || txtRandom_3.getText().contains(Character.toString(pressed))
                        || txt_1.getText().contains(Character.toString(pressed))
                        || txt_2.getText().contains(Character.toString(pressed))
                        || txt_3.getText().contains(Character.toString(pressed))
                        || txt_4.getText().contains(Character.toString(pressed))
                        || txt_5.getText().contains(Character.toString(pressed))
                        || txt_6.getText().contains(Character.toString(pressed))
                        || txt_7.getText().contains(Character.toString(pressed))
                        || txt_8.getText().contains(Character.toString(pressed))) {
                    globalSubChars.append(txtWordFIeld.getText());
                    btnCreate.setStyle("-fx-border-color: BLACK;");
                    txtWordFIeld.setStyle("-fx-border-color: BLACK;");
                    if (Character.toString(pressed).equals(txtRandom_1.getText()) && !txtRandom_1.isDisable()) {
                        System.out.println("you have" + pressed);
                        txtRandom_1.setDisable(true);
                    } else if (Character.toString(pressed).equals(txtRandom_2.getText()) && !txtRandom_2.isDisable()) {
                        System.out.println("you have" + pressed);
                        txtRandom_2.setDisable(true);
                    } else if (Character.toString(pressed).equals(txtRandom_3.getText()) && !txtRandom_3.isDisable()) {
                        System.out.println("you have" + pressed);
                        txtRandom_3.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_1.getText()) && !txt_1.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_1.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_2.getText()) && !txt_2.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_2.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_3.getText()) && !txt_3.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_3.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_4.getText()) && !txt_4.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_4.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_5.getText()) && !txt_5.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_5.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_6.getText()) && !txt_6.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_6.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_7.getText()) && !txt_7.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_7.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_8.getText()) && !txt_8.isDisable()) {
                        System.out.println("you have" + pressed);
                        txt_8.setDisable(true);
                    }
                } else if (!txtRandom_1.getText().contains(Character.toString(pressed))
                        || !txtRandom_2.getText().contains(Character.toString(pressed))
                        || !txtRandom_3.getText().contains(Character.toString(pressed))
                        || !txt_1.getText().contains(Character.toString(pressed))
                        || !txt_2.getText().contains(Character.toString(pressed))
                        || !txt_3.getText().contains(Character.toString(pressed))
                        || !txt_4.getText().contains(Character.toString(pressed))
                        || !txt_5.getText().contains(Character.toString(pressed))
                        || !txt_6.getText().contains(Character.toString(pressed))
                        || !txt_7.getText().contains(Character.toString(pressed))
                        || !txt_8.getText().contains(Character.toString(pressed))) {
                    txtWordFIeld.setStyle("-fx-border-color: RED;");
                    btnCreate.setStyle("-fx-border-color: RED;");
                }
            } catch (Exception ex) {
                txtWordFIeld.setStyle("-fx-border-color: RED;");
            }
        });

        chkEdit.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (txtWordFIeld.getText().length() == 11 && chkEdit.isSelected()) {
                txtWordFIeld.setEditable(true);
                transitionService.MakeFadeOut(anchorEditBack).play();
                anchorEditBack.setVisible(false);
            }
        });

        btnPause.setOnAction(event -> {
            transitionService.MakeFadeIn(subCheckBoxAncher).play();
            subCheckBoxAncher.setVisible(true);
            ConstantElement.isPause = true;
            checkBoxForPuaseSwap.setVisible(true);
            checkBoxForPuaseSwap.setDisable(false);
            transitionService.MakeFadeIn(ancherPause).play();
            ancherPause.setVisible(true);
            setDynamicCheckBox("pause");
            //praveens part 
            //shoould return all data to server
            //and stop the game
        });

        checkBoxForPuaseSwap.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                if (!ConstantElement.isPause) {
                    ConstantElement.isSwap = true;
                    txtRandom_1.setVisible(false);
                    txtRandom_2.setVisible(false);
                    txtRandom_3.setVisible(false);
                    txtWordFIeld.setVisible(false);
                    btnCreate.setVisible(false);
                }
            } else if (!newValue) {
                if (ConstantElement.isPause) {
                    ConstantElement.isSwap = false;
                    txtRandom_1.setVisible(true);
                    txtRandom_2.setVisible(true);
                    txtRandom_3.setVisible(true);
                    txtWordFIeld.setVisible(true);
                    btnCreate.setVisible(true);
                }
            }
        });
    }

    private void setDynamicCheckBox(String id) {
        switch (id) {
            case "pause":
                checkBoxForPuaseSwap.setText("DO YOU WANT TOT PAUSE THE GAME ?");
                break;
            case "swap":
                checkBoxForPuaseSwap.setText("DO YOU WANT TO SWAP THE CHARACTERS ?");
                break;
        }
    }

    @FXML
    void closeApplication(MouseEvent event) {
        Boolean output;//needed for further implement      
        Platform.exit();
        //serverCallToLogout
        output = ServerCall.Logout(ConstantElement.GlobalUserName, ConstantElement.GlobalPassowrd);
        //serverCallToDeleteLetters
        ServerCall.deleteLetter();
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
                validateVowelChar(characters, null, "MultipleChar");
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
                if (rBtnVowel.isSelected() && !bag.takeVowelString().isEmpty()) {
                    if (txt_1.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[3] = characterValue;
                        txt_1.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_2.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[4] = characterValue;
                        txt_2.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_3.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[5] = characterValue;
                        txt_3.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_4.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[6] = characterValue;
                        txt_4.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_5.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[7] = characterValue;
                        txt_5.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_6.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[8] = characterValue;
                        txt_6.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_7.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[9] = characterValue;
                        txt_7.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_8.getText().isEmpty()) {
                        String characterValue = Character.toString(bag.takeVowelsRandomically());
                        characters[10] = characterValue;
                        txt_8.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                        imgBagView.setDisable(true);
                        txtWordFIeld.setDisable(false);
                        transitionService.MakeFadeIn(subCheckBoxAncher).play();
                        setDynamicCheckBox("swap");
                        subCheckBoxAncher.setVisible(true);
                    }
                } else {
                    rBtnVowel.setSelected(false);
                    rBtnVowel.setDisable(true);
                }
                break;
            case "C":
                if (rBtnconsonent.isSelected() && !bag.takeConsonentString().isEmpty()) {
                    if (txt_1.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[3] = characterValue;
                        txt_1.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_1.getText().isEmpty() && txt_2.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[4] = characterValue;
                        txt_2.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_2.getText().isEmpty() && txt_3.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[5] = characterValue;
                        txt_3.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_3.getText().isEmpty() && txt_4.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[6] = characterValue;
                        txt_4.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_4.getText().isEmpty() && txt_5.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[7] = characterValue;
                        txt_5.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_5.getText().isEmpty() && txt_6.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[8] = characterValue;
                        txt_6.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_6.getText().isEmpty() && txt_7.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[9] = characterValue;
                        txt_7.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_7.getText().isEmpty() && txt_8.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        imgBagView.setDisable(true);
                        txtWordFIeld.setDisable(false);
                        String characterValue = Character.toString(bag.takeConsonentsDinamiically());
                        characters[10] = characterValue;
                        System.out.println("" + Arrays.toString(characters));
                        txt_8.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                        transitionService.MakeFadeIn(subCheckBoxAncher).play();
                        setDynamicCheckBox("swap");
                        subCheckBoxAncher.setVisible(true);

                    }
                } else {
                    rBtnconsonent.setDisable(true);
                }
                break;
            default:
                throw new Error("Error");
        }
    }

    private void validateVowelChar(String[] charArray, String perticularChar, String id) {
        String[] arrOfChars = charArray;
        String vowelOfStrings = bag.takeVowelString();
        int index;
        int noOfTimes;
        switch (id) {
            case "SingleChar":
                index = 0;
                noOfTimes = 0;
                index = vowelOfStrings.indexOf(perticularChar);
                for (int i = 0; i < arrOfChars.length; i++) {
                    if (arrOfChars[i] != null && arrOfChars.length > 0) {
                        if (Character.toString(vowelOfStrings.charAt(index)).equals(arrOfChars[i])) {
                            noOfTimes += 1;
                            if (noOfTimes == 2) {
                                if (bag.takeFilterString().isEmpty()) {
                                    rBtnVowel.setDisable(true);
                                } else {
                                    bag.setNewVowelString(arrOfChars[i]);
                                    System.out.println("this item is being removed " + bag.takeFilterString());
                                }
                            }
                        }
                    }
                }
                break;
            case "MultipleChar":
                index = 0;
                noOfTimes = 0;
                for (int i = 0; i < arrOfChars.length; i++) {
                    if (arrOfChars[i] != null && arrOfChars.length > 0) {
                        if (vowelOfStrings.contains(arrOfChars[i])) {
                            index = vowelOfStrings.indexOf(arrOfChars[i]);
                            if (Character.toString(vowelOfStrings.charAt(index)).equals(arrOfChars[i])) {
                                noOfTimes += 1;
                                if (noOfTimes == 2) {
                                    if (bag.takeFilterString().isEmpty()) {
                                        rBtnVowel.setDisable(true);
                                    } else {
                                        bag.setNewVowelString(arrOfChars[i]);
                                        System.out.println("this item is being removed " + bag.takeFilterString());
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    private void validateConsonentChar(String[] charArray, String perticularChar, String id) {
        String[] arrOfChars = charArray;
        String consonentStrings = bag.takeConsonentString();
        int index;
        int noOfTimes;

        switch (id) {
            case "singleChar":
                index = 0;
                noOfTimes = 0;
                index = consonentStrings.indexOf(perticularChar);
                for (int i = 0; i < arrOfChars.length; i++) {
                    if (arrOfChars[i] != null && arrOfChars.length > 0) {
                        if (Character.toString(consonentStrings.charAt(index)).equals(arrOfChars[i])) {
                            noOfTimes += 1;
                            if (noOfTimes == 2) {
                                if (bag.takeConsonentString().isEmpty()) {
                                    rBtnconsonent.setDisable(false);
                                } else {
                                    bag.setNewConsonent(arrOfChars[i]);
                                    System.out.println("" + bag.takeConsonentString());
                                }
                            }
                        }
                    }
                }
                break;
            case "multipleChar":
                index = 0;
                noOfTimes = 0;
                for (int i = 0; i < arrOfChars.length; i++) {
                    if (arrOfChars[i] != null && arrOfChars.length > 0) {
                        if (consonentStrings.contains(arrOfChars[i])) {
                            index = consonentStrings.indexOf(arrOfChars[i]);
                            if (Character.toString(consonentStrings.charAt(index)).equals(arrOfChars[i])) {
                                noOfTimes += 1;
                                if (noOfTimes == 2) {
                                    if (bag.takeConsonentString().isEmpty()) {
                                        rBtnconsonent.setDisable(false);
                                    } else {
                                        bag.setNewConsonent(arrOfChars[i]);
                                        System.out.println("" + bag.takeConsonentString());
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    @FXML
    void mousePressedOnCharFields(MouseEvent event) {
        txtWordFIeld.setStyle("-fx-border-color: BLACK;");
        if (txtRandom_1.isPressed()) {
            txtRandom_1.setDisable(true);
            txtWordFIeld.setText(txtWordFIeld.getText() + txtRandom_1.getText());
        } else if (txtRandom_2.isPressed()) {
            txtRandom_2.setDisable(true);
            txtWordFIeld.setText(txtWordFIeld.getText() + txtRandom_2.getText());
        } else if (txtRandom_3.isPressed()) {
            txtRandom_3.setDisable(true);
            txtWordFIeld.setText(txtWordFIeld.getText() + txtRandom_3.getText());
        } else if (!txt_1.getText().isEmpty() && txt_1.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_1.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_1.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_1.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_1.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_1.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_1.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_1.getText());
                }
            }
        } else if (!txt_2.getText().isEmpty() && txt_2.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_2.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_2.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_2.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_2.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_2.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_2.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_2.getText());
                }
            }
        } else if (!txt_3.getText().isEmpty() && txt_3.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_3.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_3.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_3.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_3.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_3.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_3.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_3.getText());
                }
            }
        } else if (!txt_4.getText().isEmpty() && txt_4.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_4.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_4.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_4.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_4.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_4.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_4.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_4.getText());
                }
            }
        } else if (!txt_5.getText().isEmpty() && txt_5.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_5.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_5.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_5.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_5.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_5.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_5.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_5.getText());
                }
            }
        } else if (!txt_6.getText().isEmpty() && txt_6.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_6.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_6.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_6.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_6.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_6.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_6.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_6.getText());
                }
            }
        } else if (!txt_7.getText().isEmpty() && txt_7.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_7.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_7.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_7.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_7.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_7.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_7.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_7.getText());
                }
            }
        } else if (!txt_8.getText().isEmpty() && txt_8.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_8.getText();
                if (bag.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_8.setText(null);
                    bag.setNewVowelString(keyEle);
                    txt_8.setText(Character.toString(bag.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (bag.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_8.setText(null);
                    bag.setNewConsonent(keyEle);
                    txt_8.setText(Character.toString(bag.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                } else if ((ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_8.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_8.getText());
                }
            }
        }
    }

    private void commonBehaviour(String id) {
        try {
            switch (id) {
                case "Vowel":
                    ConstantElement.isEditVowel = true;
                    txtRandom_1.setVisible(true);
                    txtRandom_2.setVisible(true);
                    txtRandom_3.setVisible(true);
                    txtWordFIeld.setVisible(true);
                    btnCreate.setVisible(true);
                    ConstantElement.isValidToDisableCharFieldsInVowel = true;
                    checkBoxForPuaseSwap.setSelected(false);
                    break;
                case "Constant":
                    ConstantElement.isEditConstant = true;
                    txtRandom_1.setVisible(true);
                    txtRandom_2.setVisible(true);
                    txtRandom_3.setVisible(true);
                    txtWordFIeld.setVisible(true);
                    btnCreate.setVisible(true);
                    checkBoxForPuaseSwap.setSelected(false);
                    ConstantElement.isValidToDisableCharFieldsInConst = true;
                    break;
                case "avoidConstAndVowel":
                    if (ConstantElement.isEditVowel && ConstantElement.isEditConstant) {
                        checkBoxForPuaseSwap.setDisable(true);
                        checkBoxForPuaseSwap.setVisible(false);
                        transitionService.MakeFadeOut(anchorEditBack).play();
                    } else if (!ConstantElement.isEditVowel && !ConstantElement.isEditConstant) {
                        checkBoxForPuaseSwap.setVisible(true);
                        checkBoxForPuaseSwap.setDisable(false);
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    @FXML
    void btnCreateClicked(ActionEvent event) {
        try {
            //String[] myStringArray = new String[3];
            String[] myStringArray = {"a", "p", "c", "P", "l", "l", "e"};
            //String[] myStringArray = new String[]{"a","b","c"};
            //basties code goes here
            //just return a boolean and validate it

            //my codes goes after basties validation
            //1 Para : round
            //2 para : ture validate worded with proper validation
            WordElement we = new WordElement();
            boolean result = we.validateWord(txtWordFIeld.getText());

            //autogenerate,begin
            String[] ary = getLetterArray();
            for (int q = 0; q < 11; q++) {
                System.out.println(ary[q]);

            }
            WordAutoGenerate v = new WordAutoGenerate(ary);
            v.Autogenerator();
            System.out.println("sss" + v.getLongestWord());
            //end
            if (result == true) {
                System.out.println("this is a word");
                int test = roundScoreService.getScoreFromEachRound(1, txtWordFIeld.getText());
                txtScore.setText(Integer.toString(test));
                System.out.println("sss" + v.getLongestWord());

            } else {
                System.out.println("this is not a word");
                serviceValidater.validateConditionErrors("INVALID", "Invalid Longest English Word", false, false, true, false);
            }

            //int test = roundScoreService.getScoreFromEachRound(1, txtWordFIeld.getText());
            //txtScore.setText(Integer.toString(test));
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

    private void enableAllFields() {
        txtRandom_1.setDisable(false);
        txtRandom_2.setDisable(false);
        txtRandom_3.setDisable(false);
        txt_1.setDisable(false);
        txt_2.setDisable(false);
        txt_3.setDisable(false);
        txt_4.setDisable(false);
        txt_5.setDisable(false);
        txt_6.setDisable(false);
        txt_7.setDisable(false);
        txt_8.setDisable(false);
    }

    public void getObject(ConstantElement val) {
        Const = val;
    }

    private void liveTime() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            lblTimer.setText(hour + ":" + (minute) + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public String[] getLetterArray() {
        String[] ary = new String[]{txt_1.getText().toLowerCase(), txt_2.getText().toLowerCase(), txt_3.getText().toLowerCase(), txt_4.getText().toLowerCase(), txt_5.getText().toLowerCase(), txt_6.getText().toLowerCase(), txt_7.getText().toLowerCase(), txt_8.getText().toLowerCase(), txtRandom_1.getText().toLowerCase(), txtRandom_2.getText().toLowerCase(), txtRandom_3.getText().toLowerCase()};
        return ary;
    }
}
