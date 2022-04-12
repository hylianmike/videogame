package com.example.videogame.sprites;

import com.example.videogame.GameConfig;
import com.example.videogame.Main;
import com.example.videogame.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien extends Sprite {

    public Alien(int posX, int posY) {
        super(posX, posY, GameConfig.getAlienWidth(), GameConfig.getAlienWidth(), GameConfig.getAlienSpeed());
        image = new Image(Main.class.getResource("images/alien.png").toExternalForm());
    }

    /**
     * Alien sprites can only move from right to left
     */
    public void moveUp() { }
    public void moveDown() { }
    public void moveRight() { }

    public void moveLeft() {
        if (posX < 0)
            posX = GameConfig.getGameWidth();
        else
            posX -= speed;
    }

    public void draw(GraphicsContext gc){
        if (isAlive()) {
            super.draw(gc);
            moveLeft();
        }
    }
}
