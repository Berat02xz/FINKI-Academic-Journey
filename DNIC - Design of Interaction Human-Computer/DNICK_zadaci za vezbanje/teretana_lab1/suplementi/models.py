from django.db import models

# Create your models here.
class Supplement(models.Model):
    CATEGORY_CHOICES = [
        ('proteins', 'Proteins'),
        ('vitamins', 'Vitamins'),
        ('creatine', 'Creatine'),
        ('amino acids', 'Amino Acids'),
        ('pre-workout', 'Pre-Workout'),
    ]
    supplement_name = models.CharField(max_length=100)
    supplement_image = models.CharField(max_length=250)
    supplement_code = models.CharField(max_length=300)
    supplement_manufacturer = models.CharField(max_length=50)
    is_available = models.BooleanField()
    supplement_price = models.IntegerField()
    supplement_category = models.CharField(max_length=50, choices=CATEGORY_CHOICES)

    def __str__(self):
        return f"{self.supplement_name}"