// Со цел да се подобри системот Мој Термин, со воведување функционалност за пресметување плати за лекарите за еден месец, од Министерството за здравство на Република Македонија, ги добивате следните задачи:

// Да се креира класа Lekar во која што ќе се чуваат:

// факсимил на докторот (цел број)
// име (низа од максимум 10 знаци)
// презиме (низа од максимум 10 знаци)
// почетна плата (децимален број)
// За класата да се имплементираат методите:

// void pecati(): Печати информации за лекарот во формат Факсимил: име презиме

// double plata(): ја враќа платата на лекарот

// Да се креира класа MaticenLekar која што наследува од Lekar и во неа се чуваат дополнителни информации за:

// број на пациенти со којшто лекарот соработувал во текот на месецот (цел број)
// котизации наплатени од пациентите во текот на месецот (динамички алоцирана низа од децимални броеви)
// За класата да се препокријат методитe:

// void pecati() : ги печати основните информации за лекарот, а во нов ред го печати и просекот од наплатените котизации

// double plata(): ја враќа платата на матичниот лекар

// Платата на матичниот лекар се пресметува со зголемување на основната плата за 30% од просекот од наплатените котизации за месецот
// For example:

// Input	
// 7
// 766433 Cvetanka Cvetkova 27899.90
// 123122 Stefan Stefanov 31789.50
// 454323 Trajce Trajkov 19458.30
// 343989 Goran Trajkov 28945.10
// 515788 Nikola Nikolov 36985.50
// 784512 Viktorija Stojanovska 37855.00
// 985623 Ivana Ivanova 38745.70
// 5
// 1000 2000 2500 7800 5560
// 4
// 1000 2000 3000 10000
// 6
// 7800 7800 8000 9000 900 1000
// 5
// 1000 1500 2000 2300 2400
// 3
// 15000 10000 7580
// 4
// 10000 7000 8000 1000
// 3
// 1000 2000 3000 
// 1
// 1

// Result
// ===TESTIRANJE NA KLASATA LEKAR===
// 766433: Cvetanka Cvetkova
// Osnovnata plata na gorenavedeniot lekar e: 27899.9
// 123122: Stefan Stefanov
// Osnovnata plata na gorenavedeniot lekar e: 31789.5
// 454323: Trajce Trajkov
// Osnovnata plata na gorenavedeniot lekar e: 19458.3
// 343989: Goran Trajkov
// Osnovnata plata na gorenavedeniot lekar e: 28945.1
// 515788: Nikola Nikolov
// Osnovnata plata na gorenavedeniot lekar e: 36985.5
// 784512: Viktorija Stojanovska
// Osnovnata plata na gorenavedeniot lekar e: 37855
// 985623: Ivana Ivanova
// Osnovnata plata na gorenavedeniot lekar e: 38745.7

#include <iostream>

#include <cstring>
using namespace std;

//vasiot kod pocnuva od tuka

class Lekar
{
protected:
	int faksimil;
	char ime[15];
	char prezime[15];
	double poc_plata;

public:
	Lekar(int faksimil = 0, char *ime = "", char *prezime = "", double osnovnaPlata = 0)
	{
		this->faksimil = faksimil;
		strcpy(this->ime, ime);
		strcpy(this->prezime, prezime);
		this->poc_plata = osnovnaPlata;
	}

	Lekar(Lekar& L){
		this->faksimil=L.faksimil;
		strcpy(this->ime,L.ime);
		strcpy(this->prezime,L.prezime);
		this->poc_plata=L.poc_plata;
	}

	void pecati()
	{
		cout << faksimil<<":  "<< ime << " " << prezime;
	}

	double plata()
	{
		return poc_plata;
	}

};






class MaticenLekar : public Lekar
{
private:
	int broj_pacienti;
	double *naplateni_pacienti;

public:
	MaticenLekar() : Lekar() {
		broj_pacienti=0;
		naplateni_pacienti=new double[0];
	}

	MaticenLekar(Lekar &L,int pacienti,double *kotizacii) : Lekar(L) {
		this->broj_pacienti=pacienti;
		this->naplateni_pacienti=new double[broj_pacienti];
		for(int i=0;i<broj_pacienti;i++){
			this->naplateni_pacienti[i]=kotizacii[i];
		}
	}
	
	MaticenLekar(int faksimil, char *ime,  char *prezime,double osnovnaPlata,int broj, double *kotizacii) : Lekar(faksimil,ime,prezime,osnovnaPlata) {
		this->broj_pacienti=broj;
		this->naplateni_pacienti=new double[broj_pacienti];
		for(int i=0;i<broj_pacienti;i++){
			this->naplateni_pacienti[i]=kotizacii[i];
		}
	}

	MaticenLekar( MaticenLekar&M ){
		this->broj_pacienti=M.broj_pacienti;
		this->naplateni_pacienti=new double[broj_pacienti]; 
		for(int i=0;i<broj_pacienti;i++){
			this->naplateni_pacienti[i]=M.naplateni_pacienti[i];
		}

		this->faksimil=M.faksimil;
		this->poc_plata=M.poc_plata;
		strcpy(this->ime,M.ime);
		strcpy(this->prezime,M.prezime);

	}

	void pecati()
	{
		Lekar::pecati();
		
		double prosek=0;
		for(int i=0;i<broj_pacienti;i++){
			prosek+=naplateni_pacienti[i];
		}
		prosek=broj_pacienti / prosek;

		cout << prosek << endl;
		//а во нов ред го печати и просекот од  котизации
	}

	double plata()
	{
		double prosek=0;
		for(int i=0;i<broj_pacienti;i++){
			prosek+=naplateni_pacienti[i];
		}
		prosek=broj_pacienti / prosek;

		return poc_plata + (0.3 * prosek);
	}
	~MaticenLekar(){ delete [] naplateni_pacienti; }
};

int main() {
	int n;
	cin>>n;
	int pacienti;
	double kotizacii[100];
	int faksimil;
	char ime [20];
	char prezime [20];
	double osnovnaPlata;
	
	Lekar * lekari = new Lekar [n];
	MaticenLekar * maticni = new MaticenLekar [n];
	
	for (int i=0;i<n;i++){
		cin >> faksimil >> ime >> prezime >> osnovnaPlata;
		lekari[i] = Lekar(faksimil,ime,prezime,osnovnaPlata);		
	}
	
	for (int i=0;i<n;i++){
		cin >> pacienti;
		for (int j=0;j<pacienti;j++){
			cin >> kotizacii[j];
		}
		maticni[i]=MaticenLekar(lekari[i],pacienti,kotizacii);
	}
	
	int testCase;
	cin>>testCase;
	
	if (testCase==1){
		cout<<"===TESTIRANJE NA KLASATA LEKAR==="<<endl;
		for (int i=0;i<n;i++){
			lekari[i].pecati();
			cout<<"Osnovnata plata na gorenavedeniot lekar e: "<<lekari[i].plata()<<endl;
		}
	}
	else {
		cout<<"===TESTIRANJE NA KLASATA MATICENLEKAR==="<<endl;
		for (int i=0;i<n;i++){
			maticni[i].pecati();
			cout<<"Platata na gorenavedeniot maticen lekar e: "<<maticni[i].plata()<<endl;
		}
	}
	
	delete [] lekari;
	delete [] maticni;
	
	return 0;
}