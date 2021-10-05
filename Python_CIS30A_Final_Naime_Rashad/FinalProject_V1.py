"""
Option 1, Business Python Program
Write a Python program to place order and set appointment for delivery of goods or services from a business of your choice
The choice for the business is grocery store
"""
#import all model classes
#import all the functions from the functions module
#regular expression will be used to validate the user information for email and address
#using this import to validate the appointment variable for the date selected
from user import User
from business import Business
from customer import Customer
from functions import *     
import re                     
import datetime               

#start by creating a new user by calling the function create valid user, that returns a User class object
new_user = create_valid_user()

#store our new user data into the customer sub class
#that inherits from both the business class that is a grocery store and the user class
new_customer = Customer(new_user.name, new_user.age, new_user.email, new_user.address)
new_customer.display_customer()

#create an instance of the business class, for the grocery store
grocery_store = Business() 

#store the new grocery list that is inputted by user into the order dictionary attribute in customer
new_customer.order = grocery_store.add_existing_to_cart()  

#using function validate appointment, retrieve user input for the order date in mm/dd/yy, and store within customer sub class
new_customer.appointment = validate_appointment()

#add content without changing previous
file = open('myFile.txt', 'a+') #append mode
file.write("\n========================================")
file.write(f"\nUser information:\nCustomer: {new_customer.name}\nEmail: {new_customer.email}\nShipping Address: {new_customer.address}\nOrder form:")

#loop through the order list, to display what the customer ordered, the amount, and the price of each item
for item in new_customer.order:
    file.write(f"\n{item}, amount: {new_customer.order[item][0]} at ${new_customer.order[item][1]} each.")

file.write(f"\nOrder By: {new_customer.appointment}")
file.write("\n========================================")
file.seek(0)
print(file.read())
file.close()    
