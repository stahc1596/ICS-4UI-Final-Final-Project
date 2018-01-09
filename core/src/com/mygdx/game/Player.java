/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *
 * @author messr2578
 */
public class Player {
    private float x;
    private float y;
    
    private float dx;
    private float dy;
    
    public Player(float x, float y){
        this.x = x;
        this.y = y;
        
        this.dx = 0;
        this.dy = 0;
    }
    
    public void update(){
        // movement
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.dx = 3;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.dx = -3;
        } else{
            this.dx = 0;
        } if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.dy = -3;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.dy = 3;
        } else {
            this.dy = 0;
        }
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
    }
    
}
