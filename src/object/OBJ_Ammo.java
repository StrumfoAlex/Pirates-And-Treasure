package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Ammo extends Entity {
    GamePanel gp;

    public OBJ_Ammo(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Ammo";
        image = setup("/projectile/bulletFull", gp.tileSize, gp.tileSize);
        image2 = setup("/projectile/bulletEmpty", gp.tileSize, gp.tileSize);
    }
}
