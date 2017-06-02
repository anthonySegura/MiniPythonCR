package visitors;

import grammar.MPGrammarBaseVisitor;
import grammar.MPGrammarParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.Stack;

/**
 * Created by anthony on 28/03/17.
 */
public class ASTVisitor extends BaseVisitorCR{

    private DefaultMutableTreeNode raiz;
    //Pila para controlar los niveles del arbol
    private Stack<DefaultMutableTreeNode> niveles = new Stack<>();

    private void agregarNivel(DefaultMutableTreeNode nodo){
        niveles.push(nodo);
    }

    private void subirNivel(){

        niveles.pop();
    }

    private DefaultMutableTreeNode nivelActual(){

        return niveles.lastElement();
    }


    @Override
    public Object visitProgramN(MPGrammarParser.ProgramNContext ctx) {

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        raiz = nodo;
        //Primer nivel del arbol
        agregarNivel(nodo);

        //Visita todas los statements
        visit(ctx.statement(0));
        for(int i = 1; i <= ctx.statement().size() - 1; i++){
            subirNivel();
            visit(ctx.statement(i));
        }

        return null;
    }

    @Override
    public Object visitStatdef(MPGrammarParser.StatdefContext ctx){

        //DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //nivelActual().add(nodo);
        //agregarNivel(nodo);
        visit(ctx.defStatement());

        //subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code statif}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatif(MPGrammarParser.StatifContext ctx){

        //DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //nivelActual().add(nodo);
        //agregarNivel(nodo);
        visit(ctx.ifStatement());
        //subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code statret}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatret(MPGrammarParser.StatretContext ctx){
        //DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //nivelActual().add(nodo);
        //agregarNivel(nodo);
        visit(ctx.returnStatement());


        //subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code statprint}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatprint(MPGrammarParser.StatprintContext ctx){
        //DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //nivelActual().add(nodo);
        //agregarNivel(nodo);
        visit(ctx.printStatement());

        //subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code statwhile}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatwhile(MPGrammarParser.StatwhileContext ctx){
        //DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //nivelActual().add(nodo);
        //agregarNivel(nodo);
        visit(ctx.whileStatement());

        //subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code statassign}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatassign(MPGrammarParser.StatassignContext ctx){
        //DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //nivelActual().add(nodo);
        //agregarNivel(nodo);
        visit(ctx.assignStatement());

        //subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code statfncall}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatfncall(MPGrammarParser.StatfncallContext ctx){
        //DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //nivelActual().add(nodo);
        //agregarNivel(nodo);
        visit(ctx.functionCallStatement());

        //subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code defstat}
     * labeled alternative in {@link MPGrammarParser#defStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitDefstat(MPGrammarParser.DefstatContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //Agrego los nodos terminales
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.DEF().getClass().getSimpleName());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.IDENTIFIER().getText());
        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(ctx.PIZQ().getText());
        nodo.add(t1);
        nodo.add(t2);
        nodo.add(t3);

        //Nuevo nivel del arbol
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita argList

        visit(ctx.argList());

        //Nodos terminales
        DefaultMutableTreeNode t4 = new DefaultMutableTreeNode(ctx.PDER().getText());
        DefaultMutableTreeNode t5 = new DefaultMutableTreeNode(ctx.DPUNTOS().getText());
        nodo.add(t4);
        nodo.add(t5);

        //Se visita sequence
        visit(ctx.sequence());

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code morearglist}
     * labeled alternative in {@link MPGrammarParser#argList}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMorearglist(MPGrammarParser.MorearglistContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode id = new DefaultMutableTreeNode(ctx.IDENTIFIER().getText());
        nodo.add(id);
        nivelActual().add(nodo);
        agregarNivel(nodo);

        //Se visita moreArgs
        visit(ctx.moreArgs());
        subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code epsarglist}
     * labeled alternative in {@link MPGrammarParser#argList}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitEpsarglist(MPGrammarParser.EpsarglistContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        //Nodo terminal

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code moreargsN}
     * labeled alternative in {@link MPGrammarParser#moreArgs}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMoreargsN(MPGrammarParser.MoreargsNContext ctx){

        //Se agregan todos los identificadores como nodos terminales
        for(TerminalNode n : ctx.IDENTIFIER()){
            DefaultMutableTreeNode t = new DefaultMutableTreeNode(n.getText());
            nivelActual().add(t);
        }

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code ifstat}
     * labeled alternative in {@link MPGrammarParser#ifStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitIfstat(MPGrammarParser.IfstatContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //Terminal if
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.IF().getText());
        nodo.add(t1);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita expression
        visit(ctx.expression());

        //Terminal dos puntos
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.DPUNTOS().getClass().getSimpleName());
        nodo.add(t2);
        //Se visita sequence
        visit(ctx.sequence(0));

        //Terminal else y dos puntos
        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(ctx.ELSE().getText());
        DefaultMutableTreeNode t4 = new DefaultMutableTreeNode(ctx.DPUNTOS().getClass().getSimpleName());

        //Se visita sequence de nuevo
        visit(ctx.sequence(1));

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code whilestat}
     * labeled alternative in {@link MPGrammarParser#whileStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitWhilestat(MPGrammarParser.WhilestatContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //Terminal while
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.WHILE().getText());
        nodo.add(t1);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita expression
        visit(ctx.expression());
        //Terminal dos puntos
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.DPUNTOS().getText());
        nodo.add(t2);
        //Se visita sequence
        visit(ctx.sequence());
        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code returnstat}
     * labeled alternative in {@link MPGrammarParser#returnStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitReturnstat(MPGrammarParser.ReturnstatContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        //Terminal return
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.RETURN().getText());
        nodo.add(t1);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita expression
        visit(ctx.expression());

        //Token newline
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.NEWLINE().getClass().getSimpleName());
        nodo.add(t2);
        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code printstat}
     * labeled alternative in {@link MPGrammarParser#printStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitPrintstat(MPGrammarParser.PrintstatContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.PRINT().getText());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.PIZQ().getText());
        nodo.add(t1);
        nodo.add(t2);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita expression
        visit(ctx.expression());
        //Terminales
        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(ctx.PDER().getText());
        DefaultMutableTreeNode t4 = new DefaultMutableTreeNode(ctx.NEWLINE().getClass().getSimpleName());
        nodo.add(t3);
        nodo.add(t4);

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code assignstat}
     * labeled alternative in {@link MPGrammarParser#assignStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitAssignstat(MPGrammarParser.AssignstatContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.IDENTIFIER().getText());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.ASSIGN().getText());
        nodo.add(t1);
        nodo.add(t2);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita expression
        visit(ctx.expression());

        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(ctx.NEWLINE().getClass().getSimpleName());
        nodo.add(t3);

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code fncallstat}
     * labeled alternative in {@link MPGrammarParser#functionCallStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFncallstat(MPGrammarParser.FncallstatContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita primitiveExpression
        visit(ctx.primitiveExpression());
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.PIZQ().getText());
        nodo.add(t1);
        //Se visita expressionList
        visit(ctx.expressionList());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.PDER().getText());
        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(ctx.NEWLINE().getClass().getSimpleName());
        nodo.add(t2);
        nodo.add(t3);
        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code seq}
     * labeled alternative in {@link MPGrammarParser#sequence}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitSeq(MPGrammarParser.SeqContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita moreStatement
        visit(ctx.moreStatement());
        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code morestats}
     * labeled alternative in {@link MPGrammarParser#moreStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMorestats(MPGrammarParser.MorestatsContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita el primer statement
        visit(ctx.statement(0));
        for(int i = 1; i <= ctx.statement().size() - 1; i++){
            visit(ctx.statement(i));
        }
        subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code exprsn}
     * labeled alternative in {@link MPGrammarParser#expression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitExprsn(MPGrammarParser.ExprsnContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code cmparison}
     * labeled alternative in {@link MPGrammarParser#comparison}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitCmparison(MPGrammarParser.CmparisonContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);

        for(int i = 0; i < ctx.additionExpression().size(); i++) {

            if (!ctx.IGUAL().isEmpty()) {
                DefaultMutableTreeNode ig = new DefaultMutableTreeNode(ctx.IGUAL(i).getText());
                nodo.add(ig);
            }
            if (!ctx.MENOR_QUE().isEmpty()) {
                DefaultMutableTreeNode mq = new DefaultMutableTreeNode(ctx.MENOR_QUE(i).getText());
                nodo.add(mq);
            }
            if (!ctx.MAYOR_QUE().isEmpty()) {
                DefaultMutableTreeNode maq = new DefaultMutableTreeNode(ctx.MAYOR_QUE(i).getText());
                nodo.add(maq);
            }
            if (!ctx.MAYOR_QUE_IG().isEmpty()) {
                DefaultMutableTreeNode mqi = new DefaultMutableTreeNode(ctx.MAYOR_QUE_IG(i).getText());
                nodo.add(mqi);
            }
            if (!ctx.MENOR_QUE_IG().isEmpty()) {
                DefaultMutableTreeNode meqi = new DefaultMutableTreeNode(ctx.MENOR_QUE_IG(i).getText());
                nodo.add(meqi);
            }
            //Se visita additionExpression
            visit(ctx.additionExpression(i));
            subirNivel();
        }

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code addexp}
     * labeled alternative in {@link MPGrammarParser#additionExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitAddexp(MPGrammarParser.AddexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        //Se visita multiplicationExpression
        visit(ctx.multiplicationExpression());
        //Se visita additionFactor
        visit(ctx.additionFactor());

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code addfact}
     * labeled alternative in {@link MPGrammarParser#additionFactor}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitAddfact(MPGrammarParser.AddfactContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        for (int i = 0; i < ctx.multiplicationExpression().size(); i++){
            if(!ctx.SUMA().isEmpty()){
                DefaultMutableTreeNode suma = new DefaultMutableTreeNode(ctx.SUMA(i).getText());
                nodo.add(suma);
            }
            else if(!ctx.RESTA().isEmpty()){
                DefaultMutableTreeNode resta = new DefaultMutableTreeNode(ctx.RESTA(i).getText());
                nodo.add(resta);
            }
            visit(ctx.multiplicationExpression(i));
        }

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code mulexp}
     * labeled alternative in {@link MPGrammarParser#multiplicationExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMulexp(MPGrammarParser.MulexpContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.elementExpression());
        visit(ctx.multiplicationFactor());

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code mulfact}
     * labeled alternative in {@link MPGrammarParser#multiplicationFactor}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMulfact(MPGrammarParser.MulfactContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);

        for(int i = 0; i < ctx.elementExpression().size(); i++){
            if(!ctx.MUL().isEmpty()){
                DefaultMutableTreeNode mul = new DefaultMutableTreeNode(ctx.MUL(i).getText());
                nodo.add(mul);
            }
            else if(!ctx.DIV().isEmpty()){
                DefaultMutableTreeNode div = new DefaultMutableTreeNode(ctx.DIV(i).getText());
                nodo.add(div);
            }
            visit(ctx.elementExpression(i));
        }

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code elmntexp}
     * labeled alternative in {@link MPGrammarParser#elementExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitElmntexp(MPGrammarParser.ElmntexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.primitiveExpression());
        visit(ctx.elementAccess());

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code elmntacess}
     * labeled alternative in {@link MPGrammarParser#elementAccess}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitElmntacess(MPGrammarParser.ElmntacessContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        for(int i = 0; i < ctx.expression().size(); i++){
            visit(ctx.expression(i));
        }
        subirNivel();
        return null;
    }


    /**
     * Visit a parse tree produced by the {@code fncallexp}
     * labeled alternative in {@link MPGrammarParser#functionCallExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    public Object visitFncallexp(MPGrammarParser.FncallexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.IDENTIFIER().getText());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.PIZQ().getSymbol());
        nodo.add(t1);
        nodo.add(t2);
        nivelActual().add(nodo);
        agregarNivel(nodo);

        visit(ctx.expressionList());

        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(ctx.PDER().getText());
        nodo.add(t3);

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code moreexplist}
     * labeled alternative in {@link MPGrammarParser#expressionList}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMoreexplist(MPGrammarParser.MoreexplistContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);

        visit(ctx.expression());
        visit(ctx.moreExpressions());

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code epsexplist}
     * labeled alternative in {@link MPGrammarParser#expressionList}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitEpsexplist(MPGrammarParser.EpsexplistContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code moreexps}
     * labeled alternative in {@link MPGrammarParser#moreExpressions}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMoreexps(MPGrammarParser.MoreexpsContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);

        for(int i = 0; i < ctx.expression().size(); i++){
            DefaultMutableTreeNode coma = new DefaultMutableTreeNode(ctx.COMA(i).getText());
            nodo.add(coma);
            visit(ctx.expression(i));
        }


        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code intexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitIntexp(MPGrammarParser.IntexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(ctx.INTEGER().getText());
        nodo.add(t);
        nivelActual().add(nodo);

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code strexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStrexp(MPGrammarParser.StrexpContext ctx){
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(ctx.STRING().getText());
        nodo.add(t);
        nivelActual().add(nodo);

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code idexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitIdexp(MPGrammarParser.IdexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(ctx.IDENTIFIER().getText());
        nodo.add(t);
        nivelActual().add(nodo);

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code chaexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitChaexp(MPGrammarParser.ChaexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName()) ;
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(ctx.CHAR().getText());
        nodo.add(t);
        nivelActual().add(nodo);

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code pizqexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitPizqexp(MPGrammarParser.PizqexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.PIZQ().getText());
        nodo.add(t1);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.expression());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.PDER().getText());
        nodo.add(t2);

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code primlistexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitPrimlistexp(MPGrammarParser.PrimlistexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.listExpression());

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code lenexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitLenexp(MPGrammarParser.LenexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.LEN().getText());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.PIZQ().getText());
        nodo.add(t1);
        nodo.add(t2);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.expression());
        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(ctx.PDER().getText());
        nodo.add(t3);

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code primfncallexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitPrimfncallexp(MPGrammarParser.PrimfncallexpContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.functionCallExpression());

        subirNivel();

        return null;
    }


    /**
     * Visit a parse tree produced by {@link MPGrammarParser#listExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitListExpression(MPGrammarParser.ListExpressionContext ctx){

        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(ctx.getClass().getSimpleName());
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(ctx.CIZQ().getText());
        nodo.add(t1);
        nivelActual().add(nodo);
        agregarNivel(nodo);
        visit(ctx.expressionList());
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(ctx.CDER().getText());
        nodo.add(t2);

        subirNivel();

        return null;
    }

    public DefaultMutableTreeNode getAstGUI(){
        return this.raiz;
    }

}
