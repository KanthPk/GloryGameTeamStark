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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Team Stark
 */
public class GameController implements Initializable {

    //Global Variable,begin
    ConstantElement Const;
    MiddleTier ServerCall = new MiddleTier();
    //Global Variable,end
    
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
    private Button btnHome;

    @FXML
    private TextField txtRandom_1;

    @FXML
    private TextField txtRandom_2;

    @FXML
    private TextField txtRandom_3;

    @FXML
    private AnchorPane anchQuestion;

    @FXML
    private RadioButton radio_vovel;

    @FXML
    private RadioButton radio_consonent;

    @FXML
    private ImageView imgBagView;

    @FXML
    private TextField txt_111;
    @FXML
    private TextField txt_211;
    @FXML
    private TextField txt_311;
    
    
    private Bag bag;
    public boolean verifyInputFromBagForVovel;
    public boolean verifyInputFromBagForConsonent;
    final ToggleGroup group = new ToggleGroup();
    private TransitionService transitionService;

    /**
     * Initializes the controller class.
     */
    
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //value initialization  
        //inject bag object
        bag = new Bag();
        //inject transition service
        transitionService = new TransitionService();
        anchQuestion.setVisible(false);
        radio_vovel.setToggleGroup(group);
        radio_consonent.setToggleGroup(group);

        for (int i = 1; i <= 3; i++) {
            txtRandom_1.setText(Character.toString(bag.randomGen()));
            txtRandom_2.setText(Character.toString(bag.randomGen()));
            txtRandom_3.setText(Character.toString(bag.randomGen()));
        }
        //processSelectBagOperation("V");

        radio_vovel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                fireRadioButtonVowelChange(newValue);
            }
        });

        radio_consonent.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                fireRadioButtonForConsonent(newValue);
            }
        });
        
        //Save the Initial Number into Databse
        ServerCall.DisplayInitialLetter(ConstantElement.GlobalUserName, txtRandom_1.getText(), txtRandom_2.getText(), txtRandom_3.getText());
        System.out.println("                         "+ConstantElement.GlobalUserName);
        
        String[] users1 = null;
        users1 = ServerCall.getLetter();
        int userIndex = 1;
        for (String user : users1) {
            System.out.println(userIndex + ". " + user);
            if(userIndex==1)
            {
            txt_111.setText(user);
            txt_211.setText(user);
            txt_311.setText(user);
            }
            userIndex++;
        }      
    }

    @FXML
    void closeApplication(MouseEvent event) {
        Boolean output;//needed for further implement      
        Platform.exit();
        //serverCallToLogout
        output = ServerCall.Logout(Const.get_userId(), Const.get_password());
        //serverCallToDeleteLetters
        ServerCall.deleteLetter();
        System.exit(0);
    }

    @FXML
    void imgBagView(MouseEvent event) {
        try {
            anchQuestion.setVisible(true);
            FadeTransition transServ = transitionService.MakeFadeIn(anchQuestion);
            transServ.play();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void fireRadioButtonVowelChange(boolean liveValue) {
        try {
            if (liveValue) {
                validatingVowels(1, transitionService.MakeFadeOut(anchQuestion));
                radio_vovel.setSelected(false);
            }
        } catch (Exception e) {
        }
    }

    private void fireRadioButtonForConsonent(boolean liveValue) {
        try {
            if (liveValue) {
                validatingVowels(1, transitionService.MakeFadeOut(anchQuestion));
                radio_consonent.setSelected(false);
            }
        } catch (Exception e) {
        }
    }

    //just send the id and transition for the moment 
    //need to add some more further conditions in later
    private void validatingVowels(int id, FadeTransition transition) throws Exception {
        try {
            switch (id) {
                case 1:
                    if (txt_1.getText().isEmpty()) {
                        txt_1.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_1.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                case 2:
                    if (txt_2.getText().isEmpty()) {
                        txt_2.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_2.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                case 3:
                    if (txt_3.getText().isEmpty()) {
                        txt_3.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_3.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                case 4:
                    if (txt_4.getText().isEmpty()) {
                        txt_4.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_4.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                case 5:
                    if (txt_5.getText().isEmpty()) {
                        txt_5.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_5.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                case 6:
                    if (txt_6.getText().isEmpty()) {
                        txt_6.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_6.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                case 7:
                    if (txt_7.getText().isEmpty()) {
                        txt_7.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_7.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                case 8:
                    if (txt_8.getText().isEmpty()) {
                        txt_8.setText(Character.toString(bag.takeVowelsRandomically()));
                        transition.play();
                    } else if (!txt_8.getText().isEmpty()) {
                        validatingVowels(id + 1, transition);
                    }
                    break;
                default:
                    throw new Exception("Exce");
            }
        } catch (Exception e) {
        }
    }

    private void validatingConsonents(int id, FadeTransition fadeTransition) throws Exception {
        switch (id) {
            case 1:
                if (txt_1.getText().isEmpty()) {
                    txt_1.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_1.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            case 2:
                if (txt_2.getText().isEmpty()) {
                    txt_2.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_2.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            case 3:
                if (txt_3.getText().isEmpty()) {
                    txt_3.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_3.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            case 4:
                if (txt_4.getText().isEmpty()) {
                    txt_4.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_4.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            case 5:
                if (txt_5.getText().isEmpty()) {
                    txt_5.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_5.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            case 6:
                if (txt_6.getText().isEmpty()) {
                    txt_6.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_6.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            case 7:
                if (txt_7.getText().isEmpty()) {
                    txt_7.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_7.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            case 8:
                if (txt_8.getText().isEmpty()) {
                    txt_8.setText(Character.toString(bag.takeConsonentsRandmoically()));
                    fadeTransition.play();
                } else if (!txt_8.getText().isEmpty()) {
                    validatingVowels(id + 1, fadeTransition);
                }
                break;
            default:
                fadeTransition.play();
                throw new Exception("Exce");
        }
    }
    
    public void getObject(ConstantElement val)
    {
        Const = val;
    }
    
}
