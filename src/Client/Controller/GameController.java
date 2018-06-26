package Client.Controller;

import Server.Controller.MiddleTier;
import Threads.GamePause;
import animation.TransitionService;
import glory_schema.ConstantElement;
import glory_schema.FunctionElement;
import glory_schema.GloryAward;
import glory_schema.LetterValueElement;
import glory_schema.PenaltyElement;
import glory_schema.WordElement;
import glory_services.RoundScoreService;
import glory_services.ValidatorService;
import glory_services.WordAutoGenerate;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class GameController implements Initializable {

    //GlobalVariable for Awards
    public int NoOfDiamonds;
    public int xpPoints;
    ConstantElement Const;
    GloryAward gameAwards = new GloryAward();
    MiddleTier ServerCall = new MiddleTier();
    FunctionElement scoreObj;
    public int clicks = 0;
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
    private TextField user_1_txt_1;

    @FXML
    private TextField user_1_txt_2;

    @FXML
    private TextField user_1_txt_3;

    @FXML
    private TextField user_2_txt_1;

    @FXML
    private TextField user_2_txt_2;

    @FXML
    private TextField user_2_txt_3;

    @FXML
    private TextField user_3_txt_1;

    @FXML
    private TextField user_3_txt_2;

    @FXML
    private TextField user_3_txt_3;

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
    private AnchorPane subCheckBoxAncher;

    @FXML
    private CheckBox chkEdit;
    @FXML
    private ImageView imgSearch;

    @FXML
    private AnchorPane ancherPause;

    @FXML
    private Label lblTimer;

    @FXML
    private Label lbl_live_user_1;

    @FXML
    private Label lbl_live_user_2;

    @FXML
    private Label lbl_live_user_3;

    @FXML
    private Label lbl_Gllobal_User;

    @FXML
    private Label user_1;

    @FXML
    private Label user_2;

    @FXML
    private Label user_3;

    @FXML
    private Label user_4;

    @FXML
    private Label user_1_score;

    @FXML
    private Label user_2_score;

    @FXML
    private Label user_3_score;

    @FXML
    private Label user_4_score;

    @FXML
    private Button btnNextRound;

    @FXML
    private AnchorPane anchorScore;

    @FXML
    private Label roundid;

    @FXML
    private Label user_1_global;

    @FXML
    private Label user_2_global;

    @FXML
    private Label user_3_global;

    @FXML
    private ImageView imgPause;

    @FXML
    private Label lbl_diamond;

    @FXML
    private Label lbl_user_online_1;

    @FXML
    private Label lbl_total_score;
    @FXML
    private Label user1_online;
    @FXML
    private Label user2_online;
    @FXML
    private Label user3_online;

    @FXML
    private AnchorPane anchorAutoSearch;

    public int roundVal = 1;
    protected String pause = "Stop";
    StringBuffer globalSubChars;
    private LetterValueElement letterElement;
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
    ArrayList<String> users;
    Instant startTime;
    java.time.Duration pausedAfter;
    Timeline clock = null;
    Task progressThread;
    Timeline timeline;
    private String fireScoreScreen;
    GamePause gamepause = new GamePause();
    Thread thread_pause = new Thread(gamepause);
    Task buttonPause;
    long elapsed;
    long ms;
    long s;
    long m;
    long h;
    private String[] randomLetter;
    //store autoBuild usage
    private boolean isAutoBuildUsed = false;

    /**
     * Initializes the controller class.
     */
    public GameController() {
        letterElement = new LetterValueElement();
        serviceValidater = new ValidatorService();
        scoreObj = new FunctionElement();
        characters = new String[11];
        randomLetter = new String[3];
        transitionService = new TransitionService();
        globalSubChars = new StringBuffer();
        ServerCall.setGlobalScore(ConstantElement.GlobalUserName, ConstantElement.GroupName, Integer.toString(scoreObj.getTotalScore()));
    }

    @FXML
    private void imgSearch() {
        try {
            if (txtWordFIeld.getText().isEmpty()) {
                String[] ary = getLetterArray();
                WordAutoGenerate v = new WordAutoGenerate(ary);
                v.Autogenerator();
                System.out.println("Autogenrate word" + v.getLongestWord());
                isAutoBuildUsed = true;
                txtWordFIeld.setText(v.getLongestWord());
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("***********Round ID**************" + ConstantElement.roundId);
        System.out.println("************Round Value**********************" + roundVal);
        getIntialLetter();
        rBtnVowel.setToggleGroup(group);
        rBtnconsonent.setToggleGroup(group);
        anchQuestion.setVisible(false);
        roundScoreService = new RoundScoreService();

        liveStopWatch();

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

        if (txt_1.getText().isEmpty() && txt_2.getText().isEmpty() && txt_3.getText().isEmpty() && txt_4.getText().isEmpty() && txt_5.getText().isEmpty() && txt_6.getText().isEmpty() && txt_7.getText().isEmpty() && txt_8.getText().isEmpty() || (!txt_1.getText().isEmpty() && (!txt_2.getText().isEmpty()) && (!txt_3.getText().isEmpty()))) {
            txtWordFIeld.setDisable(false);
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
                        txtRandom_1.setDisable(true);
                    } else if (Character.toString(pressed).equals(txtRandom_2.getText()) && !txtRandom_2.isDisable()) {
                        txtRandom_2.setDisable(true);
                    } else if (Character.toString(pressed).equals(txtRandom_3.getText()) && !txtRandom_3.isDisable()) {
                        txtRandom_3.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_1.getText()) && !txt_1.isDisable()) {
                        txt_1.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_2.getText()) && !txt_2.isDisable()) {
                        txt_2.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_3.getText()) && !txt_3.isDisable()) {
                        txt_3.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_4.getText()) && !txt_4.isDisable()) {
                        txt_4.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_5.getText()) && !txt_5.isDisable()) {
                        txt_5.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_6.getText()) && !txt_6.isDisable()) {
                        txt_6.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_7.getText()) && !txt_7.isDisable()) {
                        txt_7.setDisable(true);
                    } else if (Character.toString(pressed).equals(txt_8.getText()) && !txt_8.isDisable()) {
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
            if (txtWordFIeld.getText().length() != 0 && chkEdit.isSelected()) {
                txtWordFIeld.setEditable(true);
                transitionService.MakeFadeOut(anchorEditBack).play();
                anchorEditBack.setVisible(false);
            }
        });

        btnPause.setOnAction(event -> {
            imgBagView.setDisable(true);
            btnHome.setDisable(false);
            ancherPause.setVisible(true);
            ConstantElement.isPause = true;
            pausedAfter = java.time.Duration.between(startTime, Instant.now());
            clock.stop();
            thread_pause.run();
            // service.start();
        });

        checkBoxForPuaseSwap.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                ConstantElement.isSwap = true;
                txtRandom_1.setVisible(false);
                txtRandom_2.setVisible(false);
                txtRandom_3.setVisible(false);
                txtWordFIeld.setVisible(false);
                btnCreate.setVisible(false);
            } else if (!newValue) {
                ConstantElement.isSwap = false;
                txtRandom_1.setVisible(true);
                txtRandom_2.setVisible(true);
                txtRandom_3.setVisible(true);
                txtWordFIeld.setVisible(true);
                btnCreate.setVisible(true);
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
        try {
            Platform.exit();
            ServerCall.Logout(ConstantElement.GlobalUserName, ConstantElement.GroupName);
            commonServerCall();
            System.exit(0);
        } catch (Exception e) {
        }
    }

    private void commonServerCall() {
        ServerCall.leaveGroup(ConstantElement.GroupName, ConstantElement.GlobalUserName);
        ServerCall.deleteLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName);
        ServerCall.deleteRound(ConstantElement.GroupName, ConstantElement.GlobalUserName);
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
                validate("V", liveValue);
            }
        } catch (Exception e) {
        }
    }

    private void triggerForConsonent(boolean liveValue) {
        try {
            if (liveValue) {
                (transitionService.MakeFadeOut(anchQuestion)).play();
                anchQuestion.setDisable(true);
                rBtnconsonent.setSelected(true);
                validate("C", liveValue);
            }
        } catch (Exception e) {
        }
    }

    private void validate(String id, boolean liveValue) {
        switch (id) {
            case "V":
                if (liveValue && !letterElement.takeVowelString().isEmpty()) {
                    if (txt_1.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[3] = characterValue;
                        txt_1.setText(characterValue);
                        validateVowelChar(randomLetter, null, "MultipleChar");
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_2.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[4] = characterValue;
                        txt_2.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_3.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[5] = characterValue;
                        txt_3.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_4.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[6] = characterValue;
                        txt_4.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                        txtWordFIeld.setDisable(false);
                    } else if (txt_5.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[7] = characterValue;
                        txt_5.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_6.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[8] = characterValue;
                        txt_6.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_7.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[9] = characterValue;
                        txt_7.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                    } else if (txt_8.getText().isEmpty()) {
                        String characterValue = Character.toString(letterElement.takeVowelsRandomically());
                        characters[10] = characterValue;
                        System.out.println("" + Arrays.toString(characters));
                        txt_8.setText(characterValue);
                        validateVowelChar(characters, characterValue, "SingleChar");
                        rBtnVowel.setSelected(false);
                        imgBagView.setDisable(true);
                        transitionService.MakeFadeIn(subCheckBoxAncher).play();
                        subCheckBoxAncher.setVisible(true);
                        setDynamicCheckBox("swap");
                    }
                }
                break;
            case "C":
                if (liveValue && !letterElement.takeConsonentString().isEmpty()) {
                    if (txt_1.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[3] = characterValue;
                        txt_1.setText(characterValue);
                        validateConsonentChar(characters, null, "multipleChar");
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_1.getText().isEmpty() && txt_2.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[4] = characterValue;
                        txt_2.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_2.getText().isEmpty() && txt_3.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[5] = characterValue;
                        txt_3.setText(characterValue);
                        txtWordFIeld.setDisable(false);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_3.getText().isEmpty() && txt_4.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[6] = characterValue;
                        txt_4.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_4.getText().isEmpty() && txt_5.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[7] = characterValue;
                        txt_5.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_5.getText().isEmpty() && txt_6.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[8] = characterValue;
                        txt_6.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_6.getText().isEmpty() && txt_7.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[9] = characterValue;
                        txt_7.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                    } else if (!txt_7.getText().isEmpty() && txt_8.getText().isEmpty()) {
                        rBtnconsonent.setSelected(false);
                        imgBagView.setDisable(true);
                        txtWordFIeld.setDisable(false);
                        String characterValue = Character.toString(letterElement.takeConsonentsDinamiically());
                        characters[10] = characterValue;
                        System.out.println("" + Arrays.toString(characters));
                        txt_8.setText(characterValue);
                        validateConsonentChar(characters, characterValue, "singleChar");
                        letterElement.resetVowelConstants();
                        transitionService.MakeFadeIn(subCheckBoxAncher).play();
                        subCheckBoxAncher.setVisible(true);
                        setDynamicCheckBox("swap");
                    }
                }
                break;
            default:
                throw new Error("Error");
        }
    }

    private void validateVowelChar(String[] charArray, String perticularChar, String id) {
        String[] arrOfChars = charArray;
        String vowelOfStrings = letterElement.takeVowelString();
        int index;
        int noOfTimes;
        switch (id) {
            case "SingleChar":
                noOfTimes = 0;
                index = vowelOfStrings.indexOf(perticularChar);
                for (int i = 0; i < arrOfChars.length; i++) {
                    if (arrOfChars[i] != null && arrOfChars.length > 0) {
                        if (Character.toString(vowelOfStrings.charAt(index)).equals(arrOfChars[i])) {
                            noOfTimes += 1;
                            if (noOfTimes == 2) {
                                if (letterElement.takeVowelString().isEmpty()) {
                                    rBtnVowel.setDisable(true);
                                } else if (!letterElement.takeVowelString().isEmpty()) {
                                    letterElement.replaceVowelString(arrOfChars[i]);
                                    System.out.println("" + letterElement.takeVowelString());
                                }
                            }
                        }
                    }
                }
                break;
            case "MultipleChar":
                noOfTimes = 0;
                for (int i = 0; i < arrOfChars.length; i++) {
                    if (arrOfChars[i] != null && arrOfChars.length > 0) {
                        if (vowelOfStrings.contains(arrOfChars[i])) {
                            index = vowelOfStrings.indexOf(arrOfChars[i]);
                            if (Character.toString(vowelOfStrings.charAt(index)).equals(arrOfChars[i])) {
                                noOfTimes += 1;
                                if (noOfTimes == 2) {
                                    if (!letterElement.takeVowelString().isEmpty()) {
                                        letterElement.replaceVowelString(arrOfChars[i]);
                                        System.out.println("" + letterElement.takeVowelString());
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
        String consonentStrings = letterElement.takeConsonentString();
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
                                if (letterElement.takeConsonentString().isEmpty()) {
                                    rBtnconsonent.setDisable(false);
                                } else if (!letterElement.takeConsonentString().isEmpty()) {
                                    letterElement.removeExistsConsonents(arrOfChars[i]);
                                    System.out.println("" + letterElement.takeConsonentString());
                                } else if (letterElement.takeConsonentString().isEmpty()) {
                                    letterElement.resetVowelConstants();
                                }
                            }
                        }
                    }
                }
                break;
            case "multipleChar":
                noOfTimes = 0;
                for (int i = 0; i < arrOfChars.length; i++) {
                    if (arrOfChars[i] != null && arrOfChars.length > 0) {
                        if (consonentStrings.contains(arrOfChars[i])) {
                            index = consonentStrings.indexOf(arrOfChars[i]);
                            if (Character.toString(consonentStrings.charAt(index)).equals(arrOfChars[i])) {
                                noOfTimes += 1;
                                if (noOfTimes == 2) {
                                    if (letterElement.takeConsonentString().isEmpty()) {
                                        rBtnconsonent.setDisable(false);
                                    } else {
                                        letterElement.removeExistsConsonents(arrOfChars[index]);
                                        System.out.println("" + letterElement.takeConsonentString());
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
    private void mousePressedOnCharFields(MouseEvent event) {
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
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_1.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_1.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_1.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_1.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_1.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_1.getText());
                }
            }
        } else if (!txt_2.getText().isEmpty() && txt_2.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_2.getText();
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_2.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_2.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_2.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_2.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_2.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_2.getText());
                }
            }
        } else if (!txt_3.getText().isEmpty() && txt_3.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_3.getText();
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_3.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_3.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_3.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_3.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_3.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_3.getText());
                }
            }
        } else if (!txt_4.getText().isEmpty() && txt_4.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_4.getText();
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_4.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_4.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_4.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_4.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_4.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_4.getText());
                }
            }
        } else if (!txt_5.getText().isEmpty() && txt_5.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_5.getText();
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_5.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_5.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_5.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_5.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_5.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_5.getText());
                }
            }
        } else if (!txt_6.getText().isEmpty() && txt_6.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_6.getText();
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_6.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_6.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_6.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_6.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_6.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_6.getText());
                }
            }
        } else if (!txt_7.getText().isEmpty() && txt_7.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_7.getText();
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_7.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_7.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_7.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_7.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
                    txt_7.setDisable(true);
                    txtWordFIeld.setText(txtWordFIeld.getText() + txt_7.getText());
                }
            }
        } else if (!txt_8.getText().isEmpty() && txt_8.isPressed()) {
            if (ConstantElement.isSwap) {
                String keyEle = txt_8.getText();
                if (letterElement.isVowel(keyEle) && !ConstantElement.isEditVowel) {
                    txt_8.setText(null);
                    letterElement.replaceVowelString(keyEle);
                    txt_8.setText(Character.toString(letterElement.takeVowelsRandomically()));
                    commonBehaviour("Vowel");
                    commonBehaviour("avoidConstAndVowel");
                } else if (letterElement.isConstant(keyEle) && !ConstantElement.isEditConstant) {
                    txt_8.setText(null);
                    letterElement.removeExistsConsonents(keyEle);
                    txt_8.setText(Character.toString(letterElement.takeConsonentsDinamiically()));
                    commonBehaviour("Constant");
                    commonBehaviour("avoidConstAndVowel");
                }
            } else if (!ConstantElement.isSwap) {
                if (!ConstantElement.isSwap || (ConstantElement.isValidToDisableCharFieldsInVowel && ConstantElement.isValidToDisableCharFieldsInConst)) {
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
                        subCheckBoxAncher.setVisible(false);
                        transitionService.MakeFadeOut(anchorEditBack).play();
                    } else if (!ConstantElement.isEditVowel && !ConstantElement.isEditConstant) {
                        subCheckBoxAncher.setVisible(true);
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    @FXML
    void btnCreateClicked(ActionEvent event) {
        try {          
            clicks++;           
            subCheckBoxAncher.setVisible(false);

            String val = txtWordFIeld.getText();
            if (val.length() <= characters.length) {
                ConstantElement.UnusedLetters = 2;
            }

            if (!txtWordFIeld.getText().isEmpty()) {

                WordElement we = new WordElement();
                boolean result = we.validateWord(txtWordFIeld.getText());
                String[] ary = getLetterArray();
                for (int q = 0; q < 11; q++) {
                    System.out.println(ary[q]);
                }

                if (result == true) {
                    System.out.println("this is a word");
                    int RoundScore = roundScoreService.getScoreFromEachRound(1, txtWordFIeld.getText());
                    if (isAutoBuildUsed) {
                        if (NoOfDiamonds > 0) {
                            NoOfDiamonds = NoOfDiamonds - 1;
                            xpPoints = xpPoints - 500;
                            lbl_diamond.setText("" + NoOfDiamonds);
                        } else {

                            int panaltyScore = PenaltyElement.calculateRoundPanalty(RoundScore);
                            RoundScore = panaltyScore;
                        }
                    }                   
                    if (clicks == 1) {
                        txtScore.setText(Integer.toString(RoundScore));
                        ConstantElement.GlobalScore = Integer.parseInt(txtScore.getText()) + ConstantElement.GlobalScore;
                        lbl_total_score.setText(Integer.toString(ConstantElement.GlobalScore));
                    }
                    
                    //Set xpPointsStart   
                    xpPoints = xpPoints + gameAwards.GetxpPoints(elapsed, txtWordFIeld.getText());
                    NoOfDiamonds = gameAwards.GetDiamonds(xpPoints);
                    if (NoOfDiamonds >= 1) {
                        transitionService.MakeFadeOutForAutoGen(anchorAutoSearch);
                        Thread.sleep(30);
                        transitionService.MakeFadeInForAutoGen(anchorAutoSearch);
                    }
                    //Valid ouput for XP points and diamonds  
                    System.out.println("############### : xp Points" + xpPoints);
                    System.out.println("############### : Diamonds:  " + NoOfDiamonds);
                } else {
                    serviceValidater.validateConditionErrors("INVALID", "Invalid Longest English Word", false, false, true, false, false);
                }
            }
        } catch (Exception e) {
        }
    }

    @FXML
    void btnHomeClicked(ActionEvent event) {
        try {
            ConstantElement.isFinished = false;
            ConstantElement.mediaPlayer.stop();
            commonServerCall();
            //navigate
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/Home.fxml"));
                Parent parentHome = (Parent) fxmlLoader.load();
                ConstantElement s = new ConstantElement();
                Scene home_page_scene = new Scene(parentHome);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.centerOnScreen();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            } catch (Exception e) {
                serviceValidater.validateLiveError("CONNECTION FAILED", "Please check your connection", false, false, true, false, "Live", false);
            }
        } catch (RuntimeException e) {
            serviceValidater.validateLiveError("CONNECTION FAILED", "Something wrong with the server, Please try again", false, false, true, false, "Live", false);
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

    private void liveStopWatch() {

        startTime = Instant.now();
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            int countDown = 0;
            elapsed = java.time.Duration.between(startTime, Instant.now()).toMillis();
            ms = elapsed;
            s = ms / 1000;
            m = s / 60;
            h = m / 60;
            ms = ms % 1000;

            s = s % 60;
            m = m % 60;

//          String fireScoreScreen = String.format("%02d:%02d:%02d.%03d", h, m, s, ms);
            fireScoreScreen = String.format("%02d:%02d:%02d", h, m, s);
            lblTimer.setText("" + fireScoreScreen);

            if (ConstantElement.roundId == 1) {
                countDown = 59;
            } else if (ConstantElement.roundId == 2) {
                countDown = 59;
            } else if (ConstantElement.roundId == 3) {
                countDown = 40;
            } else if (ConstantElement.roundId == 4) {
                countDown = 30;
            } else if (ConstantElement.roundId == 5) {
                countDown = 20;
            }
            if (s == countDown) {
                countDown = 0;
                clock.stop();
                setProgressToNextRound();
            }
        }),
                new KeyFrame(Duration.millis(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public String[] getLetterArray() {
        String[] ary = new String[]{txt_1.getText().toLowerCase(), txt_2.getText().toLowerCase(), txt_3.getText().toLowerCase(), txt_4.getText().toLowerCase(), txt_5.getText().toLowerCase(), txt_6.getText().toLowerCase(), txt_7.getText().toLowerCase(), txt_8.getText().toLowerCase(), txtRandom_1.getText().toLowerCase(), txtRandom_2.getText().toLowerCase(), txtRandom_3.getText().toLowerCase()};
        return ary;
    }

    private void setProgressToNextRound() {
        try {
            progressThread = createWorker();
            progressThread.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveScoreOfLivePlayers() {
        try {
            clicks = 0;
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();
            scoreObj.setTotalScore(Integer.parseInt(txtScore.getText()));
            ServerCall.updateGlobalScore(ConstantElement.GroupName, ConstantElement.GlobalUserName, Integer.toString(scoreObj.getTotalScore()));
            ServerCall.setRound(ConstantElement.GroupName, ConstantElement.GlobalUserName, randomUUIDString, txtScore.getText().toString(), Integer.toString(roundVal));
            ServerCall.deleteLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName);
            Thread.sleep(3000);
            setScore();
            roundVal = roundVal + 1;
            lbl_diamond.setText("" + NoOfDiamonds);
            isAutoBuildUsed = false;
            ConstantElement.roundId = roundVal;
            if (ConstantElement.roundId != 6) {
                roundid.setText(Integer.toString(roundVal));
            } else {
                roundVal = 1;
                roundid.setText(" finished");
            }
            clearFields();
            setInitialLetter();
            Thread.sleep(2000);
            getIntialLetter();
            Thread.sleep(2000);
            getTotalScore();
        } catch (Exception e) {
        }
    }

    private Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                anchorScore.setVisible(true);
                saveScoreOfLivePlayers();
                imgBagView.setDisable(false);
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i <= 10; i++) {
                                final int counter = i;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (counter == 10) {
                                            try {
                                                letterElement.resetVowelConstants();
                                                subCheckBoxAncher.setVisible(false);
                                                if (ConstantElement.roundId == 6) {
                                                    ConstantElement.roundId = 1;
                                                    ConstantElement.GlobalScore = 0;
                                                    ConstantElement.mediaPlayer.stop();
                                                    Thread.sleep(10000);
                                                    try {
                                                        Stage stageGame = (Stage) txtRandom_1.getScene().getWindow();
                                                        stageGame.close();
                                                        AnchorPane layout = null;
                                                        Stage stage = null;
                                                        layout = FXMLLoader.load(getClass().getResource("/UI/WinnerScreen.fxml"));
                                                        stage = new Stage();
                                                        stage.setScene(new Scene(layout));
                                                        stage.setResizable(false);
                                                        stage.initStyle(StageStyle.UNDECORATED);
                                                        stage.show();
                                                    } catch (Exception e) {
                                                    }
                                                } else {
                                                    Thread.sleep(4000);
                                                    callBack();
                                                }
                                            } catch (InterruptedException ex) {
                                                serviceValidater.validateLiveError("CONNECTION FAILED", "Please check your connection", false, false, true, false, "Live", false);
                                                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    serviceValidater.validateLiveError("CONNECTION FAILED", "Please check your connection", false, false, true, false, "Live", false);
                }
                return true;
            }
        };
    }

    public void setScore() {

        int count = 0;
        JSONObject userJsonObjects = null;
        ArrayList<String> score = new ArrayList<String>();
        try {
            //clearing need to remove text in game fxml,begin
            user_2.setText("");
            user_3.setText("");
            user_4.setText("");
            user_2_score.setText("");
            user_3_score.setText("");
            user_4_score.setText("");
            //end
            JSONArray array = ServerCall.getRoundScore(ConstantElement.GroupName, ConstantElement.GlobalUserName, Integer.toString(roundVal));
            count = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < count; i++) {
                    userJsonObjects = (JSONObject) array.get(i);
                    String UserName = (String) userJsonObjects.get("UserId");
                    String Scoren = (String) userJsonObjects.get("Score");
                    String Level = (String) userJsonObjects.get("Level");
                    if (UserName.equals(ConstantElement.GlobalUserName)) {
                        user_1.setText(UserName);
                        user_1_score.setText(Scoren);
                    } else {
                        if (!UserName.equals(ConstantElement.GlobalUserName)) {
                            if (user_2.getText().isEmpty() && user_2_score.getText().isEmpty()) {
                                user_2.setText(UserName);
                                user_2_score.setText(Scoren);
                            } else if (user_3.getText().isEmpty() && user_3_score.getText().isEmpty()) {
                                user_3.setText(UserName);
                                user_3_score.setText(Scoren);
                            } else if (user_4.getText().isEmpty() && user_4_score.getText().isEmpty()) {
                                user_4.setText(UserName);
                                user_4_score.setText(Scoren);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void setInitialLetter() {
        try {
            characters[0] = Character.toString(letterElement.randomGen());
            characters[1] = Character.toString(letterElement.randomGen());
            characters[2] = Character.toString(letterElement.randomGen());

            globalSubChars.append(characters[0]);
            globalSubChars.append(characters[1]);
            globalSubChars.append(characters[2]);
            ServerCall.setInitialLetter(ConstantElement.GroupName, ConstantElement.GlobalUserName,
                    characters[0], characters[1],
                    characters[2]);
        } catch (Exception ex) {
        }
    }

    public void getIntialLetter() {
        try {
            roundid.setText(Integer.toString(roundVal));
            users = new ArrayList<String>();
            JSONArray array = ServerCall.getLetter(ConstantElement.GroupName);
            int n = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    JSONObject userJsonObjects = (JSONObject) array.get(i);
                    String user = (String) userJsonObjects.get("UserId");
                    String Letter1 = (String) userJsonObjects.get("Letter1");
                    String Letter2 = (String) userJsonObjects.get("Letter2");
                    String Letter3 = (String) userJsonObjects.get("Letter3");

                    if (user.equals(ConstantElement.GlobalUserName)) {
                        lbl_Gllobal_User.setText(ConstantElement.GlobalUserName);

                        characters[0] = Letter1;
                        characters[1] = Letter2;
                        characters[2] = Letter3;

                        randomLetter[0] = Letter1;
                        randomLetter[1] = Letter2;
                        randomLetter[2] = Letter3;

                        globalSubChars.append(characters[0]);
                        globalSubChars.append(characters[1]);
                        globalSubChars.append(characters[2]);

                        txtRandom_1.setText(characters[0]);
                        txtRandom_2.setText(characters[1]);
                        txtRandom_3.setText(characters[2]);

                    } else {
                        if (!user.equals(ConstantElement.GlobalUserName)) {
                            if (user_1_txt_1.getText().isEmpty() && user_1_txt_2.getText().isEmpty() && user_1_txt_3.getText().isEmpty()) {
                                lbl_live_user_1.setText(user);
                                user1_online.setText("Online");
                                user_1_txt_1.setText(Letter1);
                                user_1_txt_2.setText(Letter2);
                                user_1_txt_3.setText(Letter3);
                            } else if (user_2_txt_1.getText().isEmpty() && user_2_txt_2.getText().isEmpty() && user_2_txt_3.getText().isEmpty()) {
                                lbl_live_user_2.setText(user);
                                user2_online.setText("Online");
                                user_2_txt_1.setText(Letter1);
                                user_2_txt_2.setText(Letter2);
                                user_2_txt_3.setText(Letter3);
                            } else if (user_3_txt_1.getText().isEmpty() && user_3_txt_2.getText().isEmpty() && user_3_txt_3.getText().isEmpty()) {
                                lbl_live_user_3.setText(user);
                                user3_online.setText("Online");
                                user_3_txt_1.setText(Letter1);
                                user_3_txt_2.setText(Letter2);
                                user_3_txt_3.setText(Letter3);
                            }
                        }
                    }
                }
            }
        } catch (Exception s) {
        }
    }

    public void clearFields() {
        lbl_Gllobal_User.setText("");
        lbl_live_user_1.setText("");
        lbl_live_user_2.setText("");
        lbl_live_user_3.setText("");
        user_1_global.setText("");
        user_2_global.setText("");
        user_3_global.setText("");
        txt_1.setText("");
        txt_2.setText("");
        txt_3.setText("");
        txt_4.setText("");
        txt_5.setText("");
        txt_6.setText("");
        txt_7.setText("");
        txt_8.setText("");
        user_1_txt_2.setText("");
        user_1_txt_1.setText("");
        user_1_txt_3.setText("");
        user_2_txt_2.setText("");
        user_2_txt_1.setText("");
        user_2_txt_3.setText("");
        user_3_txt_1.setText("");
        user_3_txt_2.setText("");
        user_3_txt_3.setText("");
        txtWordFIeld.setText("");
        txtScore.setText("0");
    }

    public void getTotalScore() {
        try {
            roundid.setText(Integer.toString(roundVal));
            users = new ArrayList<String>();
            JSONArray array = ServerCall.getTotalScore();
            int n = array.size();
            if (!array.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    JSONObject userJsonObjects = (JSONObject) array.get(i);
                    String user = (String) userJsonObjects.get("User");
                    String Score = (String) userJsonObjects.get("Score");
                    if (user.equals(ConstantElement.GlobalUserName)) {
                    } else {
                        if (!user.equals(ConstantElement.GlobalUserName)) {
                            if (user_1_global.getText().isEmpty() && user.equals(lbl_live_user_1.getText())) {
                                user_1_global.setText(Score);
                                if (Score.isEmpty()) {
                                    user1_online.setText("Offline");
                                }
                            } else if (user_2_global.getText().isEmpty() && user.equals(lbl_live_user_2.getText())) {
                                user_2_global.setText(Score);
                                if (Score.isEmpty()) {
                                    user2_online.setText("Offline");
                                }
                            } else if (user_3_global.getText().isEmpty() && user.equals(lbl_live_user_3.getText())) {
                                user_3_global.setText(Score);
                                if (Score.isEmpty()) {
                                    user2_online.setText("Offline");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception s) {
        }
    }

    private void callBack() {
        ConstantElement.isEditVowel = false;
        ConstantElement.isEditConstant = false;
        ConstantElement.isValidToDisableCharFieldsInVowel = false;
        ConstantElement.isValidToDisableCharFieldsInConst = false;
        ConstantElement.isSwap = false;
        anchorScore.setVisible(false);
        clock.stop();
        lblTimer.setText("00:00:00");
        liveStopWatch();
    }

    @FXML
    private void imgPausePressed(MouseEvent event) {
        try {
            ancherPause.setVisible(false);
            thread_pause.stop();
            imgBagView.setDisable(false);
            btnHome.setDisable(true);
            ConstantElement.isLive = false;
            ServerCall.pauseGame("Start");
            startTime = Instant.now().minus(pausedAfter);
            clock.play();
            timeline.play();
            buttonPause.cancel();
            ancherPause.setVisible(false);
        } catch (Exception e) {
        }
    }

    private Task ButtonPauseLiveGame() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    btnHome.setDisable(false);
                                    timeline = new Timeline(new KeyFrame(Duration.minutes(1), ev -> {
                                        ConstantElement.pause = ServerCall.getpauseGame();
                                        if (ConstantElement.pause.equals(pause)) {
                                            timeline.stop();
                                        }
                                    }));
                                    timeline.setCycleCount(Animation.INDEFINITE);
                                    timeline.play();
                                } catch (Exception e) {
                                }
                            }
                        });
                    }
                }).start();
                return true;
            }
        };
    }
}
