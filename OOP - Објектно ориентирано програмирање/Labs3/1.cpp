// Во оваа задача треба да се внесат и испечатат податоци за автомобили.

// За еден автомобил (објект од класата Car) се чуваат следниве податоци:

// сопственик (објект од класата Person)
// датум на купување (објект од класата Date)
// цена (float price), предодредена вредност 0
// За класата Car потребно е да се напише метод за печатење print() и метод за добивање на цената getPrice().

// Класата Date содржи три цели броеви (int year, month, day) кои претставуваат датум. За неа треба да се 
// напише метод за печатење print(), предодреден (default) конструктор, конструктор со параметри и конструктор за копирање.

// Класата Person содржи име и презиме (низи од максимум 20 знаци, со предодредени вредности not specified), 
// предодреден конструктор, конструктор со параметри и метод за печатење print().

// Методот за печатење кај класата Person изгледа вака: [name] [lastName].

// Методот за печатење кај класата Date изгледа вака: [year].[month].[day].

// Методот за печатење кај класата Car изгледа вака:

// owner.print()

// date.print()

// Price: [price]

// Покрај тоа, потребно е да се напише метод cheaperThan(Car* cars, int numCars, float price) кој ќе ги испечати сите објекти Car
//  од низата cars со големина numCars чија цена е помала од price.

// For example:

// Input	
// 2
// Petre
// Petreski
// 2018
// 07
// 31
// 410020

// Result
// Petre Petreski
// 2018.7.31
// Price: 410020
#include <iostream>
#include <cstring>
using namespace std;


// Date Class
class Date
{
private:
    int year;
    int month;
    int day;

public:
    // default constructor
    Date()
    {
        this->year = 1999;
        this->month = 12;
        this->day = 1;
    }

    // constructor with parameters
    Date(int year, int month, int day)
    {
        this->day = day;
        this->month = month;
        this->year = year;
    }

    // copy-constructor
    Date(const Date &D)
    {
        this->day = D.day;
        this->month = D.month;
        this->year = D.year;
    }

    ~Date(){}

    // Print Method
    void print()
    {
        cout << this->year << "." << this->month << "." << this->day << endl;
    }
};

class Owner
{
private:
    char ime[20];
    char prezime[20];

public:
    // default constructor      +     //constructor with parameters
    Owner(){
        strcpy(this->ime, "");
        strcpy(this->prezime, "");
    }
    
    Owner(char *ime, char *prezime)
    {
        strcpy(this->ime, ime);
        strcpy(this->prezime, prezime);
    }

    // Method Print
    void print()
    {
        cout << this->ime << " " << this->prezime << endl;
    }
 
    ~Owner(){}
};

// Car Class
class Car
{
private:
    Owner Soptsvenik;
    Date Datum;
    float price=0;

public:
    // constructor with parameters
    Car(Owner &Sopstvenik,  Date &Datum, float price)
    {
        this->price = price;
        this->Datum = Datum;
        this->Soptsvenik = Sopstvenik;
    }

    Car(){
        this->price=0;
    }

    ~Car(){};

    void print()
    {
        this->Soptsvenik.print();
        this->Datum.print();
        cout << "Price: "<<this->price << endl;
    }

    int getPrice()
    {
        return this->price;
    }
};


// Покрај тоа, потребно е да се напише метод cheaperThan(Car* cars, int numCars, float price)
// кој ќе ги испечати сите објекти Car
//  од низата cars со големина numCars чија цена е помала од price.

void cheaperThan(Car *cars, int numCars,float priceLimit){
    
    for(int i=0;i<numCars;i++){
    if(cars[i].getPrice() < priceLimit){
        cars[i].print();
    }
}

}


int main() {
	char name[20];
	char lastName[20];
	int year;
	int month;
	int day;
	float price;

	int testCase;
	cin >> testCase;

	if (testCase == 1) {
		cin >> name;
		cin >> lastName;
		Owner lik(name, lastName);

		cin >> year;
		cin >> month;
		cin >> day;
		Date date(year, month, day);

		cin >> price;
		Car car(lik, date,  price);

		car.print();
	}
	else if (testCase == 2) {
		cin >> name;
		cin >> lastName;
		Owner lik(name, lastName);

		cin >> year;
		cin >> month;
		cin >> day;
		Date date(Date(year, month, day));

		cin >> price;
		Car car(lik, date,  price);
		car.print(); 
	}
	else {
		int numCars;
		cin >> numCars;

		Car cars[10];
		for (int i = 0; i < numCars; i++) {
			cin >> name;
			cin >> lastName;
			Owner lik(name, lastName);

			cin >> year;
			cin >> month;
			cin >> day;
			Date date(year, month, day);

			cin >> price;
			cars[i] = Car(lik, date,  price);
		}
        float priceLimit;
        cin >> priceLimit;
		cheaperThan(cars, numCars, priceLimit);
	}


	return 0;
}