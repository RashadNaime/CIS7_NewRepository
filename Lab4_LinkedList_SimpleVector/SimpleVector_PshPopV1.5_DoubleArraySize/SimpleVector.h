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
using namespace std;

template <class T>
class SimpleVector
{
private:
   T *aptr;          // To point to the allocated array
   int arraySize;    // Number of elements in the array
   int capacity;     //Used to help set the size of arraySize to double or 1/4x the original size 
   void memError();  // Handles memory allocation errors
   void subError();  // Handles subscripts out of range

public:
   // Default constructor
   SimpleVector()
      { aptr = 0; arraySize = 0;}
      
   // Constructor declaration
   SimpleVector(int);
   
   // Copy constructor declaration
   SimpleVector(const SimpleVector &);
   
   // Destructor declaration
   ~SimpleVector();
   
   // Accessor to return the array size
   int size() const
      { return arraySize; }

   // Accessor to return a specific element
   T getElementAt(int position);

   // Overloaded [] operator declaration
   T &operator[](const int &);
   
   //Create Push Front
   void pshFrnt(T);
   //Create Push Back
   void pshBck(T);
   //Create Pop Front
   T popFrnt();
   //Create Pop Back
   T popBck();
};

//***********************************************************
// Constructor for SimpleVector class. Sets the size of the *
// array and allocates memory for it.                       *
//***********************************************************

template <class T>
SimpleVector<T>::SimpleVector(int s)
{
   arraySize = s;
   capacity = arraySize;
   // Allocate memory for the array.
   try
   {
      aptr = new T [s];
   }
   catch (bad_alloc)
   {
      memError();
   }

   // Initialize the array.
   for (int count = 0; count < arraySize; count++)
      *(aptr + count) = 0;
}

//*******************************************
// Copy Constructor for SimpleVector class. *
//*******************************************

template <class T>
SimpleVector<T>::SimpleVector(const SimpleVector &obj)
{
   // Copy the array size.
   arraySize = obj.arraySize;
   
   // Allocate memory for the array.
   aptr = new T [arraySize];
   if (aptr == 0)
      memError();
      
   // Copy the elements of obj's array.
   for(int count = 0; count < arraySize; count++)
      *(aptr + count) = *(obj.aptr + count);
}

//**************************************
// Destructor for SimpleVector class.  *
//**************************************

template <class T>
SimpleVector<T>::~SimpleVector()
{
   if (arraySize > 0)
      delete [] aptr;
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
   if (sub < 0 || sub >= arraySize)
      subError();
   return aptr[sub];
}

//*******************************************************
// Overloaded [] operator. The argument is a subscript. *
// This function returns a reference to the element     *
// in the array indexed by the subscript.               *
//*******************************************************

template <class T>
T &SimpleVector<T>::operator[](const int &sub)
{
   if (sub < 0 || sub >= arraySize)
      subError();
   return aptr[sub];
}

template <class T>
void SimpleVector<T>::pshFrnt(T val){ // Pass in count to use in for loop to copy all the values from old array 
    //Allocate new array and create size x2 variable for new array
    if(arraySize == capacity){
        capacity = capacity*2; //set the size capacity to double the arraySize 
    }
    T* newAr = new T[capacity]; //set the new size to b x2
    //Copy the old array into the new array offset from the front by 1
    //cout<<"The Array Size"<< this->arraySize <<endl;
    for(int i = 0; i< this->arraySize;i++){   //Start at i = 1 and then place new 
        newAr[i+1] = this->aptr[i];
        //cout<<this->aptr[i]<<endl;
    }       
    newAr[0] = val; 
    this->arraySize = this->arraySize + 1;
    //Delete old array
    delete[] this->aptr;
    this->aptr = NULL;    
    //Set the old array pointer to new pointer
    this->aptr = newAr;    
}

template <class T>
void SimpleVector<T>::pshBck(T val){
    //Allocate new array and create size x2 variable for new array
    if(arraySize == capacity){
        capacity = capacity*2; //set the size capacity to double the arraySize
    }
    T* newAr = new T[capacity]; //set the new size to b x2
    
    //Copy the old array into the new array offset from the front by 1
    //cout<<"The back size"<< this->arraySize <<endl;
    for(int i = 0; i< this->arraySize;i++){   //Start at i = 1 and then place new 
        newAr[i] = this->aptr[i];
        //cout<<newAr[i]<<endl;
    }  
    newAr[this->arraySize] = val;
    this->arraySize = this->arraySize + 1; 
    //Delete old array
    delete[] this->aptr;
    this->aptr = NULL;    
    //Set the old array pointer to new pointer
    this->aptr = newAr;    
}

template <class T>
T SimpleVector<T>::popFrnt(){
    //Allocate new Array by subtracting 1 from its size and decrement size property by 1
    int newSize = this->arraySize-1;
    T* newAr = new T[newSize];
    //Store  the value at the front of array to later return
    int frntVal;
    frntVal = this->aptr[0];
    // Decrement the size property by 1
    this->arraySize = newSize;  //This will prevent trailing zeros
    //Copy the old array into the new array offset from the front by 1
    for(int i = 0; i< newSize;i++){   //Start at i = 1 and then place new 
        newAr[i] = this->aptr[i+1];
    }     
    //delete old array
    delete[] this->aptr;
    this->aptr = NULL;        
    //Set old array pointer to new array pointer
    this->aptr = newAr;
    //Return the front value
    return frntVal;
}

template <class T>
T SimpleVector<T>::popBck(){
    //Allocate new Array by subtracting 1 from its size and decrement size property by 1
    int newSize = this->arraySize-1; //everytime popBck is called pop B is incremented to correctly size each new array
    T* newAr = new T[newSize];
    //Store  the value at the back of array to later return
    int bckVal;
    bckVal = this->aptr[this->arraySize];
    // Decrement the size property by 1
    this->arraySize = newSize;  //This will prevent trailing zeros
    //Copy the old array into the new array offset from the front by 1
    for(int i = 0; i< newSize;i++){   //Start at i = 1 and then place new 
        newAr[i] = this->aptr[i];
    }     
    //delete old array
    delete[] this->aptr;
    this->aptr = NULL;        
    //Set old array pointer to new array pointer
    this->aptr = newAr;
    //Return the front value
    return bckVal;
}
#endif