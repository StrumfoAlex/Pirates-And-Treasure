package object;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject
{
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            collision = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
