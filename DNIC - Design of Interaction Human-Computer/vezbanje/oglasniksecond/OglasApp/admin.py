from django.contrib import admin

# Register your models here.
from .models import *


class KategorijaAdmin(admin.ModelAdmin):
    list_display = ('ime', 'opis', 'datum', 'nedvizna')


class OglasAdmin(admin.ModelAdmin):
    exclude = ("user",)
    list_display = ('naslov', 'opis', 'vreme', 'datum', 'kategorija', 'image', 'cena', 'status', 'nov')

    def save_model(self, request, obj, form, change):
        obj.user = request.user
        super().save_model(request, obj, form, change)


admin.site.register(Kategorija, KategorijaAdmin)
admin.site.register(Oglas, OglasAdmin)
