package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int pistolImageCounter = 0;
    public final int pistolImageDuration = 15; // frames to display pistol image

    public boolean lightUpdated = false;

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
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 50; // 23 pt map1
        worldY = gp.tileSize * 50; // 21 pt map1
        defaultSpeed = 4;
        speed = defaultSpeed;
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
        coins = 50;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        currentLight = null;
        projectile = new OBJ_Bullet(gp);
        //projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();

        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 50; // 23 pt map1
        worldY = gp.tileSize * 50; // 21 pt map1
        direction = "down";
    }

    public void restoreStatus() {
        life = maxLife;
        ammo = maxAmmo;
        speed = defaultSpeed;
        invincible = false;
        attacking = false;
        knockBack = false;
        lightUpdated = true;
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentShield) {
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
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
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize,  gp.tileSize * 2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize,  gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2,  gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2,  gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2,  gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2,  gp.tileSize);
        }
        else if (currentWeapon.type == type_axe) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize,  gp.tileSize * 2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize,  gp.tileSize * 2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize,  gp.tileSize * 2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize,  gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2,  gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2,  gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2,  gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2,  gp.tileSize);
        }
    }

    public void getPistolImage() {
        up1 = setup("/player/boy_gun_up", gp.tileSize,  gp.tileSize);
        up2 = setup("/player/boy_gun_up", gp.tileSize,  gp.tileSize);
        down1 = setup("/player/boy_gun_down", gp.tileSize,  gp.tileSize);
        down2 = setup("/player/boy_gun_down", gp.tileSize,  gp.tileSize);
        left1 = setup("/player/boy_gun_left", gp.tileSize,  gp.tileSize);
        left2 = setup("/player/boy_gun_left", gp.tileSize,  gp.tileSize);
        right1 = setup("/player/boy_gun_right", gp.tileSize,  gp.tileSize);
        right2 = setup("/player/boy_gun_right", gp.tileSize,  gp.tileSize);

        // Set counter to keep pistol images displayed for a duration
        pistolImageCounter = pistolImageDuration;
    }

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
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

            // CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

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
            getPistolImage();

            // SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE AMMO COST
            projectile.subtractResource(this);

            // CHECK VACANCY
            for (int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

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

        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            // gp.playSE(x);
        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible) {
                gp.playSE(8);

                if (knockBackPower > 0) {
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " +  gp.monster[gp.currentMap][i].exp + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    private void contactMonster(int i) {
        if (i != 999) {
            if (!invincible && !gp.monster[gp.currentMap][i].dying)
            {
                gp.playSE(7);
                int damage =  gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
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
            gp.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "Press C to see your stats!";
            //life = maxLife; to make full life on lvl up
        }
    }

    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible) {
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }

        }
    }

    public void pickUpObject(int i) {
        if (i != 999)
        {
            // PICKUP ONLY ITEMS
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            // OBSTACLE
            else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
                if (keyH.enterPressed) {
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            // INVENTORY ITEMS
            else {
                String text;

                if (canObtainItem(gp.obj[gp.currentMap][i])) {
                    gp.playSE(4);
                    text = "You got a " + gp.obj[gp.currentMap][i].name + "!";
                }
                else {
                    text = "You cannot carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }

    public void interactNPC(int i)
    {
        if (i != 999)
        {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }

    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

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
            if (selectedItem.type == type_light) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == type_consumable) {
                if (selectedItem.use(this)) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }

    public int searchItemInInventory(String itemName) {
        // this method also can be used when checking if player has a certain quest item

        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }

        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {

        boolean canObtain = false;

        // Check is stackable
        if (item.stackable) {
            int index = searchItemInInventory(item.name);

            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else { // New item
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else { // Not stackable
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }

        return canObtain;
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
