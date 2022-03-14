// Да се дефинира класа Krug, во која се чуваат информации за:

// радиус float
// бројот π const float.
// Во класата да се реализираат:

// default конструктор и конструктор со аргументи
// метод за пресметување плоштина
// метод за пресметување периметар
// метод кој кажува дали плоштината и периметарот на даден круг
// се еднакви

// For example:

// Input	
// 15

// Result
// 94.2
// 706.5
// 0

#include <iostream>
using namespace std;

class Krug {
private:
	float radius;
	const float pi=3.14;
public:

 Krug(){}

 Krug(float rad){
	 radius=rad;
 }
	//metodi
	float plostina(){
    return pi*(radius*radius);
	}

	float perimetar(){
	return 2*radius*pi;
	}

	float ednakvi(){
	if (plostina()==perimetar())
	{ return 1; } else { return 0;}
	}
};

int main() {
	float r;
	cin >> r;
	Krug k(r);
    //instanciraj objekt od klasata Krug cij radius e vrednosta procitana od tastatura
	cout<< k.perimetar() << endl;
	cout<< k.plostina() << endl;
	cout<< k.ednakvi() <<endl;
    //instanciraj objekt od klasata Krug cij radius ne e definiran
	return 0;
}