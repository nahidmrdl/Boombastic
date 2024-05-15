package item.powerup.placeobstacle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaceObstacleTest {
    private Player player;
    private PlaceObstacle placeObstacle;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        when(player.getPlaceObsticleCount()).thenReturn(0);
        placeObstacle = new PlaceObstacle();
    }

    @Test
    void applyShouldIncreaseObstacleCount() {
        placeObstacle.apply(player);
        verify(player).setPlaceObsticleCount(3);
    }

    @Test
    void resetDoesNotChangeObstacleCount() {
        placeObstacle.reset(player);
        verify(player, never()).setPlaceObsticleCount(anyInt());
    }
}
