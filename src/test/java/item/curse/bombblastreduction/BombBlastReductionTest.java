package item.curse.bombblastreduction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BombBlastReductionTest {
    private BombBlastReduction curse;
    private Player player;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        curse = new BombBlastReduction();
    }

    @Test
    void apply() {
        curse.apply(player);
        verify(player).setBombBlastRange(0);
    }

    @Test
    void reset() {
        curse.reset(player);
        verify(player).setBombBlastRange(1);
    }
}
