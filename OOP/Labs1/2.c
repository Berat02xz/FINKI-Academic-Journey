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
int max(int a, int b){
    if(a>=b){
        return a;
    } else {
        return b;
    }
}

int min(int a, int b){
    if(a>=b){
        return b;
    } else {
        return a;
    }
}


int onLine(otsecka l1, tocka p) {   //check whether p is on the line or not
   if(p.x <= max(l1.t1.x, l1.t2.x) && p.x <= min(l1.t1.x, l1.t2.x) &&
      (p.y <= max(l1.t1.y, l1.t2.y) && p.y <= min(l1.t1.y, l1.t2.y)))
      return 1;
   
   return 0;
} 

int direction(tocka a, tocka b, tocka c) {
   int val = (b.y-a.y)*(c.x-b.x)-(b.x-a.x)*(c.y-b.y);
   if (val == 0){
      return 0; }   //colinear
   else if(val < 0){
      return 2; }  //anti-clockwise direction
      return 1;    //clockwise direction
}

int isIntersect(otsecka l1,otsecka l2) {
   //four direction for two lines and points of other line
   int dir1 = direction(l1.t1, l1.t2, l2.t1);
   int dir2 = direction(l1.t1, l1.t2, l2.t2);
   int dir3 = direction(l2.t1, l2.t2, l1.t1);
   int dir4 = direction(l2.t1, l2.t2, l1.t2);
   
   if(dir1 != dir2 && dir3 != dir4) {
      return 1; //they are intersecting
   }
   if(dir1==0 && onLine(l1, l2.t1)) //when p2 of line2 are on the line1
    {  return 1; }

   if(dir2==0 && onLine(l1, l2.t2)) //when p1 of line2 are on the line1
    {  return 1; }

   if(dir3==0 && onLine(l2, l1.t1)) //when p2 of line1 are on the line2
     { return 1; }

   if(dir4==0 && onLine(l2, l1.t2)) //when p1 of line1 are on the line2
     { return 1;}
         
   return 0;
}


//главна програма
int main() {
    float x1, y1, x2, y2;
    scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
    tocka t1 = { x1, y1 };
    tocka t2 = { x2, y2 };
    otsecka o1 = { t1, t2 };

    scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
    tocka t3 = { x1, y1 };
    tocka t4 = { x2, y2 };
    otsecka o2 = { t3, t4 };
    
    printf("%d",isIntersect(o1, o2) );

    return 0;
}