package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Provides methods to manage npc character.
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public class NPC implements ICharacter {
    private IEquipment armorBackpack;
    private IEquipment backpack;
    private String name;
    private final List<String> quotes;
    private int quoteIndex;

    public NPC(IEquipment armorBackpack, IEquipment backpack, String name, List<String> quotes) {
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

    public void setArmorBackpack(IEquipment armorBackpack) {
        this.armorBackpack = armorBackpack;
    }

    public void setBackpack(IEquipment backpack) {
        this.backpack = backpack;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gives list of npc's possible answers.
     *
     * @return list of strings
     */
    public List<String> getQuotes() {
        return quotes;
    }

    /**
     * Gives the number that means how many answers have already been given by npc. Npc can still
     * give its answer if this number is smaller than the size of the list.
     *
     * @return int type number of answers npc gave
     */
    public int getQuoteIndex() {
        return quoteIndex;
    }

    /**
     * Gives a text that is a typical response of a given npc to the player's "talk" action. This
     * can give information about this person, location, item received and what happened earlier or
     * what is happening at the moment. If npc gave all his possible answers or did not have any
     * then it returns a text saying that the person has nothing to say.
     *
     * @return string type npc's answer
     */
    public String getQuote() {
        if (quoteIndex == 0 && quotes.isEmpty()) {
            return "This character can't tell you anything";
        } else if (quoteIndex < quotes.size()) {
            var result = quotes.get(quoteIndex);
            quoteIndex++;
            return result;
        } else {
            return "This character can't tell you anything interesting";
        }
    }

    public String getName() {
        return name;
    }
}
