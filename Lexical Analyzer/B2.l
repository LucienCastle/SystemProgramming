%{
	#include<stdio.h>
	FILE* yyin;
%}

DIGIT [0-9]
NUMBER {DIGIT}+
REAL {DIGIT}*[.]{NUMBER}
TEXT [A-Za-z]
PREPROCESSORS "import "{TEXT}+(([.]{TEXT}*)*("*")?)?{DELIM}
KEYWORDS "class"|"static"|"main"|"return"
DATATYPE "int"|"float"|"String"|"void"|"long"
IDENTIFIER {TEXT}({DIGIT}|{TEXT}|"_")*
DELIM ";"
ACCESS "public"|"private"|"protected"
ARITH "+"|"-"|"*"|"/"|"%"
RELATIONAL ">"|"<"|"<="|">="|"=="
LOGICAL "&&"|"||"|"!"|"^"
UNARY "++"|"--"
COND_KEY "if"|"else"|"else if"|"switch"|"case"
ITER_KEY "for"|"while"|"do"

%%
[ \n\t]+ ;
{PREPROCESSORS} {printf("%s\t| Prepocessor\n",yytext);}
{ACCESS} {printf("%s\t| Access Specifier\n",yytext);}
{KEYWORDS} {printf("%s\t| Keyword\n",yytext);}
{DATATYPE} {printf("%s\t| Data type\n",yytext);}
{COND_KEY} {printf("%s\t| Conditional Statement\n",yytext);}
{ITER_KEY} {printf("%s\t| Iterative Statement\n",yytext);}
{IDENTIFIER} {printf("%s\t| Identifier\n",yytext);}
{REAL} {printf("%s\t| Real Constant\n",yytext);}
{NUMBER} {printf("%s\t| Constant Integer\n",yytext);}
{DELIM} {printf("%s\t| Delimiter\n",yytext);}
{UNARY} {printf("%s\t| Unary Operator\n",yytext);}
{ARITH} {printf("%s\t| Arithmetic Operator\n",yytext);}
{LOGICAL} {printf("%s\t| Logical Operator\n",yytext);}
{RELATIONAL} {printf("%s\t| Relational Operator\n",yytext);}
"=" {printf("%s\t| Assignment Operator\n",yytext);}
"{" {printf("%s\t| Start of block\n",yytext);}
"}" {printf("%s\t| End of block\n",yytext);}
"(" {printf("%s\t| Start paranthesis\n",yytext);}
")" {printf("%s\t| End paranthesis\n",yytext);}
. ;
%%

int yywrap(){
	return 1;
}

int main(int argc, char *argv[]){
	yyin=fopen(argv[1],"r");
	yylex();
	fclose(yyin);
}
