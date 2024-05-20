from django import forms

from rest_app.models import Restaurant, Employee


class NameForm(forms.Form):
    your_name = forms.CharField(label="Your name is ", max_length=20)


class RestaurantForm(forms.ModelForm):
    class Meta:
        model = Restaurant
        fields = "__all__"


class EmployeeForm(forms.ModelForm):
    class Meta:
        model = Employee
        fields = "__all__"
        exclude = ["user"]
