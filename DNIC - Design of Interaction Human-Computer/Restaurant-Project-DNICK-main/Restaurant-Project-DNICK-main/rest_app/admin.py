from django.contrib import admin
from .models import Restaurant, Dish, DishRestaurant, Employee, BusinessHours


# Register your models here.

class BusinessHoursInlineAdmin(admin.TabularInline):
    model = BusinessHours
    extra = 1


class RestaurantAdmin(admin.ModelAdmin):
    list_display = ('name', "email", "phone",)
    list_filter = ('capacity', "name")

    inlines = [BusinessHoursInlineAdmin, ]

    fieldsets = [
        (None, {'fields': ['name', 'address', 'capacity']}),
        ("Contact", {'fields': ['email', 'phone', ]})
    ]

    def has_delete_permission(self, request, obj=None):
        if request.user.is_superuser:
            return True
        return False


class EmployeeAdmin(admin.ModelAdmin):
    exclude = ("user",)

    def has_change_permission(self, request, obj=None):
        if obj and obj.user == request.user:
            return True
        return False

    def save_model(self, request, obj, form, change):
        obj.user = request.user
        return super(EmployeeAdmin, self).save_model(request, obj, form, change)


class DishAdmin(admin.ModelAdmin):
    list_display = ('name',)

    def has_add_permission(self, request, obj=None):
        if request.user.is_superuser:
            return True
        return False


admin.site.register(Restaurant, RestaurantAdmin)
admin.site.register(Dish)
admin.site.register(DishRestaurant)
admin.site.register(Employee, EmployeeAdmin)
