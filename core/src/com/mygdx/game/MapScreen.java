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
    //create the width and height variables
    private int width;
    private int height;
    
    //initilizer for the screen
    public MapScreen(int width, int height){
        //create the empty array with the correct size
        tiles = new int[height][width];
        //store the width and height
        this.width = width;
        this.height = height;
    }
    
    //setter for the tile
    public void setTile(int row, int col, int tileType){
        //set this integer at this position to be the desired integer
        tiles[row][col] = tileType;
    }
    
    //getter for the tile
    public int getTile(int row, int col){
        //access the integer at this position
        return tiles[row][col];
    }
    
    //getter for the width of a mapscreen
    public int getWidth(){
        System.out.println("WIDTH: " + width);
        return width;
    }
    
    //getters for the height of a mapscreen
    public int getHeight(){
        System.out.println("height: " + height);
        return height;
    }
    
    
}
