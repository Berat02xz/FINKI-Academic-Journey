// Да се креира класа PositiveIntegers во која што ќе се чуваат информации за:

// низа од позитивни броеви (>0) (динамички алоцирана низа од цели броеви)
// број на елементи во низата
// максимален капацитет на низата
// За класата да се имплементираат:

// потребниот конструктор (погледнете во главната функција како се креира објект од оваа класа)
// метод void increaseCapacity(int c) којшто го зголемува максималниот капацитет на низата за бројот с
// оператор за додавање на нов број во низата од позитивни броеви +=
// број се додава ако и само ако
// оператор за множење * за множење на низата со друг цел број
// Пример, низата има објекти (1 2 3 4 5) и истата се множи со 2, резултатот ќе биде (1 2 3 4 5)*2 = (2 3 6 8 10)
// оператор за делење \ за делење на низата до друг цел број
// потребно е секој елемент од низата да биде делив со тој број, како и делителот не смее да биде нула.
// оператор [] за пристап до елемент од низата
// Потребно е да се дефинираат класи за исклучоци и секој од нив да има метод void message() којшто ќе принта соодветна порака кога исклучокот ќе биде фатен. Можните исклучоци се следните:

// ArithmeticException (се фрла ако се проба да се дели со нула)
// принта порака Division by zero is not allowed
// NumbersNotDivisibleException (се фрла ако се проба да се дели низата со некој број, а барем еден елемент од низата не е делив со тој број)
// принта порака Division by number [делителот] is not supported
// ArrayFullException (се фрла ако се проба да се додади елемент во низа која и е исполнет максималниот капацитет)
// принта порака The array is full. Increase the capacity
// IndexOutOfBoundsException (се фрла доколку се проба да се пристапи до елемент од низата со несоодветен индекс)
// принта порака Index [индексот] is out of bounds
// NumberIsNotPositiveException (се фрла доколку се проба да се внесе во низата број што не е позитивен или е нула)
// принта порака Number [бројот] is not positive integer.
// For example:

// Input	
// 3
// 3
// 2
// 4
// 6
// 2
// 2
// 8
// 10

// Result
// ===FIRST ATTEMPT TO ADD NUMBERS===
// Size: 3 Capacity: 3 Numbers: 2 4 6 
// ===INCREASING CAPACITY===
// Size: 3 Capacity: 5 Numbers: 2 4 6 
// ===SECOND ATTEMPT TO ADD NUMBERS===
// Size: 5 Capacity: 5 Numbers: 2 4 6 8 10 
// ===TESTING DIVISION===
// Division by zero is not allowed
// Size: 5 Capacity: 5 Numbers: 2 4 6 8 10 
// Size: 5 Capacity: 5 Numbers: 1 2 3 4 5 
// ===TESTING MULTIPLICATION===
// Size: 5 Capacity: 5 Numbers: 6 12 18 24 30 
// ===TESTING [] ===
// PositiveIntegers[-1] = Index -1 is out of bounds
// PositiveIntegers[2] = 6
// PositiveIntegers[3] = 8
// PositiveIntegers[12] = Index 12 is out of bounds


#include <iostream>
#include <cstring>
using namespace std;


//EXCEPTION HANDLING

class ArithmeticException {
public:
ArithmeticException(){
    message();
}
void message(){
    cout<<"Division by zero is not allowed"<<endl;
}
};

class NumbersNotDivisibleException  {
public:
int delitel;
NumbersNotDivisibleException(int delitel=0){
this->delitel=delitel;
message();
}
void message(){
    cout<<"Division by number "<<delitel<<" is not supported"<<endl;
}
};

class ArrayFullException   {
public:
ArrayFullException(){
    message();
}
void message(){
    cout<<"The array is full. Increase the capacity"<<endl;
}
};

class IndexOutOfBoundsException{
public:
int indeks;
IndexOutOfBoundsException(int indeks=0){
    this->indeks=indeks;
    message();
}
void message(){
    cout<<"Index "<<indeks<<" is out of bounds"<<endl;
}
};

class NumberIsNotPositiveException     {
public:
int brojot;
NumberIsNotPositiveException(int brojot=0){
    this->brojot=brojot;
    message();
}
void message(){
    cout<<"Number "<<brojot<<" is not positive integer."<<endl;
}
};


//MAIN CLASS

class PositiveIntegers{
private:
int *pozitivni;
int broj_elementi;
int maks_kapacitet;

public:
PositiveIntegers(int maks_kapacitet=0){
this->maks_kapacitet=maks_kapacitet;
broj_elementi=0;
pozitivni=new int[broj_elementi];
}

void increaseCapacity(int c){
    maks_kapacitet=maks_kapacitet+c;
}

PositiveIntegers &operator +=( int number ){
    if(broj_elementi == maks_kapacitet){
        throw ArrayFullException();
    }

    if(number <= 0){
        throw NumberIsNotPositiveException(number);
    }

    int *temp=new int[broj_elementi+1];
    for(int i=0;i<broj_elementi;i++){
        temp[i]=pozitivni[i];
    }
    temp[broj_elementi++]=number;
    delete[] pozitivni;
    pozitivni=temp;

    return *this;
};

PositiveIntegers &operator *(int number){

    for(int i=0;i<broj_elementi;i++){
        pozitivni[i]=pozitivni[i]*number;
    }


    return *this;
}

PositiveIntegers &operator /(int number){
    if(number == 0) {
        throw ArithmeticException();
    }

    for(int i=0;i<broj_elementi;i++){
        if(pozitivni[i]&number != 0){
            throw NumbersNotDivisibleException(number);
        }
    }
    for(int i=0;i<broj_elementi;i++){
        pozitivni[i]=pozitivni[i]/number;
    }

    return *this;
}

int &operator [](int number){

    if(number < 0 || number>broj_elementi){
        IndexOutOfBoundsException(number);
    }

    return pozitivni[number] ;
}

void print(){
cout<<"Size: "<<broj_elementi<<" Capacity: "<<maks_kapacitet<<" Numbers: ";
        for (int i=0;i<broj_elementi;i++)
            cout<<pozitivni[i]<<" ";
        cout<<endl;
}

};


int main() {
	
	int n,capacity;
	cin >> n >> capacity;
	PositiveIntegers pi (capacity);
	for (int i=0;i<n;i++){
		int number;
		cin>>number;
		pi+=number;
        
	}
	cout<<"===FIRST ATTEMPT TO ADD NUMBERS==="<<endl;
	pi.print();
	int incCapacity;
	cin>>incCapacity;
	pi.increaseCapacity(incCapacity);
	cout<<"===INCREASING CAPACITY==="<<endl;
	pi.print();
	
	int n1;
	cin>>n1;
	for (int i=0;i<n1;i++){
		int number;
		cin>>number;
		pi+=number;        
	}
	cout<<"===SECOND ATTEMPT TO ADD NUMBERS==="<<endl;
	
    pi.print();	
	PositiveIntegers pi1;
	
	cout<<"===TESTING DIVISION==="<<endl;	
	
	pi1 = pi/0;
	pi1.print();
	
	pi1 = pi/1;
	pi1.print();	
	
	pi1 = pi/2;
	pi1.print();
	
	cout<<"===TESTING MULTIPLICATION==="<<endl;
	pi1 = pi*3;
	pi1.print();
	
	
	cout<<"===TESTING [] ==="<<endl;	
	cout<<"PositiveIntegers[-1] = "<<pi[-1]<<endl;	
	cout<<"PositiveIntegers[2] = "<<pi[2]<<endl;
	cout<<"PositiveIntegers[3] = "<<pi[3]<<endl;	
	cout<<"PositiveIntegers[12] = "<<pi[12]<<endl;
	
	
	
	
	return 0;
}