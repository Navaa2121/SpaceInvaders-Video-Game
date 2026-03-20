package spaceinvaders;

import java.awt.*;

public class PaintingActions {

    public PaintingActions() {

    }

    public void drawShooter(Graphics g, SpaceInvadersUI game) {
        Image shooter_image = game.imageSelection.getShooterImage();
        int shooter_height = game.getShooterHeight();
        int shooter_width = game.getShooterWidth();
        int shooter_X_Coordinate = game.getShooter_X_Coordinate();
        int shooter_Y_Coordinate = game.getHeight() - shooter_height;

        g.drawImage(shooter_image, shooter_X_Coordinate, shooter_Y_Coordinate, shooter_width, shooter_height, game);

    }

    public void drawInvaders(Graphics g, java.util.List<SpaceInvadersUI.InvaderBox> invaderboxes, Image invaderboxImage,
            SpaceInvadersUI game) {
        for (SpaceInvadersUI.InvaderBox invaderbox : invaderboxes) {
            g.drawImage(invaderboxImage, invaderbox.x, invaderbox.y, invaderbox.size, invaderbox.size, game);
        }
    }

    public void drawBullets(Graphics g, java.util.List<SpaceInvadersUI.Bullet> bullets, ImageSelection imageSelection, SpaceInvadersUI game) {
        Image bulletImage = imageSelection.getBulletType();
        String bulletShape = imageSelection.getBulletShape();
        int s = game.getBulletSize();
        int half = s / 2;

        for (SpaceInvadersUI.Bullet bullet : bullets) {
            g.setColor(Color.YELLOW);
            if (bulletImage != null) {
                // Draw image-based bullet centered on (bullet.x, bullet.y)
                g.drawImage(bulletImage, bullet.x - half, bullet.y - half, s, s, null);
            } else if (bulletShape != null) {
                // Draw shape-based bullet centered on (bullet.x, bullet.y)
                if (bulletShape.equals("triangle")) {
                    int[] xPoints = { bullet.x, bullet.x - half, bullet.x + half };
                    int[] yPoints = { bullet.y - half, bullet.y + half, bullet.y + half };
                    g.fillPolygon(xPoints, yPoints, 3);
                } else if (bulletShape.equals("circle")) {
                    g.fillOval(bullet.x - half, bullet.y - half, s, s);
                } else if (bulletShape.equals("square")) {
                    g.fillRect(bullet.x - half, bullet.y - half, s, s);
                }
            } else {
                // Fallback to triangle if both image and shape are null
                int[] xPoints = { bullet.x, bullet.x - half, bullet.x + half };
                int[] yPoints = { bullet.y - half, bullet.y + half, bullet.y + half };
                g.fillPolygon(xPoints, yPoints, 3);
            }
        }
    }
}
