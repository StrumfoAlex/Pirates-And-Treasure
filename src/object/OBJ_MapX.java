package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MapX extends Entity {
    GamePanel gp;
    public OBJ_MapX(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Treasure Map";
        down1 = setup("/objects/mapX", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nShows on the map the location of a\nhidden treasure!";

    }
}
