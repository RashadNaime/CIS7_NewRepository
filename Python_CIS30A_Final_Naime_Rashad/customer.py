from user import User
from business import Business

#customer class will inherit from both user and business
#user attributes must be passed into the paramater and super()
#The customer will have an appointment attribute to set order date
#As well as a dictionary of orders made by the user 
class Customer(User, Business):
    def __init__(self, name, age, email, address):
        super().__init__(name,age,email,address) 
        self.order = {} 
        self.appointment = ""
        
    def display_customer(self):
        print(f"\nWelcome {self.name}. Thank you for signing up with us at Grocery Outlet")
        print(f"Your Information:\nEmail: {self.email}\nShipping Address: {self.address}.\n")