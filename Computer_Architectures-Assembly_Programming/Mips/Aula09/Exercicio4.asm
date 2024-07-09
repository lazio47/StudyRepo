.data
prompt1: .asciiz "\nInsira a frase1: "
prompt2: .asciiz "\nInsira a frase2: "
resultnum: .asciiz "\n O numero de caracteres da frase1 é: "
resultconc: .asciiz "\nA frase concatenada é: "
frase1: .space 20
frase2: .space 20
frase3: .space 40

.text
main:
	li $v0, 4
	la $a0, prompt1
	syscall
	
	li $v0, 8
	la $a0, frase1
	li $a1, 20
	syscall
	
	li $v0, 4
	la $a0, prompt2
	syscall
	
	li $v0, 8
	la $a0, frase2
	li $a1, 20
	syscall
	
	li $v0, 4
	la $a0, resultnum
	syscall
	
	la $a0,  frase1
	
	jal strlen
	
	move $a0, $v0
	li $v0, 1
	syscall
	
	li $v0, 4
	la $a0, resultconc
	syscall
	
	la $a1, frase2
	la $a0, frase1
	jal strcat
	move $a0, $v0
	li $v0, 4
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

strcopy:
	move $t0, $a0 #i=0, endereco dst[-1]
	move $t2, $a1 #i=0, src
while1:
	lb $t1, 0($t2)
	beq $t1, $zero, return1
	sb $t1, 0($t0)
	addi $t0, $t0, 1
	addi $t2, $t2, 1
	j while1
return1:
	sb $zero, 0($t0)
	la $v0, frase3
	jr $ra
	
strcat:
	move $t0, $a0#dst
	move $t1, $a1#src
	#aux = dst
	sw $ra, 0($sp)
	sw $t0, 4($sp)
	sw $t1, 8($sp)
	move $a1, $t0
	la $a0, frase3
	jal strcopy
	lw $ra, 0($sp)
	lw $t0, 4($sp)
	lw $t1, 8($sp)
	
	la $t0, frase3
	
while2:
	lb $t2, 0($t0)
	beq $t2, $zero, return2
	addi $t0, $t0, 1
	j while2
return2:
	move $a0, $t0
	la $a1, frase2
	sw $ra, 0($sp)
	jal strcopy
	lw $ra, 0($sp)
	jr $ra
	