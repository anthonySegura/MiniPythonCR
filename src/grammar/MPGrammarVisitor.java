// Generated from C:/Users/anthony/IdeaProjects/MiniPython\MPGrammar.g4 by ANTLR 4.6
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MPGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MPGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code programN}
	 * labeled alternative in {@link MPGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramN(MPGrammarParser.ProgramNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statdef}
	 * labeled alternative in {@link MPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatdef(MPGrammarParser.StatdefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statif}
	 * labeled alternative in {@link MPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatif(MPGrammarParser.StatifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statret}
	 * labeled alternative in {@link MPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatret(MPGrammarParser.StatretContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statprint}
	 * labeled alternative in {@link MPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatprint(MPGrammarParser.StatprintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statwhile}
	 * labeled alternative in {@link MPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatwhile(MPGrammarParser.StatwhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statassign}
	 * labeled alternative in {@link MPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatassign(MPGrammarParser.StatassignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statfncall}
	 * labeled alternative in {@link MPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatfncall(MPGrammarParser.StatfncallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defstat}
	 * labeled alternative in {@link MPGrammarParser#defStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefstat(MPGrammarParser.DefstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code morearglist}
	 * labeled alternative in {@link MPGrammarParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMorearglist(MPGrammarParser.MorearglistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code epsarglist}
	 * labeled alternative in {@link MPGrammarParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEpsarglist(MPGrammarParser.EpsarglistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreargsN}
	 * labeled alternative in {@link MPGrammarParser#moreArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreargsN(MPGrammarParser.MoreargsNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifstat}
	 * labeled alternative in {@link MPGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstat(MPGrammarParser.IfstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whilestat}
	 * labeled alternative in {@link MPGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhilestat(MPGrammarParser.WhilestatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnstat}
	 * labeled alternative in {@link MPGrammarParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnstat(MPGrammarParser.ReturnstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printstat}
	 * labeled alternative in {@link MPGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintstat(MPGrammarParser.PrintstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignstat}
	 * labeled alternative in {@link MPGrammarParser#assignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignstat(MPGrammarParser.AssignstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fncallstat}
	 * labeled alternative in {@link MPGrammarParser#functionCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFncallstat(MPGrammarParser.FncallstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seq}
	 * labeled alternative in {@link MPGrammarParser#sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeq(MPGrammarParser.SeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code morestats}
	 * labeled alternative in {@link MPGrammarParser#moreStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMorestats(MPGrammarParser.MorestatsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprsn}
	 * labeled alternative in {@link MPGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprsn(MPGrammarParser.ExprsnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmparison}
	 * labeled alternative in {@link MPGrammarParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmparison(MPGrammarParser.CmparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addexp}
	 * labeled alternative in {@link MPGrammarParser#additionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddexp(MPGrammarParser.AddexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addfact}
	 * labeled alternative in {@link MPGrammarParser#additionFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddfact(MPGrammarParser.AddfactContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulexp}
	 * labeled alternative in {@link MPGrammarParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulexp(MPGrammarParser.MulexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulfact}
	 * labeled alternative in {@link MPGrammarParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulfact(MPGrammarParser.MulfactContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elmntexp}
	 * labeled alternative in {@link MPGrammarParser#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElmntexp(MPGrammarParser.ElmntexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elmntacess}
	 * labeled alternative in {@link MPGrammarParser#elementAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElmntacess(MPGrammarParser.ElmntacessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fncallexp}
	 * labeled alternative in {@link MPGrammarParser#functionCallExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFncallexp(MPGrammarParser.FncallexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreexplist}
	 * labeled alternative in {@link MPGrammarParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreexplist(MPGrammarParser.MoreexplistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code epsexplist}
	 * labeled alternative in {@link MPGrammarParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEpsexplist(MPGrammarParser.EpsexplistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreexps}
	 * labeled alternative in {@link MPGrammarParser#moreExpressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreexps(MPGrammarParser.MoreexpsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntexp(MPGrammarParser.IntexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code strexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrexp(MPGrammarParser.StrexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdexp(MPGrammarParser.IdexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code chaexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChaexp(MPGrammarParser.ChaexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pizqexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPizqexp(MPGrammarParser.PizqexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primlistexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimlistexp(MPGrammarParser.PrimlistexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lenexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLenexp(MPGrammarParser.LenexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primfncallexp}
	 * labeled alternative in {@link MPGrammarParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimfncallexp(MPGrammarParser.PrimfncallexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MPGrammarParser#listExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListExpression(MPGrammarParser.ListExpressionContext ctx);
}