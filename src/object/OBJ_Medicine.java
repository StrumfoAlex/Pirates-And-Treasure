package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Medicine extends Entity {

    GamePanel gp;
    public static final String objName = "Medicine";

    public OBJ_Medicine(GamePanel gp)
    {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 4;
        down1 = setup("/objects/medicine", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nHeal your wounds!\nReceive " + value + " HP";
        price = 25;
        stackable = true;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Your drink the medicine!\n" +
                "Your life has been recovered by " + value + " HP!";
        dialogues[1][0] = "Your health is already full!";
    }
    
    public boolean use(Entity entity) {
        if (gp.player.life != gp.player.maxLife) {
            startDialogue(this, 0);
            entity.life += value;
            gp.playSE(1);
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
        }
    }
}
