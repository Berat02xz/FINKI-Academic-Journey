// Сите елементи кои ги превезуваат камионите имаат одредена маса. Имплементирајте апстрактна класа ImaMasa од која што ќе наследуваат сите класи кои имаат маса. Во оваа класа треба да се дефинираат методите:

// double vratiMasa() враќа маса изразена во kg
// void pecati() печати информации за соодветниот објект
// Нека еден камион превезува пијалоци и тоа: пакет сокови и пакет вина. Да се дефинираат класите PaketSok и PaketVino кои ги имплементираат методите од апстрактната класа ImaMasa. При тоа да се земе предвид дека класите PaketSok и PaketVino се изведени од класата PaketPijalok. Притоа да се земат дадените податоци:

// Класа PaketPijalok:

// членови:
// volumenEden double
// kolicina int
// методи:
// double vratiMasa()
// void pecati()
// int getKolicina()
// Класа PaketSok:

// членови:
// volumenEden double
// kolicina int
// daliGaziran bool
// методи:
// double vratiMasa()
// void pecati()
// Класа PaketVino:

// членови:
// volumenEden double
// kolicina int
// procentAlkohol double
// методи:
// double vratiMasa()
// double getProcentAlkohol()
// void pecati()
// Еден пијалок има маса пресметана како производ на волуменот и густината (0.8kg/dm3) и масата на амбалажата (0.2kg). Овие податоци за густината и за масата на амбалажата да се стават како статички податоци за класата PaketPijalok. Масата на пакетот е производ на масата на еден пијалок и количината на пијалоци која ја има во пакетот. Соковите кои не се газирани имаат дополнителна маса од 0.1kg по пијалок. За вината, масата се добива како производ од масата на пакетот пресметан по правилата за секој пијалок помножен со коефициентот: (0.9+procentAlkohol).

// Покрај овие класи, да се имплементира класата Kamion. За еден камион се чуваат информации за:

// регистрацијата char*
// возачот char*
// елементите кои ги превезува камионот (низа од покажувачи од ImaMasa)
// Во класата Kamion покрај методите set и get дефинирајте ги и следните методи:

// Kamion(char* ) конструктор со кој се генерира празен камион (без товар) и само возач
// void registriraj(char*) се додава регистрацијата на камионот
// void dodadiElement(ImaMasa) се додава нов елемент во камионот
// double vratiVkupnaMasa() се враќа вкупната маса на товарот кој го пренесува камионот
// void pecati() се печатат сите елементи во камионот заедно со регистрацијата и името на возачот
// Kamion pretovar(char* , char* ) се врши претовар во нов камион, но во новиот покрај тоа што се менуваат регистрацијата и името на возачот (аргументи), се отстранува пакетот со најголема маса.
// Со помош на правилата за справување со исклучоци да се модифицира програмата така што ќе се запазат следните правила: - Регистрацијата има 8 карактери, така што првите два и последните два мора да бидат букви. Да се определи каде ќе се фрли соодветниот исклучок. Онаму каде што ќе се фати исклучокот, ќе се отпечати "Pogresno vnesena registracija!".

// For example:

// Input	
// SK3456RG
// Ljupcho
// 1 20
// 1
// 3 45
// 0.7
// 2 10
// 0.12
// 0.5 50
// 0
// 5 12
// 0.8
// BT1234GH
// Petko

// Result

// Kamion so registracija SK3456RG i vozac Ljupcho prenesuva: 
// Paket sok
// kolicina 20, so po 0.8 l(dm3)
// Paket vino
// kolicina 45, 70% alkohol od po 2.4 l(dm3)
// Paket vino
// kolicina 10, 12% alkohol od po 1.6 l(dm3)
// Paket sok
// kolicina 50, so po 0.4 l(dm3)
// Paket vino
// kolicina 12, 80% alkohol od po 4 l(dm3)
// Vkupna masa: 346.24
// Kamion so registracija BT1234GH i vozac Petko prenesuva: 
// Paket sok
// kolicina 20, so po 0.8 l(dm3)
// Paket vino
// kolicina 10, 12% alkohol od po 1.6 l(dm3)
// Paket sok
// kolicina 50, so po 0.4 l(dm3)
// Paket vino
// kolicina 12, 80% alkohol od po 4 l(dm3)
// Vkupna masa: 159.04


#include<iostream>
#include<cstring>
using namespace std;
class ImaMasa
{
public:
    virtual double vratiMasa() = 0;
    virtual void pecati() = 0;
};



class PaketPijalok{
protected:
double volumenEden;
int kolicina;
static double gustina;
static double ambalaza;
public:
PaketPijalok(double volumenEden=0, int kolicina=0){
this->volumenEden=volumenEden;
this->kolicina=kolicina;
}

double vratiMasa(){
 return (volumenEden*gustina + ambalaza)*getKolicina();
}
void pecati(){
    cout<<"kolicina "<<kolicina;
}
int getKolicina(){
    return kolicina;
}
    static double getGustina()
    {
        return gustina;
    }
    double getVolumen()
    {
        return volumenEden;
    }
};

double PaketPijalok::gustina = 0.8;
double PaketPijalok::ambalaza = 0.2;
class PaketSok : public ImaMasa, public PaketPijalok
{
private:
bool daliGaziran;
public:
PaketSok(double volumenEden=0, int kolicina=0, bool daliGaziran=0): PaketPijalok(volumenEden,kolicina) {
    this->daliGaziran=daliGaziran;
}

    double vratiMasa()
    {
        if(daliGaziran){
            return PaketPijalok::vratiMasa();
        } else {
            return PaketPijalok::vratiMasa() + (0.1*PaketPijalok::getKolicina());
        }
    }
    void pecati()
    {
        cout<<"Paket sok"<<endl;
        cout<<"kolicina "<<kolicina<<", so po "<<getVolumen()*getGustina()<<" l(dm3)"<<endl;
    }
};

class PaketVino : public ImaMasa  ,public PaketPijalok
{
private:

double procentAlkohol;
public:
PaketVino(double volumenEden=0, int kolicina=0, bool procentAlkohol=0): PaketPijalok(volumenEden,kolicina) {
    this->procentAlkohol=procentAlkohol;
}


    double vratiMasa()
    {
        return PaketPijalok::vratiMasa() * (0.9 + procentAlkohol);
    }
    void pecati()
    {
        cout<<"Paket vino"<<endl;
        cout<<"kolicina "<<kolicina<<", "<<procentAlkohol*100<<"% alkohol od po "<<getVolumen()*getGustina()<<" l(dm3)"<<endl;
    }
    double getProcentAlkohol()
    {
        return procentAlkohol;
    }
};

class Kamion{
private:
char registracija[9];
char vozac[50];
ImaMasa **elementi;
int broj;
public:
Kamion(char* vozac=""){
strcpy(this->vozac,vozac);
strcpy(this->registracija,"");
int broj=0;
elementi=new ImaMasa*[0];
}

void registriraj(char* registracija=""){
    strcpy(this->registracija,registracija);
}

void dodadiElement(ImaMasa *IM){
    ImaMasa **temp=new ImaMasa*[broj+1];
    for(int i=0;i<broj;i++){
        temp[i]=elementi[i];
    }
    temp[broj++]=IM;
    delete[] elementi;
    elementi=temp;
}

double vratiVkupnaMasa(){
    int vkupnamasa=0;
    for(int i=0;i<broj;i++){
        vkupnamasa=vkupnamasa+elementi[i]->vratiMasa();
    }
    return vkupnamasa;
}

void pecati() {
    cout<<"Kamion so registracija "<<registracija<<" i vozac "<< vozac<<" prenesuva: "<<endl;
    for(int i=0;i<broj;i++){
        elementi[i]->pecati();
    }
}
Kamion(char *registracija="",char *vozac=""){
    if((isalpha(registracija[0]) !=0) && (isalpha(registracija[1]) !=0) && (isalpha(registracija[6]) !=0) && (isalpha(registracija[7]) !=0)){
    strcpy(this->registracija,registracija);
    strcpy(this->vozac,vozac);
    int broj=0;
    elementi=new ImaMasa*[broj];
    }
    else{
        throw 0;
    }
}

Kamion pretovar(char* , char* ){
        Kamion nov(registracija,vozac);
        double maxMasa=elementi[0]->vratiMasa();
        int temp=0;
        for(int i=1;i<broj;i++)
        {
            if(elementi[i]->vratiMasa() > maxMasa)
            {
                maxMasa=elementi[i]->vratiMasa();
                temp=i;
            }
        }
        for(int i=0;i<broj;i++)
        {
            if(i != temp)
            {
                nov.dodadiElement(elementi[i]);
            }
        }
        return nov;
    }


};

int main()
{
    char ime[20], reg[9];
    double vol;
    int kol;
    bool g;
    double proc;
    
            cin>>reg;
            cin>>ime;
            try{
            Kamion A(reg, ime);
            ImaMasa **d = new ImaMasa*[5];
            cin>>vol>>kol;
            cin>>g;
            d[0] = new PaketSok(vol, kol, g);
            cin>>vol>>kol;
            cin>>proc;
            d[1] = new PaketVino(vol, kol, proc);
            cin>>vol>>kol;
            cin>>proc;
            d[2] = new PaketVino(vol, kol, proc);
            cin>>vol>>kol;
            cin>>g;
            d[3] = new PaketSok(vol, kol, g);
            cin>>vol>>kol;
            cin>>proc;
            d[4] = new PaketVino(vol, kol, proc);

            A.dodadiElement(d[0]);
            A.dodadiElement(d[1]);
            A.dodadiElement(d[2]);
            A.dodadiElement(d[3]);
            A.dodadiElement(d[4]);
            A.pecati();
            cout<<"Vkupna masa: "<<A.vratiVkupnaMasa()<<endl;
            cin>>reg;
            cin>>ime;
            Kamion B = A.pretovar(reg, ime);
            B.pecati();
            cout<<"Vkupna masa: "<<B.vratiVkupnaMasa()<<endl;
            }
            catch(int){
                cout<<"Pogresno vnesena registracija"<<endl;
            }
}
