package item.curse.prohibitbombs;

import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProhibitBombsTest {
    private ProhibitBombs curse;
    private Player player;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        curse = new ProhibitBombs();
    }

    @Test
    void apply() {
        curse.apply(player);
        verify(player).setCanPlaceBomb(false);
    }

    @Test
    void reset() {
        curse.reset(player);
        verify(player).setCanPlaceBomb(true);
    }
}
