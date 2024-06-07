from django.shortcuts import render, redirect
from .forms import CarForm
# Create your views here.
from avtomobili.models import Manufacturer, Car


def index(request):
    cars = Car.objects.all()
    return render(request, "index.html", {'cars':cars})


def details(request, id):
    car = Car.objects.get(id=id)
    return render(request, "details.html", {'car':car})


def create(request):
    if request.method == "POST":
        car = CarForm(request.POST)
        if car.is_valid():
            car.save()
            return redirect("index")
    else:
        car = CarForm

    return render(request, "create.html", {'form': car})


def home(request):
    return redirect("index")
