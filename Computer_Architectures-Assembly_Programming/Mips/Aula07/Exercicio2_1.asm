.data
	prompt1: .asciiz "Introduza um numero\n"
	result: .asciiz  "A soma dos numeros positivos inseridos é: "
.text
	li $t0, 1
	li $t1, 6
	li $s0, 0
	for:
	beq $t0, $t1, end
		addi $t0, $t0, 1
		la $a0, prompt1
		li $v0, 4
		syscall
		li $v0, 5
		syscall
		blt $v0, $0, for
		add $s0, $s0, $v0
		j for
	
	end:
	la $a0, result
	li $v0, 4
	syscall
	move $a0, $s0
	li $v0, 1
	syscall