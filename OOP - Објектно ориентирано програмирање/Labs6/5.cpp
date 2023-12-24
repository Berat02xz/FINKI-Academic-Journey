// Да се дефинира класа Ekipa за коjа се чуваат следниве информации:

// името на екипата (низа од наjмногу 15 знаци)
// броj на порази
// броj на победи
// За оваа класа да се дефинира метод pecati() коjа ги печати податоците за екипаta. Од оваа класа да се изведe новa класa, FudbalskaEkipa.

// За фудбалската екипа дополнително се чуваат информации за:

// вкупниот броj на црвени картони
// вкупниот броj жолти картони
// броjот на нерешени натпревари
// За фудбалската екипа да се преоптовари методот pecati(), така што покрај останатите информации, ќе се испечатат и бројот на нерешени резултати и вкупен број на поени во формат: Име на екипа, броj на победи, броj на порази, броj на нерешени натпревари и вкупен броj на поени (за победа фудбалската екипа добива 3 поени, додека за нерешен резултата, 1 поен);

// For example:

// Input	
// Arsenal
// 4
// 6
// 1
// 2
// 5

// Result
// Ime: Arsenal Pobedi: 4 Porazi: 6 Nereseni: 5 Poeni: 17

//Vasiot kod ovde
#include <iostream>
#include <cstring>
#include <string>
#include <cmath>
using namespace std;

class Ekipa
{
private:
	char ime[15];
	int porazi;
	int pobedi;

public:
	Ekipa(char *ime, int porazi, int pobedi){
		strcpy(this->ime,ime);
		this->pobedi=pobedi;
		this->porazi=porazi;
	}

	void pecati()
	{
		cout << "Ime: " << this->ime << " Pobedi: " << this->pobedi << " Porazi: " << this->porazi; //<<" Nereseni: "<<5<<" Poeni: "<<17;
	}

	char *getime(){
		return this->ime;
	}

	int getporazi(){
		return this->porazi;
	}

	int getpobedi(){
		return this->pobedi;
	}
};

class FudbalskaEkipa : public Ekipa
{
private:
	int crveni;
	int zolti;

	int ne_reseni_natprevari;
	int reseni_natprevari;

public:
	FudbalskaEkipa(char *ime, int pob, int por,int ck, int zk, int ner) : Ekipa(ime, por, pob){
		this->crveni=ck;
		this->ne_reseni_natprevari=ner;
		this->reseni_natprevari=0;
		this->zolti=zk;
	}

	//вкупен броj на поени (за победа фудбалската екипа добива 3 поени, додека за нерешен резултата, 1 поен);
	void pecati()
	{	
		char ime[15];
		strcpy(ime,Ekipa::getime());
		int porazi=Ekipa::getporazi();
		int pobedi=Ekipa::getpobedi();
		cout << "Ime: " <<ime << " Pobedi: " <<pobedi << " Porazi: " <<porazi << " Nereseni: " << this->ne_reseni_natprevari << " Poeni: " <<pobedi*3+this->ne_reseni_natprevari;
	}
};

int main()
{
	char ime[15];
	int pob, por, ck, zk, ner;
	cin >> ime >> pob >> por >> ck >> zk >> ner;
	FudbalskaEkipa f1(ime, pob, por, ck, zk, ner);
	f1.pecati();
	return 0;
}