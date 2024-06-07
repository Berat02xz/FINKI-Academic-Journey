from django.shortcuts import render, redirect
from .models import Phone
from .forms import PhoneForm
# Create your views here.


def home(request):
    return redirect("index")

def index(request):
    phones = Phone.objects.all()
    return render(request, "index.html", {'phones': phones})


def details(request, id):
    phone = Phone.objects.get(id=id)
    return render(request, "details.html", {'phone': phone})


def create(request):
    if request.method == "POST":
        form = PhoneForm(request.POST)
        if form.is_valid():
            phone = form.save(commit=False)
            phone.phone_user = request.user
            phone.save()
            return redirect("index")
    else:
        form = PhoneForm()
    return render(request, "create.html", {'form': form})
