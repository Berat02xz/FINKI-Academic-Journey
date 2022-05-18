// --

// Потребно е да конструирате абстракна класа Shape со само еден параметар:

// страна (int)
// Конструктори:

// Shape()
// Shape(int a)
// И виртуелните методи:

// double plostina()
// void pecati()
// int getType()
// Од таа класа вие треба да конструирате 3 изведени класи:

// Square
// Circle
// Triangle
// Изведените класи немаат дополнителни парамтери, туку ја наследуваат страната од Shape

// Конструктори:

// Конструкторот на изведените класи ќе зема еден аругмент, страната на фигурата.

// При пишување на конструкторот на изведените класи да се искористи констукторот на родител класата

// Методи:

// Секоја од класите си има своја формула за plostina() соодветна за нивната геометриска фигура

// Формула за плоштина на квадрат а * a
// Формула за плоштина на круг 3.14 * a * a
// Формула за плоштина на триаголник (sqrt(3)/4) * side * side
// За pecati() методот упатете се кон outputot за тест случајите.

// getType() методот треба да враќа:

// 1 за Square
// 2 за Circle
// 3 за Triangle
// Поени: 5

// --

// Дефинирајте го методот void checkNumTypes(Shape** niza, int n) така што ќе испечати во три реда колку квадрати, кругови и триаголници има во низата.

// Поени: 2

// --

// Покрај тоа треба да ја допишете Main класата така што ќе алоцирате динамичка низа од покажувачи кон класата Shape.

// Потоа кај секој покажувач од низата зависно од дадениот input да алоцирате објект од една од трите изведени класи.

// Input:

// Прво се добива n - големината на динамичката низа. Потоа n пати се добиваат пар цели броеви t i a, каде што t е типот на објектот и а е страната на објектот.

// Пример:

// 3

// 1 3 //квадрат со страна со должина 3

// 2 2 //круг со страна со должина 2

// 3 1 //триаголник со страна со должина 1

// Поени: 3

// --

// For example:

// Input	
// 5
// 1
// 2
// 2
// 1
// 3
// 3
// 3
// 1
// 2
// 2

//Result
// Kvadrat so plostina = 4
// Krug so plostina = 3.14
// Triagolnik so plostina = 3.89711
// Triagolnik so plostina = 0.433013
// Krug so plostina = 12.56
// Broj na kvadrati vo nizata = 1
// Broj na krugovi vo nizata = 2
// Broj na triagolnici vo nizata = 2

#include <iostream>
#include <cmath>
using namespace std;


//TODO: konstruiraj ja abstraknata klasa Shape
class Shape
{
protected:
    int strana;

public:
    Shape(int strana = 0)
    {
        this->strana = strana;
    }

    virtual double plostina() = 0;
    virtual void pecati() = 0;
    virtual int getType() = 0;
};
// TODO: konstruiraj ja klasata Kvadrat
class Kvadrat : public Shape
{
protected:
public:
};
// TODO: konstruiraj ja klasata Krug
class Krug : public Shape
{
protected:
public:
};
// TODO: konstruiraj ja klasata Triagolnik
class Triagolnik : public Shape
{
protected:
public:
};

//TODO: definiraj go metodot void checkNumTypes(Shape** niza, int n)

int main(){


	int n;
	cin >> n;

	//TODO: inicijaliziraj niza od pokazuvachi kon klasata Shape

	

	//TODO: alociraj memorija so golemina n za prethodno navedenata niza
	

	int classType;
	int side;

	//TODO: konstruiraj for ciklus so chija pomosh ke ja popolnish nizata
	for(int i = 0; i < n; ++i){

		cin >> classType;
		cin >> side;
		
	}
    
    
	for(int i = 0; i < n; ++i){

		niza[i]->pecati();
	}

	checkNumTypes(niza, n);


	return 0;
}