%{
	#include<stdio.h>
	void yyerror(char*);
	int yylex();
	FILE* yyin;
%}

%token ID DELIM INT CHAR FLOAT BOOL BLVAL CHVAL REAL AS INTEGER COMMA NL

%%
s:	type1|type2|type3|type4;
type1:	INT varlist DELIM NL {printf("Valid integer variable declaration.");return 0;};
type2:	FLOAT varlist2 DELIM NL {printf("Valid float variable declaration.");return 0;};
type3:	CHAR varlist3 DELIM NL {printf("Valid character variable declaration.");return 0;};
type4:	BOOL varlist4 DELIM NL {printf("Valid boolean variable declaration.");return 0;};

varlist:ID|ID COMMA varlist|ID AS INTEGER|ID AS INTEGER COMMA varlist|;
varlist2:ID|ID COMMA varlist2|ID AS REAL|ID AS REAL COMMA varlist2|;
varlist3:ID|ID COMMA varlist3|ID AS CHVAL|ID AS CHVAL COMMA varlist3|;
varlist4:ID|ID COMMA varlist4|ID AS BLVAL|ID AS BLVAL COMMA varlist|;
%%

void yyerror(char* s){
	printf("ERROR:%s",s);
}

int main(int argc,char* argv[]){
	yyin=fopen(argv[1],"r");
	yyparse();
	fclose(yyin);
	return 0;
}
