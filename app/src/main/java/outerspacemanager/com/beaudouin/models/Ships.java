package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nico on 20/03/17.
 */

public class Ships implements Serializable {
    // space shuttle attributes
    private Float currentUserMinerals;
    private Float currentUserGas;
    private Integer size;
    private ArrayList<Ship> ships;

    // reports attributes
    private ArrayList<Ship> fleet;
    private Float capacity;

    public Ships() {
        this.ships = new ArrayList<>();
        this.size = ships.size();
    }

    public Integer getSize() { return this.size; }
    public ArrayList<Ship> getShips() { return this.ships; }
    public ArrayList<Ship> getFleet() { return this.fleet; }
    public Float getCurrentUserMinerals() { return this.currentUserMinerals; }
    public Float getCurrentUserGas() { return this.currentUserGas; }
    public Float getCapacity() { return this.capacity; }


}
