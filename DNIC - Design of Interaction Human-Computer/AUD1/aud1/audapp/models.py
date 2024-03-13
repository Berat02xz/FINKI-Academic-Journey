from django.db import models


# Create your models here.

class Musician(models.Model):
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50, blank=True, null=True)
    instrument = models.CharField(max_length=100)

    def __str__(self):
        return self.first_name + ' ' + self.last_name


class Song(models.Model):
    artist = models.ForeignKey(Musician, on_delete=models.CASCADE)
    name = models.CharField(max_length=100)
    release_date = models.DateField()
    runtime = models.IntegerField()

    def __str__(self):
        return self.name


class Album(models.Model):
    artist = models.ForeignKey(Musician, on_delete=models.CASCADE)
    name = models.CharField(max_length=100)
    songs = models.ManyToManyField(Song)
    release_date = models.DateField()
    num_stars = models.IntegerField()

    def __str__(self):
        return self.name

    def total_runtime(self):
        return self.songs.aggregate(models.Sum('runtime'))['runtime__sum']

    def display_total_runtime(self):
        return self.total_runtime()

    display_total_runtime.short_description = 'Total Runtime'

class EP(models.Model):
    artist = models.ForeignKey(Musician, on_delete=models.CASCADE)
    name = models.CharField(max_length=100)
    songs = models.ManyToManyField(Song)
    release_date = models.DateField()
    num_stars = models.IntegerField()

    def __str__(self):
        return self.name

    def total_runtime(self):
        return self.songs.aggregate(models.Sum('runtime'))['runtime__sum']

    def display_total_runtime(self):
        return self.total_runtime()

    display_total_runtime.short_description = 'Total Runtime'