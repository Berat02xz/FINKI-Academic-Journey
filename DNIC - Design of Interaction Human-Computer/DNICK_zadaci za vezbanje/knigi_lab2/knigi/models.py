from django.db import models

# Create your models here.


class RecordLabel(models.Model):
    label_name = models.CharField(max_length=50)
    label_country = models.CharField(max_length=50)
    label_city = models.CharField(max_length=50)
    label_year = models.IntegerField()
    label_website = models.CharField(max_length=200)

    def __str__(self):
        return f"{self.label_name} - {self.label_country}"

class Book(models.Model):
    COVER_CHOICES = [
        ('soft cover', 'Soft Cover'),
        ('hard cover', 'Hard Cover')
    ]
    CATEGORY_CHOICES = [
        ('romance', 'Romance'),
        ('thriller', 'Thriller'),
        ('biography', 'Biography'),
        ('classic', 'Classic'),
        ('drama', 'Drama'),
        ('history', 'History'),
    ]
    book_title = models.CharField(max_length=50)
    book_image = models.CharField(max_length=300)
    book_isbn = models.CharField(max_length=100)
    book_year = models.IntegerField()
    book_publisher = models.ForeignKey(RecordLabel, on_delete=models.CASCADE)
    book_cover = models.CharField(max_length=15, choices=COVER_CHOICES)
    book_category = models.CharField(max_length=50, choices=CATEGORY_CHOICES)
    book_price = models.IntegerField()

    def __str__(self):
        return f"{self.book_title} - ({self.book_year})"
