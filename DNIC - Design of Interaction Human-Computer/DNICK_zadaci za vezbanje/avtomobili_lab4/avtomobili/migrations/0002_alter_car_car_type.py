# Generated by Django 4.1 on 2024-06-06 21:19

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('avtomobili', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='car',
            name='car_type',
            field=models.CharField(choices=[('Sedan', 'Sedan'), ('SUV', 'SUV'), ('Hatchback', 'Hatchback'), ('Coupe', 'Coupe')], max_length=50),
        ),
    ]
