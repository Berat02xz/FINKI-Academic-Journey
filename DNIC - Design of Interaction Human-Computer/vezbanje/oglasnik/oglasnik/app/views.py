from django.shortcuts import render, redirect
from .forms import *
from .models import *


# Create your views here.

def index(request):
    oglasi = Oglas.objects.all()
    return render(request, 'index.html', {'oglasi': oglasi})


def kreiraj_oglas(request):
    if request.method == 'POST':
        form = OglasFrom(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect('index')
    else:
        form = OglasFrom()
    return render(request, 'kreirajoglas.html', {'form': form})


def details(request, id):
    return render(request, 'details.html', context={'oglas': Oglas.objects.get(id=id)})
