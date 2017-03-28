package com.textEditor;

import grammar.MPGrammarLexer;
import grammar.MPGrammarParser;
/**
* Classe que inicia o JCEditor
* @author   Cristian Henrique (cristianmsbr@gmail.com)
* @version  1.5
* @since    Desde a primeira versão
*/

public class JCEditorMain {

	/**
	* Método que inicia o programa a partir da classe "Preferencias"
	* @param   args
	*/
	public static void run(MPGrammarLexer scanner, MPGrammarParser parser) {
		new Preferencias(scanner, parser).verificar();
	}
}