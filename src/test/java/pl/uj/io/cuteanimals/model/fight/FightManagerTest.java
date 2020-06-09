package pl.uj.io.cuteanimals.model.fight;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.*;

@ExtendWith(MockitoExtension.class)
class FightManagerTest {
    @Mock private WorldMap world;
    @InjectMocks private Player player;
    private PlayerAttributes attrs;

    @BeforeEach
    void setUp() {
        attrs = (PlayerAttributes) player.getAttributes();
    }

    @Test
    void endBattleEqualLevelExperienceTest() {
        var dummy = new Monster("dummy", new NPCAttributes(1, 1, 1, 1, 0));

        player.getFightManager().beginFight(dummy);
        player.getFightManager().endBattle();
        assertThat(attrs.getExperience() == 1);
    }

    @Test
    void endBattleMonsterHigherLevelExperienceTest() {
        var dummy = new Monster("dummy", new NPCAttributes(1, 1, 11, 1, 0));

        player.getFightManager().beginFight(dummy);
        player.getFightManager().endBattle();
        assertThat(attrs.getExperience() == 1 + 5);
    }

    @Test
    void endBattleMonsterLowerLevelExperienceTest() {
        player.getAttributes().addLevel(1);
        var dummy = new Monster("dummy", new NPCAttributes(1, 1, 11, 1, 0));

        player.getFightManager().beginFight(dummy);
        player.getFightManager().endBattle();
        assertThat(attrs.getExperience() == 1);
    }
}
