package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class NPC implements ICharacter {

    private ILocation currentLocation;

    private IEquipment armorBackpack;

    private IEquipment backpack;

    private String name;

    private List<String> quotes;

    private int quoteIndex;

    public NPC(
            ILocation currentLocation,
            IEquipment armorBackpack,
            IEquipment backpack,
            String name,
            List<String> quotes) {
        this.currentLocation = currentLocation;
        this.armorBackpack = armorBackpack;
        this.backpack = backpack;
        this.name = name;
        this.quotes = quotes;
        this.quoteIndex = 0;
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
        return null;
    }

    @Override
    public Result use(IAction action) {
        return null;
    }

    @Override
    public void changeLocation(ILocation where) {}

    @Override
    public GameState getCurrentGameState() {
        return null;
    }

    public void setCurrentLocation(ILocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setArmorBackpack(IEquipment armorBackpack) {
        this.armorBackpack = armorBackpack;
    }

    public void setBackpack(IEquipment backpack) {
        this.backpack = backpack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public int getQuoteIndex() {
        return quoteIndex;
    }

    public String getQuote() {
        if (quoteIndex == 0 && quotes.size() == 0) {
            return "";
        } else if (quoteIndex < quotes.size() - 1) {
            var result = quotes.get(quoteIndex);
            quoteIndex++;
            return result;
        } else {
            return quotes.get(quoteIndex);
        }
    }

    public String getName() {
        return name;
    }
}
