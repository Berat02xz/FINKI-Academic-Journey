from django.contrib import admin

from .models import Book, DistributorHouse

# Register your models here.

admin.site.register(Book)
admin.site.register(DistributorHouse)
