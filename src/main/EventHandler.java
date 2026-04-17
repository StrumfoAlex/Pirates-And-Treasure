package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        // Initialize the event rectangle most with your preferred size. This is the area that will trigger the event when the player enters it.
        eventRect = new Rectangle();
        eventRect.x = 27;
        eventRect.y = 27;
        eventRect.width = 16;
        eventRect.height = 16;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

     public void checkEvent() {
        if (hit(47, 48, "any")) { damageBush(gp.dialogueState); }
        if (hit(48, 48, "any")) { healingBush(gp.dialogueState); }
        if (hit(46, 48, "any")) { teleport(gp.dialogueState); }
    }

    public boolean hit(int eventCol, int eventRow, String direction) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damageBush(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDiaglogue = "You ate a toxic berry!";
        gp.player.life -= 1;
    }
    public void healingBush(int gameState) {
        if (gp.keyH.enterPressed == true)
        {
            gp.gameState = gameState;
            gp.ui.currentDiaglogue = "You ate a good berry!\nYour life is fully restored!";
            gp.player.life = gp.player.maxLife;
        }
    }
    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDiaglogue = "You stepped on a teleporter!\nYou have been teleported to a new location!";
        gp.player.worldX = gp.tileSize * 40;
        gp.player.worldY = gp.tileSize * 30;

    }
}
