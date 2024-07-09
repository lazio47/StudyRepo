.data
	prompt1:  .asciiz  "Introduza um numero\n"
	strpar:  .asciiz  "O numero é par\n"
	strimp:  .asciiz  "O numero é impar\n"

.text

	li $v0, 4
	la $a0, prompt1
	Syscall
	
	li $v0, 5
	syscall
	
	move $t0, $v0
	li $t1, 1
	
	and $t2, $t0, $t1
	
	beq $t2, $0, par
	li $v0, 4
	la $a0, strimp
	syscall
	j end
	
	par:
	li $v0, 4
	la $a0, strpar
	Syscall
	
	end:
	li $v0, 10
	syscall