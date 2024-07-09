import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import org.stringtemplate.v4.*;

import java.io.*;
import java.util.*;

public class CSVMain {
   public static void main(String[] args) {
      try {
         // create a CharStream that reads from standard input:
         CharStream input = CharStreams.fromStream(System.in);
         // create a lexer that feeds off of input CharStream:
         CSVLexer lexer = new CSVLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         CSVParser parser = new CSVParser(tokens);
         // replace error listener:
         //parser.removeErrorListeners(); // remove ConsoleErrorListener
         //parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at file rule:
         ParseTree tree = parser.file();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
		MyCSVListener listener = new MyCSVListener();
		ParseTreeWalker.DEFAULT.walk(listener, tree);

		List<List<String>> rows = listener.getRows();

		STGroup templates = new STGroupFile("templateHTML.stg");
        	ST htmlTemplate = templates.getInstanceOf("table");
        		
        	htmlTemplate.add("head", rows.get(0)); // Todas as linhas
         htmlTemplate.add("rows", rows.subList(1, rows.size())); // Todas as linhas
        	
		String html = htmlTemplate.render();
		System.out.println(html);
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
