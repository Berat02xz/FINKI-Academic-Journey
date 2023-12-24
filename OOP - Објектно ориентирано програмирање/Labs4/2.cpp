// Потребно е да се имплементираат класи File (датотека) и Folder (директориум) за работа со едноставен датотечен систем.

// Во класата File треба да се чуваат следниве податоци:

// Име на датотеката (динамички алоцирана низа од знаци)
// Екстензија на датотеката (енумерација со можни вредности: txt, pdf, exe)
// Име на сопственикот на датотеката (динамички алоцирана низа од знаци)
// Големина на датотеката (цел број на мегабајти)
// Дополнително, во класата потребно е да се напишат и:

// Конструктор без параметри
// Конструктор со параметри
// Конструктор за копирање
// Деструктор
// Преоптоварување на операторот =
// Метод за печатење на информациите за една датотека (видете го излезот од тест примерите за структурата на печатење) - print()
// Метод за проверка на еднаквост помеѓу две датотеки со потпис bool equals(const File & that) кој ќе враќа true ако датотеките имаат исто име, екстензија и сопственик
// Метод за споредба на типот помеѓу две датотеки со потпис bool equalsType(const File & that) кој ќе враќа true ако датотеките се од ист тип, т.е. имаат исто име и екстензија
// Во класата Folder треба да се чуваат следниве податоци:

// Име на директориумот (динамички алоцирана низа од знаци)
// Број на датотеки во него (на почеток директориумот е празен)
// Динамички алоцирана низа од датотеки, објекти од класата File
// Дополнително, во класата потребно е да се напишат и:

// Конструктор со потпис Folder(const char* name)
// Деструктор
// Метод за печатење на информациите за еден директориум (видете го излезот од тест примерите за структурата на печатење) - print()
// Метод за бришење на датотека од директориумот со потпис void remove(const File & file) кој ќе ја избрише првата датотека од директориумот која е еднаква според equals методот
// Метод за додавање на датотека во директориумот со потпис void add(const File & file) кој ќе додава датотеката во директориумот
// Не го менувајте почетниот код.

// For example:

// Input	
// 1
// rezultati_oop
// FINKI
// 2
// 0

// Result
// ======= FILE CONSTRUCTORS AND = OPERATOR =======
// ======= CREATED =======
// File name: rezultati_oop.pdf
// File owner: FINKI
// File size: 2

// ======= COPIED =======
// File name: rezultati_oop.pdf
// File owner: FINKI
// File size: 2

// ======= ASSIGNED =======
// File name: rezultati_oop.pdf
// File owner: FINKI
// File size: 2

#include<iostream>
#include<cstring>
using namespace std;

enum Extension {txt, pdf, exe};

class File
{
private:
    char *ime;
    Extension tip;
    char *sopstvenik;
    int MB;

public:
    File() {}
    ~File()
    {
        delete[] ime;
        delete[] sopstvenik;
    }
    File(char *ime, char *sopstvenik, int MB, Extension tip)
    {
        this->ime = new char[strlen(ime) + 1];
        strcpy(this->ime, ime);

        this->sopstvenik = new char[strlen(sopstvenik) + 1];
        strcpy(this->sopstvenik, sopstvenik);

        this->tip = tip;
        this->MB = MB;
    }
    File(const File &F)
    {
        this->ime = new char[strlen(F.ime) + 1];
        strcpy(this->ime, F.ime);

        this->sopstvenik = new char[strlen(F.sopstvenik) + 1];
        strcpy(this->sopstvenik, F.sopstvenik);

        this->tip = F.tip;
        this->MB = F.MB;
    }

    File &operator=(const File &F)
    {

        this->ime = new char[strlen(F.ime) + 1];
        strcpy(this->ime, F.ime);

        this->sopstvenik = new char[strlen(F.sopstvenik) + 1];
        strcpy(this->sopstvenik, F.sopstvenik);

        this->tip = F.tip;
        this->MB = F.MB;

        return *this;
    }

    void print(){
    char ext[4];
        if(this->tip==0) strcpy(ext,"pdf");
        if(this->tip==1) strcpy(ext,"txt");
        if(this->tip==2) strcpy(ext,"exe");
    cout<<"File name: "<<this->ime<<"."<<ext<<endl;
    cout<<"File owner: "<<this->sopstvenik<<endl;
    cout<<"File size: "<<this->MB<<endl;
    }

    bool equals(const File &that)
    {
        if (this->ime == that.ime && this->sopstvenik == that.sopstvenik && this->tip == that.tip)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    bool equalsType(const File & that){
        if (this->tip==that.tip){
            return true;
        } else {
            return false;
        }
    }
};


class Folder
{
private:
    char *imedir;
    int brojdatoteki = 0;
    File *file;

public:
    Folder(char *name)
    {
        this->imedir = new char[strlen(name) + 1];
        this->imedir = name;

        this->file = new File[brojdatoteki];
    }
    ~Folder()
    {
        delete[] imedir;
        delete[] file;
    }

    void print()
    {
        cout << "Folder name: " << this->imedir<<endl;
        for (int i = 0; i < this->brojdatoteki; i++)
        {
            file[i].print();
        }
    }

    void remove(const File & file)
    {
        for (int i = 0; i < this->brojdatoteki; i++)
        {
            if (this->file[i].equals(file))
            {
                this->brojdatoteki = brojdatoteki - 1;
                for (int j = i; j < this->brojdatoteki; j++)
                {
                    this->file[j] = this->file[j + 1];
                }
                break;
            }
        }

        this->brojdatoteki = brojdatoteki - 1;
    }

    void add(const File &file)
    {
        File *tmp = new File[brojdatoteki + 1];
        for (int i = 0; i < this->brojdatoteki; i++)
        {
            tmp[i] = this->file[i];
        }
        tmp[brojdatoteki++] = file;
        this->file = tmp;
    }
};

int main() {
	char fileName[20];
	char fileOwner[20];
	int ext;
	int fileSize;

	int testCase;
	cin >> testCase;
	if (testCase == 1) {
		cout << "======= FILE CONSTRUCTORS AND = OPERATOR =======" << endl;
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File created = File(fileName, fileOwner, fileSize, (Extension) ext);
		File copied = File(created);
		File assigned = created;

		cout << "======= CREATED =======" << endl;
		created.print();
		cout << endl;
        cout << "======= COPIED =======" << endl;
		copied.print();
		cout << endl;
        cout << "======= ASSIGNED =======" << endl;
		assigned.print();
	}
	else if (testCase == 2) {
		cout << "======= FILE EQUALS AND EQUALS TYPE =======" << endl;
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File first(fileName, fileOwner, fileSize, (Extension) ext);
		first.print();

		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;	

		File second(fileName, fileOwner, fileSize, (Extension) ext);
		second.print();

		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File third(fileName, fileOwner, fileSize, (Extension) ext);
		third.print();

		bool equals = first.equals(second);
		cout << "FIRST EQUALS SECOND: ";
		if (equals)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		equals = first.equals(third);
		cout << "FIRST EQUALS THIRD: ";
		if (equals)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		bool equalsType = first.equalsType(second);
		cout << "FIRST EQUALS TYPE SECOND: ";
		if (equalsType)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		equalsType = second.equals(third);
		cout << "SECOND EQUALS TYPE THIRD: ";
		if (equalsType)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

	}
	else if (testCase == 3) {
		cout << "======= FOLDER CONSTRUCTOR =======" << endl;
		cin >> fileName;
		Folder folder(fileName);
		folder.print();

	}
	else if (testCase == 4) {
		cout << "======= ADD FILE IN FOLDER =======" << endl;
		char name[20];
		cin >> name;
		Folder folder(name);

		int iter;
		cin >> iter;

		while (iter > 0) {
			cin >> fileName;
			cin >> fileOwner;
			cin >> fileSize;
			cin >> ext;

			File file(fileName, fileOwner, fileSize, (Extension) ext);
			folder.add(file);
			iter--;
		}
		folder.print();
	}
	else {
		cout << "======= REMOVE FILE FROM FOLDER =======" << endl;
		char name[20];
		cin >> name;
		Folder folder(name);

		int iter;
		cin >> iter;

		while (iter > 0) {
			cin >> fileName;
			cin >> fileOwner;
			cin >> fileSize;
			cin >> ext;

			File file(fileName, fileOwner, fileSize, (Extension) ext);
			folder.add(file);
			iter--;
		}
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File file(fileName, fileOwner, fileSize, (Extension) ext);
		folder.remove(file);
		folder.print();
	}
	return 0;
}