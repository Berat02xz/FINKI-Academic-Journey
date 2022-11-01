package Labs2;
//
//Во една игра со карти се користат специјални карти, т.ш. за секоја карта се важни податоците за: id на херојот на картата (int id), моќта на херојот (int power), како и бројот на напади кои тој може да ги направи (int numAttacks).
//
//        Така, за секоја една карта може да се пресмета колку таа карта е значајна, според тоа колку штета може да нанесе херојот на картата, односно како производ на моќта на херојот и бројот на напади кои може да ги направи.
//
//        Двајца пријатели решаваат да ја играат оваа игра, т.ш. во две еднострано поврзани листи се чуваат податоците за картите кои им се доделени. Во првата листа се чуваат картите доделени на првиот пријател, а додека пак во втората се чуваат податоците за картите доделени на вториот пријател. И двајцата играчи на почеток имаат точно 6 карти.
//
//        На самиот почетокот на играта, правилата налагаат првиот играч (првиот пријател) да ја предаде својата најдобра карта на другиот играч, т.ш. кога вториот играч ќе ја земе картата истата треба да ја постави во средина на своите карти. Тоа значи дека потребно е од листата која ги чува картите на првиот пријател да се отстрани (избрише) најдобрата карта и таа карта да се додаде на средина на листата каде што се чуваат картите на вториот пријател.
//
//        Влез: Во секој еден ред се дадени податоци за една карта, одделени со празно место, во формат id power numAttacks. Притоа, прво се дадени картите на првиот пријател, по што следуваат податоците за картите на вториот пријател.
//
//        Излез: Во првиот ред id на сите карти на првиот пријател. Во вториот ред id на сите карти на вториот пријател.
//
//        Внимавајте:
//
//        1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата Heroes.java, со целосно имплементиран input и output. Потребно е да се менува само во рамки на void startHeroesGame(SLL<Card> firstFriendCards, SLL<Card> secondFriendCards) функцијата.
//
//        2. Притоа, бришењето треба да биде имплементирано како бришење на цел јазол, а додавањето како додавање на цел јазол. Промените (бришење/додавање елемент) не треба да се однесуваат на информациите во самите јазли туку во промени на врските помеѓу јазлите.
//
//        3. Не смее да менувате во main функцијата !
//        For example:
//
//        Input
//        84 44 87
//        79 9 67
//        26 2 81
//        57 91 59
//        45 92 8
//        55 97 80
//        10 99 4
//        13 84 28
//        64 6 90
//        93 57 23
//        14 49 36
//        50 65 12
//
//        Result
//        84 79 26 57 45
//        10 13 64 55 93 14 50
//


import java.util.Scanner;

class Card {
    private int id;
    private int power;
    private int numAttacks;

    public Card(int id, int power, int numAttacks) {
        this.id = id;
        this.power = power;
        this.numAttacks = numAttacks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNumAttacks() {
        return numAttacks;
    }

    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

    public int damage() {
        return power * numAttacks;
    }


    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

public class Heroes {

    //todo: implement function
    public static void startHeroesGame(SLL<Card> firstFriendCards, SLL<Card> secondFriendCards) {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstFriendCards = new SLL<Card>();
        SLL<Card> secondFriendCards = new SLL<Card>();

        for (int i = 0; i < 6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            firstFriendCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < 6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            secondFriendCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        startHeroesGame(firstFriendCards, secondFriendCards);
        System.out.println(firstFriendCards.toString());
        System.out.println(secondFriendCards.toString());
    }
}
