package pl.uj.io.cuteanimals.model;

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
    private final PlayerAttributes stats = new PlayerAttributes(this);
    private ILocation currentLocation = WorldMap.getInstance().getLocation("town");
    private final IEquipment armorBackpack = new ArmorBackpack(this);
    private final IEquipment backpack = new PlayerBackpack(this);
    private GameState gameState = GameState.EXPLORATION;

    //    List<IAction> abilities;
    FightManager fightManager;

    public Player() {
        //        this.abilities = new ArrayList<>();
        //        this.abilities.add( new StandardAttack(this) );
        this.fightManager = new FightManager(this);
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
        return new Result(eatingResult.toString() + "\n" + attackResult);
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
    public int getDamage(int damage) {
        var taken = Math.max(0, damage - (stats.getDefence() / 2));
        stats.addHealth(-taken);
        return taken;
    }
}
