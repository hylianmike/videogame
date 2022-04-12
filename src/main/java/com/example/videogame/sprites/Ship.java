package com.example.videogame.sprites;

import com.example.videogame.GameConfig;
import com.example.videogame.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Ship extends Sprite {

    private ArrayList<Missile> activeMissiles;
    private int missilesRemaining;
    private final int REFRESH_RATE = 20;
    private int missilePause;

    public Ship(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        super(posX, posY, imageWidth, imageHeight, speed);
        image = new Image(Main.class.getResource("images/ship.png").toExternalForm());
        activeMissiles = new ArrayList<>();
        missilesRemaining = GameConfig.getMissileCount();
        missilePause = REFRESH_RATE;
    }

    public ArrayList<Missile> getActiveMissiles() {
        return activeMissiles;
    }

    public void shootMissile(){
        if (missilePause < 0) {
            missilesRemaining--;
            activeMissiles.add(new Missile(posX + imageWidth, posY + (imageWidth / 2 - GameConfig.getMissileHeight())));
            missilePause = REFRESH_RATE;
        }
    }

    public void moveRight(){
        if (posX >= GameConfig.getGameWidth() - imageWidth)
            posX = GameConfig.getGameWidth() - imageWidth;
        else
            posX += speed;
    }
    public void moveLeft(){
        if (posX <= 0)
            posX = 0;
        else
            posX -= speed;
    }
    public void moveUp(){
        if (posY <= 0)
            posY = 0;
        else
            posY -= speed;
    }
    public void moveDown(){
        if (posY >= GameConfig.getGameHeight() - imageHeight - 80)
            posY = GameConfig.getGameHeight() - imageHeight - 80;
        else
            posY += speed;
    }

    public void draw(GraphicsContext gc){
        missilePause--;

        // draw the ship
        super.draw(gc);

        // remove the missiles that have hit an alien or gone off the screen
        activeMissiles.removeIf(missile -> !missile.isAlive());

        // loop over all the active missiles and draw them on the game board
        for (Missile missile : activeMissiles) {
            missile.draw(gc);
        }
    }

}
