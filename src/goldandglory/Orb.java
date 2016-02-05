package goldandglory;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jconklin2391
 */
public class Orb {

    //public static int speed = 8;
    static int orbWidth = 32;
    static int orbHeight = 32;
    
    private float positionX;
    private float positionY;
    private boolean visible;
    Image orbImage;
    Shape orbHitbox;

    Orb(float posX, float posY) throws SlickException{
        positionX = posX;
        positionY = posY;
        visible = true;
        orbImage = new Image("res/arrow.png");
        orbHitbox = new Rectangle(positionX + (orbWidth / 2), positionY + (orbHeight / 2), orbWidth, orbHeight);
    }
    
    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setHitBox() {
        this.orbHitbox = new Rectangle(this.getPositionX() + (orbWidth / 2), this.getPositionY()+ (orbHeight / 2), orbWidth, orbHeight);
    
    }
    
}
