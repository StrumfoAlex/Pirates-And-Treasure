package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Torch extends Entity {
    GamePanel gp;
    public static final String objName = "Torch";

    public OBJ_Torch(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        type = type_light;
        down1 = setup("/objects/torch", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nIlluminates your surroundings.";
        price = 100;
        lightRadius = 170;


    }
}
