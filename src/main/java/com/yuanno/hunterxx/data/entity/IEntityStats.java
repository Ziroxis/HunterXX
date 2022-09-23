package com.yuanno.hunterxx.data.entity;

public interface IEntityStats {

    // COMBAT MODE
    boolean getCombatMode();
    void setCombatMode(boolean value);

    // LEVEL
    int getLevel();
    void alterLevel(int value);
    void setLevel(int value);
    int getExperience();
    void alterExperience(int value);
    void setExperience(int value);
    int getMaxExperience();
    void alterMaxEperience(int value);
    void setMaxExperience(int value);
    int getLevelPoints();
    void alterLevelPoints(int value);
    void setLevelPoints(int value);

    // STATS
    // health
    int getHealth();
    void alterHealth(int value);
    void setHealth(int value);
    // strength
    int getStrength();
    void alterStrength(int value);
    void setStrength(int value);
    // defense
    int getDefense();
    void alterDefense(int value);
    void setDefense(int value);
    // speed
    int getSpeed();
    void alterSpeed(int value);
    void setSpeed(int value);
    // nen
    int getNen();
    void alterNen(int value);
    void setNen(int value);

    // NEN
    // aura
    int getAura();
    void alterAura(int value);
    void setAura(int value);
    // maxAura
    int getMaxAura();
    void alterMaxAura(int value);
    void setMaxAura(int value);
    // auraRegeneration
    int getAuraRegeneration();
    void alterAuraRegeneration(int value);
    void setAuraRegeneration(int value);
    // category
    String getCategory();
    void setCategory(String value);

    // JENNY
    int getJenny();
    void alterJenny(int value);
    void setJenny(int value);

}
