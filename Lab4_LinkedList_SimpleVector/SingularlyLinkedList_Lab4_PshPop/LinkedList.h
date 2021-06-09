/* 
 * File:   LinkedList.h
 * Author: Dr. Mark E. Lehr
 * Created on March 22nd, 2020, 3:22 PM
 *      Purpose:  Implementation of the previous linked list functions
 */

#ifndef LINKEDLIST_H
#define	LINKEDLIST_H

template<class T>
class LinkedList{
    private:
        struct Link{
            T data;         //Mainly for Objects
            Link *linkPtr;  //Self Reference which when utilized forms a linked list
        };
        Link *front,*next,*temp,*end,*found;//Permanent and Temp pointers
        void endLst();          //Find the end of the list
        void fndLst(const T&);  //Determine address of link that contains the data
    public:
        LinkedList(const T&);
        ~LinkedList();
        void prntLst();          //Print each data element in the list
        void  addLst(const T&);  //Add data at the end of the list
        int  findLst(const T&);  //Determine what link contains the data
        int   cntLst();          //How many elements are in the list
        T     getObj(int);       //Return the object
        
    //Lab 4: Create functions pshFrnt() , pshBck(), popFrnt(), popBck() , dstryLst()    
        void pshFrnt(const T&);
        void pshBck(const T&);
        void popFrnt();
        void popBck();
        void dstryLst();
};

#endif	/* LINKEDLIST_H */


//Function Create a linked list and fill with data
//Input  -> n      The number of elements in the list to create
//Output -> front  The address to the front of the allocated list.
template<class T>
LinkedList<T>::LinkedList(const T &data){
    //Think of this part as the constructor
    front=new Link;      //Allocate a link at the front of the list
    front->data=data;    //Initialize with data
    front->linkPtr=NULL; //At the moment not pointing it to anything
}

//Function Print the entire contents of the linked list
//Input -> front  The address to the front of the allocated list.
//Output-> Display the entire linked list.
template<class T>
void LinkedList<T>::prntLst(){
    if(front != NULL ){
        next=front;           //Create a pointer to the list
        cout<<endl<<"The Beginning of the List"<<endl;
        do{
            cout<<next->data<<endl; //Print the contents of the link
            next=next->linkPtr;     //Go to the next link in the list
        }while(next!=NULL);         //Loop until reaching the end
        cout<<"The End of the List"<<endl<<endl;
    }
    else{
        cout<<"The List is Empty!"<<endl;
    }
}

//Function Deallocate the entire list from the front to the end.
//Input -> front  The address to the front of the allocated list.
//Output-> Deallocate and return a NULL for the linked list pointer.
template<class T>
LinkedList<T>::~LinkedList(){
    do{
       temp=front->linkPtr;   //Point to the next link in the list
       delete front;          //Delete the front of the list
       front=temp;            //Swap the front
    }while(front!=NULL);
}

//Function Find the address of the last link in the list
//Input -> front  The address to the front of the allocated list.
//Output-> The address of the last link in the list
template<class T>
void LinkedList<T>::endLst(){
    temp=front; //Declare pointers used to step through the list
    do{
        end=temp;          //Point to the current link with a swap
        temp=temp->linkPtr; //Point to the next link
    }while(temp!=NULL);     //Your done when you hit the end
}

//Function Add a link and data to the end of the list
//Input -> front  The address to the front of the allocated list.
//         data   Data to embed at the last link in the list
template<class T>
void LinkedList<T>::addLst(const T &data){
    endLst();  //Find the last link
    Link *add=new Link;        //Create the new link
    add->data=data;            //Add the data
    add->linkPtr=NULL;         //Set the pointer to NULL
    end->linkPtr=add;         //Point to the new end of the list
}

//Function Find the number of the link that matches the data
//Input -> front  The address to the front of the allocated list.
//Output-> The Link<Object> number from the front where the data is found
template<class T>
int LinkedList<T>::findLst(const T &value){
    int n=0;                //Initialize the counter
    temp=front;             //Prepare to traverse the Link<Object>ed list
    do{
        n++;                //Increment the counter
        if(temp->data==value)return n;//Return where the data is found
        temp=temp->linkPtr; //Move to the next link
    }while(temp!=NULL);     //End when reaching the end of the linked list
    return -1;               //Not found then return a 0;
}

//Function Find the address of the link that matches the data
//Input -> front  The address to the front of the allocated list.
//Output-> The Link<Object> address from the front where the data is found
template<class T>
void LinkedList<T>::fndLst(const T &value){
    found=front;       //Set the cursor to move through the list
    do{
        if(found->data==value)return found;  //Address of data match
        found=found->linkPtr;                //Next link in the list
    }while(found!=NULL);                     //End of the list
    found=NULL;                              //Not found
}

//Function Find the number of links in the list
//Input -> front  The address to the front of the allocated list.
//Output-> The number of links in the list
template<class T>
int LinkedList<T>::cntLst(){
    int n=0;            //Initialize the counter
    temp=front;   //Set the cursor to the front
    do{
        n++;            //Increment the counter
        temp=temp->linkPtr;//Move to the next link
    }while(temp!=NULL); //Loop until the end is reached
    return n;           //Return the counter value
}

//Function Find the number of links in the list
//Input -> front  The address to the front of the allocated list.
//Output-> The number of links in the list
template<class T>
T LinkedList<T>::getObj(int indx){
    int n=0;                      //Initialize the counter
    temp=front;                   //Set the cursor to the front
    do{
        if(n==indx)return temp->data;
        n++;                      //Increment the counter
        temp=temp->linkPtr;       //Move to the next link
    }while(temp!=NULL);           //Loop until the end is reached
    return front->data;           //Return the counter value
}

//These are all the functions for Lab 4: pshfrnt/Bck , Popfront/Bck , dstryList

//Function push front, Push new node with object or value to the front of the list
template<class T>
void LinkedList<T>::pshFrnt(const T& data){
    temp = front; //set temp equal to the front of linked list 
    Link *add = new Link; //create new link too add in to the front 
    add->data = data; //add the data in to the new link 
    front = add;  //set front equal to new added link
    front->linkPtr = temp; //set the pointer of the new front list to the old one 
    
}

//Function push back, Push new node with object or value to the back of the list 
template<class T> 
void LinkedList<T>::pshBck(const T& data){
    endLst();  //Find the last link
    Link *add=new Link;        //Create the new link
    add->data=data;            //Add the data
    add->linkPtr=NULL;         //Set the pointer to NULL
    end->linkPtr=add;         //Point to the new end of the list
}

//Function pop front, remove node from the front of the list 
template<class T> 
void LinkedList<T>::popFrnt(){
    if(front != NULL){ //make sure the list is not null 
    temp = front; //set temp equal to the front of the list 
    front = front->linkPtr; //set front equal to the next node of the list 
    delete temp;  //delete the first node and values 
    }
    else{ //if the list is empty display 
        cout<<"Nothing left to pop"<<endl;
    }
}

//Function pop back, remove node from the back of the list 
template<class T> 
void LinkedList<T>::popBck(){
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

//Function dstryLst, destroy the entire linked list until front = NULL 
template<class T>
void LinkedList<T>::dstryLst(){
    while(front!= NULL ){ //While front is not null traverse and delete the nodes
       temp=front->linkPtr;   //Point to the next link in the list
       delete front;                //Delete the front of the list
       front=temp;                  //Swap the front
    }   
}