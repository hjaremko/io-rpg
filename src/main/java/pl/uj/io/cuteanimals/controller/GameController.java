package pl.uj.io.cuteanimals.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // Maybe we should wrap this with ResponseEntity?
    @PostMapping(value = "/{id}/msg", consumes = "text/plain", produces = "text/plain")
    public String receiveOrderAndReturnResult(@PathVariable int id, @RequestBody String command) {
        // TODO: make command all lowercase
        // TODO: expand logging

        logger.info("User (" + id + ") sent command: " + command);

        // TODO: replace with login
        if ("start".equals(command)) {
            return gameService.getLocationInfo();
        }

        try {
            return gameService.execute(id, command);
        } catch (InvalidCommandException e) {
            logger.debug("Parsing user provided command failed.", e);
            return e.getMessage();
        }
    }
}
