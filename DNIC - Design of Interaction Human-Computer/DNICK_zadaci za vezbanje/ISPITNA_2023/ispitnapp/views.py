from django.shortcuts import render

from ispitnapp.forms import ShowForm
# Create your views here.

from ispitnapp.models import Show


def index(request):
    shows = Show.objects.all()
    return render(request, 'index.html', {'shows': shows})


def addshow(request):
    if request.method == 'POST':
        # If the form is submitted, process the form data
        form = ShowForm(request.POST, request.FILES)
        if form.is_valid():
            # Save the form data to the database
            form.save()
            # Redirect to a success page or do something else
    else:
        # If the request is a GET request, render the form
        form = ShowForm()
    return render(request, 'AskForEvent.html', {'form': form})
