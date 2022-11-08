package Labs3;

import java.util.Scanner;

/*
Дадено ни е со какви типови на различни монети (различни вредности) располагаме, како и одредена сума. Со тие информации, да се пресмета кој е минималниот број на монети со коишто можеме да ја формираме таа сума, користејќи greedy пристап.

Влез: Во првиот ред одделени со празно место се дадени различните вредности на монети со коишто располагаме. Во вториот ред е дадена бараната сума.

Излез: Минималниот број на монети со коишто може да се формира сумата.

For example:

Input
5 1 2 10
19

Result
4

 */


public class GreedyCoins {

    public static int minNumCoins(int coins[], int sum) {
        //Vasiot kod ovde
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();

        System.out.println(minNumCoins(coins[], sum));
    }
}