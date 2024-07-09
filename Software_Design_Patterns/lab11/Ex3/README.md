# Ex3 Guiao 11

Problema:
	Sistema de controle de tráfego aéreo, onde há várias aeronaves (aviões, helicópteros, etc.) que precisam se comunicar entre si e 
	com as torres de controle para coordenar decolagens, aterrissagens, etc.
	
Solucao:
	A solução utiliza o padrão Mediator para resolver o problema do acoplamento excessivo entre as aeronaves e as torres de controle. 
	TorreDeControle atua como o mediador central que coordena as comunicações entre as diferentes aeronaves.
	Cada aeronave não precisa saber sobre as outras aeronaves, elas apenas se comunicam com o mediador quando precisam enviar uma mensagem.
	
Referências para recursos/fontes utilizados:
	https://refactoring.guru/pt-br/design-patterns/mediator;
	E o exemplo feito na aula teorica.
