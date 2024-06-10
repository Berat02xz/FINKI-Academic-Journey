from django.contrib import admin

# Register your models here.

from .models import Proizvoditel, Mobile

admin.site.register(Proizvoditel)
admin.site.register(Mobile)

class MobileAdmin(admin.ModelAdmin):
    exclude = ('user',)

    def save_model(self, request, obj, form, change):
        obj.user = request.user
        super().save_model(request, obj, form, change)

    def has_add_permission(self, request):
        if request.user.is_superuser:
            return True

    def has_change_permission(self, request, obj=None):
        return obj and obj.user == request.user or request.user.is_superuser