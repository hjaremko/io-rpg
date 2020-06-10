package pl.uj.io.cuteanimals.action;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Player;

@ExtendWith(MockitoExtension.class)
class InvestigateActionTest {
    @Mock private Player player;
    private InvestigateAction investigateAction;

    @BeforeEach
    void setUp() {
        investigateAction = new InvestigateAction("Message");
    }

    @Test
    void actionBodyShouldReturnInfoMessage() {
        when(player.getCurrentGameState()).thenReturn(GameState.EXPLORATION);
        assertThat(investigateAction.execute(player).getMessage()).isEqualTo("Message");
    }

    @Test
    void actionShouldBeAvailableOnlyInExploration() {
        assertThat(investigateAction.getAcceptableStates())
                .isEqualTo(List.of(GameState.EXPLORATION));
    }
}
