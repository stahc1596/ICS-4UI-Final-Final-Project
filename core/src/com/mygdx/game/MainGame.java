/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author slatz8075
 */
public class MainGame implements Screen{

    //create a puzzle game to display/ switch screens
    private PuzzleGame gameManager;
    // our game needs a hero
    private Player player;
    //create a new map to store the screens
    static Map map = new Map();
    //create a variables for the dimentions of the map (dimentions in terms of # of screens)
    static int mapHeight;
    static int mapWidth;
    //create variables for the dimentions of the screen
    static int ScreenTileHeight;
    static int ScreenTileWidth;

    // sprite batch
    private SpriteBatch batch;
    // camera and viewport
    private OrthographicCamera camera;
    private Viewport view;
    
    //variables for storing the screen that the player is at
    int currentScreenX;
    int currentScreenY;
    //variables for storing the startying position that the player is at
    int startX;
    int startY;

    /**
     * @param args the command line arguments
     */
    //this is the constructor to create the map and the sceens inside of it when started 
    public MainGame(PuzzleGame puzzleGame) {
        //INITILIZATION
        //Save the puzzle game so that we can talk to it
        this.gameManager = puzzleGame;
        //create a blank file
        FileReader file = null;
        //try to find the info file
        try {
            //create a url for the location of the info file
            URL url = MainGame.class.getResource("src//com.mygdxGame.game//info.txt");
            // creating the file reader
            file = new FileReader("src//com.mygdxGame.game//info.txt");
        } catch (Exception e) {
            //handle any errors
            e.printStackTrace();
            //exit the program
            System.exit(0);
        }
        //use a Scanner with the file
        Scanner in = new Scanner(file);
        //take in the variables for width and height of the map
        mapHeight = Integer.parseInt(in.next());
        mapWidth = Integer.parseInt(in.next());
        //take in the variables for width and height of screens
        ScreenTileHeight = Integer.parseInt(in.next());
        ScreenTileWidth = Integer.parseInt(in.next());
        //take in the starting position (screen wise) of the player
        currentScreenX = Integer.parseInt(in.next());
        currentScreenY = Integer.parseInt(in.next());
        //take in the position of the player (XY position wise on the screen)
        startX = Integer.parseInt(in.next());
        startY = Integer.parseInt(in.next());
        //create a player at this current position on the screen
        player = new Player(startX, startY,1,1);
        //this is counting the columns of screens in the map[][] 
        for (int mapRow = 0; mapRow < mapWidth; mapRow++) {
            //this is counting the number of column spots for the screens in the map[][]
            for (int mapCol = 0; mapCol < mapHeight; mapCol++) {
                //we are now at a new screen, so initilize it
                MapScreen screen = new MapScreen();
                //this is counting the number of rows in the map[][]for this row for this scecific screen
                for (int screenRow = 0; screenRow < ScreenTileHeight; screenRow++) {
                    //this is counting the number of columns in the map[][]for this row for this specific screen
                    for (int screenCol = 0; screenCol < ScreenTileWidth; screenCol++) {
                        //take this number and put it into its respective tile designation inside of its respective screen designation inside of the map
                        screen.setTile(screenRow, screenCol, Integer.parseInt(in.next()));
                    }
                }
                //now put this screen into the map[][]
                map.setScreen(mapRow, mapCol, screen);
                //we have now passed a screen worth of tiles, so move past the blank space ()separating the screens
                in.next();
            }
        }
        // initialize the spritebatch
        this.batch = puzzleGame.getBatch();
        //create the camera proportional to the mapScreen
        this.camera = new OrthographicCamera(ScreenTileWidth*1000, ScreenTileHeight*1000);
        //do the same for the viewport using the camera
        this.view = new FitViewport(ScreenTileWidth*1000, ScreenTileHeight*1000, camera);
        //finally apply the viewport
        view.apply();
        // move the camera to the center
        this.camera.position.set(ScreenTileWidth*1000/2, ScreenTileHeight*1000/2, 0);
        // make sure to apply the changes
        this.camera.update();
    }
    
    @Override
    public void show() {
        // this would be what happens when the game wakes up from being hidden
        // i.e. when you minimized the game and reopenned it
    }

    // the main game loop for this screen
    @Override
    public void render(float deltaTime) {
        player.update(deltaTime);
        /*
        // update the player
        player.update(deltaTime);
        
        //THIS NEEDS MODIFICATION
        // check for collisions and fix them
        for(Rectangle block: world.getBlocks()){
            player.fixCollision(block);
        }
        */
        
       
        // clears the screen in a black colour
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
        // ask the world to render
        // notice this is not in the SpriteBatch
        // This is because it uses its own ShapeRenderer
        map.render(camera, currentScreenX, currentScreenY);
        
        //WAITING ON RYAN
        /*
        if(player.getX() > 200){
            camera.position.x = player.getX();
        }
        */
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        // ask the SpriteBatch to start taking notes of what to draw
        batch.begin();
        
        //WAITING OR RYAN
        /*
	// ask the player to draw themself
        player.render(batch);
        */
        //Ask the player to draw themself
        player.render(batch);
        // tell the SpriteBatch we are good to draw everything now
	batch.end();
    }

    // used when the window is resized.... we haven't use it here
    @Override
    public void resize(int width, int height) {
        
    }

    // if the game could pause, what do you need to happen?
    @Override
    public void pause() {
        
    }

    // when the game is unpaused, what do you need to happen?
    @Override
    public void resume() {
        
    }

    // when the game needs to be minimized, what needs to happen?
    @Override
    public void hide() {
        
    }

    // get rid of heavy objects...
    @Override
    public void dispose() {
        
    }
    
    //getter for the screen tile height (helpful for the display)
    public void getScreenTileHeight(){
        //return the size of the 
    }
    
    //getter for the screen tile height (helpful for the display)
    public void getScreenTileWidth(){
        
    }
}
