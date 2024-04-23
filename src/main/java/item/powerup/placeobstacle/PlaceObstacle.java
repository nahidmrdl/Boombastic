package item.powerup.placeobstacle;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

public class PlaceObstacle extends PowerUp {

        public PlaceObstacle() {
            super(ResourceCollection.Images.OBSTACLE_POWERUP.getImage());
        }

        @Override
        public void apply(Player p) {
            p.setPlaceObsticleCount(p.getPlaceObsticleCount() + 3);
        }

        @Override
        public void reset(Player p) {

        }
}
