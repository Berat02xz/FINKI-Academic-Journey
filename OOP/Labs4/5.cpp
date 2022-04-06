// Да се дефинира класа Pica за која ќе се чуваат податоци за:

// име на пицата (низа од 15 знаци)
// цена (цел број)
// состојки (динамички алоцирана низа од знаци)
// намалување на цената во проценти (цел број) - ако пицата не е на промоција намалувањето има вредност нула, во спротивно, вредност поголема од нула и не поголема од 100.
// За потребите на оваа класа да се креираат потребните конструктори и да се напише соодветен деструктор. Дополнително за оваа класа да се дефинира функцијата:

// pecati() - функција во која ќе се печатат податоците за пицата во следниот формат: име - состојки, цена.
// istiSe(Pica p) - функција за споредба на две пици според состојките :
// Да се дефинира класа Picerija во која се чуваат:

// име (низа од максимум 15 знаци)
// динмички алоцирана низа од објекти од класата Pica
// број на пици (цел број)
// За потребите на оваа класа да се дефинираат потребните конструктори и да се напише соодветен деструктор. Да се имплементираат и следниве методи:

// dodadi (Pica P) - за додавање нова пица во пицеријата, но само ако не постои иста во низата (нема пица со исти состојки со пицата што треба да се додаде).
// void piciNaPromocija() - се печатат сите пици од пицеријата што се на промоција во формат : име - состојки, цена, цена со попуст.
// For example:

// Input
// FINKI-Pica
// 5
// Margarita
// 200
// Domaten sos, kaskaval, maslinovo maslo
// 0
// Napolitana
// 210
// Domaten sos, kaskaval, svezi sampinjoni, maslinovo maslo
// 0
// Kapricioza
// 210
// Domaten sos, kaskaval, sunka, svezi sampinjoni
// 30
// Kapricioza
// 210
// Domaten sos, kaskaval, sunka, svezi sampinjoni
// 30
// Vegetarijana
// 230
// Domaten sos, kaskaval, tikvici, svezi sampinjoni, piperka, domat, maslinki, rukola, pcenka
// 20
// Pica-FINKI
// Tuna
// 230
// Domaten sos, kaskaval, pcenka, maslinki, tuna, rukola
// 0

// Result
// FINKI-Pica
// Pici na promocija:
// Kapricioza  - Domaten sos, kaskaval, sunka, svezi sampinjoni, 210 147
// Vegetarijana - Domaten sos, kaskaval, tikvici, svezi sampinjoni, piperka, domat, maslinki, rukola, pcenka, 230 184
// Pica-FINKI
// Pici na promocija:
// Kapricioza  - Domaten sos, kaskaval, sunka, svezi sampinjoni, 210 147
// Vegetarijana - Domaten sos, kaskaval, tikvici, svezi sampinjoni, piperka, domat, maslinki, rukola, pcenka, 230 184
#include <iostream>
#include <cstring>

using namespace std;

class Pica{
private:
    char ime[15];
    int cena;
    char *sostojki;
    int procenti=0;
public:
    Pica(char ime[15]="\0", int cena=0, char *sostojki="\0", int procenti=0){
    this->sostojki=new char[strlen(sostojki)+1];
    strcpy(this->sostojki,sostojki);
    strcpy(this->ime,ime);
    this->cena=cena;
    this->procenti=procenti;
    }
   

    void pecati(){
    cout<<ime<<" - "<<this->sostojki<<", "<<this->cena<<" "<<cena*((float)(100-procenti)/100)<<endl;
    }

    bool istiSe(Pica p){
    return !strcmp(this->sostojki,p.sostojki);
    }

    char *getSostojki(){return this->sostojki;}

    bool isPromocija(){
            return procenti!=0;
    }
};

class Picerija{
    private:
        char ime[15];
        Pica *pici;
        int broj;
    public:
        Picerija(char ime[15]){
            strcpy(this->ime,ime);
            this->broj=0;
        }
        Picerija(char ime[15],Pica *p, int broj){
            strcpy(this->ime,ime);
            this->pici = new Pica[broj];
            for(int i=0;i<broj;i++){
               pici[i]=p[i];
            }
            this->broj=broj;
        }
        void setIme(char ime[15]){
            strcpy(this->ime,ime);
        }
        char* getIme(){
            return ime;
        }
        void dodadi(Pica p){
            for(int i=0;i<broj;i++){
                if(pici[i].istiSe(p)) return;
            }
            Pica *n = new Pica[broj+1];
            for(int i=0;i<broj;i++){
                n[i] = pici[i];
            }
            n[broj]=p;
            pici = n;
            broj++;
        }
        void piciNaPromocija(){
            for(int i=0;i<broj;i++){
                if(pici[i].isPromocija()) pici[i].pecati();
            }
        }
       

};
//Vasiot kod tuka

int main () {

    int n;
    char ime[15];
    cin >> ime;
    cin >> n;

    Picerija p1(ime);
    for(int i = 0; i < n; i++){
        char imp[100];
        cin.get();
        cin.getline(imp,100);
        int cena;
        cin >> cena;
        char sostojki[100];
        cin.get();
        cin.getline(sostojki,100);
        int popust;
        cin >> popust;
        Pica p(imp,cena,sostojki,popust);
        p1.dodadi(p);
    }

    Picerija p2 = p1;
    cin >> ime;
    p2.setIme(ime);
    char imp[100];
    cin.get();
    cin.getline(imp,100);
    int cena;
    cin >> cena;
    char sostojki[100];
    cin.get();
    cin.getline(sostojki,100);
    int popust;
    cin >> popust;
    Pica p(imp,cena,sostojki,popust);
    p2.dodadi(p);

    cout<<p1.getIme()<<endl;
    cout<<"Pici na promocija:"<<endl;
    p1.piciNaPromocija();

    cout<<p2.getIme()<<endl;
    cout<<"Pici na promocija:"<<endl;
    p2.piciNaPromocija();

	return 0;
}
