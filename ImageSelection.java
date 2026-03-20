package spaceinvaders;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ImageSelection {
    private Image shooterImage;
    private Image invaderImage;
    private Image bulletType;
    private String bulletShape;
    private Clip musicClip;
    private String currentMusicPath;

    public Image getShooterImage() {
        return shooterImage;
    }

    public Image getInvaderImage() {
        return invaderImage;
    }

    public Image getBulletType() {
        return bulletType;
    }

    public String getBulletShape() {
        return bulletShape;
    }

    public String getMusicPath() {
        return currentMusicPath;
    }

    public void setShooterImage(String imagePath) {
        shooterImage = loadImage("Shooter", imagePath, "/spaceinvaders/resources/classicShooter.png");
    }

    public void setInvaderImage(String imagePath) {
        invaderImage = loadImage("Invader", imagePath, "/spaceinvaders/resources/classicInvader.png");
    }

    public void setBulletType(String imagePath) {
        this.bulletType = loadImage("Bullet", imagePath, "/spaceinvaders/resources/fireBullet.png");
        this.bulletShape = null;
    }

    public void setBulletShape(String shape) {
        this.bulletType = null;
        this.bulletShape = shape;
    }

    public void setMusicType(String musicPath) {
        playMusic(musicPath, "./src/spaceinvaders/resources/classicMusic.wav");
    }

    public void setGameImages() {
        shooterImage = loadImage("Shooter", "", "/spaceinvaders/resources/classicShooter.png");
        invaderImage = loadImage("Invader", "", "/spaceinvaders/resources/classicInvader.png");
        bulletType = null;
        bulletShape = "triangle";
        playMusic("", "/spaceinvaders/resources/classicMusic.wav");
    }

    public void setCustomShooter(){
        String url = JOptionPane.showInputDialog("Enter URL for Shooter image:");
        if (url != null && !url.isEmpty()) {
            shooterImage = loadImage("Shooter", url, "/spaceinvaders/resources/classicShooter.png");
        }
    }

    public void setCustomInvader(){
        String url = JOptionPane.showInputDialog("Enter URL for Invader image:");
        if (url != null && !url.isEmpty()) {
            invaderImage = loadImage("Invader", url, "/spaceinvaders/resources/classicInvader.png");
        }
    }

    public void setCustomBullet(){
        String url = JOptionPane.showInputDialog("Enter URL for Bullet image:");
        if (url != null && !url.isEmpty()) {
            bulletType = loadImage("Bullet", url, "/spaceinvaders/resources/classicBullet.png");
            bulletShape = null;
        }
    }

    public void setCustomMusic(){
        String input = JOptionPane.showInputDialog("Enter URL for Music (Download .wav sound before entering the URL) or leave blank to choose a local file:");

    if (input != null && !input.isEmpty()) {
        try {
            java.net.URI uri = new java.net.URI(input);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(uri.toURL());

            if (musicClip != null && musicClip.isRunning()) {
                musicClip.stop();
                musicClip.close();
            }
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();
            currentMusicPath = input;
            return;
        } catch (Exception e) {
            GameExceptions.showErrorDialog("Failed to load music from URL. You can choose a local file instead.\n" + e.getMessage());
        }
    }

    try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a .wav music file");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("WAV files", "wav"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(selectedFile);
            if (musicClip != null && musicClip.isRunning()) {
                musicClip.stop();
                musicClip.close();
            }
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();
            currentMusicPath = selectedFile.getAbsolutePath();
            return;
        } else {
            playMusic("", "/spaceinvaders/resources/classicMusic.wav");
        }
    } catch (Exception e) {
        GameExceptions.showErrorDialog("Failed to load local music. Reverting to default.\n" + e.getMessage());
        playMusic("", "/spaceinvaders/resources/classicMusic.wav");
    }
    }


    private void playMusic(String musicPath, String defaultResourcePath) {
        try {
            if (musicClip != null && musicClip.isRunning()) {
                musicClip.stop();
                musicClip.close();
            }
        

        String pathToUse = (musicPath == null || musicPath.isEmpty()) ? defaultResourcePath : "/spaceinvaders/resources/" + musicPath;
        currentMusicPath = pathToUse;

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(pathToUse));
        musicClip = AudioSystem.getClip();
        musicClip.open(audioStream);
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        musicClip.start();

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        GameExceptions.showErrorDialog("Error playing music: " + e.getMessage());
    } 
}

    private static Image loadImage(String imageType, String resourcePath, String defaultResourcePath) {
        // If a specific resource path is provided, try to load it directly
        if (resourcePath != null && !resourcePath.isEmpty()) {
            try {
                if(resourcePath.startsWith("http://") || resourcePath.startsWith("https://")) {
                    java.net.URI uri = new java.net.URI(resourcePath);
                    return ImageIO.read(uri.toURL());}
                    else{
                return ImageIO.read(ImageSelection.class.getResource("/spaceinvaders/resources/" + resourcePath));
                    }
            } catch (Exception e) {
                GameExceptions.showErrorDialog("Failed to load " + imageType + " image: " + e.getMessage()
                        + "\nLoading default image instead");
            }
        }

        // If no resource path provided or loading failed, try the default
        try {
            return ImageIO.read(ImageSelection.class.getResource(defaultResourcePath));
        } catch (IOException e) {
            GameExceptions.showErrorDialog("Failed to load default " + imageType + " image: " + e.getMessage());
        }

        return null;
    }
}
