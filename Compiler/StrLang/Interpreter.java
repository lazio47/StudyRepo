import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends StrLangBaseVisitor<String> {

   @Override public String visitProgram(StrLangParser.ProgramContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitStat(StrLangParser.StatContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitStatement(StrLangParser.StatementContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitTrimExpr(StrLangParser.TrimExprContext ctx) {
      String res = visit(ctx.expr());
      return res.trim();
   }

   @Override public String visitStringExpr(StrLangParser.StringExprContext ctx) {
      String res = ctx.STRING().getText();
      return res.substring(1, res.length() - 1);
   }

   @Override public String visitParenthesisExpr(StrLangParser.ParenthesisExprContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitIdExpr(StrLangParser.IdExprContext ctx) {
      String id = ctx.ID().getText();
      if(!simbolTable.containsKey(id)){
      		System.err.println("Error: variable not found!");
      		return null;
      }
      return simbolTable.get(id);
   }

   @Override public String visitSubstituitionExpr(StrLangParser.SubstituitionExprContext ctx) {
      String str1 = visit(ctx.expr(0));
      String str2 = visit(ctx.expr(1));
      String str3 = visit(ctx.expr(2));
      return str1.replace(str2, str3);
   }


   @Override public String visitAddSubExpr(StrLangParser.AddSubExprContext ctx) {
      String str1 = visit(ctx.expr(0));
      String str2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      
      if(op.equals("+")){
      		return str1+str2;
      }     
      return str1.replace(str2, "");
   }

   @Override public String visitInput(StrLangParser.InputContext ctx) {
   	  Scanner sc = new Scanner(System.in);
   	  
   	  String string = ctx.STRING().getText();
      string = string.substring(1, string.length() - 1);
	  System.out.print(string);
	  String res = sc.nextLine();
	  
	  //sc.close();
      return res;
   }

   @Override public String visitAssign(StrLangParser.AssignContext ctx) {
      String res = visit(ctx.expr());
      String id = ctx.ID().getText();
      simbolTable.put(id, res);
      return null;
   }

   @Override public String visitPrint(StrLangParser.PrintContext ctx) {
      String res = visit(ctx.expr());
      if(res !=null)
      System.out.println(res);
      
      return null;
   }
   
   public String newVar(){
   		varIndex++;
   		return "v" + varIndex;
   }
   
   int varIndex = 0;
   
   HashMap<String, String> simbolTable = new HashMap<>();
}
