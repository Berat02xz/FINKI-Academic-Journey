from django.http import Http404, HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from rest_app.forms import EmployeeForm, NameForm, RestaurantForm
from rest_app.models import Restaurant, Dish


# def index(request):
#     return render(request, "index.html")

def restaurants(request):
    restaurants = Restaurant.objects.all()

    return render(request, "restaurants.html", {"restaurants": restaurants, "new_var": 1})


def restaurant_detail(request, restaurant_id):
    restaurant = Restaurant.objects.get(id=restaurant_id)
    return HttpResponse(
        f"<p>Restaurant details for restaurant with id = {restaurant_id}.</p><p> Restaurant name: {restaurant.name}, capacity: {restaurant.capacity}</p>")


def dishes(request):
    dishes = Dish.objects.all()

    return render(request, "dishes.html", {"dishes": dishes, "count": len(dishes)})


def dish_detail(request, dish_id):
    try:
        dish = Dish.objects.get(id=dish_id)
    except Dish.DoesNotExist:
        raise Http404("Dish does not exists!")

    return HttpResponse(dish)


def index(request):
    # if this is a POST request we need to process the form data
    if request.method == "POST":
        # create a form instance and populate it with data from the request:
        form = NameForm(request.POST)
        # check whether it's valid:
        if form.is_valid():
            request.session['your_name'] = form.cleaned_data['your_name']

    # if a GET (or any other method) we'll create a blank form
    else:
        form = NameForm()

    if 'your_name' not in request.session:
        your_name = "Anonymous"
    else:
        your_name = request.session['your_name']  # add variable to session

    return render(request, "index.html", {"form": form, "your_name": your_name})


def add_restaurant(request):
    if request.method == "POST":
        restaurant = RestaurantForm(request.POST)
        if restaurant.is_valid():
            restaurant.save()
    else:
        restaurant = RestaurantForm()

    return render(request, "add_restaurant.html", {"form": restaurant})


def add_employee(request):
    if request.method == "POST":
        employee = EmployeeForm(request.POST)
        if employee.is_valid():
            if not request.user.is_anonymous:
                employee.instance.user = request.user
                employee.save()
                employee = EmployeeForm()  # show empty form
            else:
                employee.add_error(None, "You must log in.")
        else:
            print(employee.errors)  # print in console

    else:
        employee = EmployeeForm()

    return render(request, "add_employee.html", {"form": employee})
