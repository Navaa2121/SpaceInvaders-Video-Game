package spaceinvaders;

public class SingleBulletFiringStrategy implements FiringStrategy {
    @Override
    public void fire(SpaceInvadersUI game) {
        int x = game.getShooter_X_Coordinate() + game.getShooterWidth() / 2;
        int y = game.getHeight() - game.getShooterHeight();
        game.addBullet(x, y);
    }
}