package exceptions;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Pair;

/**
 * Created by jomi_ on 29/03/2017.
 */
public class DefaultError extends DefaultErrorStrategy
{
    @Override
    protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
        String msg = "input no concuerda " + this.getTokenErrorDisplay(e.getOffendingToken()) + " se esperaba " + e.getExpectedTokens().toString(recognizer.getVocabulary());
        recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }

    @Override
    protected void reportUnwantedToken(Parser recognizer) {
        if(!this.inErrorRecoveryMode(recognizer)) {
            this.beginErrorCondition(recognizer);
            Token t = recognizer.getCurrentToken();
            String tokenName = this.getTokenErrorDisplay(t);
            IntervalSet expecting = this.getExpectedTokens(recognizer);
            String msg = "input inesperado " + tokenName + " se esperaba " + expecting.toString(recognizer.getVocabulary());
            recognizer.notifyErrorListeners(t, msg, (RecognitionException)null);
        }
    }
    @Override
    protected void reportMissingToken(Parser recognizer) {
        if(!this.inErrorRecoveryMode(recognizer)) {
            this.beginErrorCondition(recognizer);
            Token t = recognizer.getCurrentToken();
            IntervalSet expecting = this.getExpectedTokens(recognizer);
            String msg = "hace falta " + expecting.toString(recognizer.getVocabulary()) + " en " + this.getTokenErrorDisplay(t);
            recognizer.notifyErrorListeners(t, msg, (RecognitionException)null);
        }
    }
    @Override
    protected Token getMissingSymbol(Parser recognizer) {
        Token currentSymbol = recognizer.getCurrentToken();
        IntervalSet expecting = this.getExpectedTokens(recognizer);
        int expectedTokenType = expecting.getMinElement();
        String tokenText;
        if(expectedTokenType == -1) {
            tokenText = "<falta EOF>";
        } else {
            tokenText = "<falta " + recognizer.getVocabulary().getDisplayName(expectedTokenType) + ">";
        }

        Token current = currentSymbol;
        Token lookback = recognizer.getInputStream().LT(-1);
        if(currentSymbol.getType() == -1 && lookback != null) {
            current = lookback;
        }

        return recognizer.getTokenFactory().create(new Pair(current.getTokenSource(), current.getTokenSource().getInputStream()), expectedTokenType, tokenText, 0, -1, -1, current.getLine(), current.getCharPositionInLine());
    }

    @Override
    protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
        TokenStream tokens = recognizer.getInputStream();
        String input;
        if(tokens != null) {
            if(e.getStartToken().getType() == -1) {
                input = "<EOF>";
            } else {
                input = tokens.getText(e.getStartToken(), e.getOffendingToken());
            }
        } else {
            input = "<input desconocido >";
        }

        String msg = "no hay alternativa viable en el input " + this.escapeWSAndQuote(input);
        recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }

}
