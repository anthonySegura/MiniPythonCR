package SymbolTable;

import grammar.MPGrammarParser;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
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
    public class Identificador {

        private String nombre;
        private int tipo;
        private Object valor;
        private ParserRuleContext decl;
        //Atributos para las funciones
        private Object [] parametros;
        private int tipoRetorno;
        private boolean esFuncion;

        /**
         * Constructor para las variables y parametros
         * @param token
         * @param context
         * @param valor
         */
        public Identificador(Token token, ParserRuleContext context, Object valor) {
            this.nombre = token.getText();
            this.tipo = token.getType();
            this.decl = context;
            this.valor = valor;
            this.esFuncion = false;
        }

        /**
         * Constructor para las funciones
         * @param token
         * @param context
         * @param valor
         * @param parametros
         * @param tipoRetorno
         */
        public Identificador(Token token, ParserRuleContext context, Object valor, Object [] parametros, int tipoRetorno) {
            this.nombre = token.getText();
            this.tipo = Table.FUNCION;
            this.decl = context;
            this.valor = valor;
            this.esFuncion = true;
            this.parametros = parametros;
            this.tipoRetorno = tipoRetorno;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        public Object getValor() {
            return valor;
        }

        public void setValor(Object valor) {
            this.valor = valor;
        }

        private String parametrosToString(){
            String p = "";
            for(Object obj : parametros){
                p += obj.toString() + ",";
            }
            return p;
        }

        public void mostrarInformacion(){
            if(!esFuncion)
                System.out.println("\t" + getNombre() + " | " + MPGrammarParser._SYMBOLIC_NAMES[getTipo()] + " | " + getValor());
            else {
                String tipoRet = (tipoRetorno == Table.NULL)? "None" : MPGrammarParser._SYMBOLIC_NAMES[tipoRetorno];
                System.out.println("\t" + getNombre() + " | " + "Funcion" + " | " + tipoRet + " | " + parametrosToString());
            }
        }
    }

    public Scope(String nombre){
        this.registros = new ArrayList<>();
        this.nombre = nombre;
    }

    /**
     * Agrega un nuevo identificador al scope actual
     * @param nombre
     * @param tipo
     * @param ctx
     */
    public void insertar(Token token, ParserRuleContext ctx, Object valor){

        Identificador id = new Identificador(token, ctx , valor);

        registros.add(id);
    }

    public void insertarFuncion(Token token, ParserRuleContext context, Object valor, Object [] parametros, int tipoRetorno){
        Identificador id = new Identificador(token, context, valor, parametros, tipoRetorno);

        registros.add(id);
    }

    /**
     * Busca un identificador en el scope por el nombre
     * @param nombre
     * @return
     */
    public Identificador buscar(String nombre){
        for(Identificador id : registros){
            if(id.getNombre().equals(nombre)){
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
            id.mostrarInformacion();
        }
    }
}
