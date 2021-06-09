/* 
 * File:   main.cpp
 * Author: Rashad Naime
 * Created on September 16, 2020, 7:50 PM
 * Purpose:  To Test and Modify the Simple Vector with pshFrnt/Bck popFrnt/Bck
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
    int s = size;
    unsigned int val = 10;
    SimpleVector<unsigned int> sv(size);
    
    //Fill the Vector
    fillVec(sv);
    //Push front of vector
    sv.pshFrnt(val);
    sv.pshFrnt(val);
    sv.pshFrnt(val);
    //push back of vector
    sv.pshBck(val);
    sv.pshBck(val);
    sv.pshBck(val);
    
    //Print the push front and back 
    cout<<"Simple Vector after Pushing front and back"<<endl;
    prntVec(sv,10);
    
    sv.popFrnt();
    sv.popFrnt();
    sv.popFrnt();
    sv.popBck();
    sv.popBck();
    sv.popBck();
    
    //Print the Vector
    cout<<"Simple Vector after Popping front and back"<<endl;
    prntVec(sv,10);

    //Copy the Vector
    SimpleVector<unsigned int> copysv(sv);
    
    //Print the Vector
    cout<<"Simple Vector copy"<<endl;
    prntVec(copysv,10);

    return 0;
}

void prntVec(SimpleVector<unsigned int> &sv,int perLine){
    cout<<endl;
    for(int i=0;i<sv.size() ;i++){
        if(sv[i] != 0){
            cout<<sv[i]<<" ";
            if(i%perLine==(perLine-1))cout<<endl;
        }
    }
    cout<<endl;
}

void fillVec(SimpleVector<unsigned int> &sv){
    for(int i=0;i<sv.size();i++){
        sv[i]=rand()%26+65;
    }
}

