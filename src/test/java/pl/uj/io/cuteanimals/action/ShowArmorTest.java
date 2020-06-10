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
class ShowArmorTest {
    @Mock private Player player;
    private ShowArmor showArmor;

    @BeforeEach
    void setUp() {
        showArmor = new ShowArmor();
    }

    @Test
    void actionBodyShouldReturnInfoMessage() {
        var playerBackpack = mock(PlayerBackpack.class);
        when(player.getCurrentGameState()).thenReturn(GameState.EXPLORATION);
        when(player.getArmor()).thenReturn(playerBackpack);
        showArmor.execute(player);
        var thisVariableIsHereToSilenceFalsePositiveWarningReportedBySpotBugs =
                verify(playerBackpack).showItems();
        assertThat(thisVariableIsHereToSilenceFalsePositiveWarningReportedBySpotBugs)
                .isNull(); // silence spotbugs
    }

    @Test
    void actionShouldBeAvailableOnlyInExploration() {
        assertThat(showArmor.getAcceptableStates())
                .isEqualTo(List.of(GameState.EXPLORATION, GameState.FIGHT));
    }
}
