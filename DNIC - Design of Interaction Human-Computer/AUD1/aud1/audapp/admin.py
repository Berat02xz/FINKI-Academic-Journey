from django.contrib import admin

# Register your models here.

from .models import Musician, Song, Album, EP

admin.site.register(Musician)
admin.site.register(Song)
admin.site.register(Album)
admin.site.register(EP)


class AlbumAdmin(admin.ModelAdmin):
    list_display = ('name', 'artist', 'release_date', 'num_stars', 'display_total_runtime')


class EPAdmin(admin.ModelAdmin):
    list_display = ('name', 'artist', 'release_date', 'num_stars', 'display_total_runtime')


