// Generated from Shapes.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShapesParser}.
 */
public interface ShapesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShapesParser#distance}.
	 * @param ctx the parse tree
	 */
	void enterDistance(ShapesParser.DistanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShapesParser#distance}.
	 * @param ctx the parse tree
	 */
	void exitDistance(ShapesParser.DistanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShapesParser#point}.
	 * @param ctx the parse tree
	 */
	void enterPoint(ShapesParser.PointContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShapesParser#point}.
	 * @param ctx the parse tree
	 */
	void exitPoint(ShapesParser.PointContext ctx);
}