package outerspacemanager.com.beaudouin;

/**
 * Created by nico on 06/03/17.
 */

public final class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getLogin() {
        return this.username;
    }

    public void setLogin(String newUsername) {
        this.username = newUsername;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }




}
