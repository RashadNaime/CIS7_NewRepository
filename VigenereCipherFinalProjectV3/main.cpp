#include <iostream>
#include <algorithm>
#include <array>
#include <string> 
#include <cstdlib> 
#include <ctime> 
#include <string>
using namespace std; 

class Encryption
{
private: 
  int row = 27; //Used in 2d array to create the 27 x 27 vigenere cipher table
  int col = 27; //Used in 2d array to create the 27 x 27 vigenere cipher table
  char** arr = new char*[row]; //2d array used to create and store table for encryption
  char innerTable; //innertable used to create the inner sequence of the vigenere table
  char topRow;     //toprow will store the top characters for the table 
  char leftCol;    //leftCol will store the characters to the left of the table 

public:

    // Constructor for Encryption class 
    // Initialized class variables and creates the vigenere cipher table for encryption/decryption 
    Encryption()
    {
      //Set all the defaults for the variables. 
      //Starting the char variables at a to be incremented througout the vigenere cipher table
      this->innerTable = 'a'; 
      this->topRow = 'a';
      this->leftCol = 'a';

      //initialize and create the new 2d array pointer to store entire vigenere cipher table
      for(int i = 0; i < this->row; i++){
        this->arr[i] = new char[this->col]; //set the columns 
      }
      //fill in the 2d array with the vigenere cipher table letters
      //Use if statements to determine what is entered in each row and column
      //The top Row is used for the plaintext 
      //The left most Column is used for the key 
      //The inner part of the table is where the decrypted letters are retrieved 
      for(int i = 0; i < this->row; i++){
        for(int j = 0; j < this->col; j++){
          //The very first element of the array should be blank
          if(i == 0 && j == 0){
            this->arr[i][j] = ' ';

          } else if(j == 0 && i >= 1){ //Insertion for the left most column starting at row 1 col 0
            this->leftCol = OuterVigTable(this->leftCol, i, j); //use table functions to set characters
            this->arr[i][j] = this->leftCol;                    //Store into 2d array 

          } else if(i == 0 && j >= 1){ //Insertion for top most row starting at row 0 col 1
            this->topRow = OuterVigTable(this->topRow, i, j);
            this->arr[i][j] = this->topRow;

          }else{ //Insertion for the sequence of the inner table 
            this->innerTable = InnerVigTable(this->innerTable, i, j);
            this->arr[i][j] = this->innerTable;
          }
        }
      }
    }

    // Class Function Displays the vigenere cipher Table
    void DisplayVigTable()
    {
      //Create for loop of row and column to display entire 2d array with vigenere table stored 
      for(int i = 0; i < this->row; i++){
        for(int j = 0; j < this->col; j++){
          cout<<this->arr[i][j]<<" ";
        }
        cout<<endl;
      }
    }

    //This class function is used to create the inner 26x26 part of the vigenere cipher table
    //passes in character, i and j from the for loop in the constructor as rowCpy and colCpy
    char InnerVigTable(char c, int rowCpy, int colCpy){ 
      if(isalpha(c)){ //Make sure entered characters for table are alphabetical 
        c = toupper(c); //Uppercase all letters being passed in 

        //Start of the sequence begins with A, at row 1 column 1
        if(rowCpy == 1 && colCpy == 1){
          c = (((c-65))%26) + 65; 
        
        //Else every new line must shift by 2 to follow sequence of vigenere cipher table
        } else if(rowCpy > 1 && colCpy == 1){
          c = (((c-65)+2)%26) + 65;

        } else{ // Increment by 1 to get the next subsequent letter within the row 
          c = (((c-65)+1)%26) + 65;
        }
      }
      return c; //return the character to store into 2d array 
    }

    //This class function is used to create the top outer row and left most column part of the vigenere cipher table
    //passes in character, i and j from the for loop in the constructor as rowCpy and colCpy
    char OuterVigTable(char c, int rowCpy, int colCpy){

      if(isalpha(c)){
        c = toupper(c); //uppercase the character 
        
        //Make sure to start the beginning of the outmost row and column with "A"
        if(colCpy == 0 && rowCpy == 1){
          c = (((c-65))%26) + 65;
        }else if(rowCpy == 0 && colCpy == 1){ 
          c = (((c-65))%26) + 65;
        }
        else{ //Increment after the first position is started with A 
          c = (((c-65)+1)%26) + 65;
        }
      }
      return c; //return the character back to the array 
    }

    //Class function will encrypt the passed in plaintext using the passed in key 
    string NewEncrypt(string plaintext, string key){
      //Do not include spaces to the encryption result
      plaintext = trimString(plaintext); //call function to trim every space from the text 
      key = trimString(key);             //call function to trim every space from the key
      // cout<<plaintext<<plaintext.length()<<endl;
      // cout<<key<<endl;

      int txtSize = plaintext.length(); //Get the length of the plaintext 
      int keyLength = key.length() - 1; //offset the length by one to start index at 0 in array
      int keyCount = 0;                 //Use keyCount to repeat key in circular manner 
      string output = ""; //concatanate all the ciphertext from the encryption and return string 
      char eFormula; //Formula Ei = (Pi + Ki) mod 26 used to find encryption 
      char textCopy[txtSize + 1]; //create character array to copy string plaintext 
      char keyArr[txtSize + 1]; //copy key into this array, but make it the same length as the txtArray
      
      //copy the plaintext and key into character arrays, to compare with vigenere cipher table to encrypt
      for(int i = 0; i < txtSize; i++){
        if(isalpha(plaintext[i])){//If character is alphanumeric then encrypt 
          //store the string plaintext into character array to compare values with vigenere table. 
          textCopy[i] = toupper(plaintext[i]);

          //If the key is smaller than the plaintext entered, repeat the key again within new key array
          //To match the length of the plaintext using keyCount iterator 
          if(keyCount >= keyLength){ 
            keyArr[i] = toupper(key[keyCount]);//store the uppercase value of the key element 
            keyCount = 0; //reset counter to start from first term once string is fully copied 
          }
          else{
            keyArr[i] = toupper(key[keyCount]); 
            keyCount++;//increment seperate counter for key 
          }
          //Use the Ei = (Pi + Ki) mod 26 ,the plaintext(P) and key(K) are added modulo 26.
          eFormula = ((textCopy[i] + keyArr[i]) % 26) + 65; 
          output = output + eFormula;      //concatanate string with new encryption
        }
        else if (!isalpha(plaintext[i])){ //If the character is not alphanumeric then do NOT encrypt
          output = output + plaintext[i]; //just copy the number/symbol on, do not encrypt 
          keyArr[i] = toupper(key[keyCount]); //The key must still be incremented and stored 
          keyCount++; //still increment the counter if there is a number or symbol

          //If the key reaches its max length, reset counter to start at beginning of key string
          if(keyCount > keyLength){ 
            keyCount = 0; 
          }
        } 
        //cout<<i<<keyArr[i]<<" ";           
      }
      return output; //return concatanated ciphertext 
    }

    //Class function will decrypt the passed in ciphertext using the past in key 
    string NewDecrypt(string ciphertext, string key){
      //Do not include spaces to the encryption result
      ciphertext = trimString(ciphertext);
      key = trimString(key);

      int txtSize = ciphertext.length();//get length of ciphertext
      int keyLength = key.length() - 1; //offset the length by one to start index at 0 in array
      int keyCount = 0;                 //counter used to repeat key in circular manner
      string output = ""; //concatanate all the plaintext from the decryption and return string 
      char dFormula;      //Di = (Ei - Ki + 26) mod 26
      char textCopy[txtSize + 1];//create character array to copy string plaintext 
      char keyArr[txtSize + 1]; //copy key into this array, but make it the same length as the txtArray
      
      //copy the ciphertext and key into character arrays
      //to compare with vigenier cipher table to decrpyt
      for(int i = 0; i < txtSize; i++){
        if(isalpha(ciphertext[i])){
          //store the string ciphertext into character array to compare values with vigenier table. 
          textCopy[i] = toupper(ciphertext[i]);

          //If the key is smaller than the plaintext entered, repeat the key again within new key array
          //To match the length of the plaintext using keyCount iterator
          if(keyCount >= keyLength){ 
            keyArr[i] = toupper(key[keyCount]);
            keyCount = 0; //reset counter to start from first term once string is fully copied 
          }
          else{
            keyArr[i] = toupper(key[keyCount]);
            keyCount++;
          }
          //Use the Di = (Ei - Ki + 26) mod 26 , the cipher text - the key + 26, mod 26 
          dFormula = ((textCopy[i] - keyArr[i] + 26) % 26) + 65; 
          output = output + dFormula; //concatanate output with new decrypted variable
        }
        else if (!isalpha(ciphertext[i])){ //If the character is not alphanumeric then do NOT decrypt
          output = output + ciphertext[i]; //concatanate with the non alphanumeric character
          keyArr[i] = toupper(key[keyCount]);
          keyCount++; 
          if(keyCount > keyLength){ 
            keyCount = 0;
          }
        }        
      }
      return output;  //return concatanated decryption
    }

    //Class function used to remove all spaces from inputted plaintext and key 
    string trimString(string text){
      //Use this process for removing all spaces from the given string. 
      text.erase(remove(text.begin(), text.end(), ' '), text.end());
      return text; //return string with no spaces 
    }

    bool properKey(string key){
      bool goodKey = true;  

      for(int i = 0; i < key.length(); i++){
        if(!isalpha(key[i])){
            goodKey = false;
        }
      }
      if(goodKey == false){
        cout<<"They key entered has improper characters, please try again!"<<endl;
      }
      return goodKey;
    }

    // Destructor
    ~Encryption() {
      //Make sure to delete all the elements from the 2d array along with the array itself 
      for(int i = 0; i < this->row; i++){
        delete [] this->arr[i];
      }
      delete [] this->arr; 
    }
};

int main() {

  string plaintext;// = "  g e777 eks fo  4r ge 8 $ e % k  s "; //Plaintext that is entered for encryption
  string key;// = "ayush"; //key that is used for both encryption and decryption
  string ciphertext;    //String that holds the resulting ciphertext from encryption
  string newPtext;      //String that holds the resulting plaintext from decryption
  Encryption test = Encryption(); //Create a class variable to use and call class functions 

  bool menu = true; //Use boolean flag in while loop to dipslay menu until user is finished
  bool goodkey; //boolean flag used in switch case to get proper key value 
  int select = 1; 
  while(menu){
    //Create menu in while loop for user to select options 
    cout<<"Please select from the following options:"<<endl; 
    cout<<"1) Encrypt a new plaintext."<<endl; 
    cout<<"2) Decrypt a ciphertext."<<endl; 
    cout<<"3) End Program and exit."<<endl; 
    cin >> select;

    //Switch statement used for selecting options of encryption, decryption, or ending program. 
    switch(select){
      case 1:
        //Plaintext entered can have any character or symbol
        cout<<"Please enter a text to Encrypt (*may use any characters/symbols, will only encrypt alphabetical characters)"<<endl; 
        cin.ignore();//call cin.ignore() to take in input within switch case 
        getline(cin, plaintext);

        //key entered may only have alphabetical characters 
        goodkey = false; //set flag to false everytime, to ensure the key is inputted properly 
        while(goodkey == false){ //Will loop until a proper key is entered with only letters
        cout<<"Please enter a key to Encrypt the text entered (*only enter alphabetical characters)"<<endl;
        getline(cin, key); 
        key = test.trimString(key); //take all the spaces out of the key
        goodkey = test.properKey(key);//Call function properKey to check if key is all alpha characters
        }
          test.DisplayVigTable();  
          cout<<"\nInput: Plaintext: "<<plaintext<<endl; 
          cout<<"Input: KeyWord:   "<<key<<endl;    
          ciphertext = test.NewEncrypt(plaintext, key);
          cout<<"Output: CipherText: "<<ciphertext<<endl<<endl;          
        break; 
      case 2: 
        //Ciphertext entered can have any character or symbol
        cout<<"Please enter a text to Decrypt (*may use any characters/symbols, will only decrypt alphabetical characters)"<<endl; 
        cin.ignore();//call cin.ignore() to take in input within switch case 
        getline(cin, ciphertext);

        //key entered may only have alphabetical characters 
        goodkey = false; //set flag to false everytime, to ensure the key is inputted properly 
        while(goodkey == false){ //Will loop until a proper key is entered with only letters
        cout<<"Please enter a key to Decrypt the text entered (*only enter alphabetical characters)"<<endl;
        getline(cin, key); 
        key = test.trimString(key); //take all the spaces out of the key
        goodkey = test.properKey(key);//Call function properKey to check if key is all alpha characters
        }


          test.DisplayVigTable(); 
          newPtext = test.NewDecrypt(ciphertext, key);
          cout<<"\nInput: Ciphertext: "<<ciphertext<<endl;
          cout<<"Input: KeyWord:   "<<key<<endl; 
          cout<<"Output: Plaintext: "<<newPtext<<endl<<endl; 
        break; 
      case 3: 
        menu = false;
        break;
      default: 
        cout<<"Please enter a proper Option"<<endl; 
      break; 
    }
  }
  return 0; 
}
// EEUBTESMPKIQL
// eeubtesmpkiql


