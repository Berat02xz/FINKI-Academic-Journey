// Да се дефинира апстрактна класата Task во која што се чува информација за:

// ID (низа од максимум 5 знаци)
// Дополнително апстрактната класа содржи два чисто виртуелни методи:

// int getOrder() којшто го враќа редниот број (редот) на извршување на задачата (Task-oт).
// void print() којшто печати информации за задачата (види тест примери) (1 поен)
// Постојат два вида на таскови (задачи):

// TimedTask во којшто се чува информација за:
// потребното време за извршување на задачата time (цел број)
// PriorityTask во којшто се чува информација за:
// степенот на приоритет priority (цел број)
// Редниот број на извршувањe на TimedTask e дефиниран со времето на извршување, додека пак на PriorityTask со степенот на приоритет. (1 поен)

// За двата типа на таскови да се имплементираат:

// оператор за споредба == којшто ги споредува според нивниот ID и според нивното време/степен на приоритетност. (2 поени)
// препокриени функции од класата Task (2 поени)
// Дополнително, да се имплементираат следните надворешни методи:

// void scheduleTimedTasks (Task ** tasks, int n, int t)- метод којшто ќе ги распореди сите задачи од тип TimedTask со време на извршување помало од t (ќе испечати информации за нив), сортирани во растечки редослед според времето на извршување. Методот прима три аргументи (низа од покажувачи кон Task, бројот на елементи во низата и времетот t) (2 поени)
// void schedulePrioritryTask(Task ** tasks, int n, int p) - метод којшто ќе ги распореди сите задачи од тип PriorityTask со степен на приоритет помал од p (ќе испечати информации за нив), сортирани во растечки редослед според степенот на приоритетност. Методот прима три аргументи (низа од покажувачи кон Task, бројот на елементи во низата и степенот на приоритетност p) (2 поени)
// Напомена: Двете функции може да ги имплементирате и без сортирање, но во тој пример ќе ви излезат 80% тест примери точни.

// For example:

// Input	
// 0
// 5
// 1 A 2
// 1 A 7
// 0 B 8
// 0 C 25
// 1 D 75

// Result
// SCHEDULING PRIORITY TASKS WITH PRIORITY DEGREE LESS THAN 10
// PT->A:2
// PT->A:7

#include<iostream>
#include<cstring>
using namespace std;

class Task{
    protected:
    //deklariranje na promenlivite
    char ID[5];
    public:
    //konstruktor
    Task(char *ID = "") {
        strcpy(this->ID, ID);
    }
    //destruktor
    ~Task(){ delete[] ID; }
    //2 chisto virtuelni metodi (print i getOrder)
    virtual int getOrder() = 0;
    virtual void print() = 0;

    char *getID()
    {
        return ID;
    }
};

//Preoptovaruvanje na operatorot za sporedba == (prima 2 argumenti bidejki se definira nadvor od klasata Task)
bool operator ==(Task *t1, Task &t2){
return (strcmp(t1->getID(), t2.getID())==0 && t1->getOrder() == t2.getOrder());
}

class TimedTask : public Task {//izraz za nasleduvanje od Task
    private:
    //deklariranje na dopolnitelni promenlivi
    int time;
    public:
    //konstruktor
    TimedTask(char *ID="", int time=0): Task(ID){
        this->time=time;
    }
    //prepokrivanje na 2ta metodi shto se nasleduvaat od Task
     int getOrder(){
         return time;
    }
     void print(){
         cout<<"TT->"<<ID<<":"<<getOrder()<<endl;
    }

};

class PriorityTask : public Task {
     private:
    //deklariranje na dopolnitelni promenlivi
    int priority;
    public:
    //konstruktor
    PriorityTask(char *ID="", int priority=0): Task(ID){
        this->priority=priority;
    }
    //prepokrivanje na 2ta metodi shto se nasleduvaat od Task 
     int getOrder(){
         return priority;
    }
     void print(){
         cout<<"PT->"<<ID<<":"<<getOrder()<<endl;
    }
};

//void scheduleTimedTasks(Task ** tasks, int n, int t) {}
void scheduleTimedTasks (Task ** tasks, int n, int t){
 for(int i=0;i<n;i++){
        for(int k=0;k<n;k++){
            if(tasks[i]->getOrder() > tasks[k]->getOrder()){
                Task *temp=tasks[i];
                tasks[k]=tasks[i];
                tasks[k]=temp;
            }
        }
    }

    for(int i=0;i<n;i++){
        TimedTask *d=dynamic_cast<TimedTask*>(tasks[i]);
        if(d!=0) { 
            if(tasks[i]->getOrder() < t) {
                tasks[i]->print();
            } 
        }
    }
}
//void schedulePriorityTasks(Task ** tasks, int n, int p) {}
void schedulePriorityTasks(Task ** tasks, int n, int p){
    for(int i=0;i<n;i++){
        for(int k=0;k<n;k++){
            if(tasks[i]->getOrder() > tasks[k]->getOrder()){
                Task *temp=tasks[i];
                tasks[k]=tasks[i];
                tasks[k]=temp;
            }
        }
    }

    for(int i=0;i<n;i++){
        TimedTask *t=dynamic_cast<TimedTask*>(tasks[i]);
        if(t!=0) { 
            if(tasks[i]->getOrder() < p) {
                tasks[i]->print();
            } 
        }
    }
}

int main () {
    int testCase;
    int n;
    cin>>testCase;
    
    if (testCase==0){
        cin>>n;
    	Task ** tasks;
        tasks = new Task * [n];
    	for (int i=0;i<n;i++){
        char id [5];
        int timeOrPriority;
        int type; //0 za timed, 1 za priority
        cin >> type >>id >> timeOrPriority;
        if (type==0)
        	tasks[i] = new TimedTask(id,timeOrPriority);
        else
            tasks[i] = new PriorityTask(id,timeOrPriority);
        //tasks[i]->print();
    	}
        
    	cout<<"SCHEDULING PRIORITY TASKS WITH PRIORITY DEGREE LESS THAN 10"<<endl;
    	schedulePriorityTasks(tasks,n,10);
        
    }
    else if (testCase==1) {
        cin>>n;
    	Task ** tasks;
        tasks = new Task * [n];
    	for (int i=0;i<n;i++){
        char id [5];
        int timeOrPriority;
        int type; //0 za timed, 1 za priority
        cin >> type >>id >> timeOrPriority;
        if (type==0)
        	tasks[i] = new TimedTask(id,timeOrPriority);
        else
            tasks[i] = new PriorityTask(id,timeOrPriority);
        //tasks[i]->print();
    	}
        
    	
        cout<<"SCHEDULING TIMED TASKS WITH EXECUTION TIME LESS THAN 50"<<endl;
        scheduleTimedTasks(tasks,n,50);
    }
    else {
        TimedTask * tt1 = new TimedTask("11",10);
        TimedTask * tt2 = new TimedTask("11",11);
        TimedTask * tt3 = new TimedTask("11",11);
        PriorityTask * pp1 = new PriorityTask("aa",10);
        PriorityTask * pp2 = new PriorityTask("11",10);
        
        cout << (tt1==(*tt2))<<endl;
        cout << (tt2==(*tt3))<<endl;
        cout << (pp1==(*pp2))<<endl;
        cout << (pp2==(*tt1))<<endl;
    }
    
	return 0;
}