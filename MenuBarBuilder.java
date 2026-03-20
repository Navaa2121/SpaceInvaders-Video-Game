package spaceinvaders;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarBuilder {
    public static JMenuBar createMenuBar(SpaceInvadersUI game) {
        JMenuBar gameSettingsBar = new JMenuBar();

            JMenu shooterImage = new JMenu("Shooter");
            JMenuItem classicShooter = new JMenuItem("Classic Shooter");
            JMenuItem alienShooter = new JMenuItem("Alien");
            JMenuItem planktonShooter = new JMenuItem("Plankton");
            JMenuItem customShooter = new JMenuItem("Custom Shooter");

            shooterImage.add(classicShooter);
            shooterImage.add(alienShooter);
            shooterImage.add(planktonShooter);
            shooterImage.add(customShooter);

            JMenu invaderImage = new JMenu("Invader");
            JMenuItem classicInvader = new JMenuItem("Classic Invader");
            JMenuItem gbreadInvader = new JMenuItem("Ginger Bread");
            JMenuItem patrickInvader = new JMenuItem("Patrick Star");
            JMenuItem customInvader = new JMenuItem("Custom Invader");

            invaderImage.add(classicInvader);
            invaderImage.add(gbreadInvader);
            invaderImage.add(patrickInvader);
            invaderImage.add(customInvader);

            JMenu bulletType = new JMenu("Bullet");
            JMenuItem classicBullet = new JMenuItem("Classic Bullet");
            JMenuItem fireBullet = new JMenuItem("Fire");
            JMenuItem chumBullet = new JMenuItem("Chum");
            JMenuItem customBullet = new JMenuItem("Custom Bullet");

            bulletType.add(classicBullet);
            bulletType.add(fireBullet);
            bulletType.add(chumBullet);
            bulletType.add(customBullet);

            JMenu musicType = new JMenu("Music");
            JMenuItem classicMusic = new JMenuItem("Classic Music");
            JMenuItem spookyMusic = new JMenuItem("Spooky Music");
            JMenuItem underwaterMusic = new JMenuItem("Underwater Music");
            JMenuItem customMusic = new JMenuItem("Custom Music");
            
            musicType.add(classicMusic);
            musicType.add(spookyMusic);
            musicType.add(underwaterMusic);
            musicType.add(customMusic);

            JMenu backgroundMenu = new JMenu("Background");
            JCheckBoxMenuItem toggleStarfield = new JCheckBoxMenuItem("Starfield");

            backgroundMenu.add(toggleStarfield);

            gameSettingsBar.add(shooterImage);
            gameSettingsBar.add(invaderImage);
            gameSettingsBar.add(bulletType);
            gameSettingsBar.add(musicType);
            gameSettingsBar.add(backgroundMenu);

            //frame.setJMenuBar(gameSettingsBar);

            classicShooter.addActionListener(e -> game.imageSelection.setShooterImage("classicShooter.png"));
            alienShooter.addActionListener(e -> game.imageSelection.setShooterImage("alien.png"));
            planktonShooter.addActionListener(e -> game.imageSelection.setShooterImage("plankton.png"));
            customShooter.addActionListener(e -> game.imageSelection.setCustomShooter());

            classicInvader.addActionListener(e -> game.imageSelection.setInvaderImage("classicInvader.png"));
            gbreadInvader.addActionListener(e -> game.imageSelection.setInvaderImage("gbread.png"));
            patrickInvader.addActionListener(e -> game.imageSelection.setInvaderImage("patrickStar.png"));
            customInvader.addActionListener(e -> game.imageSelection.setCustomInvader());

            classicBullet.addActionListener(e -> game.imageSelection.setBulletShape("triangle"));
            fireBullet.addActionListener(e -> game.imageSelection.setBulletType("fireBullet.png"));
            chumBullet.addActionListener(e -> game.imageSelection.setBulletShape("square"));
            customBullet.addActionListener(e -> game.imageSelection.setCustomBullet());

            classicMusic.addActionListener(e -> game.imageSelection.setMusicType("classicMusic.wav"));
            spookyMusic.addActionListener(e -> game.imageSelection.setMusicType("spookyMusic.wav"));
            underwaterMusic.addActionListener(e -> game.imageSelection.setMusicType("underWaterMusic.wav"));
            customMusic.addActionListener(e -> game.imageSelection.setCustomMusic());

            toggleStarfield.addActionListener(e -> { game.backgroundEnabled = toggleStarfield.isSelected();
            game.repaint();
            });

            return gameSettingsBar;
}
}
