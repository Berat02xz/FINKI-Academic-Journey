from django.shortcuts import render

# Create your views here.

from django.shortcuts import render, redirect

from .models import Book, DistributorHouse
from .forms import BookForm

def index(request):
    books = Book.objects.all()
    return render(request, 'index.html', {'books': books})


def book_detail(request, book_id):
    book = Book.objects.get(id=book_id)
    return render(request, 'books_id.html', {'book': book})


def books_add(request):
    if request.method == 'POST':
        form = BookForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect('index')
    else:
        form = BookForm()
    return render(request, 'books_add.html', {'form': form})

