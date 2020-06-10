package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.interpreter.Expression;
import pl.uj.io.cuteanimals.interpreter.Interpreter;
import pl.uj.io.cuteanimals.model.interfaces.IResult;
import pl.uj.io.cuteanimals.service.GameService;
import pl.uj.io.cuteanimals.service.ItemService;

public class GameInstance {
    private final Interpreter interpreter;
    private final ItemService itemService;
    private final GameService gameService;
    private Player player;

    public GameInstance(
            int id, ItemService itemService, Interpreter interpreter, GameService gameService) {
        this.itemService = itemService;
        this.interpreter = interpreter;
        this.gameService = gameService;
        this.player = new Player(id, new WorldMap(itemService));
    }

    public GameInstance(int id, ItemService itemService, GameService gameService) {
        this.itemService = itemService;
        this.gameService = gameService;
        this.interpreter = new Interpreter();
        this.player = new Player(id, new WorldMap(itemService));
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
        this.player = new Player(player.getId(), new WorldMap(itemService));
        return new Result(this.gameService.pickClass(player.getId()));
    }

    private boolean isPlayerDead() {
        return player.getAttributes().getHealth() <= 0;
    }

    public String pickClass() {
        if (!player.getCurrentGameState().equals(GameState.LIMBO)) {
            return "You can't do that anymore.";
        }

        return "Pick your destiny.\n(Warrior, Monk, Archer)";
    }
}
