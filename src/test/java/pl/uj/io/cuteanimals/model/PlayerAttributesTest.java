package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.interfaces.RandomInteger;

@ExtendWith(MockitoExtension.class)
class PlayerAttributesTest {
    @Mock private RandomInteger random;
    private PlayerAttributes attrs;

    @BeforeEach
    void setUp() {
        attrs = new PlayerAttributes(new Player(), random);
    }

    @Test
    void playerSpawnsWithLevel1() {
        assertThat(attrs.getLevel()).isEqualTo(1);
    }

    @Test
    void addExperienceShouldLevelUpProperly() {
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 0);

        attrs.addExperience(0);
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 0);

        attrs.addExperience(50);
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 50);

        attrs.addExperience(49);
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 50);

        attrs.addExperience(1);
        assertThat(attrs.getLevel() == 2);
        assertThat(attrs.getExperience() == 0);

        attrs.addExperience(250);
        assertThat(attrs.getLevel() == 2);
        assertThat(attrs.getExperience() == 50);
    }

    @Test
    void getDamageIsCalculatedCorrectly() {
        when(random.nextInt(anyInt())).thenReturn(1);
        assertThat(attrs.getDamage()).isEqualTo(1);

        when(random.nextInt(anyInt())).thenReturn(25);
        attrs.addAttack(49);
        assertThat(attrs.getDamage()).isEqualTo(75);
    }
}
