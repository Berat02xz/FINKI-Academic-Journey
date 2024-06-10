from django.db import models
from django.contrib.auth.models import User

# Create your models here.

class Kategorija(models.Model):
    ime = models.CharField(max_length=100)
    opis = models.CharField(max_length=100)
    datum = models.DateField()
    nedvizna = models.BooleanField(default=False)

    def __str__(self):
        return self.ime


class Oglas(models.Model):
    STATUS = {
        "FIKSNA": "FIKSNA",
        "PRIFAKJAM PREDLOZI": "PRIFAKJAM PREDLOZI",
        "NE FIKSNA": "NE FIKSNA",
    }
    naslov = models.CharField(max_length=100)
    opis = models.CharField(max_length=100)
    vreme = models.TimeField()
    datum = models.DateField()
    kategorija = models.ForeignKey(Kategorija, on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    image = models.ImageField(upload_to='data/')
    cena = models.IntegerField()
    status = models.CharField(max_length=100, choices=STATUS)
    nov = models.BooleanField(default=False)

    def __str__(self):
        return self.naslov
