package byteCode;

/**
 * Created by anthony on 01/06/17.
 */
public class Instruction {

    String code;
    int line;
    boolean jmp;
    int jmpLine;

    //Atributos para las funciones
    String [] args;
    boolean isFunction = true;

    public Instruction(String code, int line) {
        this.code = code;
        this.line = line;
        jmp = false;
    }

    public Instruction(String code, String [] args){
        isFunction = true;
        this.code = code;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getJmpLine() {
        return jmpLine;
    }

    public void setJmpLine(int jmpLine) {
        this.jmpLine = jmpLine;
        this.jmp = true;
    }

    public String toString(){
        return this.getLine() + " " + this.getCode() + " " + ((jmp)? jmpLine : " ");
    }
}
