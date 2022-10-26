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
import static java.lang.Character.toUpperCase;

public class Initials {
    static void printInitials(String name) {
        name=name.toUpperCase();
        char first = name.charAt(0);
        StringBuilder initials= new StringBuilder(String.valueOf(first));
        for(int i=0;i<name.length();i++){
            int Compare = Character.compare(name.charAt(i), ' ');
            if(Compare==0){
             char afterspace = name.charAt(i+1);
             initials.append(afterspace);
            }
        }
        System.out.print(initials);
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

