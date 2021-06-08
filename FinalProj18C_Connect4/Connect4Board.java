/*
 * Created By: Rashad Naime, Carolina Buhler, Kevin Duong, Domenico Ventuti.
 * Purpose: Connect 4 app, final project of 18C.  Due: 12/11/20
 */
package Connect4Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

//Connect4Board class, used to create and fun the game. 
public class Connect4Board {
    //Instance Variables 
    Map<Integer, Integer> board = new HashMap<Integer, Integer>(); //This is the map used to display the board, as well as insert pieces, and check for win condtions 
    Queue<Integer> turns = new LinkedList<>(); //This queue is used for piece insertion, will alternate between player 1 and player 2
    Players newPlayers = new Players();
    boolean pwin;  //Boolean flag used to determine winner
    int bttm;  //Used for placing pieces onto the map 
    
    //These variables are for aesthetics, colors the display of the board
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
// Constructor for connect 4, used to create and fill the map, and set the queue pieces 
public Connect4Board(){
    //for loop the map, fill the map with 42 key value pairs, used for the 6x7 connect four board
    for(int i = 1; i <= 42; i++){
        board.put(new Integer(i), 0);
    }    
    
    //check if the queue is empty, and then fill it with the players pieces, 1 for player 1, 2 for player 2 
    if(turns.isEmpty()){
        turns.add(1);
        turns.add(2);    
    }      
    newPlayers.setPlayers();
    //Check to see if player names have already been used from file, find previous score and set it to the player 
        try {
            newPlayers.setHighscores();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
}

//Function used to print entire board
public void prntBoard(){
    //iterate throught map and display entire board 
    int count = 0;
    String valCopy;
    System.out.println(ANSI_BLUE + " | 1 | | 2 | | 3 | | 4 | | 5 | | 6 | | 7 |" + ANSI_RESET);
    System.out.println(ANSI_BLUE +" -----------------------------------------" + ANSI_RESET);
    for (Map.Entry mapElement : board.entrySet()) { 
        int key = (int)mapElement.getKey(); 

        // Finding the value 
        int value = (Integer)mapElement.getValue(); 
        //Display the board with the values 
        if(value == 0){
        System.out.print(" " + ANSI_BLUE + "| " + ANSI_RESET);
        System.out.print(" ");
        System.out.print(ANSI_BLUE + " |" + ANSI_RESET);     
        }
        else if(value == 1){
            valCopy = Integer.toString(value);
            System.out.print(" "+"| ");
            System.out.print(ANSI_RED + valCopy + ANSI_RESET);
            System.out.print(" |");        
        }
        else if(value == 2){
            valCopy = Integer.toString(value);
            System.out.print(" " + ANSI_BLUE + "| " + ANSI_RESET);
            System.out.print(ANSI_YELLOW + valCopy + ANSI_RESET);
            System.out.print(ANSI_BLUE + " |" + ANSI_RESET);                
        }
        count++;
        if(count == 7){
        System.out.println();
        System.out.println(ANSI_BLUE +" -----------------------------------------" + ANSI_RESET);
        count = 0;
        }            
        //System.out.println(key + " : "+ value); 
    }//End of the print 
    
    
//Hold onto original print form just incase the ANSI_Colors cause issues for running the program elsewhere
    
//    //iterate throught map and display entire board 
//    int count = 0;
//    
//    System.out.println(" | 1 | | 2 | | 3 | | 4 | | 5 | | 6 | | 7 |");
//    System.out.println(" -----------------------------------------");
//    for (Map.Entry mapElement : board.entrySet()) { 
//        int key = (int)mapElement.getKey(); 
//
//        // Finding the value 
//        int value = (Integer)mapElement.getValue(); 
//        //Display the board with the values 
//        if(value == 0){
//        System.out.print(" "+"| ");
//        System.out.print(" ");
//        System.out.print(" |");     
//        }
//        else{
//            System.out.print(" "+"| ");
//            System.out.print(value);
//            System.out.print(" |");                
//        }
//        count++;
//        if(count == 7){
//        System.out.println();
//        System.out.println(" -----------------------------------------");
//        count = 0;
//        }            
//        //System.out.println(key + " : "+ value); 
//    }//End of the print    
}

//function to create a shuffled map that is used for shuffle mode 
public void shfflBrd(){
    //Stack used to insert random pieces onto board
    Stack<Integer>  randPieces = new Stack<Integer>();
    List keys = new ArrayList(this.board.keySet()); //array list used to store the keys of the map
    Collections.shuffle(keys); //then shuffle those keys to randomize where the pieces will be placed
    Iterator<Integer> it = keys.iterator(); //Iterate through the shuffled Arraylist of keys to check it value
    
    //Create for loop to store random pieces onto the the randPieces ArrayList
    for(int i = 1; i <= 7; i++){ //adding 14 player pieces onto the map in total
        randPieces.push(1);
        randPieces.push(2);
    }
    
    //While the iterator contains the key values
    while(it.hasNext()) {
      while(!randPieces.isEmpty()){ //while the stack hasnt been emptied
          board.replace(it.next(), randPieces.pop()); //replace the random key location with a random piece using the iterator and stack
      }
      if(randPieces.isEmpty()){ //If all the pieces have been placed
          it.next();//finish iterating and finish the loop 
      }
    }    
     System.out.println();
}

//Function used to take player input to insert pieces onto the board. 
public void insrt4Brd(){
    Scanner myObj = new Scanner(System.in);
    //pieceDrp is the variable used to select the column to drop pieces into
    int pieceDrp = 0;//take in the input from the user for column selection to drop the piece
    
    //Check the queue to see who's turn it is, then prompt that player to take their turn
    if(turns.peek() == 1){
        System.out.println("Player1: "+this.newPlayers.getplayer1()+" With current score: " + this.newPlayers.getHighscore1());
        System.out.println("Enter a column number to insert a piece:");
        pieceDrp = myObj.nextInt();
    }
    if(turns.peek() == 2){
        System.out.println("Player2: "+this.newPlayers.getplayer2()+" With current score: " + this.newPlayers.getHighscore2());
        System.out.println("Enter a column number to insert a piece:");
        pieceDrp = myObj.nextInt();
    }    
    //Options for pieceDrop are 1-7, this will drop the piece into the column number selected
    if(pieceDrp == 1){ //bttm variable will start at the lowest point in each column
        boolean top = false;
        bttm = 36; //When piece drop = 1, set bttm = 36 the lowest point in column 1
        int it = board.get(bttm); //set the iterator at that spot 

        while(it != 0 && top == false){ //use this while loop to move to the next highest point if there are piece placed already
            bttm -= 7; //increment bottom by -= 7 to the next highest spot
            if(bttm < 1){ //If its hit the top, set top to true, and exit while
                top = true;
            } 
            else{
                it = board.get(bttm); //set the iterator to the next highest spot
            }
        }
        //Check to see if iterator is not at the end of the map, and bttm >=1 to set the next piece
        System.out.println(turns.peek());
        
        if(it == 0 && bttm >= 1){
            board.replace(bttm, turns.peek());//using queue insert the piece
        }
        turns.remove(); //pop the queue to move on to the next players piece

        //If the queue is empty, that means both players have set their pieces, so push the pieces back onto the queue
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        }             
    }   
    if(pieceDrp == 2){
        boolean top = false;
        bttm = 37; //When piece drop = 1, set bttm = 36 the lowest point in column 1
        int it = board.get(bttm); //set the iterator at that spot 

        while(it != 0 && top == false){ //use this while loop to move to the next highest point if there are piece placed already
            bttm -= 7; //increment bottom by -= 7 to the next highest spot
            if(bttm < 1){ //If its hit the top, set top to true, and exit while
                top = true;
            } 
            else{
                it = board.get(bttm); //set the iterator to the next highest spot
            }
        }
        //Check to see if iterator is not at the end of the map, and bttm >=1 to set the next piece
        System.out.println(turns.peek());
        if(it == 0 && bttm >= 2){
            board.replace(bttm, turns.peek());//using queue insert the piece
        }
        turns.remove(); //pop the queue to move on to the next players piece

        //If the queue is empty, that means both players have set their pieces, so push the pieces back onto the queue
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        }             
    } 
    if(pieceDrp == 3){
        boolean top = false;
        bttm = 38; //When piece drop = 1, set bttm = 36 the lowest point in column 1
        int it = board.get(bttm); //set the iterator at that spot 

        while(it != 0 && top == false){ //use this while loop to move to the next highest point if there are piece placed already
            bttm -= 7; //increment bottom by -= 7 to the next highest spot
            if(bttm < 1){ //If its hit the top, set top to true, and exit while
                top = true;
            } 
            else{
                it = board.get(bttm); //set the iterator to the next highest spot
            }
        }
        //Check to see if iterator is not at the end of the map, and bttm >=1 to set the next piece
        System.out.println(turns.peek());
        if(it == 0 && bttm >= 3){
            board.replace(bttm, turns.peek());//using queue insert the piece
        }
        turns.remove(); //pop the queue to move on to the next players piece

        //If the queue is empty, that means both players have set their pieces, so push the pieces back onto the queue
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        }             
    }  
    if(pieceDrp == 4){
        boolean top = false;
        bttm = 39; //When piece drop = 1, set bttm = 36 the lowest point in column 1
        int it = board.get(bttm); //set the iterator at that spot 

        while(it != 0 && top == false){ //use this while loop to move to the next highest point if there are piece placed already
            bttm -= 7; //increment bottom by -= 7 to the next highest spot
            if(bttm < 1){ //If its hit the top, set top to true, and exit while
                top = true;
            } 
            else{
                it = board.get(bttm); //set the iterator to the next highest spot
            }
        }
        //Check to see if iterator is not at the end of the map, and bttm >=1 to set the next piece
        System.out.println(turns.peek());
        if(it == 0 && bttm >= 4){
            board.replace(bttm, turns.peek());//using queue insert the piece
        }
        turns.remove(); //pop the queue to move on to the next players piece

        //If the queue is empty, that means both players have set their pieces, so push the pieces back onto the queue
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        }             
    }
    if(pieceDrp == 5){
        boolean top = false;
        bttm = 40; //When piece drop = 1, set bttm = 36 the lowest point in column 1
        int it = board.get(bttm); //set the iterator at that spot 

        while(it != 0 && top == false){ //use this while loop to move to the next highest point if there are piece placed already
            bttm -= 7; //increment bottom by -= 7 to the next highest spot
            if(bttm < 1){ //If its hit the top, set top to true, and exit while
                top = true;
            } 
            else{
                it = board.get(bttm); //set the iterator to the next highest spot
            }
        }
        //Check to see if iterator is not at the end of the map, and bttm >=1 to set the next piece
        System.out.println(turns.peek());
        if(it == 0 && bttm >= 5){
            board.replace(bttm, turns.peek());//using queue insert the piece
        }
        turns.remove(); //pop the queue to move on to the next players piece

        //If the queue is empty, that means both players have set their pieces, so push the pieces back onto the queue
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        }             
    }
    if(pieceDrp == 6){
        boolean top = false;
        bttm = 41; //When piece drop = 1, set bttm = 36 the lowest point in column 1
        int it = board.get(bttm); //set the iterator at that spot 

        while(it != 0 && top == false){ //use this while loop to move to the next highest point if there are piece placed already
            bttm -= 7; //increment bottom by -= 7 to the next highest spot
            if(bttm < 1){ //If its hit the top, set top to true, and exit while
                top = true;
            } 
            else{
                it = board.get(bttm); //set the iterator to the next highest spot
            }
        }
        //Check to see if iterator is not at the end of the map, and bttm >=1 to set the next piece
        System.out.println(turns.peek());
        if(it == 0 && bttm >= 6){
            board.replace(bttm, turns.peek());//using queue insert the piece
        }
        turns.remove(); //pop the queue to move on to the next players piece

        //If the queue is empty, that means both players have set their pieces, so push the pieces back onto the queue
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        }             
    }
    if(pieceDrp == 7){
        boolean top = false;
        bttm = 42; //When piece drop = 1, set bttm = 36 the lowest point in column 1
        int it = board.get(bttm); //set the iterator at that spot 

        while(it != 0 && top == false){ //use this while loop to move to the next highest point if there are piece placed already
            bttm -= 7; //increment bottom by -= 7 to the next highest spot
            if(bttm < 1){ //If its hit the top, set top to true, and exit while
                top = true;
            } 
            else{
                it = board.get(bttm); //set the iterator to the next highest spot
            }
        }
        //Check to see if iterator is not at the end of the map, and bttm >=1 to set the next piece
        System.out.println(turns.peek());
        if(it == 0 && bttm >= 7){
            board.replace(bttm, turns.peek());//using queue insert the piece
        }
        turns.remove(); //pop the queue to move on to the next players piece

        //If the queue is empty, that means both players have set their pieces, so push the pieces back onto the queue
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        }             
    }
    if(pieceDrp < 1 || pieceDrp > 7){
        turns.remove();
        System.out.println("Piece was not placed in a column, you missed the board and your turn oops!");
        if(turns.isEmpty()){
            turns.add(1);
            turns.add(2);    
        } 
    }    
}

//Check for the win conditions for player 1
public boolean p1win(){
    //start the winning flag as false 
    pwin = false; 
    
    //Starting at Column 1
    //These variables represent the rows, the lowest spot in column 1 is row1 = (spot) 36, the starting position 
    int frstR = 36;       //spot 36 row 1 (bottom left corner)
    int scndR = 29;     //spot 29 row 2 
    int thrdR = 22;     // spot 22 row 3
    int frthR = 15;     // spot 15 row 4
    int fthR = 8;       //Spot 8 row 5
    int sxR = 1;        //spot 1 row 6 (top left corner)     
    
    //Win check variables for function
    //Need to check win conditions for every row in column 1-7
    int horiz, horiz2, horiz3, horiz4, horiz5, horiz6; //The horizontal win condition
    horiz = horiz2 = horiz3 = horiz4 = horiz5 = horiz6 = 0;
    
    int vert, vert2, vert3, vert4, vert5, vert6;       //The Vertical win condition
    vert = vert2 = vert3 = vert4 = vert5 = vert6 = 0;
    
    int diag, diag2, diag3, diag4, diag5, diag6;       //The diagonal win condition
    diag = diag2 = diag3 = diag4 = diag5 = diag6 = 0;


    int count = 1; //This counter will act as a column variable, every time its incremented, shift all rows by 1 column
    while(count <= 7 && pwin == false){
        
        //As count is incremented, shift all row variables by 1 column 
        if(count == 1){
            frstR = 36; scndR = 29; thrdR = 22; frthR = 15; fthR = 8; sxR = 1;
        }
        if(count == 2){
            frstR = 37; scndR = 30; thrdR = 23; frthR = 16; fthR = 9; sxR = 2;
        }
        if(count == 3){
            frstR = 38; scndR = 31; thrdR = 24; frthR = 17; fthR = 10; sxR = 3;
        }
        if(count == 4){
            frstR = 39; scndR = 32; thrdR = 25; frthR = 18; fthR = 11; sxR = 4;
        }   
        if(count == 5){
            frstR = 40; scndR = 33; thrdR = 26; frthR = 19; fthR = 12; sxR = 5;
        }   
        if(count == 6){
            frstR = 41; scndR = 34; thrdR = 27; frthR = 20; fthR = 13; sxR = 6;
        }   
        if(count == 7){
            frstR = 42; scndR = 35; thrdR = 28; frthR = 21; fthR = 14; sxR = 7;
        }        
        int it1 = board.get(frstR);
        int it2 = board.get(scndR);
        int it3 = board.get(thrdR);
        int it4 = board.get(frthR);
        int it5 = board.get(fthR);
        int it6 = board.get(sxR);

        
        //Need to check for horizontal win condition for columns 1-4
        for(int i = 1; i <= 4; i++){
            if(count < 5){
                //Check for the horizontal win condition in each row for every column 1-4
                if(it1 == 1){ //iterator it starts at row1, check to see if a piece is within that map spot            
                    horiz++;         //increment horizontal counter for row 1 (if the value >= 4) win condition has been met
                    frstR += 1;      //increment the row variable by += 1 to traverse to the next horizontal spot
                    if(frstR <= 42){
                        it1 = board.get(frstR); //Place the variable back into the iterator and traverse to the next spot (then repeat process)
                    }
                } 
                if(it2 == 1){         
                    horiz2++;
                    scndR += 1;
                    it2 = board.get(scndR);
                }   
                if(it3 == 1){         
                    horiz3++;
                    thrdR += 1;
                    it3 = board.get(thrdR);
                } 
                if(it4 == 1){         
                    horiz4++;
                    frthR += 1;
                    it4 = board.get(frthR);
                } 
                if(it5 == 1){         
                    horiz5++;
                    fthR += 1;
                    it5 = board.get(fthR);
                } 
                if(it6 == 1){         
                    horiz6++;
                    sxR += 1;
                    it6 = board.get(sxR);
                }  
            }
        }
        //Reset each row spot after each check
        if(count == 1){
            frstR = 36; scndR = 29; thrdR = 22; frthR = 15; fthR = 8; sxR = 1;
        }
        if(count == 2){
            frstR = 37; scndR = 30; thrdR = 23; frthR = 16; fthR = 9; sxR = 2;
        }
        if(count == 3){
            frstR = 38; scndR = 31; thrdR = 24; frthR = 17; fthR = 10; sxR = 3;
        }
        if(count == 4){
            frstR = 39; scndR = 32; thrdR = 25; frthR = 18; fthR = 11; sxR = 4;
        }   
        if(count == 5){
            frstR = 40; scndR = 33; thrdR = 26; frthR = 19; fthR = 12; sxR = 5;
        }   
        if(count == 6){
            frstR = 41; scndR = 34; thrdR = 27; frthR = 20; fthR = 13; sxR = 6;
        }   
        if(count == 7){
            frstR = 42; scndR = 35; thrdR = 28; frthR = 21; fthR = 14; sxR = 7;
        }        
        //Reset variables to do the next check 
        it1 = board.get(frstR);
        it2 = board.get(scndR);
        it3 = board.get(thrdR);
        it4 = board.get(frthR);
        it5 = board.get(fthR);
        it6 = board.get(sxR);        
        
        //Need to check for vertical win for every column 
        for(int i = 1; i <= 4; i++){
                //Check for the horizontal win condition in each row for every column 1-4
                if(it1 == 1){ //iterator it starts at row1, check to see if a piece is within that map spot            
                    vert++;         //increment horizontal counter for row 1 (if the value >= 4) win condition has been met
                    frstR -= 7;      //increment the row variable by += 1 to traverse to the next horizontal spot
                    it1 = board.get(frstR); //Place the variable back into the iterator and traverse to the next spot (then repeat process)
                } 
                if(it2 == 1){         
                    vert2++;
                    scndR -= 7;
                    it2 = board.get(scndR);
                }   
                if(it3 == 1){         
                    vert3++;
                    thrdR -= 7;
                    if(thrdR >= 1){
                        it3 = board.get(thrdR);
                    }
                }   
        }     
        
        //Reset each row spot after each check
        if(count == 1){
            frstR = 36; scndR = 29; thrdR = 22; frthR = 15; fthR = 8; sxR = 1;
        }
        if(count == 2){
            frstR = 37; scndR = 30; thrdR = 23; frthR = 16; fthR = 9; sxR = 2;
        }
        if(count == 3){
            frstR = 38; scndR = 31; thrdR = 24; frthR = 17; fthR = 10; sxR = 3;
        }
        if(count == 4){
            frstR = 39; scndR = 32; thrdR = 25; frthR = 18; fthR = 11; sxR = 4;
        }   
        if(count == 5){
            frstR = 40; scndR = 33; thrdR = 26; frthR = 19; fthR = 12; sxR = 5;
        }   
        if(count == 6){
            frstR = 41; scndR = 34; thrdR = 27; frthR = 20; fthR = 13; sxR = 6;
        }   
        if(count == 7){
            frstR = 42; scndR = 35; thrdR = 28; frthR = 21; fthR = 14; sxR = 7;
        }        
        //Reset variables to do the next check 
        it1 = board.get(frstR);
        it2 = board.get(scndR);
        it3 = board.get(thrdR);
        it4 = board.get(frthR);
        it5 = board.get(fthR);
        it6 = board.get(sxR); 

        //Need to check for diagonal win condition for columns 1-4
        for(int i = 1; i <= 4; i++){
            if(count < 5){
                //Check for the horizontal win condition in each row for every column 1-4
                if(it1 == 1){ //iterator it starts at row1, check to see if a piece is within that map spot            
                    diag++;         //increment horizontal counter for row 1 (if the value >= 4) win condition has been met
                    frstR -= 6;      //increment the row variable by += 1 to traverse to the next horizontal spot
                    if(frstR <= 42){
                        it1 = board.get(frstR); //Place the variable back into the iterator and traverse to the next spot (then repeat process)
                    }
                } 
                if(it2 == 1){         
                    diag2++;
                    scndR -= 6;
                    it2 = board.get(scndR);
                }   
                if(it3 == 1){         
                    diag3++;
                    thrdR -= 6;
                    if(thrdR >= 1){
                        it3 = board.get(thrdR);
                    }
                } 
                //check diagonally down 
                if(it4 == 1){         
                    diag4++;
                    frthR += 8;
                    if(frthR <= 42){
                        it4 = board.get(frthR);
                    }
                } 
                if(it5 == 1){         
                    diag5++;
                    fthR += 8;
                    it5 = board.get(fthR);
                } 
                if(it6 == 1){         
                    diag6++;
                    sxR += 8;
                    it6 = board.get(sxR);
                }  
            }
        }        
        //Check if any of the win conditions have been met from any of the row locations of each column
        if(horiz >= 4 || horiz2 >=4 || horiz3 >=4 || horiz4 >=4 || horiz5 >=4 || horiz6 >=4){
            newPlayers.addScore1();
            System.out.println("Player 1:"+ newPlayers.getplayer1()+" with new highscore:" + newPlayers.getHighscore1()+ " Wins!");
            pwin = true;                                 //set flag to true
            return pwin;                                 //Return flag
        }
        else{
            horiz = horiz2 = horiz3 = horiz4 = horiz5 = horiz6 = 0; //If none of the win condition are met reset the checks 
        }  
        if(vert >= 4 || vert2 >=4 || vert3 >=4 || vert4 >=4 || vert5 >=4 || vert6 >=4){
            newPlayers.addScore1();
                System.out.println("Player 1:"+ newPlayers.getplayer1()+" with new highscore:" + newPlayers.getHighscore1()+ " Wins!");

            pwin = true; 
            return pwin;        
        }
        else{
            vert = vert2 = vert3 = vert4 = vert5 = vert6 = 0;     //If none of the win condition are met reset the checks 
        }
        if(diag >= 4 || diag2 >=4 || diag3 >=4 || diag4 >=4 || diag5 >=4 || diag6 >=4){
            newPlayers.addScore1();
            System.out.println("Player 1:"+ newPlayers.getplayer1()+" with new highscore:" + newPlayers.getHighscore1()+ " Wins!");
            pwin = true; 
            return pwin;        
        }
        else{
            diag = diag2 = diag3 = diag4 = diag5 = diag6 = 0;    //If none of the win condition are met reset the checks 
        }        
        count++; //increment counter to check each column
    }
    return pwin; 
}

//check the win condition for player 2
public boolean p2win(){
    //start the winning flag as false 
    pwin = false; 
    
    //Starting at Column 1
    //These variables represent the rows, the lowest spot in column 1 is row1 = (spot) 36, the starting position 
    int frstR = 36;       //spot 36 row 1 (bottom left corner)
    int scndR = 29;     //spot 29 row 2 
    int thrdR = 22;     // spot 22 row 3
    int frthR = 15;     // spot 15 row 4
    int fthR = 8;       //Spot 8 row 5
    int sxR = 1;        //spot 1 row 6 (top left corner)     
    
    //Win check variables for function
    //Need to check win conditions for every row in column 1-7
    int horiz, horiz2, horiz3, horiz4, horiz5, horiz6; //The horizontal win condition
    horiz = horiz2 = horiz3 = horiz4 = horiz5 = horiz6 = 0;
    
    int vert, vert2, vert3, vert4, vert5, vert6;       //The Vertical win condition
    vert = vert2 = vert3 = vert4 = vert5 = vert6 = 0;
    
    int diag, diag2, diag3, diag4, diag5, diag6;       //The diagonal win condition
    diag = diag2 = diag3 = diag4 = diag5 = diag6 = 0;


    int count = 1; //This counter will act as a column variable, every time its incremented, shift all rows by 1 column
    while(count <= 7 && pwin == false){
        
        //As count is incremented, shift all row variables by 1 column 
        if(count == 1){
            frstR = 36; scndR = 29; thrdR = 22; frthR = 15; fthR = 8; sxR = 1;
        }
        if(count == 2){
            frstR = 37; scndR = 30; thrdR = 23; frthR = 16; fthR = 9; sxR = 2;
        }
        if(count == 3){
            frstR = 38; scndR = 31; thrdR = 24; frthR = 17; fthR = 10; sxR = 3;
        }
        if(count == 4){
            frstR = 39; scndR = 32; thrdR = 25; frthR = 18; fthR = 11; sxR = 4;
        }   
        if(count == 5){
            frstR = 40; scndR = 33; thrdR = 26; frthR = 19; fthR = 12; sxR = 5;
        }   
        if(count == 6){
            frstR = 41; scndR = 34; thrdR = 27; frthR = 20; fthR = 13; sxR = 6;
        }   
        if(count == 7){
            frstR = 42; scndR = 35; thrdR = 28; frthR = 21; fthR = 14; sxR = 7;
        }        
        int it1 = board.get(frstR);
        int it2 = board.get(scndR);
        int it3 = board.get(thrdR);
        int it4 = board.get(frthR);
        int it5 = board.get(fthR);
        int it6 = board.get(sxR);

        
        //Need to check for horizontal win condition for columns 1-4
        for(int i = 1; i <= 4; i++){
            if(count < 5){
                //Check for the horizontal win condition in each row for every column 1-4
                if(it1 == 2){ //iterator it starts at row1, check to see if a piece is within that map spot            
                    horiz++;         //increment horizontal counter for row 1 (if the value >= 4) win condition has been met
                    frstR += 1;      //increment the row variable by += 1 to traverse to the next horizontal spot
                    if(frstR <= 42){
                        it1 = board.get(frstR); //Place the variable back into the iterator and traverse to the next spot (then repeat process)
                    }
                } 
                if(it2 == 2){         
                    horiz2++;
                    scndR += 1;
                    it2 = board.get(scndR);
                }   
                if(it3 == 2){         
                    horiz3++;
                    thrdR += 1;
                    it3 = board.get(thrdR);
                } 
                if(it4 == 2){         
                    horiz4++;
                    frthR += 1;
                    it4 = board.get(frthR);
                } 
                if(it5 == 2){         
                    horiz5++;
                    fthR += 1;
                    it5 = board.get(fthR);
                } 
                if(it6 == 2){         
                    horiz6++;
                    sxR += 1;
                    it6 = board.get(sxR);
                }  
            }
        }
        //Reset each row spot after each check
        if(count == 1){
            frstR = 36; scndR = 29; thrdR = 22; frthR = 15; fthR = 8; sxR = 1;
        }
        if(count == 2){
            frstR = 37; scndR = 30; thrdR = 23; frthR = 16; fthR = 9; sxR = 2;
        }
        if(count == 3){
            frstR = 38; scndR = 31; thrdR = 24; frthR = 17; fthR = 10; sxR = 3;
        }
        if(count == 4){
            frstR = 39; scndR = 32; thrdR = 25; frthR = 18; fthR = 11; sxR = 4;
        }   
        if(count == 5){
            frstR = 40; scndR = 33; thrdR = 26; frthR = 19; fthR = 12; sxR = 5;
        }   
        if(count == 6){
            frstR = 41; scndR = 34; thrdR = 27; frthR = 20; fthR = 13; sxR = 6;
        }   
        if(count == 7){
            frstR = 42; scndR = 35; thrdR = 28; frthR = 21; fthR = 14; sxR = 7;
        }        
        //Reset variables to do the next check 
        it1 = board.get(frstR);
        it2 = board.get(scndR);
        it3 = board.get(thrdR);
        it4 = board.get(frthR);
        it5 = board.get(fthR);
        it6 = board.get(sxR);        
        
        //Need to check for vertical win for every column 
        for(int i = 1; i <= 4; i++){
                //Check for the horizontal win condition in each row for every column 1-4
                if(it1 == 2){ //iterator it starts at row1, check to see if a piece is within that map spot            
                    vert++;         //increment horizontal counter for row 1 (if the value >= 4) win condition has been met
                    frstR -= 7;      //increment the row variable by += 1 to traverse to the next horizontal spot
                    it1 = board.get(frstR); //Place the variable back into the iterator and traverse to the next spot (then repeat process)
                } 
                if(it2 == 2){         
                    vert2++;
                    scndR -= 7;
                    it2 = board.get(scndR);
                }   
                if(it3 == 2){         
                    vert3++;
                    thrdR -= 7;
                    if(thrdR >= 1){
                        it3 = board.get(thrdR);
                    }
                }   
        }     
        
        //Reset each row spot after each check
        if(count == 1){
            frstR = 36; scndR = 29; thrdR = 22; frthR = 15; fthR = 8; sxR = 1;
        }
        if(count == 2){
            frstR = 37; scndR = 30; thrdR = 23; frthR = 16; fthR = 9; sxR = 2;
        }
        if(count == 3){
            frstR = 38; scndR = 31; thrdR = 24; frthR = 17; fthR = 10; sxR = 3;
        }
        if(count == 4){
            frstR = 39; scndR = 32; thrdR = 25; frthR = 18; fthR = 11; sxR = 4;
        }   
        if(count == 5){
            frstR = 40; scndR = 33; thrdR = 26; frthR = 19; fthR = 12; sxR = 5;
        }   
        if(count == 6){
            frstR = 41; scndR = 34; thrdR = 27; frthR = 20; fthR = 13; sxR = 6;
        }   
        if(count == 7){
            frstR = 42; scndR = 35; thrdR = 28; frthR = 21; fthR = 14; sxR = 7;
        }        
        //Reset variables to do the next check 
        it1 = board.get(frstR);
        it2 = board.get(scndR);
        it3 = board.get(thrdR);
        it4 = board.get(frthR);
        it5 = board.get(fthR);
        it6 = board.get(sxR); 

        //Need to check for diagonal win condition for columns 1-4
        for(int i = 1; i <= 4; i++){
            if(count < 5){
                //Check for the horizontal win condition in each row for every column 1-4
                if(it1 == 2){ //iterator it starts at row1, check to see if a piece is within that map spot            
                    diag++;         //increment horizontal counter for row 1 (if the value >= 4) win condition has been met
                    frstR -= 6;      //increment the row variable by += 1 to traverse to the next horizontal spot
                    if(frstR <= 42){
                        it1 = board.get(frstR); //Place the variable back into the iterator and traverse to the next spot (then repeat process)
                    }
                } 
                if(it2 == 2){         
                    diag2++;
                    scndR -= 6;
                    it2 = board.get(scndR);
                }   
                if(it3 == 2){         
                    diag3++;
                    thrdR -= 6;
                    if(thrdR >= 1){
                        it3 = board.get(thrdR);
                    }
                } 
                //check diagonally down 
                if(it4 == 2){         
                    diag4++;
                    frthR += 8;
                    if(frthR <= 42){
                        it4 = board.get(frthR);
                    }
                } 
                if(it5 == 2){         
                    diag5++;
                    fthR += 8;
                    it5 = board.get(fthR);
                } 
                if(it6 == 2){         
                    diag6++;
                    sxR += 8;
                    it6 = board.get(sxR);
                }  
            }
        }        
        //Check if any of the win conditions have been met from any of the row locations of each column
        if(horiz >= 4 || horiz2 >=4 || horiz3 >=4 || horiz4 >=4 || horiz5 >=4 || horiz6 >=4){
            newPlayers.addScore2();
            System.out.println("Player 2:"+ newPlayers.getplayer2()+" with new highscore:" + newPlayers.getHighscore2()+ " Wins!");
            pwin = true;                                 //set flag to true
            return pwin;                                 //Return flag
        }
        else{
            horiz = horiz2 = horiz3 = horiz4 = horiz5 = horiz6 = 0; //If none of the win condition are met reset the checks 
        }  
        if(vert >= 4 || vert2 >=4 || vert3 >=4 || vert4 >=4 || vert5 >=4 || vert6 >=4){  
            newPlayers.addScore2();
            System.out.println("Player 2:"+ newPlayers.getplayer2()+" with new highscore:" + newPlayers.getHighscore2()+ " Wins!");
            pwin = true; 
            return pwin;        
        }
        else{
            vert = vert2 = vert3 = vert4 = vert5 = vert6 = 0;     //If none of the win condition are met reset the checks 
        }
        if(diag >= 4 || diag2 >=4 || diag3 >=4 || diag4 >=4 || diag5 >=4 || diag6 >=4){  
            newPlayers.addScore2();
            System.out.println("Player 2:"+ newPlayers.getplayer2()+" with new highscore:" + newPlayers.getHighscore2()+ " Wins!");
            pwin = true; 
            return pwin;        
        }
        else{
            diag = diag2 = diag3 = diag4 = diag5 = diag6 = 0;    //If none of the win condition are met reset the checks 
        }        
        count++; //increment counter to check each column
    }
    return pwin;
}

//If neither of the win conditions of met for either player then check to see if the game is tied
public boolean tieGame(){
    
    //increment the tie value, when it reaches 42 the entire board is full, then return true for tied game
    int tie = 0;
    
    //iterate throught map 
    for (Map.Entry mapElement : board.entrySet()) { 
        //set key
        int key = (int)mapElement.getKey(); 

        //get the value of each spot on the map
        int value = (Integer)mapElement.getValue(); 
        
        //If the spot on the map contains a piece, then increment the tie value
        if(value == 1 || value == 2){
           tie++;
        }
    }  
    //If all 42 spots have been filled with win conditions not being met then display tie message and end game. 
    if(tie == 42){
        System.out.println("Game has ended in a tie!");
        return true; 
    }
    
    //If the board has not been completely filled then return tied game as false 
    return false;
}

//This function is used to store the player Names and Highscore using the Players Class
public void storeData() throws IOException{
    newPlayers.filePlayers();
}

//Binary search print function, prints out the results from the binary search function from players class
public void BinSrchPrnt(String search){
    //Use try catch with IOexception because the binary search requires the PlayerNames.txt file
        try {
            //Set result equal to the result of the binary search
            String result = newPlayers.BinarySearch(search);
            if(result != null){ //If the binary search returns a name, diplay player found as well as the name and highscore
                System.out.println();
                System.out.println("Player was found: ");
                System.out.println(result);
                System.out.println();
            }
            else{
                System.out.println();
                System.out.println("Player not found "); //If it isnt found then display message
                System.out.println();
            }
        } catch (IOException ex) {
            System.out.println("File not found");
            }
}

//Function used to reset the map and queues after a game has been completed 
public void ResetGame(){
    //Replace all values on board back to zero
    for(int i = 1; i <= 42; i++){
        board.replace(new Integer(i), 0);
    } 
    //empty the turns queue and reset the values back to proper order
    turns.removeAll(turns);
    //check if the queue is empty, and then fill it with the players pieces, 1 for player 1, 2 for player 2 
    if(turns.isEmpty()){
        turns.add(1);
        turns.add(2);    
    } 
}
}
