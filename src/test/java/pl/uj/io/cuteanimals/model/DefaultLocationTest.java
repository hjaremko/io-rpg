package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;

@ExtendWith(MockitoExtension.class)
class DefaultLocationTest {
    @Mock IAction action;
    @Mock IPlayer player;
    private DefaultLocation defaultLocation;

    @BeforeEach
    void setUp() {
        defaultLocation = new DefaultLocation();
        defaultLocation.setDescription("desc");
    }

    @Test
    void getDescription() {
        assertThat(defaultLocation.getDescription()).isEqualTo("desc");
    }

    @Test
    void getAvailableActions() {}

    @Test
    void setAvailableActions() {}

    @Test
    void getNPCs() {
        var claudius =
                new NPC(
                        null,
                        null,
                        "Claudius",
                        List.of("If I were you I would run as fast as I can..."));
        var mag1 = new NPC(null, null, "Herschel", List.of("Please... help us!"));

        defaultLocation.setNPCs(List.of(claudius, mag1));
        assertThat(defaultLocation.getNPCs()).contains(mag1);
        assertThat(defaultLocation.getNPCs()).contains(claudius);
    }

    @Test
    void getItems() {
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
                        2,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 1, 1, 1, 1, 1),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        var backpack = new Backpack();
        backpack.putItem(armor2);
        backpack.putItem(armor);
        defaultLocation.setItems(backpack);
        assertThat(defaultLocation.getItems().getItems()).contains(armor);
        assertThat(defaultLocation.getItems().getItems()).contains(armor2);
    }

    @Test
    void actionOnEnter() {
        defaultLocation.setActionOnEnter(action);
        assertThat(defaultLocation.getActionOnEnter()).isEqualTo(action);
        defaultLocation.onEnter(player);
        verify(action).execute(player);
    }

    @Test
    void nullActionOnEnter() {
        assertThat(defaultLocation.onEnter(player).getMessage()).isBlank();
    }
}
