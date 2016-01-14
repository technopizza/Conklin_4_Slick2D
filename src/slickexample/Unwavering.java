package slickexample;

import org.newdawn.slick.state.*;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.Font;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.TrueTypeFont;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;

public class Unwavering extends BasicGameState {

    public Item healthpotion, healthpotion1;
    public Item1 speedpotion, speedpotion1;
    public ItemWin antidote;
    public Ninja ninja;
    public Enemy indian1;
    public Enemy indian2;
    public Enemy indian3;
    
    Treasure smallprize;
    Treasure grandprize;

    public ArrayList<Treasure> treasures = new ArrayList();
    
    public ArrayList<Enemy> enemies = new ArrayList();
    
    public ArrayList<Item> stuff = new ArrayList();

    public ArrayList<Item1> stuff1 = new ArrayList();

    public ArrayList<ItemWin> stuffwin = new ArrayList();

    private boolean[][] hostiles;

    private static TiledMap grassMap;

    private static AppGameContainer app;

    private static Camera camera;

    public static int counter = 0;

    // Player stuff
    private Animation sprite, up, down, left, right, wait;

    /**
     *
     * The collision map indicating which tiles block movement - generated based
     *
     * on tile properties
     */
    // changed to match size of sprites & map
    private static final int SIZE = 64;

    // screen width and height won't change
    private static final int SCREEN_WIDTH = 1000;

    private static final int SCREEN_HEIGHT = 750;

    public Unwavering(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);

        gc.setShowFPS(false);

        // *******************
        // Scenerey Stuff
        // ****************
        grassMap = new TiledMap("res/d4.tmx");

        // Ongoing checks are useful
        System.out.println("Tile map is this wide: " + grassMap.getWidth());

        camera = new Camera(gc, grassMap);

        // *********************************************************************************
        // Player stuff --- these things should probably be chunked into methods
        // and classes
        // *********************************************************************************
        SpriteSheet runningSS = new SpriteSheet(
                "res/explorer.png", 64, 64, 0);

        // System.out.println("Horizontal count: "
        // +runningSS.getHorizontalCount());
        // System.out.println("Vertical count: " +runningSS.getVerticalCount());
        up = new Animation();

        up.setAutoUpdate(true);

        up.addFrame(runningSS.getSprite(0, 8), 330);

        up.addFrame(runningSS.getSprite(1, 8), 330);

        up.addFrame(runningSS.getSprite(2, 8), 330);

        up.addFrame(runningSS.getSprite(3, 8), 330);

        up.addFrame(runningSS.getSprite(4, 8), 330);

        up.addFrame(runningSS.getSprite(5, 8), 330);

        up.addFrame(runningSS.getSprite(6, 8), 330);

        up.addFrame(runningSS.getSprite(7, 8), 330);

        up.addFrame(runningSS.getSprite(8, 8), 330);

        down = new Animation();

        down.setAutoUpdate(true);

        down.addFrame(runningSS.getSprite(0, 10), 330);

        down.addFrame(runningSS.getSprite(1, 10), 330);

        down.addFrame(runningSS.getSprite(2, 10), 330);

        down.addFrame(runningSS.getSprite(3, 10), 330);

        down.addFrame(runningSS.getSprite(4, 10), 330);

        down.addFrame(runningSS.getSprite(5, 10), 330);

        down.addFrame(runningSS.getSprite(6, 10), 330);

        down.addFrame(runningSS.getSprite(7, 10), 330);

        down.addFrame(runningSS.getSprite(8, 10), 330);

        left = new Animation();

        left.setAutoUpdate(true);

        left.addFrame(runningSS.getSprite(0, 9), 330);

        left.addFrame(runningSS.getSprite(1, 9), 330);

        left.addFrame(runningSS.getSprite(2, 9), 330);

        left.addFrame(runningSS.getSprite(3, 9), 330);

        left.addFrame(runningSS.getSprite(4, 9), 330);

        left.addFrame(runningSS.getSprite(5, 9), 330);

        left.addFrame(runningSS.getSprite(6, 9), 330);

        left.addFrame(runningSS.getSprite(7, 9), 330);

        left.addFrame(runningSS.getSprite(8, 9), 330);

        right = new Animation();

        right.setAutoUpdate(true);

        right.addFrame(runningSS.getSprite(0, 11), 330);

        right.addFrame(runningSS.getSprite(1, 11), 330);

        right.addFrame(runningSS.getSprite(2, 11), 330);

        right.addFrame(runningSS.getSprite(3, 11), 330);

        right.addFrame(runningSS.getSprite(4, 11), 330);

        right.addFrame(runningSS.getSprite(5, 11), 330);

        right.addFrame(runningSS.getSprite(6, 11), 330);

        right.addFrame(runningSS.getSprite(7, 11), 330);

        right.addFrame(runningSS.getSprite(8, 11), 330);

        wait = new Animation();

        wait.setAutoUpdate(true);

        wait.addFrame(runningSS.getSprite(0, 14), 733);

        wait.addFrame(runningSS.getSprite(1, 14), 733);

        wait.addFrame(runningSS.getSprite(2, 14), 733);

        wait.addFrame(runningSS.getSprite(3, 14), 733);

        // wait.addFrame(runningSS.getSprite(2, 14), 733);
        // wait.addFrame(runningSS.getSprite(5, 14), 333);
        sprite = wait;

        // *****************************************************************
        // Obstacles etc.
        // build a collision map based on tile properties in the TileD map
        Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        // System.out.println("Map height:" + grassMap.getHeight());
        // System.out.println("Map width:" + grassMap.getWidth());
        // There can be more than 1 layer. You'll check whatever layer has the
        // obstacles.
        // You could also use this for planning traps, etc.
        // System.out.println("Number of tile layers: "
        // +grassMap.getLayerCount());
        System.out.println("The grassmap is " + grassMap.getWidth() + " by "
                + grassMap.getHeight());

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                // int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                // Why was this changed?
                // It's a Different Layer.
                // You should read the TMX file. It's xml, i.e.,human-readable
                // for a reason
                int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");

                if ("true".equals(value)) {

                    System.out.println("The tile at (" + xAxis + ", "
                            + yAxis + ") is blocked.");

                    Blocked.blocked[xAxis][yAxis] = true;

                }

            }

        }

        System.out.println("Array length" + Blocked.blocked[0].length);

        // A remarkably similar process for finding hostiles
        hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        Item i = new Item(xAxis * SIZE, yAxis * SIZE);
                        stuff.add(i);
                        //stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (xBlock % 9 == 0 && yBlock % 25 == 0) {
                        Item1 h = new Item1(xAxis * SIZE, yAxis * SIZE);
                        //	stuff.add(i);
                        stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }
        
        

        //ninja = new Ninja(200, 200);
        
        healthpotion = new Item(100, 100);
        healthpotion1 = new Item(450, 400);
        stuff.add(healthpotion);
        stuff.add(healthpotion1);

        smallprize = new Treasure(450, 250);
        grandprize = new Treasure(200, 250);
        
        treasures.add(smallprize);
        treasures.add(grandprize);
        
        
        indian1= new Enemy(450, 100);
        //indian2= new Enemy(550, 100);
       // indian3= new Enemy(650, 100);
        
        enemies.add(indian1);
        //enemies.add(indian2);
        //enemies.add(indian3);
        
        speedpotion = new Item1(100, 150);
        speedpotion1 = new Item1(450, 100);
        stuff1.add(speedpotion);
        stuff1.add(speedpotion1);

        antidote = new ItemWin(3004, 92);
        stuffwin.add(antidote);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) Player.x, (int) Player.y);

        camera.drawMap();

        camera.translateGraphics();

        // it helps to add status reports to see what's going on
        // but it gets old quickly
        // System.out.println("Current X: " +player.x + " \n Current Y: "+ y);
        sprite.draw((int) Player.x, (int) Player.y);

        //g.drawString("x: " + (int)player.x + "y: " +(int)player.y , player.x, player.y - 10);
        g.drawString("Health: " + Player.health / 1000, camera.cameraX + 10,
                camera.cameraY + 10);

        g.drawString("speed: " + (int) (Player.speed * 10), camera.cameraX + 10,
                camera.cameraY + 25);
        g.drawString("treasure: " + (int) (Player.gold), camera.cameraX + 10,
                camera.cameraY + 40);

        g.draw(Player.rect);
        g.drawString("time passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
        // moveenemies();

//        if (ninja.isvisible) {
//            ninja.currentImage.draw(ninja.x, ninja.y);
//                // draw the hitbox
//            //g.draw(i.hitbox);
//
//        }

        //moveenemies();

        drawenemies();


        for (Enemy e : enemies) {

            g.drawString("" + e.getID(), e.Bx, e.By);

            g.drawString("" + e.health, e.Bx, e.By + SIZE / 2);

            g.draw(e.rect);

        }

        
        for (Treasure b : treasures) {
            if (b.isvisible) {
                b.currentImage.draw(b.x, b.y);
                // draw the hitbox
                //g.draw(i.hitbox);

            }
        }
        
        for (Item i : stuff) {
            if (i.isvisible) {
                i.currentImage.draw(i.x, i.y);
                // draw the hitbox
                //g.draw(i.hitbox);

            }
        }

        for (Item1 h : stuff1) {
            if (h.isvisible) {
                h.currentImage.draw(h.x, h.y);
                // draw the hitbox
                //g.draw(h.hitbox);

            }
        }

        for (ItemWin w : stuffwin) {
            if (w.isvisible) {
                w.currentImage.draw(w.x, w.y);
                // draw the hitbox
                //g.draw(w.hitbox);

            }
        }

    }

       public void update(GameContainer gc, StateBasedGame sbg, int delta)

            throws SlickException {

        Input input = gc.getInput();

        float fdelta = delta * 0.4f;

        Player.setpdelta(fdelta);

        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

        //System.out.println("Right limit: " + rightlimit);

        float projectedright = Player.x + fdelta + SIZE;

        boolean cangoright = projectedright < rightlimit;


        //there are two types of fixes. A kludge and a hack. This is a kludge.

        if (input.isKeyDown(Input.KEY_UP)) {

            sprite = up;

            float fdsc = (float) (fdelta - (SIZE * .15));


            if (!(isBlocked(Player.x, Player.y - fdelta) || isBlocked((float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {

                sprite.update(delta);

                // The lower the delta the slower the sprite will animate.


                Player.y -= fdelta;

                moveenemies();

            }

        } else if (input.isKeyDown(Input.KEY_DOWN)) {

            sprite = down;


            if (!isBlocked(Player.x, Player.y + SIZE + fdelta)

                    || !isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta)) {

                sprite.update(delta);

                Player.y += fdelta;

                moveenemies();

            }

        } else if (input.isKeyDown(Input.KEY_LEFT)) {

            sprite = left;


            if (!(isBlocked(Player.x - fdelta, Player.y) || isBlocked(Player.x

                    - fdelta, Player.y + SIZE - 1))) {

                sprite.update(delta);

                Player.x -= fdelta;

                moveenemies();

            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {

            sprite = right;


            //the boolean-kludge-implementation

            if (cangoright && (!(isBlocked(Player.x + SIZE + fdelta,

                    Player.y) || isBlocked(Player.x + SIZE + fdelta, Player.y + SIZE - 1)))) {

                sprite.update(delta);

                Player.x += fdelta;

                moveenemies();

            } //else { System.out.println("Right limit reached: " + rightlimit);}

        }


        Player.rect.setLocation(Player.getplayershitboxX(), Player.getplayershitboxY());

for (Treasure i : treasures) {

            if (Player.rect.intersects(i.hitbox)) {
                //System.out.println("yay");
                if (i.isvisible) {

                    Player.gold += 100;
                    i.isvisible = false;
                }

            }
        }

        for (Item i : stuff) {

            if (Player.rect.intersects(i.hitbox)) {
                //System.out.println("yay");
                if (i.isvisible) {

                    Player.health += 10000;
                    i.isvisible = false;
                }

            }
        }

        for (Item1 h : stuff1) {

            if (Player.rect.intersects(h.hitbox)) {
                //System.out.println("yay");
                if (h.isvisible) {

                    Player.speed += 0.2f;
                    h.isvisible = false;
                }

            }
        }

        for (ItemWin w : stuffwin) {

            if (Player.rect.intersects(w.hitbox)) {
                //System.out.println("yay");
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }

            }
        }
        
        
        
        for (Enemy e : enemies) {

            e.rect.setLocation(e.getskhitboxX(), e.getskhitboxY());

        }


        for (Enemy e : enemies) {

            if (Player.rect.intersects(e.rect)) {

                Player.health -= 100;

            }

        }

        for (int en1 = 0; en1 < Enemy.getNumberOfEnemies(); en1++) {

            for (int en2 = 0; en2 < Enemy.getNumberOfEnemies(); en2++) {

                if (enemies.get(en1).rect.intersects(enemies.get(en2).rect)) {

                    if (enemies.get(en1).getID() != enemies.get(en2).getID()) {

                        if (enemies.get(en1).health > 0 && enemies.get(en2).health > 0) {

                            enemies.get(en1).health -= 1;

                            enemies.get(en2).health -= 1;

                        }

                    }

                }


            }

        }
Player.health -= counter/1000;
		if(Player.health <= 0){
			makevisible();
			sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
                if(Player.gold >= 200){
			makevisible();
                        Player.gold = 0;
			sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}

    }


    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (Item1 h : stuff1) {

            h.isvisible = true;
        }

        for (Item i : stuff) {

            i.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return Blocked.blocked[xBlock][yBlock];

        // this could make a better kludge
    }

    
    private void moveenemies() throws SlickException {

        for (Enemy e : enemies) {

            //  e.setdirection();

            e.move();

        }


    }


    private void drawenemies() throws SlickException {

        try {

            for (Enemy e : enemies) {

                //System.out.println("The current selection is: " +e.currentanime);

                e.currentanime.draw(e.Bx, e.By);


            }

        } catch (IndexOutOfBoundsException e) {

            System.err.println("IndexOutOfBoundsException: "

                    + e.getMessage());

        }

    }
    
}
