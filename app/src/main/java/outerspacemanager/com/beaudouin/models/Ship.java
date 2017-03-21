package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;

/**
 * Created by nico on 20/03/17.
 */

public class Ship implements Serializable {
    private Integer shipId;
    private String name;
    private Float gasCost;
    private Float life;
    private Float maxAttack;
    private Float minAttack;
    private Float mineralCost;
    private Float shield;
    private Integer spatioportLevelNeeded;
    private Float speed;
    private Float timeToBuild;
    private Integer amount;

    public Ship() {}

    public Integer getShipId() { return this.shipId; }
    public String getName() { return this.name; }
    public Float getGasCost() { return this.gasCost; }
    public Float getLife() { return this.life; }
    public Float getMaxAttack() { return this.maxAttack; }
    public Float getMinAttack() { return this.minAttack; }
    public Float getMineralCost() { return this.mineralCost; }
    public Float getShield() { return this.shield; }
    public Integer getSpatioportLevelNeeded() { return this.spatioportLevelNeeded; }
    public Float getSpeed() { return this.speed; }
    public Float getTimeToBuild() { return this.timeToBuild; }

    public void setAmount(Integer amount) { this.amount = amount; }

}
