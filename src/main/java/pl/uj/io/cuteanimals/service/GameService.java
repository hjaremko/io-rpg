package pl.uj.io.cuteanimals.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.WorldMap;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IResult;
import pl.uj.io.cuteanimals.model.interpreter.Expression;
import pl.uj.io.cuteanimals.model.interpreter.Interpreter;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);
    // TODO: replace with multiple players, they should be initialized after WorldMap
    private final Player player;

    @Autowired
    public GameService(ItemService itemService) {
        // TODO: use repository
        WorldMap.getInstance().initialize(itemService);
        // Every new player should spawn with sword
        player = new Player();
        player.getEquipment().putItem(itemService.getItem(1));
    }

    public String execute(int characterId, String command) throws InvalidCommandException {
        Expression expr =
                Interpreter.parse(command, player.getCurrentLocation().getAvailableActions());
        IAction action = expr.interpret(player.getCurrentLocation().getAvailableActions());
        IResult result = action.execute(player);
        return result.getMessage();
    }

    public String getLocationInfo() {
        return player.getCurrentLocation().getDescription();
    }
}
