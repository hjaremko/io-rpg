package pl.uj.io.cuteanimals.model;

import java.util.Random;
import pl.uj.io.cuteanimals.model.interfaces.RandomInteger;

public class RandomIntegerImpl implements RandomInteger {
    private final Random random = new Random();

    public int nextInt(int max) {
        return random.nextInt(max);
    }
}
