package com.example.videogame.sprites;

import com.example.videogame.GameConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
    
    protected Image image;
    protected int posX, posY, imageWidth, imageHeight, speed;
    private boolean alive = true;

    /**
     * This is the constructor for the sprite class
     * @param posX - left most position of the sprite
     * @param posY - top most position of the sprite
     * @param imageWidth - how wide the sprite will be when drawn
     * @param imageHeight - how tall the sprite will be when drawn
     * @param speed - the speed at which the sprite will transition positions
     */
    public Sprite(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        setPosX(posX);
        setPosY(posY);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setSpeed(speed);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        int furthestValue = GameConfig.getGameWidth() + 200;
        if (posX >= 0 && posX <= furthestValue)
            this.posX = posX;
        else throw new IllegalArgumentException("X-Position must be in the range of 0 - " + furthestValue);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        int furthestValue = GameConfig.getGameHeight() - imageHeight;
        if (posY >= 0 && posY <= furthestValue)
            this.posY = posY;
        else throw new IllegalArgumentException("X-Position must be in the range of 0 - " + furthestValue);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        if (imageWidth >= 10 && imageWidth <= GameConfig.getMaxSpriteWidth())
            this.imageWidth = imageWidth;
        else throw new IllegalArgumentException("Image Width must be in the range of 10 - " + GameConfig.getMaxSpriteWidth());
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        if (imageHeight >= 10 && imageHeight <= GameConfig.getMaxSpriteHeight())
            this.imageHeight = imageHeight;
        else throw new IllegalArgumentException("Image Height must be in the range of 10 - " + GameConfig.getMaxSpriteHeight());
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * How many pixels should the sprite move each time it is drawn
     * @param speed
     */
    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= 10)
            this.speed = speed;
        else throw new IllegalArgumentException("Speed must be in the range of 0 - 10");
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, posX, posY, imageWidth, imageHeight);
    }

    /**
     * This method returns true if two sprites collide
     */
    public boolean collidesWith(Sprite sprite){
        return ((posX + imageWidth/2 > sprite.posX) && (posX < sprite.posX + sprite.imageWidth/2) &&
                (posY + imageHeight/2 > sprite.posY) && (posY < sprite.posY + sprite.imageHeight/2));

    }

}
