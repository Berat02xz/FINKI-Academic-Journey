// Потребно е да се напише класа за работа со комплексни броеви ComplexNumber. Во неа се чуваат две променливи кои можат да бидат децимални броеви. Едната го претставува реалниот дел од комплексниот број - real, а другата имагинарниот дел - imaginary. За класата да се напише:

// конструктор со параметри и со предодредени вредности - нули
// метод со потпис double module() кој го враќа модулот на комплексниот број. Модул се пресметува според следната формула:

// sqrt(pow(real, 2) + pow(imaginary, 2))

// Дополнително, потребно е да се преоптоварат следниве оператори:

// оператор за доделување вредност =
// оператор за печатење на стандарден излез << кој ќе го печати бројот во формат [real]+[imaginary]i. Доколку реалниот дел е 0, се печати само [imaginary]i, a доколку имагинарниот дел е 0, се печати само [real].
// оператор за собирање на комплексни броеви +. Резултатот е нов комплексен број, чиј реален и имагинарен дел се збир од соодветните делови од собироците
// оператор за одземање на комплексни броеви -. Резултатот е нов комплексен број, чиј реален и имагинарен дел се разлика од соодветните делови од собироците
// оператор за множење на комплексни броеви *. Резултатот е нов комплексен број, чиј реален и имагинарен дел се производ од соодветните делови од множителите
// оператор за делење на комплексни броеви /. Резултатот е нов комплексен број, чиј реален и имагинарен дел се количник од соодветните делови од делителите
// оператор за еднаквост на комплексни броеви ==. Два комплексни броеви се еднакви ако и само ако се еднакви нивните реални и имагинарни делови
// оператори за споредба на комплексни броеви > и <. Комплексните броеви се споредуваат според вредноста на нивниот реален дел. Ако тој е еднаков, тогаш се споредуваат според вредноста на имагинарниот дел
// Целосна функционалност на класата носи (5 поени).

// Потоа, потребно е да се напише класа за работа со равенки од комплексни броеви Equation. Во неа ќе се чуваат динамички алоцирана низа од комплексни броеви (објекти од класата ComplexNumber) и динамички алоцирана низа од знаци char - аритметички оператори за операции меѓу броевите.

// Следно, треба да се преоптовари операторот за читање од стандарден влез >> кој ќе чита податоци за равенката. Прво се вчитуваат податоци за еден комплексен број (прво реален па имагинарен дел), па оператор, па повторно комплексен број, повторно оператор итн. сѐ додека не се прочита знакот =. Со вчитување на знакот = (на местото за оператор) читањето од влез завршува (2 поени).

// За класата да се напишат и следниве методи:

// ComplexNumber result() кој ќе го врати резултатот од равенката. При пресметувањето на резултатот сите аритметички оператори (+, -, *, /) имаат еднаква предност, т.е. не треба да внимавате да извршувате множење пред собирање или слично, туку ги извршувате операциите во оној редослед во кој сте ги добиле при вчитување од стандарден влез. (2 поени)
// double sumModules() кој ќе врати збир од модулите на сите комплексни броеви во равенката. (1 поен)
// Забелешка: Не го менувајте почетниот код!

// For example:

// Input	
// 8
// 1.2
// 1.4
// 2.5
// 2.5

// Result
// ===== MODULE =====
// 1.2+1.4i
// Module: 1.84391
// 2.5+2.5i
// Module: 3.53553

#include<iostream>
#include<cmath>
using namespace std;

class ComplexNumber
{
private:
	double real;
	double imaginary;

public:
	ComplexNumber(double real = 0, double imaginary = 0)
	{
		this->imaginary = imaginary;
		this->real = real;
	}

	double module(){
	return sqrt(pow(real, 2) + pow(imaginary, 2));
	}

	 friend ostream &operator << (ostream &OS, const ComplexNumber &C){
		 OS<<C.real;
		 OS<<C.imaginary;
		 return OS;
	}

	ComplexNumber &operator ==(const ComplexNumber &C){
		if(this == &C) {return *this; }
		this->imaginary=C.imaginary;
		this->real=C.real;
		return *this;
	}

	void setReal(float real){
            this->real=real;
        }

    void setImaginary(float imaginary){
            this->imaginary=imaginary;
        }

	ComplexNumber &operator + (const ComplexNumber &C){
		this->imaginary=this->imaginary+C.imaginary;
		this->real=this->real+C.real;
		return *this;
	}
	ComplexNumber &operator-(ComplexNumber &num){
            this->real -= num.real;
            this->imaginary -= num.imaginary;
            return *this;
        }
        ComplexNumber &operator*(ComplexNumber &num){
            this->real *= num.real;
            this->imaginary *= num.imaginary;
            return *this;
        }
        ComplexNumber &operator/(ComplexNumber &num){
            this->real /= num.real;
            this->imaginary /= num.imaginary;
            return *this;
        }

	  bool operator==(ComplexNumber &C){
		if(this->imaginary==C.imaginary && this->real==C.real){
			return true;
		} else {
			return false;
		}
	}

	bool operator<(ComplexNumber &C){
		if(real<C.real) return true;
            else if(real<C.real) return false;
            return imaginary<C.imaginary;
	}

	bool operator>(ComplexNumber &C){
            if(real>C.real) return true;
            else if(real<C.real) return false;
            return imaginary>C.imaginary;
        }

};

class Equation{
private:
	ComplexNumber *nums;
	char *operators;
public:
	Equation(){
		this->operators=nullptr;
		this->nums=nullptr;
	}
	friend istream &operator >> (istream &IS, Equation &E){
		char op='o';
		int index=0;
		float number;

		while(op != '='){
		ComplexNumber *temp=new ComplexNumber[index+1];
		char *tempc=new char[index+1];

		for(int i=0;i<index;i++){
		temp[i]=E.nums[i];
		tempc[i]=E.operators[i];
		}
			IS>>number;
			temp[index].setReal(number);
			IS>>number;
			temp[index].setImaginary(number);
			IS>>op;
			tempc[index]=op;

			index++;

			E.nums=temp;
			E.operators=tempc;
		}

		return IS;
	}

	ComplexNumber result(){
		int index = 0;
            ComplexNumber result = this->nums[0];
            while (this->operators[index]!='='){
                switch (this->operators[index]){
                    case '+':
                        result=result+this->nums[index+1];
                        break;
                    case '-':
                        result=result-this->nums[index+1];
                        break;
                    case '*':
                        result=result*this->nums[index+1];
                        break;
                    case '/':
                        result=result/this->nums[index+1];
                        break;
                }
                index++;
            }
            return result;
	}

		double sumModules(){
            double sum = 0;
            sum+=this->nums[0].module();
            for(int i=0;this->operators[i]!='=';i++){
                sum+=this->nums[i+1].module();
            }
            return sum;
        }
}

// Не го менувајте main методот.

int main() {
	int testCase;
	double real, imaginary;
	ComplexNumber first, second, result;

	cin >> testCase;

	if (testCase <= 8) {
		cin >> real;
		cin >> imaginary;
		first = ComplexNumber(real, imaginary);
		cin >> real;
		cin >> imaginary;
		second = ComplexNumber(real, imaginary);
	}

	if (testCase == 1) {
		cout << "===== OPERATOR + =====" << endl;
		result = first + second;
		cout << result;
	}
	else if (testCase == 2) {
		cout << "===== OPERATOR - =====" << endl;
		result = first - second;
		cout << result;
	}
	else if (testCase == 3) {
		cout << "===== OPERATOR * =====" << endl;
		result = first * second;
		cout << result;
	}
	else if (testCase == 4) {
		cout << "===== OPERATOR / =====" << endl;
		result = first / second;
		cout << result;
	}
	else if (testCase == 5) {
		cout << "===== OPERATOR == =====" << endl;
		cout << first;
		cout << second;
		if (first == second)
			cout << "EQUAL" << endl;
		else
			cout << "NOT EQUAL" << endl;
	}
	else if (testCase == 6) {
		cout << "===== OPERATOR > =====" << endl;
		cout << first;
		cout << second;
		if (first > second)
			cout << "FIRST GREATER THAN SECOND" << endl;
		else
			cout << "FIRST LESSER THAN SECOND" << endl;
	}
	else if (testCase == 7) {
		cout << "===== OPERATOR < =====" << endl;
		cout << first;
		cout << second;
		if (first < second)
			cout << "FIRST LESSER THAN SECOND" << endl;
		else
			cout << "FIRST GREATER THAN SECOND" << endl;
	}
	else if (testCase == 8) {
		cout << "===== MODULE =====" << endl;
		double module = first.module();
		cout << first;
		cout << "Module: " << module << endl;
		cout << second;
		module = second.module();
		cout << "Module: " << module << endl;
	}
	else if (testCase == 9) {
		cout << "===== OPERATOR >> & SUM OF MODULES =====" << endl;
		Equation equation;
		cin >> equation;
		cout << equation.sumModules();
	}
	else if (testCase == 10) {
		cout << "===== EQUATION RESULT =====" << endl;
		Equation equation;
		cin >> equation;
		result = equation.result();
		cout << result;
	}
	return 0;
}
