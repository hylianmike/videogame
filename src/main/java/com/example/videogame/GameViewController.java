package com.example.videogame;

import com.example.videogame.sprites.Alien;
import com.example.videogame.sprites.Explosion;
import com.example.videogame.sprites.Missile;
import com.example.videogame.sprites.Ship;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    private AnimationTimer timer;

    /**
     * A set is a data structure that prevents duplicates, otherwise much like an ArrayList
     *
     * KeyCode - character pressed on the keyboard
     */
    private Set<KeyCode> activeKeys;

    @FXML
    private void startGame(ActionEvent event){
        Canvas canvas = new Canvas(GameConfig.getGameWidth(), GameConfig.getGameHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        anchorPane.getChildren().add(canvas);

        // initialize the set to hold the keycode pressed by the user;
        activeKeys  = new HashSet<>();

        // This creates a "listener" that will add the key pressed to the set
        // The -> is called a "lambda expression" and is a short form of calling a method and passing in a variable
        anchorPane.getScene().setOnKeyPressed(keyPressed -> {
            activeKeys.add(keyPressed.getCode());
        });

        anchorPane.getScene().setOnKeyReleased(keyPressed -> {
            activeKeys.remove(keyPressed.getCode());
        });

        // load the background
        Image background = new Image(getClass().getResource("images/space.png").toExternalForm());

        // load an image for our ship
        Ship ship = new Ship(100, 100, 100, 70, 5);

        ArrayList<Alien> aliens = new ArrayList<>();
        SecureRandom rng = new SecureRandom();
        // Add aliens to the scene
        for (int i = 0; i < 5; i++) {
            aliens.add(new Alien(rng.nextInt(600, 900),
                    rng.nextInt(30, GameConfig.getGameHeight() - 80 - GameConfig.getAlienHeight())));
        }

        ArrayList<Explosion> explosions = new ArrayList<>();

        // Add a timer to draw and update sprite
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0, 0, GameConfig.getGameWidth(), GameConfig.getGameHeight());
                updateShipLocation(ship);

                ship.draw(gc);

                // this is a lambda expression
                // it loops over the collection of aliens and calls the method isAlive() for each alien
                // if the !alien.isAlive() evaluates to true, then remove it from the collection
                aliens.removeIf(alien -> !alien.isAlive());

                for (Alien alien : aliens) {
                    alien.draw(gc);

                    for (Missile missile : ship.getActiveMissiles()){
                        if (missile.collidesWith(alien)){
                            explosions.add(new Explosion(alien.getPosX(), alien.getPosY(), 50, 50));
                            missile.setAlive(false);
                            alien.setAlive(false);
                        }
                    }

                    if (alien.collidesWith(ship)){
                        finalMessage(gc, "The Enemy Got You...", Color.RED);
                        timer.stop();
                    }
                }

                // remove any explosions that have completed before attempting to draw them
                explosions.removeIf(explosion -> !explosion.isAlive());

                for (Explosion explosion : explosions)
                    explosion.draw(gc);

                updateStats(gc, aliens);

                if (aliens.size() == 0){
                    finalMessage(gc, "Congratulations! You saved the universe!", Color.LIGHTGREEN);
                    if (explosions.size() == 0)
                        timer.stop();
                }
            }
        };
        timer.start();
    }

    private void updateStats(GraphicsContext gc, ArrayList<Alien> aliens){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, GameConfig.getGameHeight() - 80, GameConfig.getGameWidth(), 80);

        // draw how many aliens are remaining
        Font font = Font.font("Arial", FontWeight.NORMAL, 32);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
        gc.fillText("Aliens Remaining: " + aliens.size(), GameConfig.getGameWidth() - 400, GameConfig.getGameHeight() - 30);
    }

    /**
     * This method should be called when the game is over and you want to display a message to the user
     */
    private void finalMessage(GraphicsContext gc, String message, Color color){
        Font font = Font.font("Arial", FontWeight.NORMAL, 32);
        gc.setFont(font);
        gc.setFill(color);
        gc.fillText(message, 250, 350);
    }

    private void updateShipLocation(Ship ship) {
        if (activeKeys.contains(KeyCode.A))
            ship.moveLeft();
        if (activeKeys.contains(KeyCode.D))
            ship.moveRight();
        if (activeKeys.contains(KeyCode.W))
            ship.moveUp();
        if (activeKeys.contains(KeyCode.S))
            ship.moveDown();
        if (activeKeys.contains(KeyCode.ADD))
            ship.shootMissile();
    }
}
