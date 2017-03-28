package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nico on 24/03/17.
 */

public class Report implements Serializable {
    private ArrayList<Ship> attackerFleet;
    private Ships attackerFleetAfterBattle;
    private ArrayList<Ship> defenderFleet;
    private Ships defenderFleetAfterBattle;
    private Long date;
    private Long dateInv;
    private String from;
    private String to;
    private String type;
    private Float gasWon;
    private Float mineralsWon;

    public Report() {}

    public ArrayList<Ship> getAttackerFleet() { return this.attackerFleet; }
    public Ships getAttackerFleetAfterBattle() { return this.attackerFleetAfterBattle; }
    public ArrayList<Ship> getDefenderFleet() { return this.defenderFleet; }
    public Ships getDefenderFleetAfterBattle() { return this.defenderFleetAfterBattle; }
    public Long getDate() { return this.date; }
    public Long getDateInv() { return this.dateInv; }
    public String getFrom() { return this.from; }
    public String getTo() { return this.to; }
    public String getType() { return this.type; }
    public Float getGasWon() { return this.gasWon; }
    public Float getMineralsWon() { return this.mineralsWon; }

}
