import datetime #using this import to validate the appointment variable for the date selected
from user import User
from business import Business
from customer import Customer

#validate appointment will be used to set and validate the delivery date of that the user will enter
def validate_appointment():
    print("\nPlease select an appointment date in the format mm/dd/yy")
    #using flag to have while loop continue until user inputs date in the correct format
    set_date = False 

    while not set_date:
        #try except statement used to prevent the user from inputting any incorrect values for appointment
        try: 
            appointment = input("\nPlease enter a proper date for an appointment in format mm/dd/yy: ") #will only take in format of mm/dd/yy
            
            #using datetime to validate the date the user entered
            month, day, year = appointment.split("/")
            datetime.datetime(int(year), int(month), int(day))
            
            #if statement is used to make sure the year entered is within at least a year in range of 2021
            #the user may only order within the 1-year span of 2021 to 2022
            if int(year) < 21 or int(year) > 22:
                print("\nPlease enter a current year, either 2021 or 2022 for your order")
                continue

        except ValueError:
            print("Must input appointment date in mm/dd/yy format, please try again")
        else:
            print("Thank you for entering a proper date\n")
            set_date = True
    
    return appointment


#create valid user will validate all of the user inputs for their information
#Then return a user object, created using the User class with inputs as parameters
def create_valid_user():
    print("\nPlease enter your information to make an appointment")
    valid_info = False #set flag for while loop
    
    #using while loop take in the inputs for the user's information and validate each field of name, age, email, address
    while not valid_info:
            name = input("Enter your full name: ")
            if name.replace(' ','').isalpha():
                valid_info = True
            else:
                print("Please enter a proper name")
                valid_info = False
                continue
                
            age = input("Enter your age: ")
            if age.isdigit():
                valid_info = True
            else:
                print("Please enter a proper age")
                valid_info = False
                continue
                
            email = input("Enter your email for shipping information: ")
            if "@" in email:
                valid_info = True
            else:
                valid_info = False
                continue

            address = input("Enter your address to have product shipped to your location: ")
            if address.replace(' ','').isalnum():
                valid_info = True
            else:
                print("Invalid address please try again")
                valid_info = False
        
    temp = User(name, age, email, address) #set the validated inputs into the User class, and return that user class object
        
    return temp