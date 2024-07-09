grammar PDrawGrammar;

// parser rules
program:
    stat* EOF;

stat:
    (outputExpr          
    | penDecl           
    | canvasDecl        
    | assignment        
    | instruc           
    | conditional       
    | loop              
    | pauseExpr         
    | execute           
    | declaration        
    | assignment        
    | selectCanvas      
    | setBackground
    | setProperty
    )';'
    //| functionDecl     #StatFunctionDecl
    //| functionCall  #StatFunctionCall
;                         

penDecl:
    'define' 'pen' ID '{' property+ '}'
;

expr returns[String var = null, int v=0,Type t = null]:
    point                                                       # ExprPoint
    | input                                                     # ExprInput
    //| functionCall                                              # ExprFunctionCall
    | '(' expr ')'                                              # ExprParentheses
    | dataType '(' expr ')'                                     # TypeConversion
    | ID op=('+'|'-') point                                     # ExprPointIncrement
    | op=('+' | '-') expr                                       # ExprUnary
    | e1=expr op=('*' | '/' | '//' | '\\\\') e2=expr              # ExprMultDivMod
    | e1=expr op=('+' | '-') e2=expr                            # ExprAddSub
    | e1=expr op=('==' | '!=' | '<' | '>' | '>=' |'<=') e2=expr # ExprBoolOperation
    | op='not' e1=expr                                          # ExprNot
    | e1=expr op=('and' | 'or') e2=expr                         # ExprAndOr
    | 'new' ID?                                                 # ExprNewPen
    | expr expr                                                 # ExprConcat
    | INT                                                       # ExprInt
    | REAL                                                      # ExprReal 
    | STRING                                                    # ExprString 
    | BOOLEAN                                                   # ExprBoolean                
    | COLOR                                                     # ExprColor
    | ID                                                        # ExprId
    | instruc                                                   # ExprInstrucToPen
    | PI                                                        # ExprPI
;

property:
    propertyName '=' expr ('º'|'°')?  ';' 
;

canvasDecl:
    'define' 'canvas' ID STRING '(' expr ',' expr ')' ('background' '=' expr)?  # CanvasDeclWithExpr
;
selectCanvas:
    'select' 'canvas' ID
;
setBackground:
    'background' '=' expr
;

declaration:
    dataType (ID|assignment) (',' (ID|assignment))*
;

assignment returns[String var = null]: 
    ID '=' expr              
; 

setProperty:
    expr '<-' propertyName expr  
;

propertyName:
    'color'
    | 'position'
    | 'orientation'
    | 'thickness'
    | 'pressure'
;

input:
    'stdin' STRING
;

outputExpr:
     expr '->' 'stdout'
;

instruc:
    ID (miniInstruc|pauseExpr|moves)    #InstructFirst
    |instruc (miniInstruc|pauseExpr)    #InstructRecusive
;

miniInstruc:
    Direction expr ('º'|'°')?
;

conditional:
    'if' '(' expr ')' statementBlock ('else' 'if' '(' expr ')' statementBlock)* ('else' statementBlock)?;
       
loop:
    'for' '(' (declaration | assignment) ';' expr ';' assignment ')' statementBlock  #LoopFor
    | 'while' '(' expr ')' statementBlock                                           #LoopWhile
    | 'until' '(' expr ')' statementBlock                                           #LoopUntil
;


pauseExpr:
    'pause' expr
;

execute:
    expr '<-' 'execute' STRING
;

point returns[String var = null]:
    '(' x=expr ',' y=expr ')'
;
statementBlock:
    '{' stat* /*returnStat?*/ '}'
;
/*functionDecl:
    dataType ID '(' params? ')' statementBlock
;
params:
    param (',' param)*
;
param:
    dataType ID
;
functionCall:
    ID '(' args? ')'
;
args:
    expr (',' expr)*
;
/*returnStat:
    'return' expr ';'
;*/

dataType:
    'string'
    | 'real'
    | 'int'
    | 'bool'
    | 'color'
    | 'pen'
    | 'position' 
    | 'void'
;

moves:
     'down' | 'up'
;

// Lexer rules for basic types
Direction: 'right' | 'left'| 'forward'| 'backward'
;
PI: 'PI'
;
COLOR: 'white' | 'black' | 'green' | 'red' | 'blue' | 'yellow' | '#' HEX HEX HEX HEX HEX HEX (HEX HEX)?
;
fragment HEX: [a-fA-F0-9] 
;
INT: [0-9]+
;
REAL: [0-9]+ '.' [0-9]* | [0-9]* '.' [0-9]+
;
BOOLEAN: 'true' | 'false'
;
ID: [a-zA-Z_0-9]+
;
STRING: '"' .*? '"'
;
COMMENT: '%' .*? '\n' -> skip
;
NEWLINE: '\r'? '\n' -> skip
;
WS: [ \t\r\n]+ -> skip
; 
ERROR: .;