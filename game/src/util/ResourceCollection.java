package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class ResourceCollection {


    public enum Images {
        JAMIL,
        NAHID,
        MIKE,
        GOSHA,
        BOXMAP1,
        BOXMAP2,
        BOXMAP3,
        BLAST,
        WALLMAP1,
        WALLMAP2,
        WALLMAP3,
        GROUNDMAP1,
        GROUNDMAP2,
        GROUNDMAP3,
        POWER_BOMBSTAGE1,
        POWER_BOMBSTAGE2,
        POWER_BOMBSTAGE3,
        COLLECTIBLE,
        CURSE_ICON,
        POWERUP_ICON,

        BLASTRANGE_EXTENSION,
        CANNOTPLACE_BOMB,
        DETONATOR_POWERUP,
        GHOST_POWERUP,
        IMMEDIATE_BOMP_PLACEMENT_CURSE,
        INVINCIBLE_POWERUP,
        LOWBLASTRANGE_CURSE,
        LOWSPEED_CURSE,
        OBSTACLE_POWERUP,
        ROLLERSKATESPEED_POWERUP,
        MAP_AZE,
        MAP_DNIPRO,
        MAP_SUMY;
        private BufferedImage image = null;

        public BufferedImage getImage() {
            return this.image;
        }
    }



    public enum Files {
        DEFAULT_MAP;

        private InputStreamReader file = null;

        public InputStreamReader getFile() {
            return this.file;
        }
    }

    /**
     * Read files into the program.
     */
    public static void readFiles() {
        try {
            String basePath = "src/assets/";

            // Assigning images to enum values
            Images.JAMIL.image = ImageIO.read(new File(basePath + "jamil.jpg"));
            Images.NAHID.image = ImageIO.read(new File(basePath + "nahid.jpg"));
            Images.MIKE.image = ImageIO.read(new File(basePath + "mike.jpg"));
            Images.GOSHA.image = ImageIO.read(new File(basePath + "gosha.jpg"));
            Images.BOXMAP1.image = ImageIO.read(new File(basePath + "mapAssets/map1/map1box.png"));
            Images.BOXMAP2.image = ImageIO.read(new File(basePath + "mapAssets/map2/map2box.png"));
            Images.BOXMAP3.image = ImageIO.read(new File(basePath + "mapAssets/map3/map3box.png"));
            Images.WALLMAP1.image = ImageIO.read(new File(basePath + "mapAssets/map1/map1wall.png"));
            Images.WALLMAP2.image = ImageIO.read(new File(basePath + "mapAssets/map2/map2wall.png"));
            Images.WALLMAP3.image = ImageIO.read(new File(basePath + "mapAssets/map3/map3wall.png"));
            Images.GROUNDMAP1.image = ImageIO.read(new File(basePath + "mapAssets/map1/map1walkable.png"));
            Images.GROUNDMAP2.image = ImageIO.read(new File(basePath + "mapAssets/map2/map2walkable.png"));
            Images.GROUNDMAP3.image = ImageIO.read(new File(basePath + "mapAssets/map3/map3walkable.png"));
            Images.POWER_BOMBSTAGE1.image = ImageIO.read(new File(basePath + "icons/bombfirststate.png"));
            Images.POWER_BOMBSTAGE2.image = ImageIO.read(new File(basePath + "icons/bombsecondstate.png"));
            Images.POWER_BOMBSTAGE3.image = ImageIO.read(new File(basePath + "icons/bombthirdstate.png"));
            Images.CURSE_ICON.image = ImageIO.read(new File(basePath + "icons/curseicon.png"));
            Images.POWERUP_ICON.image = ImageIO.read(new File(basePath + "icons/powerupicon.png"));
            Images.MAP_AZE.image = ImageIO.read(new File(basePath + "azer.jpg"));
            Images.MAP_DNIPRO.image = ImageIO.read(new File(basePath + "dnipro.jpg"));
            Images.MAP_SUMY.image = ImageIO.read(new File(basePath + "sumy.jpg"));
            Images.BLAST.image = ImageIO.read(new File(basePath + "icons/blast.png"));
            Images.COLLECTIBLE.image = ImageIO.read(new File(basePath + "icons/collectible.png"));
            Images.BLASTRANGE_EXTENSION.image = ImageIO.read(new File(basePath + "icons/blastRangeExtensionPowerup.png"));
            Images.CANNOTPLACE_BOMB.image = ImageIO.read(new File(basePath + "icons/CannotPlaceBombCurse.png"));
            Images.DETONATOR_POWERUP.image = ImageIO.read(new File(basePath + "icons/detonatorPowerup.png"));
            Images.GHOST_POWERUP.image = ImageIO.read(new File(basePath + "icons/GhostPowerup.png"));

            Images.IMMEDIATE_BOMP_PLACEMENT_CURSE.image = ImageIO.read(new File(basePath + "icons/ImmediateBombPlacementCurse.png"));
            Images.INVINCIBLE_POWERUP.image = ImageIO.read(new File(basePath + "icons/InvinciblePowerup.png"));
            Images.LOWBLASTRANGE_CURSE.image = ImageIO.read(new File(basePath + "icons/LowBlastRangeCurse.png"));
            Images.LOWSPEED_CURSE.image = ImageIO.read(new File(basePath + "icons/LowSpeedCurse.png"));
            Images.OBSTACLE_POWERUP.image = ImageIO.read(new File(basePath + "icons/ObstaclePowerup.png"));
            Images.ROLLERSKATESPEED_POWERUP.image = ImageIO.read(new File(basePath + "icons/RollerSkateSpeedUpPowerup.png"));







//            COLLECTIBLE,
//                    CURSE_ICON,
//                    POWERUP_ICON,
//
//                    MAP_AZE,
//                    MAP_DNIPRO,
//                    MAP_SUMY;



//            Files.DEFAULT_MAP.file = new InputStreamReader(ResourceCollection.class.getResourceAsStream("/resources/default.csv"));
        } catch (IOException e) {
            System.err.println(e + ": Cannot read image file");
            e.printStackTrace();
        }
    }




}