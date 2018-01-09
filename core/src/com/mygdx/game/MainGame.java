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
    int mapHeight;
    int mapWidth;
    //create variables 
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
    }
}
