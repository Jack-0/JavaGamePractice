package game.level;

import game.graphics.Screen;
import game.graphics.Sprite;
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

        // render the map
        // pixel precision to tile precision
        int x0 = xScroll >> 4;// left side of the screen and turn the 32 pixels into 1 tile
        int x1 = (xScroll + screen.width + 16) >> 4; // right side of screen, 16 ensures we don't have a black bar
        int y0 = yScroll >> 4; // top
        int y1 = (yScroll + screen.width + 16) >> 4; // bottom, +16 to remove black bar this should be tile.SIZE really!

        // render from the top left to bottom right of screen.width \/
        for(int y=y0; y<y1; y++){
            for(int x=x0; x<x1; x++){
                getTile(x,y).render(x,y,screen); // this works because we have tile precision
            }
        }
    }

    public Tile getTile(int x, int y){
        if( x<0 || y<0 || x>= width || y>= height) return  Tile.voidTile;
        if(tiles[x+y*width]==0) return Tile.grass;
        if(tiles[x+y*width]==1) return Tile.grassStones;
        if(tiles[x+y*width]==2) return Tile.grassStones; // was water
        if(tiles[x+y*width]==3) return Tile.rareResource;

        //return Tile.water;
        return Tile.voidTile;
    }
}
