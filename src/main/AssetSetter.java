package main;

import entity.NPC_OldMan;
import entity.NPC_Smuggler;
import monster.MON_Boss;
import monster.MON_Skeleton;
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
        gp.obj[mapNum][i].worldX = gp.tileSize * 54;
        gp.obj[mapNum][i].worldY = gp.tileSize * 27;
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
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 41;
        i++;

        gp.obj[mapNum][i] = new OBJ_Shield_Iron(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 41;
        i++;

        gp.obj[mapNum][i] = new OBJ_Lamp(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 62;
        gp.obj[mapNum][i].worldY = gp.tileSize * 40;
        i++;

        gp.obj[mapNum][i] = new OBJ_Torch(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 62;
        gp.obj[mapNum][i].worldY = gp.tileSize * 44;
        i++;

        gp.obj[mapNum][i] = new OBJ_Tent(gp);
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

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 43;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 81;
        i++;

        // CHESTS
        gp.obj[mapNum][i] = new OBJ_Chest(gp); // any prize here
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 36;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp); // any prize here
        gp.obj[mapNum][i].setLoot(new OBJ_MapX(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 31;
        gp.obj[mapNum][i].worldY = gp.tileSize * 86;
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
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 53;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 65;
        i++;

        gp.monster[mapNum][i] = new MON_Skeleton(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 87;
        i++;

        gp.monster[mapNum][i] = new MON_Skeleton(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;

    }

    public void setFinalBattle() {
        int i = 0;
        int mapNum = 0;

        // spawn sidekicks

        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 82;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 82;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;
        i++;

        gp.monster[mapNum][i] = new MON_Skeleton(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 83;
        gp.monster[mapNum][i].worldY = gp.tileSize * 28;
        i++;

        gp.monster[mapNum][i] = new MON_Skeleton(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 83;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;

        // spawn the final boss
        gp.monster[mapNum][i] = new MON_Boss(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 84;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
    }

    public void spawnTreasureIfUnlocked() {
        if (!gp.treasureUnlocked) return;

        int mapNum = 0;
        int spawnX = gp.tileSize * 81;
        int spawnY = gp.tileSize * 29;

        for (int i = 0; i < gp.obj[mapNum].length; i++) {
            if (gp.obj[mapNum][i] != null && "Treasure".equals(gp.obj[mapNum][i].name)) {
                gp.treasureUnlocked = false;
                return;
            }
        }

        for (int i = 0; i < gp.obj[mapNum].length; i++) {
            if (gp.obj[mapNum][i] == null) {
                gp.obj[mapNum][i] = new OBJ_Treasure(gp);
                gp.obj[mapNum][i].worldX = spawnX;
                gp.obj[mapNum][i].worldY = spawnY;
                gp.treasureUnlocked = false;
                return;
            }
        }
    }

    public void setInteractiveTile() {
        int i = 0;
        int mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 52, 56); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 56); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 57); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 34, 78); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 35, 79); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 35, 80); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 80); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 81); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 80); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 81);
    }
}
