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
import pl.uj.io.cuteanimals.model.PlayerBackpack;

@ExtendWith(MockitoExtension.class)
class ShowBackpackTest {
    @Mock private Player player;
    private ShowBackpack showBackpack;

    @BeforeEach
    void setUp() {
        showBackpack = new ShowBackpack();
    }

    @Test
    void actionBodyShouldReturnInfoMessage() {
        when(player.getCurrentGameState()).thenReturn(GameState.EXPLORATION);
        var playerBackpack = mock(PlayerBackpack.class);
        when(player.getEquipment()).thenReturn(playerBackpack);

        assertThat(showBackpack.execute(player).getMessage()).isNotBlank();
        var thisVariableIsHereToSilenceFalsePositiveWarningReportedBySpotBugs =
                verify(playerBackpack).showItems();
        var thisOneToo = verify(playerBackpack).getRemainingCapacity();
        assertThat(thisVariableIsHereToSilenceFalsePositiveWarningReportedBySpotBugs)
                .isNull(); // silence spotbugs
        assertThat(thisOneToo).isNotNull(); // silence spotbugs
    }

    @Test
    void actionShouldBeAvailableOnlyInExplorationAndFight() {
        assertThat(showBackpack.getAcceptableStates())
                .isEqualTo(List.of(GameState.EXPLORATION, GameState.FIGHT));
    }
}
