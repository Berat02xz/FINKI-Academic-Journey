// Да се креира класа за претставување на планинарско друштво во која ќе се чуваат информации за името на друштвото (динамички алоцирана низа од знаци), број на поминати тури (цел број) и број на членови во планинарското друштво (цел број). За оваа класа да се напише:

// оператор + за собирање на две друштва што како резултат враќа друштво со број на членови еднаков на збирот од членовите од двете друштва, а останатите атрибути на резултантното друштво ги добиваат вредностите на соодветните атрибути од друштвото со поголем број на членови

// оператори >, < за споредба во однос на бројот на членови во планинарските друштва

// оператор << за печатење на информациите за планинарското друштво

// Да се напише функција што на влез прима низа од планинарски друштва и вкупен број на друштва во низата и го печати планинарското друштво што има најголем број на членови .

// For example:

// Input	
// Bistra
// 12
// 75
// Kozuv
// 15
// 89
// Kozjak
// 2
// 15

// Result
// Ime: Kozuv Turi: 15 Clenovi: 164
// Najmnogu clenovi ima planinarskoto drustvo: Ime: Kozuv Turi: 15 Clenovi: 89
#include <iostream>
#include <string.h>
#include <cstring>
using namespace std;

class PlDrustvo{
private:
    char *ime;
    int pominatituri;
    int brojclenovi;
public:
    PlDrustvo operator +(PlDrustvo &P){

        if(this->brojclenovi>P.brojclenovi){
                PlDrustvo dr(this->ime,this->pominatituri,this->brojclenovi+P.brojclenovi);
                return dr;
            }
        PlDrustvo dr(P.ime,P.pominatituri,this->brojclenovi+P.brojclenovi);
        return dr;

    }
    
    bool operator >(PlDrustvo &P){
        return this->brojclenovi > P.brojclenovi;
    }

    bool operator <(PlDrustvo &P){
        return this->brojclenovi < P.brojclenovi;
    }

    friend ostream &operator <<(ostream &OS, PlDrustvo&P){
        OS<<"Ime: "<<P.ime<<" Turi: "<< P.pominatituri<<" Clenovi: "<<P.brojclenovi<<endl;
        return OS;
    }

    PlDrustvo(){
        this->brojclenovi=0;
        this->pominatituri=0;
        this->ime=nullptr;
    }

    PlDrustvo(char *ime,int brTuri,int brClenovi){
        this->ime=new char[strlen(ime)+1];
        strcpy(this->ime,ime);
        this->brojclenovi=brClenovi;
        this->pominatituri=brTuri;
    }
};

void najmnoguClenovi(PlDrustvo *niza, int broj){
        PlDrustvo maks;
        int maksindex=0;
        for(int i=0;i<broj;i++){
            if(niza[i]>maks){
                maksindex=i;
                maks=niza[i];
            }
        }

        cout<<"Najmnogu clenovi ima planinarskoto drustvo: "<<maks<<endl;
    }

int main()
{        		
    PlDrustvo drustva[3];
    PlDrustvo pl;
    for (int i=0;i<3;i++)
   	{
    	char ime[100];
    	int brTuri;
    	int brClenovi;
    	cin>>ime;
    	cin>>brTuri;
    	cin>>brClenovi;
    	PlDrustvo p(ime,brTuri,brClenovi);
        drustva[i] = p;
    	
   	}
    
    pl = drustva[0] + drustva[1];
    cout<<pl;
    
    najmnoguClenovi(drustva, 3);
    
    return 0;
}
