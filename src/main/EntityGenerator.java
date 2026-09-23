package main;

import entity.Entity;
import object.*;

public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        Entity obj = null;

        switch (itemName) {
            case OBJ_Sword_Normal.objName:
                obj = new OBJ_Sword_Normal(gp);
                break;
            case OBJ_Axe.objName:
                obj = new OBJ_Axe(gp);
                break;
            case OBJ_Shield_Wood.objName:
                obj = new OBJ_Shield_Wood(gp);
                break;
            case OBJ_Shield_Iron.objName:
                obj = new OBJ_Shield_Iron(gp);
                break;
            case OBJ_Boots.objName:
                obj = new OBJ_Boots(gp);
                break;
            case OBJ_Key.objName:
                obj = new OBJ_Key(gp);
                break;
            case OBJ_Lamp.objName:
                obj = new OBJ_Lamp(gp);
                break;
            case OBJ_Torch.objName:
                obj = new OBJ_Torch(gp);
                break;
            case OBJ_Tent.objName:
                obj = new OBJ_Tent(gp);
                break;
            case OBJ_Medicine.objName:
                obj = new OBJ_Medicine(gp);
                break;
            case OBJ_MapX.objName:
                obj = new OBJ_MapX(gp);
                break;
            case OBJ_Door.objName:
                obj = new OBJ_Door(gp);
                break;
            case OBJ_Chest.objName:
                obj = new OBJ_Chest(gp);
                break;
            case OBJ_Coin.objName:
                obj = new OBJ_Coin(gp);
                break;
            case OBJ_Ammo.objName:
                obj = new OBJ_Ammo(gp);
                break;
            case OBJ_Rock.objName:
                obj = new OBJ_Rock(gp);
                break;
            case OBJ_Bullet.objName:
                obj = new OBJ_Bullet(gp);
                break;
            case OBJ_Heart.objName:
                obj = new OBJ_Heart(gp);
                break;
        }
        return obj;
    }
}
