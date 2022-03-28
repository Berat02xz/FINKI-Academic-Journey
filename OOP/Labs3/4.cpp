// Во оваа задача е потребно да уредите даден дом со маси. 
// Креирајте класа Masa со следниве атрибути:

// должина (целобројна вредност)
// ширина (целобројна вредност)
// конструктор со и без параметри и метода pecati().

// Креирајте класа Soba која содржи:

// маса (објект од класата Маса)
// должина на собата (целобројна вредност)
// ширина на собата (целобројна вредност)
// конструктор со и без параметри и метода pecati() во која се повикува и pecati() за објектот Masa.

// Креирајте класа Kukja со атрибути:

// соба (објект од класата Soba)
// адреса (низа од 50 знаци), и соодветни методи.
// конструктор со и без параметри, деструктор и метода pecati() во која се повикува и pecati() за објектот Soba.

// For example:

// Input	
// 2
// 1 2 10 5 Dame_Gruev_93b
// 1 3 12 3 Petta_Avenija_6

//Result
// Adresa: Dame_Gruev_93b Soba: 10 5 Masa: 1 2
// Adresa: Petta_Avenija_6 Soba: 12 3 Masa: 1

#include <iostream>
#include <cstring>
using namespace std;

class Masa
{
private:
    int dolzina;
    int sirina;

public:
    Masa()
    {
        this->dolzina = 0;
        this->sirina = 0;
    }

    ~Masa() {}

    Masa(int sirina, int dolzina)
    {
        this->dolzina = dolzina;
        this->sirina = sirina;
    }

    void pecati()
    {
        cout << " Masa: " << this->sirina << " " << this->dolzina << endl;
    }
};

class Soba
{
private:
    Masa masa;
    int dolzina;
    int sirina;

public:
    Soba()
    {
        this->dolzina = 0;
        this->sirina = 0;
    }
    Soba(int sirina, int dolzina, Masa m)
    {
        this->sirina = sirina;
        this->dolzina = dolzina;
        this->masa = m;
    }
    ~Soba() {}

    void pecati()
    {
        cout<< " Soba: " << this->sirina << " " << this->dolzina; masa.pecati();
    }
};

class Kukja
{
private:
    Soba soba;
    char adresa[50];

public:
    Kukja()
    {
        strcpy(this->adresa, "");
    }
    ~Kukja() {}
    Kukja(Soba &s, char *adresa)
    {
        strcpy(this->adresa, adresa);
        this->soba = s;
    }

    void pecati()
    {
        cout << "Adresa: " << this->adresa; soba.pecati();
    }
};

//ne smee da se menuva main funkcijata!
int main(){
    int n;
    cin>>n;
    for(int i=0;i<n;i++){
    	int masaSirina,masaDolzina;
        cin>>masaSirina;
        cin>>masaDolzina;
    	Masa m(masaSirina,masaDolzina);
    	int sobaSirina,sobaDolzina;
        cin>>sobaSirina;
        cin>>sobaDolzina;
    	Soba s(sobaSirina,sobaDolzina,m);
    	char adresa[30];
        cin>>adresa;
    	Kukja k(s,adresa);
    	k.pecati();
	}
    
    return 0;
}