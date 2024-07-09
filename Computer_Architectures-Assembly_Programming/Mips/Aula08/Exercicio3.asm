.data
	prompt1: .asciiz "Introduza uma string\n"
	result: .asciiz "O número de caracteres numéricos: "
	str: .space 40
.text
.globl main
	main:
	print_prompt:
	la $a0, prompt1
	li $v0, 4
	syscall
	read_string:
	li $v0, 8
	la $a0, str
	li $a1, 40
	syscall
	li $t0, 0#n = 0
	li $t1, 0#i = 0
	la $s1, str #endereço de str
	for:
	add $t5, $s1, $t1
	lb $s2, 0($t5) #valor str[i]
	beq $s2, '\0', print_result
	sge $t6, $s2, '0'
	sle $t7, $s2, '9'
	and $s3, $t6, $t7
	beq $s3, $0, add_index
	addi $t0, $t0, 1 #n++
	add_index:
	addi $t1, $t1, 1
	j for
	print_result:
	la $a0, result
	li $v0, 4
	syscall #print result
	move $a0, $t0
	li $v0, 1
	syscall #print n
	exit:
	li $v0, 10
	syscall
	
		