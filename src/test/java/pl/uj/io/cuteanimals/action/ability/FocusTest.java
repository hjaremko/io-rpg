package pl.uj.io.cuteanimals.action.ability;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.NPCAttributes;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.PlayerAttributes;
import pl.uj.io.cuteanimals.model.interfaces.RandomInteger;

@ExtendWith(MockitoExtension.class)
class FocusTest {
    @Mock private RandomInteger random;
    private Player player;
    private Monster dummy;
    private PlayerAttributes attrs;

    @BeforeEach
    void setUp() {
        player = new Player(random);
        attrs = (PlayerAttributes) player.getAttributes();

        dummy = new Monster("dummy", new NPCAttributes(0, 1, 1, 0, 0));
        player.getFightManager().beginFight(dummy);
        var focus = new Focus();
        focus.execute(player);
    }

    @Test
    void shouldDrain20Mana() {
        var manaBefore = attrs.getMana();
        player.getFightManager().attack();
        assertThat(attrs.getMana()).isEqualTo(manaBefore - 20);
    }

    @Test
    void atLeast20ManaNeeded() {
        attrs.addMana(-200);
        var result = (new Focus()).execute(player);
        assertThat(result.getMessage())
                .isEqualTo("* Not enough mana! You need at least 20 mana to use this ability.");
    }

    @Test
    void attackShouldInflictAdditionalDamageLevelOneTest() {
        player.getFightManager().attack();
        assertThat(dummy.getAttributes().getHealth()).isEqualTo(-3);
    }

    @Test
    void attackShouldInflictAdditionalDamageLevel100Test() {
        when(random.nextInt(anyInt())).thenReturn(25);

        attrs.addAttack(49);
        player.getFightManager().attack();
        assertThat(dummy.getAttributes().getHealth()).isEqualTo(-175);
    }
}
