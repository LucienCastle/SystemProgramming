%{
	#include<stdio.h>
	#include "y.tab.h"
%}

DIGIT [0-9]
REAL {DIGIT}+[.]{DIGIT}*
LETTER [A-Za-z]
ASSIGN =

%%
[\t ] ;
int {printf("%s\t| Data Type\n",yytext);return (INT);}
float {printf("%s\t| Data Type\n",yytext);return (FLOAT);}
char {printf("%s\t| Data Type\n",yytext);return (CHAR);}
boolean {printf("%s\t| Data Type\n",yytext);return (BOOL);}
true|false {printf("%s\t| Boolean Value\n",yytext);return (BLVAL);}
['][^\t\n]['] {printf("%s\t| Character Value\n",yytext);return (CHVAL);}
{LETTER}+({LETTER}|{DIGIT})* {printf("%s\t| Identifier\n",yytext);return (ID);}
{REAL} {printf("%s\t| Real Number\n",yytext);return (REAL);}
{DIGIT}+ {printf("%s\t| Integer\n",yytext);return (INTEGER);}
"," {printf("%s\t| Comma\n",yytext);return (COMMA);}
";" {printf("%s\t| Delimiter\n",yytext);return (DELIM);}
{ASSIGN} {printf("%s\t| Assignment Operator\n",yytext);return (AS);}
\n return (NL);
. ;
%%

int yywrap(){
	return 1;
}
