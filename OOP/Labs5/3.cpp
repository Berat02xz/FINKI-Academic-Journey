// Да се напише класа за матрица. Во класата се чуваат елементите од матрицата од тип float (матрица со максимална димензија [10]x[10]) и големината на матрицата (број на редици и колони). За оваа класа да се преоптоварат следните оператори:

// оператор + за собирање матрица со број
// оператор - за одземање на матрици
// оператор * за множење на матрици
// операторот >> за внесување на елементите на матрицата
// операторот << за печатење на елементите на матрицата
// Во главната функција да се креираат објекти A, B и C со подразбирливиот конструктор на класата Matrica. Од стандарден влез да се прочитаат нивните вредности. Да се отпечати вредноста на изразот A-(B*C)+2 на стандарден излез.

// Да се претпостави дека секогаш матриците ќе бидат квадратни со ист број на редици и колони.

// For example:

// Input	
// 4
// 4
// 2 3 -1 1
// 0 2 1 1
// 0 2 3 2 
// 2 1 -1 1

// 4
// 4
// 1 0 -1 1
// 1 2 1 1
// 0 2 0 2 
// 2 1 -1 0

// 4
// 4
// 2 0 0 0
// 0 2 0 0
// 0 0 1 0 
// 0 0 -0 1

// Result
// 2 5 2 2 
// 0 0 2 2 
// 2 0 5 2 
// 0 1 2 3 

#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;
//vashiot kod ovde
class Matrica{
    private:
    float elementi[10][10];
    int golemina;
    public:

    Matrica &operator +(const Matrica &M){
        for(int i=0;i<golemina;i++){
            for(int j=0;j<golemina;j++){
                this->elementi[i][j]+=M.elementi[i][j];
            }
        }
        return *this;
    }

    
    Matrica &operator -(const Matrica &M){
        
        for(int i=0;i<golemina;i++){
            for(int j=0;j<golemina;j++){
                this->elementi[i][j]-=M.elementi[i][j];
            }
        }
        return *this;
    }

    
    Matrica &operator *(const Matrica &M){
        
         float temp[golemina][golemina];
            for(int i=0;i<golemina;i++){
                for(int j=0;j<golemina;j++){
                    int zbir = 0;
                    for(int k=0;k<golemina;k++){
                        zbir+=(this->elementi[i][k]*M.elementi[k][j]);
                    }
                    temp[i][j]=zbir;
                }
            }


            for(int i=0;i<golemina;i++){
                for(int j=0;j<golemina;j++){
                    this->elementi[i][j]=temp[i][j];
                }
            }
            return *this;
    }

    
    friend istream &operator >>(istream &IS, Matrica &M){
        int goleminae;
        IS>>goleminae;
        IS>>goleminae;
        M.golemina=goleminae;
        for(int i=0;i<goleminae;i++){
            for(int j=0;j<goleminae;j++){
                int element;
                IS>>element;
                M.elementi[i][j]=element;
            }
        }

        return IS;
    }

    
    friend ostream &operator <<(ostream &OS,const Matrica &M){
        for(int i=0;i<M.golemina;i++){
            for(int j=0;j<M.golemina;j++){
                OS<<M.elementi[i][j]<<" ";
            }
            OS<<endl;
        }

        return OS;
    }
    

    //default constructor kaj shto site elementi se stavat na 0
    Matrica(int num=0){
        this->golemina=10;
        for(int i=0;i<golemina;i++){
            for(int j=0;j<golemina;j++){
            this->elementi[i][j]=num;
            }
        }
    }

};




int main()
{
    Matrica A,B,C;
    cin>>A>>B>>C;
    Matrica D=B*C;
    Matrica R=A-D+2;
    cout<<R;
}