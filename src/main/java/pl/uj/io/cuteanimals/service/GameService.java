package pl.uj.io.cuteanimals.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.uj.io.cuteanimals.model.interpreter.Expression;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    public String execute(Expression expr) {
        // We need to somehow create&provide context&character there:
        //
        // IAction action = expr.interpret(CONTEXT_PLACEHOLDER);
        //
        // IResult result = action.execute(CHARACTER_PLACEHOLDER);
        //
        // return result.getMessage();
        return null;
    }
}
