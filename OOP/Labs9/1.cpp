// Да се дефинира апстрактна класа User за која ќе се чуваат:

// username (char[50])
// password (char[50])
// email (char[50])
// Класата треба да содржи еден чист виртуелен метод double popularity().

// Од оваа класа да се изведат две класи и тоа FacebookUser и TwitterUser.

// За класата FacebookUser уште се чуваат и:

// број на пријатели
// број на лајкови и
// број на коментари
// Популарноста се пресметува така што се собираат бројот на пријатели, бројот на лајкови и бројот на коментари, но притоа бројот на лајкови се множи со коефициент на вредност на лајкот кој е ист за секој корисник и изнесува 0.1, а исто така и бројот на коментари се множи со ист таков коефициент кој е 0.5.

// За класата TwitterUser уште се чуваат и:

// број на следачи и
// број на твитови
// Популарноста се пресметува така што се собираат бројот на следачи и бројот на твитови, но притоа бројот на твитови се множи со коефициент на вредност на твитот кој е ист за секој корисник и изнесува 0.5.

// Да се креира класа SocialNetwork која ќе содржи:

// динамичка низа од покажувачи од класата User
// број на тековни корисници и
// максимален број на корисници кој може да го содржи мрежата и кој е ист за сите мрежи и иницијално е поставен на 5
// Да се преоптовари операторот += со кој ќе се додава нов корисник.

// Да се дефинира метод avgPopularity() кој ќе ја враќа просечната популарност на корисниците во мрежата.

// Исто така да се овозможи промена на максималната големина на низата преку методот changeMaximumSize(int number).

// Потребно е да се справите со следните исклучоци:

// Доколку лозинката на корисникот не содржи барем 1 голема буква, 1 мала буква и 1 број да се фрли исклучок од класа InvalidPassword така што како параметар ќе се прати пораката Password is too weak.
// Доколку емаилот на корисникот не содржи точно еднаш @ да се фрли исклучок од класа InvalidEmail така што како параметар ќе се прати пораката Mail is not valid.
// Доколку проба да се додаде корисник во социјалната мрежа, а веќе максималниот број на корисници е пополнет да се фрли исклучок од класа MaximumSizeLimit така што како параметар ќе се прати максималниот број на корисници во мрежата.
// Сите класи кои се справуваат со исклучоци треба да го имаат имплементирано методот void message() така што за првите две класи ќе ја печати пораката којашто е испратена како параметар, а за последната класа ќе печати You can't add more than N users, каде што N е параметарот кој е пратен. Исто така со try-catch блокови справете се со исклучоците на соодветните места во main(), каде што во catch ќе го повикате методот message() од соодветниот исклучок.

// For example:

// Input	
// 4
// blazer Gargamel2  blazeryahoo.com 1 123 411 204
// Scooby cart00nNetw0rk scoobydoo@gmail.com 1 282 1098 41
// IronMan Avangers iron@man.com 2 678 1025
// Dexter Massuc0 lisbon@dexter.com 2 418 299
// EdSheeran D1v1d3 edsheeran@sheeran.com 2 10423 188

// Result
// Mail is not valid.
// Password is too weak.
// 2064.43

#include <iostream>
#include <string.h>
using namespace std;

class InvalidPassword
{
private:
    char password[50];
public:
    InvalidPassword(char *password="")
    {
        strcpy(this->password,password);
    }
    void message()
    {
        cout<<password<<endl;
    }
};
class InvalidEmail
{
private:
    char text[50];
public:
    InvalidEmail(char *text="")
    {
        strcpy(this->text,text);
    }
    void message()
    {
        cout<<text<<endl;
    }
};
class MaximumSizeLimit
{
private:
    int n;
public:
    MaximumSizeLimit(int n=0)
    {
        this->n=n;
    }
    void message()
    {
        cout<<"You can't add more than "<<n<<" users."<<endl;
    }
};






class User {
protected:
char Username[50];
char password[50];
char email[50];
public:
User(char *username="", char *password="", char *email=""){
strcpy(this->Username,username);
setPassword(password);
setEmail(email);

}
~User(){}

virtual double popularity() = 0;

//Exception Handling----
void setPassword(char *password){
    int malabukva=0,golemabukva=0,broj=0;
    for(int i=0;i<strlen(password);i++){
        if (password[i]>='A' && password[i]<='Z'){ golemabukva++;}
        if (password[i]>='a' && password[i]<='z'){ malabukva++;}
        if (password[i]>='0' && password[i]<='9'){broj++;}
    }

    if(!malabukva || !golemabukva || !broj){
        throw InvalidPassword("Password is too weak");
    }

    strcpy(this->password,password);
}

void setEmail(char *email){
    int znak=0;

    for(int i=0;i<strlen(email);i++){
        if (email[i] == '@') {
            znak++;
        }
    }
    if(znak==0){
        throw InvalidEmail("Mail is not valid.");
    }


    strcpy(this->email,email);
}


};

class FacebookUser : public User{
protected:
int friends;
int likes;
int comments;
public:
FacebookUser(char *username="", char *password="", char *email="", int friends=0, int likes=0, int comments=0): User(username,password,email) {
this->friends=friends;
this->likes=likes;
this->comments=comments;
} 

double popularity(){
 return (comments*0.5) + (likes * 0.1) + friends;
}


};


class TwitterUser  : public User{
protected:
int followers;
int tweets;
public:
TwitterUser (char *username="", char *password="", char *email="", int followers=0, int tweets=0): User(username,password,email) {
this->tweets=tweets;
this->followers=followers;

} 
~TwitterUser(){}
double popularity(){
 return (tweets * 0.5) + followers;
}
};


class SocialNetwork{
protected:
User **niza;
int users;
//Static Member
static int max_users;


public:
SocialNetwork(){
users=0;
niza=new User*[users];
}

SocialNetwork &operator+=(User *U){
    if(users == max_users)
        {
            throw (MaximumSizeLimit(max_users));
        }

    User **temp = new User*[users+1];
        for(int i=0;i<users;i++)
        {
            temp[i]=niza[i];
        }
        temp[users++]=U;
        delete[] niza;
        niza=temp;
        return *this;
}

double avgPopularity(){
    double maks_popularity=0;
    for(int i=0;i<users;i++){
        maks_popularity=maks_popularity+niza[i]->popularity();
    }

    return maks_popularity/users;
}

void changeMaximumSize(int number){
    SocialNetwork::max_users=number;
}


};

//Static Declaration
int SocialNetwork::max_users =5;


///////////////////////////////////////////
int main() {

  SocialNetwork network = SocialNetwork();
    int n;
    cin >> n;
    char username[50];
    char password[50];
    char email[50];
    int userType;
    for (int i=0; i < n; ++i) {
      cin >> username;
      cin >> password;
      cin >> email;
      cin >> userType;
      if (userType == 1) {
        int friends;
        int likes;
        int comments;
        cin >> friends >> likes >> comments;
          
        // TODO: Try-catch
        try{
        User * u = new FacebookUser(username,password,email,friends,likes,comments);
        network += u;
        }
        catch(InvalidPassword &IP){
            IP.message();
        }
        catch(InvalidEmail &IM){
            IM.message();
        }
        catch(MaximumSizeLimit &MS){
            MS.message();
        }


      }
      else {
        int followers;
        int tweets;
        cin >> followers >> tweets;
          
        // TODO: Try-catch  
        try{
        User * u= new TwitterUser(username,password,email,followers,tweets);
        network += u;
        }
        catch(InvalidPassword &IP){
            IP.message();
        }
        catch(InvalidEmail &IM){
            IM.message();
        }
        catch(MaximumSizeLimit &MS){
            MS.message();
        }




      }

    }
    network.changeMaximumSize(6);
    cin >> username;
    cin >> password;
    cin >> email;
    int followers;
    int tweets;
    cin >> followers >> tweets;
    
    // TODO: Try-catch
try{
    User * u= new TwitterUser(username,password,email,followers,tweets);
    network += u;
}
catch(InvalidPassword &IP){
            IP.message();
        }
        catch(InvalidEmail &IM){
            IM.message();
        }
        catch(MaximumSizeLimit &MS){
            MS.message();
        }

    cout << network.avgPopularity();

}
