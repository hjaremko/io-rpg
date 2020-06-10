package pl.uj.io.cuteanimals.action;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.action.ability.Focus;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.interfaces.IAction;

@ExtendWith(MockitoExtension.class)
class ShowAbilitiesTest {
    @Mock private Player player;
    private ShowAbilities showAbilities;

    @BeforeEach
    void setUp() {
        showAbilities = new ShowAbilities();
    }

    @Test
    void actionBodyShouldReturnInfoMessage() {
        var skills = new HashMap<String, IAction>();
        skills.put("focus", new Focus());
        when(player.getCurrentGameState()).thenReturn(GameState.EXPLORATION);
        when(player.getAbilities()).thenReturn(skills);

        var out = showAbilities.execute(player).getMessage();
        assertThat(out).isNotBlank();
        assertThat(out).contains("focus");
    }

    @Test
    void actionShouldBeAvailableOnlyInExplorationAndFight() {
        assertThat(showAbilities.getAcceptableStates())
                .isEqualTo(List.of(GameState.EXPLORATION, GameState.FIGHT));
    }
}
