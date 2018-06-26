/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author TeamStark
 */
public class LoginModel {

    private String userName;
    private String password;
    private boolean isOnline;
    private boolean logStatus;
    private String token;
    private boolean gameAvailableStatus;
    private int round;
    private boolean isRemember;

    private static final Logger LOG = Logger.getLogger(LoginModel.class.getName());

    public LoginModel() {
    }

    public LoginModel(String userName, String password, boolean isOnline, boolean logStatus, String token, boolean gameAvailableStatus, int round, boolean isRemember) {
        this.userName = userName;
        this.password = password;
        this.isOnline = isOnline;
        this.logStatus = logStatus;
        this.token = token;
        this.gameAvailableStatus = gameAvailableStatus;
        this.round = round;
        this.isRemember = isRemember;
    }

  
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void setLogStatus(boolean logStatus) {
        this.logStatus = logStatus;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setGameAvailableStatus(boolean gameAvailableStatus) {
        this.gameAvailableStatus = gameAvailableStatus;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setIsRemember(boolean isRemember) {
        this.isRemember = isRemember;
    }
    
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public boolean isLogStatus() {
        return logStatus;
    }

    public String getToken() {
        return token;
    }

    public boolean isGameAvailableStatus() {
        return gameAvailableStatus;
    }

    public int getRound() {
        return round;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public boolean isIsRemember() {
        return isRemember;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoginModel other = (LoginModel) obj;
        if (this.isOnline != other.isOnline) {
            return false;
        }
        if (this.logStatus != other.logStatus) {
            return false;
        }
        if (this.gameAvailableStatus != other.gameAvailableStatus) {
            return false;
        }
        if (this.round != other.round) {
            return false;
        }
        if (this.isRemember != other.isRemember) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        return true;
    }    
}
