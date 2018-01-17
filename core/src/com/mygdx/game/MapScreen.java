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
    private String door;
    private String puzzle;
    private String passible;
    private String impassible;
    //initilizer for the screen
    public void Screen(int width, int height){
        //create the empty array with the correct size
        tiles = new int[width][height];
        this.door = "door";
        this.puzzle= "puzzle";
        this.passible = "passible";
        this.impassible = "impassible";
        
    }
    
    //setter for the tile
    public void setTile(int row, int col, int tileType){
        //set this integer at this position to be the desired integer
        tiles[row][col] = tileType;
    }
    
    //getter for the tile
    public String getTile(float row, float col){
        //access the integer(int row, int col){
        //access the inte at this position
        int tile = tiles[row][col];
        String TileType= "";
        if(tile ==0){
        TileType= this.passible;
        return TileType;
        }else if(tile == 1){
            TileType= this.impassible;
        return TileType;
        }else if(tile == 2){
            TileType= this.door;
        return TileType;
        }else{
            TileType= this.puzzle;
        return TileType;
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
