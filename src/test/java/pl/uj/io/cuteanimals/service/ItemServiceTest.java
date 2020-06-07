package pl.uj.io.cuteanimals.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.ItemType;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.repository.ItemsRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemsRepository itemsRepository;

    @InjectMocks
    private ItemService itemService;

    private Item firstItem;

    private Item secondItem;

    @BeforeEach
    private void setup() {
        firstItem = new Item(1, "firstItem", "first of items",
                1 ,null, ItemType.ARMOR);
        secondItem = new Item(2, "secondItem", "second of items",
                2 ,null, ItemType.WEAPON);
    }

    @Test
    public void getAllItemsReturnsProperListIfThereAreAnyItems() {
        given(itemsRepository.findAll()).willReturn(List.of(firstItem, secondItem));

        var items = itemsRepository.findAll();

        assertThat(items).containsExactlyInAnyOrder(firstItem, secondItem);
    }

    @Test
    public void getAllItemsReturnsProperEmptyListIfThereAreNoItems() {
        given(itemsRepository.findAll()).willReturn(Collections.emptyList());

        var items = itemsRepository.findAll();

        assertThat(items).isEmpty();
    }

}
