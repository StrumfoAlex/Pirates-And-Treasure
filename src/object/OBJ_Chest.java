package object;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest() {
        name = "Chest";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            collision = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}