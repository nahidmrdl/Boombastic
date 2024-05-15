package item.curse.instantbombplacement;

import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class InstantBombPlacementTest {
    private InstantBombPlacement curse;
    private Player player;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        curse = new InstantBombPlacement();
    }

    @Test
    void apply() throws InterruptedException {
        curse.apply(player);
        // Assuming environment setup allows for testing multi-threaded behavior
        Thread.sleep(200); // Wait for the scheduled bomb placement
        verify(player, times(1)).placeBomb();
    }
}
