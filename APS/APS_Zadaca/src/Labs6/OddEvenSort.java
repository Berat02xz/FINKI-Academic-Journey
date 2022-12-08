package Labs6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Collection Arrays Add
import java.util.Arrays;

public class OddEvenSort {

    static void oddEvenSort(int a[], int length)
    {
        int [] newArray = new int[length];
        Arrays.sort(a);

        int k=0;
        for(int i=0;i<length;i++){
            if(a[i]%2==1){
                newArray[k] = a[i];
                k++;

            }
        }
        for(int i=length-1;i>=0;i--){
            if(a[i]%2==0){
                newArray[k]=a[i];
                k++;
            }

        }

        for(int i=0;i<newArray.length;i++){
            a[i] = newArray[i];
            //END
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}
