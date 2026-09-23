package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {

    GamePanel gp;
    public static final String objName = "Tent";

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = objName;
        description = "[" + name + "]\n\nRest at night and recover your\nhealth!";
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        price = 300;
        stackable = true;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Cannot sleep during the day!";
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
//            gp.ui.addMessage("Cannot sleep during the day!");
            gp.gameState = gp.dialogueState;
            startDialogue(this, 0);
            return false;
        }

        // remove after use = true
        // keep after use = false
    }
}
