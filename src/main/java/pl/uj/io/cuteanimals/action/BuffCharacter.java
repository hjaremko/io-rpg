package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class BuffCharacter extends ArgumentlessAction {
    private final IAttributes attributes;

    public BuffCharacter(IAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public IResult actionBody(IPlayer player) {
        player.getAttributes().addHealth(attributes.getHealth());
        player.getAttributes().addAttack(attributes.getAttack());
        player.getAttributes().addDefence(attributes.getDefence());

        var message = "";
        message += (attributes.getHealth() != 0 ? "+" + attributes.getHealth() + " health " : "");
        message += (attributes.getAttack() != 0 ? "+" + attributes.getAttack() + " attack" : "");
        message +=
                (attributes.getDefence() != 0 ? "+" + attributes.getDefence() + " defence " : "");

        return new Result(message);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
