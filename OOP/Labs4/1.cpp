// Да се имплементира класа List во којашто ќе се чуваат информации за:

// броеви што се дел од листата (динамички алоцирана низа од цели броеви)
// бројот на броеви што се дел од листата
// За класата да се дефинираат следните методи:

// конструктор (со аргументи), copy-конструктор, деструктор, оператор =
// void pecati()метод што ќе ги печати информациите 
// за листата во форматот: [број на елементи во листата]: x1 x2 .. xn sum: [сума] average: [просек]
// int sum() метод што ја враќа сумата на елементите во листата
// double average() метод што ќе го враќа просекот на броевите во листата
// Дополнително, креирајте класата ListContainer, во која што ќе се чуваат информации за:

// низа од листи (динамички алоцирана низа од објекти од класата List)
// број на елементи во низата од листи (цел број)
// број на обиди за додавање на листа во контејнерот (цел број првично поставен на нула)
// За класата потребно е да ги дефинирате следните методи:

// конструктор (default), copy-конструктор, деструктор, оператор =
// void pecati() метод што ќе ги печати информациите за
//  контејнерот во форматот: List number: [реден број на листата] List info: [испечатени информации за листата со методот List::pecati()] \n sum: [сума] average: [просек]
// доколку контејнерот е празен се печати само порака The list is empty.
// void addNewList(List l) метод со којшто се додава листа во контејнерот
// Напомена: една листа се додава во контејнерот ако и само ако има различна сума од сите листи што се веќе додадени во контејнерот
// int sum() метод што ја враќа сумата на сите елементи во сите листи
// double average() метод што го враќа просекот на сите елементи во сите листи во контејнерот
// For example:

// Input	
// 5
// 5
// 1 2 3 4 5   
// 2
// 10 5      
// 3
// 1 2 3     
// 6
// 1 4 5 6 7 10 
// 3
// 10 10 13
// 0

// Result
// List number: 1 List info: 5: 1 2 3 4 5 sum: 15 average: 3
// List number: 2 List info: 3: 1 2 3 sum: 6 average: 2
// List number: 3 List info: 6: 1 4 5 6 7 10 sum: 33 average: 5.5
// Sum: 54 Average: 3.85714
// Succe
#include <iostream>
#include <cstring>
using namespace std;

class List
{
private:
    int *array;
    int broj_na_broevi;

public:
    List(){
        this->array=nullptr;
        this->broj_na_broevi=0;
    }

    List(int *array, int broj_na_broevi)
    {
        this->broj_na_broevi = broj_na_broevi;
        this->array = new int[broj_na_broevi];

        for (int i = 0; i < broj_na_broevi; i++)
        {
            this->array[i] = array[i];
        }
    }

    List(const List &L)
    {
        this->broj_na_broevi = L.broj_na_broevi;
        this->array = new int[broj_na_broevi];

        for (int i = 0; i < broj_na_broevi; i++)
        {
            this->array[i] = L.array[i];
        }
    }

    List &operator = (List &L){
    //if the object is the same with the other object
    //Than just return the same object
    //Else take everything from the first and transfer to the second object
    if (this == &L) {
        return *this;
    } else {
    this->broj_na_broevi=L.broj_na_broevi;
    this->array = new int[broj_na_broevi];

        for (int i = 0; i < broj_na_broevi; i++)
        {
            this->array[i] = L.array[i];
        }
    return *this;
    }
    }

    ~List() { delete[] array; }

    void pecati(){
    cout<<broj_na_broevi<<" "<<endl; elementi(); cout<<"sum: "<<endl; sum(); cout<<" average: "<<endl; average();      
    }

    void elementi(){
        for(int i=0;i<broj_na_broevi;i++){
            cout<<this->array[i]<<" "<<endl;
        }
    }

    int sum(){
    int suma=0;
    for(int i=0;i<broj_na_broevi;i++){
        suma=suma+this->array[i];
    }
    return suma;
    }

    double average(){
    int suma=sum();
    return suma/broj_na_broevi;
    }

    int getnum(){
        return this->broj_na_broevi;
    }
};

class ListContainer
{
private:
    List *listi;
    int broj_elemnti;
    int obidi = 0;

public:
    // constructor default
    ListContainer()
    {
        this->listi = nullptr;
        this->broj_elemnti = 0;
        this->obidi = 0;
    }
    // copy constructor
    ListContainer(ListContainer &L)
    {
        this->broj_elemnti = L.broj_elemnti;
        this->obidi = L.obidi;
        // if this object is not the same as the other one, than we copy everything from that one into our object.
        if (this != &L)
        {
            this->broj_elemnti = L.broj_elemnti;

            delete[] this->listi;

            this->listi = new List[broj_elemnti];

            for (int i = 0; i < broj_elemnti; i++)
            {
                this->listi[i] = L.listi[i];
            }

            this->obidi = L.obidi;
        }
    }

    // destructor
    ~ListContainer() { delete[] this->listi; }
    
    // operator = (dodeluvanje)
    ListContainer &operator=(const ListContainer &L)
    {
        if(this != &L){
            this->broj_elemnti = L.broj_elemnti;

            delete[] this->listi;

            this->listi = new List[broj_elemnti];

            for (int i = 0; i < broj_elemnti; i++)
            {
                this->listi[i] = L.listi[i];
            }

            this->obidi = L.obidi;

            return *this;
        }
        else {
            return *this;
        }
    }


    //Metodi----------
    int sum()
    {
        int sum = 0;
        for (int i = 0; i < this->broj_elemnti; i++)
        {
            sum=sum+this->listi[i].sum();
        }
        return sum;
    }

    double average()
    {
        double sredna_vrednost = 0.00;
        int total = 0;

        for (int i = 0; i < this->broj_elemnti; i++)
        {
            total = total + this->listi[i].getnum();
            sredna_vrednost += this->listi[i].sum();
        }
        return sredna_vrednost / total;
    }

    void print()
    {
        if (!this->broj_elemnti)
        {
            cout << "The list is empty" <<endl;
        }
        else
        {
            for (int i = 0; i < this->broj_elemnti; i++)
            {
                cout << "List number: " << i + 1 << " "<<endl;
                this->listi[i].pecati();
            }
            cout << "Sum: " << sum() << " Average: " << average() <<endl;
            cout<< "Successful attempts: " << this->broj_elemnti << " Failed attempts: " << this->obidi <<endl;
        }
    }

    void addNewList(List &L)
    {
        List *tmp = new List[broj_elemnti + 1];
        for (int i = 0; i < broj_elemnti; i++)
        {
            if (L.sum() == this->listi[i].sum())
            {
                this->obidi++;
            }
            tmp[i] = this->listi[i];
        }
        tmp[broj_elemnti++] = L;
        delete[] this->listi;
        this->listi = tmp;
    }
};

int main() {
	
	ListContainer lc;
	int N;	
	cin>>N;	
    
	for (int i=0;i<N;i++) {
		int n;
		int niza[100];
		
		cin>>n;
       
		for (int j=0;j<n;j++){
			cin>>niza[j];
            
		}
       
		List l=List(niza,n);
	
		lc.addNewList(l);
	}	
	
    
    int testCase;
    cin>>testCase;
    
    if (testCase==1) {
        cout<<"Test case for operator ="<<endl;
        ListContainer lc1;
        lc1.print();
        cout<<lc1.sum()<<" "<<lc.sum()<<endl;
        lc1=lc;
        lc1.print();
        cout<<lc1.sum()<<" "<<lc.sum()<<endl;
        lc1.sum();
        lc1.average();
        
    }
    else {
        lc.print();
    }
}

