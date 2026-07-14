package monster;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import object.OBJ_Ammo;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Slime extends Entity {

    GamePanel gp;

    public MON_Slime(GamePanel gp)
    {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 3;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage()
    {
        up1 = setup("/monster/slime_down_1", gp.tileSize,  gp.tileSize);
        up2 = setup("/monster/slime_left_2", gp.tileSize,  gp.tileSize);
        down1 = setup("/monster/slime_left_1", gp.tileSize,  gp.tileSize);
        down2 = setup("/monster/slime_down_2", gp.tileSize,  gp.tileSize);
        left1 = setup("/monster/slime_left_1", gp.tileSize,  gp.tileSize);
        left2 = setup("/monster/slime_left_2", gp.tileSize,  gp.tileSize);
        right1 = setup("/monster/slime_down_1", gp.tileSize,  gp.tileSize);
        right2 = setup("/monster/slime_down_2", gp.tileSize,  gp.tileSize);
    }
    public void setAction()
    {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // 1-100

            if (i <= 25) {
                direction = "up";
            }
            else if (i > 25 && i <= 50) {
                direction = "down";
            }
            else if (i > 50 && i <= 75) {
                direction = "left";
            }
            else if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

        int i = new Random().nextInt(100)+1;
        if (i > 99 && !projectile.alive && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop() {
        // Cast a die
        int i = new Random().nextInt(100)+1;

        // set the monster drop
        if (i < 50) {
            dropItem(new OBJ_Coin(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Ammo(gp));
        }
    }

}
