package pl.uj.io.cuteanimals.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.model.GameInstance;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

@Service
public class GameService {
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);
    // TODO: replace with multiple instances
    private final GameInstance gameInstance;

    @Autowired
    public GameService(ItemService itemService) {
        this.gameInstance = new GameInstance(itemService);
    }

    public IResult execute(int characterId, String command) throws InvalidCommandException {
        return gameInstance.executeInput(command);
    }

    public String getLocationInfo(int characterId) {
        return gameInstance.getPlayer().getCurrentLocation().getDescription();
    }

    public String pickClass(int characterId) {
        return gameInstance.pickClass();
    }
}
