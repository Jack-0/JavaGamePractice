import java.beans.ParameterDescriptor;
import java.util.Random;

public class Screen {

    private int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 8;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] tiles = new int[MAP_SIZE*MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height]; // 50,400
        for(int i=0; i< MAP_SIZE*MAP_SIZE; i++){
            tiles[i]=random.nextInt(0xffffff); // give each tile a random color when class is created
            tiles[0]=0; // set first tiles to black
        }
    }

    // Clean the screen
    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i]=0;
        }
    }

    public void render(int xOffset, int yOffset){ //TODO
        for(int y=0; y<height; y++){
            int yy = y+yOffset; // Todo . . . was yy=y;
            //if(yy < 0 || yy > height) break;
            for(int x=0; x<width; x++){
                int xx = x+xOffset; // Todo . . . was xx=x;
                //if(xx < 0 || xx > width) break;
                int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE; // ensure index
                                                                                                      // doesn't out_bnd
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }

}
