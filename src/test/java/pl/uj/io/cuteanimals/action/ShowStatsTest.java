package pl.uj.io.cuteanimals.action;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.entity.Attributes;

@ExtendWith(MockitoExtension.class)
class ShowStatsTest {
    @Mock private Player player;
    private ShowStats showStats;

    @BeforeEach
    void setUp() {
        showStats = new ShowStats();
    }

    @Test
    void actionBodyShouldReturnInfoMessage() {
        when(player.getCurrentGameState()).thenReturn(GameState.EXPLORATION);
        when(player.getAttributes()).thenReturn(new Attributes(1, 1, 1, 1, 1, 1));

        assertThat(showStats.execute(player).getMessage()).isNotBlank();
        var thisVariableIsHereToSilenceFalsePositiveWarningReportedBySpotBugs =
                verify(player).getAttributes();
        assertThat(thisVariableIsHereToSilenceFalsePositiveWarningReportedBySpotBugs)
                .isNull(); // silence spotbugs
    }

    @Test
    void actionShouldBeAvailableOnlyInExploration() {
        assertThat(showStats.getAcceptableStates())
                .isEqualTo(List.of(GameState.EXPLORATION, GameState.FIGHT));
    }
}
