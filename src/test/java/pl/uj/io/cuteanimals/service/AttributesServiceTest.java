package pl.uj.io.cuteanimals.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.repository.AttributesRepository;

@ExtendWith(MockitoExtension.class)
public class AttributesServiceTest {

    @Mock private AttributesRepository attributesRepository;

    @InjectMocks private AttributesService attributesService;

    private Attributes firstAttributes;

    private Attributes secondAttributes;

    @BeforeEach
    private void setup() {
        firstAttributes = new Attributes(1, 10, 20, 30, 40, 50);
        secondAttributes = new Attributes(2, 12, 22, 32, 42, 52);
    }

    @Test
    public void getAllItemsReturnsProperListIfThereAreAnyItems() {
        given(attributesRepository.findAll())
                .willReturn(List.of(firstAttributes, secondAttributes));

        var attributesList = attributesService.getAllAttributes();

        assertThat(attributesList).containsExactlyInAnyOrder(firstAttributes, secondAttributes);
    }

    @Test
    public void getAllItemsReturnsProperEmptyListIfThereAreNoItems() {
        given(attributesRepository.findAll()).willReturn(Collections.emptyList());

        var attributesList = attributesService.getAllAttributes();

        assertThat(attributesList).isEmpty();
    }

    @Test
    public void getItemSucceedAndReturnsProperIFItemExists() {
        given(attributesRepository.findById(1)).willReturn(Optional.ofNullable(firstAttributes));

        var attributes = attributesService.getAttributes(1);

        AssertionsForClassTypes.assertThat(attributes).isEqualTo(firstAttributes);
    }

    @Test
    public void getItemThrowsNotFoundWhenItemDoesntExist() {
        given(attributesRepository.findById(1)).willReturn(Optional.empty());

        assertThatThrownBy(
                        () -> {
                            attributesService.getAttributes(1);
                        })
                .isInstanceOf(ResponseStatusException.class)
                .hasFieldOrPropertyWithValue("status", HttpStatus.NOT_FOUND)
                .hasFieldOrPropertyWithValue("reason", "Unknown attributes with id 1");
    }
}
