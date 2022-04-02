// Да се дефинира класа ParkingPlac во која се чуваат податоци за:

// Адреса на плацот (низа од знаци, максимум 20),
// Идентификациски број на плацот (динамичка низа од знаци),
// Цена за паркирање за 1 час (цел број),
// Заработка на паркинг плацот (цел број)
// параметри потребни за дополнителниот дел од задачата:

// Динамичка низа од класата Avtomobil
// Број на паркирани автомобили во даден момент
// За потребите на класата да се имплементираат :

// соодветни конструктори и деструктор, и потребните get-функции (погледнете ги повиците во main функцијата)
// функција pecati() за печатење на податоците за еден паркинг плац во следниот формат:

// идентификациски_број адреса - X denari, ако X е заработката на паркинг плацот X не е 0

// идентификациски_број адреса , ако X =0

// функција platiCasovi(int casovi) која на заработката ја додава платената вредност за дадениот број на часови.

// функција daliIstaAdresa(ParkingPlac p) во која се споредува дали дадениот паркинг плац е на иста адреса како паркинг плацот p.
// Во дадената main функција прво се внесуваат n паркинг плацеви. Потоа се внесуваат m плаќања и на крај се печатат сите паркинг плацеви од низата кои имаат иста адреса со дадениот паркинг плац (објектот pCentar).

// ДОПОЛНИТЕЛНО БАРАЊЕ

// Дефинирајте класа Avtomobilво која се чуваат податоците за:

// Бојата на автомобилот (низа од знаци, максимум 20)
// Брендот на автомобилот (низа од знаци, максимум 20)
// Моделот на автомобилот (низа од знаци, максимум 20)
// За потребите на класата ќе треба да :

// Се имплементира конструктор
// Да го преоптоварите assignment operator-от
// Понатака да пишете метод за класата ParkingPlac наречен parkirajVozilo(Avtomobil novoVozilo), неговата цел ќе биде да се паркира ново возило на паркингот во кој се повикува методот.

// На крај ќе треба да се испечатат паркираните возила во еден паркинг преку методот pecatiParkiraniVozila() кој исто така треба да биде имплементиран во класата ParkingPlac.

// Ориентирајте се според дадениот код во main методот за како се ова треба да го имплементирате.

// For example:

// Input	
// 3
// Cvetan Dimov
// C12 100
// Partizanska
// A05 50
// Cvetan Dimov
// A04 50
// 3
// C12 1
// A07 2
// A04 3

// Result
// Ne e platen parking. Greshen ID.
// =========
// C12 Cvetan Dimov - 100 denari
// A04 Cvetan Dimov 

#include<iostream>
#include <string.h>
using namespace std;

// вашиот код
class Avtomobil
{
private:
    char boja[20];
    char Brand[20];
    char Model[20];

public:
    Avtomobil(char boja[20] = "", char Brand[20] = "", char Model[20] = "")
    {
        strcpy(this->boja, boja);
        strcpy(this->Brand, Brand);
        strcpy(this->Model, Model);
    }

    Avtomobil &operator=(const Avtomobil &A)
    {
        if (this != &A)
        {
            strcpy(this->boja, A.boja);
            strcpy(this->Brand, A.Brand);
            strcpy(this->Model, A.Model);
            return *this;
        }
        return *this;
    }

    void print(){
			cout<<this->boja<<" "<<this->Brand<< " "<<this->Model<<endl;
		}


    
};

class ParkingPlac
{
private:
    char Adresa[20];
    char *ID;
    int cena;
    int zarabotka;

    Avtomobil *A;
    int parkirani;

public:
    ParkingPlac(char Adresa[20] = "", char *ID = "", int cena = 0)
    {
        strcpy(this->Adresa, Adresa);
        this->ID = new char[strlen(ID) + 1];
        strcpy(this->ID, ID);
        this->cena = cena;
        this->zarabotka = 0;
    }

    void pecati(){
        if(this->cena >= 0){
        cout<<this->ID<<" "<<this->Adresa<<" - "<<this->zarabotka<<" denari"<<endl;
        } else {
            cout<<this->ID<<" "<<this->Adresa<<endl;
        }
    }

    void platiCasovi(int casovi){
        this->zarabotka+=casovi*this->cena;
    }

    char *GetAdresa(){
    return this->Adresa;
    }

    char *getId(){
     return this->ID;
    }

    bool daliIstaAdresa(ParkingPlac p)
    {
        return !strcmp(this->Adresa, p.GetAdresa() );
    }

//narednoto e kopirano od DANI (github) bidejki 2 saat e i mi se spie
    void parkirajVozilo(Avtomobil novoVozilo){
			Avtomobil *k = new Avtomobil[this->parkirani+1];
			for(int i=0;i<parkirani;i++){
				k[i]=this->A[i];
			}
			k[this->parkirani]=novoVozilo;
			this->A=k;
			this->parkirani++;
		}
		void pecatiParkiraniVozila(){
			cout<<"Vo parkingot se parkirani slednite vozila:"<<endl;
			for(int i=0;i<parkirani;i++){
				cout<<i+1<<".";
				this->A[i].print();
			}
		}
    // Thanks Dani
};

int main(){

	ParkingPlac p[100];
	int n,m;
	char adresa[50],id[50];
	int brojcasovi,cenacas;
	cin>>n;
	if(n > 0){


		for (int i=0;i<n;i++){
	        cin.get();
			cin.getline(adresa,50);
			cin>>id>>cenacas;
			
			ParkingPlac edna(adresa,id,cenacas);
	        
	        p[i]=edna;
		}
	    
		//plakjanje
		cin>>m;
		for (int i=0;i<m;i++){

			cin>>id>>brojcasovi;
	        
	        int findId=false;
	        for (int j=0;j<m;j++){
	            if (strcmp(p[j].getId(),id)==0){
	                p[j].platiCasovi(brojcasovi);
	                findId=true;
	            }
	        }
			if (!findId)
	        cout<<"Ne e platen parking. Greshen ID."<<endl;
		}

	    cout<<"========="<<endl;
	    ParkingPlac pCentar("Cvetan Dimov","C10",80);
		for (int i=0;i<n;i++)
	        if (p[i].daliIstaAdresa(pCentar))
	            p[i].pecati();
	} else {

		ParkingPlac najdobarPlac("Mars", "1337", 1);
	    int brVozila;
	    cin >> brVozila;
	    for(int i = 0; i < brVozila; ++i){

	    	char boja[20];
	    	char brend[20];
	    	char model[20];

	    	cin >> boja >> brend >> model;
	    	Avtomobil novAvtomobil(boja, brend, model);
	    	najdobarPlac.parkirajVozilo(novAvtomobil);
	    }
	    if(brVozila != 0)
	    najdobarPlac.pecatiParkiraniVozila();

	}  
}