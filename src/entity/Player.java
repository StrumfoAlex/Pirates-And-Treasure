package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    //public int hasKey = 0;

    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 24;
    
    public int pistolImageCounter = 0;
    public final int pistolImageDuration = 15; // frames to display pistol image

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth /2 - gp.tileSize /2;
        screenY = gp.screenHeight /2 - gp.tileSize /2;

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 40;

//        attackArea.width = 36;
//        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 50; // 23 pt map1
        worldY = gp.tileSize * 50; // 21 pt map1
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxAmmo = 4;
        ammo = maxAmmo;
        ammoRocks = 10;
        strength = 1; // more strength -> more damage
        dexterity = 1; // more dexterity -> less damage received
        exp = 0;
        nextLevelExp = 5;
        coins = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Bullet(gp);
        //projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1", gp.tileSize,  gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize,  gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize,  gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize,  gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize,  gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize,  gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize,  gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize,  gp.tileSize);
    }

    public void getPlayerAttackImage() {

        if (currentWeapon.type == type_sword) {
            attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize,  gp.tileSize * 2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize,  gp.tileSize * 2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize,  gp.tileSize * 2);;
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize,  gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2,  gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2,  gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2,  gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2,  gp.tileSize);
        }
        else if (currentWeapon.type == type_axe) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize,  gp.tileSize * 2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize,  gp.tileSize * 2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize,  gp.tileSize * 2);;
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize,  gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2,  gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2,  gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2,  gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2,  gp.tileSize);
        }
    }

    public void pistolImage() {
        up1 = setup("/player/boy_gun_up", gp.tileSize,  gp.tileSize);
        up2 = setup("/player/boy_gun_up", gp.tileSize,  gp.tileSize);
        down1 = setup("/player/boy_gun_down", gp.tileSize,  gp.tileSize);;
        down2 = setup("/player/boy_gun_down", gp.tileSize,  gp.tileSize);
        left1 = setup("/player/boy_gun_left", gp.tileSize,  gp.tileSize);
        left2 = setup("/player/boy_gun_left", gp.tileSize,  gp.tileSize);
        right1 = setup("/player/boy_gun_right", gp.tileSize,  gp.tileSize);
        right2 = setup("/player/boy_gun_right", gp.tileSize,  gp.tileSize);

        // Set counter to keep pistol images displayed for a duration
        pistolImageCounter = pistolImageDuration;
    }

    public void update() {

        if (keyH.attackKeyPressed) {
            attacking = true;
        }
        if (attacking)
        {
            attacking();
        }
        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT COLLISION
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 14) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            spriteCounter++;
            if (spriteCounter > 20) {
                spriteNum = 1;
                spriteCounter = 0;
            }
        }

        if (gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30
            && projectile.haveResource(this)) {

            // DRAW THE PLAYER SHOOTING IMAGES
            pistolImage();

            // SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE AMMO COST
            projectile.subtractResource(this);

            // ADD IT TO THE LIST
            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

            gp.playSE(9);
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        // Handle pistol image display timer
        if (pistolImageCounter > 0) {
            pistolImageCounter--;
            if (pistolImageCounter == 0) {
                // Restore player images after pistol image duration expires
                getPlayerImage();
            }
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (life > maxLife) {
            life = maxLife;
        }
        if (ammo > maxAmmo) {
            ammo = maxAmmo;
        }
    }

    private void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
            gp.playSE(5);
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldXY for attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with updated worldXY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            // After checking collision, restore original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                gp.playSE(8);

                int damage = attack - gp.monster[i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " +  gp.monster[i].exp + "!");
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    private void contactMonster(int i) {
        if (i != 999) {
            if (!invincible && !gp.monster[i].dying)
            {
                gp.playSE(7);
                int damage =  gp.monster[i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.playSE(1);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDiaglogue = "You are level " + level + " now!\n"
                    + "Press C to see your stats!";
            life = maxLife;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999)
        {
            // PICKUP ONLY ITEMS
            if (gp.obj[i].type == type_pickupOnly) {
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }
            // INVENTORY ITEMS
            else {
                String text;

                if (inventory.size() != maxInventorySize) {
                    inventory.add(gp.obj[i]);
                    gp.playSE(4);

                    text = "You got a " + gp.obj[i].name + "!";
                }
                else {
                    text = "You cannot carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
            }
        }
    }

    public void interactNPC(int i)
    {
        if (i != 999)
        {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }

    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword ||  selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                if (gp.player.life != gp.player.maxLife) {
                    selectedItem.use(this);
                    inventory.remove(itemIndex);
                }
                else {
                    //gp.ui.addMessage("Your health is already full!");
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDiaglogue = "Your health is already full!";
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction) {
            case "up":
                if (!attacking)
                {
                    if (spriteNum == 1) { image = up1; }
                    if (spriteNum == 2) { image = up2; }
                }
                if (attacking)
                {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) { image = attackUp1; }
                    if (spriteNum == 2) { image = attackUp2; }
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) { image = down1; }
                    if (spriteNum == 2) { image = down2; }
                }
                if (attacking) {
                    if (spriteNum == 1) { image = attackDown1; }
                    if (spriteNum == 2) { image = attackDown2; }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) { image = left1; }
                    if (spriteNum == 2) { image = left2; }
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) { image = attackLeft1; }
                    if (spriteNum == 2) { image = attackLeft2; }
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) { image = right1; }
                    if (spriteNum == 2) { image = right2; }
                }
                if (attacking) {
                    if (spriteNum == 1) { image = attackRight1; }
                    if (spriteNum == 2) { image = attackRight2; }
                }
                break;
        }

        // Half transparent on hit
        if (invincible)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // RESTORE DEFAULT
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
