package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nico on 20/03/17.
 */

public class Ships implements Serializable {
    private Integer size;
    private ArrayList<Ship> ships;

    public Ships() {
        this.ships = new ArrayList<>();
        this.size = ships.size();
    }

    public Integer getSize() { return this.size; }
    public ArrayList<Ship> getShips() { return this.ships; }


}
