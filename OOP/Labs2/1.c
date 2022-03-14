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
};


bool changeOfSeconds(Agol a, int sec){
	return a.getSekundi()!=sec;
}

int main() {
    
    //da se instancira objekt od klasata Agol
    int deg, min, sec;
    cin >> deg >> min >> sec;
    
    if (proveri(deg, min, sec)) {
    
    	a1.set_stepeni(deg);
        a1.set_minuti(min);
        a1.set_sekundi(sec);
        cout << a1.to_sekundi();
        if (changeOfSeconds(a1,sec))
			cout << "Ne smeete da gi menuvate sekundite vo ramkite na klasata!" << endl;
        
    }
    
    return 0;
}