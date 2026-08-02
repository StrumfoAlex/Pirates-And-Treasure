package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Ammo extends Entity {
    GamePanel gp;

    public OBJ_Ammo(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Ammo";
        value = 1;
        down1 = setup("/projectile/bulletFull", gp.tileSize, gp.tileSize);
        image = setup("/projectile/bulletFull", gp.tileSize, gp.tileSize);
        image2 = setup("/projectile/bulletEmpty", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
        gp.playSE(10);
        gp.ui.addMessage("Ammo +" + value);
        entity.ammo += value;

        return true;
    }
}
