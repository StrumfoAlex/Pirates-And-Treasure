package main;

import entity.NPC_OldMan;
import monster.MON_Slime;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;

        // KEYS
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize * 12;
        gp.obj[i].worldY = gp.tileSize * 51;
        i++;

        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize * 33;
        gp.obj[i].worldY = gp.tileSize * 64;
        i++;

        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize * 59;
        gp.obj[i].worldY = gp.tileSize * 56;
        i++;

        // TOOLS
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 62;
        gp.obj[i].worldY = gp.tileSize * 40;
        i++;

        gp.obj[i] = new OBJ_Shield_Iron(gp);
        gp.obj[i].worldX = gp.tileSize * 64;
        gp.obj[i].worldY = gp.tileSize * 40;
        i++;

        // RESOURCES
        gp.obj[i] = new OBJ_Medicine(gp);
        gp.obj[i].worldX = gp.tileSize * 64;
        gp.obj[i].worldY = gp.tileSize * 44;

        // DOORS
//        gp.obj[i] = new OBJ_Door(gp);
//        gp.obj[i].worldX = gp.tileSize * 63;
//        gp.obj[i].worldY = gp.tileSize * 42;
//        i++;
//
//        gp.obj[i] = new OBJ_Door(gp);
//        gp.obj[i].worldX = gp.tileSize * 63;
//        gp.obj[i].worldY = gp.tileSize * 46;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 60;
        gp.npc[0].worldY = gp.tileSize * 48;
    }

    public void setMonster() {
        gp.monster[0] = new MON_Slime(gp);
        gp.monster[0].worldX = gp.tileSize * 50;
        gp.monster[0].worldY = gp.tileSize * 48;

        gp.monster[1] = new MON_Slime(gp);
        gp.monster[1].worldX = gp.tileSize * 50;
        gp.monster[1].worldY = gp.tileSize * 46;
    }
}
