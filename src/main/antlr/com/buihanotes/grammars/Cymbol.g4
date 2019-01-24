grammar Cymbol;

@header {
package com.buihanotes.grammars;
}

compilationUnit: declaration+;

declaration
    : typeSpecifier IDENTIFIER ('=' expression)? ';'   #variableDeclaration
    | typeSpecifier IDENTIFIER '(' formalParameters? ')' blockStatement #functionDeclaration
    ;

formalParameters
    :   formalParameter (',' formalParameter)*
    ;

formalParameter
    : typeSpecifier IDENTIFIER    #functionDeclarationParameter
    | IDENTIFIER                  #functionCallParameter
    ;

typeSpecifier
    :   'int'
    |   'float'
    |	'void'
    ;

statement
    :   blockStatement
    |   jumpStatement
    |   expressionStatement
    ;

blockStatement
    :   '{' (statement | declaration)*  '}'
    ;

jumpStatement
    : 'return' expression ';'
    | 'continue' ';'
    | 'break' ';'
    ;

expressionStatement
    :   expression (',' expression)*
    ;

selectionStatement
	: IF '(' expression ')' statement ELSE statement
	| IF '(' expression ')' statement
	| SWITCH '(' expression ')' statement
	;

expression
    :   additiveExpression
    |   assignmentExpression
    ;

assignmentExpression
    : IDENTIFIER '=' expression ';'
    | conditionalExpression
    ;

conditionalExpression
    : logical_or_expression
    ;

logical_or_expression
    : logical_and_expression
    ;

logical_and_expression
    : inclusive_or_expression
    ;

inclusive_or_expression
    : exclusive_or_expression
    ;

exclusive_or_expression
    :
    ;

additiveExpression
	:	multiplicativeExpression
	|   postfixExpression ('+' postfixExpression)*
	|	postfixExpression ('-' postfixExpression)*
	;

multiplicativeExpression
    :   primaryExpression
    |   postfixExpression ('*' postfixExpression)*
    |   postfixExpression ('/' postfixExpression)*
    |   postfixExpression ('%' postfixExpression)*
    ;

postfixExpression
    :   primaryExpression                                   #postfixExpressionPrimary
    |   primaryExpression '(' expressionStatement* ')' ';'  #postfixExpressionCall
    ;

primaryExpression
    :   IDENTIFIER            #primaryIdentifier
    |   CONSTANT              #primaryConstant
    ;

// LEXER RULES
fragment
LETTER: ('a'..'z' | 'A'..'Z');
CONSTANT: INTEGER | DOUBLE;
INTEGER: ('1'..'9')('0'..'9')*;
DOUBLE: ('1'..'9')('0'..'9')* '.' ('1'..'9')('0'..'9')*;
IDENTIFIER
    :   LETTER (LETTER | '0'..'9')* ;
WS:   (' '|'\r'|'\t'|'\n') -> skip;
SL_COMMENT: '//' ~('\r'|'\n')* '\r'? '\n' -> skip;
IF: 'if';
ELSE: 'else';
SWITCH: 'switch';