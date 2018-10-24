package game.graphics;

public class Sprite {

    public final int SIZE;
    private int x,y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
    public static Sprite grassStones = new Sprite(16,1,0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16,0x6699ff);

    public static Sprite player0 = new Sprite(16,0,3,SpriteSheet.tiles);
    public static Sprite player1 = new Sprite(16,1,3,SpriteSheet.tiles);
    public static Sprite player2 = new Sprite(16,0,4,SpriteSheet.tiles);
    public static Sprite player3 = new Sprite(16,1,4,SpriteSheet.tiles);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        this.pixels = new int[SIZE*SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int colour){
        SIZE = size;
        pixels= new int[SIZE*SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for(int i=0; i<SIZE*SIZE; i++){
            pixels[i] = colour;
        }
    }

    // extract sprite from sprite sheet
    private void load(){
        for(int y = 0; y<SIZE; y++){
            for(int x = 0; x<SIZE; x++){
                pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];
            }
        }
    }


}
