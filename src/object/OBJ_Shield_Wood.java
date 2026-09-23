package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public static final String objName = "Wooden Shield";

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        name = objName;
        type = type_shield;
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\n\nOld shield for a simple crew member!";
        price = 100;
    }
}
