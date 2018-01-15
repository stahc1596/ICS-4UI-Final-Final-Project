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
    //THIS IS FOR RYAN
    // player location variables
    private float x;
    private float y;
    // player movement variables
    private float dx;
    private float dy;
    // facing left or not
    private boolean facingLeft;

    // the amount of time an animation has been running
    private float elapsed;

    // animation variables for moving
    private Animation<TextureRegion> run;
    private Animation<TextureRegion> runL;
    
    // pictures when standing still
    private TextureRegion stand;
    private TextureRegion standL;

    // texture atlas that will help load in the images from the big image
    // this was created from running the texture packer (in Desktop Launcher)
    private TextureAtlas atlas;

    // the collision rectangle to help us fix collisions
    private Rectangle bounds;

    // constructor - we need to know where the player starts
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
            String test = this.world.getTileType();
            if (test.equals("Puzzle")) {
                puzzleInteract(this.world.getTile);
            } else if (test.equals("Door")) {
                doorInteract(this.world.getTile);
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

    public void setScreen(Screen places) {
        this.world = places;
    }

    public float getPlayerX() {
        return this.x;
    }

    public float getPlayerY() {
        return this.y;
    }

    public int getWorldCol() {
        return this.worldColumn;
    }

    public int getWorldRow() {
        return this.worldRow;
    }

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

    public void puzzleInteract(String puzzle) {

        //interact
        world.changePuzzleTile(this.x, this.y);
    }

    public void doorInteract(Tile Door) {
        //interact
        world.changeMap(this.worldRow, this.worldColumn, this.x, this.y);
    }
}

    public void render(SpriteBatch batch) {
        // standing
        if (this.dx == 0) {
            // pic the correct picture for left or right
            if (facingLeft) {
                batch.draw(standL, x, y);
            } else {
                batch.draw(stand, x, y);
            }
        // right animation
        } else if (this.dx > 0) {
            batch.draw(run.getKeyFrame(elapsed, true), x, y);
        // left animation
        } else if (this.dx < 0) {
            batch.draw(runL.getKeyFrame(elapsed, true), x, y);
        }
    }
    
    // get rid of heavy objects
    public void dispose(){
        atlas.dispose();
    }
}
