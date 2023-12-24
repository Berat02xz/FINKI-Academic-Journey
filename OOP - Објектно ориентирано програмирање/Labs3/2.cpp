// Да се дефинира класа Potpisuvac во која се чуваат информации за:

// име (низа од максимум 20 знаци)
// презиме (низа од максимум 20 знаци)
// ЕМБГ (низа од максимум 14 знаци)
// За класата да се дефинира copy конструктор, default конструктор и конструктор со аргументи.

// Да се дефинира класа Dogovor, во која се чуваат информации за:

// број на договор (int),
// категорија на договор (низа од 50 знаци),
// поле од 3 потпишувачи на договорот (објекти од класата Potpisuvac)
// Во класата да се додаде метод кој ќе проверува дали постојат два исти потпишувачи (имаат ист ЕМБГ).

// For example:

// Input	
// 2
// 0101988450001 Alek Aleksov
// 0101988450001 Alek Aleksov
// 0202987440022 Marko Markov
// 1 Osiguruvanje
// 0101988450001 Alek Aleksov
// 0101988450011 Tome Tomov
// 0202987440022 Marko Markov
// 2 Kreditiranje

// Result
// Dogovor 1:
// Postojat potpishuvaci so ist EMBG
// Dogovor 2:
// Ne postojat potpishuvaci so ist EMBG

#include <iostream>
#include <cstring>

using namespace std;

class Potpisuvac
{
    // vasiot kod ovde
private:
    char ime[20];
    char prezime[20];
    char EMBG[14];

public:
    char *getEMBG(){
     return this->EMBG;    
    }
    Potpisuvac()
    {
        strcpy(this->ime,"");
        strcpy(this->prezime,"");
        strcpy(this->EMBG,"");
    }
    Potpisuvac(char *ime, char *prezime, char *EMBG)
    {
        strcpy(this->ime,ime);
        strcpy(this->prezime,prezime);
        strcpy(this->EMBG,EMBG);
    }
    Potpisuvac(Potpisuvac &P){
        strcpy(this->ime,P.ime);
        strcpy(this->prezime,P.prezime);
        strcpy(this->EMBG,P.EMBG);
    }
    ~Potpisuvac(){}
};
class Dogovor
{
    // vasiot kod ovde
private:
    int brojdogovor;
    char kategorija[50];
    Potpisuvac Potpisuvaci[3];

public:
    Dogovor(int broj, char *kategorija, Potpisuvac *p){
        this->brojdogovor=brojdogovor;
        strcpy(this->kategorija,kategorija);

        for(int i=0;i<=3;i++){
        this->Potpisuvaci[i]=p[i];
        }
    }
    ~Dogovor(){}
    bool proverka()
    {
        for(int i=0;i<=3;i++){
            if( strcmp(Potpisuvaci[i].getEMBG(),Potpisuvaci[i+1].getEMBG()) ){ 
                return false;
            } else {
                return true;
            }
        }
    }
};

//ne smee da se menuva main funkcijata
int main()
{
    char embg[14], ime[20], prezime[20], kategorija[20];
    int broj, n;
    cin >> n;
    for(int i = 0; i < n; i++){
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p1(ime, prezime, embg);
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p2(ime, prezime, embg);
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p3(ime, prezime, embg);
    	cin >> broj >> kategorija;
    	Potpisuvac p[3];
    	p[0] = p1; p[1] = p2; p[2] = p3;
    	Dogovor d(broj, kategorija, p);
        cout << "Dogovor " << broj << ":" << endl; 
    	if(d.proverka() == true)
    	    cout << "Postojat potpishuvaci so ist EMBG" << endl;
    	else
    	    cout << "Ne postojat potpishuvaci so ist EMBG" << endl;
    }
    return 0;
}