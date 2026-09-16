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
        stackable = true;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You use the " + name + " and open the door!";
        dialogues[1][0] = "The " + name + " cannot be used here!";
    }

    public boolean use(Entity entity) {
        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(2);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
        }
    }
}
