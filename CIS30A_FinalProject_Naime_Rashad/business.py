#setting up module of the business class used to process product orders
class Business:
    def __init__(self):
        self.title = "Grocery Outlet"
        self.products = {} 
    
    #User will add to cart, from existing list of products
    def add_existing_to_cart(self):
        #create predifined dictionary with grocery items, have the user select items from this list
        product_menu = {"dairy": ["eggs", 2.99, "milk", 3.59, "cheese", 2.50], "Fruit": ["apples", 3.05 ,"grapes", 2.05, "oranges", 1.99], "meat": ["steak", 5.99, "chicken", 4.99]}
        
        #display product menu
        for stuff in product_menu:
            print(f" {stuff}: {product_menu[stuff]}")
            
        #set a flag for the while loop, and continue through while until user decides they are done purchasing items
        done_shopping = False
        
        while not done_shopping:
            try:
                #this will be the name of the item the user would like to order
                new_item = input("Please enter an item you would like to purchase from the grocery store: ")
                
                #if numbers where inputted into the item, restart from the top of the while loop, and display error message
                if not new_item.replace(' ','').isalpha(): 
                    print("\nenter a proper product name\n")
                    continue
                
                amount = int(input("Please enter the how many of this item you are purchasing: ")) #the amount of the item the user wants
                
            except ValueError:
                print("Enter a proper amount value")
            else:
                #loop through the keys in the menu, and then use if statment to check if the item entered exists within the dictionary
                for key in product_menu:  
                    if new_item in product_menu[key]: #if the new_item exists within the dictionary, then loop through the length of the list within that key spot
                        for i in range(len(product_menu[key])):
                            if new_item == product_menu[key][i]: #if the item entered from the user exists within the menu, then add it to the self.products
                                self.products[new_item] = [amount, product_menu[key][i+1]]
                
                #display the current list of items that the user has purchased
                print("\nYour Current List:")
                for list_item in self.products:
                    print(f"{list_item}: {self.products[list_item]}")
                    
                cont_shopping = input("\nWould you like to continue shopping Y or N:  ")
                cont_shopping = cont_shopping.upper()
                
                if cont_shopping == "N":
                    done_shopping = True              

        print(self.products)
        return self.products


    #allow users to create and add what they like to their cart, (**Not used within the project**)
    def add_to_cart(self):
        #set a flag for the while loop, and continue through while until user decides they are done purchasing items
        done_shopping = False
        
        while not done_shopping:
            try:
                #this will be the name of the item the user would like to order
                new_item = input("Please enter an item you would like to purchase from the grocery store: ")
                
                #if numbers where inputted into the item, restart from the top of the while loop, and display error message
                if not new_item.replace(' ','').isalpha(): 
                    print("\nenter a proper product name\n")
                    continue
                
                amount = int(input("Please enter the how many of this item you are purchasing: ")) #the amount of the item the user wants
                
            except ValueError:
                print("Enter a proper amount value")
            else:
                #if the item exists within the products dictionary increase its amount by requested input
                #else if the item requested does not exist, set it as a new item within the products dictionary
                if new_item in self.products:
                    self.products[new_item] += amount
                else:
                    self.products[new_item] = amount
                
                #display the current list of items that the user has purchased
                print("\nYour Current List:")
                for list_item in self.products:
                    print(f"{list_item}: {self.products[list_item]}")
                    
                cont_shopping = input("\nWould you like to continue shopping Y or N:  ")
                cont_shopping = cont_shopping.upper()
                
                if cont_shopping == "N":
                    done_shopping = True                
        
        #return the list of products purchased and the quantity selected
        return self.products
    
    #display the list of products, the user has purchased
    def display_products(self):
        for item in self.products:
            print(f"{list_item}: {self.products[list_item]}")
    