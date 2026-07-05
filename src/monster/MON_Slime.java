package monster;

import entity.Entity;
import main.GamePanel;

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
        exp = 5;

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
    }

    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
