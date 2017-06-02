package visitors;

import grammar.MPGrammarBaseVisitor;
import grammar.MPGrammarParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by anthony on 01/06/17.
 * Clase base para generar los demas visitor sin tener que escribir todos los visit a mano
 */
public abstract class BaseVisitorCR extends MPGrammarBaseVisitor {

    @Override
    public abstract Object visitProgramN(MPGrammarParser.ProgramNContext ctx);


    @Override
    public abstract Object visitStatdef(MPGrammarParser.StatdefContext ctx);


    @Override
    public abstract Object visitStatif(MPGrammarParser.StatifContext ctx);


    @Override
    public abstract Object visitStatret(MPGrammarParser.StatretContext ctx);


    @Override
    public abstract Object visitStatprint(MPGrammarParser.StatprintContext ctx);


    @Override
    public abstract Object visitStatwhile(MPGrammarParser.StatwhileContext ctx);


    @Override
    public abstract Object visitStatassign(MPGrammarParser.StatassignContext ctx);


    @Override
    public abstract Object visitStatfncall(MPGrammarParser.StatfncallContext ctx);


    @Override
    public abstract Object visitDefstat(MPGrammarParser.DefstatContext ctx);


    @Override
    public abstract Object visitMorearglist(MPGrammarParser.MorearglistContext ctx);


    @Override
    public abstract Object visitEpsarglist(MPGrammarParser.EpsarglistContext ctx);


    @Override
    public abstract Object visitMoreargsN(MPGrammarParser.MoreargsNContext ctx);


    @Override
    public abstract Object visitIfstat(MPGrammarParser.IfstatContext ctx);


    @Override
    public abstract Object visitWhilestat(MPGrammarParser.WhilestatContext ctx);


    @Override
    public abstract Object visitReturnstat(MPGrammarParser.ReturnstatContext ctx);


    @Override
    public abstract Object visitPrintstat(MPGrammarParser.PrintstatContext ctx);


    @Override
    public abstract Object visitAssignstat(MPGrammarParser.AssignstatContext ctx);


    @Override
    public abstract Object visitFncallstat(MPGrammarParser.FncallstatContext ctx);


    @Override
    public abstract Object visitSeq(MPGrammarParser.SeqContext ctx);


    @Override
    public abstract Object visitMorestats(MPGrammarParser.MorestatsContext ctx);


    @Override
    public abstract Object visitExprsn(MPGrammarParser.ExprsnContext ctx);


    @Override
    public abstract Object visitCmparison(MPGrammarParser.CmparisonContext ctx);


    @Override
    public abstract Object visitAddexp(MPGrammarParser.AddexpContext ctx);


    @Override
    public abstract Object visitAddfact(MPGrammarParser.AddfactContext ctx);


    @Override
    public abstract Object visitMulexp(MPGrammarParser.MulexpContext ctx);


    @Override
    public abstract Object visitMulfact(MPGrammarParser.MulfactContext ctx);


    @Override
    public abstract Object visitElmntexp(MPGrammarParser.ElmntexpContext ctx);


    @Override
    public abstract Object visitElmntacess(MPGrammarParser.ElmntacessContext ctx);


    public abstract Object visitFncallexp(MPGrammarParser.FncallexpContext ctx);


    @Override
    public abstract Object visitMoreexplist(MPGrammarParser.MoreexplistContext ctx);


    @Override
    public abstract Object visitEpsexplist(MPGrammarParser.EpsexplistContext ctx);


    @Override
    public abstract Object visitMoreexps(MPGrammarParser.MoreexpsContext ctx);


    @Override
    public abstract Object visitIntexp(MPGrammarParser.IntexpContext ctx);


    @Override
    public abstract Object visitStrexp(MPGrammarParser.StrexpContext ctx);


    @Override
    public abstract Object visitIdexp(MPGrammarParser.IdexpContext ctx);


    @Override
    public abstract Object visitChaexp(MPGrammarParser.ChaexpContext ctx);


    @Override
    public abstract Object visitPizqexp(MPGrammarParser.PizqexpContext ctx);


    @Override
    public abstract Object visitPrimlistexp(MPGrammarParser.PrimlistexpContext ctx);


    @Override
    public abstract Object visitLenexp(MPGrammarParser.LenexpContext ctx);


    @Override
    public abstract Object visitPrimfncallexp(MPGrammarParser.PrimfncallexpContext ctx);


    @Override
    public abstract Object visitListExpression(MPGrammarParser.ListExpressionContext ctx);


}
