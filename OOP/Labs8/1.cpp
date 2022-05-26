// Следете ги упатствата во кодот.

// For example:

// Input
// 0

// Result
// Drawing a shape.
// Drawing a rectangle.


#include <iostream>

using namespace std;

class Shape {
public:
    virtual void draw() {
        cout << "Drawing a shape.\n";
    }
};

// креирајте класа Rectangle која наследува од Shape 
class Rectangle : public Shape{
public:
void draw(){
        cout << "Drawing a rectangle.\n";
}
};
    // во неа препокријте ја функцијата draw() така што ќе се испечати: "Drawing a rectangle.\n"


int main() {
    Shape* shape;

    // тука инстанцирајте објект од класата Shape и покажувачот shape нека покажува кон него
    Shape sh;
    shape = &sh;

    shape->draw();

    // сега инстанцирајте објект од класата Rectangle и покажувачот shape нека покажува кон него
    // повторно повикајте ја функцијата draw() на shape
    Rectangle rec;
    shape = &rec;
    shape->draw();

    return 0;
}