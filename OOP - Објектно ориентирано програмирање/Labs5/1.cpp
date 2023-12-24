// Да се напише класа Automobile во која се чуваат информации за марката на автомобилот 
// (динамички алоцирана низа од знаци), регистрација (динамички алоцирана низа од 5 цели броја) и 
// максимална брзина (цел број). За класата да се обезбедат set и get методите што се користат и 
// да се преоптоварат следните оператори:

// оператор == за споредување на два автомобила според регистрацијата

// оператор << за печатење на податоци на автомобил во формат Marka:име Registracija:[x y z k l]

// Да се напише класа RentACar за агенција за измајмување возила во која се чуваат информација 
// за името на агенцијата (низа од 100 знци), низа од автомобили (динамички алоациана низа од објекти 
// од класата Automobile) и број на автомобили со кој располага (цел број). Во класата RentACar да се 
// напише конструктор со еден аргумент за иницијализација на името на агенцијата. При секое додавање 
// на нов автомобил, динамички алоцираната низа да го зголемува капацитетот за 1 елемент. Во оваа класа 
// да се преоптоварат операторите:

// += за додавање на нов автомобил во агенцијата и

// -= за отстранување на даден автомобил од агенцијата (оној со иста регистрација).

// Да се напише main функција во која се инстанцира објект од класата RentACar. Во овој објект да се 
// додадат сите автомобили чии информации се читаат од тастатура со операторот +=. Меѓутоа, откриено е 
// дека во внесувањето на податоците има грешка затоа што при обид да се додаде нов автомобил во агенцијата, 
// увидено е дека таа регистрација веќе постои. Во последниот ред од влезот дадени се инфромации тој автомобил. 
// Потребно е да се избрише автомобилот што претходно е погрешно внесен и да се додаде новиот.

// На излез да се отпечатат името на агенцијата и листа на автомобили што таа ги изнајмува, а чија максимална 
// брзина е поголема од 150. Последново да се направи со функција pecatiNadBrzina(int max) што треба да се дефинира во класата RentACar.

// For example:

// Input	Result
// 3
// Opel 4 5 6 7 4 200
// Toyota 2 3 4 1 2 120
// Audi 1 2 1 1 1 130
// Suzuki 1 2 1 1 1 100
// FINKI-Car

// Result
// Marka	Opel	Registracija[ 4 5 6 7 4 ]

#include<iostream>
#include<cmath>
#include<cstring>
using namespace std;

class Automobile{
private:
    char *marka;
    int *registracija;
    int maksimalna_brzina;
    
public:
     Automobile(){
            this->registracija = new int[5];
            this->maksimalna_brzina=0;
        }

    Automobile(char *marka, int *registracija, int maksimalna_brzina){
            this->marka = new char[strlen(marka)+1];
            strcpy(this->marka,marka);
            this->registracija = new int[5];
            for(int i=0;i<5;i++){
                this->registracija[i]=registracija[i];
            }
            this->maksimalna_brzina = maksimalna_brzina;
        }

    //Vrakja Bool konstanta
    bool operator==(Automobile &A){
        for(int i=0;i<5;i++){
            if(this->registracija!=A.registracija) return false;
        }
        return true;

    }

    //Vrakja Ostream (output) - (mora da bide friend)
    friend ostream &operator<<(ostream &O, Automobile &A){
        O<<"Marka\t"<<A.marka<<"\tRegistracija[ ";
            for(int i=0;i<5;i++){
                O<<A.registracija[i]<<" ";
            }
            O<<"]"<<endl;
            return O;
    }

    int getBrzina(){return maksimalna_brzina;}

};



class RentACar{
private:
    char ime_agencija[100];
    Automobile *Vozila;
    int broj_auto;
public:
    RentACar(char *ime_agencija){
        strcpy(this->ime_agencija,ime_agencija);
        this->broj_auto=0;
    }

    RentACar &operator += (Automobile &A){
        //Kreirame Temporary niza od vozila
       Automobile *temp= new Automobile[broj_auto+1];

        //stavame site vozila vo temp
       for(int i=0;i<broj_auto;i++){
        temp[i]=Vozila[i];
       }

        //pravime delete na segasnata niza
       if(broj_auto){ delete [] Vozila; }

        //stavame na temporary novoto vozilo
        temp[broj_auto]=A;

        //vrakjame novata niza vo aktuelna glavna niza
        broj_auto++;
        Vozila=temp;

        return *this;
    }

    RentACar &operator -= (Automobile &A){
        int deleteindex = broj_auto;

        //barame kade vo nizata ima isto kolo kako A, i stavame flag tamu
        for(int i=0;i<broj_auto;i++){
            if(Vozila[i]==A) { deleteindex = i;}
        }
        
        //gi pomestuvame site za a[i]=a[i+1] , takada se pravi delete na A
        //i se pomestuvaat site mesto toj empty A
        for(int i=deleteindex;i<broj_auto-1;i++){
            Vozila[i]=Vozila[i+1];
        }

        //minusirame broj_auto i vrakjame RentACar
        broj_auto--;
        return *this;
    }

        void pecatiNadBrzina(int max){
            cout<<ime_agencija<<endl;
            for(int i=0;i<broj_auto;i++){
                if(Vozila[i].getBrzina()>max) cout<<Vozila[i];
            }
        }

    
};

int main()
{
   RentACar agencija("FINKI-Car");
   int n;
   cin>>n;
    
   for (int i=0;i<n;i++)
   {
    	char marka[100];
    	int regisracija[5];
    	int maximumBrzina;
    
       	cin>>marka;
    
       	for (int i=0;i<5;i++)
    		cin>>regisracija[i];
    
    	cin>>maximumBrzina;
    
       	Automobile nov=Automobile(marka,regisracija,maximumBrzina);
       
    	//dodavanje na avtomobil
       	agencija+=nov;   
       
   }
    //se cita grehsniot avtmobil, za koj shto avtmobilot so ista registracija treba da se izbrishe
    char marka[100];
    int regisracija[5];
    int maximumBrzina;
    cin>>marka;
    for (int i=0;i<5;i++)
    cin>>regisracija[i];
    cin>>maximumBrzina;
    
    Automobile greshka=Automobile(marka,regisracija,maximumBrzina);
    
    //brishenje na avtomobil
    agencija-=greshka;
    
    agencija.pecatiNadBrzina(150);
    
    return 0;
}