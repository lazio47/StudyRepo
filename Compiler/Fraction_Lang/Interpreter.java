@SuppressWarnings("CheckReturnValue")
public class Interpreter extends FracLangBaseVisitor<String> {

   @Override public String visitProgram(FracLangParser.ProgramContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitStat(FracLangParser.StatContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitStatement(FracLangParser.StatementContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitFracExpr(FracLangParser.FracExprContext ctx) {
      String res = ctx.var;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitPrentesesExpr(FracLangParser.PrentesesExprContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitMulDivExpr(FracLangParser.MulDivExprContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitIdExpr(FracLangParser.IdExprContext ctx) {
      String res = ctx.ID().getText();
      if(!symbolTable.containsKey(res)){
      	System.err.println("Erro semantico: "+res+" nao foi declarada!");
      }
      return res;
   }

   @Override public String visitReadExpr(FracLangParser.ReadExprContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitUnarioExpr(FracLangParser.UnarioExprContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitPlusMinusExpr(FracLangParser.PlusMinusExprContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitIntExpr(FracLangParser.IntExprContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitReduceExpr(FracLangParser.ReduceExprContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitAssign(FracLangParser.AssignContext ctx) {
   	  String id = visit(ctx.ID());
      String res = visit(ctx.expr());
      symbolTable.put(id, res);
      return null;
   }

   @Override public String visitDisplay(FracLangParser.DisplayContext ctx) {
      String res = visit(ctx.expr());
      System.out.println(res);
      return res;
   }

   @Override public String visitReduce(FracLangParser.ReduceContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitRead(FracLangParser.ReadContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitFrac(FracLangParser.FracContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }
   
   public String newVar(){
   		varIndex++;
   		return "v"+varIndex;
   }
   
   HashMap<String, Frac> symbolTable = new HashMap<>();
   
   public int varIndex = 0;
}
