package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.*;
import pl.uj.io.cuteanimals.plot.locations.Trap;

public class Player implements IPlayer {
    PlayerAttributes stats = new PlayerAttributes(this);
    ILocation currentLocation = WorldMap.getInstance().getLocation("town");
    IEquipment armorBackpack = new ArmorBackpack(this);
    IEquipment backpack = new PlayerBackpack(this);
    GameState gameState = GameState.EXPLORATION;

    @Override
    public IEquipment getEquipment() {
        return backpack;
    }

    @Override
    public IEquipment getArmor() {
        return armorBackpack;
    }

    @Override
    public IAttributes getAttributes() {
        return stats;
    }

    @Override
    public Result use(IAction action) {
        return null;
    }

    @Override
    public void changeLocation(ILocation where) {
        if (where.getClass().equals(Trap.class)) {
            stats.addHealth(-10);
        }

        currentLocation = where;
    }

    public ILocation getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public GameState getCurrentGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
