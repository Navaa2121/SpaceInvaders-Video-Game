package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AnimatedBackground {
    
    private final List<Star> stars;
    private final int width, height;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final Random random = new Random();

    public AnimatedBackground(int width, int height, int numStars) {
        this.width = width;
        this.height = height;
        stars = new ArrayList<>();
        for (int i = 0; i < numStars; i++) {
            stars.add(new Star(random.nextInt(width), random.nextInt(height), 2 + random.nextInt(3)));
        }
    }

    public void update() {
        for (Star s : stars) {
            s.y += s.speed;
            if (s.y > height) {
                s.y = 0;
                s.x = random.nextInt(width);
            }
        }
        pcs.firePropertyChange("update", false, true);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        for (Star s : stars) {
            g.fillOval(s.x, s.y, s.size, s.size);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private static class Star {
        int x, y, size;
        int speed;

        Star(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = 1 + (size / 2); // Bigger stars move faster
        }
    }
}
