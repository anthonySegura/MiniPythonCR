package SymbolTable;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

/**
 * Created by anthony on 22/04/17.
 * Tabla para cada scope
 */
public class Scope {

    String nombre;
    List<Identificador> registros;

    /**
     * Clase para representar cada identificador en la tabla
     */
    class Identificador {
        Token token;
        ParserRuleContext decl;

        public Identificador(Token token, ParserRuleContext context) {
            this.token = token;
            this.decl = context;
        }
    }

    public Scope(String nombre){
        this.nombre = nombre;
    }

    /**
     * Agrega un nuevo identificador al scope actual
     * @param nombre
     * @param tipo
     * @param ctx
     */
    public void insertar(String nombre, int tipo, ParserRuleContext ctx){
        Token tkn = new CommonToken(tipo, nombre);
        Identificador id = new Identificador(tkn, ctx);

        registros.add(id);
    }

    /**
     * Busca un identificador en el scope por el nombre
     * @param nombre
     * @return
     */
    public Identificador buscar(String nombre){
        for(Identificador id : registros){
            if(id.token.getText().equals(nombre)){
                return id;
            }
        }
        return null;
    }

    /**
     * Muestra el nombre del scope y los identificadores que estan dentro
     */
    public void imprimirScope(){
        System.out.println(this.nombre);
        for(Identificador id : registros){
            System.out.println("\t" + id.token.getText() + " | " + id.token.getType());
        }
    }
}
