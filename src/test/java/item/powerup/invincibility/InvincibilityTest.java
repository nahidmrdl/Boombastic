package item.powerup.invincibility;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvincibilityTest {
    private Player player;
    private Invincibility invincibility;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        invincibility = new Invincibility();
    }

    @Test
    void applyShouldMakePlayerInvincible() {
        invincibility.apply(player);
        verify(player).setInvincible(true);
    }

    @Test
    void resetShouldMakePlayerNotInvincible() {
        invincibility.reset(player);
        verify(player).setInvincible(false);
    }
}
