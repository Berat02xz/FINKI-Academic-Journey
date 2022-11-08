package Labs3;
/*
Дадени се N точки (N>=2) во дводимензионален простор. Пресметајте кое е најмалото растојание помеѓу две точки.

Влез: Во првиот ред од влезот е даден бројот на точки (N). Потоа, во следните N редови влез, се дадени координатите на секоја точка.

Излез: На излез треба да се испечати минималното растојание помеѓу две точки.

For example:

Input
3
0 1
0 6
0 3

Result
2.00

 */


import java.util.Scanner;

public class MinDistance {

    public static float minimalDistance(float points[][]) {
        //Vasiot kod ovde
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}