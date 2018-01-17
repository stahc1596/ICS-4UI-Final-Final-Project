/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author slatz8075
 */
public class Player {
    private float x;
    private float y;
    private float dx;
    private float dy;
    private float elapsed;
    private Texture player;
    private int directionX;
    private int directionY;
    private int distanceTraveledX;
    private int distanceTraveledY;
    private int worldRow;
    private int worldColumn;
    private MapScreen world;
    
    // pictures when standing still
    private TextureRegion stand;
    private TextureRegion standL;

    // texture atlas that will help load in the images from the big image
    // this was created from running the texture packer (in Desktop Launcher)
    private TextureAtlas atlas;

    // the collision rectangle to help us fix collisions
    private Rectangle bounds;

   
    /**
     *
     * @param x the players x position on the screen
     * @param y the players y position on the screen
     * @param row the row of the map where the player is
     * @param col the column of the map where the player is
     */
    public Player(float x, float y, int row, int col) {
        this.x = x;
        this.y = y;
        this.world = new MapScreen();
        this.dx = 0;
        this.dy = 0;
        this.worldRow = row;
        this.worldColumn = col;
        this.distanceTraveledX = 0;
        this.distanceTraveledY = 0;

        this.player = new Texture("player.jpg");
    }

    public void update(boolean place) {
        // movement
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.dx = 3;
            this.directionX = 2;
            if (!Gdx.input.isKeyPressed(Input.Keys.UP) || !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                this.directionY = 0;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.dx = -3;
            this.directionX = 1;
            if (!Gdx.input.isKeyPressed(Input.Keys.UP) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                this.directionY = 0;
            }
        } else {
            this.dx = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.dy = -3;
            this.directionY = 2;
            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                this.directionX = 0;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.dy = 3;
            this.directionY = 1;
            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                this.directionX = 0;
            }
        } else {
            this.dy = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            String test = this.world.getTile(this.x,this.y);
            if (test.equals("Puzzle")) {
                puzzleInteract();
            } else if (test.equals("Door")) {
                doorInteract();
            }
        }
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
    }

    public void setWorldRow(int row) {
        this.worldRow = row;
    }

    public void setWorldCol(int col) {
        this.worldColumn = col;
    }

    public void setScreen(MapScreen places) {
        this.world = places;
    }
/**
 * getting the players x position
 * @return the players x position
 */
    public float getPlayerX() {
        return this.x;
    }
/**
 * getting the players y position
 * @return the players y position
 */
    public float getPlayerY() {
        return this.y;
    }
/**
 * 
 * @return 
 */
    public int getWorldCol() {
        return this.worldColumn;
    }
/**
 * 
 * @return 
 */
    public int getWorldRow() {
        return this.worldRow;
    }
/**
 * 
 * @return the direction the player is facing 
 */
    public String getDiretion() {
        if (this.directionX == 2 && this.directionY == 0) {
            return "right";
        } else if (this.directionX == 2 && this.directionY == 1) {
            return "down-right";
        } else if (this.directionX == 2 && this.directionY == 2) {
            return "up-right";
        } else if (this.directionX == 1 && this.directionY == 0) {
            return "left";
        } else if (this.directionX == 1 && this.directionY == 1) {
            return "down-left";
        } else if (this.directionX == 1 && this.directionY == 2) {
            return "up-left";
        } else if (this.directionX == 0 && this.directionY == 2) {
            return "up";
        } else if (this.directionX == 0 && this.directionY == 1) {
            return "down";
        } else {
            return "error no direction found";
        }
    }
/**
 * tells the wold the player wants to interact with the world 
 */
    public void puzzleInteract() {
        //interact
        world.changePuzzleTile(this.x, this.y);
    }
/**
 * has the player tell the world he wants to go through a door
 */
    public void doorInteract() {
        //interact
        world.PassThroughDoor(this.worldRow, this.worldColumn, this.x, this.y);
    }
}

    