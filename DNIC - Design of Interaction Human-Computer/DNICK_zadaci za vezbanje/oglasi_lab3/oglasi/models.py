from django.db import models
from django.contrib.auth.models import User
# Create your models here.
# Секој оглас се карактеризира со наслов, опис, време и датум на постирање,
# категорија на оглас, корисникот кој го креирал огласот, фотографија од продуктот,
# цена, статус (цената е фиксна, прифаќам предлози, цената не е фиксна) и
# информација за тоа дали е нов или не е.
# За секоја категорија се чува име, опис, датум на креирање, информација за тоа дали се работи за недвижнини.
# При креирање на нов оглас, корисникот се доделува автоматски според најавениот корисник.
# Креирајте ги соодветните модели кои ви се потребни за да овозможите правилно функционирање на системот.
# Потребно е да овозможите додавање на објекти преку Админ панелот.

class Category(models.Model):
    category_name = models.CharField(max_length=50)
    category_description = models.CharField(max_length=200)
    category_created_date = models.DateTimeField()
    is_real_estate = models.BooleanField(default=False)
    def __str__(self):
        return f"{self.category_name} - {self.category_description}"

class Listing(models.Model):
    PRICE_STATUS = [
        ('fiksna', 'Cenata e fiksna'),
        ('predlozi', 'Prifakjam predlozi'),
        ('ne e fiksna', 'Cenata ne e fiksna')
    ]
    listing_title = models.CharField(max_length=100)
    listing_description = models.CharField(max_length=200)
    listing_date = models.DateTimeField()
    listing_category = models.ForeignKey(Category, on_delete=models.CASCADE)
    listing_user = models.ForeignKey(User, on_delete=models.CASCADE)
    listing_image = models.CharField(max_length=300)
    listing_price = models.IntegerField()
    listing_status = models.CharField(max_length=50, choices=PRICE_STATUS)
    is_new = models.BooleanField(default=False)

    def __str__(self):
        return f"{self.listing_title} - {self.listing_description}"