package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;

/**
 * Created by nico on 21/03/17.
 */

public class Search implements Serializable {
    private Integer searchId;
    private String name;
    private String effect;
    private Integer level;
    private Boolean building;
    private Float amountOfEffectByLevel;
    private Float amountOfEffectLevel0;
    private Float gasCostLevel0;
    private Float gasCostByLevel;
    private Float mineralCostLevel0;
    private Float mineralCostByLevel;
    private Float timeToBuildLevel0;
    private Float timeToBuildByLevel;

    public Search() {}

    public Integer getSearchId() { return this.searchId; }
    public String getName() { return this.name; }
    public String getEffect() { return this.effect; }
    public Integer getLevel() { return this.level; }
    public Boolean isBuilding() { return this.building; }
    public Float getAmountOfEffectByLevel() { return this.amountOfEffectByLevel; }
    public Float getAmountOfEffectLevel0() { return this.amountOfEffectLevel0; }
    public Float getGasCostLevel0() { return this.gasCostLevel0; }
    public Float getGasCostByLevel() { return this.gasCostByLevel; }
    public Float getMineralCostLevel0() { return this.mineralCostLevel0; }
    public Float getMineralCostByLevel() { return this.mineralCostByLevel; }
    public Float getTimeToBuildLevel0() { return this.timeToBuildLevel0; }
    public Float getTimeToBuildByLevel() { return this.timeToBuildByLevel; }

}
