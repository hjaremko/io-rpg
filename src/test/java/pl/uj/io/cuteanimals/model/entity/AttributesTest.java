package pl.uj.io.cuteanimals.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.w3c.dom.Attr;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class AttributesTest {

    private Attributes attributes;

    @BeforeEach
    private void setup() {
        attributes = new Attributes(1, 10, 0, 10, 0);
    }

    @Test
    public void toStringReturnsProperString() {
        var result = attributes.toString();
        var expected = "Health: 10. Level: 10. ";

        assertThat(result).isEqualTo(expected);
    }
}
