package pl.uj.io.cuteanimals.model.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface RandomInteger {
    int nextInt(int max);
}
