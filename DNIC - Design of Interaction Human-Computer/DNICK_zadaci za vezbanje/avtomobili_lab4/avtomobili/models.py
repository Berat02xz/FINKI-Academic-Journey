from django.db import models
from django.contrib.auth.models import User
# Create your models here.


class Manufacturer(models.Model):
    manufacturer_name = models.CharField(max_length=50)
    manufacturer_location = models.CharField(max_length=50)
    manufacturer_user = models.ForeignKey(User, on_delete=models.CASCADE)
    manufacturer_year = models.IntegerField()
    number_of_employees = models.IntegerField()

    def __str__(self):
        return f"{self.manufacturer_name} - {self.manufacturer_location}"

class Car(models.Model):
    CAR_TYPES = [
        ('Sedan', 'Sedan'),
        ('SUV', 'SUV'),
        ('Hatchback', 'Hatchback'),
        ('Coupe', 'Coupe'),
    ]

    manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)
    car_price = models.IntegerField()
    car_chassis = models.CharField(max_length=100)
    car_model = models.CharField(max_length=100)
    car_color = models.CharField(max_length=20)
    car_year = models.IntegerField()
    car_mileage = models.IntegerField()
    car_type = models.CharField(max_length=50, choices=CAR_TYPES)
    car_image = models.CharField(max_length=200)

    def __str__(self):
        return f"{self.car_model}"