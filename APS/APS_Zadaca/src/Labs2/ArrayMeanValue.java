package Labs2;

//За дадена низа од N (1≤N≤50) природни броеви, да се најде бројот кој е најблиску до нивниот просек. Ако постојат два броја со исто растојание до просекот, да се врати помалиот од нив. На пример за низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, што значи дека бројот кој треба да се врати и е најблиску до просекот е 3.
//
//        За низата 1, 2, 3, 4, 5, 6 просекот е 3.5 и двата броја 3 и 4 се на исто растојание од просекот. Точната вредност која треба да се врати е помалиот од нив, а тоа е 3.
//
//        Во низата може да има дупликати.
//
//        Влез: Првиот број од влезот е бројот на елементи во низата N, а потоа во секој ред се дадени броевите.
//
//        Излез: Најдениот број кој што е најблиску до просекот.
//
//        Внимавајте:
//
//        1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата ArrayMeanValue.java, со целосно имплементиран input и output. Потребно е да се менува само во рамки на int brojDoProsek(Array<Integer arr) функцијата.
//
//        2. Не смее да менувате во main функцијата !
//
//        For example:
//
//        Input
//        5
//        1
//        2
//        3
//        4
//        5
//        Result
//        3
//

import java.util.Scanner;

@SuppressWarnings("unchecked")
class Array<E> {
    private E data[]; // declared to be an Object since it would be too
    // complicated with generics
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void insertLast(E o) {
        //check if there is enough capacity, and if not - resize
        if(size + 1 > data.length)
            this.resize();
        data[size++] = o;
    }

    public void insert(int position, E o) {
        // before all we check if position is within range
        if (position >= 0 && position <= size) {
            //check if there is enough capacity, and if not - resize
            if(size + 1 > data.length)
                this.resize();
            //copy the data, before doing the insertion
            for(int i=size;i>position;i--) {
                data[i] = data[i-1];
            }
            data[position] = o;
            size++;
        } else {
            System.out.println("Ne mozhe da se vmetne element na taa pozicija");
        }
    }

    public void set(int position, E o) {
        if (position >= 0 && position < size)
            data[position] = o;
        else
            System.out.println("Ne moze da se vmetne element na dadenata pozicija");
    }

    public E get(int position) {
        if (position >= 0 && position < size)
            return data[position];
        else
            System.out.println("Ne e validna dadenata pozicija");
        return null;
    }

    public int find(E o) {
        for (int i = 0; i < size; i++) {
            if(o.equals(data[i]))
                return i;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public void delete(int position) {
        // before all we check if position is within range
        if (position >= 0 && position < size) {
            // first resize the storage array
            E[] newData = (E[]) new Object[size - 1];
            // copy the data prior to the delition
            for (int i = 0; i < position; i++)
                newData[i] = data[i];
            // move the data after the deletion
            for (int i = position + 1; i < size; i++)
                newData[i - 1] = data[i];
            // replace the storage with the new array
            data = newData;
            size--;
        }
    }

    public void resize() {
        // first resize the storage array
        E[] newData = (E[]) new Object[size*2];
        // copy the data
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        // replace the storage with the new array
        this.data = newData;
    }

    @Override
    public String toString() {
        String ret = new String();
        if(size>0) {
            ret = "{";
            ret += data[0];
            for(int i=1;i<size;i++) {
                ret += "," + data[i];
            }
            ret+="}";
            return ret;
        }
        else {
            ret = "Prazna niza!";
        }
        return ret;
    }

}

public class ArrayMeanValue {

    //todo: implement function
    public static int brojDoProsek(Array<Integer> arr) {
        //Fins sum of all elements in arr
        int sumOfElements = 0;
        for(int i=0; i<arr.getSize(); i++){
            sumOfElements+=arr.get(i);
        }

        //Average Sum of elements
        double averageSumOfElements = (double)sumOfElements/(double)arr.getSize();

        //Calculates The Closes Element that has same value as averageSumOfElements
        int indexOfClosest=0;
        double calculationOfClosest = Math.abs(averageSumOfElements-(double)(Integer)arr.get(0));
        double calculation= 0.0;
        for(int i=0;i<arr.getSize();i++){
            calculation = Math.abs(averageSumOfElements- (double)(Integer)arr.get(i));

            if(calculation < calculationOfClosest){
                calculationOfClosest=calculation;
                indexOfClosest=i;
            } else if(calculation == calculationOfClosest){
                if(arr.get(i) < arr.get(indexOfClosest)){
                calculationOfClosest=calculation;
                indexOfClosest=i;}
            }

        }

        return arr.get(indexOfClosest);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        Array<Integer> arr = new Array<>(N);

        for(int i=0;i<N;i++) {
            arr.insertLast(input.nextInt());
        }

        System.out.println(brojDoProsek(arr));
    }
}