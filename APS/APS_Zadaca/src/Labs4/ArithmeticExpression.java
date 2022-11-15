package Labs4;

/*
Даден е некој аритметички израз. Аритметичкиот израз е во облик (A+B) или (A-B) каде што А и B истовремено се други аритметички изрази или цифри од 0-9. Потребно е да го евалуирате дадениот израз.

Име на класата (Java): ArithmeticExpression


For example:

Input
(1+2)

Result
3

 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r

        public static char[] toPostFix(char[] c){
            Stack<Character> ops = new Stack<>();
            StringBuilder nova = new StringBuilder(new String());

            for(int i=0;i<c.length;i++){
                if(c[i]=='('){
                    ops.push(c[i]);
                }
                if(Character.isDigit(c[i])){
                    nova.append(c[i]);
                }
                if(c[i]==')'){
                    nova.append(ops.pop());
                    ops.pop();
                }
                if(c[i]=='+' || c[i]=='-'){
                    ops.push(c[i]);
                }
            }
            return nova.toString().toCharArray();
        }

        static int presmetaj(char k[], int l, int r) {

            Stack<Integer> rez = new Stack<>();
            char [] c = new char[k.length];
            c=toPostFix(k);
            System.out.printf(String.valueOf(c));
            System.out.printf("\n");

            for(int i=0; i<c.length;i++){
            int a=0;
            int b=0;
                if(Character.isDigit(c[i])){
                    rez.push(Integer.parseInt(String.valueOf(c[i])));
                }
                if(c[i]=='+'){
                  b= rez.pop();
                  a= rez.pop();
                  rez.push(a+b);
                }

                if(c[i]=='-'){
                    b= rez.pop();
                    a= rez.pop();
                    rez.push(a-b);
                }
            }
            return Math.abs(rez.pop());
        }





    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
