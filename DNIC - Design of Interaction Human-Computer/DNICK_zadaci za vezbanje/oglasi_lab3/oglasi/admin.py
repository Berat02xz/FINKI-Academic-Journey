from django.contrib import admin
from .models import Listing, Category
# Register your models here.


class ListingAdmin(admin.ModelAdmin):
    exclude = ('listing_user',)

    def save_model(self, request, obj, form, change):
        obj.listing_user = request.user
        return super(ListingAdmin, self).save_model(request, obj, form, change)


admin.site.register(Listing, ListingAdmin)
admin.site.register(Category)
