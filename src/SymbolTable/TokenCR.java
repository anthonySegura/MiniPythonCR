package SymbolTable;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

/**
 * Created by anthony on 04/05/17.
 */
public class TokenCR extends CommonToken{

    //Atributos para las listas
    private int [] tipoParametros;
    private boolean esLista;

    public TokenCR(int type, String text) {
        super(type, text);
    }

    /**
     * Constructor para las listas
     * @param type
     * @param text
     * @param token
     */
    public TokenCR(int type, String text, int [] lista) {
        super(type, text);
        this.esLista = true;
        this.tipoParametros = lista;
    }

    public String getNombre(){
        return super.getText();
    }

    public int getTipo(){
        return super.getType();
    }
}
