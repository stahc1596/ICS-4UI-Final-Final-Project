/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author slatz8075
 */
public class MainGame {

    //create a variables for the dimentions of the map (dimentions in terms of # of screens)
    static int mapHeight;
    static int mapWidth;
    //create variables for the dimentions of the screen
    static int ScreenTileHeight;
    static int ScreenTileWidth;
    //create a new map to store the screens
    Map map = new Map();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //INITILIZATION
        //create a blank file
        FileReader file = null;
        //try to find the info file
        try {
            //create a url for the location of the info file
            URL url = MainGame.class.getResource("src//hhssAdv//info.txt");
            // creating the file reader
            file = new FileReader("src//hhssAdv//info.txt");
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
        //this is counting the columns of screens in the map[][] 
        for (int mapRow = 0; mapRow < mapWidth; mapRow++) {
            //this is counting the number of column spots for the screens in the map[][]
            for (int mapCol = 0; mapCol < mapHeight; mapCol++) {
                //we are now at a new screen, so initilize it
                Screen screen = new Screen();
                //this is counting the number of rows in the map[][]for this row for this scecific screen
                for (int screenRow = 0; screenRow < ScreenTileHeight; screenRow++) {
                    //this is counting the number of columns in the map[][]for this row for this specific screen
                    for (int screenCol = 0; screenCol < ScreenTileWidth; screenCol++) {
                        //take this int at this position
                        int 
                        //take this number and put it into its respective tile designation inside of its respective screen designation inside of the map
                        screen.setTile(screenRow, screenCol, in.next());
                        //move to the next character in line
                        in.next();
                    }
                }
                //now put this screen into the map[][]
                //map[mapRow][mapCol];//.setScreen(screenRow, screenCol, screen);
                //we have now passed a screen worth of tiles, so move past the blank space ()separating the screens
                in.next();
            }
        }
    }
}
