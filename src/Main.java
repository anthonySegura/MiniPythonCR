/**
 * Created by anthony on 20/03/17.
 */

import grammar.MPGrammarLexer;
import grammar.MPGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;

import java.io.FileReader;
import java.util.List;
import com.textEditor.JCEditorMain;


public class Main {

    public static void main(String [] args){

        MPGrammarLexer scanner = null;
        MPGrammarParser parser = null;

        new JCEditorMain().run(scanner, parser);
    }
}
