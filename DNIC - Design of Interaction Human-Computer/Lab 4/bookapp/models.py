from django.db import models


# Create your models here.

class Book(models.Model):
    COVER_TYPES = (
        ('H', 'Hardcover'),
        ('P', 'Paperback'),
    )

    CATEGORY_TYPES = (
        ('F', 'Fiction'),
        ('NF', 'Non-Fiction'),
        ('B', 'Biography'),
        ('C', 'Children'),
    )

    title = models.CharField(max_length=100)
    image = models.ImageField(upload_to='images/')
    ISBN = models.CharField(max_length=13)
    year = models.IntegerField()
    Distributor = models.ForeignKey('DistributorHouse', on_delete=models.CASCADE)
    pages = models.IntegerField()
    dimensions = models.CharField(max_length=100)
    cover = models.CharField(max_length=1, choices=COVER_TYPES)
    category = models.CharField(max_length=2, choices=CATEGORY_TYPES)
    price = models.DecimalField(max_digits=6, decimal_places=2)

    def __str__(self):
        return self.title


class DistributorHouse(models.Model):
    name = models.CharField(max_length=100)
    address = models.CharField(max_length=100)
    phone = models.CharField(max_length=10)
    email = models.EmailField(max_length=100)

    def __str__(self):
        return self.name
