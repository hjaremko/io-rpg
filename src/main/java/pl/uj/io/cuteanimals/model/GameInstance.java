package pl.uj.io.cuteanimals.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.interpreter.Expression;
import pl.uj.io.cuteanimals.interpreter.Interpreter;
import pl.uj.io.cuteanimals.model.interfaces.IResult;
import pl.uj.io.cuteanimals.service.ItemService;

@Component
public class GameInstance {
    private final Interpreter interpreter;
    private final ItemService itemService;
    private Player player;

    @Autowired
    public GameInstance(ItemService itemService, Interpreter interpreter) {
        this.itemService = itemService;
        this.interpreter = interpreter;
        this.player = new Player(new WorldMap(itemService));
    }

    public GameInstance(ItemService itemService) {
        this.itemService = itemService;
        this.interpreter = new Interpreter();
        this.player = new Player(new WorldMap(itemService));
    }

    public IResult executeInput(String input) throws InvalidCommandException {
        Expression expr =
                interpreter.parse(input, player.getCurrentLocation().getAvailableActions());
        var inputResult =
                expr.interpret(player.getCurrentLocation().getAvailableActions()).execute(player);

        if (isPlayerDead()) {
            return new CompoundResult(List.of(inputResult, gameOver()));
        }

        return inputResult;
    }

    public Player getPlayer() {
        return player;
    }

    public IResult gameOver() {
        this.player = new Player(new WorldMap(itemService));
        return new Result("oj nie nie byczq");
    }

    private boolean isPlayerDead() {
        return player.getAttributes().getHealth() <= 0;
    }

    public String pickClass() {
        if (!player.getCurrentGameState().equals(GameState.LIMBO)) {
            return "You can't do that anymore.";
        }

        return "Pick your destiny.\n(Warrior, Magician, Archer)";
    }
}
