package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, attackKeyPressed, shotKeyPressed;
    //debug
    public boolean debug = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            playState(code);
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }

        // CHARACTER STATE
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }

        // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }

        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            else if (gp.ui.commandNum == 1) {
                // add later
            }
            else if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        // Option menu
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }

        //Debug for seeing how long it takes to draw
        if (code == KeyEvent.VK_O) {
            debug = !debug;
        }
        if (code == KeyEvent.VK_I) {
            switch (gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("/maps/world02.txt", 0);
                    break;
                case 1:
                    gp.tileM.loadMap("/maps/ship01.txt", 1);
                    break;
            }
        }

        // If player attack
        if (code == KeyEvent.VK_J) {
            attackKeyPressed = true;
        }
        if (code == KeyEvent.VK_K) {
            shotKeyPressed = true;
        }
    }
    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W ||  code == KeyEvent.VK_UP) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(4);
            }
        }
        if (code == KeyEvent.VK_A ||   code == KeyEvent.VK_LEFT) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(4);
            }
        }
        if (code == KeyEvent.VK_S ||  code == KeyEvent.VK_DOWN) {
            if (gp.ui.slotRow < 3) {
                gp.ui.slotRow++;
                gp.playSE(4);
            }
        }
        if (code == KeyEvent.VK_D ||  code == KeyEvent.VK_RIGHT) {
            if (gp.ui.slotCol < 5) {
                gp.ui.slotCol++;
                gp.playSE(4);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 4; break;
            case 2: maxCommandNum = 1; break;

        }

        if (code == KeyEvent.VK_W ||  code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(4);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S ||  code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(4);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A ||  code == KeyEvent.VK_LEFT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(4);
                }
                if (gp.ui.commandNum == 1 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(4);
                }
            }
        }
        if (code == KeyEvent.VK_D ||  code == KeyEvent.VK_RIGHT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(4);
                }
                if (gp.ui.commandNum == 1 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(4);
                }
            }
        }
    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W ||  code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(4);
        }
        if (code == KeyEvent.VK_S ||  code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(4);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.ui.commandNum = 0;
                gp.stopMusic();
                gp.restart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_J) {
            attackKeyPressed = false;
        }
        if (code == KeyEvent.VK_K) {
            shotKeyPressed = false;
        }
    }
}
