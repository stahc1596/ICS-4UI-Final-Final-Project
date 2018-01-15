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
    private boolean faceLeft;
    private boolean faceRight;
    private boolean faceUp;
    private boolean faceDown;

    // the amount of time an animation has been running
    private float elapsed;

    // animation variables for moving
    private Animation<TextureRegion> runR;
    private Animation<TextureRegion> runL;
    private Animation<TextureRegion> runU;
    private Animation<TextureRegion> runD;
    
    // pictures when standing still
    private TextureRegion standR;
    private TextureRegion standL;
    private TextureRegion standU;
    private TextureRegion standD;

    // texture atlas that will help load in the images from the big image
    // this was created from running the texture packer (in Desktop Launcher)
    private TextureAtlas atlas;

    // the collision rectangle to help us fix collisions
    private Rectangle bounds;
    
    private Texture player;
    private int directionX;
    private int directionY;
    private int distanceTraveledX;
    private int distanceTraveledY;
    private int worldRow;
    private int worldColumn;
    private MapScreen world;


    // constructor - we need to know where the player starts
    public Player(float x, float y, int row, int col) {
        // sets the income position
        this.x = x;
        this.y = y;

        // player starts standing still
        this.dx = 0;
        this.dy = 0;

        // no animation going on, so no time yet
        this.elapsed = 0;

        // load in the texture atlast to start finding pictures
        this.atlas = new TextureAtlas("packed/player.atlas");

        // finding the standing picture and load it in
        this.standD = atlas.findRegion("standD");
        // we create a new texture from the standing picture
        // this creates a duplicate picture so we can flip it for the other direction
        this.standL = new TextureRegion(standL);
        this.standL.flip(true, false);

        // create a run animation by finding every picture named run
        // the atlas has an index from each picture to order them correctly
        // this was done by naming the pictures in a certain way (run_1, run_2, etc.)
        runR = new Animation(1f / 10f, atlas.findRegions("run"));

        // load in the pictures from the atlas again, but we will flip them this time
        Array<TextureAtlas.AtlasRegion> runLFrames = atlas.findRegions("run");
        for (int i = 0; i < runLFrames.size; i++) {
            // this is how we flip each image
            runLFrames.get(i).flip(true, false);
        }
        // create the left animation
        runL = new Animation(1f / 10f, runLFrames);

        // start off by facing right
        this.faceLeft = false;
        // my collision rectangle is at the x,y value passed in
        // it has the width and height of the standing picture
        this.bounds = new Rectangle(x, y, standR.getRegionWidth(), standR.getRegionHeight());
        this.worldRow = row;
        this.worldColumn = col;
        this.distanceTraveledX = 0;
        this.distanceTraveledY = 0;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
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
        }/**
         *Replace getTileTyp with something else
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            String test = this.world.getTileType();
            if (test.equals("Puzzle")) {
                puzzleInteract(this.world.getTile);
            } else if (test.equals("Door")) {
                doorInteract(this.world.getTile);
            }
        }
        */
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
    }

    public void fixCollision(Rectangle block) {
        // are they colliding?
        if (bounds.overlaps(block)) {
            // calculate how much the are overlaping
            float width = Math.min(bounds.x + bounds.width, block.x + block.width) - Math.max(bounds.x, block.x);
            float height = Math.min(bounds.y + bounds.height, block.y + block.height) - Math.max(bounds.y, block.y);
            // seperate the axis by finding the least amount of collision
            if (width < height) {
                // on the left
                if (this.x < block.x) {
                    // move the player to the left
                    this.x = this.x - width;
                // on the right
                } else {
                    // move the player to the right
                    this.x = this.x + width;
                }
            } else {
                // under it
                if (this.y < block.y) {
                    // move the player down
                    this.y = this.y - height;
                // above it
                } else {
                    // move the player up
                    this.y = this.y + height;
                }
            }
            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
        }
    }

    public void render(SpriteBatch batch){
        //Check if the player is standing
        if (this.dx == 0){
            //Determine which direction the player is standing
            //If the player is facing left
            if(faceLeft){
                batch.draw(standL, x, y);
            //If the player is facing Right
            }else if(faceRight){
                batch.draw(standR, x, y);
            //If the player is facing up
            }else if(faceUp){
                batch.draw(standU, x, y);
            //If the player is facing down
            }else{
                batch.draw(standD, x, y);
            }
        //If the player is moving, one of the four animations play, even if
        //they're moving diagonally
        //If the player is moving to the right
        }else if(this.dx > 0){
            batch.draw(runR.getKeyFrame(elapsed, true), x, y);
        //If the player is moving to the left
        }else if(this.dx < 0){
            batch.draw(runL.getKeyFrame(elapsed, true), x, y);
        //If the player is moving upwards
        }else if(this.dy < 0){
            batch.draw(runU.getKeyFrame(elapsed, true), x, y);
        //If the player is moving downwards
        }else if(this.dy > 0){
            batch.draw(runD.getKeyFrame(elapsed, true), x, y);
        }
    }
    
    // get rid of heavy objects
    public void dispose(){
        atlas.dispose();
    }
}
