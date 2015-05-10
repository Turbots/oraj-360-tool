package be.ordina.threesixty.person.model;

import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by stevedezitter on 22/04/15.
 */
public class Credentials {
    @Indexed
    private String username;
    //Use @JSONView to exclude this from being returned as rest service result
    private String password;
    private String salt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
