package org.example.du10;

public class Player {
    private String name;
    private String health;
    private String mana;

    public Player(String name, String health, String mana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }
}
