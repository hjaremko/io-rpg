package pl.uj.io.cuteanimals.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.uj.io.cuteanimals.action.BuffCharacter;
import pl.uj.io.cuteanimals.action.ability.Focus;
import pl.uj.io.cuteanimals.model.fight.FightManager;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Provides methods to manage player.
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
@Component
public class Player implements IPlayer {
    private final WorldMap world;
    private PlayerAttributes stats;
    private ILocation currentLocation;
    private final IEquipment armorBackpack;
    private final IEquipment backpack;
    private GameState gameState;
    private final Map<String, IAction> abilities;
    private final FightManager fightManager;

    public Player(WorldMap world) {
        this.world = world;
        this.stats = new PlayerAttributes(this);
        this.currentLocation = world.getLocation("town");
        this.armorBackpack = new ArmorBackpack(this);
        this.backpack = new PlayerBackpack(this);
        this.gameState = GameState.EXPLORATION;
        this.abilities = new HashMap<>();
        this.fightManager = new FightManager(this);

        this.abilities.put("focus", new Focus());
    }

    @Autowired
    public Player(WorldMap world, RandomInteger random) {
        this(world);
        this.stats = new PlayerAttributes(this, random);
        this.abilities.put("focus", new Focus());
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
        return "Player";
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
        return abilities;
    }

    @Override
    public WorldMap getWorld() {
        return world;
    }
}
