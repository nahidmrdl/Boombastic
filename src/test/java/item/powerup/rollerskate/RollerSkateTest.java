package item.powerup.rollerskate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RollerSkateTest {
    private Player player;
    private RollerSkate rollerSkate;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        rollerSkate = new RollerSkate();
    }

    @Test
    void apply() {
        rollerSkate.apply(player);
        verify(player).setSpeed(35);
    }

    @Test
    void reset() {
        rollerSkate.reset(player);
        verify(player).resetDefaultSpeed();
    }
}
