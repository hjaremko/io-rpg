package pl.uj.io.cuteanimals.model.fight;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.NPCAttributes;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.PlayerAttributes;

class FightManagerTest {
    private Player player;
    private PlayerAttributes attrs;

    @BeforeEach
    void setUp() {
        player = new Player();
        attrs = (PlayerAttributes) player.getAttributes();
    }

    @Test
    void endBattleEqualLevelExperienceTest() {
        var dummy = new Monster("dummy", new NPCAttributes(1, 1, 1, 1));

        player.getFightManager().beginFight(dummy);
        player.getFightManager().endBattle();
        assertThat(attrs.getExperience() == 1);
    }

    @Test
    void endBattleMonsterHigherLevelExperienceTest() {
        var dummy = new Monster("dummy", new NPCAttributes(1, 1, 11, 1));

        player.getFightManager().beginFight(dummy);
        player.getFightManager().endBattle();
        assertThat(attrs.getExperience() == 1 + 5);
    }

    @Test
    void endBattleMonsterLowerLevelExperienceTest() {
        player.getAttributes().addLevel(1);
        var dummy = new Monster("dummy", new NPCAttributes(1, 1, 11, 1));

        player.getFightManager().beginFight(dummy);
        player.getFightManager().endBattle();
        assertThat(attrs.getExperience() == 1);
    }
}
