package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SlaveTest {
    private Slave slave;

    @BeforeEach
    void setUp() {
        slave = new Slave();
    }

    @Test
    void getAbilities() {
        assertThat(slave.getAbilities()).isNotEmpty();
        assertThat(slave.getAbilities()).containsKeys("focus");
    }
}
