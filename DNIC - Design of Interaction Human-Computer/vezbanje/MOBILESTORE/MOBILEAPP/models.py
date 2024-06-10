from django.db import models
from django.contrib.auth.models import User


# Create your models here.

class Proizvoditel(models.Model):
    ime = models.CharField(max_length=100)
    lokacija = models.CharField(max_length=100)
    opis = models.TextField()
    datum = models.DateField(auto_now_add=True)
    vo_EU = models.BooleanField(default=True)

    def __str__(self):
        return self.ime


class Mobile(models.Model):
    TIP_UREDJA = (
        ('M', 'Mal'),
        ('S', 'Sreden'),
        ('G', 'Golem')
    )

    proizvoditel = models.CharField(max_length=100)
    model = models.CharField(max_length=100)
    tip = models.CharField(max_length=1, choices=TIP_UREDJA)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    image = models.ImageField(upload_to='data/', blank=True, null=True)
    cena = models.DecimalField(max_digits=10, decimal_places=2)
    godina = models.IntegerField()
    nov = models.BooleanField(default=True)

    def __str__(self):
        return self.model
