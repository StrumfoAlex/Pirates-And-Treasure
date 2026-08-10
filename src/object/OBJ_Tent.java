package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {
    GamePanel gp;

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Tent";
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        price = 300;
        stackable = true;
    }

    public boolean use(Entity entity) {
        if (gp.eManager.lighting.dayState == gp.eManager.lighting.night) {
            gp.gameState = gp.sleepState;
            gp.playSE(12);
            gp.player.life = gp.player.maxLife;
            gp.player.ammo = gp.player.maxAmmo;

            gp.player.getSleepingImage(down1);

            return true;
        }
        else {
            gp.ui.addMessage("Cannot sleep during the day!");
            return false;
        }

        // remove after use = true
        // keep after use = false
    }
}
