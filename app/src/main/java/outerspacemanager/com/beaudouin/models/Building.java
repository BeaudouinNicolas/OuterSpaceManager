package outerspacemanager.com.beaudouin.models;

/**
 * Created by nico on 07/03/17.
 */

public class Building {
    private Integer amountOfEffectByLevel;
    private Integer amountOfEffectLevel0;
    private Boolean building;
    private String effect;
    private Integer gasCostByLevel;
    private Integer gasCostLevel0;
    private Integer level;
    private Integer mineralCostByLevel;
    private Integer mineralCostLevel0;
    private String name;
    private Integer timeToBuildByLevel;
    private Integer timeToBuildLevel0;
    private String imageUrl;

    public Building() { }

    public Integer getAmountOfEffectByLevel() { return this.amountOfEffectByLevel; }
    public Integer getAmountOfEffectLevel0() { return this.amountOfEffectLevel0; }
    public Boolean getBuilding() { return this.building; }
    public String getEffect() { return this.effect; }
    public Integer getGasCostByLevel() { return this.gasCostByLevel; }
    public Integer getGasCostLevel0() { return this.gasCostLevel0; }
    public Integer getLevel() { return this.level; }
    public Integer getMineralCostByLevel() { return this.gasCostByLevel; }
    public Integer getMineralCostLevel0() { return this.gasCostLevel0; }
    public String getName() { return this.name; }
    public Integer getTimeToBuildByLevel() { return this.timeToBuildByLevel; }
    public Integer getTimeToBuildLevel0() { return this.timeToBuildLevel0; }
    public String getImageUrl() { return this.imageUrl; }

    public void setAmountOfEffectByLevel(Integer amountOfLevel) { this.amountOfEffectByLevel = amountOfLevel; }
    public void setAmountOfEffectLevel0(Integer amountOf0) { this.amountOfEffectLevel0 = amountOf0; }
    public void setBuilding(Boolean building) { this.building = building; }
    public void setEffect(String effect) { this.effect = effect; }
    public void setGasCostByLevel(Integer gasCostLevel) { this.gasCostByLevel = gasCostLevel; }
    public void setGasCostLevel0(Integer gasCost0) { this.gasCostLevel0 = gasCost0; }
    public void setLevel(Integer level) { this.level = level; }
    public void setMineralCostByLevel(Integer mineralCostLevel) { this.mineralCostByLevel = mineralCostLevel; }
    public void setMineralCostLevel0(Integer mineralCost0) { this.mineralCostLevel0 = mineralCost0; }
    public void setName(String name) { this.name = name; }
    public void setTimeToBuildByLevel(Integer timeBuildLevel) { this.timeToBuildByLevel = timeBuildLevel; }
    public void setTimeToBuildLevel0(Integer timeBuild0) { this.timeToBuildLevel0 = timeBuild0; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }



}
