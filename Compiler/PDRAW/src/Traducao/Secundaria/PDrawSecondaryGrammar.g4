grammar PDrawSecondaryGrammar;

// parser rules
program: stat* EOF;

stat:
	(
		outputExpr
		| canvasDecl
		| assignment
		| instruc
		| conditional
		| loop
		| pauseExpr
		| declaration
		| assignment
		| selectCanvas
		| setBackground
	) ';';

expr:
    point                                                       # ExprPoint
    | input                                                     # ExprInput
    | '(' expr ')'                                              # ExprParentheses
    | dataType '(' expr ')'                                     # TypeConversion
    | e1=expr op=('*' | '/' | '//' | '\\\\') e2=expr              # ExprMultDivMod
    | e1=expr op=('+' | '-') e2=expr                            # ExprAddSub
    | op=('+' | '-') expr                                       # ExprUnary
    | e1=expr op=('==' | '!=' | '<' | '>' | '>=' |'<=') e2=expr # ExprBoolOperation
    | op='not' e1=expr                                          # ExprNot
    | e1=expr op=('and' | 'or') e2=expr                         # ExprAndOr
    | expr expr                                                 # ExprConcat
    | INT                                                       # ExprInt
    | REAL                                                      # ExprReal 
    | STRING                                                    # ExprString 
    | BOOLEAN                                                   # ExprBoolean                
    | COLOR                                                     # ExprColor
    | ID                                                           # ExprId
    | instruc                                                      # ExprId
    | PI                                                        # ExprPI
;
property:
     propertyName expr 'º'?  
;

canvasDecl:
	'define' 'canvas' ID STRING '(' expr ',' expr ')' # CanvasDeclWithExpr;

selectCanvas: 'select' 'canvas' ID;

setBackground: 'background' '=' expr;

declaration:
	dataType (ID | assignment) (',' (ID | assignment))*;

assignment: 
    ID '=' expr              
; 

setProperty:
    propertyName expr  
;

propertyName:
	'color'
	| 'position'
	| 'orientation'
	| 'thickness'
	| 'pressure';

input: 'stdin' STRING;

outputExpr: expr '->' 'stdout';

instruc:
	(miniInstruc | pauseExpr | moves | positionExpr)	# InstructFirst
	| instruc (miniInstruc | pauseExpr)					# InstructRecusive;

positionExpr:
	'position' '(' expr ',' expr ')' # PositionInstruction;

miniInstruc: Direction expr ('º')?;

conditional:
	'if' '(' expr ')' statementBlock (
		'else' 'if' '(' expr ')' statementBlock
	)* ('else' statementBlock)?;

loop:
	'for' '(' (declaration | assignment) ';' expr ';' assignment ')' statementBlock	# LoopFor
	| 'while' '(' expr ')' statementBlock											# LoopWhile
	| 'until' '(' expr ')' statementBlock											# LoopUntil;

pauseExpr: 'pause' expr;

execute: expr '<-' 'execute' STRING;

point: '(' x = expr ',' y = expr ')';

statementBlock: '{' stat* '}';

dataType:
	'string'
	| 'real'
	| 'int'
	| 'bool'
	| 'color'
	| 'position'
	| 'void';

Direction: 'right' | 'left' | 'forward' | 'backward';

moves: 'down' | 'up';

// Lexer rules for basic types
PI: 'PI';

COLOR:
	'white'
	| 'black'
	| 'green'
	| 'red'
	| 'blue'
	| 'yellow'
	| '#' HEX HEX HEX HEX HEX HEX;

fragment HEX: [a-fA-F0-9];

INT: [0-9]+ | '-' [0-9]+;

REAL: [0-9]+ '.' [0-9]* | [0-9]* '.' [0-9]+;

BOOLEAN: 'true' | 'false';

ID: [a-zA-Z_0-9]+;

STRING: '"' .*? '"';

COMMENT: '%' .*? '\n' -> skip;

NEWLINE: '\r'? '\n' -> skip;

WS: [ \t\r\n]+ -> skip;

ERROR: .;