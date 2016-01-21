/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slickexample;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


/**
 *
 * @author jconklin2391
 */
public class Arrow {
     public float x;
	public float y;
	public boolean isvisible = true;
	Image currentImage;
	Shape hitbox;
	Image arrowImage = new Image("res/arrow.png");
        String direction;
        static int speed = 8;
        private int startX, startY, width = 22, height = 64;

    float hitboxX = x+22;

    float hitboxY = this.y;

    
    
    public Shape rect;

	Arrow(float a, float b, String dir) throws SlickException {
		this.x = a;
		this.y = b;
                this.direction=dir;
		this.hitbox = new Rectangle(a, b, width, height);// 64 is the width of the item
		this.currentImage = arrowImage;
                
                
}
        public  float getX() {

		return this.x;

	}

	public  float getY() {

		return y;

	}

	public  float gethitboxX() {

		return x + 22;

	}

	public  float gethitboxY() {

		return y;
	}

	public  void sethitboxX() {

		hitboxX = gethitboxX();

	}

	public  void setplayershitboxY() {

		hitboxY = gethitboxY();

	}
        
        
}
