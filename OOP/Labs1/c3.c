// За програмски јазик C.

// Да се надополни програмата со следните барања:

// да се креира структура на точка во тродимензионален простор и да се напише функција која ќе го пресметува растојанието помеѓу две такви точки.
// да се напише функција која како аргумент прима три точки во дводимензионален простор и ќе проверува дали тие точки лежат на иста права.
// For example:

// Input	
// 0 1 
// 2 2
// 4 3

// Result
// 2.24
// 2.45
// 1

#include <stdio.h>
#include <math.h>

typedef struct Tocka2D{
float x;
float y;
}tocka2D;

//Cartesian Formula 2D
float rastojanie(tocka2D t1, tocka2D t2){
float distance=sqrt( (t2.x - t1.x) * (t2.x - t1.x) + (t2.y - t1.y) * (t2.y - t1.y) );
return distance;
}

typedef struct Tocka3D{
float x;
float y;
float z;
}tocka3D;

//Cartesian Formula 3D
float rastojanie3D(tocka3D t1, tocka3D t2){
float distance=sqrt( (t2.x - t1.x) * (t2.x - t1.x) + (t2.y - t1.y) * (t2.y - t1.y) + (t2.z - t1.z) * (t2.z - t1.z) );
return distance;
}

int ista_prava(tocka2D A, tocka2D B, tocka2D C){
    //Use the concept, if ABC is a straight line than, AB+BC=AC
    int AB=(B.y - A.y) / (B.x - A.x);
    int BC=(C.y - B.y) / (C.x - B.x);
    int AC=(C.y - A.y) / (C.x - A.x);
//if AB+BC=AC than on same line, if not than different line
if (AB+BC == AC){
    return 1;
} else {
    return 0;
}
}

int main() {
    float x1, y1, x2, y2;
    printf("Vnesete x, y:\n");
    scanf("%f %f", &x1, &y1);
    printf("Vnesete x2, y2:\n");
	scanf("%f %f", &x2, &y2);
	tocka2D t1 = { x1, y1 };
	tocka2D t2 = { x2, y2 };
	printf("Rastojanieto pomegu tie dve tocki: %.2f\n", rastojanie(t1, t2));
    float z1, z2;

    printf("Vnesete x, y:\n");
    scanf("%f %f", &z1, &z2);
    tocka3D t3 = {x1, y1, z1};
    tocka3D t4 = {x2, y2, z2};
    printf("Rastojanie vo 3D: %.2f\n", rastojanie3D(t3, t4));
    tocka2D t5 = {z1, z2};
    printf("Ista Prava: %d\n", ista_prava(t1, t2, t5));
	return 0;
}
