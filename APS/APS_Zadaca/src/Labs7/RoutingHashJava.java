package Labs7;

/*

Потребно е да се симулира рутирање преку хеш табела. Секој рутер претставува една кофичка од хеш табелата и притоа пакетите на влез ги прима преку еден интерфејс. Бидејќи рутерот, рутирањето на даден пакет го врши користејќи ги статичките рути што тој ги знае, кога ќе добие пакет преку влезниот интерфејс, тој треба да даде одговор дали може да го рутира пакетот до дадениот уред во таа мрежа (postoi или nepostoi). Важно е тоа што сите адреси имаат мрежна маска /24, што значи дека последните 8 бита се наменети за адресирање. Претпоставуваме дека сите адреси се зафатени во таа мрежа, така што до било кој уред од таа мрежа, доколку ја има во рутирачката табела, може да се достави пакетот. Така што доколку во рутирачката табела има 10.10.10.0, тоа значи дека рутерот може да го проследи пакетот до  сите уреди во таа мрежа (10.10.10.1- 10.10.10.254).



На влез најпрвин се внесува бројот на рутери, потоа најизменично IP адресата на влезниот интерфејс, па во следниот ред IP адресите на мрежите до кој рутерот има статички рути. Потоа се внесува бројот на обиди за рутирање на пакети. Во следните редови најизменично се внесува влезен интерфејс и IP адреса на уред за која треба да се даде одговор дали тој рутер ја познава или не.

Име на класта :RoutingHashJava
*/

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RoutingHashJava {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int NumberOf = Integer.parseInt(bf.readLine());
        Map<String, Router> mapata = new HashMap<>();

        //CYCLE FOR ALL LIST
        for(int i=0;i<NumberOf;i++){
            String routerIp = bf.readLine();
            String [] accessRange = bf.readLine().split(",");
            ArrayList<String> Opseg = new ArrayList<>();
            Collections.addAll(Opseg, accessRange);
            //COLLECTION ADDED
            Router router = new Router(routerIp, Opseg);
            mapata.put(routerIp, router);
        }

        int M = Integer.parseInt(bf.readLine());
        for(int i=0;i<M;i++){
            String routerIp = bf.readLine();

            String [] parts = bf.readLine().split("\\.");
            //---
            String ip="";
            ip = parts[0]+parts[1]+parts[2];
            if(mapata.containsKey(routerIp)){
                Router router = mapata.get(routerIp);
                //if range exist
                if(router.isInRange(ip)){
                    System.out.println("postoi");
                }else{System.out.println("ne postoi");
                }}else{System.out.println("ne postoi");}
        }
    }
}

class Router{
    String IPADRESS;
    ArrayList<String> ACCESS;

    public Router(String routerIp, ArrayList<String> accessRange) {
        this.IPADRESS = routerIp;
        this.ACCESS = accessRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return Objects.equals(IPADRESS, router.IPADRESS) && Objects.equals(ACCESS, router.ACCESS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IPADRESS, ACCESS);
    }

    public boolean isInRange(String ip) {
        for (String access : ACCESS) {
            String[] parts = access.split("\\.");
            String ipAddress = "";
            ipAddress = parts[0] + parts[1] + parts[2];
            if (ipAddress.equals(ip)) {
                return true;
            }
        }
        return false;
    }
}