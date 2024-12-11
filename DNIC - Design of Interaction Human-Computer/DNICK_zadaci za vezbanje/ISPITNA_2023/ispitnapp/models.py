from django.contrib.auth.models import User
from django.db import models


# Create your models here.

class Show(models.Model):
    Title = models.CharField(max_length=100)
    Date = models.DateField()
    Time = models.TimeField()
    Poster = models.ImageField(upload_to='posters/')
    UserCreated = models.ForeignKey(User, on_delete=models.CASCADE)
    Bands = models.ManyToManyField('Band')

    def __str__(self):
        return f"{self.Title} {self.Date}"


class Band(models.Model):
    Name = models.CharField(max_length=100)
    YearFormed = models.IntegerField()
    Country = models.CharField(max_length=100)
    NumberOfShows = models.IntegerField()

    def __str__(self):
        return f"{self.Name} {self.Country}"
