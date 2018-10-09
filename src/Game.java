import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


// Game class
// just a comment . . .
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static int width = 300; // we will render at 300 and scale up to get a bigger image - better on resources
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;

    // raster stuff
    private BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game(){
        Dimension size = new Dimension(width * scale,height * scale);
        setPreferredSize(size);

        frame = new JFrame();
        screen = new Screen(width,height);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this,"Display");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(running){
            update(); // tick
            render();
        }
    }

    // game tick
    public void update(){
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();
        for(int i=0; i<pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics(); // link graphics to the buffer strategy
        // BEGIN: graphics code
        g.setColor(new Color(100,100,10));
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(image, 0,0, getWidth(), getHeight(), null); // screen class test
        // END: graphics code
        g.dispose(); // remove graphics at the end of the frame
        bs.show();
    }


    public static void main(String args[]){
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Java Game Practice");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
