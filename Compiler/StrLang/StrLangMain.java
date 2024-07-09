import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class StrLangMain {
   public static void main(String[] args) {
      try {
      	if(args.length > 0){
      		 File file = new File(args[0]);
      		 
      		 InputStream in = new FileInputStream(file);
		     // create a CharStream that reads from standard input:
		     CharStream input = CharStreams.fromStream(in);
		     // create a lexer that feeds off of input CharStream:
		     StrLangLexer lexer = new StrLangLexer(input);
		     // create a buffer of tokens pulled from the lexer:
		     CommonTokenStream tokens = new CommonTokenStream(lexer);
		     // create a parser that feeds off the tokens buffer:
		     StrLangParser parser = new StrLangParser(tokens);
		     // replace error listener:
		     //parser.removeErrorListeners(); // remove ConsoleErrorListener
		     //parser.addErrorListener(new ErrorHandlingListener());
		     // begin parsing at program rule:
		     ParseTree tree = parser.program();
		     if (parser.getNumberOfSyntaxErrors() == 0) {
		        Interpreter visitor = new Interpreter();
		        visitor.visit(tree);
		     }
		 }else{
		 	System.out.println("Passe o ficheiro nos argumentos");
		 }
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
