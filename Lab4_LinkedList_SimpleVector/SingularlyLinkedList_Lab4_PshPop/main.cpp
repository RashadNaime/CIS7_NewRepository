/* 
 * File:   main.cpp
 * Edited: Rashad Naime 
 * Created on October 10th, 2020 1:00 PM
 * Purpose:  Lab 4 Linked List, create a linked list class with psh/pop and dstryList functions
 */

//System Libraries Here
#include <iostream>
using namespace std;

//User Libraries Here
#include "Object.h"
#include "LinkedList.h"

//Global Constants Only, No Global Variables

//Function Prototypes Here

//Program Execution Begins Here
int main(int argc, char** argv) {
    //Declare an object and place in the linked list
    Object obj(5);
    LinkedList<Object> *lnkList;
    lnkList=new LinkedList<Object>(obj);
    lnkList->prntLst(); // Print before Pushing and Popping 
    //Create new objects to insert using push functions 
    Object val1(2);
    Object val2(3);
    Object val3(4);
    Object val4(5);
    
    //Push Front 
    lnkList->pshFrnt(val1);
    lnkList->pshFrnt(val2);
    
    //Push Back 
    lnkList->pshBck(val3);
    lnkList->pshBck(val4);
    
    //Print After pushing front and back 
    cout<<"Linked list after pushing front and back: "<<endl;
    lnkList->prntLst();
    
    //Pop Front 
    lnkList->popFrnt();
    lnkList->popFrnt();
    
    //Pop Back 
    lnkList->popBck();
    lnkList->popBck();
    
    //Print the entire list
    cout<<"Linked list after popping front and back: "<<endl;
    lnkList->prntLst();
    
    
    //Deallocate memory
    cout<<"Linked list is being destroyed:"<<endl;
    lnkList->dstryLst(); //Destroy the entire linked list 

    lnkList->prntLst(); //Print out to show that linked list was deleted 

    //Exit
    return 0;
}