package outerspacemanager.com.beaudouin.models;

/**
 * Created by nico on 06/03/17.
 */

public final class User {

    private String username;
    private String password;
    private String token;
    private Float gas;
    private Float minerals;
    private Integer points;

    public User(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getUsername() {
        return this.username;
    }
    public String getToken() { return this.token; }
    public Float getGas() { return this.gas; }
    public Float getMinerals() { return this.minerals; }
    public Integer getPoints() { return this.points; }

    public void setLogin(String newUsername) {
        this.username = newUsername;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setToken(String newToken) { this.token = newToken; }
    public void setPoints(Integer newPoints) { this.points = newPoints; }
    public void setGas(Float moarGas) {
        if(this.gas > 0)
            this.gas += moarGas;
        else
            this.gas = moarGas;
    }
    public void setMinerals(Float moarMinerals) {
        if(this.gas > 0)
            this.gas += moarMinerals;
        else
            this.gas = moarMinerals;
    }




}
