package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Iron extends Entity {

    public static final String objName = "Iron Shield";

    public OBJ_Shield_Iron(GamePanel gp) {
        super(gp);

        name = objName;
        type = type_shield;
        down1 = setup("/objects/shield_iron", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\n\nGood shield for an advance crew\nmember!";
        price = 200;
    }
}
