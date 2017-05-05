package SymbolTable;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

/**
 * Created by anthony on 04/05/17.
 */
public class TokenCR extends CommonToken{

    //Atributos para las listas
    private Object [] tipoParametros;
    private String nombre;
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
    public TokenCR(int type, String text, Object [] lista) {
        super(type, text);
        this.esLista = true;
        this.tipoParametros = lista;
    }

    public Object [] getLista(){
        return this.tipoParametros;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        if(nombre == null){
            return super.getText();
        }
        else{
            return this.nombre;
        }

    }

    public int getTipo(){
        return super.getType();
    }
}
