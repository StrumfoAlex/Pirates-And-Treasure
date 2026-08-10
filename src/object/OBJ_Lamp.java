package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lamp extends Entity {
    GamePanel gp;

    public OBJ_Lamp(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Lamp";
        type = type_light;
        down1 = setup("/objects/lamp", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nIlluminates your surroundings.";
        price = 200;
        lightRadius = 300;


    }
}
