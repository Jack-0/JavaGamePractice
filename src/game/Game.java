package game;

import game.entity.mob.Player;
import game.input.Keyboard;
import game.graphics.Screen;
import game.level.Level;
import game.level.RandomLevel;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

// Game class
// just a comment . . .
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static String title = "My Game";
    public static int width = 400; // we will render at 300 and scale up to get a bigger image - better on resources
    public static int height = width / 16 * 9; // 168.5
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Keyboard key;
    private Screen screen;
    private Level level;
    private Player player;

    private int fps = 0;

    public void setFps(int fps) {this.fps = fps;}
    public int getFps() {return fps;}

    // raster stuff
    private BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game(){
        Dimension size = new Dimension(width * scale,height * scale);
        setPreferredSize(size);

        frame = new JFrame();
        screen = new Screen(width,height);
        key = new Keyboard();
        level = new RandomLevel(64,64); // new level that's 64 by 64 tiles
        player = new Player(key);
        addKeyListener(key);
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

        requestFocus();

        long timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;

        int frames = 0;
        int updates = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1) { // will only happen 60 times a second
                update(); // tick
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){ // if a second has passed
                timer += 1000;
                setFps(frames);
                frames = updates = 0;
            }
        }
    }

    // game tick
    public void update(){
        key.update();
        player.update();
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        // for player offset
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;

        level.render(xScroll,yScroll,screen);
        player.render(screen);

        for(int i=0; i<pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics(); // link graphics to the buffer strategy
        g.setColor(new Color(100,100,10));
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(image, 0,0, getWidth(), getHeight(), null); // screen class test

        // TODO: below is debug code for development
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica",1,14));
        g.drawString("fps: " + getFps(), 2, 12);
        g.drawString("x:    " + player.x, 2, 26);
        g.drawString("y:    " + player.y, 2, 40);
        // end dev ease code

        g.dispose(); // remove graphics at the end of the frame
        bs.show();
    }


    public static void main(String args[]){
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
