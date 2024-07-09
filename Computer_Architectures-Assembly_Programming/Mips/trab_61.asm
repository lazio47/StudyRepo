#$t0 = 0x12345678 , $t1 = 0x0000000F



and $t2, $t0, $t1
or $t3, $t0, $t1
nor $t4, $t0, $t1
xor $t5, $t0, $t1

move $a0, $0x12345678
li $v0, 34 # $v0 = 1 (syscall "print_int")
syscall