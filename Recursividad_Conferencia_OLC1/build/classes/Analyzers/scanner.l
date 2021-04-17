package Analyzers;

import java_cup.runtime.Symbol;

%%

%class scanner
%public
%line
%char
%cup
%unicode
%ignorecase 
%8bit
%full


%init{ 
    yyline = 0; 
    yychar = 1; 
%init}

entero = [0-9]+
letra = [a-zA-ZÑñ]
id = {letra}({letra}|[0-9]|"_")*
ComentarioMulti = ("/*"[^/]~"*/")|("/*"[^*/]*"*/")
espacio = (\t|\f|" "|\r)

%%

"int" {return new Symbol(sym.int_, yychar, yyline + 1, yytext());}
"if" {return new Symbol(sym.if_, yychar, yyline + 1, yytext());}
"return" {return new Symbol(sym.return_, yychar, yyline + 1, yytext());}
"println" {return new Symbol(sym.println_, yychar, yyline + 1, yytext());}

"+" {return new Symbol(sym.mas, yychar, yyline + 1, yytext());}
"-" {return new Symbol(sym.menos, yychar, yyline + 1, yytext());}
"*" {return new Symbol(sym.por, yychar, yyline + 1, yytext());}
">" {return new Symbol(sym.mayorQue, yychar, yyline + 1, yytext());}
"<" {return new Symbol(sym.menorQue, yychar, yyline + 1, yytext());}
">=" {return new Symbol(sym.mayorIgual, yychar, yyline + 1, yytext());}
"<=" {return new Symbol(sym.menorIgual, yychar, yyline + 1, yytext());}
"==" {return new Symbol(sym.igualIgual, yychar, yyline + 1, yytext());}
"&&" {return new Symbol(sym.and, yychar, yyline + 1, yytext());}

"," {return new Symbol(sym.coma, yychar, yyline + 1, yytext());}
";" {return new Symbol(sym.puntoComa, yychar, yyline + 1, yytext());}
"(" {return new Symbol(sym.parIzq, yychar, yyline + 1, yytext());}
")" {return new Symbol(sym.parDer, yychar, yyline + 1, yytext());}
"{" {return new Symbol(sym.llaveAbre, yychar, yyline + 1, yytext());}
"}" {return new Symbol(sym.llaveCierre, yychar, yyline + 1, yytext());}
                                                  
{entero} {return new Symbol(sym.entero, yychar, yyline + 1, yytext());}
{id} {return new Symbol(sym.id, yychar + 1, yyline + 1, yytext());}

"\n" {yychar = 1;}
{espacio} {/*Ignorar*/}
{ComentarioMulti} {yychar = 1;}

.
{
    System.out.println("Error lexico: " + yytext());
}