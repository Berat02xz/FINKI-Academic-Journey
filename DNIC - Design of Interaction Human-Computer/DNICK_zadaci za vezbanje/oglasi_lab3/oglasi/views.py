from django.shortcuts import render, redirect
from datetime import datetime
from .models import Listing, Category
from .forms import ListingForm
# Create your views here.
def index(request):
    listings = Listing.objects.all()
    return render(request, "index.html", {'listings' : listings})

def listing_detail(request, id):
    listing = Listing.objects.get(id=id)
    return render(request, "detail.html", {'listing':listing})

def create(request):
    if request.method == "POST":
        form = ListingForm(request.POST)
        if form.is_valid():
            listing = form.save(commit=False)
            listing.listing_user = request.user
            listing.listing_date = datetime.now()
            listing.save()
            return redirect("index")
    else:
        listing = ListingForm()
    return render(request, "create.html", {'form':listing})

def home(request):
    return redirect("index")


# listing.listing_user = request.user
#             listing.listing_date = datetime.now()
#             listing.save()