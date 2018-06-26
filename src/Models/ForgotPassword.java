/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Objects;

/**
 *
 * @author TeamStark
 */
public class ForgotPassword {
    private String email;
    private String oldpassword;
    private String newpassword;

    public ForgotPassword() {
    }
   
    public ForgotPassword(String email, String oldpassword, String newpassword) {
        this.email = email;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getEmail() {
        return email;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ForgotPassword other = (ForgotPassword) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.oldpassword, other.oldpassword)) {
            return false;
        }
        if (!Objects.equals(this.newpassword, other.newpassword)) {
            return false;
        }
        return true;
    }
}
