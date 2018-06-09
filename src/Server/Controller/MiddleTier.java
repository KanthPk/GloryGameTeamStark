/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Controller;

import glory_schema.ConstantElement;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author pk
 */
public class MiddleTier {

    public Boolean Logout(String user_id, String password) {
        try {
            URL url = new URL("https://kanthpk.000webhostapp.com/logout.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            ps.print("&username=" + user_id);
            ps.print("&password=" + password);
            con.getInputStream();
            ps.close();

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
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                animals = inputLine.split("\\s");
            }
            in.close();

        } catch (Exception e) {
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
                PrintStream ps = new PrintStream(con.getOutputStream());
                ps.print("&username=" + userName);
                ps.print("&password=" + pasword);
                con.getInputStream();
                ps.close();
                DataInputStream inStream = new DataInputStream(con.getInputStream());
                inputLine = inStream.readLine();
                System.out.println("Pk Testing    " + inputLine);
                inStream.close();
            }
        } catch (Exception ex) {
        }

        return inputLine;
    }

    public void registerUser(String userName, String email, String password, String confirmpwd) {
        try {
            //save the data
            // open a connection to the site
            URL url = new URL("https://kanthpk.000webhostapp.com/insert.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            ps.print("&username=" + userName);
            ps.print("&email=" + email);
            ps.print("&password=" + password);
            ps.print("&confirmpassword=" + confirmpwd);
            con.getInputStream();
            ps.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
