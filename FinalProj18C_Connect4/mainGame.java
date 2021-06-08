/*
 * Created By: Rashad Naime, Carolina Buhler, Kevin Duong, Domenico Ventuti.
 * Purpose: Connect 4 app, final project of 18C.  Due: 12/11/20
 */
package Connect4Game;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList; 
import java.util.Queue; 
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//mainGame class is the interface that user will use to run the program and start the game. 
public class mainGame {
   public static void main(String[] args) { //Begin main method for program
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        boolean cont = false;                    // Boolean flag used to end menuing in main
        String search;                           // search variable used to take in input for binary search 
                
        //Start the display of the program    
        System.out.println("Welcome to the Connect 4 Program!");  
        //Create new connect 4 class, will prompt user(s) for names of player1 and player2
        Connect4Board game1 = new Connect4Board(); 

        //While loop used for users to access menu and end program by picking the option in the switch case
        while(cont == false){
            System.out.println("Please Select from the following:");
            System.out.println("1) Connect 4 Original");
            System.out.println("2) Binary Search for Player");
            System.out.println("3) Connect 4 Shuffle Mode ");
            System.out.println("4) Finish up and exit program");
            int select = myObj.nextInt();
            //Create a select variable and have user choose option
            switch (select){
                case 1:
                    //Start game using class functions. print board first then prompt player to select column to place pieces, then alternate between p1 and p2
                    game1.prntBoard(); 
                    boolean gameover = false; //This is the flag used to end the game, if set to true the game is over
                    while(gameover == false){ //while the flag is false run the game
                        game1.insrt4Brd(); //use class insertion function, users input 1-7 on keyboard to drop piece
                        System.out.println();
                        game1.prntBoard(); //reprint the board after each insertion to show the updated board
                        gameover = game1.p1win(); //check to see if player1 has met the win conditions
                        if(gameover == false){ // if player1 has not met the win condition, check player2
                            gameover = game1.p2win();
                        }
                        if(gameover == false){
                            gameover = game1.tieGame();
                                    
                        }
                    }
                    //After the game is finished, store player names and scores
                    //This try catch statement is for storing player names and highscores to file
                    try {
                       game1.storeData();
                    } catch (IOException ex) {
                       System.out.println("File not found");
                    }
                    //After everygame is complete, reset the map and queue if the user wishes to play again
                    game1.ResetGame();
                    System.out.println();                    
                    break;
                case 2:
                    Scanner input = new Scanner(System.in);
                    //This function is the binary search, searches for player name within the file PlayerNames.txt
                    System.out.println("Please enter Name of player you would like to look up:");
                    search = input.nextLine();
                    search = search.substring(0, 1).toUpperCase() + search.substring(1);
                    //Take in input for the search and pass it into the binary search function
                    game1.BinSrchPrnt(search);                    
                    break;
                case 3:
                    game1.shfflBrd(); //shuffle mode requires this function to place random pieces onto the board 
                    game1.prntBoard();
                    gameover = false; //This is the flag used to end the game, if set to true the game is over
                    while(gameover == false){ //while the flag is false run the game
                        game1.insrt4Brd(); //use class insertion function, users input 1-7 on keyboard to drop piece
                        System.out.println();
                        game1.prntBoard(); //reprint the board after each insertion to show the updated board
                        gameover = game1.p1win(); //check to see if player1 has met the win conditions
                        if(gameover == false){ // if player1 has not met the win condition, check player2
                            gameover = game1.p2win();
                        }
                        if(gameover == false){
                            gameover = game1.tieGame();
                                    
                        }                        
                    }
                    //After the game is finished, store player names and scores
                    //This try catch statement is for storing player names and highscores to file
                    try {
                       game1.storeData();
                    } catch (IOException ex) {
                       System.out.println("File not found");
                    }
                    //After everygame is complete, reset the map and queue if the user wishes to play again
                    game1.ResetGame();
                    System.out.println();                     
                    
                    break;
                case 4:
                    //set while loop flag to true to exit and end the program
                    cont = true;
                    break;                    
                default:
                    System.out.println("Please enter a proper menu number!");
                    break;    
            }            
        }                 
   } // end method main    
}//end of class 

