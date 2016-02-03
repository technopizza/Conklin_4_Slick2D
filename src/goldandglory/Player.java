/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldandglory;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author jconklin2391
 */
public class Player {
    
	public static float x = 128f;

	public static float y = 128f;

	public static int health = 100000;
        
        public static int gold = 0;
	
	public static int speed = 2;

	static float hitboxX = x;

	static float hitboxY = y;

	private static int startX, startY, width = 62, height = 62;

	public static Shape rect = new Rectangle(getplayershitboxX(),
			getplayershitboxY(), width, height);

	public static float pdelta;

        static SpriteSheet runningSS;
        
        
	public static Animation playeranime;
        
        public static Animation walkUp, walkDown, walkLeft, walkRight, faceUp, faceDown, faceLeft, faceRight,
            thrustUp, thrustDown, thrustLeft, thrustRight, wait;
        
        
        
        static void spriteInit() throws SlickException{
            runningSS = new SpriteSheet("res/explorerSpear.png", 64, 64, 0);
            
                    faceUp = new Animation();
        faceUp.addFrame(runningSS.getSprite(0, 8), 110);

        walkUp = new Animation();

        walkUp.setAutoUpdate(false);
        walkUp.addFrame(runningSS.getSprite(0, 8), 200);
        walkUp.addFrame(runningSS.getSprite(1, 8), 200);

        walkUp.addFrame(runningSS.getSprite(2, 8), 200);

        walkUp.addFrame(runningSS.getSprite(3, 8), 200);

        walkUp.addFrame(runningSS.getSprite(4, 8), 200);

        walkUp.addFrame(runningSS.getSprite(5, 8), 200);

        walkUp.addFrame(runningSS.getSprite(6, 8), 200);

        walkUp.addFrame(runningSS.getSprite(7, 8), 200);

        walkUp.addFrame(runningSS.getSprite(8, 8), 200);

        faceDown = new Animation();
        faceDown.addFrame(runningSS.getSprite(0, 10), 330);

        walkDown = new Animation();

        walkDown.setAutoUpdate(false);

        walkDown.addFrame(runningSS.getSprite(0, 10), 200);
        walkDown.addFrame(runningSS.getSprite(1, 10), 200);

        walkDown.addFrame(runningSS.getSprite(2, 10), 200);

        walkDown.addFrame(runningSS.getSprite(3, 10), 200);

        walkDown.addFrame(runningSS.getSprite(4, 10), 200);

        walkDown.addFrame(runningSS.getSprite(5, 10), 200);

        walkDown.addFrame(runningSS.getSprite(6, 10), 200);

        walkDown.addFrame(runningSS.getSprite(7, 10), 200);

        walkDown.addFrame(runningSS.getSprite(8, 10), 200);

        faceLeft = new Animation();
        faceLeft.addFrame(runningSS.getSprite(0, 9), 330);

        walkLeft = new Animation();

        walkLeft.setAutoUpdate(false);

        walkLeft.addFrame(runningSS.getSprite(0, 9), 200);
        walkLeft.addFrame(runningSS.getSprite(1, 9), 200);

        walkLeft.addFrame(runningSS.getSprite(2, 9), 200);

        walkLeft.addFrame(runningSS.getSprite(3, 9), 200);

        walkLeft.addFrame(runningSS.getSprite(4, 9), 200);

        walkLeft.addFrame(runningSS.getSprite(5, 9), 200);

        walkLeft.addFrame(runningSS.getSprite(6, 9), 200);

        walkLeft.addFrame(runningSS.getSprite(7, 9), 200);

        walkLeft.addFrame(runningSS.getSprite(8, 9), 200);

        faceRight = new Animation();
        faceRight.addFrame(runningSS.getSprite(0, 11), 200);

        walkRight = new Animation();

        walkRight.setAutoUpdate(false);

        walkRight.addFrame(runningSS.getSprite(0, 11), 200);
        walkRight.addFrame(runningSS.getSprite(1, 11), 200);

        walkRight.addFrame(runningSS.getSprite(2, 11), 200);

        walkRight.addFrame(runningSS.getSprite(3, 11), 200);

        walkRight.addFrame(runningSS.getSprite(4, 11), 200);

        walkRight.addFrame(runningSS.getSprite(5, 11), 200);

        walkRight.addFrame(runningSS.getSprite(6, 11), 200);

        walkRight.addFrame(runningSS.getSprite(7, 11), 200);

        walkRight.addFrame(runningSS.getSprite(8, 11), 200);

        thrustRight = new Animation();

        thrustRight.setAutoUpdate(false);

        thrustRight.addFrame(runningSS.getSprite(0, 7), 150);
        thrustRight.addFrame(runningSS.getSprite(1, 7), 150);

        thrustRight.addFrame(runningSS.getSprite(2, 7), 150);

        thrustRight.addFrame(runningSS.getSprite(3, 7), 150);

        thrustRight.addFrame(runningSS.getSprite(4, 7), 150);

        thrustRight.addFrame(runningSS.getSprite(5, 7), 150);

        thrustRight.addFrame(runningSS.getSprite(6, 7), 150);

        thrustRight.addFrame(runningSS.getSprite(7, 7), 150);

        thrustLeft = new Animation();

        thrustLeft.setAutoUpdate(false);

        thrustLeft.addFrame(runningSS.getSprite(0, 5), 150);
        thrustLeft.addFrame(runningSS.getSprite(1, 5), 150);

        thrustLeft.addFrame(runningSS.getSprite(2, 5), 150);

        thrustLeft.addFrame(runningSS.getSprite(3, 5), 150);

        thrustLeft.addFrame(runningSS.getSprite(4, 5), 150);

        thrustLeft.addFrame(runningSS.getSprite(5, 5), 150);

        thrustLeft.addFrame(runningSS.getSprite(6, 5), 150);

        thrustLeft.addFrame(runningSS.getSprite(7, 5), 150);

        thrustUp = new Animation();

        thrustUp.setAutoUpdate(false);

        thrustUp.addFrame(runningSS.getSprite(0, 4), 150);
        thrustUp.addFrame(runningSS.getSprite(1, 4), 150);

        thrustUp.addFrame(runningSS.getSprite(2, 4), 150);

        thrustUp.addFrame(runningSS.getSprite(3, 4), 150);

        thrustUp.addFrame(runningSS.getSprite(4, 4), 150);

        thrustUp.addFrame(runningSS.getSprite(5, 4), 150);

        thrustUp.addFrame(runningSS.getSprite(6, 4), 150);

        thrustUp.addFrame(runningSS.getSprite(7, 4), 150);

        thrustDown = new Animation();

        thrustDown.setAutoUpdate(false);

        thrustDown.addFrame(runningSS.getSprite(0, 6), 150);
        thrustDown.addFrame(runningSS.getSprite(1, 6), 150);

        thrustDown.addFrame(runningSS.getSprite(2, 6), 150);

        thrustDown.addFrame(runningSS.getSprite(3, 6), 150);

        thrustDown.addFrame(runningSS.getSprite(4, 6), 150);

        thrustDown.addFrame(runningSS.getSprite(5, 6), 150);

        thrustDown.addFrame(runningSS.getSprite(6, 6), 150);

        thrustDown.addFrame(runningSS.getSprite(7, 6), 150);

        wait = new Animation();

        wait.setAutoUpdate(true);

        wait.addFrame(runningSS.getSprite(0, 14), 733);

        wait.addFrame(runningSS.getSprite(1, 14), 733);

        wait.addFrame(runningSS.getSprite(2, 14), 733);

        wait.addFrame(runningSS.getSprite(3, 14), 733);
            
        }
        
        

        
	public static void setpdelta(float somenum) {

		pdelta = somenum;

	}

	public static float getpdelta() {

		return pdelta;

	}

	public static float getplayersX() {

		return x;

	}

	public static float getplayersY() {

		return y;

	}

	public static float getplayershitboxX() {

		return x +1;

	}

	public static float getplayershitboxY() {

		return y + 1;
	}

	public static void setplayershitboxX() {

		hitboxX = getplayershitboxX();

	}

	public static void setplayershitboxY() {

		hitboxY = getplayershitboxY();

	}

}
