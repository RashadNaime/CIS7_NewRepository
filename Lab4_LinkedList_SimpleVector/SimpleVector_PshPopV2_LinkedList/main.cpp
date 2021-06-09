/* 
 * File:   main.cpp
 * Author: Rashad Naime
 * Created on September 16, 2020, 7:50 PM
 * Purpose:  To Test and Modify the Simple Vector with Linked List
 */

//System Libraries
#include <iostream>
using namespace std;

//User Libraries
#include "SimpleVector.h"

//Global Constants

//Function prototypes
void fillVec(SimpleVector<unsigned int> &);
void prntVec(SimpleVector<unsigned int> &,int);

//Execution Begins Here
int main(int argc, char** argv) {
    //Declare Variables
    unsigned int size=200;
    unsigned int val = 10;
    //Construct new simple vector using Linked List 
    SimpleVector<unsigned int> sv(size); //Create simplevector with constructor, now uses linked list (Is only filled with 0's)  
    sv.fllVect(size);   //Fill the vector with random numbers by inserting into the linked list  (this function is located in SimpleVector.h)
    sv.prntVect(); //Print Vector using linked list (this function is located in SimpleVector.h)
    
    cout<<"Simple vector Linked List push Front and Back:"<<endl;
    //Push Front and Push Back functions using linked list
    sv.pshFrnt(val);
    sv.pshFrnt(val);
    sv.pshFrnt(val);
    sv.pshFrnt(val);

    sv.pshBck(val);
    sv.pshBck(val);
    sv.pshBck(val);
    
    sv.prntVect(); //Print Vector using linked list
    
    cout<<"Simple vector push COPY:"<<endl;
    SimpleVector<unsigned int> cpysv(sv);
    cpysv.prntVect();    
    
    cout<<"Simple vector Linked List pop Front and Back:"<<endl;
    //Pop front and back of simple vector using linked list
    sv.popFrnt();
    sv.popFrnt();
    sv.popFrnt();
    sv.popFrnt();    
    
    sv.popBck();
    sv.popBck();
    sv.popBck();
    
    sv.prntVect(); //Print again after popping front and back
    
    //copy the simplevector with linked list 
    cout<<"Simple vector pop cpy:"<<endl;
    SimpleVector<unsigned int> cpysv2(sv);
    cpysv2.prntVect();

    return 0;
}

//These functions are not used for the linked list version 
void prntVec(SimpleVector<unsigned int> &sv,int perLine){
    cout<<endl;
    for(int i=0;i<sv.size();i++){
        cout<<sv[i]<<" ";
        if(i%perLine==(perLine-1))cout<<endl;
    }
    cout<<endl;
}

void fillVec(SimpleVector<unsigned int> &sv){
    for(int i=0;i<sv.size();i++){
        sv[i]=rand()%26+65;
    }
}

