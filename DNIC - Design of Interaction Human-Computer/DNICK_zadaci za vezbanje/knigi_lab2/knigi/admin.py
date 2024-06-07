from django.contrib import admin
from .models import RecordLabel, Book
# Register your models here.
admin.site.register(Book)
admin.site.register(RecordLabel)