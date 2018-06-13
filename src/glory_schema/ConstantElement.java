/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_schema;

//Team Stark
public class ConstantElement {

    private int no_of_players;

    public static final int BlueEssenceForFirstClassEnglishWord = 50;
    public static final int BlueEssenceForSecondClassEnglishWord = 35;
    public static final int BlueEssenceForThirdClassEnglishWord = 20;
    
    //scoring rules
    public static final int MaxEnglishWordLength = 11;
    public static final int StandardWordLength = 8;
    
    //deduct points
    public static final int StandardDeductPoints = 1;
    
    //length is 11
    public static final int ExtraPointsForWord = 8;
    
    //unused letters
    public static final int UnusedLetters = 2;
    public static String GlobalUserName;
    
    public static String RandomeNo;
    public static String UserMail;
    
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
}
