package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarriorTest {
    private Warrior m;

    @BeforeEach
    void setUp() {
        m = new Warrior();
    }

    @Test
    void testToString() {
        assertThat(m.toString()).isEqualTo("Warrior");
    }

    @Test
    void getAbilities() {
        assertThat(m.getAbilities()).isNotEmpty();
        assertThat(m.getAbilities()).containsKeys("focus");
        assertThat(m.getAbilities()).containsKeys("double down");
    }
}
