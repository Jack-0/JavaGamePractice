import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

    public static int width = 300; // we will render at 300 and scale up to get a bigger image - better on resources
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    public Game(){
        Dimension size = new Dimension(width * scale,height * scale);
        setPreferredSize(size);

        frame = new JFrame();
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

        }
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
