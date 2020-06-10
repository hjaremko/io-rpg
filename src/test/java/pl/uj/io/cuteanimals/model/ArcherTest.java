package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArcherTest {
    private Archer m;

    @BeforeEach
    void setUp() {
        m = new Archer();
    }

    @Test
    void testToString() {
        assertThat(m.toString()).isEqualTo("Archer");
    }

    @Test
    void getAbilities() {
        assertThat(m.getAbilities()).isNotEmpty();
        assertThat(m.getAbilities()).containsKeys("focus");
        assertThat(m.getAbilities()).containsKeys("bullseye");
    }
}
