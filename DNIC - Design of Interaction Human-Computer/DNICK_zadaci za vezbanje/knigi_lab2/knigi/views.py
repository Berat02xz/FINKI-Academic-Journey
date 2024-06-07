from django.shortcuts import render, redirect
from .models import Book, RecordLabel
from .forms import BookForm
# Create your views here.
def index(request):
    books = Book.objects.all()
    return render(request, "index.html", {'books':books})

def book_details(request, book_id):
    book = Book.objects.get(id=book_id)
    return render(request, "book_detail.html", {'book':book})

def book_add(request):
    if request.method == "POST":
        book_add_form = BookForm(request.POST)
        if book_add_form.is_valid():
            book_add_form.save()
            return redirect("index")
    else:
        book_add_form = BookForm()
    return render(request, "add_book.html", {"form":book_add_form})


def home(request):
    return redirect("index")
