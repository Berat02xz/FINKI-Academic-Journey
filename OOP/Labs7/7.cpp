// Да се имплементира апстрактна класа Secret со два чисто виртуелни методи:

// double simpleEntropy() - се пресеметува како бројот на уникатни (цифри/букви) поделен со бројот на вкупно знаци. (2 поени)
// int total()- го враќа вкупниот број на цифри/букви. (1 поени)
// Од оваа класа да се имплементираат две изведени класи DigitSecret (цифри 0-9) и CharSecret (букви a-z) во кои се чува низа од максимум 100 цифри односно 100 мали букви од a до z (1 поени).

// Да се имплементира соодветен конструктор, како и следните оператори:

// оператори== и != за споредување на два објекти од каков било тип (DigitSecret,CharSecret) според елементите што ги содржат (1 поени)
// оператор << за печатење на стандарден излез во формат: (1 поени)
// [cifri/bukvi] Simple entropy: [entropy] Total: [total]

// Да се имплементираaт методите:

// void process(Secret **secrets, int n) - што прима низа од покажувачи од класата Secret и нивниот број и треба да го отпечати елементот со најголема вредност за simpleEntropy. (3 поени)
// void printAll(Secret **secrets, int n) - што прима исти аргументи како претходниот и треба да ги испечати сите елементи во низата secrets. (1 поен)
// For example:

// Input	
// 1

// Result
// operator <<
// 12345 Simple entropy: 1 Total: 5
// abcabc Simple entropy: 0 Total: 6

#include<cstring>
#include<iostream>
using namespace std;

class Secret{
    public:
    //deklariranje na chisto virtuelnite metodi
    virtual double simpleEntropy() = 0;
    virtual int total() =0;
};

//preoptovaruvanje na operator ==

//preoptovaruvanje na operator !=

class DigitSecret : public Secret { //da se dopolni so izraz za nasleduvanje
    private:
    // da se deklariraat potrebnite promenlivi
    int digits[100];
    int n;
    public:
    //default konstruktor
    DigitSecret(int *digits=0, int n=0){
        this->n=n;
        for(int i=0;i<n;i++){
            this->digits[i]=digits[i];
        }
    }
    //konstruktor so argumenti
    
    //prepokrivanje na dvata chisto virtuelni metodi od Secret
    double simpleEntropy() {
        int count=0,vkupno=0;
        for(int i=0;i<n;i++)
        {
            count=0;
            for(int j=0;j<n;j++)
            {
                if(digits[i] == digits[j])
                {
                    count++;
                }
            }
            if(count == 1)
            {
                vkupno++;
            }
        }
        return (vkupno*1.0)/n;
    }
    int total() {
        return n;
    }
friend bool operator==(Secret &s, Secret &s1)
{
    return ((s.simpleEntropy() == s1.simpleEntropy()) && (s.total() == s1.total()));
}
friend bool operator!=(Secret &s,Secret &s1)
{
    return !((s.simpleEntropy() == s1.simpleEntropy()) && (s.total() == s1.total()));
}


    //preoptovaruvanje na operatorot za pechatenje <<
    friend ostream &operator <<(ostream &O, DigitSecret &D ){
        for(int i=0;i<D.n;i++)
        {
            O<<D.digits[i];
        }
        O<<" Simple entropy: "<<D.simpleEntropy()<<" Total: "<<D.total();
        return O;
    }
    
};

class CharSecret : public Secret { //da se dopolni so izraz za nasleduvanje
    private:
    // da se deklariraat potrebnite promenlivi
    char bukvi[100];
    public:
    //default konstruktor
    CharSecret(char *bukvi=""){
        strcpy(this->bukvi,bukvi);
    }
    //konstruktor so argumenti
    
    //prepokrivanje na dvata chisto virtuelni metodi od Secret
    double simpleEntropy() {
        int count=0,vkupno=0;
        for(int i=0;i<strlen(bukvi);i++)
        {
            count=0;
            for(int j=0;j<strlen(bukvi);j++)
            {
                if(bukvi[i]==bukvi[j])
                {
                    count++;
                }
            }
            if(count == 1)
            {
                vkupno++;
            }
        }
        return (vkupno*1.0)/strlen(bukvi);
    }
    int total() {
        return strlen(bukvi);
    }

    char *text(){
        return bukvi;
    }
    //preoptovaruvanje na operatorot za pechatenje <<
    friend ostream &operator <<(ostream &O, CharSecret &D ){
        O<<D.text();
        O<<" Simple entropy: "<<D.simpleEntropy()<<" Total: "<<D.total();
        return O;
    }
    
};

//void process(Secret ** secrets, int n){}
void process(Secret ** secrets, int n){
    double max=secrets[0]->simpleEntropy();
    int index;
    for(int i=1;i<n;i++)
    {
        if(secrets[i]->simpleEntropy() > max)
        {
            max=secrets[i]->simpleEntropy();
            index=i;
        }
    }
    DigitSecret *ds=dynamic_cast<DigitSecret*>(secrets[index]);
    if(ds != 0)
    {
        cout<<*ds<<endl;
    }
    CharSecret *cs=dynamic_cast<CharSecret*>(secrets[index]);
    if(cs != 0)
    {
        cout<<*cs<<endl;
    }
}
//void printAll (Secret ** secrets, int n) {}
void printAll (Secret ** secrets, int n) {
    for(int i=0;i<n;i++)
    {
        DigitSecret *ds=dynamic_cast<DigitSecret*>(secrets[i]);
        if(ds != 0)
        {
        cout<<*ds<<endl;
        }
        CharSecret *cs=dynamic_cast<CharSecret*>(secrets[i]);
        if(cs != 0)
        {
        cout<<*cs<<endl;
        }
    }
}
int main() {
    int n;
    cin >> n;
    if(n == 0) {
		cout << "Constructors" << endl;
		int numbers [] = {1,2,3,4,5};
        DigitSecret ds(numbers,5);
        CharSecret cs("abcabc");
        cout << "OK" << endl;
    } else if(n == 1) {
  		cout << "operator <<" << endl;
		int numbers [] = {1,2,3,4,5};
        DigitSecret ds(numbers,5);
        CharSecret cs("abcabc");
        cout << ds << endl;
        cout << cs << endl;
    }  else if(n == 2) {
  		cout << "== and !=" << endl;
		int numbers [] = {1,2,3,4,5};
        DigitSecret ds(numbers,5);
        CharSecret cs("abcabc");
        CharSecret css("abcabc");
        cout << (ds == cs) << endl;
        cout << (cs != ds) << endl;
        cout << (cs == css) << endl;
        cout << (cs != css) << endl;
    } else if(n == 3) {
  		cout << "Secret processor" << endl;
		int numbers1 [] = {1,2,3,4,5,6,4,3,2,1,1,2,3,4,5};
        DigitSecret ds1(numbers1,15);
		int numbers2 [] = {1,2,3,4,5,0,0,0,5,5,4,4,3,3,2};
        DigitSecret ds2(numbers2,15);
		int numbers3 [] = {1,0,9,4,3,8,4,0,9,3,1,4,3,2,1,4,4,3,7,2};
        DigitSecret ds3(numbers3,20);
        CharSecret cs1("fhdaieowujkfjdsafjdsakjhueiqoyroq");
        CharSecret cs2("nvzcfsadrqipqhfjdfncxoqw");
        CharSecret cs3("uyoihfdsanmqeqrzvdhfeqyrq");
        Secret** s = new Secret*[6];
        s[0] = &ds1;
        s[1] = &ds2;
        s[2] = &ds3;
        s[3] = &cs1;
        s[4] = &cs2;
        s[5] = &cs3;
        process(s,6);
        delete [] s;
    }
    else if (n==4){
        cout << "Print all secrets" << endl;
		int numbers1 [] = {1,2,3,4,5,5,4,3,2,1,1,2,3,4,5};
        DigitSecret ds1(numbers1,15);
		int numbers2 [] = {1,2,3,4,5,0,0,0,5,5,4,4,3,3,2};
        DigitSecret ds2(numbers2,15);
		int numbers3 [] = {1,0,9,4,3,8,4,0,9,3,1,4,3,2,1,4,4,3,7,2};
        DigitSecret ds3(numbers3,20);
        CharSecret cs1("fhdaieowujkfjdsafjdsakjhueiqoyroq");
        CharSecret cs2("nvzcfsadrqipqhfjdfncxoqw");
        CharSecret cs3("uyoihfdsanmqeqrzvdhfeqyrq");
        Secret** s = new Secret*[6];
        s[0] = &ds1;
        s[1] = &ds2;
        s[2] = &ds3;
        s[3] = &cs1;
        s[4] = &cs2;
        s[5] = &cs3;
        printAll(s,6);
        delete [] s;
    }
    
    return 0;
}