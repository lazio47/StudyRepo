grammar FracLang;

program: stat* EOF;

stat: statement ';';

statement: display | assign;

expr: 	
	  op=('-'|'+') expr             #UnarioExpr
	| expr op=('*'|':') expr		#MulDivExpr
	| expr op=('+'|'-') expr		#PlusMinusExpr
	| '(' expr ')'					#PrentesesExpr
	| reduce						#ReduceExpr
	| read							#ReadExpr
	| frac							#FracExpr
    | INT							#IntExpr
	| ID							#IdExpr
    ;

assign: ID '<=' expr;

display: 'display' expr; 

reduce:  'reduce' (expr | read);

read: 'read' STRING;

frac: INT '/' INT;

ID: [a-z]+;

STRING: '"' .*? '"';

INT: [0-9]+;

COMMENT: '--' .*? '\n' -> skip;

WS: [ \t\n\r]+ -> skip;
