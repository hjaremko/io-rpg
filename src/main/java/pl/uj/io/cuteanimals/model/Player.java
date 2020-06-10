package pl.uj.io.cuteanimals.model;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.action.BuffCharacter;
import pl.uj.io.cuteanimals.model.fight.FightManager;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Provides methods to manage player.
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public class Player implements IPlayer {
    private final int id;
    private final WorldMap world;
    private final IEquipment armorBackpack;
    private final IEquipment backpack;
    private final FightManager fightManager;
    private PlayerClass playerClass;
    private PlayerAttributes stats;
    private ILocation currentLocation;
    private GameState gameState;

    public Player(int id, WorldMap world) {
        this.id = id;
        this.world = world;
        this.stats = new PlayerAttributes(this);
        this.currentLocation = world.getLocation("town");
        this.armorBackpack = new ArmorBackpack(this);
        this.backpack = new PlayerBackpack(this);
        this.gameState = GameState.LIMBO;
        this.fightManager = new FightManager(this);
        this.playerClass = new Slave();
    }

    public Player(int id, WorldMap world, RandomInteger random) {
        this(id, world);
        this.stats = new PlayerAttributes(this, random);
    }

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
    public IResult use(IItem item) {
        var eatingResult = new BuffCharacter(item.getAttributes()).execute(this);
        getEquipment().removeItem(item);
        var attackResult = fightManager.contrAttack();

        if (attackResult != null) {
            return new CompoundResult(List.of(eatingResult, attackResult));
        }

        return eatingResult;
    }

    @Override
    public IResult changeLocation(ILocation where) {
        currentLocation = where;
        return currentLocation.onEnter(this);
    }

    @Override
    public ILocation getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public GameState getCurrentGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String toString() {
        return playerClass.toString();
    }

    @Override
    public FightManager getFightManager() {
        return fightManager;
    }

    @Override
    public int takeDamage(int damage) {
        var taken = Math.max(0, damage - (stats.getDefence() / 2));
        stats.addHealth(-taken);
        return taken;
    }

    @Override
    public Map<String, IAction> getAbilities() {
        return playerClass.getAbilities();
    }

    @Override
    public WorldMap getWorld() {
        return world;
    }

    @Override
    public void setClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    public int getId() {
        return id;
    }
}
