package pl.uj.io.cuteanimals.action.entrance;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.Player;

public class EntranceRemoveHealthActionTest {

    private int healthLoss;

    private EntranceRemoveHealthAction entranceRemoveHealthAction;

    private Player player;

    @BeforeEach
    private void setup() {
        healthLoss = 15;
        entranceRemoveHealthAction = new EntranceRemoveHealthAction(healthLoss);
        player = new Player();
    }

    @Test
    public void executeProperlyReducePlayersHealthAndReturnProperMessage() {
        var defaultHealth = player.getAttributes().getHealth();
        var result = entranceRemoveHealthAction.execute(player);

        assertThat(result.getMessage()).isEqualTo("(You lose " + healthLoss + " health points).");
        assertThat(player.getAttributes().getHealth()).isEqualTo(defaultHealth - healthLoss);
    }
}
