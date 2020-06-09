package pl.uj.io.cuteanimals.controller;

import static pl.uj.io.cuteanimals.model.Color.GREEN;
import static pl.uj.io.cuteanimals.model.Color.RED;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.CompoundResult;
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
        command = command.toLowerCase();
        // TODO: expand logging

        logger.info("User (" + id + ") sent command: " + command);

        // TODO: replace with login
        if ("start".equals(command)) {
            return gameService.pickClass(id);
        }
        try {
            var result = gameService.execute(id, command);

            // TODO: fix printing nested results
            if (result instanceof CompoundResult) {
                logger.info("Adding color to compound Result");

                return ((CompoundResult) result)
                        .getResults()
                        .stream()
                        .map(r -> addColor(r.getMessage(), r.getColor()))
                        .collect(Collectors.joining("\n"));
            }

            logger.info("Adding color to normal Result with message:");
            logger.info(result.getMessage());
            return addColor(result.getMessage(), result.getColor());
        } catch (InvalidCommandException e) {
            logger.debug("Parsing user provided command failed.", e);
            return e.getMessage();
        }
    }

    static String addColor(String string, Color color) {
        StringBuilder stringResult = new StringBuilder();

        switch (color) {
            case RED:
                stringResult.append("\u001b[31m").append(string).append("\u001b[0m");
                break;
            case GREEN:
                stringResult.append("\u001b[32m").append(string).append("\u001b[0m");
                break;
            case YELLOW:
                stringResult.append("\u001b[33m").append(string).append("\u001b[0m");
                break;
            default:
                stringResult.append(string);
        }

        return stringResult.toString();
    }
}
