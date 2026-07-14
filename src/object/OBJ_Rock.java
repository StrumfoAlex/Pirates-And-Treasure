package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Rock extends Projectile {

    GamePanel gp;

    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/rock", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        return user.ammoRocks >= useCost;
    }

    public void subtractResource(Entity user) {
        user.ammoRocks -= useCost;
    }

    public Color getParticleColor() {
        return new Color(204, 97, 115, 189);
    }

    public int getParticleSize() {
        return 10; // 10 pixels
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }
}
