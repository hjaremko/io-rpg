package pl.uj.io.cuteanimals.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NPCTest {
    @Mock private ArmorBackpack abp;
    @Mock private Backpack bp;
    private NPC npc;

    @BeforeEach
    void setUp() {
        npc = new NPC(abp, bp, "test", List.of("t1", "t2", "t3"));
    }

    @Test
    void getQuote() {
        assertThat(npc.getQuote()).isEqualTo("t1");
        assertThat(npc.getQuote()).isEqualTo("t2");
        assertThat(npc.getQuote()).isEqualTo("t3");
        assertThat(npc.getQuote()).isEqualTo("This character can't tell you anything interesting");
        assertThat(npc.getQuote()).isEqualTo("This character can't tell you anything interesting");
        assertThat(npc.getQuote()).isEqualTo("This character can't tell you anything interesting");
        assertThat(npc.getQuote()).isEqualTo("This character can't tell you anything interesting");
        assertThat(npc.getQuote()).isEqualTo("This character can't tell you anything interesting");
    }
}
