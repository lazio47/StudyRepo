import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.stringtemplate.v4.*;
@SuppressWarnings("CheckReturnValue")
public class PDrawCompiler extends PDrawGrammarBaseVisitor<ST> {

   @Override public ST visitProgram(PDrawGrammarParser.ProgramContext ctx) {
      ST res = templates.getInstanceOf("module");
      ST prog = templates.getInstanceOf("program");

      for (PDrawGrammarParser.StatContext statCtx : ctx.stat()) {
         ST stat = visit(statCtx);
         if (stat != null) {
            prog.add("stats", stat.render());
         }
      }
      if (prog != null) {
         res.add("stat", prog.render());
      } else {
         System.out.println("Erro: ST nulo.");
      }
      canvasList.add("canvas");
      res.add("canvasList", canvasList);
      return res;
   }

   @Override public ST visitStat(PDrawGrammarParser.StatContext ctx) {
      if (ctx.getChildCount() > 0) {
         ST res = visit(ctx.getChild(0));
         if(ctx.pauseExpr() != null){
            res.add("stat", "pause("+ctx.pauseExpr().expr().var+")");
         }
         return res;
     } else {
         return null;
     }
   }

   @Override public ST visitPenDecl(PDrawGrammarParser.PenDeclContext ctx) {
      ST res = templates.getInstanceOf("penDecl");
      String id = ctx.ID().getText();
      String var = newVar();
      vars.put(id, var);
      res.add("penName", var);
      res.add("canvas", canvasAtual);
      penActual = var;

      List<ST> properties = new ArrayList<>();
      for (PDrawGrammarParser.PropertyContext propCtx : ctx.property()) {
         properties.add(visitProperty(propCtx));
      }
      res.add("properties", properties);
      return res;
   }

   @Override public ST visitExprPI(PDrawGrammarParser.ExprPIContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      asg.add("value", "math.pi");
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprInstrucToPen(PDrawGrammarParser.ExprInstrucToPenContext ctx) {
      return visit(ctx.instruc());
   }

   @Override public ST visitExprPointIncrement(PDrawGrammarParser.ExprPointIncrementContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      String var = vars.get(ctx.ID().getText());
      res.add("stat", visit(ctx.point()).render());

      String pnt = newVar();
      ST asgp = templates.getInstanceOf("pointAssign");
      asgp.add("var", pnt);
      if (ctx.op.getText().equals("+")){
         asgp.add("x", ctx.point().x.var);
         asgp.add("y", ctx.point().y.var);
      }else{
         asgp.add("x", "-"+ctx.point().x.var);
         asgp.add("y", "-"+ctx.point().y.var);
      }
      
      res.add("stat", asgp.render());

      ST asg = templates.getInstanceOf("assignment");
      ST bop = templates.getInstanceOf("method");
      bop.add("var", var);
      bop.add("method", "increase_position");
      bop.add("arg", pnt);

      asg.add("var", ctx.var);
      asg.add("value", bop.render());
  
      res.add("stat", asg.render()+"\n");
      return res;
   }

   @Override public ST visitExprBoolOperation(PDrawGrammarParser.ExprBoolOperationContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());

      ST bop = templates.getInstanceOf("binaryOperation");
      bop.add("var", ctx.var);
      bop.add("e1", ctx.e1.var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.e2.var);
      res.add("stat", bop.render()+"\n");
      return res;
   }

   @Override public ST visitExprNewPen(PDrawGrammarParser.ExprNewPenContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      if (ctx.ID() != null) {
         String penType = ctx.ID().getText();
         ST newp = templates.getInstanceOf("newPen");
         newp.add("penType", vars.get(penType));

         ST asg = templates.getInstanceOf("assignment");
         asg.add("var", ctx.var);
         asg.add("value", newp.render());

         res.add("stat", asg.render());
     } else {
         ST newp = templates.getInstanceOf("newPenDefault");
         ST asg = templates.getInstanceOf("assignment");
         asg.add("var", ctx.var);
         asg.add("value", newp.render());
         res.add("stat", asg.render());
     }
      return res;
   }

   @Override public ST visitExprString(PDrawGrammarParser.ExprStringContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      asg.add("value", ctx.STRING().getText());
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprPoint(PDrawGrammarParser.ExprPointContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.point()).render());
      ctx.var = newVar();
      ST pnt = templates.getInstanceOf("pointAssign");
      pnt.add("var", ctx.var);
      pnt.add("x", ctx.point().x.var);
      pnt.add("y", ctx.point().y.var);

      res.add("stat", pnt.render());
      return res;
   }

   @Override public ST visitExprParentheses(PDrawGrammarParser.ExprParenthesesContext ctx) {
      ST res = templates.getInstanceOf("stat");
      res.add("stat", visit(ctx.expr()).render());
      ctx.var = newVar();
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      asg.add("value", ctx.expr().var);
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprInt(PDrawGrammarParser.ExprIntContext ctx) {
      ST res = templates.getInstanceOf("stat");
      ctx.var = newVar();
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      asg.add("value", ctx.INT().getText());
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprBoolean(PDrawGrammarParser.ExprBooleanContext ctx) {
      ST res = templates.getInstanceOf("stat");
      ctx.var = newVar();
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      String str = ctx.BOOLEAN().getText();
      asg.add("value", str.substring(0,1).toUpperCase()+str.substring(1));
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprColor(PDrawGrammarParser.ExprColorContext ctx) {
      ST res = templates.getInstanceOf("assignment");
      ctx.var = newVar();
      res.add("var", ctx.var);
      res.add("value", "\""+ctx.COLOR().getText()+"\"");
      return res;
   }

   @Override public ST visitExprInput(PDrawGrammarParser.ExprInputContext ctx) {
      ST res = templates.getInstanceOf("assignment");
      ctx.var = newVar();
      res.add("var", ctx.var);
      res.add("value", visit(ctx.input()).render());
      return res;
   }

   @Override public ST visitExprNot(PDrawGrammarParser.ExprNotContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());

      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      asg.add("value", "not " + ctx.e1.var);
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprAndOr(PDrawGrammarParser.ExprAndOrContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());
      ST bop = templates.getInstanceOf("binaryOperation");
      bop.add("var", ctx.var);
      bop.add("e1", ctx.e1.var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.e2.var);
      res.add("stat", bop.render());
      return res;
   }

   @Override public ST visitExprMultDivMod(PDrawGrammarParser.ExprMultDivModContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());

      String operator = ctx.op.getText();
      ST bop = templates.getInstanceOf("binaryOperation");
      bop.add("var", ctx.var);
      bop.add("e1", ctx.e1.var);
      bop.add("e2", ctx.e2.var);

      if (operator.equals("\\")) {
         bop.add("op", "%");
      } else {
         bop.add("op", operator);
      }
      res.add("stat", bop.render());
      return res;
   }

   @Override public ST visitExprAddSub(PDrawGrammarParser.ExprAddSubContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());
      ST bop = templates.getInstanceOf("binaryOperation");
      bop.add("var", ctx.var);
      bop.add("e1", ctx.e1.var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.e2.var);
      res.add("stat", bop.render());
      return res;
   }

   @Override public ST visitTypeConversion(PDrawGrammarParser.TypeConversionContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.expr()).render());
      
      ST tcv = templates.getInstanceOf("typeConversion");
      tcv.add("var", ctx.var);
      if (ctx.dataType().getText().equals("real")){
         tcv.add("type", "float");
      }else{
         tcv.add("type", ctx.dataType().getText());
      }
      tcv.add("value", ctx.expr().var);
      res.add("stat", tcv.render());
      return res;
   }

   @Override public ST visitExprUnary(PDrawGrammarParser.ExprUnaryContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr()).render());
      ctx.var = newVar();
      
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      asg.add("value", ctx.op.getText() + ctx.expr().var);

      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprReal(PDrawGrammarParser.ExprRealContext ctx) {
      ST res = templates.getInstanceOf("stat");
      ctx.var = newVar();
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.var);
      asg.add("value", ctx.REAL().getText());
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitExprId(PDrawGrammarParser.ExprIdContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = vars.get(ctx.ID().getText());
      // vars.put(ctx.ID().getText(), ctx.var);
      return res;
   }

   @Override public ST visitProperty(PDrawGrammarParser.PropertyContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr()).render());
      ST prop = templates.getInstanceOf("property");
      prop.add("propName", ctx.propertyName().getText());
      prop.add("value", ctx.expr().var);
      prop.add("penName", penActual);

      res.add("stat", prop.render());
      return res;
   }

   @Override public ST visitCanvasDeclWithExpr(PDrawGrammarParser.CanvasDeclWithExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      String var = newVar();
      canvasAtual = var;
      vars.put(ctx.ID().getText(), var);
      canvasList.add(var);

      ST cnv = templates.getInstanceOf("canvasDecl");
      cnv.add("var", var);
      cnv.add("title", ctx.STRING().getText());
      cnv.add("width", ctx.expr(0).var);
      cnv.add("height", ctx.expr(1).var);
      res.add("stat", cnv.render());

      return res;
   }

   @Override public ST visitSelectCanvas(PDrawGrammarParser.SelectCanvasContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitSetBackground(PDrawGrammarParser.SetBackgroundContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitDeclaration(PDrawGrammarParser.DeclarationContext ctx) {
      ST res = templates.getInstanceOf("decls");
      if (ctx.ID() != null){
         for (int i = 0; i < ctx.ID().size(); i++) {
            vars.put(ctx.ID(i).getText(), newVar());
         }
      }
      if (ctx.assignment() != null) {
         for (int i = 0; i < ctx.assignment().size(); i++) {
               res.add("decls", visit(ctx.assignment(i)).render());
         }
      }

      return res;
   }

   @Override public ST visitAssignment(PDrawGrammarParser.AssignmentContext ctx) {
      ST res = templates.getInstanceOf("stats");
      visit(ctx.ID());
      res.add("stat", visit(ctx.expr()).render());
      String id = ctx.ID().getText();
      String var = null;
      if(!vars.containsKey(id)){
         var = newVar();
         vars.put(id, var);
      }else{
         var = vars.get(id);
      }
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", vars.get(id));
      asg.add("value", ctx.expr().var);

      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitSetProperty(PDrawGrammarParser.SetPropertyContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST meth = templates.getInstanceOf("method");
      meth.add("var", ctx.expr(0).var);
      meth.add("method", "set_"+ctx.propertyName().getText());
      meth.add("arg", ctx.expr(1).var);

      res.add("stat", meth.render());
      return res;
   }

   @Override public ST visitInput(PDrawGrammarParser.InputContext ctx) {
      ST res = templates.getInstanceOf("stat");
      res.add("stat", "input(" + ctx.STRING().getText() + ")");
      return res;
   }

   @Override public ST visitOutputExpr(PDrawGrammarParser.OutputExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr()).render());
      ST prt = templates.getInstanceOf("print");
      prt.add("obj", ctx.expr().var);
      res.add("stat", prt.render());
      return res;
   }
 
   @Override public ST visitInstructRecusive(PDrawGrammarParser.InstructRecusiveContext ctx) {
      ST res = templates.getInstanceOf("stats");

      res.add("stat", visit(ctx.instruc()).render());
      ST meth = templates.getInstanceOf("method");
      meth.add("var", penActual);
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", penActual);

      if (ctx.miniInstruc() != null) {
         res.add("stat", visit(ctx.miniInstruc()).render());
         meth.add("method", ctx.miniInstruc().Direction().getText());
         meth.add("arg", ctx.miniInstruc().expr().var);
      } else if (ctx.pauseExpr() != null) {
         res.add("stat", visit(ctx.pauseExpr()).render());
         meth.add("method", "pause");
         meth.add("arg", ctx.pauseExpr().expr().var); 
      }
      asg.add("value", meth.render());
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitInstructFirst(PDrawGrammarParser.InstructFirstContext ctx) {
      ST res = templates.getInstanceOf("stats");

      String id = ctx.ID().getText();
      penActual = vars.get(id);

      ST meth = templates.getInstanceOf("method");
      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", penActual);
      meth.add("var", vars.get(id));

      if (ctx.miniInstruc() != null) {
         res.add("stat", visit(ctx.miniInstruc()).render());
         meth.add("method", ctx.miniInstruc().Direction().getText());
         meth.add("arg", ctx.miniInstruc().expr().var);
      } else if (ctx.pauseExpr() != null) {
         res.add("stat", visit(ctx.miniInstruc()).render());
         meth.add("method", "pause");
         meth.add("arg", ctx.pauseExpr().expr().var);                                                     
      } else if (ctx.moves() != null) {
         meth.add("method", visit(ctx.moves()).render());
         meth.add("arg", "");
      }
      asg.add("value", meth.render());
      res.add("stat", asg.render());
      return res;
   }

   @Override public ST visitMiniInstruc(PDrawGrammarParser.MiniInstrucContext ctx) {
      return visit(ctx.expr());
   }

   @Override public ST visitConditional(PDrawGrammarParser.ConditionalContext ctx) {
      ST conditional = templates.getInstanceOf("conditional");
    
      conditional.add("condition", visit(ctx.expr(0)).render());
      conditional.add("ifBlock", visit(ctx.statementBlock(0)).render());
      
      for (int i = 1; i < ctx.expr().size(); i++) {
         ST elifBlock = templates.getInstanceOf("elifBlock");
         elifBlock.add("condition", visit(ctx.expr(i)).render());
         elifBlock.add("block", visit(ctx.statementBlock(i)).render());
         conditional.add("elifBlocks", elifBlock.render());
      }
   
      if (ctx.expr().size() < ctx.statementBlock().size()) {
            conditional.add("hasElse", true);
            conditional.add("elseBlock", visit(ctx.statementBlock(ctx.statementBlock().size() - 1)).render());
      } else {
            conditional.add("hasElse", false);
      }

      return conditional;
   }

   @Override public ST visitLoopFor(PDrawGrammarParser.LoopForContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", "## ciclo for\n");
      ST init = visit(ctx.declaration() != null ? ctx.declaration() : ctx.assignment(0));
      res.add("stat", init.render());

      String controlVar = ctx.declaration() != null ? 
                           ctx.declaration().assignment(0).var
                        : ctx.assignment(0).var;

      ST whileLoop = templates.getInstanceOf("while");
      ST condition = visit(ctx.expr());
      res.add("stat", condition.render());
      whileLoop.add("condition", ctx.expr().var);

      ST body = templates.getInstanceOf("stats");
      ST statementBlock = visit(ctx.statementBlock());
      body.add("stat", statementBlock.render());

      int lastAssignmentIndex = ctx.assignment().size() - 1;
      ST increment = visit(ctx.assignment(lastAssignmentIndex));
      body.add("stat", increment.render());

      ST asg = templates.getInstanceOf("assignment");
      asg.add("var", ctx.expr().var);
      asg.add("value", condition.render());

      body.add("stat", asg.render());

      whileLoop.add("stats", body.render());
      res.add("stat", whileLoop.render());

      return res;
   }

   @Override public ST visitLoopWhile(PDrawGrammarParser.LoopWhileContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitLoopUntil(PDrawGrammarParser.LoopUntilContext ctx) {
      ST res = templates.getInstanceOf("stats");
    
      ST whileLoop = templates.getInstanceOf("while");
      ST condition = visit(ctx.expr());
      String varControl = ctx.expr().var;
      String negatedCondition = "not (" + ctx.expr().var + ")";
      whileLoop.add("condition", negatedCondition);
      
      ST body = templates.getInstanceOf("stats");
      ST statementBlock = visit(ctx.statementBlock());
      body.add("stat", statementBlock.render());
      
      // Adiciona o corpo ao while
      whileLoop.add("stats", body.render());
      
      // Adiciona o while ao resultado final
      res.add("stat", whileLoop.render());
      
      return res;
   }

   @Override public ST visitPauseExpr(PDrawGrammarParser.PauseExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr()).render());
      return res;
   }

   @Override public ST visitExecute(PDrawGrammarParser.ExecuteContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr()).render());

      ST exe = templates.getInstanceOf("execute");
      String filename = ctx.STRING().getText();
      exe.add("psec", ctx.expr().var);
      exe.add("filename", filename);
      res.add("stat", exe.render());
      return res;
   }

   @Override public ST visitPoint(PDrawGrammarParser.PointContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.x).render());
      res.add("stat", visit(ctx.y).render());
      return res;
   }

   @Override public ST visitStatementBlock(PDrawGrammarParser.StatementBlockContext ctx) {
      ST block = templates.getInstanceOf("block");
      for (PDrawGrammarParser.StatContext statCtx : ctx.stat()) {
         block.add("stats", visit(statCtx).render());
      }
      return block;
   }

   @Override public ST visitDataType(PDrawGrammarParser.DataTypeContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitMoves(PDrawGrammarParser.MovesContext ctx) {
      ST res = templates.getInstanceOf("stat");
      String move = ctx.getText();
      res.add("stat", move);
      return res;
   }

   private String newVar() {
      numVars++;
      return "v" + numVars;
  }

  private HashMap<String, String> vars = new HashMap<>();
  private String canvasAtual = "canvas";
  private String penActual = null;
  private ArrayList<String> canvasList = new ArrayList<>();
   private int numVars = 0;
   private STGroup templates = new STGroupFile("python.stg");
}
