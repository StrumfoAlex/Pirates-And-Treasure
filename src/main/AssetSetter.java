package main;

import entity.NPC_OldMan;
import entity.NPC_Smuggler;
import monster.MON_Slime;
import object.*;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;
        int mapNum = 0;

        // KEYS
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 12;
        gp.obj[mapNum][i].worldY = gp.tileSize * 51;
        i++;

        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 64;
        i++;

        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 59;
        gp.obj[mapNum][i].worldY = gp.tileSize * 56;
        i++;

        // HELPFUL RESOURCES (may delete later)
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 58;
        gp.obj[mapNum][i].worldY = gp.tileSize * 56;
        i++;
        gp.obj[mapNum][i] = new OBJ_Ammo(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 57;
        gp.obj[mapNum][i].worldY = gp.tileSize * 56;
        i++;

        // TOOLS
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 62;
        gp.obj[mapNum][i].worldY = gp.tileSize * 40;
        i++;

        gp.obj[mapNum][i] = new OBJ_Shield_Iron(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 64;
        gp.obj[mapNum][i].worldY = gp.tileSize * 40;
        i++;

        // RESOURCES
        gp.obj[mapNum][i] = new OBJ_Medicine(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 64;
        gp.obj[mapNum][i].worldY = gp.tileSize * 44;
        i++;

        // test a new object in the ship map
//        mapNum = 1;
//        gp.obj[mapNum][i] = new OBJ_Medicine(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize * 52;
//        gp.obj[mapNum][i].worldY = gp.tileSize * 50;

        // DOORS
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 63;
        gp.obj[mapNum][i].worldY = gp.tileSize * 42;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 63;
        gp.obj[mapNum][i].worldY = gp.tileSize * 46;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 39;
        i++;

        // CHEST
        gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 36;
    }

    public void setNPC() {
        int i = 0;
        int mapNum = 0;

        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 60;
        gp.npc[mapNum][i].worldY = gp.tileSize * 48;

        mapNum = 1;
        gp.npc[mapNum][i] = new NPC_Smuggler(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 48;
        gp.npc[mapNum][i].worldY = gp.tileSize * 50;
    }

    public void setMonster() {
        int i = 0;
        int mapNum = 0;

        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 50;
        gp.monster[mapNum][i].worldY = gp.tileSize * 48;
        i++;

        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 50;
        gp.monster[mapNum][i].worldY = gp.tileSize * 46;
    }

    public void setInteractiveTile() {
        int i = 0;
        int mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 52, 56); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 56); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 57);
    }
}
