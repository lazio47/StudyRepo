.data
prompt1: .asciiz "Introduza uma string:\n"
result: .asciiz "Número de caracteres: "
str: .space 40
.text

main:
	li $v0,4
	la $a0, prompt1
	syscall
	
	li $v0, 8
	la $a0, str
	li $a1, 40
	syscall
	la $a0, str 
	
	jal strlen
	
	move $s0, $v0
	
	li $v0, 4
	la $a0, result
	syscall
	
	move $a0, $s0
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall


strlen:
	li $t0, 0 #n=0
	move $t1, $a0#endereço da string, i=0
	
while:
	lb $t3, 0($t1)
	beq $t3, $zero, return
	addi $t0, $t0, 1
	addi $t1, $t1, 1
	j while
return:
	subi $t0, $t0, 1
	move $v0, $t0
	jr $ra
	
	
