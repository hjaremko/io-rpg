package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

@ExtendWith(MockitoExtension.class)
class PlayerTest {
    @Mock private WorldMap world;
    Player player;
    private PlayerAttributes attrs;
    private ILocation town;

    @BeforeEach
    void setUp() {
        player = new Player(0, world);
        town = new DefaultLocation();
        attrs = (PlayerAttributes) player.getAttributes();
        player.setGameState(GameState.EXPLORATION);
    }

    @Test
    void getEquipment() {
        assertThat(player.getEquipment().getItems()).isEmpty();
    }

    @Test
    void getArmor() {
        assertThat(player.getArmor().getItems()).isEmpty();
    }

    @Test
    void useShouldAddAttributesPermanentlyNoFight() {
        when(world.getLocation(anyString())).thenReturn(town);
        var statsBefore = new Player(0, world).getAttributes();
        var item =
                new Item(
                        1,
                        "it",
                        "desc",
                        1,
                        new Attributes(1, 100, 100, 100, 100, 100),
                        ItemType.USABLE);
        player.use(item);

        assertThat(attrs.getHealth())
                .isEqualTo(statsBefore.getHealth() + item.getAttributes().getHealth());
        assertThat(attrs.getAttack())
                .isEqualTo(statsBefore.getAttack() + item.getAttributes().getAttack());
        assertThat(attrs.getLevel()).isEqualTo(statsBefore.getLevel());
        assertThat(attrs.getDefence())
                .isEqualTo(statsBefore.getDefence() + item.getAttributes().getDefence());
        assertThat(attrs.getMana())
                .isEqualTo(statsBefore.getMana() + item.getAttributes().getMana());
    }

    @Test
    void unusableItemsShouldDoNothing() {
        when(world.getLocation(anyString())).thenReturn(town);
        var statsBefore = new Player(0, world).getAttributes();
        var item =
                new Item(
                        1,
                        "it",
                        "desc",
                        1,
                        new Attributes(1, 100, 100, 100, 100, 100),
                        ItemType.NEUTRAL);
        player.use(item);

        assertThat(attrs.getHealth()).isEqualTo(statsBefore.getHealth());
        assertThat(attrs.getAttack()).isEqualTo(statsBefore.getAttack());
        assertThat(attrs.getLevel()).isEqualTo(statsBefore.getLevel());
        assertThat(attrs.getDefence()).isEqualTo(statsBefore.getDefence());
        assertThat(attrs.getMana()).isEqualTo(statsBefore.getMana());
    }

    @Test
    void changeLocation() {
        when(world.getLocation(anyString())).thenReturn(town);
        player = new Player(0, world);

        var inn = spy(new DefaultLocation());
        assertThat(player.getCurrentLocation()).isEqualTo(town);
        player.changeLocation(inn);
        verify(inn).onEnter(player);
    }

    @Test
    void armorShouldBlockDamage() {
        var beforeAttack = attrs.getHealth();
        attrs.addDefence(100);
        player.takeDamage(100);
        assertThat(attrs.getHealth()).isEqualTo(beforeAttack - 50);
    }

    @Test
    void getWorld() {
        assertThat(player.getWorld()).isNotNull();
    }

    @Test
    void setClass() {
        player.setClass(new Monk());
        assertThat(player.getAbilities()).containsKeys("heal");
    }
}
