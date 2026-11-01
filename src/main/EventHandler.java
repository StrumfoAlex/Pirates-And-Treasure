package main;

import entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true, canActivateFinal = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // Initialize the event rectangle most with your preferred size. This is the area that will trigger the event when the player enters it.
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 27;
            eventRect[map][col][row].y = 27;
            eventRect[map][col][row].width = 16;
            eventRect[map][col][row].height = 16;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

        setDialogue();
    }

    public void setDialogue() {
        eventMaster.dialogues[0][0] = "You ate a toxic berry!";
        eventMaster.dialogues[1][0] = "You ate a good berry!\nYour life is fully restored!\nYou have full ammo!\n" +
                "(Your progress is saved!)";
        eventMaster.dialogues[2][0] = "You find the treasure!\nBut someone follows you to stole it...";
        eventMaster.dialogues[2][1] = "Is the old pirate...\nHe was tricking you to find the treasure!\nHe wants to fight you!\nYou have to defeat him!";
        eventMaster.dialogues[3][0] = "You may have a clue where is the big treasure!\nBut you have to find the map first!";
    }

     public void checkEvent() {
         // Check if the player character is more than 1 tile away from the previous event.
         // This is to prevent the player from triggering the same event multiple times in a row.
         int xDistance = Math.abs(gp.player.worldX - previousEventX);
         int yDistance = Math.abs(gp.player.worldY - previousEventY);
         int distance = Math.max(xDistance, yDistance);
         if (distance > gp.tileSize) {
             canTouchEvent = true;
         }

         if (canTouchEvent) {
             if (hit(0, 47, 48, "any")) { damageBush(gp.dialogueState); }
             else if (hit(0, 48, 48, "any")) { healingBush(gp.dialogueState); }
             else if (hit(0, 11, 50, "any")) { teleport(1, 53, 50); }
             else if (hit(1, 53, 50, "any")) { teleport(0, 11, 50); }
             else if (hit(0, 80, 29, "any")) { finalBattle(gp.dialogueState); }
             else if (hit(1, 50, 50, "left")) { speak(gp.npc[1][0]); }
         }
        // directia poate fi "up", "down", "left", "right", sau "any"
        if (hit(0, 46, 48, "any")) { teleport(1, 53, 50); }
    }

    public boolean hit(int map, int col, int row, String direction) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damageBush(int gameState) {
        gp.gameState = gameState;
        gp.playSE(7);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingBush(int gameState) {
        if (gp.keyH.enterPressed)
        {
            gp.gameState = gameState;
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.ammo =  gp.player.maxAmmo;
            gp.aSetter.setMonster();
            gp.saveLoad.save();
        }
    }
    public void teleport(int map, int col, int row) {
        // the old code
//        gp.gameState = gameState;
//        gp.ui.currentDialogue = "You stepped on a teleporter!\nYou have been teleported to a new location!";
//        gp.player.worldX = gp.tileSize * 40;
//        gp.player.worldY = gp.tileSize * 30;

        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
    }

    public void finalBattle(int gameState) {
        boolean hasTreasureMap = gp.player.searchItemInInventory("Treasure Map") != 999;

        // final battle only one time
        if (hasTreasureMap && canActivateFinal) {
            gp.gameState = gameState;
            eventMaster.startDialogue(eventMaster, 2);
            canTouchEvent = false;
            canActivateFinal = false;
            gp.aSetter.setFinalBattle();
            gp.npc[0][0] = null;
            return;
        }

        // if you have not gotten the treasure map, you cannot activate the final battle
        if (!hasTreasureMap && canActivateFinal) {
            gp.gameState = gameState;
            eventMaster.startDialogue(eventMaster, 3);
            canTouchEvent = false;
        }

    }

    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            entity.speak();
        }
    }
}
