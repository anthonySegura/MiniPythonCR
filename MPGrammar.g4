grammar MPGrammar;

/**
    Scanner
**/

tokens { INDENT, DEDENT }

options
{

}

@lexer::header {
  import com.yuvalshavit.DenterHelper;
}

@lexer::members {
  private final DenterHelper denter = DenterHelper.builder()
    .nl(NEWLINE)
    .indent(MPGrammarParser.INDENT)
    .dedent(MPGrammarParser.DEDENT)
    .pullToken(MPGrammarLexer.super::nextToken);

  @Override
  public Token nextToken() {
    return denter.nextToken();
  }

  @Override
   public void notifyListeners(LexerNoViableAltException e) {
     String text = this._input.getText(Interval.of(this._tokenStartCharIndex, this._input.index()));
     String msg = "error reconociendo token : \'" + this.getErrorDisplay(text) + "\'";
     ANTLRErrorListener listener = this.getErrorListenerDispatch();
     listener.syntaxError(this, (Object)null, this._tokenStartLine, this._tokenStartCharPositionInLine, msg, e);
  }
}


//Tokens para simbolos

//Simbolos Generales
PIZQ        :   '(';
PDER        :   ')';
CIZQ        :   '[';
CDER        :   ']';
DPUNTOS     :   ':';
COMA        :   ',';
ASSIGN      :   '=';

//Operadores Matematicos
SUMA        :   '+';
RESTA       :   '-';
MUL         :   '*';
DIV         :   '/';
DIV_ENT     :   '//';
POT         :   '**';

//Operadores Logicos
MENOR_QUE     :   '<';
MAYOR_QUE     :   '>';
MENOR_QUE_IG  :   '<=';
MAYOR_QUE_IG  :   '>=';
IGUAL         :   '==';

//Palabras Reservadas
DEF         :   'def';
IF          :   'if';
ELSE        :   'else';
WHILE       :   'while';
RETURN      :   'return';
PRINT       :   'print';
LEN         :   'len';


//Elementos ignorados
COMMENT     :  '#'.*?'\r'?'\n' -> skip ;
MULTILINE_COMMENT : '“' (COMILLAS | . )*? '”' -> skip;

WS : [' '+\r\t] -> skip ;

//Expresiones regulares
IDENTIFIER  :   CharInicial CharContenido* ;
INTEGER
            :	'0'
        	| '1'..'9' ('0'..'9')*
        	| '0'..'9' '.' ('0'..'9')+
        	;

STRING      :  '"' ('""'|~'"')* '"' ;
CHAR        :  '\'' ('A'..'Z' | 'a'..'z' | '0'..'9'| '_' | SYMBOLS) '\'';
NEWLINE : ('\r'? '\n' (' ' | '\t')* );



fragment
CharContenido
   : CharInicial
   | '0'..'9'
   | '_'
   | '\u00B7'
   | '\u0300'..'\u036F'
   | '\u203F'..'\u2040'
   ;
fragment
CharInicial
   : 'A'..'Z' | 'a'..'z'
   | '_'
   | '\u00C0'..'\u00D6'
   | '\u00D8'..'\u00F6'
   | '\u00F8'..'\u02FF'
   | '\u0370'..'\u037D'
   | '\u037F'..'\u1FFF'
   | '\u200C'..'\u200D'
   | '\u2070'..'\u218F'
   | '\u2C00'..'\u2FEF'
   | '\u3001'..'\uD7FF'
   | '\uF900'..'\uFDCF'
   | '\uFDF0'..'\uFFFD'
   ;

fragment
 COMILLAS  :  '“' | '”';

fragment
 SYMBOLS   : '+' | '-' | '*' | '/' | '@' | '{' | '[' | ']';

/**

    Parser

**/

//Reglas gramaticales

program         :   statement (statement)*                                          #programN ;

statement       :   defStatement                                                    #statdef
                | ifStatement                                                       #statif
                | returnStatement                                                   #statret
                | printStatement                                                    #statprint
                | whileStatement                                                    #statwhile
                | assignStatement                                                   #statassign
                | functionCallStatement                                             #statfncall
                ;

defStatement    :   DEF IDENTIFIER PIZQ argList PDER DPUNTOS sequence               #defstat;

argList         :   IDENTIFIER moreArgs                                             #morearglist
                |                                                                   #epsarglist
                ;

moreArgs        :   (COMA IDENTIFIER)*                                              #moreargsN;
ifStatement     :   IF expression DPUNTOS sequence ELSE DPUNTOS sequence            #ifstat;
whileStatement  :   WHILE expression DPUNTOS sequence                               #whilestat;
returnStatement :   RETURN expression NEWLINE                                       #returnstat;
printStatement  :   PRINT PIZQ expression PDER NEWLINE                              #printstat;
assignStatement :   IDENTIFIER ASSIGN expression NEWLINE                            #assignstat;
functionCallStatement   :  primitiveExpression PIZQ expressionList PDER NEWLINE     #fncallstat;

sequence      :   INDENT moreStatement DEDENT                                       #seq;

//sequence      : NEWLINE INDENT moreStatement DEDENT;

moreStatement   :   statement (statement)*                                          #morestats;
expression      :   additionExpression comparison                                   #exprsn;
comparison      :   ((MENOR_QUE | MAYOR_QUE | MENOR_QUE_IG | MAYOR_QUE_IG | IGUAL) additionExpression )* #cmparison;
additionExpression  :  multiplicationExpression additionFactor                      #addexp;
additionFactor      :  ((SUMA | RESTA) multiplicationExpression)*                   #addfact;
multiplicationExpression    :  elementExpression multiplicationFactor               #mulexp;
multiplicationFactor        :  ((MUL | DIV) elementExpression )*                    #mulfact;
elementExpression           :  primitiveExpression elementAccess                    #elmntexp;
elementAccess               :  (CIZQ expression CDER)*                              #elmntacess;
functionCallExpression      :  IDENTIFIER PIZQ expressionList PDER                  #fncallexp;
expressionList              : expression moreExpressions                            #moreexplist
                            |                                                       #epsexplist
                            ;


moreExpressions             : (COMA expression)*                                    #moreexps;

primitiveExpression         : INTEGER                                               #intexp
                            | STRING                                                #strexp
                            | IDENTIFIER                                            #idexp
                            | CHAR                                                  #chaexp
                            | PIZQ expression PDER                                  #pizqexp
                            | listExpression                                        #primlistexp
                            | LEN PIZQ expression PDER                              #lenexp
                            | functionCallExpression                                #primfncallexp
                            ;


listExpression              : CIZQ expressionList CDER;