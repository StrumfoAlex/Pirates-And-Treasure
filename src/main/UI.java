package main;
import entity.Entity;
import object.OBJ_Ammo;
import object.OBJ_Coin;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font consolas;
    public Font maruMonica;
    //BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank, ammo_full, ammo_blank, coin;
    public boolean messageOn = false;
    //public String message = "";
    //int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;

    int subState = 0;
    int counter = 0;

    public Entity npc;

//    double playTime;
//    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            assert is != null;
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/font/CONSOLA.TTF");
            assert is != null;
            consolas = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        //OBJ_Key key = new OBJ_Key(gp);
        //keyImage = key.image;

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity ammo = new OBJ_Ammo(gp);
        ammo_full = ammo.image;
        ammo_blank = ammo.image2;
        Entity goldCoin = new OBJ_Coin(gp);
        coin = goldCoin.down1;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(consolas);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        // TRANSITION STATE
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        // TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
    }

    private void drawPlayerLife()
    {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // FULL HEART
        while (i < gp.player.life / 2) {
            g2.drawImage(heart_full, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // HALF HEART
        if (gp.player.life % 2 == 1) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // BLANK HEART
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // DRAW MAX AMMO
        x = gp.tileSize / 2 - 7;
        y = (int)(gp.tileSize * 1.75);
        i = 0;
        while (i < gp.player.maxAmmo) {
            g2.drawImage(ammo_blank, x, y, null);
            i++;
            x += 45;
        }

        // DRAW AMMO
        x = gp.tileSize / 2 - 7;
        // y from above
        i = 0;
        while (i < gp.player.ammo) {
            g2.drawImage(ammo_full, x, y, null);
            i++;
            x += 45;
        }

    }

    private void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    private void drawTitleScreen() {
        //background color
        g2.setColor(new Color(248, 178, 92));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //title name
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Pirates And Treasure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //character
        x = gp.screenWidth / 2  + gp.tileSize * 4;
        y += gp.tileSize * 3;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        //x = getXforCenteredText(text);
        x = gp.screenWidth / 2  - gp.tileSize * 6;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        text = "LOAD GAME";
        // same x
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }

        text = "QUIT";
        // same x
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawPauseScreen() {
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 128F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCharacterScreen() {
        // CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 7;
        final int frameHeight =  gp.tileSize * 12;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 56; // or 62 with a line less

        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Ammo", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coins", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;
        g2.drawString("Shield", textX, textY);

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // RESET textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" +  gp.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.ammo + "/" +  gp.player.maxAmmo);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coins);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight - 10;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize + 10, textY - 30, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize + 10, textY - 30, null);

    }

    public void drawInventory(Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gp.player) {
            frameX = gp.tileSize * 14;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 7;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 7;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;

        // DRAW PLAYER'S ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {
            int slotX = slotXstart + (i % 6) * gp.tileSize;
            int slotY = slotYstart + (i / 6) * gp.tileSize;

            // SLOT BACKGROUND
            g2.setColor(new Color(60, 50, 50));
            g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);

            // EQUIP HIGHLIGHT (draw on top of background)
            if (entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield)
            {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            // ITEM IMAGE
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            // DISPLAY AMOUNT
            if (entity == gp.player && entity.inventory.get(i).amount > 1) {
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;
                String s = "" + entity.inventory.get(i).amount;

                amountX = getXForAlignToRightText(s, slotX + 66);
                amountY = slotY + gp.tileSize;

                // SHADOW
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);
                // NUMBER
                g2.setColor(Color.white);
                g2.drawString(s, amountX - 3, amountY - 3);

            }
        }

        // CURSOR
        if (cursor) {
            int cursorX = slotXstart + (gp.tileSize * slotCol);
            int cursorY = slotYstart + (gp.tileSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight =  gp.tileSize;

            // DRAW CURSOR
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // DESCRIPTION FRAME
            // dFrameX = frameX;
            int dFrameY =  frameY + frameHeight;
            // dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;

            // DRAW DESCRIPTION TEXT
            int textX = frameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(20f));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);
                for (String line: entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 140f));

        text = "Game Over";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(70f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }
        // Quit
        text = "Quit";
        x = getXforCenteredText(text);
        y += 80;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // sub window
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 10;
        int frameHeight = gp.tileSize * 12;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_control(frameX, frameY); break;
            case 2: options_endGameConfirmation(frameX, frameY); break;
        }
    }

    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "OPTIONS";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;

        // Music
        textY += gp.tileSize * 2;
        g2.drawString("Music", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
        }

        // SE
        textY += (int)(gp.tileSize * 1.5);
        g2.drawString("SE", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        // Control
        textY += (int)(gp.tileSize * 1.5);
        g2.drawString("Control", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 1;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }

        // End Game
        textY += (int)(gp.tileSize * 1.5);
        g2.drawString("End Game", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }

        // Back
        textY += gp.tileSize * 3;
        g2.drawString("Back", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                commandNum = 0;
                gp.gameState = gp.playState;
                gp.keyH.enterPressed = false;
            }
        }


        textX = frameX + gp.tileSize * 5;
        g2.setStroke(new BasicStroke(3));

        // Music volume
        textY = frameY + gp.tileSize * 2 + 40;
        g2.drawRect(textX, textY, 240, 24); // 240/5 = 48
        int volumeWidth = 48 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE volume
        textY += (int)(gp.tileSize * 1.5);
        g2.drawRect(textX, textY, 240, 24);
        volumeWidth = 48 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        // Title
        String text = "CONTROL";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm", textX, textY); textY += gp.tileSize;
        g2.drawString("Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot", textX, textY); textY += gp.tileSize;
        g2.drawString("Character screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Full Map", textX, textY); textY += gp.tileSize;
        g2.drawString("Mini Map", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY);

        textX = frameX + gp.tileSize * 7;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("J", textX, textY); textY += gp.tileSize;
        g2.drawString("K", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("M", textX, textY); textY += gp.tileSize;
        g2.drawString("E", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);

        // Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 11;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0)
        {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 2;
                gp.keyH.enterPressed = false;
            }
        }

    }

    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "Quit the game and \nreturn to the title screen?";
        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                gp.gameState = gp.titleState;
                gp.keyH.enterPressed = false;
                gp.stopMusic();
            }
        }

        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
                gp.keyH.enterPressed = false;
            }
        }

    }

    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.eHandler.tempCol * gp.tileSize;
            gp.player.worldY = gp.eHandler.tempRow * gp.tileSize;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }

        String text = "LOADING...";
        g2.setFont(g2.getFont().deriveFont(50F));
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 7;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

    }

    public void drawTradeScreen() {
        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select() {
        drawDialogueScreen();

        int x = gp.tileSize * 17;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;

        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed) {
                subState = 1;
            }
        }
        y += gp.tileSize;

        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed) {
                subState = 2;
            }
        }
        y += gp.tileSize;

        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "See you again to make some trades!";
            }
        }


    }

    public void trade_buy() {
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        // DRAW NPC INVENTORY
        drawInventory(npc, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 14;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coins: " + gp.player.coins, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = gp.tileSize * 7;
            y = gp.tileSize * 6;
            width = gp.tileSize * 2;
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 15, y + 11, 38, 38, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize * 9 - 28);
            g2.drawString(text, x, y + 36);

            // BUY AN ITEM
            if (gp.keyH.enterPressed) {
                if (npc.inventory.get(itemIndex).price > gp.player.coins) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need more coins to buy that!";
                    drawDialogueScreen();
                }
                else {
                    if (gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gp.player.coins -= npc.inventory.get(itemIndex).price;
                    }
                    else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "Your inventory is full!";
                    }
                }
            }
        }
    }

    public void trade_sell() {
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 14;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coins: " + gp.player.coins, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = gp.tileSize * 19;
            y = gp.tileSize * 6;
            width = gp.tileSize * 2;
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 15, y + 11, 38, 38, null);

            int price = gp.player.inventory.get(itemIndex).price / 2;
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize * 21 - 28);
            g2.drawString(text, x, y + 36);

            // SELL AN ITEM
            if (gp.keyH.enterPressed) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                    gp.player.inventory.get(itemIndex) == gp.player.currentShield)
                {
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Cannot sell an equipped item!";
                }
                else {
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else  {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coins += price;
                }
            }
        }
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        return slotCol + (slotRow * 6);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 190);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
}
