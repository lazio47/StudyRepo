.data
src: .asciiz "Hello, world"
dst: .asciiz "Ola mundo, "
aux: .space 60

.text

main:
	la $a1, src
	la $a0, dst
	jal strcat
	move $a0, $v0
	li $v0, 4
	syscall
	
	li $v0, 10
	syscall

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
	la $v0, aux
	jr $ra
	
strcat:
	move $t0, $a0#dst
	move $t1, $a1#src
	#aux = dst
	sw $ra, 0($sp)
	sw $t0, 4($sp)
	sw $t1, 8($sp)
	move $a1, $t0
	la $a0, aux
	jal strcopy
	lw $ra, 0($sp)
	lw $t0, 4($sp)
	lw $t1, 8($sp)
	
	la $t0, aux
	
while2:
	lb $t2, 0($t0)
	beq $t2, $zero, return2
	addi $t0, $t0, 1
	j while2
return2:
	move $a0, $t0
	la $a1, src
	sw $ra, 0($sp)
	jal strcopy
	lw $ra, 0($sp)
	jr $ra
	