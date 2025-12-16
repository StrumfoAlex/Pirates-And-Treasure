package object;

import main.GamePanel;

public class OBJ_Chest extends SuperObject {

    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        this.gp = gp;
        name = "Chest";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            collision = true;
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}