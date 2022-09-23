package com.yuanno.hunterxx.data.entity;

public class EntityStatsBase implements IEntityStats {

    private int level;
    private int experience;
    private int maxExperience;
    private int levelPoints;
    private boolean combatMode;
    private int health;
    private int strength;
    private int defense;
    private int speed;
    private int nen;
    private int aura;
    private int maxAura;
    private int auraRegeneration;
    private String category = "";
    private int jenny;


    @Override
    public boolean getCombatMode() {
        return this.combatMode;
    }

    @Override
    public void setCombatMode(boolean value) {
        this.combatMode = value;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void alterLevel(int value) {
        this.level = this.level + value;
    }

    @Override
    public void setLevel(int value) {
        this.level = value;
    }

    @Override
    public int getExperience() {
        return this.experience;
    }

    @Override
    public void alterExperience(int value) {
        this.experience = this.experience + value;
    }

    @Override
    public void setExperience(int value) {
        this.experience = value;
    }

    @Override
    public int getMaxExperience() {
        return this.maxExperience;
    }

    @Override
    public void alterMaxEperience(int value) {
        this.maxExperience = this.maxExperience + value;
    }

    @Override
    public void setMaxExperience(int value) {
        this.maxExperience = value;
    }

    @Override
    public int getLevelPoints() {
        return this.levelPoints;
    }

    @Override
    public void alterLevelPoints(int value) {
        this.levelPoints = this.levelPoints + value;
    }

    @Override
    public void setLevelPoints(int value) {
        this.levelPoints = value;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void alterHealth(int value) {
        this.health = this.health + value;
    }

    @Override
    public void setHealth(int value) {
        this.health = value;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void alterStrength(int value) {
        this.strength = this.strength + value;
    }

    @Override
    public void setStrength(int value) {
        this.strength = value;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public void alterDefense(int value) {
        this.defense = this.defense + value;
    }

    @Override
    public void setDefense(int value) {
        this.defense = value;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void alterSpeed(int value) {
        this.speed = this.speed + value;
    }

    @Override
    public void setSpeed(int value) {
        this.speed = value;
    }

    @Override
    public int getNen() {
        return this.nen;
    }

    @Override
    public void alterNen(int value) {
        this.nen = this.nen + value;
    }

    @Override
    public void setNen(int value) {
        this.nen = value;
    }

    @Override
    public int getAura() {
        return this.aura;
    }

    @Override
    public void alterAura(int value) {
        this.aura = this.aura + value;
    }

    @Override
    public void setAura(int value) {
        this.aura = value;
    }

    @Override
    public int getMaxAura() {
        return this.maxAura;
    }

    @Override
    public void alterMaxAura(int value) {
        this.maxAura = this.maxAura + value;
    }

    @Override
    public void setMaxAura(int value) {
        this.maxAura = value;
    }

    @Override
    public int getAuraRegeneration() {
        return this.auraRegeneration;
    }

    @Override
    public void alterAuraRegeneration(int value) {
        this.auraRegeneration = this.auraRegeneration + value;
    }

    @Override
    public void setAuraRegeneration(int value) {
        this.auraRegeneration = value;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(String value) {
        this.category = value;
    }

    @Override
    public int getJenny() {
        return this.jenny;
    }

    @Override
    public void alterJenny(int value) {
        this.jenny = this.jenny + value;
    }

    @Override
    public void setJenny(int value) {
        this.jenny = value;
    }
}
