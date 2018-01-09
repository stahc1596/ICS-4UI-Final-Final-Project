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
    
    private Tile place;
    private int directionX;
    private int directionY;
    
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
            this.directionX = 2;
            if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT)||!Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                this.directionY = 0;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.dx = -3;
            this.directionX = 1;
            if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT)||!Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                this.directionY = 0;
            }
        } else{
            this.dx = 0;
        } if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.dy = -3;
            this.directionY = 2;
            if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT)||!Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                this.directionY = 0;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.dy = 3;
            this.directionY = 1;
            if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT)||!Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                this.directionY = 0;
            }
        } else {
            this.dy = 0;
        }
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
    }
    
    private void setTile(Tile tile){
        this.place = tile;
    }
    public String getDiretion(){
        if(this.directionX = 2 && this.directionY = 0){
            return "right";
        }else if(this.directionX = 2 && this.directionY = 1){
            return "down-right";
        }else if(this.directionX = 2 && this.directionY = 2){
            
        }
    }
}
