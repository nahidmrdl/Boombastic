package item.powerup.ghost;

import static org.mockito.Mockito.*;

import entity.player.Player;
import cell.Cell;
import cell.normalCell.NormalCell;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GhostTest {
    private Player player;
    private Ghost ghost;
    private Cell cell;
    private GameMap gameMap;
    private Cell[][] mockCells; // Mocked 2D array for the map

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        cell = mock(NormalCell.class);
        gameMap = mock(GameMap.class);
        mockCells = new Cell[10][10]; // Assuming a 10x10 map for simplicity

        // Fill the mock map with mock cells
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mockCells[i][j] = mock(Cell.class);
            }
        }

        when(cell.getMap()).thenReturn(gameMap);
        when(gameMap.getMap()).thenReturn(mockCells); // Ensure getMap() returns the mockCells array
        when(gameMap.getCell(anyInt(), anyInt())).thenReturn(cell); // Default to returning a normal cell
        ghost = new Ghost();
        ghost.setCell(cell);
    }

    @Test
    void applyShouldMakePlayerGhost() {
        ghost.apply(player);
        verify(player).setGhost(true);
    }

    @Test
    void resetShouldMakePlayerNotGhost() {
        when(player.getX()).thenReturn(1); // Ensure player's x-coordinate is within bounds
        when(player.getY()).thenReturn(1); // Ensure player's y-coordinate is within bounds
        ghost.reset(player);
        verify(player).setGhost(false);
        verify(player, never()).setDead(true); // Ensure the player is not set to dead
    }
}
