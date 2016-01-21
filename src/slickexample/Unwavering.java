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

//    public Item healthpotion, healthpotion1;
//    public Item1 speedpotion, speedpotion1;
//    public ItemWin antidote;
//    public Ninja ninja;
    public Enemy indianBow1;
    //public Enemy indian2;
    //public Enemy indian3;
    Treasure smallprize;
    Treasure grandprize;

    public ArrayList<Treasure> treasures = new ArrayList();

    public ArrayList<Enemy> enemies = new ArrayList();
    public ArrayList<Enemy> enemiesBow = new ArrayList();
    
    public ArrayList<Arrow> arrows = new ArrayList();

    public ArrayList<Item> stuff = new ArrayList();

    public ArrayList<Item1> stuff1 = new ArrayList();

    public ArrayList<ItemWin> stuffwin = new ArrayList();

    private boolean[][] hostiles;

    private static TiledMap grassMap;

    private static AppGameContainer app;

    private static Camera camera;

    public static int counter = 0;

    // Player stuff
    private Animation sprite, walkUp, walkDown, walkLeft, walkRight, faceUp, faceDown, faceLeft, faceRight,
            thrustUp, thrustDown, thrustLeft, thrustRight, wait;
    boolean attacking = false;
    int attackCounter = 0;
    String direction = "down";
    /**
     *
     * The collision map indicating which tiles block movement - generated based
     *
     * on tile properties
     */
    // changed to match size of sprites & map
    private static final int tileSize = 64;
    private static final int tilesX = 50;
    private static final int tilesY = 100;
    // screen width and height won't change
    private static final int SCREEN_WIDTH = tilesX * tileSize;
    private static int currentsteps = 0;
    private static final int SCREEN_HEIGHT = tilesY * tileSize;

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
                "res/explorerSpear.png", 64, 64, 0);

        // System.out.println("Horizontal count: "
        // +runningSS.getHorizontalCount());
        // System.out.println("Vertical count: " +runningSS.getVerticalCount());
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

        // wait.addFrame(runningSS.getSprite(2, 14), 733);
        // wait.addFrame(runningSS.getSprite(5, 14), 333);
        sprite = walkDown;

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
        //hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        Item i = new Item(xAxis * tileSize, yAxis * tileSize);
                        stuff.add(i);
                        //stuff1.add(h);
                        //   hostiles[xAxis][yAxis] = true;
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
                        Item1 h = new Item1(xAxis * tileSize, yAxis * tileSize);
                        //	stuff.add(i);
                        stuff1.add(h);
                        //hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        //ninja = new Ninja(200, 200);
//        
//        healthpotion = new Item(100, 100);
//        healthpotion1 = new Item(450, 400);
//        stuff.add(healthpotion);
//        stuff.add(healthpotion1);
        smallprize = new Treasure(450, 250);
        grandprize = new Treasure(200, 250);

        treasures.add(smallprize);
        treasures.add(grandprize);

        indianBow1= new Enemy(448, 64, "down", false);
        // indian2= new Enemy(576, 192);
        // indian3= new Enemy(650, 100);
        enemies.add(indianBow1);
        indianBow1.configBow();
        //enemies.add(indian2);
        //enemies.add(indian3);
//        speedpotion = new Item1(100, 150);
//        speedpotion1 = new Item1(450, 100);
//        stuff1.add(speedpotion);
//        stuff1.add(speedpotion1);
//
//        antidote = new ItemWin(3004, 92);
//        stuffwin.add(antidote);
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

        //g.draw(Player.rect);
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

//        for (Enemy e : enemies) {
//
//            g.drawString("" + e.getID(), e.Bx, e.By);
//
//            g.drawString("" + e.health, e.Bx, e.By + tileSize / 2);
//
//            //g.draw(e.rect);
//
//        }
        
        

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

        double rightlimit = (grassMap.getWidth() * tileSize) - (tileSize * 0.75);

        //System.out.println("Right limit: " + rightlimit);
        float projectedright = Player.x + fdelta + tileSize;

        boolean cangoright = projectedright < rightlimit;
for (Arrow a : arrows) {
            if(a.direction == "up"){
                a.y -= Arrow.speed;
            }
            else if(a.direction == "down"){
                a.y += Arrow.speed;
            }
            else if(a.direction == "right"){
                a.x += Arrow.speed;
            }
            else if(a.direction == "left"){
                a.x -= Arrow.speed;
            }
        }
        //there are two types of fixes. A kludge and a hack. This is a kludge.
        if (attackCounter == 0) {
            attacking = false;
        }
        if (!(currentsteps > 0) && input.isKeyDown(Input.KEY_Z)) {
            attacking = true;
            attackCounter = 8;
            if (direction == "up") {
                sprite = thrustUp;
            } else if (direction == "down") {
                sprite = thrustDown;
            } else if (direction == "left") {
                sprite = thrustLeft;
            } else if (direction == "right") {
                sprite = thrustRight;
            }
        }
        if (!(currentsteps > 0) && !attacking) {

            if (input.isKeyDown(Input.KEY_UP)) {

                if (direction != "up") {
                    sprite = faceUp;
                    direction = "up";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x, Player.y - tileSize))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = walkUp;
                    }

                }
            } else if (input.isKeyDown(Input.KEY_DOWN)) {

                if (direction != "down") {
                    sprite = faceDown;
                    direction = "down";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x, Player.y + tileSize))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = walkDown;
                    }

                }

            } else if (input.isKeyDown(Input.KEY_LEFT)) {

                if (direction != "left") {
                    sprite = faceLeft;
                    direction = "left";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x - tileSize, Player.y))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = walkLeft;
                    }

                }

            } else if (input.isKeyDown(Input.KEY_RIGHT)) {

                if (direction != "right") {
                    sprite = faceRight;
                    direction = "right";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x + tileSize, Player.y))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = walkRight;
                    }

                }
            }
        } else {
            sprite.update(delta);
            if (attackCounter > 0) {
                attackCounter--;
            }
            for (Enemy e : enemies) {

            arrows.add(new Arrow(e.getskX() + 22, e.getskY(), e.direction));

        }
            
            if (!attacking) {
                currentsteps -= 1;
                if (direction == "up") {

                    Player.y -= Player.speed;

                } else if (direction == "down") {
                    Player.y += Player.speed;
                } else if (direction == "left") {
                    Player.x -= Player.speed;
                } else if (direction == "right") {
                    Player.x += Player.speed;
                }

            }
            moveenemies();
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
        
        for (Arrow a : arrows) {

            if (Player.rect.intersects(a.hitbox)) {
                //System.out.println("yay");
                if (a.isvisible) {

                    Player.health -= 20;
                    a.isvisible = false;
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
        if(!arrows.isEmpty()){ 
            System.out.println("check arrows");
            
        for (Arrow a : arrows) {
            System.out.println(a.x + " , " + a.y);
            
            
            try {
               //  a.rect.setLocation(a.gethitboxX(), a.gethitboxY());
            }
           catch (IndexOutOfBoundsException e ){
               System.out.println("oops");
           }

        }}

        for (Enemy e : enemies) {

            if (Player.rect.intersects(e.rect)) {

                Player.health -= 100;

            }

        }

        for (int en1 = 0;
                en1 < Enemy.getNumberOfEnemies();
                en1++) {

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
        Player.health -= counter / 1000;
        if (Player.health
                <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (Player.gold
                >= 200) {
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

        int xBlock = (int) tx / tileSize;

        int yBlock = (int) ty / tileSize;

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
