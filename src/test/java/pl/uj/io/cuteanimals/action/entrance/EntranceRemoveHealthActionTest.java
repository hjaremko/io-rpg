package pl.uj.io.cuteanimals.action.entrance;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.WorldMap;

@ExtendWith(MockitoExtension.class)
public class EntranceRemoveHealthActionTest {
    private int healthLoss;
    private EntranceRemoveHealthAction entranceRemoveHealthAction;
    @Mock WorldMap world;
    @InjectMocks private Player player;

    @BeforeEach
    private void setup() {
        healthLoss = 15;
        entranceRemoveHealthAction = new EntranceRemoveHealthAction(healthLoss);
    }

    @Test
    public void executeProperlyReducePlayersHealthAndReturnProperMessage() {
        var defaultHealth = player.getAttributes().getHealth();
        var result = entranceRemoveHealthAction.execute(player);

        assertThat(result.getMessage()).isEqualTo("(You lose " + healthLoss + " health points).");
        assertThat(player.getAttributes().getHealth()).isEqualTo(defaultHealth - healthLoss);
    }
}
