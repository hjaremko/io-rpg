package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.model.entity.Item;

class BackpackTest {
    private Backpack backpack;

    @BeforeEach
    void setUp() {
        backpack = new Backpack();
    }

    @Test
    void putAndRemoveItem() {
        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        var heavy =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        40,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);

        assertThat(backpack.putItem(weapon));
        assertThat(backpack.putItem(heavy));
        assertThat(backpack.getItems().size()).isEqualTo(2);
        assertThat(backpack.getItems()).contains(heavy);
        assertThat(backpack.getItems()).contains(weapon);
        assertThat(backpack.removeItem(weapon));
        assertThat(backpack.getItems().size()).isEqualTo(1);
        assertThat(backpack.removeItem(heavy));
        assertThat(backpack.getItems()).isEmpty();
    }

    @Test
    void showItemsDoesntCrash() {
        assertThat(backpack.showItems()).isNotBlank();
        assertThat(
                backpack.putItem(
                        new Item(
                                1,
                                "pach",
                                "aaa",
                                1,
                                new Attributes(1, 1, 1, 1, 1, 1),
                                ItemType.WEAPON,
                                ItemClass.ANY)));
        assertThat(backpack.showItems()).isNotBlank();
    }
}
