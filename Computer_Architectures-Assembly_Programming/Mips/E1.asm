	.data
oper1:	.word -217
oper2:	.byte 48
result: .space 4
	.text
	la $t1, oper2
	lb $t2, 0($t1)
	la $t0, oper1
	lw $t3, 0($t0)
	add $a0, $t2, $t3
	xori $a0, $a0, 0x8000
	la $s0, result
	sw $a0, 0($s0)
	
	li $v0, 4
	move $a0, $s0
	syscall