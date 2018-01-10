/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author slatz8075
 */
public class Screen {
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
    public int getTile(int row, int col){
        //access the integer at this position
        return tiles[row][col];
    }
    
    
}
