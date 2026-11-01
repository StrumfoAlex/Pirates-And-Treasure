package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Treasure extends Entity {

    GamePanel gp;
    public static final String objName = "Treasure";

    public OBJ_Treasure(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/treasure", gp.tileSize,  gp.tileSize);

        setDialogues();
    }

    public void setDialogues() {
        dialogues[0][0] = "You obtain the treasure!\nYou are a respected pirate now!";
        dialogues[0][1] = "Congratulations!";

    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);   // this = treasure object
        gp.gameState = gp.dialogueState;
        return true;
    }

}
