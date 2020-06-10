package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultTest {
    private Result result;

    @BeforeEach
    void setUp() {
        result = new Result("i am result");
    }

    @Test
    void getMessage() {
        assertThat(result.getMessage()).isEqualTo("i am result");
        assertThat(result.getColor()).isEqualTo(Color.NORMAL);
    }

    @Test
    void setMessage() {
        result.setMessage("oj oj");
        assertThat(result.getMessage()).isEqualTo("oj oj");
        assertThat(result.getColor()).isEqualTo(Color.NORMAL);
    }

    @Test
    void testToString() {
        assertThat(result.toString()).isEqualTo(result.getMessage());
    }

    @Test
    void getColor() {
        result = new Result("aa", Color.YELLOW);
        assertThat(result.getMessage()).isEqualTo("aa");
        assertThat(result.getColor()).isEqualTo(Color.YELLOW);
    }
}
