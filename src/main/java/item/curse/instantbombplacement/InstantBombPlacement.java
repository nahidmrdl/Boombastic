package item.curse.instantbombplacement;

import entity.player.Player;
import item.curse.Curse;
import util.ResourceCollection;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a curse that allows the player to place a bomb instantly.
 */
public class InstantBombPlacement extends Curse {

    public InstantBombPlacement() {
        super(ResourceCollection.Images.IMMEDIATE_BOMP_PLACEMENT_CURSE.getImage());
    }

    /**
     * Applies the curse to the player
     * @param p player to apply the curse to
     */
    @Override
    public void apply(Player p) {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                    p.placeBomb();
            }, 150, TimeUnit.MILLISECONDS);
            scheduler.shutdown();
        setFinishTime(System.currentTimeMillis() + 3000);
    }

    /**
     * Resets the curse for the player
     * @param p player to reset the curse for
     */
    @Override
    public void reset(Player p) {
       setFinishTime(System.currentTimeMillis());
    }
}
