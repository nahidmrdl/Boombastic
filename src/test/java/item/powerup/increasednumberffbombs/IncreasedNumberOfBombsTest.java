package item.powerup.increasednumberffbombs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncreasedNumberOfBombsTest {
    private Player player;
    private IncreasedNumberOfBombs increasedNumberOfBombs;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        increasedNumberOfBombs = new IncreasedNumberOfBombs();
    }

    @Test
    void applyShouldIncrementBombCount() {
        when(player.getBombCount()).thenReturn(1);  // Assume the current bomb count is 1
        increasedNumberOfBombs.apply(player);
        verify(player).setBombCount(2);  // Check if the bomb count was set to 2
    }

    @Test
    void resetDoesNotChangeBombCount() {
        increasedNumberOfBombs.reset(player);
        verify(player, never()).setBombCount(anyInt());  // We verify that setBombCount is never called during reset
    }
}
