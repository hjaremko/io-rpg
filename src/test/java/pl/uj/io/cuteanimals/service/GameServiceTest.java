package pl.uj.io.cuteanimals.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.interpreter.Interpreter;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private Player player;

    @Mock
    private Interpreter interpreter;

    @InjectMocks
    private GameService gameService;

    private IAction action = new IAction() {
        @Override
        public IResult execute(IPlayer player) {
            return null;
        }

        @Override
        public List<String> getArgs() {
            return null;
        }

        @Override
        public void setArgs(List<String> args) {

        }

        @Override
        public List<GameState> getAcceptableStates() {
            return null;
        }
    };

    private

    @Test
    public void executeSucceedAndReturnsProperResult() {
        given(player.getCurrentLocation().getAvailableActions()).willReturn(
                Map.of("a", new IAction() {
                            @Override
                            public IResult execute(IPlayer player) {
                                return null;
                            }

                            @Override
                            public List<String> getArgs() {
                                return null;
                            }

                            @Override
                            public void setArgs(List<String> args) {

                            }

                            @Override
                            public List<GameState> getAcceptableStates() {
                                return null;
                            }
                        }
//        given(interpreter.parse())
    }
}
