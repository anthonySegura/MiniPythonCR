package byteCode;

/**
 * Created by anthony on 01/06/17.
 * Clase abstracta para referenciar las instrucciones de ByteCode
 */
public abstract class MPByteCode {

    //Constantes para las instrucciones ByteCode de Python
    public static String
            CARGAR_CONST = "LOAD_CONST \t",
            CARGAR_VARIABLE = "LOAD_FAST \t",
            GUARDAR_VARIABLE = "STORE_FAST \t",
            CARGAR_GLOBAL = "LOAD_GLOBAL \t",
            LLAMAR_FUNCION = "CALL_FUNCTION \t",
            RETURN = "RETURN_VALUE \t",
            COMPARAR = "COMPARE_OP \t",
            RESTAR = "BINARY_SUBSTRACT \t",
            SUMAR = "BINARY_ADD \t",
            MULTIPLICAR = "BINARY_MULTIPLY \t",
            DIVIDIR = "BINARY_DIVIDE \t",
            MOD = "BINARY_MODULO \t",
            AND = "BINARY_AND \t",
            OR = "BINARY_OR \t",
            GUARDAR_EN_LISTA = "STORE_SUBSCR \t",
            CARGAR_DESDE_LISTA = "BINARY_SUBSCR \t",
            JMP_ABS = "JUMP_ABSOLUTE \t",
            JMP_IF_TRUE = "JUMP_IF_TRUE \t",
            JMP_IF_FALSE = "JUMP_IF_FALSE \t",
            CONSTRUIR_LISTA = "BUILD_LIST \t";

}
