package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.model.entity.Item;

class BackpackTest {
    private Backpack bp;

    @BeforeEach
    void setUp() {
        bp = new Backpack();
    }

    @Test
    void putAndRemoveItem() {
        var weapon =
                new Item(1, "pach", "aaa", 1, new Attributes(1, 1, 1, 1, 1, 1), ItemType.WEAPON);
        var heavy =
                new Item(1, "pach", "aaa", 40, new Attributes(1, 1, 1, 1, 1, 1), ItemType.ARMOR);

        assertThat(bp.putItem(weapon));
        assertThat(bp.putItem(heavy));
        assertThat(bp.getItems().size()).isEqualTo(2);
        assertThat(bp.getItems()).contains(heavy);
        assertThat(bp.getItems()).contains(weapon);
        assertThat(bp.removeItem(weapon));
        assertThat(bp.getItems().size()).isEqualTo(1);
        assertThat(bp.removeItem(heavy));
        assertThat(bp.getItems()).isEmpty();
    }

    @Test
    void showItemsDoesntCrash() {
        assertThat(bp.showItems()).isNotBlank();
        assertThat(
                bp.putItem(
                        new Item(
                                1,
                                "pach",
                                "aaa",
                                1,
                                new Attributes(1, 1, 1, 1, 1, 1),
                                ItemType.WEAPON)));
        assertThat(bp.showItems()).isNotBlank();
    }
}
