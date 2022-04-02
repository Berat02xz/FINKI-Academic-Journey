// ИТ продавница 2 Problem 1 (0 / 0)


// Во програмскиот јазик C да се креира структура Laptop за опишување на спецификациите на даден преносен компјутер.
// Структурата треба да содржи: (5 поени)

// Марка ( низа од 100 знаци)
// Големина на хард диск (цел број)
// Големина на RAM (цел број)
// Дали хард дискот е SSD (булова променлива)


// Да се креира структура ITStore, што содржи: (5 поени)

// Име на продавницата (низа од 100 знаци)
// Локација (низа од 100 знаци)
// Низа од достапни лаптопи (низа од максимум 100 елементи од структурата Laptop)
// Број на елементи во низата (цел број)

// Да се креира функција print за печатење на информациите за сите лаптопи во една продавница
// во формат: (10 поени)

// [Ime na prodavnicata] [Lokacija]
// [Marka1] [golemina na hard disk] [golemina na RAM]
// [Marka2] [golemina na hard disk] [golemina na RAM]
// [Marka3] [golemina na hard disk] [golemina na RAM]
// ...
// Да се креира функција najdobra_memorija, која што прима променлива од тип ITStore
// како влезен параметар и го враќа бројот на лапопи кои имаат RAM >= на 8GB и имаат
// SSD за хард диск. (7 поени)

// Да се креира функција najdobra_ponuda, што прима низа од променливи од типот ITStore
// и го печати името и локацијата на онаа продавница која нуди најголем број преносни
// компјутери со најдобра меморија. Ако има повеќе такви продавници, се печати последно
// најдената (8 поени)

// Да се дополни функцијата main (10 поени).

// Од тастатура се внесува бројот на продавници, па потоа за секоја продавница се внесуваат
// името и локацијата, а потоа бројот на компјутери, па за секој од компјутерите марка,
// големина на хард дискг, големина на RAM, дали е SSD. Потоа се печатат сите внесени
// продавници и најдобрата понуда.

#include <stdio.h>
#include <math.h>

typedef struct Laptop{
char Marka[100];
int Mem;
int SSD;
}Laptop;

typedef struct ITStore{
char ime[100];
char lokacija[100];
Laptop kompjuteri[100];
int brojelementi;
}Store;


void print(Store s){
        printf("%s %s", s.ime, s.lokacija);
    for(int i=0;i<s.brojelementi;i++){
        printf("%s %d %d", s.kompjuteri[i].Marka , s.kompjuteri[i].Mem, s.kompjuteri[i].SSD);
    }
}

void najdobra_memorija(Store s){
    for(int i=0;i<s.brojelementi;i++){
        if(s.kompjuteri[i].Mem >= 8 && s.kompjuteri[i].SSD){
            printf("samo poveke od 8GB imaat: %s %d %d", s.kompjuteri[i].Marka , s.kompjuteri[i].Mem, s.kompjuteri[i].SSD);
        }
    }
}

int main(){
int n;
Store s[10];
printf("broj na prodavnici:");
scanf("%d", &n);

for(int i=0; i<n; i++){
printf("Vnesi ime, lokacija");
scanf("%s %s", s->ime,s->lokacija );
printf("Broj na kompjuteri:");
scanf("%d", s->brojelementi);
int broj=s->brojelementi;
for(int j=1; j<broj; j++){
printf("Vnesi Marka, Golemina Hard Disk & dali ima SSD [1/0] %s %d %d",s->kompjuteri[j].Marka,s->kompjuteri[j].Mem,s->kompjuteri[j].SSD );
}
}

print(*s);
najdobra_memorija(*s);

}