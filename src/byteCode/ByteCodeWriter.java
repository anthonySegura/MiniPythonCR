package byteCode;

import java.io.PrintWriter;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import com.textEditor.JCEditor;

/**
 * Created by anthony on 01/06/17.
 * Clase que genera el archivo con el ByteCode
 */
public class ByteCodeWriter {

    private static final String path = "./bytecode.txt";

    public static void write(List<Instruction> instructions){
        try {
            FileWriter write = new FileWriter(path, false);
            PrintWriter print_line = new PrintWriter(write);

            for(Instruction inst : instructions){
                print_line.println(inst.toString());
            }

            print_line.close();
            JCEditor.showMessage("Archivo creado en raiz del proyecto/bytecode.txt");
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

}
