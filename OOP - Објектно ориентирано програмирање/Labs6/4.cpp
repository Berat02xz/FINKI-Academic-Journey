// For example:

// Input	
// Kukja_vo_Centar
// 60
// 850
// Vila_na_Vodno
// 110
// 1120
// 10

// Result
// Kukja_vo_Centar, Kvadratura: 60, Cena po Kvadrat: 850
// Danok za: Kukja_vo_Centar, e: 2550
// Vila_na_Vodno, Kvadratura: 110, Cena po Kvadrat: 1120, Danok na luksuz: 10
// Danok za: Vila_na_Vodno, e: 18480

//---------------------------------------------------------------------
// Да се развие класа Nediviznina за коjа се чуваат следниве информации:

// адреса (динамички алоцирана низа од знаци)
// квадратура (цел боj)
// цена за квадрат (цел боj)
// За оваа класа да се имплементираат соодветните конструктори и следните методи:

// cena() коj ќе jа враќа цената на недвижнината (квадратура * цена-за-квадрат)
// pecati() коj ќе ги испечати сите информации за истата
// danokNaImot() коj го ваќа данокот што се плаќа за недвижнината, а истиот се пресметува како 5% од цената на недвижнината.


#include <iostream>
#include <cstring>
using namespace std;
//Vasiot kod ovde....

class Nedviznina{
protected:
    char *adresa;
    int kvadratura;
    int cena;
public:
    Nedviznina(){
        strcpy(this->adresa,"");
        this->kvadratura=0;
        this->cena=0;
    }


    void pecati(){
        cout<<this->adresa<<", Kvadratura: "<<this->kvadratura<<", Cena po Kvadrat: "<<this->cena;
    }

    double danokNaImot(){
        return kvadratura*cena*0.05;
    }

    friend istream &operator >>(istream &IS, Nedviznina &N){
        char *temp;
        IS>>temp;
        N.adresa=new char[strlen(temp)+1];
        strcpy(N.adresa,temp);

        IS>>N.kvadratura;
        IS>>N.cena;

        return IS;
    }

    char *getAdresa()
    {
        return adresa;
    }

};


// Од оваа класа да се изведе класа Vila за коjа дополнително се чува данок на луксуз (цел боj, пр. 10%). 
// За оваа класа да се преоптоварат методите:

// pecati()
// danokNaImot() со тоа што пресметаниот данок се зголемува процентуално за данокот на луксуз.
// И за двете класи треба да се преоптовари operator >>.

class Vila : public Nedviznina
{
private:
    int danok;
public:
    Vila() : Nedviznina()
    {
        this->danok = 0;
    }

    void pecati()
    {
        Nedviznina::pecati();
        cout << "Danok na luksuz: " << this->danok << endl;
    }

    friend istream &operator>>(istream &IS, Vila &N)
    {
        IS >> N.adresa >> N.kvadratura >> N.cena >> N.danok;
        return IS;
    }

    char *getAdresa()
    {
        return adresa;
    }

    double danokNaImot()
    {
        return (kvadratura * cena * ((danok + 5) / 100.0));
    }
};

int main(){
    Nedviznina n;
    Vila v;
    cin>>n;
    cin>>v;
    n.pecati();
    cout<<"Danok za: "<<n.getAdresa()<<", e: "<<n.danokNaImot()<<endl;
    v.pecati();
    cout<<"Danok za: "<<v.getAdresa()<<", e: "<<v.danokNaImot()<<endl;
    return 0;
}