// Од тастатура се внесува низа од целобројни елементи А со должина N ( N <= 100).
// Дополнително се внесува и број К. Да се трансформира низата така што на почеток 
// ќе се преместат сите елементи помали од K,
// а после нив ќе следуваат броевите поголеми или еднакви на K. Редоследот на елементите да не се менува.
// Испечатете ги елементите на трансформираната низа во еден ред на 
// стандарден излез разделени со празно место.

#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
    }
    int k;
    scanf("%d",&k);
    for(int i=0;i<n;i++){
        if(niza[i]<k){
            printf("%d ",niza[i]);
        }
    }
    for(int i=0;i<n;i++){
        if(niza[i]>=k){
            printf("%d ",niza[i]);
        }
    }

    return 0;
}