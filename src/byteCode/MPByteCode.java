package byteCode;

/**
 * Created by anthony on 01/06/17.
 * Clase abstracta para referenciar las instrucciones de ByteCode
 */
public abstract class MPByteCode {

    //Nombres mas faciles de recordar para las instrucciones ByteCode de Python
    static String
            CARGAR_CONST = "LOAD_CONST",
            CARGAR_VARIABLE = "LOAD_FAST",
            GUARDAR_VARIABLE = "STORE_FAST",
            CARGAR_GLOBAL = "LOAD_GLOBAL",
            LLAMAR_FUNCION = "CALL_FUNCTION",
            RETURN = "RETURN_VALUE",
            COMPARAR = "COMPARE_OP",
            RESTAR = "BINARY_SUBSTRACT",
            SUMAR = "BINARY_ADD",
            MULTIPLICAR = "BINARY_MULTIPLY",
            DIVIDIR = "BINARY_DIVIDE",
            MOD = "BINARY_MODULO",
            AND = "BINARY_AND",
            OR = "BINARY_OR",
            GUARDAR_EN_LISTA = "STORE_SUBSCR",
            CARGAR_DESDE_LISTA = "BINARY_SUBSCR",
            JMP_ABS = "JUMP_ABSOLUTE",
            JMP_IF_TRUE = "JUMP_IF_TRUE",
            JMP_IF_FALSE = "JUMP_IF_FALSE",
            CONSTRUIR_LISTA = "BUILD_LIST";

}
