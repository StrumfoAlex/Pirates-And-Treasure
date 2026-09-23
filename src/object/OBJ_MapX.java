package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MapX extends Entity {
    GamePanel gp;
    public static final String objName = "Treasure Map";
    public OBJ_MapX(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/mapX", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nShows on the map the location of a\nhidden treasure!";

    }
}
