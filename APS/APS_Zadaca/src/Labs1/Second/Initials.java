package Labs1.Second;

// За дадено име и презиме на личност кои се внесуваат од стандарден влез,
// да се испечатат иницијалите за таа личност. На влез во првиот ред се дава
// број на личности за кои ќе се внесуваат соодветото име и презиме.
// Во наредните линии се внесуваат имињата и презимињата одделени со празно место.
//
//        For example:
//
//        Input	Result
//        1
//        Steve Jobs
//        SJ


import java.util.Scanner;

public class Initials {

    static void printInitials(String name)
    {

    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int n=input.nextInt();
        String name;
        input.nextLine();

        for(int i=0; i<n; i++){
            name = input.nextLine();
            printInitials(name);
            System.out.println();
        }
    }
}

