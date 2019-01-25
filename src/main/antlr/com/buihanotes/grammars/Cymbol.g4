grammar Cymbol;

@header {
package com.buihanotes.grammars;
}

compilationUnit: declaration+;

declaration
    :   typeSpecifier IDENTIFIER initializer? ';'                           #variableDeclaration
    |   typeSpecifier IDENTIFIER '(' formalParameters? ')' blockStatement   #functionDeclaration
    ;

typeSpecifier
    :   VOID
    |   INT
    |   FLOAT
    |   BOOLEAN
    ;

initializer
    : '=' expression
    ;

statement
    :   blockStatement
    |   selectionStatement
    |   jumpStatement
    |   expressionStatement
    ;

blockStatement
    :   '{' (statement | declaration)*  '}'
    ;

jumpStatement
    :   'return' expression ';'
    |   'continue' ';'
    |   'break' ';'
    ;

expressionStatement
    :   expression ';'
    |   expression (',' expression)* ';'
    ;

selectionStatement
	:   'if' '(' conditionalExpression ')' statement
	|   'if' '(' conditionalExpression ')' statement 'else' statement
	|   'switch' '(' conditionalExpression ')' statement
	;

expression
    :   primaryExpression                                      #primaryExp
    |   IDENTIFIER '(' (expression (',' expression)*)* ')'     #functionCall
    |   expression ('*' | '/' | '%') expression                #multiplicativeExpression
    |   expression ('+' | '-') expression                      #additiveExpression
    |   IDENTIFIER '=' expression ';'                          #assignmentExpression
    |   conditionalExpression                                  #condition
    ;

// Start: Logic expression
conditionalExpression
    :   primaryExpression
    |   NOT conditionalExpression
    |   conditionalExpression comparator conditionalExpression
    |   conditionalExpression binary conditionalExpression
    ;
// End: Logic expression

formalParameters
    :   formalParameter (',' formalParameter)*
    ;

formalParameter
    :   typeSpecifier IDENTIFIER
    ;

primaryExpression
    :   IDENTIFIER            #primaryIdentifier
    |   DECIMAL               #primaryDecimal
    |   BOOL                  #primaryBool
    ;

comparator
    : GT | GE | LT | LE | EQ
    ;

binary
    : AND | OR | XOR
    ;

AND: '&&';
OR: '||';
XOR: '^';
NOT: '~';
GT: '>';
GE: '>=';
LT: '<';
LE: '<=';
EQ: '==';
INT: 'int';
VOID: 'void';
FLOAT: 'float';
BOOLEAN: 'bool';
DECIMAL: INTEGER | DOUBLE;
BOOL: TRUE | FALSE;

IDENTIFIER: LETTER (LETTER | '0'..'9')* ;

// Skip whitespace
WS:   (' '|'\r'|'\t'|'\n') -> skip;
SL_COMMENT: '//' ~('\r'|'\n')* '\r'? '\n' -> skip;

// LEXER RULES
fragment
LETTER: ('a'..'z' | 'A'..'Z');
INTEGER: ('0'..'9')+;
DOUBLE: ('0'..'9')* '.' ('0'..'9')*;
TRUE: 'true';
FALSE: 'false';