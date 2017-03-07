package outerspacemanager.com.beaudouin.models;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.models.Building;

/**
 * Created by nico on 07/03/17.
 */

public class Buildings {
    private Integer size;
    private ArrayList<Building> buildings;

    public Buildings() {
        this.buildings = new ArrayList<>();
        this.size = buildings.size();
    }

    public Integer getSize() { return this.size; }
    public ArrayList<Building> getBuildings() { return this.buildings; }

    public void addBuilding(Building newBuilding) { this.buildings.add(newBuilding); }

}
