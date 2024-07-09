grammar CSV;

file: header row+ EOF;

header: row;

row: field (',' field)* '\r'? '\n';

field: TEXT;

TEXT: ~[,\n\r"]+;

