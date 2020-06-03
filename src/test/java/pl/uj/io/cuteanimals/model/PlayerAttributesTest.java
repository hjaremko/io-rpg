package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PlayerAttributesTest {
    @Test
    void addExperienceShouldLevelUpProperly() {
        var attrs = new PlayerAttributes(new Player());
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
}
