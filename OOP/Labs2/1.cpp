// Да се дефинира класа Agol, во која се чуваат информации за:

// степени, минути и секунди (int)
// Во класата да се реализираат:

// конструктор по потреба
// методи за поставување на вредности на атрибутите на класата (set методи)
// метод за пресметување на вредноста на аголот во секунди
// Да се дефинира и метод за проверување на тоа дали внесениот агол е валиден, односно дали се внесени соодветни вредности за атрибутите (во границите кои ги дозволуваат).

// For example:

// Input	
// 15 20 20

//Result
// 55220

#include <iostream>
using namespace std;

class Agol {
    private:
int stepeni;
int minuti;
int sekundi;
    public:
 Agol(){}; 
 ~Agol(){};

//setteri
void set_stepeni(int deg){
    stepeni=deg;
}

void set_sekundi(int sec){
    minuti=sec;
}

void set_minuti(int min){
    minuti=min;
}

//getteri
int getSekundi(){
    return sekundi;
}

int to_sekundi(){
return sekundi + (stepeni*3600) + (minuti*60);
}
};

int proveri(int deg, int min, int sec){
return !(deg>360 || min > 60 || min <= 0 || deg <= 0 || sec < 0);
}

bool changeOfSeconds(Agol a, int sec){
	return a.getSekundi()!=sec;
}

int main() {
    Agol a1;
    //da se instancira objekt od klasata Agol
    int deg, min, sec;
    cin >> deg >> min >> sec;
    
    if (proveri(deg, min, sec)) {
    
    	a1.set_stepeni(deg);
        a1.set_minuti(min);
        a1.set_sekundi(sec);
        cout << a1.to_sekundi();
        if (changeOfSeconds(a1,sec))
{}
        
    } else{
        cout<<"Nevalidni vrednosti za agol"<<endl;
    }
    
    return 0;
}