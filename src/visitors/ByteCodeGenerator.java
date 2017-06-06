package visitors;

import byteCode.Instruction;
import byteCode.MPByteCode;
import grammar.MPGrammarBaseVisitor;
import grammar.MPGrammarParser;
import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthony on 01/06/17.
 */
public class ByteCodeGenerator extends BaseVisitorCR {

    private List<Instruction> instructions = new ArrayList<>();
    private int lineCount = 1;

    public void imprimir(){

        for(Instruction ins : instructions){
            System.out.println(ins.toString());
        }
    }


    @Override
    public Object visitProgramN(MPGrammarParser.ProgramNContext ctx) {

        visit(ctx.statement(0));
        for (int i = 1; i <= ctx.statement().size() - 1; i++){
            visit(ctx.statement(i));
        }

        return null;
    }

    @Override
    public Object visitStatdef(MPGrammarParser.StatdefContext ctx) {

        return visit(ctx.defStatement());
    }

    @Override
    public Object visitStatif(MPGrammarParser.StatifContext ctx) {

        return visit(ctx.ifStatement());
    }

    @Override
    public Object visitStatret(MPGrammarParser.StatretContext ctx) {

        return visit(ctx.returnStatement());
    }

    @Override
    public Object visitStatprint(MPGrammarParser.StatprintContext ctx)
    {

        return visit(ctx.printStatement());
    }

    @Override
    public Object visitStatwhile(MPGrammarParser.StatwhileContext ctx)
    {

        return visit(ctx.whileStatement());
    }

    @Override
    public Object visitStatassign(MPGrammarParser.StatassignContext ctx)
    {
        visit(ctx.assignStatement());

        return null;
    }

    @Override
    public Object visitStatfncall(MPGrammarParser.StatfncallContext ctx)
    {
        return visit(ctx.functionCallStatement());
    }

    @Override
    public Object visitDefstat(MPGrammarParser.DefstatContext ctx) {


        visit(ctx.argList());

        //Se escribe el codigo del cuerpo de la funcion
        visit(ctx.sequence());

        return null;
    }

    @Override
    public Object visitMorearglist(MPGrammarParser.MorearglistContext ctx) {

        visit(ctx.moreArgs());
        return null;
    }

    @Override
    public Object visitEpsarglist(MPGrammarParser.EpsarglistContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreargsN(MPGrammarParser.MoreargsNContext ctx) {


        return null;
    }

    @Override
    public Object visitIfstat(MPGrammarParser.IfstatContext ctx)
    {

        visit(ctx.expression());
        //System.out.println("JUMP_IF_TRUE " + "xx");
        Instruction temp = new Instruction(MPByteCode.JMP_IF_TRUE, lineCount++);
        instructions.add(temp);
        visit(ctx.sequence(1));
        temp.setJmpLine(lineCount + 1);
        //System.out.println("JUMP_ABSOLUTE " + "yy");
        Instruction temp2 = new Instruction(MPByteCode.JMP_ABS, lineCount++);
        instructions.add(temp2);
        visit(ctx.sequence(0));
        temp2.setJmpLine(lineCount);

        return null;
    }

    /**
     * Salta si es falso. Se escribe el código del cuerpo del while y se salta a la linea siguiente
     * @param ctx
     * @return
     */
    @Override
    public Object visitWhilestat(MPGrammarParser.WhilestatContext ctx) {

        //Respaldo de la linea de la condicion
        int condicion = lineCount;
        //Codigo de la condicion
        visit(ctx.expression());
        //Salto si es falso
        Instruction jmp = new Instruction(MPByteCode.JMP_IF_FALSE, lineCount++);
        instructions.add(jmp);
        //Codigo del cuerpo del while
        visit(ctx.sequence());
        jmp.setJmpLine(lineCount + 1);

        //Salto a la comparación
        Instruction jmpAbs = new Instruction(MPByteCode.JMP_ABS, lineCount++);
        jmpAbs.setJmpLine(condicion);
        instructions.add(jmpAbs);

        return null;
    }

    @Override
    public Object visitReturnstat(MPGrammarParser.ReturnstatContext ctx) {

        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitPrintstat(MPGrammarParser.PrintstatContext ctx) {

        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitAssignstat(MPGrammarParser.AssignstatContext ctx)
    {

        Object r = visit(ctx.expression());
        if(r != null){
            instructions.add(new Instruction(MPByteCode.CONSTRUIR_LISTA + " " + (int)r, lineCount++));
        }


        instructions.add(new Instruction(MPByteCode.GUARDAR_VARIABLE + " " + ctx.IDENTIFIER() , lineCount++));
        return null;
    }

    @Override
    public Object visitFncallstat(MPGrammarParser.FncallstatContext ctx) {

        visit(ctx.expressionList());

        return null;
    }

    @Override
    public Object visitSeq(MPGrammarParser.SeqContext ctx) {

        visit(ctx.moreStatement());

        return null;
    }

    @Override
    public Object visitMorestats(MPGrammarParser.MorestatsContext ctx)
    {

        for (int i=0; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }


        return null;
    }

    @Override
    public Object visitExprsn(MPGrammarParser.ExprsnContext ctx)
    {

        Object r = visit(ctx.additionExpression());
        visit(ctx.comparison());
        return r;
    }


    private String getOp(MPGrammarParser.CmparisonContext ctx){
        String op = null;

        if(ctx.IGUAL().size() != 0){
            op = "==";
        }
        else if(ctx.MAYOR_QUE().size() != 0){
            op = ">";
        }
        else if(ctx.MENOR_QUE().size() != 0){
            op = "<";
        }
        else if(ctx.MAYOR_QUE_IG().size() != 0){
            op = ">=";
        }
        else if(ctx.MENOR_QUE_IG().size() != 0){
            op = "<=";
        }

        return op;
    }

    @Override
    public Object visitCmparison(MPGrammarParser.CmparisonContext ctx)
    {

        for(int i = 0; i < ctx.additionExpression().size(); i++){
            visit(ctx.additionExpression(i));
            String op = getOp(ctx);
            instructions.add(new Instruction(MPByteCode.COMPARAR + " " + op, lineCount++));
        }

        return null;
    }

    @Override
    public Object visitAddexp(MPGrammarParser.AddexpContext ctx)
    {
        Object r = visit(ctx.multiplicationExpression());

        visit(ctx.additionFactor());

        return r;
    }

    @Override
    public Object visitAddfact(MPGrammarParser.AddfactContext ctx) {

        for(int i = 0; i < ctx.multiplicationExpression().size(); i++){
            visit(ctx.multiplicationExpression(i));
            if(!ctx.SUMA().isEmpty()){
                instructions.add(new Instruction(MPByteCode.SUMAR, lineCount++));

            }
            else if(!ctx.RESTA().isEmpty()){
                instructions.add(new Instruction(MPByteCode.RESTAR, lineCount++));
            }
        }

        return null;
    }

    @Override
    public Object visitMulexp(MPGrammarParser.MulexpContext ctx) {

        Object r = visit(ctx.elementExpression());

        visit(ctx.multiplicationFactor());

        return r;
    }

    @Override
    public Object visitMulfact(MPGrammarParser.MulfactContext ctx)
    {

        for(int i = 0; i < ctx.elementExpression().size(); i++){
            if(!ctx.MUL().isEmpty()){
                visit(ctx.elementExpression(i));
                instructions.add(new Instruction(MPByteCode.MULTIPLICAR, lineCount++));
            }
            else if(!ctx.DIV().isEmpty()){
                visit(ctx.elementExpression(i));
                instructions.add(new Instruction(MPByteCode.DIVIDIR, lineCount++));
            }

        }
        return null;
    }

    @Override
    public Object visitElmntexp(MPGrammarParser.ElmntexpContext ctx) {

        Object r = visit(ctx.primitiveExpression());

        visit(ctx.elementAccess());

        return r;
    }

    @Override
    public Object visitElmntacess(MPGrammarParser.ElmntacessContext ctx) {

        for(int i = 0; i < ctx.expression().size(); i++){
            visit(ctx.expression(i));
            instructions.add(new Instruction(MPByteCode.CARGAR_DESDE_LISTA, lineCount++));
        }

        return null;
    }

    @Override
    public Object visitFncallexp(MPGrammarParser.FncallexpContext ctx) {

        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitMoreexplist(MPGrammarParser.MoreexplistContext ctx) {

        visit(ctx.expression());
        int i = (int)visit(ctx.moreExpressions());

        return i + 1;
    }

    @Override
    public Object visitEpsexplist(MPGrammarParser.EpsexplistContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreexps(MPGrammarParser.MoreexpsContext ctx) {

        for(int i = 0; i < ctx.expression().size(); i++){
            visit(ctx.expression(i));
        }

        return ctx.expression().size();
    }

    @Override
    public Object visitIntexp(MPGrammarParser.IntexpContext ctx) {

        instructions.add(new Instruction(MPByteCode.CARGAR_CONST + " " + ctx.INTEGER(), lineCount++));
        return null;


    }

    @Override
    public Object visitStrexp(MPGrammarParser.StrexpContext ctx) {

        instructions.add(new Instruction(MPByteCode.CARGAR_CONST + " " + ctx.STRING(), lineCount++));
        return null;
    }

    @Override
    public Object visitIdexp(MPGrammarParser.IdexpContext ctx) {

        instructions.add(new Instruction(MPByteCode.CARGAR_VARIABLE +  " " + ctx.IDENTIFIER(), lineCount++));
        return null;
    }

    @Override
    public Object visitChaexp(MPGrammarParser.ChaexpContext ctx) {
        instructions.add(new Instruction(MPByteCode.CARGAR_CONST + " " + ctx.CHAR(), lineCount++));
        return null;
    }

    @Override
    public Object visitPizqexp(MPGrammarParser.PizqexpContext ctx) {

        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitPrimlistexp(MPGrammarParser.PrimlistexpContext ctx) {

        return visit(ctx.listExpression());
    }

    @Override
    public Object visitLenexp(MPGrammarParser.LenexpContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimfncallexp(MPGrammarParser.PrimfncallexpContext ctx) {
        return null;
    }

    @Override
    public Object visitListExpression(MPGrammarParser.ListExpressionContext ctx) {

        return visit(ctx.expressionList());

    }
}



