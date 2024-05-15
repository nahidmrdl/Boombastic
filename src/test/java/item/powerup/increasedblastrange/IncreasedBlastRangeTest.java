package item.powerup.increasedblastrange;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncreasedBlastRangeTest {
    private Player player;
    private IncreasedBlastRange powerUp;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        when(player.getBombBlastRange()).thenReturn(1);
        powerUp = new IncreasedBlastRange();
    }

    @Test
    void applyShouldIncreaseBlastRange() {
        powerUp.apply(player);
        verify(player).setBombBlastRange(2);
    }

    @Test
    void resetShouldDecreaseBlastRange() {
        powerUp.reset(player);
        verify(player).setBombBlastRange(0);
    }
}
