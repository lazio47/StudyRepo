.data
	prompt1: .asciiz "Introduza um numero\n"
	result: .asciiz "O fatorial do numero inserido é: "
.text
	li $v0, 4
	la $a0, prompt1
	syscall
	
	li $v0, 5
	syscall
	
	move $t0, $v0
	li $t1, 1
	
	for:
	beq $t0, $0, print
	mul $t1, $t1, $t0
	subi $t0, $t0, 1
	j for
	
	print:
	li $v0, 4
	la, $a0, result
	syscall
	li $v0, 1
	move $a0, $t1
	syscall
	
	exit:
	li $v0, 10
	syscall