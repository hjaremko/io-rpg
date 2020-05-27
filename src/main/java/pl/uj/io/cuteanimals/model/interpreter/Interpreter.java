package pl.uj.io.cuteanimals.model.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.uj.io.cuteanimals.model.exceptions.InvalidCommandException;

/**
 * Interprets provided command according to grammar
 *
 * <p>expr ::= action args | action <br>
 * action ::= `str` args <br>
 * args ::= args `str` | `str`
 *
 * <p>Builds corresponding IAction object.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class Interpreter {

    private static final Logger logger = LoggerFactory.getLogger(Interpreter.class);

    /**
     * Forms Expression object from given token and put it on the stack.
     *
     * @param token string representation of a token
     * @param stack parse stack
     * @return Expression from it is possible to derive tokens on the stack
     */
    private static ParseToken parseToken(String token, Deque<ParseToken> stack) {
        logger.debug("Parsing token: " + token);

        switch (token) {
                // TODO: new actions go here, remember to provide corresponding context object
            case "go":
            case "investigate":
                // case "...":
                return parseActionToken(token, stack);
            default:
                return parseArgumentToken(token, stack);
        }
    }

    /**
     * Parses action token.
     *
     * @param token action token
     * @param stack parse stack
     * @return Expression and Type from it is possible to derive given token
     */
    private static ParseToken parseActionToken(String token, Deque<ParseToken> stack) {
        logger.debug("Parsing action token: " + token);

        // No argument on stack -> build action with no arguments Expression
        // (rule expr -> action)
        if (stack.isEmpty()) {
            return new ParseToken(Expression.action(token), ParseToken.Type.Action);
        }

        // Pop arguments
        var arg = stack.pop();

        // Multiple actions on stack, no production for that
        // Should result in parsing error.
        if (!arg.getType().equals(ParseToken.Type.Argument)) {
            return null;
        }

        // Production expr -> action args
        return new ParseToken(
                Expression.expr(Expression.action(token), arg.getExpr()), ParseToken.Type.Start);
    }

    /**
     * Parses argument token.
     *
     * @param token argument token
     * @param stack parse stack
     * @return Expression and Type from it is possible to derive given token
     */
    private static ParseToken parseArgumentToken(String token, Deque<ParseToken> stack) {
        logger.debug("Parsing argument token: " + token);

        // Production args -> 'str'
        if (stack.isEmpty()) {
            return new ParseToken(Expression.argument(token), ParseToken.Type.Argument);
        }

        var arg = stack.pop();

        // We accept only args -> args 'str'.
        // Should result in parsing error.
        if (!(arg.getType().equals(ParseToken.Type.Argument))) {
            return null;
        }

        // Production args -> args 'str'
        return new ParseToken(Expression.argument(arg.getExpr(), token), ParseToken.Type.Argument);
    }

    /**
     * Parses input string (user provided).
     *
     * @param expression Command to tokenize
     * @return Provided command in form of Expression object. Needs to be interpreted using
     *     interpret method.
     * @throws InvalidCommandException - If parsing does not end on starting symbol then the input
     *     is invalid.
     */
    public static Expression parse(String expression) throws InvalidCommandException {
        logger.debug("Parsing expression: " + expression);

        Deque<ParseToken> stack = new ArrayDeque<>();

        // Tokenize string (split by whitespace)
        var tokens = expression.split(" ");

        // Parse tokens in reverse order
        int num = tokens.length - 1;
        IntStream.rangeClosed(0, num)
                .mapToObj(i -> tokens[num - i])
                .forEach(
                        token -> {
                            var parsed = parseToken(token, stack);
                            if (parsed != null) {
                                stack.push(parsed);
                            }
                        });

        // On the stack should be left only one object corresponding to start symbol of out grammar.

        // Empty stack means invalid command was provided.
        if (stack.isEmpty()) {
            throw new InvalidCommandException("Invalid expression: " + expression);
        }

        var symbol = stack.pop();

        // More than one symbol on stack means invalid command was provided.
        // Symbol which is not start symbol means invalid command was provided.
        // Single Action expression is ok.
        if (!(symbol.getType().equals(ParseToken.Type.Start)
                        || symbol.getType().equals(ParseToken.Type.Action))
                && stack.isEmpty()) {
            throw new InvalidCommandException("Invalid expression: " + expression);
        }

        return symbol.expr;
    }

    /**
     * Helper class. Since Expression is a functional interface we can't include this information in
     * instances.
     */
    private static class ParseToken {
        private final Expression expr;
        private final Type type;

        public ParseToken(Expression expr, Type type) {
            this.expr = expr;
            this.type = type;
        }

        public Expression getExpr() {
            return expr;
        }

        public Type getType() {
            return type;
        }

        enum Type {
            Start,
            Action,
            Argument,
        }
    }
}
