/*
* File:   SimpleVector.h
 * Author: Rashad Naime
 *
 * Created on September 16, 2020, 8:10 PM
 */

// SimpleVector class template
#ifndef SIMPLEVECTOR_H
#define SIMPLEVECTOR_H
#include <iostream>
#include <new>       // Needed for bad_alloc exception
#include <cstdlib>   // Needed for the exit function
#include <ctime>
using namespace std;

template <class T>
class SimpleVector
{
private:
    //Lab 4, use linked list instead of arrays 
    struct Link{
        T data;         //Mainly for Objects
        Link *linkPtr;  //Self Reference which when utilized forms a linked list
    }; 
    
    Link *front,*next,*temp,*end,*found, *cpy;//Permanent and Temp pointers
   T *aptr;          // To point to the allocated array
   int arraySize;    // Number of elements in the array
   void memError();  // Handles memory allocation errors
   void subError();  // Handles subscripts out of range

public:
   // Default constructor
   SimpleVector(){
    aptr = 0; arraySize = 0;
    
    //For lab 4, default construction of linked list
    front=new Link;      //Allocate a link at the front of the list
    front->data=10;    //Initialize with data
    front->linkPtr=NULL; //At the moment not pointing it to anything
   }
      
   // Constructor declaration
   SimpleVector(int); //Modified to use linked list, uses size to create the amount of nodes (fills with 0's)
   
   // Copy constructor declaration
   SimpleVector(const SimpleVector &); //copy constructor modified to copy linked lists 
   
   // Destructor declaration
   ~SimpleVector(); //Modified to destroy linked lists 
   
   // Accessor to return the array size
   int size() const
      { return arraySize; }

   // Accessor to return a specific element
   T getElementAt(int position); //returns element at given node position 

   // Overloaded [] operator declaration
   T &operator[](const int &);
   
   //All of these functions have been modified to use linked lists 
   //Create Push Front
   void pshFrnt(T);
   //Create Push Back
   void pshBck(T);
   //Create Pop Front
   T popFrnt();
   //Create Pop Back
   T popBck();
   
   //Create a print function for Linked List Lab 4
   void prntVect();  //print the vector using linked lists
   void fllVect(int s); //fill the vector using linked lists 
};

//***********************************************************
// Constructor for SimpleVector class. Sets the size of the *
// array and allocates memory for it.                       *
//***********************************************************

template <class T>
SimpleVector<T>::SimpleVector(int s)
{
    //Construct the Linked list, use size to create that many nodes 
    front=new Link;
    next = front;
    
    //Fill the rest of the list with decreasing data down to 1
    s--;
    front->data = 0;
    do{
       temp=new Link; //Allocate a new link
       temp->data = 0;        //Fill with 0's , use fillVec function to enter in values 
       temp->linkPtr=NULL;  //Place at the end
       next->linkPtr=temp;  //Hook the new link to the end of the list
       next=temp;           //Move the pointer to the end
    }while(--s>0);          //Continue till you count down to Zero
    
}

//*******************************************
// Copy Constructor for SimpleVector class. *
//*******************************************

template <class T>
SimpleVector<T>::SimpleVector(const SimpleVector &obj){
    //If the vector being copy has a null head, set new cpy linked list = to null
    if (obj.front == NULL) {
        front = NULL;
    }
    else { //else create new linked list and cpy the linked list
        front = new Link;               //set front to new Linked list 
        front->data = obj.front->data;  //set new front data of copy linked list equal to original
        next = front;                   //set next equal to front to traverse the linked list 
        Link *objFront = obj.front;     //create new temporary front of list to copy over  
        Link *nextObj = objFront;       //create new temporary next pointer for traversal 
        while (nextObj->linkPtr != NULL) { //Use temporary linked list pointer to traverse until list is copied over 
            next->linkPtr = new Link;   //set next equal to new linked list 
            next->linkPtr->data = nextObj->linkPtr->data; //copy the data of the next node
            nextObj = nextObj->linkPtr; //Have temp next pointer traverse 
            next = next->linkPtr;       //set cpy next pointer to traverse 
        }
    }    
}

//**************************************
// Destructor for SimpleVector class.  *
//**************************************

template <class T>
SimpleVector<T>::~SimpleVector()
{
    if(front != NULL ){
        do{
           temp=front->linkPtr;   //Point to the next link in the list
           delete front;                //Delete the front of the list
           front=temp;                  //Swap the front
        }while(front!=NULL);
    }
}

//*******************************************************
// memError function. Displays an error message and     *
// terminates the program when memory allocation fails. *
//*******************************************************

template <class T>
void SimpleVector<T>::memError()
{
   cout << "ERROR:Cannot allocate memory.\n";
   exit(EXIT_FAILURE);
}

//***********************************************************
// subError function. Displays an error message and         *
// terminates the program when a subscript is out of range. *
//***********************************************************

template <class T>
void SimpleVector<T>::subError()
{
   cout << "ERROR: Subscript out of range.\n";
   exit(EXIT_FAILURE);
}

//*******************************************************
// getElementAt function. The argument is a subscript.  *
// This function returns the value stored at the sub-   *
// cript in the array.                                  *
//*******************************************************

template <class T>
T SimpleVector<T>::getElementAt(int sub)
{
    int n=0;                //Initialize the counter
    temp=front;             //Prepare to traverse the Link<Object>ed list
    do{
        n++;                //Increment the counter
        if(temp->data==sub)return temp->data;//Return where the data in that spot 
        temp=temp->linkPtr; //Move to the next link
    }while(temp!=NULL);     //End when reaching the end of the linked list
    return -1;               //Not found then return a 0;
}

//*******************************************************
// Overloaded [] operator. The argument is a subscript. *
// This function returns a reference to the element     *
// in the array indexed by the subscript.               *
//*******************************************************

template <class T>
T &SimpleVector<T>::operator[](const int &sub)
{
//    // Start at the head of the list
//    next = front;
//
//    // Loop as long as we don't go of the end of the list and `n` is larger than zero
//    // Also decrement `n` after checking its value
//    while (next != nullptr && sub-- > 0)
//    {
//        // Make `current` point to the next node in the list
//        next = next->linkPtr;
//    }
//
//    // If `current` is a null pointer, then we have gone of the end of the list, return some default value
//    // Otherwise return the value of node we were looking for
//    return (next == nullptr ? 0 : next->data);
}

//All functions are modified to take in linked lists instead of arrays. 
template <class T>
void SimpleVector<T>::pshFrnt(T val){
    temp = front; //set temp equal to the front of linked list 
    Link *add = new Link; //create new link too add in to the front 
    add->data = val; //add the data in to the new link 
    front = add;  //set front equal to new added link
    front->linkPtr = temp; //set the pointer of the new front list to the old one  
}

template <class T>
void SimpleVector<T>::pshBck(T val){
    //Use this to find the last node to remove 
    temp=front; //Declare pointers used to step through the list
    do{
        end=temp;          //Point to the current link with a swap
        temp=temp->linkPtr; //Point to the next link
    }while(temp!=NULL);     //Your done when you hit the end
    
    //add new value to the new node, and set end pointer to new node
    Link *add=new Link;        //Create the new link
    add->data=val;            //Add the data
    add->linkPtr=NULL;         //Set the pointer to NULL
    end->linkPtr=add;         //Point to the new end of the list    
}

template <class T>
T SimpleVector<T>::popFrnt(){
    if(front != NULL){ //make sure the list is not null 
    temp = front; //set temp equal to the front of the list 
    front = front->linkPtr; //set front equal to the next node of the list 
    delete temp;  //delete the first node and values 
    }
    else{ //if the list is empty display 
        cout<<"Nothing left to pop"<<endl;
    }
}

template <class T>
T SimpleVector<T>::popBck(){
    if(front != NULL ){
        temp=front; //Declare pointers used to step through the list
        while(temp!= NULL){ //Find the end of the list 
            end=temp;          //Point to the current link with a swap
            temp=temp->linkPtr; //Point to the next link
        }
        if(temp == NULL ){ //Once temp has looped through linked list 
            temp = end; //set it equal to the last list 
        }
        delete temp;  //delete temp of the last list 
        temp = front; //set temp equal to the front again to traverse through linked list 1 more time 
        while(temp->linkPtr != end){ //while temp->linkPtr is not equal to the deleted list
            temp = temp->linkPtr;   //traverse to the list right before the deletion
        }
        end = temp; //Set the end pointer to the previous list before it to be the new end 
        end->linkPtr = NULL ; //set the end pointer to NULL
    }
}

//This function prints out the vector 
template <class T>
void SimpleVector<T>::prntVect(){
    next=front;           //Create a pointer to the list
    cout<<endl<<"The Beginning of the List"<<endl;
    int count = 1; //use count for display 
    do{
        cout<<next->data<<" "; //Print the contents of the link
        next=next->linkPtr;     //Go to the next link in the list
        count++;
        if(count == 10){ // every 10 variables will start a new line 
            cout<<endl;
            count = 1;
        }
    }while(next!=NULL);         //Loop until reaching the end
    cout<<endl<<"The End of the List"<<endl<<endl;
}

//This function will fill the linked list nodes with random numbers 
template <class T>
void SimpleVector<T>::fllVect(int s){
    //Construct the Linked list, use size to create that many nodes 
    front=new Link;
    next = front;    
    //Fill the rest of the list with decreasing data down to 1
    s--;
    front->data = rand()%26+65;
    //cout<<"Front"<<front->data<<endl;
    do{
       temp=new Link; //Allocate a new link
       temp->data=rand()%26+65;        //Fill with data
       temp->linkPtr=NULL;  //Place at the end
       next->linkPtr=temp;  //Hook the new link to the end of the list
       //cout<<temp->data<<endl;
       next=temp;           //Move the pointer to the end
    }while(--s>0);          //Continue till you count down to Zero
}
#endif