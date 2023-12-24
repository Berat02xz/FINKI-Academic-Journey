.data
Array: .space 80        # 80 = 4bytes * 20 Max Elements

.text
addi $t1,$zero,0
addi $t2,$zero,0

li $v0,5
syscall
move $t1,$v0 # t1 = Adress Number

li $v0,5
syscall
move $t2,$v0 # t2 = Number Of Elements

addi $t3,$zero,0 # t3 = Counter Beggining at 0
addi $t5,$zero,0 # t5 = Index
addi $t6,$zero,5 # t6 = Number 5 (to be divided with)
addi $t8,$zero,0 # Result Number is gonna be stored here
#------------------------------------------------
# For Cycle to read elements and put them in Array 
Cycle:
li $v0,5
syscall
move $t4,$v0 # t4 = The Element That Has Been Read

sw $t4, Array($t5) # Element Stored in Array[index]

addi $t3, $t3, 1 # Counter Increment
addi $t5,$t5,4  #Index increment by 4 bytes (next word)
blt $t3,$t2, Cycle
#-------------------------------------------------


#Reseting Counters:
addi $t3, $zero, 0 # Counter Reset
addi $t5,$zero,0  #Index Reset


#------------------------------------------------
# For Cycle to Get Elements and Divide Them
Cycle2:
lw $t4, Array($t5) # Element Selected from Array[Index]

div $t4,$t6
mfhi $t7  # Remainder From Element/5
bnez $t7,Skip
add $t8,$t8,$t4
Skip:

addi $t3, $t3, 1 # Counter Increment
addi $t5,$t5,4  #Index increment by 4 bytes (next word)
ble $t3,$t2, Cycle2
#-------------------------------------------------

li $v0,1
move $a0,$t8
syscall

#dopolnitelno baranje:
sw $t8, ($t1)
move $s1,$t8

#
#Posle ova sakam da umram, vi blagodaram za vnimanieto
#
