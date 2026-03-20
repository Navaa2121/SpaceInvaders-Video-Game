package spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.*;

public class SpaceInvadersUI extends JPanel implements ActionListener, KeyListener {

    private final Timer timer;
    public ArrayList<InvaderBox> invaderboxes;
    public ArrayList<Bullet> bullets;
    public Random random;
    public boolean moveLeft, moveRight;
    private final ListenerActions listenerActions;
    public final ImageSelection imageSelection;
    private final PaintingActions paintingActions;
    private int shooter_width = 50;
    private int shooter_height = 60;
    private int shooter_X_Coordinate = 200;
    // Size (in pixels) used to draw bullets (images and shapes)
    private int bulletSize = 20; // default larger size; adjust as needed
    public static int breakpointcounter = 0;
    private final javax.swing.Timer autoFireTimer;
    private boolean spaceHeld = false;
    private FiringStrategy firingStrategy = new SingleBulletFiringStrategy();
    private AnimatedBackground animatedBackground;
    public boolean backgroundEnabled = false;

    // Constructor
    public SpaceInvadersUI() {
        //
        timer = new Timer(20, this); // 20ms delay for smoother animations
        invaderboxes = new ArrayList<>(); // Need to describe what ArrayList<> is.
        bullets = new ArrayList<>();
        random = new Random();
        moveLeft = false;
        moveRight = false;
        listenerActions = new ListenerActions();
        imageSelection = new ImageSelection();
        paintingActions = new PaintingActions();
        // For debugging

        // Set images
        imageSelection.setGameImages();

        animatedBackground = new AnimatedBackground(800, 600, 100);
        animatedBackground.addPropertyChangeListener(e -> repaint());

        setFocusable(true);
        addKeyListener(this);

        autoFireTimer = new javax.swing.Timer(180, e -> firingStrategy.fire(this));
        autoFireTimer.setRepeats(true);
        timer.start();
    }

    @Override
    // Perhaps change this to specifically look for timer event or move all code to
    // ListenerActions and add overloading
    public void actionPerformed(ActionEvent e) {
        
        if(backgroundEnabled) {
            animatedBackground.update();
        }
        listenerActions.updatePositions(this);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        // Handle continuous fire here (decoupled from movement)
        if (key == KeyEvent.VK_SPACE && !spaceHeld) {
        spaceHeld = true;
        firingStrategy.fire(this);   // immediate shot for responsiveness
        autoFireTimer.start();       // then steady cadence
    }

        listenerActions.keyPressed(e, this);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Stop continuous fire on SPACE release
        if (key == KeyEvent.VK_SPACE) {
            spaceHeld = false;
            autoFireTimer.stop();
        }
        listenerActions.keyReleased(e, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used. Not providing an implementation Violates Interface Segregation
        // Principle
        // Could be used for character keys.
    }

    @Override
    // Let's move these methods into a separate PaintUI class
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(backgroundEnabled) {
            animatedBackground.draw(g);
        } else {
        setBackground(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Draw shooter (rectangle)
        paintingActions.drawShooter(g, this);

        // Draw falling invaderboxes (as images)
        paintingActions.drawInvaders(g, invaderboxes, imageSelection.getInvaderImage(), this);

    // Draw bullets (bullets)
    paintingActions.drawBullets(g, bullets, imageSelection, this);
    }

    public void addBullet(int x, int y) {
    bullets.add(new Bullet(x, y));
    }

    public int getBulletSize() {
        return bulletSize;
    }


    public int getShooterWidth() {
        return (shooter_width);
    }

    public int getShooterHeight() {
        return (shooter_height);
    }

    public int getShooter_X_Coordinate() {
        return (shooter_X_Coordinate);
    }

    public void setShooter_X_Coordinate(int shooter_X) {
        shooter_X_Coordinate = shooter_X;
    }

    // These are the characters or objects used in the game. Create a shooter class
    // thing.

    // Inner class representing falling invaderboxes
    public class InvaderBox {
        int x, y, size;

        public InvaderBox(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    // Inner class representing bullets (bullets)
    // Look for Java bullet class
    public class Bullet {
        int x, y;

        public Bullet(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
