package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() {
        // Create a buffer image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        if (gp.player.currentLight == null) {
            g2.setColor(new Color(0, 0, 0, 0.90f));
        }
        else {
            // Create a center x and y of the light circle
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;

            // Create a gradation effect within the light circle
            Color[] color = new Color[10];
            float[] fraction =  new float[10];

            color[0] = new Color(0, 0, 0, 0f);
            color[1] = new Color(0, 0, 0, 0.15f);
            color[2] = new Color(0, 0, 0, 0.25f);
            color[3] = new Color(0, 0, 0, 0.35f);
            color[4] = new Color(0, 0, 0, 0.45f);
            color[5] = new Color(0, 0, 0, 0.55f);
            color[6] = new Color(0, 0, 0, 0.65f);
            color[7] = new Color(0, 0, 0, 0.75f);
            color[8] = new Color(0, 0, 0, 0.88f);
            color[9] = new Color(0, 0, 0, 0.90f);

            fraction[0] = 0f;
            fraction[1] = 0.15f;
            fraction[2] = 0.25f;
            fraction[3] = 0.35f;
            fraction[4] = 0.45f;
            fraction[5] = 0.55f;
            fraction[6] = 0.65f;
            fraction[7] = 0.75f;
            fraction[8] = 0.88f;
            fraction[9] = 1f;

            // Create a gradation paint settings for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);

            // Set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }

    public void update() {
        if (gp.player.lightUpdated)
        {
            setLightSource();
            gp.player.lightUpdated = false;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
