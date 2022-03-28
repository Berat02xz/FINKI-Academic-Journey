// Креирајте класа Rabotnik која во себе содржи:

// ime (низа од максимум 30 знаци)
// prezime (низа од максимум 30 знаци)
// plata (целобројна вредност)
// За оваа класа да се креираат default конструктор 
// и конструктор со аргументи. Да се имплементираат и следните методи:

// getPlata() која ја враќа платата на работникот
// pecati() која ги печати името, презимето и платата.
// Креирајте класа Fabrika во која има:

// rabotnik [100] (низа од вработени)
// brojVraboteni (целобројна вредност)
// Во класата имплементирајте ги следните методи:

// pecatiVraboteni() ги печати сите вработени
// pecatiSoPlata(int plata) ги печати сите вработени со плата
//  поголема или еднаква на дадената во аргументот(int plata).
// Во главната функција се внесуваат податоци за n вработени. 
// Притоа прво се внесува n, па податоците за сите n вработени. 
// Во последниот ред се чита минималната плата.

// На излез да се прикажат прво сите вработени, а потоа само оние со 
// поголема плата од минималната. Треба да се корисатат методите pecatiVraboteni и pecatiSoPlata!

// For example:

// Input	
// 6
// Mile Palkovski 20000
// Kalina Saleska 9530
// Aco Noveski 66320
// Kalina Saleska 10720
// Ilinka Ilieska 30220
// Vesna Petkova 10500
// 23000

// Result
// Site vraboteni:
// Mile Palkovski 20000
// Kalina Saleska 9530
// Aco Noveski 66320
// Kalina Saleska 10720
// Ilinka Ilieska 30220
// Vesna Petkova 10500
// Vraboteni so plata povisoka od 23000 :
// Aco Noveski 66320
// Ilinka Ilieska 30220


#include <iostream>
#include <cstring>
using namespace std;

class Rabotnik{
private:
    char ime[30];
    char prezime[30];
    int plata;
public:
    Rabotnik(){
        strcpy(this->ime,"");
        strcpy(this->prezime,"");
        this->plata=0;
    }
    ~Rabotnik(){}
    Rabotnik(char *ime, char *prezime, int plata){
        strcpy(this->ime,ime);
        strcpy(this->prezime,prezime);
        this->plata=plata;
    }

    int getPlata(){
        return this->plata;
    }

    void pecati(){
        cout<<this->ime<<" "<<this->prezime<<" "<<this->plata<<endl;
    }

};

class Fabrika{
private:
    Rabotnik rabotnici[100];
    int brojvraboteni;
public:
    void PecatiVraboteni(){
        cout<<"Site vraboteni: "<<endl;
        for(int i=0;i<brojvraboteni;i++){
            rabotnici[i].pecati();
        }
    }

    void PecatiSoPlata(int m){
    cout<<"Vraboteni so plata povisoka od "<<m<<" :"<<endl;
    
    for(int i=0; i<brojvraboteni;i++){
        if(rabotnici[i].getPlata() > m ) {
            rabotnici[i].pecati();
        }
    }

    }

    Fabrika(){
        this->brojvraboteni=0;
    }
    ~Fabrika(){}
    
    Fabrika(Rabotnik *R, int brojvraboteni){
        this->brojvraboteni=brojvraboteni;

//Mnogu Bitno 
        for(int i=0;i<brojvraboteni;i++){
            this->rabotnici[i]=R[i];
        }
    }
    
};

int main(){
// Во главната функција се внесуваат податоци за n вработени. 
// Притоа прво се внесува n, па податоците за сите n вработени. 
// Во последниот ред се чита минималната плата.

// На излез да се прикажат прво сите вработени, а потоа само оние со 
// поголема плата од минималната. Треба да се корисатат методите pecatiVraboteni и pecatiSoPlata!
int n;
cin>>n;
Rabotnik r[100];

char ime[30];
char prezime[40];
int plata;
for(int i=0;i<n;i++){
cin>>ime;
cin>>prezime;
cin>>plata;

r[i]=Rabotnik(ime,prezime,plata);
}

Fabrika Fabrik(r,n);

int najgolemaplata;
cin>>najgolemaplata;

Fabrik.PecatiVraboteni();
Fabrik.PecatiSoPlata(najgolemaplata);

return 0;
}