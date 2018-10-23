package game.entity;

import game.graphics.Screen;
import game.level.Level;

import java.util.Random;

public class Entity {
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update(){
    }

    public void render(Screen screen){
    }

    public void remove() {
        // remove from level
        removed = true;
    }

    public boolean isRemoved() {return removed;}

}
