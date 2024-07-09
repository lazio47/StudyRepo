import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
@SuppressWarnings("CheckReturnValue")

public class MyListener extends ShapesBaseListener {

   @Override public void enterPoint(ShapesParser.PointContext ctx) {
	int x = Integer.parseInt(ctx.x.getText());
	int y = Integer.parseInt(ctx.y.getText());
	System.out.println("enterPoint x=" + x + ", y=" + y);   
}

   @Override public void exitPoint(ShapesParser.PointContext ctx) {
	int x = Integer.parseInt(ctx.x.getText());
        int y = Integer.parseInt(ctx.y.getText());
        System.out.println("exitPoint x=" + x + ", y=" + y);   
}
}
