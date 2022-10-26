package Labs1.Third;
//За потребите на софтвер за евиденција на работно време, да се дефинира класа RabotnaNedela во која се чуваат бројот на работни часови за секој работен ден во неделата (5 дена) и број на неделата.
//
//Потоа да се дефинира друга класа Rabotnik за кој се чува име (string ) и низа од работни недели (макс 4). За класите да се имплементираат соодветните конструктори и методи за правилно извршување на програмата.
//
//Да се имплементираат следните барања:
//
//• Метод int sumNedeli(Rabotnik r) кој ќе врати сума од сите работни часови во сите недели за дадениот работник
//
//• Метод Rabotnik najvreden_rabotnik(Rabotnik [] niza) кој за дадената низа од работници ќе го врати работникот со најмногу работни часови (од сите недели)
//
//• Да се дополни main методот во кој ќе се иницијализира низа од работници и работни недели согласно влезните тест примери. Во првиот ред се чита број на работници, а во секој нареден ред се читаат името и работните недели за секој работник.
//
//• На стандарден излез да се испечати низата од работници со помош на методата void table(Rabotnik [] niza) која за низата од работници ќе отпечати приказ во следниот формат (за простор при печатење се користат три празни места):
//
//For example:
//
//Input	Result
//5
//Damijan
//35 33 42 42 40
//31 57 52 54 44
//56 32 51 52 35
//43 39 34 54 55
//Eva
//44 47 44 50 51
//30 46 59 36 60
//39 32 35 31 47
//44 38 41 46 43
//Goran
//51 37 39 55 54
//36 60 54 38 47
//41 57 41 39 41
//56 34 43 54 56
//Stefan
//59 55 57 37 32
//60 54 50 37 53
//54 38 34 36 55
//57 55 36 41 39
//Sara
//58 54 42 49 44
//30 45 47 44 39
//52 39 44 32 37
//40 50 44 56 52
//Rab   1   2   3   4   Vkupno
//Damijan   192   238   226   225   881
//Eva   236   231   184   212   863
//Goran   236   235   219   243   933
//Stefan   240   254   217   228   939
//Sara   247   205   204   242   898
//
//NAJVREDEN RABOTNIK: Stefan

import java.util.Scanner;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }
    @Override
    public String toString() {

    }

}

class Rabotnik{

    private String ime;
    private RabotnaNedela [] nedeli;

    public Rabotnik(String ime, RabotnaNedela[] nedeli) {
        super();
        this.ime = ime;
        this.nedeli = nedeli;
    }
    @Override
    public String toString() {

    }

}

public class Main {

    public static Rabotnik najvreden_rabotnik(Rabotnik [] niza)
    {

    }
    public static void table(Rabotnik [] niza)
    {

    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        Rabotnik [] niza = new Rabotnik[n];
        for(int i=0;i<n;i++)
        {
            //vasiot kod tuka
        }

        table(niza);
        System.out.println("NAJVREDEN RABOTNIK: " +najvreden_rabotnik(niza).getIme());

    }
}
