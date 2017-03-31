package exceptions;

import com.textEditor.JCEditor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;

import java.util.Collections;
import java.util.List;

/**
 * Created by jomi_ on 30/03/2017.
 */
public class TokensExeption extends ConsoleErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        String tmp = JCEditor.consoleTextArea.getText() + "\n";
        tmp += "Línea " + line + ":" + charPositionInLine + " " + msg + "\n";
        JCEditor.showMessage(tmp);

    }
}
