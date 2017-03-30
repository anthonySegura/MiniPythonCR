package exceptions;

import com.textEditor.JCEditor;
import org.antlr.v4.runtime.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by anthony on 26/03/17.
 */
public class SyntaxException extends BaseErrorListener {


    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,int line, int charPositionInLine, String msg, RecognitionException e) {

        List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
        Collections.reverse(stack);

        String tmp = JCEditor.consoleTextArea.getText() + "\n";
        tmp += "Línea " + line + ":" + charPositionInLine + " " + msg + "\n";
        tmp += underlineError(recognizer, (Token)offendingSymbol, line, charPositionInLine);

        JCEditor.showMessage(tmp);

    }

    
    private String underlineError(Recognizer recognizer, Token offendingToken, int line, int charPositionInLine) {
        CommonTokenStream tokens = (CommonTokenStream)recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String underline = "";
        String errorLine = lines[line - 1];
        underline += errorLine + "\n";
        for (int i=0; i<charPositionInLine; i++) underline += " ";
        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        if ( start>=0 && stop>=0 ) {
            for (int i=start; i<=stop; i++) underline += "^";
        }
        return underline + "\n";
    }

}


