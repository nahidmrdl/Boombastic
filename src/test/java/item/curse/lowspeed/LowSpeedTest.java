package item.curse.lowspeed;

import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LowSpeedTest {
    private LowSpeed curse;
    private Player player;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        curse = new LowSpeed();
    }

    @Test
    void apply() {
        curse.apply(player);
        verify(player).setSpeed(135);
    }

    @Test
    void reset() {
        curse.reset(player);
        verify(player).resetDefaultSpeed();
    }
}
