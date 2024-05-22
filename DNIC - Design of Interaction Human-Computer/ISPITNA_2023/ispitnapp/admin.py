from django.contrib import admin
from django.contrib.auth.models import User

# Register your models here.

from .models import Show, Band

admin.site.register(Show)
admin.site.register(Band)


class ShowAdmin(admin.ModelAdmin):

    def has_add_permission(self, request):
        if request.user.is_superuser:
            return True
        else:
            return False

    def has_change_permission(self, request, obj=None):
        if request.user == obj.UserCreated:
            return True
        else:
            return False