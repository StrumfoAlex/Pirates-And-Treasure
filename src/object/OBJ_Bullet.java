package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Bullet extends Projectile {

    GamePanel gp;

    public OBJ_Bullet(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Bullet";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        knockBackPower = 0;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/bullet_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/bullet_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/bullet_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/bullet_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/bullet_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/bullet_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/bullet_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/bullet_right_2", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        return user.ammo >= useCost;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }

    public Color getParticleColor() {
        return new Color(125, 78, 223);
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
