from django import forms
from .models import *


class OglasFrom(forms.ModelForm):
    class Meta:
        model = Oglas
        exclude = {'user', }

