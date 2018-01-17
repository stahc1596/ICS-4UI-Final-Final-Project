/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author slatz8075
 */
public class MapScreen {
    //blank 2d screen array
    private int[][] tiles;
    
    //initilizer for the screen
    public void Screen(int width, int height){
        //create the empty array with the correct size
        tiles = new int[width][height];
    }
    
    //setter for the tile
    public void setTile(int row, int col, int tileType){
        //set this integer at this position to be the desired integer
        tiles[row][col] = tileType;
    }
    
    //getter for the tile
    public int getTile(){
        //access the integer(int row, int col){
        //access the inte at this position
        return tiles[1][1];
        }
    }

    void changePuzzleTile(float x, float y) {
        // take where the player is
        // have it interact with the puzzle how it is needed to
    }

    void PassThroughDoor(int worldRow, int worldColumn, float x, float y) {
        //take where player is and fing the corresponding door
        // go through the door and change where we are
    }

    
        
    
    
}
