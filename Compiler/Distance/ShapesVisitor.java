// Generated from Shapes.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ShapesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ShapesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ShapesParser#distance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistance(ShapesParser.DistanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ShapesParser#point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPoint(ShapesParser.PointContext ctx);
}