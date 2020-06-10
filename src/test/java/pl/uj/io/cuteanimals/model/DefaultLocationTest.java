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
    @Mock IAction ac;
    @Mock IPlayer p;
    private DefaultLocation loc;

    @BeforeEach
    void setUp() {
        loc = new DefaultLocation();
        loc.setDescription("desc");
    }

    @Test
    void getDescription() {
        assertThat(loc.getDescription()).isEqualTo("desc");
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

        loc.setNPCs(List.of(claudius, mag1));
        assertThat(loc.getNPCs()).contains(mag1);
        assertThat(loc.getNPCs()).contains(claudius);
    }

    @Test
    void getItems() {
        var armor = new Item(1, "pach", "aaa", 1, new Attributes(1, 1, 1, 1, 1, 1), ItemType.ARMOR);
        var armor2 =
                new Item(2, "pach", "aaa", 1, new Attributes(1, 1, 1, 1, 1, 1), ItemType.ARMOR);
        var bp = new Backpack();
        bp.putItem(armor2);
        bp.putItem(armor);
        loc.setItems(bp);
        assertThat(loc.getItems().getItems()).contains(armor);
        assertThat(loc.getItems().getItems()).contains(armor2);
    }

    @Test
    void actionOnEnter() {
        loc.setActionOnEnter(ac);
        assertThat(loc.getActionOnEnter()).isEqualTo(ac);
        loc.onEnter(p);
        verify(ac).execute(p);
    }

    @Test
    void nullActionOnEnter() {
        assertThat(loc.onEnter(p).getMessage()).isBlank();
    }
}
