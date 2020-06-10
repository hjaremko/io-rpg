package pl.uj.io.cuteanimals.plot.actions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.ItemClass;
import pl.uj.io.cuteanimals.model.ItemType;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;

@ExtendWith(MockitoExtension.class)
public class DungeonInvestigateActionTest {

    @Mock private Player player;

    private DungeonInvestigateAction action;

    private String infoMessage;

    @Mock private IEquipment equipment;

    @BeforeEach
    private void setup() {
        infoMessage = "message";
        action = new DungeonInvestigateAction(infoMessage);
    }

    @Test
    public void actionBodyReturnsProperMessageIfPlayerStateIsWrong() {
        given(player.getCurrentGameState()).willReturn(GameState.FIGHT);

        var result = action.actionBody(player);

        assertThat(result.getMessage()).isEqualTo("This action cannot be executed now");
    }

    @Test
    public void actionBodyReturnsProperMessageIfStateIsCorrectAndPlayerHasTorch() {
        given(player.getCurrentGameState()).willReturn(GameState.EXPLORATION);
        given(player.getArmor()).willReturn(equipment);
        given(equipment.getItems())
                .willReturn(
                        List.of(
                                new Item(
                                        1,
                                        "Torch",
                                        "aaa",
                                        1,
                                        null,
                                        ItemType.WEAPON,
                                        ItemClass.ANY)));

        var result = action.actionBody(player);

        assertThat(result.getMessage()).isEqualTo(infoMessage);
    }

    @Test
    public void actionBodyReturnsProperMessageIfStateIsCorrectAndPlayerHasNotTorch() {
        given(player.getCurrentGameState()).willReturn(GameState.EXPLORATION);
        given(player.getArmor()).willReturn(equipment);
        given(equipment.getItems()).willReturn(Collections.emptyList());

        var result = action.actionBody(player);

        assertThat(result.getMessage()).isEqualTo("You can see nothing but the darkness.");
    }
}
