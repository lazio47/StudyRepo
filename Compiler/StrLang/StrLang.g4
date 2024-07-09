grammar StrLang;

program: stat* EOF;

stat: statement;

statement: print | assign;

expr: 
		  input										#InputExpr
		| expr '/' expr '/' expr					#SubstituitionExpr
		| '(' expr ')'								#ParenthesisExpr
		| expr op=('+'|'-') expr					#AddSubExpr 
		| STRING									#StringExpr
		| ID										#IdExpr
		| 'trim' expr								#TrimExpr
		;
		

input: 'input' '(' STRING ')';

assign: ID ':' expr;

print: 'print' expr;

ID: [a-z][a-z0-9]*;

STRING: '"' .*? '"';

COMMENT: '//' .*? '\n' -> skip;

WS: [ \t\r\n] -> skip;
