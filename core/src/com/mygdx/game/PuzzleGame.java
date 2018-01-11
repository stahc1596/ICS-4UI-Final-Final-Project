/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author slatz8075
 */
public class PuzzleGame extends Game{
    
        //create the sprite batch for the game
	private SpriteBatch batch;

	@Override
	public void create() {
            // initialize the sprite batch
            batch = new SpriteBatch();
            // create the main game screen we want to use
            MainGame game = new MainGame(this);
            // set the screen to show it
            this.setScreen(game);
	}

	@Override
	public void render() {
            // this will automatically call render on the screen that is set
            super.render();
	}
	
	@Override
	public void dispose() {
            // get rid of heavy variables
            batch.dispose();
	}
        
        public SpriteBatch getBatch(){
            // returns the spritebatch so other screens can use it
            return batch;
        }
}
