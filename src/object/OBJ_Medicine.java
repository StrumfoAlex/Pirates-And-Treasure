package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Medicine extends Entity {

    GamePanel gp;
    int value = 4;

    public OBJ_Medicine(GamePanel gp)
    {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Medicine";
        down1 = setup("/objects/medicine", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nHeal your wounds!\nReceive " + value + " HP";
    }
    
    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDiaglogue = "Your drink the medicine!\n" +
                "Your life has been recovered by " + value + " HP!";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(1);
    }
}
