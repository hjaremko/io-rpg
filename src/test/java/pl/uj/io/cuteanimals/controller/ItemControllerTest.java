package pl.uj.io.cuteanimals.controller;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.uj.io.cuteanimals.model.ItemClass;
import pl.uj.io.cuteanimals.model.ItemType;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.service.ItemService;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    private MockMvc mockMvc;

    @Mock private ItemService itemService;

    @InjectMocks private ItemController itemController;

    private Item firstItem;

    private Item secondItem;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();

        firstItem =
                new Item(1, "firstItem", "first of items", 1, null, ItemType.ARMOR, ItemClass.ANY);
        secondItem =
                new Item(
                        2,
                        "secondItem",
                        "second of items",
                        2,
                        null,
                        ItemType.WEAPON,
                        ItemClass.ANY);
    }

    @Test
    public void getItemSucceedAndReturnProperItem() throws Exception {
        given(itemService.getItem(1)).willReturn(firstItem);

        var response =
                mockMvc.perform(get("/items/1").accept(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("first of items");
    }

    @Test
    public void getItemSucceedAndReturnNullIfItemDoesntExist() throws Exception {
        given(itemService.getItem(3)).willReturn(null);

        var response =
                mockMvc.perform(get("/items/3").accept(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    public void getAllItemsSucceedAndReturnProperListOfItems() throws Exception {
        given(itemService.getAllItems()).willReturn(List.of(firstItem, secondItem));

        var response =
                mockMvc.perform(get("/items").accept(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("first of items");
        assertThat(response.getContentAsString()).contains("second of items");
    }

    @Test
    public void getAllItemsSucceedAndReturnEmptyListIfItemsDoesntExist() throws Exception {
        given(itemService.getAllItems()).willReturn(Collections.emptyList());

        var response =
                mockMvc.perform(get("/items").accept(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
