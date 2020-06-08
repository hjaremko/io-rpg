package pl.uj.io.cuteanimals.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.interpreter.Expression;
import pl.uj.io.cuteanimals.interpreter.Interpreter;
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock private Player player;

    @Mock private Interpreter interpreter;

    @Mock private ItemService itemService;

    @InjectMocks private GameService gameService;

    private Expression expression;

    private IAction action;

    @BeforeEach
    private void setup() {
        action =
                new IAction() {
                    @Override
                    public IResult execute(IPlayer player) {
                        return new Result("first result", Color.BOLD);
                    }

                    @Override
                    public List<String> getArgs() {
                        return null;
                    }

                    @Override
                    public void setArgs(List<String> args) {}

                    @Override
                    public List<GameState> getAcceptableStates() {
                        return null;
                    }
                };

        expression = context -> action;
    }

    @Test
    public void executeSucceedAndReturnsProperResult() throws Exception {
        given(interpreter.parse(any(String.class), any(Map.class))).willReturn(expression);

        var result = gameService.execute(1, "aaa");

        assertThat(result.getMessage()).isEqualTo("first result");
        assertThat(result.getColor()).isEqualTo(Color.BOLD);
    }
}
