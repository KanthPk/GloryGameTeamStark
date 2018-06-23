/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Controller;

import animation.TransitionService;
import glory_schema.ConstantElement;
import glory_services.ValidatorService;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author TeamStark
 */
public class MiddleTier {

    //Variables,begin
    TransitionService service;
    private ValidatorService serviceValidater;
    //Variables,end

    public MiddleTier() {
        service = new TransitionService();
        serviceValidater = new ValidatorService();
    }

    public void Logout(String user_id, String group) {
        try {
            URL url = new URL("https://kanthpk.000webhostapp.com/logout.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&username=" + user_id);
                ps.print("&group=" + group);
                con.getInputStream();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        //return true;
    }

    public JSONArray onlineUsers() {
        String[] scores = null;
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/sample.php");
            URLConnection yc = oracle.openConnection();
            yc.setDoOutput(true);
            try (PrintStream ps = new PrintStream(yc.getOutputStream())) {
                ps.print("&=" + ConstantElement.GroupName);
                yc.getInputStream();
            }
            try (DataInputStream inStream = new DataInputStream(yc.getInputStream())) {
                JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(inStream.readLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;
    }

    public String Login(String userName, String pasword) {
        String inputLine = null;
        try {
            if (!userName.isEmpty() && !pasword.isEmpty()) {
                URL url = new URL("https://kanthpk.000webhostapp.com/login.php");
                URLConnection con = url.openConnection();
                con.setDoOutput(true);
                try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                    ps.print("&username=" + userName);
                    ps.print("&password=" + pasword);
                    con.getInputStream();
                }
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                    System.out.println("Pk Testing    " + inputLine);
                }
            }
        } catch (IOException ex) {
        }

        return inputLine;
    }

    public void registerUser(String userName, String email, String password, String confirmpwd) {
        String inputLine = null;
        String userExist = "User exist";
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/insert.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&username=" + userName);
                ps.print("&email=" + email);
                ps.print("&password=" + password);
                ps.print("&confirmpassword=" + confirmpwd);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                    if (inputLine.equals(userExist)) {
                        serviceValidater.validateConditionErrors("CHECK User Name", "User name is already existing", false, false, true, false, false);
                    }
                }
            }
        } catch (SecurityException e) {
            serviceValidater.validateLiveError("CONNECTION FAILED", "Something wrong with the server, Please try again", false, false, true, false, "Live", false);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
/*Tobe Removec
    public void DisplayInitialLetter(String User, String letter_1, String letter_2, String letter_3) {

        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/LetterValue.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&User=" + User);
                ps.print("&Letter1=" + letter_1 + letter_2 + letter_3);
                ps.print("&Letter2=" + letter_2);
                ps.print("&Letter3=" + letter_3);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                    System.out.println("LetterValue" + inputLine);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
*/
    public void deleteLetter(String GroupName, String UserName) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/deleteLetter.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&groupid=" + GroupName);
                ps.print("&userid=" + UserName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public JSONArray getLetter(String group) {
        String[] members = null;
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getLetterValue.php");
            URLConnection con = oracle.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&group=" + group);
                con.getInputStream();
            }
            try (DataInputStream in = new DataInputStream(con.getInputStream())) {
                JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(in.readLine());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;

    }

    public void setGroup(String groupName, String nickName, String member) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/group.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&GroupName=" + groupName);
                ps.print("&UserName=" + nickName);
                ps.print("&Players=" + member);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public JSONArray getGroup() {
        String[] members = null;
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getgroup.php");
            URLConnection yc = oracle.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()))) {
                JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(in.readLine());
                int n = array.size();
                for (int i = 0; i < n; i++) {
                    // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                    JSONObject jo = (JSONObject) array.get(i);
                    String GroupName = (String) jo.get("GroupName");
                    String UserName = (String) jo.get("UserName");
                    String Players = (String) jo.get("Players");
                    //System.out.println("outputyss"+GroupName+UserName+Players);                
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;

    }

    public void leaveGroup(String groupName, String nickName) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/leavegroup.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);           
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&groupname=" + groupName);
                ps.print("&username=" + nickName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public void setGroupUSer(String groupName, String nickName) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/usergroup.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&GroupName=" + groupName);
                ps.print("&UserName=" + nickName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public JSONArray getUserGroup(String GroupName,String nickName) {
        String[] members = null;       
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getusergroup.php");
            URLConnection yc = oracle.openConnection();
            yc.setDoOutput(true);
            try (PrintStream ps = new PrintStream(yc.getOutputStream())) {
                    ps.print("&username=" + nickName);
                    ps.print("&group=" + GroupName);
                    yc.getInputStream();
                }
                try (DataInputStream inStream = new DataInputStream(yc.getInputStream())) {
                    JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(inStream.readLine());
                }           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;
    }

    public void setInitialLetter(String GroupName, String UserName, String L1, String L2, String L3) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/setLetters.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&GroupName=" + GroupName);
                ps.print("&UserName=" + UserName);
                ps.print("&l1=" + L1);
                ps.print("&l2=" + L2);
                ps.print("&l3=" + L3);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public String checkEmail(String UserName, String Email) {
        String inputLine = null;
        try {
            if (!UserName.isEmpty() && !Email.isEmpty()) {
                URL url = new URL("https://kanthpk.000webhostapp.com/checkmail.php");
                URLConnection con = url.openConnection();
                con.setDoOutput(true);
                try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                    ps.print("&username=" + UserName);
                    ps.print("&email=" + Email);
                    con.getInputStream();
                }
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            else
            {
            serviceValidater.validateConditionErrors("CHECK INPUTS", "Please check your inputs", false, false, true, false, false);
            }
        } catch (IOException ex) {
        }
        return inputLine;
    }
    
    public void setRound(String groupName, String nickName, String roundid, String score,String level) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/setRound.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&GroupName=" + groupName);
                ps.print("&UserName=" + nickName);
                ps.print("&roundid="+roundid);
                ps.print("&score="+score);
                ps.print("&level="+level);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public JSONArray getRoundScore(String groupName, String nickName,String level) {
        String[] scores = null;
        System.out.println("     cou"+groupName+"nickName"+nickName);
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getScore.php");
            URLConnection yc = oracle.openConnection();
            yc.setDoOutput(true);
            try (PrintStream ps = new PrintStream(yc.getOutputStream())) {
                    ps.print("&username=" + nickName);
                    ps.print("&group=" + groupName);
                    ps.print("&level=" + level);
                    yc.getInputStream();
                }
                try (DataInputStream inStream = new DataInputStream(yc.getInputStream())) {
                    JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(inStream.readLine());
                }           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;

    }
    
    public void setGlobalScore( String nickName,String groupName,String Score) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/setGlobalScore.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&GroupName=" + groupName);
                ps.print("&UserName=" + nickName);
                ps.print("&score=" + Score);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public JSONArray getTotalScore() {
        String[] scores = null;
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getTotalScore.php");
            URLConnection yc = oracle.openConnection();
            yc.setDoOutput(true);
            try (PrintStream ps = new PrintStream(yc.getOutputStream())) {
                ps.print("&GroupName=" + ConstantElement.GroupName);
                yc.getInputStream();
            }
            try (DataInputStream inStream = new DataInputStream(yc.getInputStream())) {
                JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(inStream.readLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;
    }
    public JSONArray getFinalScore() {
        String[] scores = null;
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/FinalScore.php");
            URLConnection yc = oracle.openConnection();
            yc.setDoOutput(true);
            try (PrintStream ps = new PrintStream(yc.getOutputStream())) {
                ps.print("&GroupName=" + ConstantElement.GroupName);
                yc.getInputStream();
            }
            try (DataInputStream inStream = new DataInputStream(yc.getInputStream())) {
                JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(inStream.readLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;
    }
    public void updateGlobalScore(String groupName, String nickName, String Score) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/updateScore.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&GroupName=" + groupName);
                ps.print("&UserName=" + nickName);
                ps.print("&score=" + Score);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteRound(String GroupName, String UserName) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/deleteRound.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&groupid=" + GroupName);
                ps.print("&userid=" + UserName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    
    public void deleteEachRound(String GroupName, String UserName) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/deleteeachRound.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&groupid=" + GroupName);
                ps.print("&userid=" + UserName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    public void pauseGame(String status) {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/pause.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&status=" + status);
                ps.print("&group=" + ConstantElement.GroupName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    
    public String getpauseGame() {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/getPause.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&group=" + ConstantElement.GroupName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                    //System.out.println(""+inputLine);
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
return inputLine;
    }
    public String getPlayerReady() {
        String inputLine = null;
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/playerReadToPlay.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&group=" + ConstantElement.GroupName);
                ps.print("&username=" + ConstantElement.GlobalUserName);
                con.getInputStream();
                try (DataInputStream inStream = new DataInputStream(con.getInputStream())) {
                    inputLine = inStream.readLine();
                    //System.out.println(""+inputLine);
                }
            }
            //getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
return inputLine;
    }
    
    public String getCheckPlayer(String groupName, String nickName) {
        String inputLine = null;      
        JSONArray array = new JSONArray();
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/checkAllPlayer.php");
            URLConnection yc = oracle.openConnection();
            yc.setDoOutput(true);
            try (PrintStream ps = new PrintStream(yc.getOutputStream())) {
                ps.print("&group=" + groupName);
                ps.print("&username=" + nickName);
                yc.getInputStream();
                try (DataInputStream inStream = new DataInputStream(yc.getInputStream())) {
                    inputLine = inStream.readLine();
                    //System.out.println(""+inputLine);
                }
            }      
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputLine;

    }
    public String getUserGroupStart(String GroupName,String nickName) {
        String inputLine = null;             
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getGroupPlay.php");
            URLConnection yc = oracle.openConnection();
            yc.setDoOutput(true);
            try (PrintStream ps = new PrintStream(yc.getOutputStream())) {
                    ps.print("&username=" + nickName);
                    ps.print("&group=" + GroupName);
                    yc.getInputStream();
                }
                try (DataInputStream inStream = new DataInputStream(yc.getInputStream())) {
                    inputLine = inStream.readLine();
                    //System.out.println(""+inputLine);
                }          
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return inputLine;
    }
    
}
