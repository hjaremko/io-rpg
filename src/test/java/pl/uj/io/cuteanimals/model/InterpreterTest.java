package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.uj.io.cuteanimals.model.interpreter.Interpreter.parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.action.GoAction;
import pl.uj.io.cuteanimals.model.action.InvestigateAction;
import pl.uj.io.cuteanimals.model.exceptions.InvalidCommandException;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interpreter.Expression;
import pl.uj.io.cuteanimals.model.interpreter.Interpreter;

class InterpreterTest {
    @Test
    void singleActionParseTest() throws InvalidCommandException {
        var expr = parse("investigate");
        Map<String, IAction> context = new HashMap<>();
        context.put("investigate", new InvestigateAction());

        var result = expr.interpret(context);
        assertThat(result.execute(null).getMessage().equals("Looking around null"));
    }

    @Test
    void actionWithArgsParseTest() throws InvalidCommandException {
        var expr = parse("go flavour town");
        Map<String, IAction> context = new HashMap<>();

        context.put("go", new GoAction());

        var result = expr.interpret(context);
        assertThat(result.execute(null).getMessage().equals("Going to [flavour, town]"));
    }

    @Test
    void argumentInterpretTest() {
        var arg = Expression.argument("left");
        Map<String, IAction> context = new HashMap<>();
        var result = arg.interpret(context);

        assertThat(result.execute(null).getMessage().equals("[left]"));
    }

    @Test
    void multipleArgumentParseTest() {
        var left = Expression.argument("left");
        var right = Expression.argument(left, "right");
        var up = Expression.argument(right, "up");
        Map<String, IAction> context = new HashMap<>();
        var result = up.interpret(context);

        assertThat(
                result.execute(null)
                        .getMessage()
                        .equals(List.of("up", "right", "left").toString()));
    }

    @Test
    void invalidCommandTest() {
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("i am invalid"));
    }

    @Test
    void singleInvalidTokenTest() {
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("invalid"));
    }

    @Test
    void multipleActionTokenTest() {
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("go go invalid go"));
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("go go go go go"));
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("go go go go"));
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("go go go"));
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("go go"));
    }

    @Test
    void reversedArgumentsTest() {
        assertThrows(InvalidCommandException.class, () -> Interpreter.parse("flavour go"));
    }
}
