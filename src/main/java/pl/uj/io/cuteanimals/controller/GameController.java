package pl.uj.io.cuteanimals.controller;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.CompoundResult;
import pl.uj.io.cuteanimals.model.interfaces.IResult;
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

    @GetMapping("/")
    public int receiveFirstFreeID() {
        int id = gameService.getFirstFreeID();
        logger.info("New player ID: " + id);
        return id;
    }

    @PostMapping(value = "/{id}/msg", consumes = "text/plain", produces = "text/plain")
    public String receiveOrderAndReturnResult(@PathVariable int id, @RequestBody String command) {
        command = command.toLowerCase().trim();

        logger.debug("User (" + id + ") sent command: " + command);

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
                        .map(this::addColor)
                        .collect(Collectors.joining("\n"));
            }

            logger.trace("Adding color to normal Result with message:");
            logger.debug(result.getMessage());
            return addColor(result);
        } catch (InvalidCommandException e) {
            logger.debug("Parsing user provided command failed.", e);
            return e.getMessage();
        }
    }

    private String addColor(IResult result) {
        StringBuilder stringResult = new StringBuilder();

        if (result instanceof CompoundResult) {
            for (var r: ((CompoundResult) result).getResults()) {
                stringResult.append(addColor(r));
            }
        } else {
            var color = result.getColor();
            var message = result.getMessage();
            switch (color) {
                case RED:
                    stringResult.append("\u001b[31m").append(message).append("\u001b[0m");
                    break;
                case GREEN:
                    stringResult.append("\u001b[32m").append(message).append("\u001b[0m");
                    break;
                case YELLOW:
                    stringResult.append("\u001b[33m").append(message).append("\u001b[0m");
                    break;
                default:
                    stringResult.append(message);
            }
        }


        return stringResult.toString();
    }
}
