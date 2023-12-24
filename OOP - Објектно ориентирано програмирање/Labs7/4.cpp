// Да се дефинира класа Vozilo која ќе содржи информација за неговата маса (децимален број), ширина и висина (цели броеви).

// Од оваа класа да се изведе класата Автомобил во која како дополнителна информација се чува информацијата за бројот на врати (цел број).

// Од класата возило да се изведе и класата Автобус во која се чуваат информации и за бројот на патници кои може да ги пренесува.

// Од класата возило да се изведе класата Камион во која се чуваат информации и за максималната маса која може да се товари на него (децимална вредност).

// За сите класи да се креираат погодни контруктори, како и set и get функции.

// Да се дефинира класа ParkingPlac за која се чува динамичко алоцирано поле од покажувачи кон Vozilo, како и бројот на елементи во полето. Во оваа класа да се дефинираат:

// конструктор
// деструктор
// операторот += за додавање на ново возило (аргументот е покажувач кон Vozilo)
// функција float presmetajVkupnaMasa() со која се пресметува вкупната маса на сите возила во паркинг плацот
// функција int brojVozilaPoshirokiOd(int l) со која се пресметува бројот на возила кои се пошироки од дадената вредност
// функција void pecati() со која се печати: Brojot na avtomobili e X, brojot na avtobusi e Y i brojot na kamioni e Z.
// функција int pogolemaNosivostOd(Vozilo& v) во која се враќа бројот на сите камиони кои имаат носивост поголема од масата на возилото предадено како аргумент.
// Да се дефинира виртуелна функција int vratiDnevnaCena() во класата Vozilo и истата да се преоптовари во сите изведени класи. За секој автомобил со помалку од 5 врати дневната цена е 100, а инаку е 130 денари. За секој камион цената се пресметува со формулата: (masa+nosivost)*0.02. За секој автобус цената е 5 денари по лице кое може да се пренесува.

// Во класата ParkingPlac да се додаде следната функција: - функција int vratiDnevnaZarabotka() со која се враќа дневната заработка од сите возила на паркингот.

// For example:

// Input	Result
// 5
// 1 1300 3 2 3
// 2 4500 10 4 45
// 3 6000 8 5 2000
// 1 2100 3 3 5
// 2 4200 9 5 52

//Result
// Brojot na avtomobili e 2, brojot na avtobusi e 2 i brojot na kamioni e 1.

// Zarabotkata e 875
// Vkupnata masa e 18100
// Brojot poshiroki od 5 e 3
// Brojot na kamioni so nosivost pogolema od avtomobilot e 1
// Answer:(penalty regime: 0 %)

#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;

class Vozilo
{
protected:
    double masa;
    int visina;
    int shirina;

public:
    Vozilo(double masa, int shirina, int visina)
    {
        this->masa = masa;
        this->shirina = shirina;
        this->visina = visina;
    }
    virtual int vratiDnevnaCena() = 0;
    double getMasa()
    {
        return masa;
    }
    int getSirina()
    {
        return shirina;
    }
    int getVisina()
    {
        return visina;
    }
};

class Avtomobil : public Vozilo
{
protected:
    int broj_vrati;

public:
    Avtomobil(double masa, int shirina, int visina, int broj_vrati) : Vozilo(masa, shirina, visina)
    {
        this->broj_vrati = broj_vrati;
    }
    int vratiDnevnaCena()
    {
        if (broj_vrati < 5)
            return 100;
        return 130;
    }
};

class Avtobus : public Vozilo
{
protected:
    int patnici;

public:
    Avtobus(double masa, int shirina, int visina, int patnici) : Vozilo(masa, shirina, visina)
    {
        this->patnici = patnici;
    }
    int vratiDnevnaCena()
    {
        return patnici * 5;
    }
};

class Kamion : public Vozilo
{
protected:
    double maks_masa;

public:
    Kamion(double masa, int shirina, int visina, double maks_masa) : Vozilo(masa, shirina, visina)
    {
        this->maks_masa = maks_masa;
    }
    int vratiDnevnaCena()
    {
        return (masa + maks_masa) * 0.02;
    }
    double getMaxMasa()
    {
        return maks_masa;
    }
};

class ParkingPlac
{
protected:
    Vozilo **niza;
    int broj;

public:
    // Konstruktor
    ParkingPlac()
    {
        niza = new Vozilo *[0];
        broj = 0;
    }
    // Destruktor
    ~ParkingPlac() { delete[] niza; }
    // Operator +=
    ParkingPlac &operator+=(Vozilo *V)
    {
        Vozilo **temp = new Vozilo *[broj + 1];
        for (int i = 0; i < broj; i++)
        {
            temp[i] = niza[i];
        }
        temp[broj++] = V;
        delete[] niza;
        niza = temp; // Operator =
        return *this;
    }

    // Operator=
    ParkingPlac &operator=(ParkingPlac &P)
    {
        broj = P.broj;
        niza = new Vozilo *[P.broj];
        for (int i = 0; i < broj; i++)
        {
            niza[i] = P.niza[i];
        }
        return *this;
    }

    // Copy Constructor
    ParkingPlac(ParkingPlac &P)
    {
        broj = P.broj;
        niza = new Vozilo *[P.broj];
        for (int i = 0; i < broj; i++)
        {
            niza[i] = P.niza[i];
        }
    }

    float presmetajVkupnaMasa()
    {
        double masa = 0;
        for (int i = 0; i < broj; i++)
        {
            masa += niza[i]->getMasa();
        }
        return (float)masa;
    }

    int brojVozilaPoshirokiOd(int l)
    {
        int rez = 0;
        for (int i = 0; i < broj; i++)
        {
            if (niza[i]->getSirina() <= l)
            {
                rez++;
            }
        }
        return rez;
    }

    void pecati()
    {
        int X = 0, Y = 0, Z = 0;
        for (int i = 0; i < broj; i++)
        {
            Avtomobil *a = dynamic_cast<Avtomobil *>(niza[i]);
            if (a != 0)
            {
                X++;
            }

            Avtobus *b = dynamic_cast<Avtobus *>(niza[i]);
            if (b != 0)
            {
                Y++;
            }

            Kamion *k = dynamic_cast<Kamion *>(niza[i]);
            if (k != 0)
            {
                Z++;
            }
        }
        cout << "Brojot na avtomobili e " << X << ", brojot na avtobusi e " << Y << " i brojot na kamioni e " << Z << "." << endl;
    }

    int pogolemaNosivostOd(Vozilo &v)
    {
        int rez = 0;
        for (int i = 0; i < broj; i++)
        {
            Kamion *k = dynamic_cast<Kamion *>(niza[i]);
            if (k != 0)
            {
                if (k->getMaxMasa() > v.getMasa())
                {
                    rez++;
                }
            }
        }
        return rez;
    }

    int vratiDnevnaZarabotka()
    {
        int rez = 0;
        for (int i = 0; i < broj; i++)
        {
            rez += niza[i]->vratiDnevnaCena();
        }
        return rez;
    }
};

int main(){
ParkingPlac p;

int n;
cin>>n;
int shirina,visina, broj;
float masa,nosivost;
for (int i=0;i<n;i++){
    int type;
    cin>>type;
    if(type==1){
        cin>>masa>>shirina>>visina>>broj;
        Avtomobil *a=new Avtomobil(masa,shirina,visina,broj);
        p+=a;
    }
    if(type==2){
        cin>>masa>>shirina>>visina>>broj;
        p+=new Avtobus(masa,shirina,visina,broj);
    }
    if(type==3){
        cin>>masa>>shirina>>visina>>nosivost;
        p+=new Kamion(masa,shirina,visina,nosivost);
    }
}

p.pecati();

cout<<"\nZarabotkata e "<<p.vratiDnevnaZarabotka()<<endl;
cout<<"Vkupnata masa e "<<p.presmetajVkupnaMasa()<<endl;
cout<<"Brojot poshiroki od 5 e "<<p.brojVozilaPoshirokiOd(5)<<endl;
Avtomobil a(1200,4,3,5);
cout<<"Brojot na kamioni so nosivost pogolema od avtomobilot e "<<p.pogolemaNosivostOd(a)<<endl;


}
