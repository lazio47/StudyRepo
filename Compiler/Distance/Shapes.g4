grammar Shapes;

distance: 'distance ' point point;

point: '(' x=NUM ',' y=NUM  ')';

NUM: [0-9]+;
WS: [ \t\r\n]+ -> skip;
