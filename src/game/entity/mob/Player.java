package game.entity.mob;

import game.graphics.Screen;
import game.input.Keyboard;

public class Player extends Mob{

    private Keyboard input;


    public Player(Keyboard input){
        this.input = input;
    }

    public Player(Keyboard input, int x, int y){
        this.input = input;
        this.x = x;
        this.y = y;
    }

    public void update(){
        int xa=0, ya=0;
        if(input.up)    ya--;
        if(input.down)  ya++;
        if(input.left)  xa--;
        if(input.right) xa++;

        if(xa != 0 || ya != 0) move(xa, ya); // move adds xa and ya to player.x and player.y
    }

    public void render(Screen screen){
        int xx = x - 16;
        int yy = y - 16;
        screen.renderPlayer(sprite.player0,xx,yy);
        screen.renderPlayer(sprite.player1,xx+16,yy);
        screen.renderPlayer(sprite.player2,xx,yy+16);
        screen.renderPlayer(sprite.player3,xx+16,yy+16);
    }
}
