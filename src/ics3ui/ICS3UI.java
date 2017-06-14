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

import java.util.ArrayList;
import javax.imageio.ImageIO;

import java.io.IOException;
 
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
    boolean rightPressed= false;
    boolean leftPressed = false;
    
    //creating a score variable
    int score = 0;
    
    // making a variable for the gravity
    int gravity = 0;
    
    //creating the player
    Rectangle player = new Rectangle (175, 550, 30, 30);
    
    
    // the frame (For pixels)
    int frameCount = 0;
    
    // creating the coins
    ArrayList <Rectangle> coins = new ArrayList<>();
    
    // creating the bombs
    ArrayList<Rectangle> bombs = new ArrayList<>();
    //Title of the window
    String title = "My Game";
 
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
     
    
    // importing the  images 
    BufferedImage bomb = loadImage("images/avoid.png");
    BufferedImage Background = loadImage("images/wall.png");
    BufferedImage coin =loadImage("images/money.png");
    
    
//    //create a method for loading the images
//    public BufferedImage loadImage(String filename) {
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File(filename));
//        } catch (Exception e) {
//            System.out.println("Error loading " + filename);
//        }
//        return img;
//    }

   
 
    // YOUR GAME VARIABLES WOULD GO HERE
    
       
    
 
    // GAME VARIABLES END HERE   
 
    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public ICS3UI(){
        //creating an array list for the bombs position - player needs to avoid
        bombs = new ArrayList<>();
        bombs.add(new Rectangle(75, 50, 40, 20));
        bombs.add(new Rectangle(0, 150, 40, 20));
        bombs.add(new Rectangle(150, 440, 40, 20));
        bombs.add(new Rectangle(250, 0, 40, 20));
        bombs.add(new Rectangle(150, 400, 40, 20));
        bombs.add(new Rectangle(280, 480, 40, 20));
        bombs.add(new Rectangle(350, 250, 40, 20));
        bombs.add(new Rectangle(275, 75, 40, 20));
        bombs.add(new Rectangle(15, 345, 40, 20));
        bombs.add(new Rectangle(350, 300, 40, 20));
        bombs.add(new Rectangle(200, 200, 40, 20));
        bombs.add(new Rectangle(500, 375, 40, 20));
        bombs.add(new Rectangle(50, 500, 40, 20));
        bombs.add(new Rectangle(120, 550, 40, 20));
        bombs.add(new Rectangle(260, 350, 40, 20));
        bombs.add(new Rectangle(15, 260, 40, 20));
        bombs.add(new Rectangle(45, 25, 40, 20));
        bombs.add(new Rectangle(100, 175, 40, 20));
        bombs.add(new Rectangle(160, 90, 40, 20));
        bombs.add(new Rectangle(130, 275, 40, 20));
        
        
        // creating an arraylist for the coins position- player needs to get 
        coins = new ArrayList<>();
        coins.add (new Rectangle (30, 10, 30, 20));
        coins.add (new Rectangle (100, 10, 30, 20));
        coins.add (new Rectangle (210, 100, 30, 20));
        coins.add (new Rectangle (225, 250, 30, 20));
        coins.add (new Rectangle (290, 300, 30, 20));
        coins.add (new Rectangle (350, 90, 30, 20));
        coins.add (new Rectangle (300, 50, 30, 20));
        coins.add (new Rectangle (190, 175, 30, 20));
        coins.add (new Rectangle (10, 350, 30, 20));
        coins.add (new Rectangle (125, 410, 30, 20));
        coins.add (new Rectangle (50, 50, 30, 20));
        coins.add (new Rectangle (80, 210, 30, 20));
        
        
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
    // creating a score time
    Font scoreFont = new Font ("Arial" , Font.BOLD, 42);
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
 
        // GAME DRAWING GOES HERE
          
//           // drawing the bomb on the side of the screen
////           
//            g.drawImage(bomb, 0, 0, 100, 80, null);
//            
//            g.drawImage(coin, 0, 0, 100, 80, null);
        
            

            // Creating the background of the game
            g.drawImage(Background, 0, 0, WIDTH, HEIGHT, null);
            
            // drawing the player of the game
            g.setColor(Color.DARK_GRAY);
            g.fillOval (player.x, player.y, player.width, player.height);
            
            //drawing the coins from the array list
            
            for(Rectangle coin : coins ){
                g.drawImage(this.coin, coin.x, coin.y, 100, 80 ,null);
            }

            // drawing the bombs of the game from the array list
             for(Rectangle bomb : bombs){
                g.drawImage(this.bomb, bomb.x, bomb.y, 100, 80, null);
            }
            
        //draw the font on the screen
        g.setColor (Color.WHITE);
        g.setFont(scoreFont);
        g.drawString("" + score, WIDTH/2, 50);
            }
        
        
        // GAME DRAWING ENDS HERE
    
 
 
    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void  preSetup(){
       // Any of your pre setup before the loop starts should go here
        
//       Background = loadImage("images/wall.png");
//       bomb = loadImage("images/avoid.png");
//       coin = loadImage("images/money.png");
 
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
                    // the player will move at the spesd of 3 to the left
                    player.x = player.x - 3;
                } 
                if (rightPressed) {
                    // the player will move at the speed of to the right
                    player.x = player.x + 3;
                }
              
                    // go through all of rectangles
                    for (Rectangle bomb : bombs) {
                        // making the bombs go down
                        bomb.y = bomb.y + 2;
                        if (bomb.y > 600){
                          bomb.y = - 100;
                        }
                    }
                    // go through all of diamonds
                    for (Rectangle coin : coins){
                        //making the diamonds to go down
                        coin.y = coin.y + 2;
                        if (coin.y > 600){
                            coin.y = - 100;
                            
                        }
                    }
      
                    //go through all of the bombs
                    for (Rectangle bomb : bombs){
                         // when the player encounters (collides) with a bomb
                        if(player.intersects(bomb)){
                            // if yes the player is hit
                            done = true;
                            
                        }
                    }                 
                    // go through all of the coins
                    for (Rectangle coin : coins ){
                        // when the player will collide with a coin it will add a score
                        if (player.intersects (coin)){
                            score = score + 1 ;
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
        public void mousePressed(MouseEvent e){
            
        }
        
        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
        
        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
            
        }
 
        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e){
            
        }
    }
    
    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter{
        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e){
            
        //moving the player to the left using this left key
        int key = e.getExtendedKeyCode();
        if (key == KeyEvent.VK_LEFT){
            leftPressed = true;
        // moving the player to the rigth using the right key
        } 
        if (key == KeyEvent.VK_RIGHT){
            rightPressed = true;

        }
            
        }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
            
        int key = e.getExtendedKeyCode();
        if (key == KeyEvent.VK_LEFT){
            //moving the plyer to the left using this left key
            leftPressed = false;
        } 
         // moving the player to the rigth using the right key
        if (key == KeyEvent.VK_RIGHT){
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
    public BufferedImage loadImage(String filename) {
        
        BufferedImage img = null;

        try {
            // use ImageIO to load in an Image
            // ClassLoader is used to go into a folder in the directory and grab the file
            img = ImageIO.read(ClassLoader.getSystemResourceAsStream(filename));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return img;
    }
}
  

