package pl.uj.io.cuteanimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.uj.io.cuteanimals.model.exceptions.InvalidCommandException;
import pl.uj.io.cuteanimals.model.interpreter.Interpreter;
import pl.uj.io.cuteanimals.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // Maybe we should wrap this with ResponseEntity?
    @PostMapping(value = "/msg", consumes = "text/plain", produces = "text/plain")
    public String receiveOrderAndReturnResult(@RequestBody String command) {
        try {
            gameService.execute(Interpreter.parse(command));
            // TODO: remove this shit
            return command;
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}
