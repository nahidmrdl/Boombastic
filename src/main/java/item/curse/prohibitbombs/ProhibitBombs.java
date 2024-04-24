package item.curse.prohibitbombs;

import item.curse.Curse;

import entity.player.Player;

import util.ResourceCollection;

import java.awt.*;

public class ProhibitBombs extends Curse {

    public ProhibitBombs() {
        super(ResourceCollection.Images.CANNOTPLACE_BOMB.getImage());
    }

    @Override
    public void apply(Player p) {
        p.setCanPlaceBomb(false);
        System.out.println("Player cannot place bomb for some time");
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    @Override
    public void reset(Player p) {
        p.setCanPlaceBomb(true);
    }
}
