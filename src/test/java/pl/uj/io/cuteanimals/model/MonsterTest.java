package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.entity.Attributes;

// Sweet 100% coverage
class MonsterTest {
    private Monster mob;

    @BeforeEach
    void setUp() {
        mob = new Monster("mob", new Attributes(1, 1, 1, 1, 1, 1));
    }

    @Test
    void getEquipment() {
        assertThat(mob.getEquipment()).isNotNull();
    }

    @Test
    void getArmor() {
        assertThat(mob.getArmor()).isNull();
    }
}
