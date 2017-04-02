package exceptions;

import com.textEditor.JCEditor;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.Collections;
import java.util.List;

/**
 * Created by jomi_ on 30/03/2017.
 */
public class TokensException extends ConsoleErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String tmp = JCEditor.consoleTextArea.getText() + "\n";
        tmp += "Línea " + line + ":" + charPositionInLine + " " + msg + "\n";
        JCEditor.showMessage(tmp);

    }
}
