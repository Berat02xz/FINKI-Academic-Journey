from django.contrib import admin
from .models import Car, Manufacturer
# Register your models here.


class ManufacturerAdmin(admin.ModelAdmin):
    exclude=('manufacturer_user',)
    def save_model(self, request, obj, form, change):
        obj.manufacturer_user = request.user
        return super(ManufacturerAdmin, self).save_model(request, obj, form, change)


admin.site.register(Car)
admin.site.register(Manufacturer, ManufacturerAdmin)
