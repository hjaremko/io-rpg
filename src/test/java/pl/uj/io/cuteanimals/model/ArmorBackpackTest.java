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
class ArmorBackpackTest {
    private ArmorBackpack eq;
    @Mock IAttributes attrMock;

    @BeforeEach
    void setUp() {
        eq = new ArmorBackpack(attrMock);
    }

    @Test
    void getEmptyItems() {
        assertThat(eq.getItems().isEmpty());
    }

    @Test
    void putWeapon() {
        when(attrMock.getLevel()).thenReturn(1);

        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);

        assertThat(eq.putItem(weapon));
        assertThat(eq.getItems().size()).isEqualTo(1);
        assertThat(eq.getItems().contains(weapon));
    }

    @Test
    void puttingHigherLeveledItemShouldFail() {
        when(attrMock.getLevel()).thenReturn(1);

        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 100, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        assertThat(!eq.putItem(weapon));
        assertThat(eq.getItems().isEmpty());
    }

    @Test
    void putArmor() {
        when(attrMock.getLevel()).thenReturn(1);

        var armor =
                new Item(
                        1,
                        "arrrmor",
                        "bzzbzz",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        assertThat(eq.putItem(armor));
        assertThat(eq.getItems().size()).isEqualTo(1);
        assertThat(eq.getItems().contains(armor));
    }

    @Test
    void putNotWeaponNotArmorShouldFail() {
        when(attrMock.getLevel()).thenReturn(1);

        var mysteriousItem =
                new Item(
                        1,
                        "orgonite",
                        "fnord",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.NEUTRAL,
                        ItemClass.ANY);
        assertThat(!eq.putItem(mysteriousItem));
        assertThat(eq.getItems().isEmpty());
    }

    @Test
    void onlyOneWeaponAtOnce() {
        when(attrMock.getLevel()).thenReturn(1);

        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        var weapon2 =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        assertThat(eq.putItem(weapon));
        assertThat(!eq.putItem(weapon2));
        assertThat(eq.getItems().size()).isEqualTo(1);
        assertThat(eq.getItems().contains(weapon));
    }

    @Test
    void onlyOneArmorAtOnce() {
        when(attrMock.getLevel()).thenReturn(1);

        var armor =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        var armor2 =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        assertThat(eq.putItem(armor));
        assertThat(!eq.putItem(armor2));
        assertThat(eq.getItems().size()).isEqualTo(1);
        assertThat(eq.getItems().contains(armor));
    }

    @Test
    void removeWeapon() {
        when(attrMock.getLevel()).thenReturn(1);

        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        var armor =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        assertThat(eq.putItem(weapon));
        assertThat(eq.putItem(armor));
        assertThat(eq.getItems().size()).isEqualTo(2);

        assertThat(eq.removeItem(weapon));
        assertThat(!eq.getItems().contains(weapon));
        assertThat(eq.getItems().contains(armor));
        assertThat(eq.getItems().size()).isEqualTo(1);
    }

    @Test
    void removeArmor() {
        when(attrMock.getLevel()).thenReturn(1);

        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        var armor =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        assertThat(eq.putItem(weapon));
        assertThat(eq.putItem(armor));
        assertThat(eq.getItems().size()).isEqualTo(2);

        assertThat(eq.removeItem(armor));
        assertThat(!eq.getItems().contains(armor));
        assertThat(eq.getItems().contains(weapon));
        assertThat(eq.getItems().size()).isEqualTo(1);
    }

    @Test
    void removeNotEquippedShouldFail() {
        when(attrMock.getLevel()).thenReturn(1);

        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);

        var armor =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        assertThat(eq.putItem(weapon));
        assertThat(!eq.removeItem(armor));
        assertThat(eq.getItems().contains(weapon));
        assertThat(!eq.getItems().contains(armor));
        assertThat(eq.getItems().size()).isEqualTo(1);
    }

    @Test
    void showItemsDoesntCrash() {
        when(attrMock.getLevel()).thenReturn(1);

        assertThat(eq.showItems()).isEqualTo("");

        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        var armor =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        assertThat(eq.putItem(weapon));
        assertThat(eq.showItems()).isNotEqualTo("");
        assertThat(eq.putItem(armor));
        assertThat(eq.showItems()).isNotEqualTo("");
    }
}
