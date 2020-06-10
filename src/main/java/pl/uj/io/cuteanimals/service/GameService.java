package pl.uj.io.cuteanimals.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.interpreter.Interpreter;
import pl.uj.io.cuteanimals.model.GameInstance;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

@Service
public class GameService {
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);
    private final Interpreter interpreter;
    private final ItemService itemService;
    private final List<GameInstance> players;
    private final Lock lock;

    @Autowired
    public GameService(Interpreter interpreter, ItemService itemService) {
        this.interpreter = interpreter;
        this.itemService = itemService;
        this.players = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public IResult execute(int characterId, String command) throws InvalidCommandException {
        return players.get(characterId).executeInput(command);
    }

    public String getLocationInfo(int characterId) {
        return players.get(characterId).getPlayer().getCurrentLocation().getDescription();
    }

    public int getFirstFreeID() {
        lock.lock();
        int result = -1;
        try {
            result = players.size();
            players.add(new GameInstance(result, itemService, interpreter, this));
        } finally {
            lock.unlock();
        }
        return result;
    }

    public String pickClass(int characterId) {
        logger.trace("User with ID: " + characterId + "is picking class");
        return players.get(characterId).pickClass();
    }
}
