package pl.uj.io.cuteanimals.model.interfaces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.RandomIntegerImpl;

class RandomIntegerTest {

    @Test
    void nextInt() {
        var random = new RandomIntegerImpl();
        assertThat(random.nextInt(10)).isBetween(0, 10);
    }
}
