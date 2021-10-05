#importing flask, render_template to create pages, request to grab data from form, session to store session data, and flash to display errors
#and redirect to move to new route after taking in form data
from flask import Flask, render_template, redirect, request, session, flash # Import Flask to allow us to create our app
#from flask_app.models.pet import Pet #always import the classes from the model
from flask_app import app, bcrypt

#from flask_app.models.user import User


# @app.route("/newpet/validate")
# def new_pet():
#     if not "unique_userid" in session:
#         return redirect("/")

#     dict = {"id": session["unique_userid"]}

#     #pass the entire table, and pass in the current logged in user
#     return render_template("create_pet.html", logged_user = User.get_one(dict))


# @app.route("/newpet/create", methods = ['POST'])
# def create_pet():
#     if not Pet.validate_pet(request.form):
#         return redirect("/newpet/validate")
    
#     form_data = { **request.form, "user_id": session['unique_userid']} # This is how to insert user id into creating something 
    
#     Pet.create(form_data) # Using the variable we just created form_data
    

#     return redirect("/users/page")


# @app.route("/listpets/<int:id>/view")
# def view_pet(id):

#     dict = {"id": id}

#     return render_template("pet_list.html", 
#     user = User.get_one_join(dict), 
#     logged_user = User.get_one( {"id": session['unique_userid']}), 
#     user_name = User.get_one(dict))



# @app.route("/listpets/<int:pet_id>/edit")
# def edit_pet(pet_id):

#     dict2 = {"id": pet_id}

#     return render_template("edit_pet.html", logged_user = User.get_one( {"id": session['unique_userid']}), pet = Pet.get_one(dict2))
#     # The variables in this line give the template access to specific info, passing in logged_in user and the users pet

# @app.route("/existing_pet/<int:id>/edit", methods = ['POST'])
# def edit_petform(id):
#     if not Pet.validate_pet(request.form):
#         return redirect("/users/page")
    
#     form_data = { **request.form, "id": id} # This is how to insert user id into creating something 
    
#     Pet.update(form_data) # Using the variable we just created form_data
    

#     return redirect("/users/page")


# @app.route("/delete/<int:id>/pet")
# def delete_pet(id):

#     dict = {"id": id}
#     Pet.delete(dict)

#     return redirect("/users/page")
    