package outerspacemanager.com.beaudouin.models;

/**
 * Created by nico on 06/03/17.
 */

public final class User {

    private String username;
    private String password;
    private String email;
    private String token;
    private Float gas;
    private Float minerals;
    private Double points;

    public User() {}
    public User(String username, String password, String token, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
    }

    public String getUsername() {
        return this.username;
    }
    public String getToken() { return this.token; }
    public Float getGas() { return this.gas; }
    public Float getMinerals() { return this.minerals; }
    public Double getPoints() { return this.points; }

    public void setLogin(String newUsername) {
        this.username = newUsername;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
    public void setToken(String newToken) { this.token = newToken; }
    public void setPoints(Double newPoints) { this.points = newPoints; }
    public void setGas(Float moarGas) {
        if(this.gas != null) {
            if(this.gas > 0)
                this.gas += moarGas;
            else
                this.gas = moarGas;
        } else {
            this.gas = moarGas;
        }
    }
    public void setMinerals(Float moarMinerals) {
        if(this.minerals != null) {
            if(this.minerals > 0)
                this.minerals += moarMinerals;
            else
                this.minerals = moarMinerals;
        } else {
            this.minerals = moarMinerals;
        }

    }




}
