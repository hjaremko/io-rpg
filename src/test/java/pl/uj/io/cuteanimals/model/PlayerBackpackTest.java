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
    @Mock private IAttributes attributes;
    private PlayerBackpack playerBackpack;

    @BeforeEach
    void setUp() {
        playerBackpack = new PlayerBackpack(attributes);
    }

    @Test
    void putItem() {
        when(attributes.getAttack()).thenReturn(1);
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

        assertThat(playerBackpack.putItem(weapon));
        assertThat(!playerBackpack.putItem(heavy));
        assertThat(playerBackpack.getItems().size()).isEqualTo(1);
        assertThat(playerBackpack.getItems()).contains(weapon);
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

        assertThat(playerBackpack.putItem(weapon));
        assertThat(playerBackpack.getItems()).contains(weapon);
        assertThat(playerBackpack.removeItem(weapon));
        assertThat(playerBackpack.getItems()).isEmpty();
    }

    @Test
    void getCapacity() {
        when(attributes.getAttack()).thenReturn(0);
        assertThat(playerBackpack.getCapacity()).isEqualTo(10);

        when(attributes.getAttack()).thenReturn(10);
        assertThat(playerBackpack.getCapacity()).isEqualTo(40);
    }
}
