package game.graphics;

import game.level.tile.Tile;

import java.util.Random;


public class Screen {

    public int width, height;
    public int[] pixels;
    public int xOffset, yOffset;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] tiles = new int[MAP_SIZE*MAP_SIZE];


    private Random random = new Random();

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height * 2]; // 50,625 why *2 remove and debug with break point
        //for(int i=0; i< MAP_SIZE*MAP_SIZE; i++){
            //tiles[i]=random.nextInt(0xffffff); // give each tile a random color when class is created
            // create a black square to show loop
            //tiles[0]=0;tiles[1]=0;tiles[64]=0;tiles[1+64]=0;
        //}
    }

    // Clean the screen
    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i]=0;
        }
    }

    public void renderTile(int xp, int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;

        for(int y=0; y<tile.sprite.SIZE; y++){
            int ya = y + yp; // absolute position
            for(int x=0; x<tile.sprite.SIZE; x++){
                int xa = x + xp;
                if(xa<-tile.sprite.SIZE || xa>=width || ya<0 || ya>=width) break; // if tile is off the screen don't render
                if(xa<0) xa =0;
                // pixels[]   = pixels on screen
                // tile.s.p[] = what pixels in sprite to render
                pixels[xa + ya * width] = tile.sprite.pixels[ x + y * tile.sprite.SIZE]; // render sprite
            }
        }
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
