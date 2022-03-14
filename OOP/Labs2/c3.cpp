// Да се дефинира класа Film, во која ќе се чуваат информации за:

// име низа од 100 знаци
// режисер низа од 50 знаци
// жанр низа од 50 знаци
// година цел број
// Сите променливи треба да бидат приватни. Соодветно во 
// рамките на класата да се дефинираат:

// default конструктор и конструктор со аргументи
// метод за печатење на информациите за филмот
// Дополнително да се реализира надворешна функција:

// void pecati_po_godina(Film *f, int n, int godina) која ќе 
// прима аргумент низа од филмови, вкупниот број на филмови и 
// година, а треба да ги отпечати само филмовите кои се 
// направени во дадената година.

// For example:

// Input	
// 4
// Frankenweenie
// Tim_Burton
// Animation
// 2012
// Lincoln
// Steven_Spielberg
// History
// 2012
// Wall-E
// Andrew_Stanton
// Animation
// 2008
// Avatar
// James_Cameron
// Fantasy
// 2009
// 2008

// Result
// Ime: Wall-E
// Reziser: Andrew_Stanton
// Zanr: Animation
// Godina: 2008

#include <iostream>
#include <cstring>
using namespace std;

class Film{
private:
    char title[100];
    char producer[50];
    char genre[50];
    int year;

public:
//konstruktori
Film(){}; ~Film(){};

void set(char ime[100], char reziser[50], char zanr[50], int godina){
strcpy(title,ime);
strcpy(producer,reziser);
strcpy(genre,zanr);
year=godina;
}

void print(){
    cout<<"Ime: "<<title<<endl;
    cout<<"Reziser: "<<producer<<endl;
    cout<<"Zanr: "<<genre<<endl;
    cout<<"Godina: "<<year<<endl;
}

int getyear(){
    return year;
}

};

void pecati_po_godina(Film *f, int n, int godina){
for(int i=0; i<n; ++i){
if (f[i].getyear()==godina){
    f[i].print();
}
}
}

int main() {
 	int n;
 	cin >> n;
 	Film filmovi[n];
 	for(int i = 0; i < n; ++i) {
 		char ime[100];
 		char reziser[50];
 		char zanr[50];
 		int godina;
 		cin >> ime;
 		cin >> reziser;
 		cin >> zanr;
 		cin >> godina;
 		filmovi[i].set(ime,reziser,zanr,godina);
    }
 	int godina;
 	cin >> godina;
 	pecati_po_godina(filmovi, n, godina);
    
    return 0;
 }