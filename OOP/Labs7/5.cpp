// Да се креира хиерархиjа на класи за репрезентациjа на жичани инструменти. За потребите на оваа хиерархиjа да се дефинира класа ZicanInstrument од коjа ќе бидат изведени двете класи Mandolina и Violina.

// Во класата ZicanInstrument се чуваат податоци за:

// името на инструментот (низа од 20 знаци)
// броjот на жици
// основната цена на инструментот.
// За класата Mandolina дополнително се чува неjзината форма (низа од 20 знаци).

// За класата Violina дополнително се чува неjзината големина (децимален броj).

// За секоjа изведените класи треба да се дефинираат соодветните конструктори и следните методи:

// price() за пресметување на цената на инструментот
// основната цена на мандолината се зголемува за 15% доколку таа има форма во Неаполитански стил (вредноста на променливата форма е “Neapolitan”)
// основната цена на виолината се зголемува за 10% ако неjзината големина има вредност 1/4 (0.25), односно за 20% ако неjзината големина има вредност 1 (1.00)
// проптоварување на операторот ==, коj ги споредува жичаните инструменти според броjот на жици што го имаат
// преоптоварување на операторот << за печатење на сите податоци за жичаните инструменти.
// Да се напише функциjа pecatiInstrumenti(ZicanInstrument &zi, ZicanInstrument **i, int n) коjа на влез прима жичан инструмент, низа од покажувачи кон жичани инструменти и броj на елементи во низата. Функциjата jа печати цената на сите жичани инструменти од низата кои имаат ист броj на жици како и инструментот проследен како прв аргумент на функциjата.

// For example:

// Input	
// Mandolina_1 10 5000 Nepoznata
// 2
// Mandolina_2 5 3000 Prava
// Mandolina_3 10 8000 Neapolitan
// Violina_1 10 4000 0.25
// Violina_2 10 6000 0.8

// Result
// 9200
// 4400
// 6000

//Vasiot kod ovde:
#include <iostream>
#include <cstring>
using namespace std;

class ZicanInstrument
{
protected:
    char ime[20];
    int broj_zici;
    double price;

public:
    ZicanInstrument(char *ime = "", int broj_zici = 0, double price = 0)
    {
        strcpy(this->ime, ime);
        this->broj_zici = broj_zici;
        this->price = price;
    }
    virtual double cena() = 0;

    friend bool operator==(ZicanInstrument *Z, ZicanInstrument &N)
    {
        if (N.broj_zici == Z->broj_zici)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    friend ostream &operator<<(ostream &O, ZicanInstrument &Z)
    {
        O << Z.ime << " " << Z.broj_zici << " " << Z.price << endl;
    }
};

class Mandolina : public ZicanInstrument
{
protected:
    char forma[20];

public:
    Mandolina(char *ime = "", int broj_zici = 0, double price = 0, char *forma = "") : ZicanInstrument(ime, broj_zici, price)
    {
        strcpy(this->forma, forma);
    }
    double cena()
    {
        int compare = strcmp(this->forma, "Neapolitan");
        if (compare)
            return (price * 1.15);
        return price;
    }

    friend ostream &operator<<(ostream &O, Mandolina &Z)
    {
        O << Z.ime << " " << Z.broj_zici << " " << Z.price << "" << Z.forma << endl;
    }
};

class Violina : public ZicanInstrument
{
protected:
    double golemina;

public:
    Violina(char *ime = "", int broj_zici = 0, double price = 0, double golemina = 0) : ZicanInstrument(ime, broj_zici, price)
    {
        this->golemina = golemina;
    }

    double cena()
    {
        if (golemina == 0.25)
            return price * 1.1;
        if (golemina == 1.0)
            return price * 1.2;
        return price;
    }

    friend ostream &operator<<(ostream &O, Violina &Z)
    {
        O << Z.ime << " " << Z.broj_zici << " " << Z.price << "" << Z.golemina << endl;
    }
};

void pecatiInstrumenti(ZicanInstrument &zi, ZicanInstrument **niza, int n)
{
    for (int i = 0; i < n; i++)
    {
        if (niza[i] == zi)
        {
            Mandolina *m = dynamic_cast<Mandolina *>(niza[i]);
            if (m != 0)
            {
                cout << m->cena() << endl;
            }

            Violina *v = dynamic_cast<Violina *>(niza[i]);
            if (v != 0)
            {
                cout << v->cena() << endl;
            }
        }
    }
}

int main() {
	char ime[20];
	int brojZici;
	float price;
	char forma[20];
	cin >> ime >> brojZici >> price >> forma;
	Mandolina m(ime, brojZici, price, forma);
	int n;
	cin >> n;
	ZicanInstrument **zi = new ZicanInstrument*[2 * n];
	for(int i = 0; i < n; ++i) {
		cin >> ime >> brojZici >> price >> forma;
		zi[i] = new Mandolina(ime, brojZici, price, forma);
	}
	for(int i = 0; i < n; ++i) {
		float golemina;
		cin >> ime >> brojZici >> price >> golemina;
		zi[n + i] = new Violina(ime, brojZici, price, golemina);
	}
	pecatiInstrumenti(m, zi, 2 * n);
	for(int i = 0; i < 2 * n; ++i) {
		delete zi[i];
	}
	delete [] zi;
	return 0;
}