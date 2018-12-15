package game.entity.mob;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.input.Keyboard;

import java.awt.*;

public class Player extends Mob{

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;


    public Player(Keyboard input){
        this.input = input;
    }

    public Player(Keyboard input, int x, int y){
        this.input = input;
        this.x = x;
        this.y = y;
        sprite = Sprite.playerUp0;
    }

    public void update(){
        int xa=0, ya=0;
        if(input.up)    ya--;
        if(input.down)  ya++;
        if(input.left)  xa--;
        if(input.right) xa++;

        if(anim<1000)
            anim++;
        else
            anim=0;

        if(xa != 0 || ya != 0) {
            move(xa, ya); // move adds xa and ya to player.x and player.y
            walking = true;
        }
        else {
            walking =false;
        }
    }

    public void render(Screen screen){
        int xx = x - 16;
        int yy = y - 16;
        switch (dir)
        {
            case 0:
                sprite = Sprite.playerUp0;
                if(walking && (anim % 20 > 10))
                    sprite = Sprite.playerUp0;
                else
                    sprite = Sprite.playerUp1;
                break;
            case 1:
                sprite = Sprite.playerRight0;
                if(walking && (anim % 20 > 10))
                    sprite = Sprite.playerRight0;
                else
                    sprite = Sprite.playerRight1;
                break;
            case 2:
                sprite = Sprite.playerDown0;
                if(walking && (anim % 20 > 10))
                    sprite = Sprite.playerDown0;
                else
                    sprite = Sprite.playerDown1;
                break;
            case 3:
                sprite = Sprite.playerLeft0;
                if(walking && (anim % 20 > 10))
                    sprite = Sprite.playerLeft0;
                else
                    sprite = Sprite.playerLeft1;
                break;
        }
        screen.renderPlayer(sprite,xx,yy);
        //screen.renderPlayer(sprite.player1,xx+16,yy);
        //screen.renderPlayer(sprite.player2,xx,yy+16);
        //screen.renderPlayer(sprite.player3,xx+16,yy+16);
    }
}
