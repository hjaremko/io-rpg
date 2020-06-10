package pl.uj.io.cuteanimals.plot.actions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uj.io.cuteanimals.model.DefaultLocation;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.WorldMap;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.model.interfaces.RandomInteger;

@ExtendWith(MockitoExtension.class)
class DungeonEntranceGoActionTest {
    @Mock private RandomInteger rand;
    @Mock private Player player;
    private DungeonEntranceGoAction goAction;

    @BeforeEach
    void setUp() {
        var map = new HashMap<String, ILocation>();
        map.put("location", new DefaultLocation());
        goAction = new DungeonEntranceGoAction(map, rand);
    }

    @Test
    void passSuccessful() {
        var args = new ArrayList<String>();
        args.add("location");
        goAction.setArgs(args);

        when(rand.nextInt(anyInt())).thenReturn(5);
        when(player.getCurrentGameState()).thenReturn(GameState.EXPLORATION);
        goAction.execute(player);
        verify(player).changeLocation(any(ILocation.class));
    }

    @Test
    void passFailed() {
        var map = mock(WorldMap.class);
        player = new Player(0, map);
        player.setGameState(GameState.EXPLORATION);
        var args = new ArrayList<String>();
        args.add("location");
        goAction.setArgs(args);

        when(rand.nextInt(anyInt())).thenReturn(1);
        when(map.getLocation(anyString())).thenReturn(new DefaultLocation());

        goAction.execute(player);
        var x = verify(map).getLocation("medical");
        // this is to silence spotbugs
        assertThat(x).isNull();
    }

    @Test
    void locationNotFound() {
        var args = new ArrayList<String>();
        args.add("aaa");
        goAction.setArgs(args);

        when(player.getCurrentGameState()).thenReturn(GameState.EXPLORATION);
        goAction.execute(player);
        verify(player, never()).changeLocation(any(ILocation.class));
    }
}
