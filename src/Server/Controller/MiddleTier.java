/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Controller;

import animation.TransitionService;
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
 * @author pk
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

    public Boolean Logout(String user_id, String password) {
        try {
            URL url = new URL("https://kanthpk.000webhostapp.com/logout.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            try (PrintStream ps = new PrintStream(con.getOutputStream())) {
                ps.print("&username=" + user_id);
                ps.print("&password=" + password);
                con.getInputStream();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

    public String[] onlineUsers() {
        String[] animals = null;
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/sample.php");
            URLConnection yc = oracle.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    animals = inputLine.split("\\s");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return animals;
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
                    if (inputLine.equals("User exist")) {
                        serviceValidater.validateConditionErrors("CHECK User Name", "User name is already existing", false, false, true, false);
                    }
                }
            }
        } catch (SecurityException e) {
            serviceValidater.validateLiveError("CONNECTION FAILED", "Something wrong with the server, Please try again", false, false, true, false, "Live");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

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

    public void deleteLetter() {
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/Obsolete.php");
            URLConnection yc = oracle.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()))) {
                System.out.println("" + in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public String[] getLetter() {
        String[] animals = null;
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getLetterValue.php");
            URLConnection yc = oracle.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    animals = inputLine.split("\\s");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return animals;

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
            getGroup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public JSONArray getGroup() {
        String[] members = null;
        JSONArray array = null;
        try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/getgroup.php");
            URLConnection yc = oracle.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()))) {
                JSONParser parser =new JSONParser();
                array =(JSONArray)parser.parse(in.readLine());
                int n = array.size();            
                for (int i = 0; i < n; i++) {
                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                JSONObject jo = (JSONObject)array.get(i);      
                String GroupName = (String) jo.get("GroupName");
                String UserName = (String) jo.get("UserName");
                String Players = (String) jo.get("Players");
                System.out.println("outputyss"+GroupName+UserName+Players);                
            }                                                                       
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;

    }
    
    
}
