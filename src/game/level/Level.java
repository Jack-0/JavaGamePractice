package game.level;

import game.graphics.Screen;
import game.level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width*height];
        generateLvl();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void generateLvl() {
    }

    private void loadLevel(String path) {
    }

    public void update() {
    }

    private void time(){
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        // pixel precision to tile precision
        int x0 = xScroll >> 4;// left side of the screen and turn the 32 pixels into 1 tile
        int x1 = (xScroll + screen.width) >> 4; // right side of screen
        int y0 = yScroll >> 4; // top
        int y1 = (yScroll + screen.width) >> 4; // bottom

        // render from the top left to bottom right of screen.width \/
        for(int y=y0; y<y1; y++){
            for(int x=x0; x<x1; x++){
                getTile(x,y).render(x,y,screen); // this works because we have tile precision
            }
        }
    }

    public Tile getTile(int x, int y){
        if(tiles[x+y*width]==0) return Tile.grass;
        return Tile.voidTile;
    }
}
