package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;

/**
 * Created by nico on 07/03/17.
 */

public class Building implements Serializable {
    private Integer id;
    private Double amountOfEffectByLevel;
    private Double amountOfEffectLevel0;
    private Boolean building;
    private String effect;
    private Double gasCostByLevel;
    private Double gasCostLevel0;
    private Integer level;
    private Double mineralCostByLevel;
    private Double mineralCostLevel0;
    private String name;
    private Integer timeToBuildByLevel;
    private Integer timeToBuildLevel0;
    private String imageUrl;

    public Building() { }

    public Integer getId() { return this.id; }
    public Double getAmountOfEffectByLevel() { return this.amountOfEffectByLevel; }
    public Double getAmountOfEffectLevel0() { return this.amountOfEffectLevel0; }
    public Boolean isBuilding() { return this.building; }
    public String getEffect() { return this.effect; }
    public Double getGasCostByLevel() { return this.gasCostByLevel; }
    public Double getGasCostLevel0() { return this.gasCostLevel0; }
    public Integer getLevel() { return this.level; }
    public Double getMineralCostByLevel() { return this.gasCostByLevel; }
    public Double getMineralCostLevel0() { return this.gasCostLevel0; }
    public String getName() { return this.name; }
    public Integer getTimeToBuildByLevel() { return this.timeToBuildByLevel; }
    public Integer getTimeToBuildLevel0() { return this.timeToBuildLevel0; }
    public String getImageUrl() { return this.imageUrl; }
}
