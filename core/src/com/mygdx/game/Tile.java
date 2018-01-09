/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author slatz8075
 */
public class Tile {
    
    private Map[][] map;
    private Screen[][] screen;
    private boolean block;
    private Player p1;
    
    public Tile(boolean block){
        //Note that all tiles must either be programmed seperately or some other
        //way. Don't leave them all false -Come back to this-
        this.block = false;
    }
    //Check if the player has pressed the interact button
    public void checkInteract(){
        if(this.p1.interact()){
            //Do something with the tile if able
        }
    }
}
