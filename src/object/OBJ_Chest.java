package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity
{
    GamePanel gp;

    public static final String objName = "Chest";

    public OBJ_Chest(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/objects/chest", gp.tileSize,  gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize,  gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setLoot(Entity loot) {
        this.loot = loot;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You open the chest and find a " + loot.name + " and 500 coins!" + "\n\nBut your inventory is full!\nYou cannot carry any more!";
        dialogues[1][0] = "You open the chest and find a " + loot.name + " and 500 coins!" + "\n\nYou obtain the treasure!";
        dialogues[2][0] = "The chest is empty!";
    }

    public void interact()
    {
        if (!opened) {
            gp.playSE(2);

            if (!gp.player.canObtainItem(loot)) {
                startDialogue(this, 0);
            }
            else {
                startDialogue(this, 1);
                gp.player.coins += 500;
                down1 = image2;
                opened = true;
            }

        }
        else {
            startDialogue(this, 2);
        }
    }
}