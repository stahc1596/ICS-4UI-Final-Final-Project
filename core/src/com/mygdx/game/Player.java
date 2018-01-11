/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author messr2578
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

        this.dx = 0;
        this.dy = 0;
        setWorldRow(row);
        setWorldCol(col);
        this.distanceTraveledX = 0;
        this.distanceTraveledY = 0;
        
        this.player = new Texture("player.jpg");
    }

    public void update(boolean place) {
        // movement
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.dx = 3;
            this.directionX = 2;
            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                this.directionY = 0;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.dx = -3;
            this.directionX = 1;
            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                this.directionY = 0;
            }
        } else {
            this.dx = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.dy = -3;
            this.directionY = 2;
            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                this.directionY = 0;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.dy = 3;
            this.directionY = 1;
            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                this.directionY = 0;
            }
        } else {
            this.dy = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            if(place){
                interact(place);
            }
        }
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
    }
    public void setWorldRow(int row){   
        this.worldRow = row;
    } 
    public void setWorldCol(int col){   
        this.worldColumn = col;
    } 
    public float getPlayerX(){
        return this.x;
    }
    public float getPlayerY(){
        return this.y;
    }
    public int getWorldCol(){
        return this.worldColumn;
    }
    public int getWorldRow(){
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
    public void interact(boolean puzzle){
        if(puzzle && Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            //interact
        }
    }
}
