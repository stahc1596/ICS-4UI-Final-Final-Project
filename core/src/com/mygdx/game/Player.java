/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    public Player(float x, float y) {
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
        this.stand = atlas.findRegion("stand");
        // we create a new texture from the standing picture
        // this creates a duplicate picture so we can flip it for the other direction
        this.standL = new TextureRegion(stand);
        this.standL.flip(true, false);

        // create a run animation by finding every picture named run
        // the atlas has an index from each picture to order them correctly
        // this was done by naming the pictures in a certain way (run_1, run_2, etc.)
        run = new Animation(1f / 10f, atlas.findRegions("run"));

        // load in the pictures from the atlas again, but we will flip them this time
        Array<TextureAtlas.AtlasRegion> runLFrames = atlas.findRegions("run");
        for (int i = 0; i < runLFrames.size; i++) {
            // this is how we flip each image
            runLFrames.get(i).flip(true, false);
        }
        // create the left animation
        runL = new Animation(1f / 10f, runLFrames);

        // start off by facing right
        this.facingLeft = false;
        // my collision rectangle is at the x,y value passed in
        // it has the width and height of the standing picture
        this.bounds = new Rectangle(x, y, stand.getRegionWidth(), stand.getRegionHeight());
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    
    public void update(float deltaTime) {
        // if I'm pressing right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            // set the x displacement to start moving right
            this.dx = 3;
            // increase the animation timer
            this.elapsed = this.elapsed + deltaTime;
            // set boolean to face right
            this.facingLeft = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // set the x displacement to start moving left
            this.dx = -3;
            // increase the animation timer
            this.elapsed = this.elapsed + deltaTime;
            // im facing left
            this.facingLeft = true;
        } else {
            // stop the x displacement
            this.dx = 0;
            // no more animation so reset the timer
            this.elapsed = 0;
        }

        this.x = this.x + this.dx;

        // update collision rectangle
        this.bounds.setX(this.x);
        this.bounds.setY(this.y);
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
