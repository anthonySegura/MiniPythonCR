// Generated from /home/anthony/IdeaProjects/MiniPython2/MPGrammar.g4 by ANTLR 4.7
package grammar;

  import com.yuvalshavit.DenterHelper;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MPGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PIZQ=1, PDER=2, CIZQ=3, CDER=4, DPUNTOS=5, COMA=6, ASSIGN=7, SUMA=8, RESTA=9, 
		MUL=10, DIV=11, DIV_ENT=12, POT=13, MENOR_QUE=14, MAYOR_QUE=15, MENOR_QUE_IG=16, 
		MAYOR_QUE_IG=17, IGUAL=18, DEF=19, IF=20, ELSE=21, WHILE=22, RETURN=23, 
		PRINT=24, LEN=25, COMMENT=26, MULTILINE_COMMENT=27, WS=28, IDENTIFIER=29, 
		INTEGER=30, STRING=31, CHAR=32, NEWLINE=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PIZQ", "PDER", "CIZQ", "CDER", "DPUNTOS", "COMA", "ASSIGN", "SUMA", "RESTA", 
		"MUL", "DIV", "DIV_ENT", "POT", "MENOR_QUE", "MAYOR_QUE", "MENOR_QUE_IG", 
		"MAYOR_QUE_IG", "IGUAL", "DEF", "IF", "ELSE", "WHILE", "RETURN", "PRINT", 
		"LEN", "COMMENT", "MULTILINE_COMMENT", "WS", "IDENTIFIER", "INTEGER", 
		"STRING", "CHAR", "NEWLINE", "CharContenido", "CharInicial", "COMILLAS", 
		"SYMBOLS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'['", "']'", "':'", "','", "'='", "'+'", "'-'", "'*'", 
		"'/'", "'//'", "'**'", "'<'", "'>'", "'<='", "'>='", "'=='", "'def'", 
		"'if'", "'else'", "'while'", "'return'", "'print'", "'len'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PIZQ", "PDER", "CIZQ", "CDER", "DPUNTOS", "COMA", "ASSIGN", "SUMA", 
		"RESTA", "MUL", "DIV", "DIV_ENT", "POT", "MENOR_QUE", "MAYOR_QUE", "MENOR_QUE_IG", 
		"MAYOR_QUE_IG", "IGUAL", "DEF", "IF", "ELSE", "WHILE", "RETURN", "PRINT", 
		"LEN", "COMMENT", "MULTILINE_COMMENT", "WS", "IDENTIFIER", "INTEGER", 
		"STRING", "CHAR", "NEWLINE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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


	public MPGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MPGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00f5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\7\33\u009c\n\33"+
		"\f\33\16\33\u009f\13\33\3\33\5\33\u00a2\n\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\7\34\u00ab\n\34\f\34\16\34\u00ae\13\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\7\36\u00ba\n\36\f\36\16\36\u00bd\13\36"+
		"\3\37\3\37\3\37\7\37\u00c2\n\37\f\37\16\37\u00c5\13\37\3\37\3\37\3\37"+
		"\6\37\u00ca\n\37\r\37\16\37\u00cb\5\37\u00ce\n\37\3 \3 \3 \3 \7 \u00d4"+
		"\n \f \16 \u00d7\13 \3 \3 \3!\3!\3!\5!\u00de\n!\3!\3!\3\"\5\"\u00e3\n"+
		"\"\3\"\3\"\7\"\u00e7\n\"\f\"\16\"\u00ea\13\"\3#\3#\5#\u00ee\n#\3$\3$\3"+
		"%\3%\3&\3&\4\u009d\u00ac\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E\2G\2I\2K\2\3\2\t\7\2\13\13\17"+
		"\17\"\"))--\3\2$$\6\2\62;C\\aac|\4\2\13\13\"\"\7\2\62;aa\u00b9\u00b9\u0302"+
		"\u0371\u2041\u2042\20\2C\\aac|\u00c2\u00d8\u00da\u00f8\u00fa\u0301\u0372"+
		"\u037f\u0381\u2001\u200e\u200f\u2072\u2191\u2c02\u2ff1\u3003\ud801\uf902"+
		"\ufdd1\ufdf2\uffff\t\2,-//\61\61BB]]__}}\2\u00ff\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3M\3\2\2\2\5O\3\2\2\2\7Q\3\2\2\2\tS\3\2\2"+
		"\2\13U\3\2\2\2\rW\3\2\2\2\17Y\3\2\2\2\21[\3\2\2\2\23]\3\2\2\2\25_\3\2"+
		"\2\2\27a\3\2\2\2\31c\3\2\2\2\33f\3\2\2\2\35i\3\2\2\2\37k\3\2\2\2!m\3\2"+
		"\2\2#p\3\2\2\2%s\3\2\2\2\'v\3\2\2\2)z\3\2\2\2+}\3\2\2\2-\u0082\3\2\2\2"+
		"/\u0088\3\2\2\2\61\u008f\3\2\2\2\63\u0095\3\2\2\2\65\u0099\3\2\2\2\67"+
		"\u00a7\3\2\2\29\u00b3\3\2\2\2;\u00b7\3\2\2\2=\u00cd\3\2\2\2?\u00cf\3\2"+
		"\2\2A\u00da\3\2\2\2C\u00e2\3\2\2\2E\u00ed\3\2\2\2G\u00ef\3\2\2\2I\u00f1"+
		"\3\2\2\2K\u00f3\3\2\2\2MN\7*\2\2N\4\3\2\2\2OP\7+\2\2P\6\3\2\2\2QR\7]\2"+
		"\2R\b\3\2\2\2ST\7_\2\2T\n\3\2\2\2UV\7<\2\2V\f\3\2\2\2WX\7.\2\2X\16\3\2"+
		"\2\2YZ\7?\2\2Z\20\3\2\2\2[\\\7-\2\2\\\22\3\2\2\2]^\7/\2\2^\24\3\2\2\2"+
		"_`\7,\2\2`\26\3\2\2\2ab\7\61\2\2b\30\3\2\2\2cd\7\61\2\2de\7\61\2\2e\32"+
		"\3\2\2\2fg\7,\2\2gh\7,\2\2h\34\3\2\2\2ij\7>\2\2j\36\3\2\2\2kl\7@\2\2l"+
		" \3\2\2\2mn\7>\2\2no\7?\2\2o\"\3\2\2\2pq\7@\2\2qr\7?\2\2r$\3\2\2\2st\7"+
		"?\2\2tu\7?\2\2u&\3\2\2\2vw\7f\2\2wx\7g\2\2xy\7h\2\2y(\3\2\2\2z{\7k\2\2"+
		"{|\7h\2\2|*\3\2\2\2}~\7g\2\2~\177\7n\2\2\177\u0080\7u\2\2\u0080\u0081"+
		"\7g\2\2\u0081,\3\2\2\2\u0082\u0083\7y\2\2\u0083\u0084\7j\2\2\u0084\u0085"+
		"\7k\2\2\u0085\u0086\7n\2\2\u0086\u0087\7g\2\2\u0087.\3\2\2\2\u0088\u0089"+
		"\7t\2\2\u0089\u008a\7g\2\2\u008a\u008b\7v\2\2\u008b\u008c\7w\2\2\u008c"+
		"\u008d\7t\2\2\u008d\u008e\7p\2\2\u008e\60\3\2\2\2\u008f\u0090\7r\2\2\u0090"+
		"\u0091\7t\2\2\u0091\u0092\7k\2\2\u0092\u0093\7p\2\2\u0093\u0094\7v\2\2"+
		"\u0094\62\3\2\2\2\u0095\u0096\7n\2\2\u0096\u0097\7g\2\2\u0097\u0098\7"+
		"p\2\2\u0098\64\3\2\2\2\u0099\u009d\7%\2\2\u009a\u009c\13\2\2\2\u009b\u009a"+
		"\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a2\7\17\2\2\u00a1\u00a0\3"+
		"\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7\f\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a6\b\33\2\2\u00a6\66\3\2\2\2\u00a7\u00ac\7\u201e"+
		"\2\2\u00a8\u00ab\5I%\2\u00a9\u00ab\13\2\2\2\u00aa\u00a8\3\2\2\2\u00aa"+
		"\u00a9\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\7\u201f\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b2\b\34\2\2\u00b28\3\2\2\2\u00b3\u00b4\t\2\2\2"+
		"\u00b4\u00b5\3\2\2\2\u00b5\u00b6\b\35\2\2\u00b6:\3\2\2\2\u00b7\u00bb\5"+
		"G$\2\u00b8\u00ba\5E#\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc<\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be"+
		"\u00ce\7\62\2\2\u00bf\u00c3\4\63;\2\u00c0\u00c2\4\62;\2\u00c1\u00c0\3"+
		"\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\u00ce\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\4\62;\2\u00c7\u00c9\7\60"+
		"\2\2\u00c8\u00ca\4\62;\2\u00c9\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00be\3\2"+
		"\2\2\u00cd\u00bf\3\2\2\2\u00cd\u00c6\3\2\2\2\u00ce>\3\2\2\2\u00cf\u00d5"+
		"\7$\2\2\u00d0\u00d1\7$\2\2\u00d1\u00d4\7$\2\2\u00d2\u00d4\n\3\2\2\u00d3"+
		"\u00d0\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8"+
		"\u00d9\7$\2\2\u00d9@\3\2\2\2\u00da\u00dd\7)\2\2\u00db\u00de\t\4\2\2\u00dc"+
		"\u00de\5K&\2\u00dd\u00db\3\2\2\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2"+
		"\2\u00df\u00e0\7)\2\2\u00e0B\3\2\2\2\u00e1\u00e3\7\17\2\2\u00e2\u00e1"+
		"\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e8\7\f\2\2\u00e5"+
		"\u00e7\t\5\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9D\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ee"+
		"\5G$\2\u00ec\u00ee\t\6\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee"+
		"F\3\2\2\2\u00ef\u00f0\t\7\2\2\u00f0H\3\2\2\2\u00f1\u00f2\4\u201e\u201f"+
		"\2\u00f2J\3\2\2\2\u00f3\u00f4\t\b\2\2\u00f4L\3\2\2\2\21\2\u009d\u00a1"+
		"\u00aa\u00ac\u00bb\u00c3\u00cb\u00cd\u00d3\u00d5\u00dd\u00e2\u00e8\u00ed"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}