from django.db import models
from django.contrib.auth.models import User
# Create your models here.


class Manufacturer(models.Model):
    manufacturer_name = models.CharField(max_length=50)
    manufacturer_location = models.CharField(max_length=50)
    manufacturer_description = models.CharField(max_length=50)
    manufacturer_date = models.DateField()
    is_eu = models.BooleanField()

    def __str__(self):
        return f"{self.manufacturer_name} - {self.manufacturer_location}"


class Phone(models.Model):
    PHONE_TYPE = [
        ('small', 'Small'),
        ('Medium', 'Medium'),
        ('Large', 'Large')
    ]
    phone_manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)
    phone_model = models.CharField(max_length=60)
    phone_type = models.CharField(max_length=15, choices=PHONE_TYPE)
    phone_user = models.ForeignKey(User, on_delete=models.CASCADE)
    phone_image = models.CharField(max_length=250)
    phone_price = models.IntegerField()
    phone_year = models.IntegerField()
    is_new = models.BooleanField()

    def __str__(self):
        return f"{self.phone_manufacturer.manufacturer_name} - {self.phone_model}"
