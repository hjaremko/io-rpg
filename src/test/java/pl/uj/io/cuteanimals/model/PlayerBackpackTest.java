package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.model.interfaces.IAttributes;

@ExtendWith(MockitoExtension.class)
class PlayerBackpackTest {
    @Mock private IAttributes attrs;
    private PlayerBackpack bp;

    @BeforeEach
    void setUp() {
        bp = new PlayerBackpack(attrs);
    }

    @Test
    void putItem() {
        when(attrs.getAttack()).thenReturn(1);
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
                        ItemType.WEAPON,
                        ItemClass.ANY);

        assertThat(bp.putItem(weapon));
        assertThat(!bp.putItem(heavy));
        assertThat(bp.getItems().size()).isEqualTo(1);
        assertThat(bp.getItems()).contains(weapon);
    }

    @Test
    void removeItem() {
        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);

        assertThat(bp.putItem(weapon));
        assertThat(bp.getItems()).contains(weapon);
        assertThat(bp.removeItem(weapon));
        assertThat(bp.getItems()).isEmpty();
    }

    @Test
    void getCapacity() {
        when(attrs.getAttack()).thenReturn(0);
        assertThat(bp.getCapacity()).isEqualTo(10);

        when(attrs.getAttack()).thenReturn(10);
        assertThat(bp.getCapacity()).isEqualTo(40);
    }
}
