package item.powerup.placeobstacle;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

/**
 * Represents a power-up that allows the player to place obstacles for some time.
 */
public class PlaceObstacle extends PowerUp {

        /**
         * Constructor for PlaceObstacle
         */
        public PlaceObstacle() {
            super(ResourceCollection.Images.OBSTACLE_POWERUP.getImage());
        }

        /**
         * Applies the power-up to the player
         * @param p player to apply the power-up to
         */
        @Override
        public void apply(Player p) {
            p.setPlaceObsticleCount(p.getPlaceObsticleCount() + 3);
        }

        /**
         * Resets the power-up for the player
         * @param p player to reset the power-up for
         */
        @Override
        public void reset(Player p) {
                setFinishTime(System.currentTimeMillis());
        }
}
