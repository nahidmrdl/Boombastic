package item.bomb;

import item.bomb.Bomb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BombTest {

    private Bomb detonatorBomb;
    private Bomb nonDetonatorBomb;

    @BeforeEach
    void setUp() {
        detonatorBomb = new Bomb(true);
        nonDetonatorBomb = new Bomb(false);
    }

    @Test
    void testDetonatorBomb() {
        assertTrue(detonatorBomb.isDetonator());
    }

    @Test
    void testNonDetonatorBomb() {
        assertFalse(nonDetonatorBomb.isDetonator());
    }

    @Test
    void testBlastRadius() {
        int blastRadius = 2;
        detonatorBomb.setBlastRadius(blastRadius);
        assertEquals(blastRadius, detonatorBomb.getBlastRadius());

    }

   }
