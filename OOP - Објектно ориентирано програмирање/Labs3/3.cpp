// Во оваа задача потребно е да се внесат податоци за насловна страница на списание.

// За претставување на насловната страница напишете класа FrontPage која ќе содржи:

// објект од класата NewsArticle која ја претставува насловната вест на страницата
// цена (float price) со предодредена вредност 0
// број на издание на списанието (int editionNumber) со предодредена вредност 0
// За класата FrontPage напишете предодреден (default) конструктор и конструктор со параметри. Класата NewsArticle треба да содржи:

// објект од класата Category која ја претставува категоријата во која спаѓа веста
// наслов од максимум 30 знаци (char title[30]) со предодредена вредност untitled
// За класата NewsArticle напишете предодреден конструктор и конструктор со параметри.

// Класата Category треба да содржи име од максимум 20 знаци (char name[20]) со предодредена вредност unnamed.

// За сите класи треба да напишете соодветен метод за печатење print().

// Внимајте на редоследот на параметрите во конструкторите. Не го менувајте main методот.

// За категоријата се печати само името: Category: [name].

// За веста се печати насловот, па категоријата во нов ред:

// Title: [title]

// category.print()

// За насловната страница се печати цената и изданието во прв ред, па веста во втор:

// Price: [price], Edition number: [editionNumber]

// article.print()

// For example:

// Input	
// 2
// sport
// 120
// 2

// Result
// Price: 120, Edition number: 2
// Article title: untitled
// Category: sport

#include<iostream>
#include<cstring>
using namespace std;

class Category
{
private:
	char name[20];

public:
	Category()
	{
		strcpy(this->name, "unnamed");
	}

	Category(char *name)
	{
		strcpy(this->name, name);
	}
	~Category() {}

	void print(){
		cout<<"Category: "<<this->name<<endl;
	}
};

class NewsArticle
{
private:
	Category Kategorija;
	char title[100]="untitled";

public:
	NewsArticle() {
		strcpy(this->title,"untitled");
	}
	NewsArticle(Category Kategorija, char *title)
	{
		strcpy(this->title, title);
		this->Kategorija = Kategorija;
	}
	NewsArticle(Category Kategorija) { this->Kategorija = Kategorija; }
	~NewsArticle() {}

//Mnogu biten metod za povikanje na objekt (Klasa)
	Category getC() { return this->Kategorija; }

	void print() { cout << "Article title: " << this->title << endl; }
};

class FrontPage
{
private:
	NewsArticle Article;
	float price = 0;
	int editionNumber = 0;

public:
	FrontPage() {}
	FrontPage(NewsArticle Article, float price, int editionNumber)
	{
		this->Article = Article;
		this->price = price;
		this->editionNumber = editionNumber;
	}
	FrontPage(NewsArticle Article, float price)
	{
		this->Article = Article;
		this->price = price;
	}
	~FrontPage() {}

	void print()
	{
		cout << "Price: " << this->price << ", Edition number: " << this->editionNumber <<endl;
		Article.print();
		Article.getC().print();
	}
};

int main() {
	char categoryName[20];
	char articleTitle[30];
	float price;
	int editionNumber;


	int testCase;
	cin >> testCase;


	if (testCase == 1) {
		int iter;
		cin >> iter;
		while (iter > 0) {
			cin >> categoryName;
			cin >> articleTitle;
			cin >> price;
			cin >> editionNumber;
			Category category(categoryName);
			NewsArticle article(category, articleTitle);
			FrontPage frontPage(article, price, editionNumber);
			frontPage.print();
			iter--;
		}
	}
	else if (testCase == 2) {
		cin >> categoryName;
		cin >> price;
		cin >> editionNumber;
		Category category(categoryName);
		NewsArticle article(category);
		FrontPage frontPage(article, price, editionNumber);
		frontPage.print();
	}// test case 3
	else if (testCase == 3) {
		cin >> categoryName;
		cin >> articleTitle;
		cin >> price;
		Category category(categoryName);
		NewsArticle article(category, articleTitle);
		FrontPage frontPage(article, price);
		frontPage.print();
	}
    else {
    	FrontPage frontPage = FrontPage();
        frontPage.print();
    }
	return 0;
}