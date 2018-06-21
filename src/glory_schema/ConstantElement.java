/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_schema;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

//Team Stark
public class ConstantElement {

    public static boolean isPause;
    public static boolean isSwap = false;
    public static boolean isEditVowel = false;
    public static boolean isEditConstant = false;
    public static boolean isValidToDisableCharFieldsInVowel = false;
    public static boolean isValidToDisableCharFieldsInConst = false;
    public static boolean isDisableBtnPlay = false;
    public static boolean isPopedUp = false;
    public static String GroupName;
    public static int no_of_players;
    public static boolean isJoin;
    public static String[] userArray;
    public static boolean isFinished = false;
    public static boolean prepareToSave = false;

    //for common message boxes
    public static boolean forEmailConfirmation = false;
    public static boolean visiblityForTextField = false;
    public static boolean isNeedBtnOK = false;
    public static boolean isNeedBtnCancel = false;
    public static boolean forCahangePassword = false;
    public static boolean forCahangeUserName = false;
    public static boolean isNeedResendButton = false;

    public static boolean manageTexFields;
    public static final int BlueEssenceForFirstClassEnglishWord = 50;
    public static final int BlueEssenceForSecondClassEnglishWord = 35;
    public static final int BlueEssenceForThirdClassEnglishWord = 20;

    //scoring rules
    public static final int MaxEnglishWordLength = 11;
    public static final int StandardWordLength = 8;

    //deduct points
    public static final int StandardDeductPoints = 1;
   
    //The music palyer
    public static MediaPlayer mediaPlayer;


    //length is 11
    public static final int ExtraPointsForWord = 8;

    //unused letters
    public static final int UnusedLetters = 2;
    public static String GlobalUserName;
    public static String GlobalPassowrd;
    public static String RandomeNo;
    public static String UserMail;
    public static boolean isLive;

    private int Plagetyers;
    private String Timer;
    private boolean IsOnline;
    private boolean IsIngame;
    private boolean IsAway;
    private String ProfilePicPath;
    private String UnserName;
    private String GameName;

    //server configuration
    private String ServerPath;
    private String UserName;
    private String Password;
    //later
     public static int roundId = 1;

    public static String IsFieldClicked;

    public void set_userId(String userId) {
        this.UserName = userId;
    }

    public String get_userId() {
        return this.UserName;
    }

    public void set_password(String pw) {
        this.Password = pw;
    }

    public String get_password() {
        return this.Password;
    }

    public ConstantElement() {

    }

    public ConstantElement(int no_of_players) {
        this.no_of_players = no_of_players;
    }

    public ConstantElement(Object object) {

    }

    public ConstantElement(boolean isIngame, boolean isOnline, boolean isAway) {

    }

    public ConstantElement(String serverPath, String userName, String password) {
        this.ServerPath = serverPath;
        this.UserName = userName;
        this.Password = password;
    }

    public int getNo_of_players() {
        return this.no_of_players;
    }
    
    public static void player()
    {     
   String path = System.getProperty("user.home") + File.separator + "Documents"+File.separator + "GloryGameFiles"+File.separator+"MyLittleAdventure.mp3";
  Media hit = new Media(new File(path).toURI().toString());
    mediaPlayer = new MediaPlayer(hit);
    }
    
}
