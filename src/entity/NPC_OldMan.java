package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        type = type_npc;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize,  gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize,  gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize,  gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize,  gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize,  gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize,  gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize,  gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize,  gp.tileSize);
    }
    public void setDialogue() {
        dialogues[0][0] = "Hello, young pirate!";
        dialogues[0][1] = "So you've come to this island to find the treasure?";
        dialogues[0][2] = "I used to be a great adventurer back in the day.\n" +
                        "But now I just stay here and talk to people like you.";

        dialogues[1][0] = "You seem like you need some help, young pirate!\n" +
                        "So I have a deal for you!";
        dialogues[1][1] = "Here is my house where you can find some old stuff.\n" +
                        "You can have all that is in the house with one condition.";
        dialogues[1][2] = "If you manage to find the treasure you need to give me\na part of it!";

        dialogues[2][0] = """
                Go around on the island and find keys to unlock
                door houses and chests.
                And maybe some clues to find the map!""";
        dialogues[2][1] = "Good luck on your journey!";
    }

    public void setAction() {
        if (onPath) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);
        } else {
            actionLockCounter++;

            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; // 1-100

                if (i <= 25) {
                    direction = "up";
                } else if (i <= 50) {
                    direction = "down";
                } else if (i <= 75) {
                    direction = "left";
                } else {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
    }

    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
//            dialogueSet = 0; - trow in a specific time in the dialogue
            dialogueSet--; // show only the las one
        }

        onPath = false;
    }
}
