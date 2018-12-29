grammar Cymbol;

@header {
package com.buihanotes.grammars;
}

file: (functionDecl | varDecl)+;

varDecl: type ID ('=' expr)? ';' ;

type: 'int'
    | 'float'
    | 'void'
    ;

functionDecl: type ID '(' formalParameters? ')' block
            ;

formalParameters: formalParameter (',' formalParameter)* ;

formalParameter: type ID;

block: '{' stat* '}';

stat: block
    | varDecl
    | 'if' expr 'then' stat ('else' stat)?
    | 'return' expr ';'
    | expr '=' expr ';'
    | expr ';'
    ;

expr: ID '(' exprList? ')'     # Call
    | expr '[' expr ']'        # Index
    | '-' expr                 # Negate
    | '!' expr                 # Not
    | expr ('*'|'/') expr      # MultDiv
    | expr ('+'|'-') expr      # AddSub
    | expr '==' expr           # Equal
    | ID                       # Var
    | NUMBER                   # Number
    | '(' expr ')'             # Parens
    ;

exprList: expr (',' expr)*;

ID: LETTER (LETTER|DIGIT)*;
NUMBER: DIGIT+
    | DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;

fragment LETTER: [a-zA-Z_];
fragment DIGIT: [0-9];

WS: [ \t\r\n]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' .*? '\n' -> skip;


