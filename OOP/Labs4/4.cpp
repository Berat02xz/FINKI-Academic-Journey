// Да се дефинира класата Film во која се чуваат информации за:

// име на филмот (динамички алоцирано поле од знаци)
// мемориската зафатнина на филмот изразена во MB
// жанр на филмот кој може да биде: акција, комедија или драма (енумерација zanr)
// Да се обезбедат сите потребни методи за класата за правилно функционирање на програмата.

// Дополнително за оваа класа да се дефинира функцијата:

// pecati() - функција во која ќе се печатат одделени со цртичка(-): мемориска зафатнина на филмот со постфикс MB и името на филмот во наводници.
// Да се креира класата DVD во која се чуваат информации за :

// низа од филмови снимени на DVD-то (Поле од најмногу 5 објекти од класата Film )
// број на филмови снимени на DVD-то
// меморискиот капацитет на DVD-то (во MB)
// Да се обезбедат сите потребни методи за класата за правилно функционирање на програмата.

// Дополнително за оваа класа да се дефинираат методите:

// dodadiFilm (Film f) - со кој се додава филмот f на DVDто, но само ако има доволно преостанато место (земајќи го предвид меморискиот капацитет на DVD-то и мемориската зафатнина на снимените филмови) и притоа да има помалку од 5 филмови на DVD-то.
// pecatiFilmoviDrugZanr(zanr z) – со кој се печатат информациите за сите филмови кои НЕ се од жанрот zanr (акција, комедија или драма).
// (дополнително барање):float procentNaMemorijaOdZanr(zanr z)- со кој се пресметува процентот на меморијата која ја зафаќаат филмовите од жанр z на DVD-то.
// For example:

// Input	
// 5
// 4
// Terminator 20 0
// Up 20 2
// Clueless 4 1
// Gia 15 2
// 0

// Result
// ===== Testiranje na metodot pecatiFilmoviDrugZanr() od klasata DVD ======
// 20MB-"Up"
// 4MB-"Clueless"
#include <iostream>
#include <cstring>
using namespace std;

enum zanr{akcija,komedija,drama};

class Film
{
private:
    char *ime;
    int golemina;
    zanr z;

public:
    Film(char *ime = "", int golemina = 0, zanr z = akcija)
    {
        this->ime = new char[strlen(ime) + 1];
        strcpy(this->ime, ime);

        this->golemina = golemina;
        this->z = z;
    }

    void pecati()
    {
        cout << this->golemina << "MB-\"" << this->ime << "\"" <<endl;
    }

    int getMem()
    {
        return this->golemina;
    }

    zanr getZanr(){
        return this->z;
    }
};

class DVD
{
private:
    Film filmovi[5];
    int brojfilmovi;
    int memoriski_kapacitet;

public:
    DVD(int memoriski_kapacitet){
    this->memoriski_kapacitet=memoriski_kapacitet;
    this->brojfilmovi=0;
    }
    DVD(Film filmovi[], int brojfilmovi, int memoriski_kapacitet)
    {
    for (int i = 0; i < brojfilmovi; i++)
        {
            this->filmovi[i] = filmovi[i];
        }

        this->brojfilmovi = brojfilmovi;
        this->memoriski_kapacitet = memoriski_kapacitet;

       
    }
    void dodadiFilm(Film film)
    {
        int vkupnaMemorija = 0;
            for(int i=0;i<brojfilmovi;i++){
                vkupnaMemorija+=filmovi[i].getMem();
            }
            if(brojfilmovi>4 || vkupnaMemorija+film.getMem()>memoriski_kapacitet) return;
            filmovi[brojfilmovi] = film;
            brojfilmovi++;
    }



    void pecatiFilmoviDrugZanr(zanr z){
         for(int i=0;i<this->brojfilmovi;i++){
                if(filmovi[i].getZanr()!=z){return ;} else {filmovi[i].pecati();}
            }
    }

    int procentNaMemorijaOdZanr(zanr z){
        return 1;
    }

    Film getFilm(int i){
    return filmovi[i];
    }

    int getBroj(){
       return this->brojfilmovi;
    }

};

int main() {
    // se testira zadacata modularno
    int testCase;
    cin >> testCase;

    int n, memorija, kojzanr;
    char ime[50];

    if (testCase == 1) {
        cout << "===== Testiranje na klasata Film ======" << endl;
        cin >> ime;
        cin >> memorija;
        cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
        Film f(ime, memorija, (zanr) kojzanr);
        f.pecati();
    } else if (testCase == 2) {
        cout << "===== Testiranje na klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        for (int i = 0; i < n; i++)
            (omileno.getFilm(i)).pecati();
    } else if (testCase == 3) {
        cout << "===== Testiranje na metodot dodadiFilm() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        for (int i = 0; i < omileno.getBroj(); i++)
            (omileno.getFilm(i)).pecati();
    } else if (testCase == 4) {
        cout << "===== Testiranje na metodot pecatiFilmoviDrugZanr() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        omileno.pecatiFilmoviDrugZanr((zanr) kojzanr);

    } else if (testCase == 5) {
        cout << "===== Testiranje na metodot pecatiFilmoviDrugZanr() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        omileno.pecatiFilmoviDrugZanr((zanr) kojzanr);

    } else if (testCase == 6){
		cout<<"===== Testiranje na metodot procentNaMemorijaOdZanr() od klasata DVD =====" <<endl;
		DVD omileno(40);
		cin >> n;
		for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        cout<<"Procent na filmovi od dadeniot zanr iznesuva: "<<omileno.procentNaMemorijaOdZanr((zanr) kojzanr)<<"%\n";
		
	}

    return 0;
}