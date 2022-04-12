package com.example.videogame;

public class GameConfig {

    private static int gameHeight = 800;
    private static int gameWidth = 1000;
    private static int maxSpriteHeight = 100;
    private static int maxSpriteWidth = 150;
    private static int missileCount = 30;
    private static int missileWidth = 50;
    private static int missileHeight = 30;
    private static int missileSpeed = 9;
    private static int alienWidth = 70;
    private static int alienHeight = 70;
    private static int alienSpeed = 2;

    public static int getAlienWidth() {
        return alienWidth;
    }

    public static void setAlienWidth(int alienWidth) {
        GameConfig.alienWidth = alienWidth;
    }

    public static int getAlienHeight() {
        return alienHeight;
    }

    public static void setAlienHeight(int alienHeight) {
        GameConfig.alienHeight = alienHeight;
    }

    public static int getAlienSpeed() {
        return alienSpeed;
    }

    public static void setAlienSpeed(int alienSpeed) {
        GameConfig.alienSpeed = alienSpeed;
    }

    public static int getMissileWidth() {
        return missileWidth;
    }

    public static void setMissileWidth(int missileWidth) {
        GameConfig.missileWidth = missileWidth;
    }

    public static int getMissileHeight() {
        return missileHeight;
    }

    public static void setMissileHeight(int missileHeight) {
        GameConfig.missileHeight = missileHeight;
    }

    public static int getMissileSpeed() {
        return missileSpeed;
    }

    public static void setMissileSpeed(int missileSpeed) {
        GameConfig.missileSpeed = missileSpeed;
    }

    public static int getMissileCount() {
        return missileCount;
    }

    public static void setMissileCount(int missileCount) {
        if (missileCount > 0 && missileCount <= 50)
            GameConfig.missileCount = missileCount;
        else
            throw new IllegalArgumentException("Initial Missile count must be 1 - 50");
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static void setGameHeight(int gameHeight) {
        GameConfig.gameHeight = gameHeight;
    }

    public static int getGameWidth() {
        return gameWidth;
    }

    public static void setGameWidth(int gameWidth) {
        GameConfig.gameWidth = gameWidth;
    }

    public static int getMaxSpriteHeight() {
        return maxSpriteHeight;
    }

    public static void setMaxSpriteHeight(int maxSpriteHeight) {
        GameConfig.maxSpriteHeight = maxSpriteHeight;
    }

    public static int getMaxSpriteWidth() {
        return maxSpriteWidth;
    }

    public static void setMaxSpriteWidth(int maxSpriteWidth) {
        GameConfig.maxSpriteWidth = maxSpriteWidth;
    }
}
