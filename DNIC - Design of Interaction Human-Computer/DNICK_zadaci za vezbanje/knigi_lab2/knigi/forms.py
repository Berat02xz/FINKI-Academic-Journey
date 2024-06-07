from django import forms

from knigi.models import Book, RecordLabel

class BookForm(forms.ModelForm):
    class Meta:
        model = Book
        fields="__all__"