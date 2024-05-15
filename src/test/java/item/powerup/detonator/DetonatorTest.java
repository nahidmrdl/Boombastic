package item.powerup.detonator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DetonatorTest {
    private Player player;
    private Detonator detonator;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        detonator = new Detonator();
    }

    @Test
    void apply() {
        detonator.apply(player);
        verify(player).setDetonator(true);
    }

    @Test
    void reset() {
        detonator.reset(player);
        verify(player).setDetonator(false);
    }
}
