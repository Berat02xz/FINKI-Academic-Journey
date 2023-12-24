// За програмски јазик C.

// Да се напише програма во која од стандарден влез се вчитува N (бројот на производи), 
//а потоа се вчитуваат податоците за N производи (име, цена, количина). 
//Програмата треба на стандарден излез да ја отпечати листата на купени производи и вкупната сума која треба да се плати во следниот облик (пример):

// 1. Flips    10.00 x 3 = 30.00
// 2. CocaCola 75.00 x 2 = 150.00
// 3. ChokoBanana  5.00 x 10 = 50.00
// Total: 230.00


#include <stdio.h>
#include <string.h>

//структурата
typedef struct Proizvod{
char ime[50];
float cena;
float kolicina;
}Proizvod;

//главна програма
int main() {
Proizvod p[100];

int N;
printf("Vnesi Broj Na Produkti: \n");
scanf("%d", &N);

for(int i=0;i<N;i++){
printf("Na ist red vnesi Ime Cena i Kolicina na produktot: \n");
scanf("%s %f %f", &p[i].ime, &p[i].cena, &p[i].kolicina);
}

float Total=0.0;
for(int i=0;i<N;i++){
float vkupno= (p[i].cena) * (p[i].kolicina);
Total=Total+vkupno;
printf("%d. %s    %.02f x %f = %.02f \n", i+1 ,p[i].ime,p[i].cena,p[i].kolicina ,vkupno);
}
printf("Total: %.02f", Total);
return 0;
}