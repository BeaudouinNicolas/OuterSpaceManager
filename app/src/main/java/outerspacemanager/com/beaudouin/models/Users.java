package outerspacemanager.com.beaudouin.models;

import java.util.ArrayList;

/**
 * Created by nico on 14/03/17.
 */

public class Users {
    private Integer size;
    private ArrayList<User> users;

    public Users(ArrayList<User> users) {
        this.size = users.size();
        this.users = users;
    }

    public Integer getSize() { return this.size; }
    public ArrayList<User> getUsers() { return this.users; }
}
