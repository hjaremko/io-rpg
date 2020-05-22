package pl.uj.io.cuteanimals.model.interpreter;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.action.ActionBuilder;
import pl.uj.io.cuteanimals.model.action.MessageAction;
import pl.uj.io.cuteanimals.model.interfaces.IAction;

/**
 * Implements formal grammar <br>
 * expr ::= action args | action <br>
 * action ::= `str` args <br>
 * args ::= args `str` | `str`
 */
@FunctionalInterface
public interface Expression {

    /** expr -> action -> 'str' */
    static Expression action(String name) {
        return context -> context.get(name);
    }

    /** args -> 'str' */
    static Expression argument(String arg) {
        return context ->
                context.getOrDefault(
                        arg,
                        new ActionBuilder()
                                .addAction(new MessageAction())
                                .addArgs(List.of(arg))
                                .collect());
    }

    /** args -> args 'str' */
    static Expression argument(Expression arg1, String arg2) {
        return context ->
                new ActionBuilder()
                        .addAction(arg1.interpret(context))
                        .addArgs((List.of(arg2)))
                        .collect();
    }

    /** expr -> action args */
    static Expression expr(Expression action, Expression args) {
        return context ->
                new ActionBuilder()
                        .addAction(action.interpret(context))
                        .addArgs(args.interpret(context).getArgs())
                        .collect();
    }

    /**
     * @param context Maps action strings to their IAction equivalent
     * @return IAction corresponding to given parsed text
     */
    IAction interpret(Map<String, IAction> context);
}
