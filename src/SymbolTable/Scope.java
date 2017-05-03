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
        private ParserRuleContext decl;
        //Atributos para las funciones
        private Object [] parametros;
        private int [] tipoParametros;  //Codigo del tipo de dato de cada parametro
        private boolean esFuncion;

        /**
         * Constructor para las variables y parametros
         * @param token
         * @param context
         * @param valor
         */
        public Identificador(Token token, ParserRuleContext context) {
            this.nombre = token.getText();
            this.tipo = token.getType();
            this.decl = context;
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
        public Identificador(Token token, ParserRuleContext context, Object [] parametros,int [] tipoParametros, int tipoRetorno) {
            this.nombre = token.getText();
            this.tipo = tipoRetorno;
            this.decl = context;
            this.esFuncion = true;
            this.parametros = parametros;
            this.tipoParametros = tipoParametros;
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

        public int[] getTipoParametros() {
            return tipoParametros;
        }

        public void setTipoParametros(int[] tipoParametros) {
            this.tipoParametros = tipoParametros;
        }

        public ParserRuleContext getDecl() {

            return decl;
        }

        public void setDecl(ParserRuleContext decl) {
            this.decl = decl;
        }

        public Object[] getParametros() {
            return parametros;
        }

        private String parametrosToString(){
            String p = "";
            for(Object obj : parametros){
                p += obj.toString() + ",";
            }
            return p;
        }

        private String tiposParamsToString(){
            String p = "";

            for(int tp : tipoParametros){
                p += Table._SYMBOLIC_NAMES[tp] + ",";
            }

            return p;
        }

        public void mostrarInformacion(){
            if(!esFuncion) {
                String tipo = (getTipo() == Table.BOOL)? "Boolean" : Table._SYMBOLIC_NAMES[getTipo()];
                System.out.println("\t" + getNombre() + " | " + tipo);
            }
            else {
                String tipoRet = (getTipo() == Table.NULL)? "None" : Table._SYMBOLIC_NAMES[getTipo()];
                System.out.println("\t" + getNombre() + " | " + "Funcion" + " | " + tipoRet + " | " + parametrosToString() +
                        " | " + tiposParamsToString());
            }
        }
    }

    public Scope(String nombre){
        this.registros = new ArrayList<>();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Agrega un nuevo identificador al scope actual
     * @param nombre
     * @param tipo
     * @param ctx
     */
    public void insertar(Token token, ParserRuleContext ctx){

        Identificador id = new Identificador(token, ctx);

        registros.add(id);
    }

    public void insertarFuncion(Token token, ParserRuleContext context, Object [] parametros,int [] tipoParametros, int tipoRetorno){
        Identificador id = new Identificador(token, context, parametros,tipoParametros,tipoRetorno);

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
