package pl.uj.io.cuteanimals.interpreter;

import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.action.GoAction;
import pl.uj.io.cuteanimals.action.InvestigateAction;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.model.DefaultLocation;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.interfaces.IAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InterpreterTest {

    private Interpreter interpreter = new Interpreter();

    @Test
    void singleActionParseTest() throws InvalidCommandException {
        Player player = new Player();
        Map<String, IAction> context = new HashMap<>();
        context.put("investigate", new InvestigateAction("Looking around null"));
        var expr = interpreter.parse("investigate", context);

        var result = expr.interpret(context);

        assertThat(result.execute(player).getMessage().equals("Looking around null"));
    }

    @Test
    void actionWithArgsParseTest() throws InvalidCommandException {
        Player player = new Player();
        Map<String, IAction> context = new HashMap<>();
        context.put("go", new GoAction(Map.of("flavour", new DefaultLocation())));
        var expr = interpreter.parse("go flavour town", context);

        var result = expr.interpret(context);

        assertThat(result.execute(player).getMessage().equals("Going to [flavour, town]"));
    }

    @Test
    void argumentInterpretTest() throws InvalidCommandException {
        Player player = new Player();
        Map<String, IAction> context = new HashMap<>();
        var arg = Expression.argument("left");

        var result = arg.interpret(context);

        assertThat(result.execute(player).getMessage().equals("[left]"));
    }

    @Test
    void multipleArgumentParseTest() throws InvalidCommandException {
        Map<String, IAction> context = new HashMap<>();
        var left = Expression.argument("left");
        var right = Expression.argument(left, "right");
        var up = Expression.argument(right, "up");

        var result = up.interpret(context);

        assertThat(
                result.execute(new Player())
                        .getMessage()
                        .equals(List.of("up", "right", "left").toString()));
    }

    @Test
    void invalidCommandTest() {
        Map<String, IAction> context = new HashMap<>();

        assertThrows(
                InvalidCommandException.class, () -> interpreter.parse("i am invalid", context));
    }

    @Test
    void singleInvalidTokenTest() {
        Map<String, IAction> context = new HashMap<>();

        assertThrows(InvalidCommandException.class, () -> interpreter.parse("invalid", context));
    }

    @Test
    void multipleActionTokenTest() {
        Map<String, IAction> context = new HashMap<>();

        assertThrows(
                InvalidCommandException.class,
                () -> interpreter.parse("go go invalid go", context));
        assertThrows(
                InvalidCommandException.class, () -> interpreter.parse("go go go go go", context));
        assertThrows(
                InvalidCommandException.class, () -> interpreter.parse("go go go go", context));
        assertThrows(InvalidCommandException.class, () -> interpreter.parse("go go go", context));
        assertThrows(InvalidCommandException.class, () -> interpreter.parse("go go", context));
    }

    @Test
    void reversedArgumentsTest() {
        Map<String, IAction> context = new HashMap<>();

        assertThrows(InvalidCommandException.class, () -> interpreter.parse("flavour go", context));
    }
}
