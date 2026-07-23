package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    GamePanel gp;

    public OBJ_Axe(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Axe";
        type = type_axe;
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nA tool for cutting or hitting\nenemies.";
        price = 175;
    }

}
