package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Medicine extends Entity {

    GamePanel gp;

    public OBJ_Medicine(GamePanel gp)
    {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Medicine";
        value = 4;
        down1 = setup("/objects/medicine", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nHeal your wounds!\nReceive " + value + " HP";
        price = 25;
    }
    
    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        if (gp.player.life != gp.player.maxLife) {
            gp.ui.currentDialogue = "Your drink the medicine!\n" +
                    "Your life has been recovered by " + value + " HP!";
            entity.life += value;
            gp.playSE(1);
            return true;
        }
        else {
            //gp.ui.addMessage("Your health is already full!");
            gp.ui.currentDialogue = "Your health is already full!";
            return false;
        }
    }
}
