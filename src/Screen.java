public class Screen {

    private int width, height;
    public int[] pixels;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height]; // 50,400
    }

    // Clean the screen
    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i]=0;
        }
    }
    int counter=0, time=0;

    public void render(){
        counter++;
        if(counter % 100 == 0){
            time++;
        }
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                //pixels[x+y*width] = 0xff00ff;
                pixels[time+time*width] = 0xff000ff;
            }
        }
    }

}
