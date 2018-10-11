package game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("textures/sprite_sheet.png",256);

    public SpriteSheet(String path, int size){
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        load(path);
    }

    private void load(String path){
        try {
            //BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            BufferedImage image = ImageIO.read(new File("/home/shinji/IdeaProjects/JavaGamePractice/res/textures/sprite_sheet.png"));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0,w,h,pixels,0,w); // put image into the pixel array
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
