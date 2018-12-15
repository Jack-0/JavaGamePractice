package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

// TILES render themselves
public class Tile {

    public int x,y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass); // grass tile is static is does not change
    public static Tile grassStones = new GrassTile(Sprite.grassStones); // grass tile is static is does not change
    public static Tile water = new VoidTile(Sprite.water);
    public static Tile rareResource = new VoidTile(Sprite.rareResource);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);


    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){
    }

    public boolean solid(){
        return false;
    }


}
