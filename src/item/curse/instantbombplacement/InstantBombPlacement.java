package item.curse.instantbombplacement;

import entity.player.Player;
import item.curse.Curse;
import util.ResourceCollection;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InstantBombPlacement extends Curse {

    public InstantBombPlacement() {
        super(ResourceCollection.Images.IMMEDIATE_BOMP_PLACEMENT_CURSE.getImage());
    }

    @Override
    public void apply(Player p) {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                    p.placeBomb();
            }, 150, TimeUnit.MILLISECONDS);
            scheduler.shutdown();
        System.out.println("Bomb placed instantly, If the player has a bomb/can place a bomb");
        setFinishTime(System.currentTimeMillis() + 3000); // Set the curse duration
    }

    @Override
    public void reset(Player p) {
       // Do nothing
    }
}
