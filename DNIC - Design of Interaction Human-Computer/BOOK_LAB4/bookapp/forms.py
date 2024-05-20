from django import forms

from .models import Book, DistributorHouse


class BookForm(forms.ModelForm):
    class Meta:
        model = Book
        fields = '__all__'
