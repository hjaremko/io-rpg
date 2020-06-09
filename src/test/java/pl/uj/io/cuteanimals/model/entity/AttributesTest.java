package pl.uj.io.cuteanimals.model.entity;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttributesTest {

    private Attributes attributes;

    @BeforeEach
    private void setup() {
        attributes = new Attributes(1, 10, 0, 10, 0, 0);
    }

    @Test
    public void toStringReturnsProperString() {
        var result = attributes.toString();
        var expected = "Health: 10. Level: 10. ";

        assertThat(result).isEqualTo(expected);
    }
}
