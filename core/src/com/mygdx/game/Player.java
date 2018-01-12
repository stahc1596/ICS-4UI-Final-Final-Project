/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author slatz8075
 */
public class Player {
    
    //Player location variables
    private float x;
    private float y;
    
    //Player movement variables
    private float dx;
    private float dy;
    
    //Player direction variables
    private boolean faceLeft;
    private boolean faceUp;
    private boolean faceRight;
    
    //The amount of time an animation has been running
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

    public void update(float deltaTime){
        
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
}
