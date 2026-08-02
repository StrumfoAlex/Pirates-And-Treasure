package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity
{
    GamePanel gp;
    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize,  gp.tileSize);
        description = "[" + name + "]\n\nIt opens a door or a chest!";
        price = 75;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            gp.ui.currentDialogue = "You use the " + name + " and open the door!";
            gp.playSE(2);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            gp.ui.currentDialogue = "The " + name + " cannot be used here!";
            return false;
        }
    }
}
