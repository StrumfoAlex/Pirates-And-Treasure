package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map extends TileManager{
    GamePanel gp;
    BufferedImage[] worldMap;
    BufferedImage miniMapImage;
    public boolean miniMapOn = false;

    public Map(GamePanel gp) {
        super(gp);
        this.gp = gp;
        createWorldMap();
        // Try to load the small "map" image from resources (classpath first, then file system)
        try (java.io.InputStream is = getClass().getResourceAsStream("/res/objects/map.png")) {
            if (is != null) {
                miniMapImage = ImageIO.read(is);
            } else {
                // Fallback to file system relative path
                miniMapImage = ImageIO.read(new File("res/objects/map.png"));
            }
        } catch (IOException e) {
            // If loading fails, leave miniMapImage null (map will not be drawn)
            System.err.println("Failed to load mini map image: " + e.getMessage());
            miniMapImage = null;
        }
    }

    public void createWorldMap() {
        worldMap = new BufferedImage[gp.maxMap];
        int worldMapWidth = gp.tileSize * gp.maxWorldCol;
        int worldMapHeight = gp.tileSize * gp.maxWorldRow;

        for (int i = 0; i < gp.maxMap; i++) {
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                int tileNum = mapTileNum[i][col][row];
                int x = gp.tileSize * col;
                int y = gp.tileSize * row;
                g2.drawImage(tile[tileNum].image, x, y, null);

                col++;
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    public void drawFullMapScreen(Graphics g2) {
        // Background Color
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw Map
        int width = 800;
        int height = 800;
        int x = gp.screenWidth / 2 - width / 2;
        int y = gp.screenHeight / 2 - height / 2;

        if (miniMapImage != null) {
            g2.drawImage(miniMapImage, 50, -50, width + 400, height + 200, null);
        }
        g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

        // Draw Player
        double scale = (double)(gp.tileSize * gp.maxWorldCol) / width;
        int playerX = (int)(x + gp.player.worldX / scale) -10;
        int playerY = (int)(y + gp.player.worldY / scale) -10;
        int playerSize = (int)(gp.tileSize / scale) + 15;
        g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);

        // Draw X is player has the Treasure Map
        if (gp.player.searchItemInInventory("Treasure Map") != 999) {
            g2.setColor(Color.RED);
            g2.setFont(gp.ui.maruMonica.deriveFont(60f));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60f));
            x = gp.screenWidth - 425;
            y = 275;
            g2.drawString("x", x, y);
        }

        // Hint
        g2.setFont(gp.ui.maruMonica.deriveFont(32f));
        g2.setColor(Color.white);
        g2.drawString("Press M to close.", 1100, 750);
    }

    public void drawMiniMap(Graphics2D g2) {
        if (miniMapOn)
        {
            int width = 300;
            int height = 300;
            int x = gp.screenWidth - width - 50;
            int y = 50;

            // Draw background map image if available (from res/objects/map.png)
            if (miniMapImage != null) {
                g2.drawImage(miniMapImage, x - 50, y - 30, width + 100, height + 70, null);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

            // Draw Player
            double scale = (double)(gp.tileSize * gp.maxWorldCol) / width;
            int playerX = (int)(x + gp.player.worldX / scale) - 7;
            int playerY = (int)(y + gp.player.worldY / scale) - 7;
            int playerSize = (int)(gp.tileSize / scale) + 15;
            g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            // Draw X is player has the Treasure Map
            if (gp.player.searchItemInInventory("Treasure Map") != 999) {
                g2.setColor(Color.RED);
                g2.setFont(gp.ui.maruMonica.deriveFont(32f));
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));
                x = gp.screenWidth - 115;
                y = 150;
                g2.drawString("x", x, y);
            }
        }
    }
}
