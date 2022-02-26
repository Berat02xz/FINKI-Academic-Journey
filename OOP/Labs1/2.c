// За програмски јазик C.

// Да се напише структура која ќе опишува отсечка во дводимензионален простор (две точки).
// Потоа да се напише функција која ќе проверува дали две отсечки се сечат.
// For example:

// Input	    Result
// -2 -2 2 0    0
//  0  1 1 3


#include <stdio.h>
#include <string.h>

//структурата
typedef struct Tocka{
float x;
float y;
}tocka;

typedef struct otsecka{
     tocka t1;
     tocka t2;
}otsecka;


//методи
void se_secat(otsecka o1,otsecka o2){
    printf("%f", o1.t1.x );
}

//главна програма
int main() {
    double x1, y1, x2, y2;
    scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
    tocka t1 = { x1, y1 };
    tocka t2 = { x2, y2 };
    otsecka o1 = { t1, t2 };

    scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
    tocka t3 = { x1, y1 };
    tocka t4 = { x2, y2 };
    otsecka o2 = { t3, t4 };
    printf("direktno : %f \n", o1.t1.x );
    se_secat(o1, o2);

    return 0;
}