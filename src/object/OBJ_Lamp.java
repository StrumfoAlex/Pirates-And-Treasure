package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lamp extends Entity {
    GamePanel gp;
    public static final String objName = "Lamp";

    public OBJ_Lamp(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        type = type_light;
        down1 = setup("/objects/lamp", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nIlluminates your surroundings.";
        price = 200;
        lightRadius = 300;


    }
}
