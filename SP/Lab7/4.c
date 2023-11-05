// Да се напише рекурзивна функција proizvod() 
// која за даден природен број ќе го пресметува и враќа производот на сите цифри 
// кои се наоѓаат на парни позиции (гледано од десно кон лево). 
// Се смета дека најмалку значајната цифра се наоѓа на 1-та позиција. 
// Потоа да се напише програма која за природен број n (што се внесува од тастатура) 
// ќе ја повика функцијата proizvod() за да го испечати производот на цифрите 
// кои што се наоѓаат на парни позиции во n.

#include <stdio.h>


int proizvod(int broj){
    if(!broj) return 1;
    return broj%10*proizvod(broj/100);
}

int main(){
    int n;
    scanf("%d",&n);
    printf("%d",proizvod(n/10));
    return 0;
}