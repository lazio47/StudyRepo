.data
	minus:	.asciiz		"texto em minusculas"
	maius:  .space 		20
.text
	li $s0, 0 #contador i = 0
	la $s1, minus #endere�o de texto minus
	la $s2, maius #endere�o de texto maius
	
	while:
		add $t0, $s1, $s0 #endere�o minus[i]
		lb $t1, 0($t0) #minus[i]
		beq $t1, '\0', print #parar se minus nao tiver nenhum valor 
		add $t2, $s2, $s0 #endere�o maius[i]
		lb $t3, 0($t2)#maius[i]
		li $a0, 'a'
		li $a1, 'z'
		sle $a2, $a0, $t1
		sle $a3, $t1, $a1
		and $t4, $a2, $a3
		beq $t4, $0, non_alfa
		addi $t3, $t1, 'A'
		sub $t3, $t3, $a0
		non_alfa:
		bne $t4, $0, add_index
		addi $t3, $t1, 0
		add_index:
		sb $t3, 0($t2)
		addi $s0, $s0, 1
		j while
			
	print:
		la $a0, maius
		li $v0, 4
		syscall
	
	exit: 
		li $v0, 10
		syscall
	
