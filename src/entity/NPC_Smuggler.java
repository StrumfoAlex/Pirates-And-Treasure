package entity;

import main.GamePanel;
import object.*;

public class NPC_Smuggler extends Entity{
    public NPC_Smuggler(GamePanel gp) {
        super(gp);
        direction = "right";
        speed = 1;
        type = type_npc;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize,  gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize,  gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize,  gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize,  gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize,  gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize,  gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize,  gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize,  gp.tileSize);
    }
    public void setDialogue() {
        dialogues[0][0] = "Hi pirate! Let's see what offer may we do for you today!";
        dialogues[1][0] = "See you again to make some trades!";
        dialogues[2][0] = "You need more coins to buy that!";
        dialogues[3][0] = "Your inventory is full!";
        dialogues[4][0] = "Cannot sell an equipped item!";


    }

    public void setItems() {
        inventory.add(new OBJ_Medicine(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Iron(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Lamp(gp));
    }

    public void speak() {
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
