package SymbolTable;

import java.util.Stack;

/**
 * Created by anthony on 22/04/17.
 * Tabla de simbolos general
 */

public class Table {

    //Pila de subtablas. Cada tabla es un scope
    private Stack<Scope> scopes;
    //Tipos de datos adicionales
    public static final int FUNCION = 100, NULL = -1, BOOL = 300, ERROR = 200;
    //Nombre de los tokens
    public static final String[] _SYMBOLIC_NAMES = {
            null, "PIZQ", "PDER", "CIZQ", "CDER", "DPUNTOS", "COMA", "ASSIGN", "SUMA",
            "RESTA", "MUL", "DIV", "DIV_ENT", "POT", "MENOR_QUE", "MAYOR_QUE", "MENOR_QUE_IG",
            "MAYOR_QUE_IG", "IGUAL", "DEF", "IF", "ELSE", "WHILE", "RETURN", "PRINT",
            "LEN", "COMMENT", "MULTILINE_COMMENT", "WS", "IDENTIFIER", "INTEGER",
            "STRING", "CHAR", "NEWLINE", "INDENT", "DEDENT"
    };

    public Table(){
        scopes = new Stack<>();
        scopes.push(new Scope("Global_Scope"));
    }

    /**
     * Agrega un nuevo scope a la tabla
     * @param nombre
     */
    public void abrirScope(String nombre){
        scopes.push(new Scope(nombre));
    }

    /**
     * Elimina el ultimo scope de la tabla
     */
    public void cerrarScope(){
        scopes.pop();
    }

    /**
     * Regresa el scope mas reciente
     * @return
     */
    public Scope scopeActual(){
        return scopes.lastElement();
    }

    /**
     * Busca en la tabla de simbolos empezando desde el scope mas reciente
     * @param nombre
     * @return Identificador
     */
    public Scope.Identificador buscar(String nombre){
        Scope.Identificador id = null;
        for(int i = scopes.size() - 1; i >= 0; i--){
            id = scopes.get(i).buscar(nombre);
            if(id != null){
                break;
            }
        }
        return id;
    }

}
