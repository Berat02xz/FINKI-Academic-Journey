// Да се дефинира апстрактна класа Employee којашто ќе содржи име на вработениот, години и работно искуство во години (integer). Да се дефинираат чисти виртуелни функции plata() и bonus() (double).

// Од класата Employee да се изведе класа SalaryEmployee која покрај основните информации содржи и информација за основната плата. Бонусот на овие работници се пресметува како процент од основната плата, а процентот е бројот на години со работно искуство. На пример ако работеле 10 години, бонусот е 10 проценти од основната плата. Вкупната плата се пресметува како основната плата плус бонусот.

// Од класата Employee исто така да се изведе класа HourlyEmployee која покрај основните информации содржи информација и за вкупниот број на часови кои ги одработил работникот и платата по час. Вкупната плата се пресметува како бројот на часови помножен со платата по час плус бонусот, додека бонусот се пресметува на следниот начин: За секој час над 320-тиот се добива 50 проценти од платата по час.

// Од класата Employee на крај се изведува класата Freelancer која покрај основните информации содржи и број на проекти на кои работел вработениет и низа со суми кои ги добил за тие проекти (double). По направени 5 проекти, за секој нареден вработените добиваат бонус од 1000 денари. Вкупната плата на овој тип на вработени изнесува вкупната сума добиена од сите проекти плус бонусот.

// Да се преоптовари операторот == кој ќе прима два објекти од класата Employee и ќе ги споредува според тоа дали имаат ист број на години и дали добиваат ист бонус.

// Да се дефинира класа Company која ќе содржи информации за името на компанијата, бројот на вработени, и динамична низа од покажувачи од класата Employee или Employee **. За потребите на оваа класа треба да се дефинира конструктор кој прима само еден аргумент - името на компанијата, да се преоптовари операторот += и да се дефинираат следните методи:

// double vkupnaPlata() - метод којшто ја враќа вкупната плата на сите вработени во компанијата
// double filtriranaPlata(Employee * emp) - метод којшто ја враќа платата само на работниците кои се еднакви со дадениот вработен (според оператор ==)
// void pecatiRabotnici() - метод којшто печати по колку вработени има од секој тип на работници во компанијата, а форматот на печатење можете да го видите од тест примерите
// Hint: За потребите на последниот метод можете да искористите статички членови и статички функции во секоја од класата, dynamic_cast, виртуелна функција која ќе ја преоптоварите во секоја од класите или нешто друго по ваша желба.

// For example:

// Input	
// Advokatsko_drustvo_Bambus
// 3
// 1 Lidija_Andova 31 5 16000
// 2 Martin_Kovacev 31 7 336 100
// 2 Tea_Vinarova 29 4 330 60

// Result
// Vo kompanijata Advokatsko_drustvo_Bambus rabotat:
// Salary employees: 1
// Hourly employees: 2
// Freelancers: 0
// Vkupnata plata e: 71300
// Filtriranata plata e: 51200

#include <iostream>
#include<cstring>
#include<cmath>
using namespace std;

class Employee
{
protected:
  char ime[100];
  int godini;
  int rabotno_iskustvo;

public:
//OPERATOR == OVVERIDE
bool operator ==(Employee &E){
if (this->godini == E.godini && bonus() == E.bonus()){
  return true;
} else {
  return false;
}
}


  Employee(char *ime="", int godini=0, int rabotno_iskustvo=0)
  {
    this->godini = godini;
    this->rabotno_iskustvo = rabotno_iskustvo;
    strcpy(this->ime, ime);
  }

  virtual double plata() = 0;
  virtual double bonus() = 0;
};

class SalaryEmployee : public Employee
{
protected:
  double osnovna_plata;

public:
  SalaryEmployee(char *ime="", int godini=0, int rabotno_iskustvo=0, double osnovna_plata=0) : Employee(ime, godini, rabotno_iskustvo)
  {
    this->osnovna_plata = osnovna_plata;
  }

  double plata()
  {
    return osnovna_plata + bonus();
  }
  double bonus()
  {
    return (rabotno_iskustvo * osnovna_plata) / 100;
  }
};

class HourlyEmployee : public Employee
{
protected:
  int casovi;
  double plata_po_cas;

public:
  HourlyEmployee(char *ime="", int godini=0, int rabotno_iskustvo=0, int casovi=0, double plata_po_cas=0) : Employee(ime, godini, rabotno_iskustvo)
  {
    this->casovi = casovi;
    this->plata_po_cas = plata_po_cas;
  }

  double plata()
  {
    return plata_po_cas * casovi + bonus();
  }

  double bonus()
  {
    return (casovi - 320) * 0.5 * plata_po_cas;
  }
};

class Freelancer : public Employee
{
protected:
  int broj_proekti;
  double sumi[100];

public:
  Freelancer(char *ime="", int godini=0, int rabotno_iskustvo=0, int broj_proekti=0, double *sumi=0) : Employee(ime, godini, rabotno_iskustvo)
  {
    this->broj_proekti = broj_proekti;
    for (int i = 0; i < broj_proekti; i++)
    {
      this->sumi[i]=sumi[i];
    }
  }

  double plata()
  {
    int rez = 0;
    for (int i = 0; i < broj_proekti; i++)
    {
      rez += sumi[i];
    }
    return rez + bonus();
  }

  double bonus()
  {
    if (broj_proekti > 5)
      return (broj_proekti - 5) * 1000;
    return 0;
  }
};

class Company
{
private:
  char ime[100];
  int vraboteni;
  Employee **niza;

public:
  Company(char *ime="")
  {
    strcpy(this->ime, ime);
    this->vraboteni = 0;
    niza = new Employee *[0];
  }

  ~Company() { delete[] niza; }

//OPERATOR += FOR ADDING NEW EMPLOYEE
  Company &operator+=(Employee *E)
  {
    //first create new temporary array and put the new employee there
    Employee **temp = new Employee *[vraboteni + 1];

    for (int i = 0; i < vraboteni; i++)
    {
      temp[i] = niza[i];
    }
    temp[vraboteni++] = E;

    //Delete original array and swap the temporary with original
    delete[] niza;
    niza = temp;

    //return *this
    return *this;
  }

double vkupnaPlata(){
  double rez=0;
  for(int i=0;i<vraboteni;i++){
    rez+=niza[i]->plata();
  }
  return rez;
}

double filtriranaPlata(Employee * emp){
  double rez=0;
  for(int i=0;i<vraboteni;i++){
    if(niza[i]==emp){ rez+=niza[i]->plata(); }
  }
  return rez;
}

void pecatiRabotnici()
{
        int countSalary=0,countHourly=0,countFree=0;
        cout<<"Vo kompanijata "<<ime<< " rabotat:"<<endl;
        for(int i=0;i<vraboteni;i++)
        {
            SalaryEmployee *se = dynamic_cast<SalaryEmployee *>(niza[i]);
            if(se != 0)
            {
                countSalary++;
                continue;
            }
            HourlyEmployee *he = dynamic_cast<HourlyEmployee *>(niza[i]);
            if(he != 0)
            {
                countHourly++;
                continue;
            }
            Freelancer *fl=dynamic_cast<Freelancer*>(niza[i]);
            if(fl != 0)
            {
                countFree++;
                continue;
            }

        }
        cout<<"Salary employees: "<<countSalary<<endl;
        cout<<"Hourly employees: "<<countHourly<<endl;
        cout<<"Freelancers: "<<countFree<<endl;
    }



};

int main() {

char name[50];
cin >> name;
Company c(name);

int n;
cin >> n;

char employeeName[50];
int age;
int experience;
int type;

for (int i=0; i <n; ++i) {
  cin >> type;
  cin >> employeeName >> age >> experience;

  if (type == 1) {
    int basicSalary;
    cin >> basicSalary;
    c += new SalaryEmployee(employeeName, age, experience, basicSalary);
  }

  else if (type == 2) {
    int hoursWorked;
    int hourlyPay;
    cin >> hoursWorked >> hourlyPay;
    c += new HourlyEmployee(employeeName, age, experience, hoursWorked, hourlyPay);
  }

  else {
    int numProjects;
    cin >> numProjects;
    double projects[10];
    for (int i=0; i < numProjects; ++i) {
      cin >> projects[i];
    }
    c += new Freelancer(employeeName, age, experience, numProjects, projects);
  }
}

c.pecatiRabotnici();
cout << "Vkupnata plata e: " << c.vkupnaPlata() << endl;
Employee * emp = new HourlyEmployee("Petre_Petrov",31,6,340,80);
cout << "Filtriranata plata e: " << c.filtriranaPlata(emp);

delete emp;
}