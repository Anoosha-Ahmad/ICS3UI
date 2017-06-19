package ics3ui;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author ${user}
 */
public class ICS3UI extends JComponent {
  
    // Height and Width of our game
    static final int WIDTH = 400;
    static final int HEIGHT = 600;

    // if the player is hit with
    boolean start = false;
    boolean dead = false;

    //game variables
    boolean rightPressed = false;
    boolean leftPressed = false;

    //creating a score variable
    int score = 0;

    // making a variable for the gravity ---> variabled of coin and bomb fall down
    int gravity = 0;

    //creating the player
    Rectangle player = new Rectangle(175, 550, 25, 25);

    // new colour
    Color darkRed = new Color(138, 57, 42);
    
    // the frame (For pixels)
    int frameCount = 0;

    // creating the coins
    ArrayList<Rectangle> coins = new ArrayList<>();

    // creating the bombs
    ArrayList<Rectangle> bombs = new ArrayList<>();
    
    //Title of the window
    String title = "DangerBomb";

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;

    // importing the  images 
    BufferedImage bomb = loadImage("bomb.png");
    BufferedImage Background = loadImage("wall.png");
    BufferedImage coin = loadImage("money.png");
   

    // YOUR GAME VARIABLES WOULD GO HERE
    // GAME VARIABLES END HERE   
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public ICS3UI() {
        //creating an array list for the bombs position - player needs to avoid
        bombs = new ArrayList<>();
        bombs.add(new Rectangle(75, 50, 25, 20));
        bombs.add(new Rectangle(0, 150, 25, 20));
        bombs.add(new Rectangle(150, 440, 25, 20));
        bombs.add(new Rectangle(250, 0, 25, 20));
        bombs.add(new Rectangle(150, 400, 25, 20));
        bombs.add(new Rectangle(280, 480, 25, 20));
        bombs.add(new Rectangle(350, 250, 25, 20));
        bombs.add(new Rectangle(275, 75, 25, 20));
        bombs.add(new Rectangle(15, 345, 25, 20));
        bombs.add(new Rectangle(350, 300, 25, 20));
        bombs.add(new Rectangle(200, 200, 25, 20));
        bombs.add(new Rectangle(500, 375, 25, 20));
        bombs.add(new Rectangle(50, 500, 25, 20));
        bombs.add(new Rectangle(120, 550, 25, 20));
        bombs.add(new Rectangle(260, 350, 25, 20));
        bombs.add(new Rectangle(15, 260, 25, 20));
        bombs.add(new Rectangle(45, 25, 25, 20));
        bombs.add(new Rectangle(100, 175, 25, 20));
        bombs.add(new Rectangle(160, 90, 25, 20));
        bombs.add(new Rectangle(130, 275, 25, 20));

        // creating an arraylist for the coins position- player needs to get 
        coins = new ArrayList<>();
        coins.add(new Rectangle(30, 10, 30, 20));
        coins.add(new Rectangle(100, 10, 30, 20));
        coins.add(new Rectangle(210, 100, 30, 20));
        coins.add(new Rectangle(225, 250, 30, 20));
        coins.add(new Rectangle(290, 300, 30, 20));
        coins.add(new Rectangle(350, 90, 30, 20));
        coins.add(new Rectangle(300, 50, 30, 20));
        coins.add(new Rectangle(190, 175, 30, 20));
        coins.add(new Rectangle(10, 350, 30, 20));
        coins.add(new Rectangle(125, 410, 30, 20));
        coins.add(new Rectangle(50, 50, 30, 20));
        coins.add(new Rectangle(80, 210, 30, 20));

        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();

        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }
    // setting the font for the display of the players' score 
    Font scoreFont = new Font("Impact", Font.BOLD, 45);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
//                     
        // Creating the background of the game --> importing the image
        g.drawImage(Background, 0, 0, WIDTH, HEIGHT, null);

        // drawing the player of the game --> basic drawing skill
        g.setColor(darkRed);
        g.fillOval(player.x, player.y, player.width, player.height);

        //drawing the coins from the array list and the importing the image (coins)
        for (Rectangle coin : coins) {
            g.drawImage(this.coin, coin.x, coin.y, coin.width, coin.height, null);

        }
        // drawing the bombs of the game from the array list and importing the image (bombs)
        for (Rectangle bomb : bombs) {
            g.drawImage(this.bomb, bomb.x, bomb.y, bomb.width, bomb.height, null);
        }

        //font and position of the players' score on the screen
        g.setColor(Color.WHITE);
        g.setFont(scoreFont);
        g.drawString("" + score, WIDTH / 2, 50);


    }

    // GAME DRAWING ENDS HERE
    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            //moving the player from left to right
            if (leftPressed) {
                // the speed of player to be set to 5 (to the left)
                player.x = player.x - 5;
            }
            if (rightPressed) {
                // the speed of the player to be set to 5 (to the right)
                player.x = player.x + 5;
            }

            // go through all of bombs from the array list
            for (Rectangle bomb : bombs) {
                // moving the bombs down
                // the speed of the bombs falling down to be set to 2
                bomb.y = bomb.y + 2;
                if (bomb.y > 700) {
                    bomb.y = - 100;
                }
            }
            // go through all of the coins from the array list
            for (Rectangle coin : coins) {
                //the speed of the coins falling down to be set to 2
                coin.y = coin.y + 2;
                if (coin.y > 700) {
                    coin.y = - 100;

                }
            }

            //go through all of the bombs
            for (Rectangle bomb : bombs) {
                // when the player encounters (collides) with a bomb
                if (player.intersects(bomb)) {
                    // when player hits bomb, end game
                    done = true;

                }
            }
            // go through all of the coins
            for (Rectangle coin : coins) {
                // when the player will collide/passes through a coin it will add on to  score (adds 22 each time)
                if (player.intersects(coin)) {
                    score = score + 1;
                }
            }

            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {

        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {

            //movement of player with the left key
            int key = e.getExtendedKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                leftPressed = true;
                
            }
            // movement of the player with the right key
            if (key == KeyEvent.VK_RIGHT) {
                rightPressed = true;

            }

        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getExtendedKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                //releasing the left key, so the player does not move in the left direction anymore
                leftPressed = false;
            }
            // releasing the right key, so that player does not move in the right direction anymore
            if (key == KeyEvent.VK_RIGHT) {
                rightPressed = false;

            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        ICS3UI game = new ICS3UI();

        // starts the game loop
        game.run();
    }

    // A method used to load in an image
    // The filname is used to pass in the EXACT full name of the image from the src folder
    // i.e.  images/picture.png
    public static BufferedImage loadImage(String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(name));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return img;
    }
}
