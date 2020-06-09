package pl.uj.io.cuteanimals.model;

import java.util.Random;
import org.springframework.stereotype.Component;
import pl.uj.io.cuteanimals.model.interfaces.RandomInteger;

@Component
public class RandomIntegerImpl implements RandomInteger {
    private final Random random = new Random();

    public int nextInt(int max) {
        return random.nextInt(max);
    }
}
