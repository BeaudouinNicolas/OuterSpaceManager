package outerspacemanager.com.beaudouin.models;

/**
 * Created by nico on 07/03/17.
 */

public class Building {
    private Integer id;
    private Float amountOfEffectByLevel;
    private Float amountOfEffectLevel0;
    private Boolean building;
    private String effect;
    private Float gasCostByLevel;
    private Float gasCostLevel0;
    private Integer level;
    private Float mineralCostByLevel;
    private Float mineralCostLevel0;
    private String name;
    private Integer timeToBuildByLevel;
    private Integer timeToBuildLevel0;
    private String imageUrl;

    public Building() { }

    public Integer getId() { return this.id; }
    public Float getAmountOfEffectByLevel() { return this.amountOfEffectByLevel; }
    public Float getAmountOfEffectLevel0() { return this.amountOfEffectLevel0; }
    public Boolean getBuilding() { return this.building; }
    public String getEffect() { return this.effect; }
    public Float getGasCostByLevel() { return this.gasCostByLevel; }
    public Float getGasCostLevel0() { return this.gasCostLevel0; }
    public Integer getLevel() { return this.level; }
    public Float getMineralCostByLevel() { return this.gasCostByLevel; }
    public Float getMineralCostLevel0() { return this.gasCostLevel0; }
    public String getName() { return this.name; }
    public Integer getTimeToBuildByLevel() { return this.timeToBuildByLevel; }
    public Integer getTimeToBuildLevel0() { return this.timeToBuildLevel0; }
    public String getImageUrl() { return this.imageUrl; }

    public void setAmountOfEffectByLevel(Float amountOfLevel) { this.amountOfEffectByLevel = amountOfLevel; }
    public void setAmountOfEffectLevel0(Float amountOf0) { this.amountOfEffectLevel0 = amountOf0; }
    public void setBuilding(Boolean building) { this.building = building; }
    public void setEffect(String effect) { this.effect = effect; }
    public void setGasCostByLevel(Float gasCostLevel) { this.gasCostByLevel = gasCostLevel; }
    public void setGasCostLevel0(Float gasCost0) { this.gasCostLevel0 = gasCost0; }
    public void setLevel(Integer level) { this.level = level; }
    public void setMineralCostByLevel(Float mineralCostLevel) { this.mineralCostByLevel = mineralCostLevel; }
    public void setMineralCostLevel0(Float mineralCost0) { this.mineralCostLevel0 = mineralCost0; }
    public void setName(String name) { this.name = name; }
    public void setTimeToBuildByLevel(Integer timeBuildLevel) { this.timeToBuildByLevel = timeBuildLevel; }
    public void setTimeToBuildLevel0(Integer timeBuild0) { this.timeToBuildLevel0 = timeBuild0; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }



}
