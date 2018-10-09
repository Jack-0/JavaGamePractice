public class Screen {

    private int width, height;
    public int[] pixels;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void render(){
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                pixels[x+y*width] = 0xff00ff;
                //pixels[20+30*width] = 0xff000ff;
                /* 1st row: x = 19  y=0  19 + (0*100 )= 19 where width = 100 . . .
                 * 2nd row: x = 19  y=1  19 + (1*100) = 119
                 * 3rd row: x = 19  y=2  19 + (2*100) = 219
                 */
            }
        }
    }

}
