from django import forms

from oglasi.models import Listing

class ListingForm(forms.ModelForm):
    class Meta:
        model=Listing
        datetime_field= forms.DateTimeField()
        fields="__all__"
        exclude=["listing_user", "listing_date"]