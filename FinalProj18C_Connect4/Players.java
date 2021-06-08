/*
 * Created By: Rashad Naime, Carolina Buhler, Kevin Duong, Domenico Ventuti.
 * Purpose: Connect 4 app, final project of 18C.  Due: 12/11/20
 */
package Connect4Game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.Queue;
import java.util.Iterator;

//This class will be used to display player names,
public class Players {
    private String player1; //Player1 and 2 variables are used for taking in the names of the players 
    private String player2;
    private int highscore1; 
    private int highscore2;
    private LinkedList<String> players = new LinkedList<String>(); //The player names are stored into an ordered collection linkedlist used to display player names during the game

//Players default constructor, sets default names for the player1 and 2, and sets the new highscore = 0    
public Players(){
    //Default constructor will give default names for players 
    this.player1 = "Player1Default";
    this.player2 = "Player2Default";
    this.highscore1 = 0;
    this.highscore2 = 0;
}  

//Use this function to take in user input for player names 
public void setPlayers(){
    //Set up the scanner to take in input of player names
    Scanner names = new Scanner(System.in);
    
    //take in input for player names, and force capitilization on the first letter, for sorting purposes in the file
    System.out.println("Please enter the name of player 1: ");
    player1 = names.nextLine();
    player1 = player1.substring(0, 1).toUpperCase() + player1.substring(1);
    
    //Same process for player 2
    System.out.println("Please enter the name of player 2: ");
    player2 = names.nextLine();    
    player2 = player2.substring(0, 1).toUpperCase() + player2.substring(1);
    
    //Insert the player names into the linked list that will get called throughout the connect4board class 
    players.add(player1);
    players.add(player2);
}

//create getters to return the name of player1 and player2
public String getplayer1(){    
    return players.getFirst();
}

public String getplayer2(){
    return players.getLast();
}

//if a player wins a game call this addscore function to increase their highscore
public void addScore1(){
    this.highscore1 += 1000;
}

public void addScore2(){
    this.highscore2 += 1000;
}

//Check to see if playername has been used and copy that players current score from the file, then it will be updated and stored back on PlayerNames.txt
public void setHighscores() throws IOException{
    //Read the entire file line by line, and check to see if inputted names match with names on file
    try (BufferedReader br = new BufferedReader(new FileReader("PlayerInfo.txt"))) {
        String line;
        int ncount = 0;
        String[] phscores = new String[10000];
        
        //Read the file line by line, each line contains the name of a player and their highscore
        while ((line = br.readLine()) != null) {    
            String[] array = line.split(" "); //us an array to split the strings of each line to seperate player name and score
            for (String a : array){ //loop through entire split array
                phscores[ncount] = a; //ever 2 elements should contain a name, and its highscore
                ncount++; //increment counter to set next element
            }
        }
        
        //Use this for loop to loop through the array containing names/highscores 
        for (int i = 0; i < phscores.length; i+=2) {
            //If the array element and element + 1 are not equal to null compare values
            if(phscores[i] != null && phscores[i+1] != null){
                //check to see if inputted names from players match any within existing file
                if(players.getFirst().equals(phscores[i])){
                    this.highscore1 = Integer.parseInt(phscores[i+1]); //if it does, copy the score but not the name, to rewrite over existing name and update the score
                }   
                else if(players.getLast().equals(phscores[i])){
                    this.highscore2 = Integer.parseInt(phscores[i+1]); //if it does, copy the score but not the name, to rewrite over existing name and update the score
                }
            }
        }        
    } 
    catch (FileNotFoundException fe) 
    { 
        System.out.println("File not found"); 
    }
}

//get the highscores to display during the game, and when someone wins
public int getHighscore1(){
    return this.highscore1;
}

public int getHighscore2(){
    return this.highscore2;
}

//This function is used to store the player names and score into the file PlayerNames.txt
public void filePlayers() throws IOException{
    //Use this linked list to read and write to file 
    LinkedList<String> sFile = new LinkedList<String>(); //linkedlist used to sort file

    //Use try catch to open file and read line by line
    try (BufferedReader br = new BufferedReader(new FileReader("PlayerInfo.txt"))) {
        String line;
        int ncount = 0;
        String[] phscores = new String[10000];
        
        //Read the file line by line, each line contains the name of a player and their highscore
        while ((line = br.readLine()) != null) {    
            String[] array = line.split(" "); //us an array to split the strings of each line to seperate player name and score
            for (String a : array){ //loop through entire split array
                phscores[ncount] = a; //ever 2 elements should contain a name, and its highscore
                ncount++; //increment counter to set next element
            }
        }
        
        //Use this for loop to loop through the array containing names/highscores 
        for (int i = 0; i < phscores.length; i+=2) {
            //If the array element and element + 1 are not equal to null compare values
            if(phscores[i] != null && phscores[i+1] != null){
                //System.out.println("Str[" + i + "]:" + phscores[i]);
                
                //check to see if inputted names from players match any within existing file
                if(players.getFirst().equals(phscores[i])){
                    //If the player name matches dont copy it over from file, just rewrite it with new score
                }
                else if(players.getLast().equals(phscores[i])){
                    //If the player name matches dont copy it over from file, just rewrite it with new score
                }                    
                else{
                    sFile.add(phscores[i] + " " + phscores[i+1]); //copy all the data from file, except for matching input names. 
                }         
            }
        }        
    } 
    catch (FileNotFoundException fe) 
    { 
        System.out.println("File not found"); 
    }
    
    //After reading from file, store the new/updated player names and scores on the sFile linked list
    sFile.add(players.getFirst()+ " " + this.highscore1);
    sFile.add(players.getLast()+ " " + this.highscore2);
    
    //Sorting the ordered collection linkedlist
    Collections.sort(sFile, new Comparator<String>() {
     @Override
     public int compare(String o1, String o2) { //compare string values and sort alphebtically 
         return Collator.getInstance().compare(o1, o2); //use recursion to sort the entire list 
     }
    });    
    
    //Write to file, take the list with all the player names and highscores and sort, then store the sorted list back onto the file 
    // attach a file to FileWriter  
    FileWriter fw = new FileWriter("PlayerInfo.txt");
    String newLine = System.getProperty("line.separator");
    for(int i = 0; i < sFile.size(); i++){ //loop through entire sorted linked list
        fw.write(sFile.get(i) + newLine);  //store onto the PlayerNames.txt file 
    }
    fw.close(); //close file after writing to it      
}

//Binary search of the file PlayerNames.txt 
public static String BinarySearch(String search) throws IOException{
    //Use try catch to open file and read line by line
    //copy the entire file onto an array, but split the string and only store player names
    try (BufferedReader br = new BufferedReader(new FileReader("PlayerInfo.txt"))) {
        String line;
        int ncount = 0;
        int scount = 0;
        int skip = 1; //use this variable to skip reading highscores, just grab player names 
        String[] fileNames = new String[10000];
        String[] fileScores = new String[10000];
        Queue<String> pNames = new LinkedList<>();
        Queue<String> pScores = new LinkedList<>();
        
        //Read the file line by line, each line contains the name of a player and their highscore
        while ((line = br.readLine()) != null) {    
            String[] array = line.split(" "); //us an array to split the strings of each line
            for (String a : array){ //loop through entire split array
                if(skip > 2){
                    skip = 1;
                    pScores.add(a);
                }           
                if(skip == 2){
                    fileScores[scount] = a;
                    scount++;                
                }
                if(skip == 1){ //only store the names, so skip every other index in the array
                    fileNames[ncount] = a; //ever 2 elements should contain a name, and its highscore
                    pNames.add(a);
                    //System.out.println(phscores[ncount]);
                    ncount++; //increment counter to set next element
                }
                skip++;
            }
        }
        
        //Starting binary search 
        int low = 0;
        int high = ncount - 1;
        int mid; 
        String nameScore;
        //keep cutting array in half until value is found
        while (low <= high) {
            mid = (low + high) / 2; //set the middle of the array
            
            //If the value is less than zero then increment the low 
            if (fileNames[mid].compareTo(search) < 0) {
                low = mid + 1;
            } 
            //if the value is greather than zero decrement the high value
            else if (fileNames[mid].compareTo(search) > 0) {
                high = mid - 1;
            } 
            else {
                nameScore = fileNames[mid] + ",  Highscore:" + fileScores[mid];
                //System.out.println(fileNames[mid] + " " + fileScores[mid]);
                return nameScore; //if its found return what index it is located at
            }
        }  
    } 
    catch (FileNotFoundException fe) 
    { 
        System.out.println("File not found"); 
    }
    return null; //if it isnt found display not found using -1
}
}
