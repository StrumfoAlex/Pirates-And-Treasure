package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font consolas_40, consolas_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        consolas_40 = new Font("Consolas", Font.PLAIN, 40);
        consolas_80B = new Font("Consolas", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {
            g2.setFont(consolas_40);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(consolas_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(consolas_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, 0, 0, gp.tileSize + 20, gp.tileSize + 20, null);
            g2.drawString("x " + gp.player.hasKey, 75, 50);

            // TIME
            playTime += (double)1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 18, 50);

            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 3);
                messageCounter++;
                if (messageCounter > 120) { // 2 seconds
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
