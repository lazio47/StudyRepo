.data
	prompt1: .asciiz  "Introduza um numero\n"
	char1: .asciiz  "-"
.text
	li $v0, 4
	la $a0, prompt1
	syscall
	
	li $v0, 5
	syscall
	
	move $t0, $v0
	
	li $t1, 0
	
	for:
	beq $t0, $t1, exit
	li $v0, 4
	la $a0, char1
	syscall
	addi $t1, $t1, 1
	j for
	
	exit:
	li $v0, 10
	syscall