from django.contrib.auth.models import User
from django.db import models

# Create your models here.

class Restaurant(models.Model):

    name = models.CharField(max_length=30)
    address = models.CharField(max_length=50)
    phone = models.CharField(max_length=30)
    email = models.EmailField()
    capacity = models.IntegerField()

    def __str__(self):
        return self.name


class Employee(models.Model):

    POSITION_CHOICES = [
        ("SR", "Server"),
        ("DE", "Delivery"),
        ("MG", "Manager"),
        ("CH", "Chef"),
        ("DW", "DishWasher")
    ]

    name = models.CharField(max_length=30)
    surname = models.CharField(max_length=30)
    date_of_birth = models.DateField()
    position = models.CharField(max_length=3, choices=POSITION_CHOICES)
    restaurant = models.ForeignKey(Restaurant, on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.name} {self.surname}"


class Dish(models.Model):

    name = models.CharField(max_length=30)
    ingredients = models.TextField(blank=True, null=True)
    calories = models.IntegerField()

    def __str__(self):
        return f"{self.name} - {self.calories}"


class DishRestaurant(models.Model):
    restaurant = models.ForeignKey(Restaurant, on_delete=models.CASCADE)
    dish = models.ForeignKey(Dish, on_delete=models.CASCADE)
    price = models.DecimalField(max_digits=6, decimal_places=2)


class BusinessHours(models.Model):

    DAY_CHOISES=[
        ("Mon", "Monday"),
        ("Tue", "Tuesday"),
        ("Wed", "Wednesday"),
        ("Thu", "Thursday"),
        ("Fri", "Friday"),
        ("Sat", "Saturday"),
        ("Sun", "Sunday")
    ]

    day_of_the_week = models.CharField(max_length=4, choices=DAY_CHOISES)
    restaurant = models.ForeignKey(Restaurant, on_delete=models.CASCADE)
    time_from = models.TimeField()
    time_to = models.TimeField()


