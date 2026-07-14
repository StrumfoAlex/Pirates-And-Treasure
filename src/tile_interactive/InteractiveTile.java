package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible = false;
    int col;
    int row;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        this.col = col;
        this.row = row;
    }

    public boolean isCorrectItem(Entity entity) {
        return false;
    }

    public void playSE() {}

    public InteractiveTile getDestroyedForm() {
        return null;
    }

    public void update() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
