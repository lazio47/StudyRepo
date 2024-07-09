.data
	pergunta:	.asciiz		"Qual é seu nome? "
	resp: 		.asciiz		"Olá, "
	nome:		.space		25
	exclama:	.asciiz		"!"
.text

	li $v0, 4
	la $a0, pergunta
	syscall
	
	
 	la $a0, nome
        la $a1, 25
        li $v0, 8
        syscall
        
        li $v0, 4
        la $a0, resp
        syscall
        
        li $v0, 4
        la $a0, nome
        syscall
        
        li $v0, 4
        la $a0, exclama
        syscall
