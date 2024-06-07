from django.shortcuts import render, get_object_or_404, redirect

from suplementi.models import Supplement
from .forms import SupplementForm

# Create your views here.
def index(request):
    supplements = Supplement.objects.all()
    return render(request, "index.html", {'supplements':supplements})

def supplement_details(request, id):
    supplement = Supplement.objects.get(id=id)
    return render(request,  "details.html", {'supplement':supplement})

def supplement_add(request):
    if request.method == "POST":
        supplement = SupplementForm(request.POST)
        if supplement.is_valid():
            supplement.save()
            return redirect("index")
    else:
        supplement = SupplementForm()

    return render(request, "add_suplement.html", {'form': supplement})


def home(request):
    return redirect("index")
