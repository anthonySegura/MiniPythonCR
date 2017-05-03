package visitors;

import SymbolTable.Scope;
import SymbolTable.Table;
import grammar.MPGrammarBaseVisitor;
import grammar.MPGrammarParser;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by anthony on 22/04/17.
 * Realiza el analisis semantico usando el patron de diseño Visitor
 */
public class SemanticVisitor extends MPGrammarBaseVisitor {

    private Table tablaSimbolos = new Table();
    //Indica si el proceso de analisis semantico se realizo con exito o no
    private Boolean Status = true;

    @Override
    public Object visitProgramN(MPGrammarParser.ProgramNContext ctx) {

        visit(ctx.statement(0));
        for(int i = 1; i <= ctx.statement().size() - 1; i++){
            visit(ctx.statement(i));
        }

        tablaSimbolos.scopeActual().imprimirScope();

        return null;
    }

    @Override
    public Object visitStatdef(MPGrammarParser.StatdefContext ctx){

        return visit(ctx.defStatement());
    }


    /**
     * Visit a parse tree produced by the {@code statif}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatif(MPGrammarParser.StatifContext ctx){

        return visit(ctx.ifStatement());
    }


    /**
     * Visit a parse tree produced by the {@code statret}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatret(MPGrammarParser.StatretContext ctx){

        return visit(ctx.returnStatement());
    }


    /**
     * Visit a parse tree produced by the {@code statprint}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatprint(MPGrammarParser.StatprintContext ctx){

        return visit(ctx.printStatement());
    }


    /**
     * Visit a parse tree produced by the {@code statwhile}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatwhile(MPGrammarParser.StatwhileContext ctx){

        return visit(ctx.whileStatement());
    }


    /**
     * Visit a parse tree produced by the {@code statassign}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatassign(MPGrammarParser.StatassignContext ctx){

        return visit(ctx.assignStatement());
    }


    /**
     * Visit a parse tree produced by the {@code statfncall}
     * labeled alternative in {@link MPGrammarParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStatfncall(MPGrammarParser.StatfncallContext ctx){

        return visit(ctx.functionCallStatement());
    }


    /**
     * Visit a parse tree produced by the {@code defstat}
     * labeled alternative in {@link MPGrammarParser#defStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitDefstat(MPGrammarParser.DefstatContext ctx){

        tablaSimbolos.abrirScope("Def_Scope_" + ctx.IDENTIFIER().getText());

        Object [] params = (Object[]) visit(ctx.argList());
        Object ret = visit(ctx.sequence());
        int tipoRetorno = (ret == null) ? Table.NULL : (int)ret;

        tablaSimbolos.scopeActual().imprimirScope();

        tablaSimbolos.cerrarScope();

        //Se agrega la funcion al scope actual
        tablaSimbolos.scopeActual().insertarFuncion(ctx.IDENTIFIER().getSymbol(), ctx, params, tipoRetorno);

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

        Scope scopeActual = tablaSimbolos.scopeActual();
        //Se busca el identificador en la tabla
        Scope.Identificador identificador = tablaSimbolos.buscar(ctx.IDENTIFIER().getText());
        if(identificador != null){
            String nombre = identificador.getNombre();
            int tipo = identificador.getTipo();
            ParserRuleContext cntx = identificador.getDecl();
            scopeActual.insertar(new CommonToken(tipo, nombre), ctx);
        }
        else {
            System.err.println("Error no se encuentra los tipos de los parametros");
            return null;
        }

        Object [] params = (Object[]) visit(ctx.moreArgs());
        params[0] = ctx.IDENTIFIER().getText();

        return params;
    }


    /**
     * Visit a parse tree produced by the {@code epsarglist}
     * labeled alternative in {@link MPGrammarParser#argList}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitEpsarglist(MPGrammarParser.EpsarglistContext ctx){

        Object [] params = new Object[0];

        return params;
    }


    /**
     * Visit a parse tree produced by the {@code moreargsN}
     * labeled alternative in {@link MPGrammarParser#moreArgs}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMoreargsN(MPGrammarParser.MoreargsNContext ctx){
        Scope scopeActual = tablaSimbolos.scopeActual();
        Object [] params = new Object[ctx.IDENTIFIER().size() + 1];
        int i = 1;
        //Se agregan los argumentos al scope de la funcion
        for(TerminalNode node : ctx.IDENTIFIER()){
            Scope.Identificador identificador = tablaSimbolos.buscar(node.getText());
            if(identificador != null){
                String nombre = identificador.getNombre();
                int tipo = identificador.getTipo();
                ParserRuleContext cntx = identificador.getDecl();
                scopeActual.insertar(new CommonToken(tipo, nombre), ctx);
            }
            else {
                System.err.println("Error no se encuentra los tipos de los parametros");
                return null;
            }
            params[i] = node.getText();
            i++;
        }

        return params;
    }


    /**
     * Visit a parse tree produced by the {@code ifstat}
     * labeled alternative in {@link MPGrammarParser#ifStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitIfstat(MPGrammarParser.IfstatContext ctx){

        tablaSimbolos.abrirScope("If_Scope");
        //Se comprueba si la condición es correcta
        Object cmprsn = visit(ctx.expression());
        if(cmprsn == null){
            System.err.println("Condición invalida en IF ");
        }
        visit(ctx.sequence(0));
        visit(ctx.sequence(1));

        tablaSimbolos.scopeActual().imprimirScope();

        tablaSimbolos.cerrarScope();

        return null;
    }


    /**
     * Comprueba si las variables usadas estan declaradas y si se pueden comparar
     * Visit a parse tree produced by the {@code whilestat}
     * labeled alternative in {@link MPGrammarParser#whileStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitWhilestat(MPGrammarParser.WhilestatContext ctx){

        tablaSimbolos.abrirScope("While_Scope");

        visit(ctx.expression());
        visit(ctx.sequence());

        tablaSimbolos.scopeActual().imprimirScope();

        tablaSimbolos.cerrarScope();

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

        return visit(ctx.expression());
    }


    /**
     * Visit a parse tree produced by the {@code printstat}
     * labeled alternative in {@link MPGrammarParser#printStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitPrintstat(MPGrammarParser.PrintstatContext ctx){

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

        //ID = expression

        Object tipo = visit(ctx.expression());
        if(tipo != null) {

            String id = ctx.IDENTIFIER().getText();
            //Se busca el id en la tabla de simbolos
            Scope.Identificador identificador = tablaSimbolos.scopeActual().buscar(id);
            if (identificador == null) {
                tablaSimbolos.scopeActual().insertar(new CommonToken((int)tipo, id), ctx);
            }
            //Se comprueba si los tipos coinciden
            else {
                if (identificador.getTipo() != (int)tipo) {
                    System.err.println("Error tipos incompatibles en línea " + ctx.IDENTIFIER().getSymbol().getLine());
                }
            }
        }

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

        visit(ctx.primitiveExpression());

        visit(ctx.expressionList());

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

        return visit(ctx.moreStatement());
    }


    /**
     * Visit a parse tree produced by the {@code morestats}
     * labeled alternative in {@link MPGrammarParser#moreStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMorestats(MPGrammarParser.MorestatsContext ctx){

        Object tmp = visit(ctx.statement(0));
        for(int i = 1; i <= ctx.statement().size() - 1; i++){
            tmp = visit(ctx.statement(i));
        }
        //return visit(ctx.statement(ctx.statement().size() - 1));
        return tmp;
    }


    /**
     * Visit a parse tree produced by the {@code exprsn}
     * labeled alternative in {@link MPGrammarParser#expression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitExprsn(MPGrammarParser.ExprsnContext ctx){

        Object result = null;

        Object exp = visit(ctx.additionExpression());
        Object cmprsn = visit(ctx.comparison());

        //Si existe una comparación
        if(cmprsn != null){
            //Se revisa si los tipos son comparables
            //Solo se pueden comparar int o char
            if((int)cmprsn == Table.ERROR){
                return null;
            }

            if((int)cmprsn != MPGrammarParser.INTEGER && (int)cmprsn != MPGrammarParser.CHAR && (int)exp != MPGrammarParser.INTEGER && (int)exp != MPGrammarParser.CHAR){
                System.err.println("Error solo se pueden comparar INTEGER y CHAR");
            }
            else if((int)cmprsn != (int)exp){
                System.err.println("Error tipos incompatibles");
            }

            //Todo esta bien
            else {
                result = Table.BOOL;
            }
        }
        else {
            result = exp;
        }

        return result;
    }


    /**
     * Chequea si la instruccion es valida y retorna el tipo de los elementos comparados
     * Visit a parse tree produced by the {@code cmparison}
     * labeled alternative in {@link MPGrammarParser#comparison}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitCmparison(MPGrammarParser.CmparisonContext ctx){

        //Se comprueba el tipo de los elementos comparados, si no son iguales no es valido

        Object tipo = null;
        if(ctx.additionExpression().size() == 1){
            tipo = visit(ctx.additionExpression(0));
            if(tipo == null) tipo = Table.ERROR;
        }
        else if(ctx.additionExpression().size() > 1){
            int tipoAnt = (int)visit(ctx.additionExpression(0));
            for(int i = 0; i < ctx.additionExpression().size(); i++){
                if(tipoAnt != (int)visit(ctx.additionExpression(i))){
                    System.err.println("Error tipos compatibles en comparación");
                    tipo = Table.ERROR;
                    break;
                }
            }
        }
        return tipo;
    }


    /**
     * Visit a parse tree produced by the {@code addexp}
     * labeled alternative in {@link MPGrammarParser#additionExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitAddexp(MPGrammarParser.AddexpContext ctx){

        Object result = null;
        Object mulexp = visit(ctx.multiplicationExpression());
        Object addFact = visit(ctx.additionFactor());

        if(addFact != null){
            //Error en la expresion
            if((int)addFact == Table.ERROR){
                return null;
            }
            if((int)mulexp != (int)addFact){
                System.err.println("Error elementos incompatibles " +
                        " " + Table._SYMBOLIC_NAMES[(int)mulexp] + " [+ , -] " + Table._SYMBOLIC_NAMES[(int)addFact] +
                        " en " + "'" + ctx.getText() + "'");
            }
            else if((int)mulexp != MPGrammarParser.INTEGER && (int) mulexp != MPGrammarParser.STRING && (int)addFact != MPGrammarParser.INTEGER && (int) addFact != MPGrammarParser.STRING){
                System.err.println("Error tipos de datos invalidos para la suma");
            }
            else {
                result = mulexp;
            }
        }
        else {
            result = mulexp;
        }

        return result;
    }


    /**
     * Visit a parse tree produced by the {@code addfact}
     * labeled alternative in {@link MPGrammarParser#additionFactor}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitAddfact(MPGrammarParser.AddfactContext ctx){

        Object tipo = null;

        if(ctx.multiplicationExpression().size() == 1){
            tipo = visit(ctx.multiplicationExpression(0));
        }
        else if(ctx.multiplicationExpression().size() > 1){
            int tipoAnt = (int)visit(ctx.multiplicationExpression(0));
            for(int i = 0; i < ctx.multiplicationExpression().size(); i++){
                if(tipoAnt != (int)visit(ctx.multiplicationExpression(i))){
                    System.err.println("Error tipos incompatibles");
                    tipo = Table.ERROR;
                    break;
                }
            }
        }

        return tipo;
    }


    /**
     * Realiza el chequeo de tipos y retorna el tipo de la expresion
     * Visit a parse tree produced by the {@code mulexp}
     * labeled alternative in {@link MPGrammarParser#multiplicationExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMulexp(MPGrammarParser.MulexpContext ctx){

        Object result = null;
        Token elmnt1 = (Token) visit(ctx.elementExpression());
        Object elmnt2 = visit(ctx.multiplicationFactor());

        if(elmnt2 != null){
            //Error en la expresion
            if((int)elmnt2 == Table.ERROR){
                return null;
            }
            if(elmnt1.getType() != (int)elmnt2 ){
                System.err.println("Error elementos incompatibles en la multiplicacion en línea " + elmnt1.getLine() +
                        " " + Table._SYMBOLIC_NAMES[elmnt1.getType()] + " [* , /] " + Table._SYMBOLIC_NAMES[(int)elmnt2] +
                        " en " + "'" + ctx.getText() + "'");
            }
            else if(elmnt1.getType() != MPGrammarParser.INTEGER && (int)elmnt2 != MPGrammarParser.INTEGER){
                System.err.println("Error tipos de datos invalidos para la multiplicación en línea " + elmnt1.getLine());
            }
            else {
                result = elmnt1.getType();
            }
        }
        else{
            result = elmnt1.getType();
        }

        return result;
    }


    /**
     * Visit a parse tree produced by the {@code mulfact}
     * labeled alternative in {@link MPGrammarParser#multiplicationFactor}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMulfact(MPGrammarParser.MulfactContext ctx){

        Object tipo = null;

        if(ctx.elementExpression().size() == 1){
            tipo = ((Token)visit(ctx.elementExpression(0))).getType();
        }

        else if(ctx.elementExpression().size() > 1){
            int tipoAnt = ((Token)visit(ctx.elementExpression(0))).getType();
            for(int i = 0; i < ctx.elementExpression().size(); i++) {
                if(tipoAnt != ((Token)visit(ctx.elementExpression(i))).getType()){
                    System.err.println("Error tipos incompatibles");
                    tipo = Table.ERROR;
                    break;
                }
            }
        }

        return tipo;
    }


    /**
     * Visit a parse tree produced by the {@code elmntexp}
     * labeled alternative in {@link MPGrammarParser#elementExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitElmntexp(MPGrammarParser.ElmntexpContext ctx){

        Object prmt = visit(ctx.primitiveExpression());
        visit(ctx.elementAccess());

        return prmt;
    }


    /**
     * Visit a parse tree produced by the {@code elmntacess}
     * labeled alternative in {@link MPGrammarParser#elementAccess}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitElmntacess(MPGrammarParser.ElmntacessContext ctx){

        for (int i = 0; i < ctx.expression().size(); i++){
            visit(ctx.expression(i));
        }

        return null;
    }


    /**
     * Visit a parse tree produced by the {@code fncallexp}
     * labeled alternative in {@link MPGrammarParser#functionCallExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    public Object visitFncallexp(MPGrammarParser.FncallexpContext ctx){

        visit(ctx.expressionList());

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

        visit(ctx.expression());
        visit(ctx.moreExpressions());

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

        for(int i = 0; i < ctx.expression().size(); i++){
            visit(ctx.expression(i));
        }

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

        Token token = new CommonToken(MPGrammarParser.INTEGER, ctx.INTEGER().getText());

        return token;
    }


    /**
     * Visit a parse tree produced by the {@code strexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStrexp(MPGrammarParser.StrexpContext ctx){

        Token token = new CommonToken(MPGrammarParser.STRING, ctx.STRING().getText());

        return token;
    }


    /**
     * Visit a parse tree produced by the {@code idexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitIdexp(MPGrammarParser.IdexpContext ctx){

        Token token = null;
        Scope.Identificador id = tablaSimbolos.buscar(ctx.IDENTIFIER().getText());
        if(id == null){
            System.err.println("Error " + ctx.IDENTIFIER().getText() + " no esta declarado en este scope");
        }
        else {
            token = new CommonToken(id.getTipo(), ctx.IDENTIFIER().getText());
        }

        return token;
    }


    /**
     * Visit a parse tree produced by the {@code chaexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitChaexp(MPGrammarParser.ChaexpContext ctx){
        Token token = new CommonToken(MPGrammarParser.CHAR, ctx.CHAR().getText());

        return token;
    }


    /**
     * Visit a parse tree produced by the {@code pizqexp}
     * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitPizqexp(MPGrammarParser.PizqexpContext ctx){

        visit(ctx.expression());

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

        visit(ctx.listExpression());

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

        visit(ctx.expression());

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

        visit(ctx.functionCallExpression());

        return null;
    }


    /**
     * Visit a parse tree produced by {@link MPGrammarParser#listExpression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitListExpression(MPGrammarParser.ListExpressionContext ctx){

        visit(ctx.expressionList());

        return null;
    }

}
