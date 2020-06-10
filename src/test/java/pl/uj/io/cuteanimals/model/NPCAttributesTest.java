package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NPCAttributesTest {
    private NPCAttributes attrs;

    @BeforeEach
    void setUp() {
        attrs = new NPCAttributes(1, 2, 3, 4, 5);
    }

    @Test
    void getHealth() {
        assertThat(attrs.getHealth()).isEqualTo(1);
    }

    @Test
    void getAttack() {
        assertThat(attrs.getAttack()).isEqualTo(2);
    }

    @Test
    void getLevel() {
        assertThat(attrs.getLevel()).isEqualTo(3);
    }

    @Test
    void getDefence() {
        assertThat(attrs.getDefence()).isEqualTo(4);
    }

    @Test
    void getMana() {
        assertThat(attrs.getMana()).isEqualTo(5);
    }

    @Test
    void addHealth() {
        attrs.addHealth(200);
        assertThat(attrs.getHealth()).isEqualTo(201);
        attrs.addHealth(-100);
        assertThat(attrs.getHealth()).isEqualTo(101);
    }

    @Test
    void addAttack() {
        attrs.addAttack(200);
        assertThat(attrs.getAttack()).isEqualTo(202);
        attrs.addAttack(-100);
        assertThat(attrs.getAttack()).isEqualTo(102);
    }

    @Test
    void addLevel() {
        attrs.addLevel(200);
        assertThat(attrs.getLevel()).isEqualTo(203);
        attrs.addLevel(-100);
        assertThat(attrs.getLevel()).isEqualTo(103);
    }

    @Test
    void addDefence() {
        attrs.addDefence(200);
        assertThat(attrs.getDefence()).isEqualTo(204);
        attrs.addDefence(-100);
        assertThat(attrs.getDefence()).isEqualTo(104);
    }

    @Test
    void addMana() {
        attrs.addMana(200);
        assertThat(attrs.getMana()).isEqualTo(205);
        attrs.addMana(-100);
        assertThat(attrs.getMana()).isEqualTo(105);
    }
}
