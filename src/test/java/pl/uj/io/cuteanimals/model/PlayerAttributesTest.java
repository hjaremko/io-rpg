package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.model.interfaces.RandomInteger;

@ExtendWith(MockitoExtension.class)
class PlayerAttributesTest {
    @Mock private RandomInteger random;
    @Mock private WorldMap world;
    private Player player;
    private PlayerAttributes attrs;

    @BeforeEach
    void setUp() {
        player = new Player(0, world, random);
        attrs = (PlayerAttributes) player.getAttributes();
    }

    @Test
    void playerSpawnsWithLevel1() {
        assertThat(attrs.getLevel()).isEqualTo(1);
    }

    @Test
    void addExperienceShouldLevelUpProperly() {
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 0);

        attrs.addExperience(0);
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 0);

        attrs.addExperience(50);
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 50);

        attrs.addExperience(49);
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getExperience() == 50);

        attrs.addExperience(1);
        assertThat(attrs.getLevel() == 2);
        assertThat(attrs.getExperience() == 0);

        attrs.addExperience(250);
        assertThat(attrs.getLevel() == 2);
        assertThat(attrs.getExperience() == 50);
    }

    @Test
    void getDamageIsCalculatedCorrectly() {
        when(random.nextInt(anyInt())).thenReturn(1);
        assertThat(attrs.getDamage()).isEqualTo(1);

        when(random.nextInt(anyInt())).thenReturn(25);
        attrs.addAttack(49);
        assertThat(attrs.getDamage()).isEqualTo(75);
    }

    @Test
    void getHealthWithItem() {
        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 100, 0, 0, 0, 0),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        player.getArmor().putItem(weapon);
        assertThat(attrs.getHealth()).isEqualTo(150);
        player.getArmor().removeItem(weapon);
        assertThat(attrs.getHealth()).isEqualTo(50);
    }

    @Test
    void getAttackWithItem() {
        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 0, 100, 0, 0, 0),
                        ItemType.ARMOR,
                        ItemClass.ANY);
        player.getArmor().putItem(weapon);
        assertThat(attrs.getAttack()).isEqualTo(101);
        player.getArmor().removeItem(weapon);
        assertThat(attrs.getAttack()).isEqualTo(1);
    }

    @Test
    void getDefenceWithItem() {
        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 0, 0, 0, 100, 0),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        player.getArmor().putItem(weapon);
        assertThat(attrs.getDefence()).isEqualTo(100);
        player.getArmor().removeItem(weapon);
        assertThat(attrs.getDefence()).isEqualTo(0);
    }

    @Test
    void getManaWithItem() {
        var weapon =
                new Item(
                        1,
                        "pach",
                        "aaa",
                        1,
                        new Attributes(1, 0, 0, 0, 0, 100),
                        ItemType.WEAPON,
                        ItemClass.ANY);
        player.getArmor().putItem(weapon);
        assertThat(attrs.getMana()).isEqualTo(200);
        player.getArmor().removeItem(weapon);
        assertThat(attrs.getMana()).isEqualTo(100);
    }

    @Test
    void getRequiredExperience() {
        assertThat(attrs.getLevel() == 1);
        assertThat(attrs.getRequiredExperience() == 2);
        attrs.addLevel(1);
        assertThat(attrs.getRequiredExperience() == 4);
    }

    @Test
    void getMana() {
        assertThat(attrs.getMana()).isEqualTo(100);
    }

    @Test
    void addMana() {
        attrs.addMana(100);
        assertThat(attrs.getMana()).isEqualTo(200);
        attrs.addMana(-100);
        assertThat(attrs.getMana()).isEqualTo(100);
    }

    @Test
    void testToString() {
        assertThat(attrs.toString()).isNotBlank();
    }
}
