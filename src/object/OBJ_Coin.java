package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Coin extends Entity {
    GamePanel gp;

    public static final String objName = "Coin";

    public OBJ_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        value = 20;
        down1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
        gp.playSE(4);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coins += value;

        return true;
    }
}
