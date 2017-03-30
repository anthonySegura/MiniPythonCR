/**
 * Created by anthony on 20/03/17.
 */

import grammar.MPGrammarLexer;
import grammar.MPGrammarParser;

import com.textEditor.JCEditorMain;


public class Main {

    public static void main(String [] args){

        MPGrammarLexer scanner = null;
        MPGrammarParser parser = null;

        new JCEditorMain().run(scanner, parser);
    }
}
