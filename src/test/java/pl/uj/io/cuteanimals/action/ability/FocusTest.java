package pl.uj.io.cuteanimals.action.ability;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.NPCAttributes;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.PlayerAttributes;

class FocusTest {
    private Player player;
    private Monster dummy;
    private PlayerAttributes attrs;

    @BeforeEach
    void setUp() {
        player = new Player();
        attrs = (PlayerAttributes) player.getAttributes();
        player.getAttributes().addLevel(4);

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
    void attackShouldInflictAdditionalDamage() {
        player.getFightManager().attack();
        var playerAttack = player.getAttributes().getAttack();
        var playerDamage = attrs.getDamage();
        var expected = -(playerDamage + 2 * playerAttack);
        // getDamage is randomized
        assertThat(dummy.getAttributes().getHealth()).isBetween(expected - 1, expected);
    }
}
