#this model will hold take the information of the user making the order to the business

class User:
    #get the input for the user information, to create User class object
    def __init__(self, name, age, email, address): 
        self.name = name
        self.age = age
        self.email = email
        self.address = address
    
    #display the user information using this function
    def display_user_info(self): 
        print("Name:", self.name)
        print("Age:", self.age)
        print("Email:", self.email)
        print("Address:", self.address)


