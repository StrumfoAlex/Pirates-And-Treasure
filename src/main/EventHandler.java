package main;

public class EventHandler {
    GamePanel gp;
    EventRect[][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // Initialize the event rectangle most with your preferred size. This is the area that will trigger the event when the player enters it.
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 27;
            eventRect[col][row].y = 27;
            eventRect[col][row].width = 16;
            eventRect[col][row].height = 16;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
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
             if (hit(47, 48, "any")) { damageBush(47, 48, gp.dialogueState); }
             if (hit(48, 48, "any")) { healingBush(48, 48, gp.dialogueState); }
         }
        // directia poate fi "up", "down", "left", "right", sau "any"
        if (hit(46, 48, "any")) { teleport(gp.dialogueState); }
    }

    public boolean hit(int col, int row, String direction) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gp.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damageBush(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.playSE(7);
        gp.ui.currentDialogue = "You ate a toxic berry!";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingBush(int col, int row, int gameState) {
        if (gp.keyH.enterPressed)
        {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You ate a good berry!\nYour life is fully restored!\nYou have full ammo!";
            gp.player.life = gp.player.maxLife;
            gp.player.ammo =  gp.player.maxAmmo;
            gp.aSetter.setMonster();
        }
    }
    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You stepped on a teleporter!\nYou have been teleported to a new location!";
        gp.player.worldX = gp.tileSize * 40;
        gp.player.worldY = gp.tileSize * 30;

    }
}
