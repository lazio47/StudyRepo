.data
prompt1: .asciiz "Introduza um numero\n"
result1: .asciiz "O fatorial do número "
result2: .asciiz " é: "
.text
	
main:
	li $v0, 4
	la $a0, prompt1
	syscall
	
	li $v0, 5
	syscall
	move $s0, $v0
	
	move $a0, $s0
	jal factorial
	move $s1, $v0
	
	li $v0, 4
	la $a0, result1
	syscall
	
	li $v0, 1
	addi $a0, $s0, 0
	syscall
	
	li $v0, 4
	la $a0, result2
	syscall
	
	li $v0, 1
	addi $a0, $s1, 0
	syscall
	
	li $v0, 10
	syscall 

factorial:
	li, $t0, 1
	move $t1, $a0
for:
	beq, $t1, $zero, return
	mul $t0, $t0, $t1
	subi $t1, $t1, 1
	j for
return:
	move $v0, $t0
	jr $ra
	