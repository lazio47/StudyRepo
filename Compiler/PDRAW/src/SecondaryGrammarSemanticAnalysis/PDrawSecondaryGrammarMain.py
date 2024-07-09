import sys
from antlr4 import *
from PDrawSecondaryGrammarLexer import PDrawSecondaryGrammarLexer
from PDrawSecondaryGrammarParser import PDrawSecondaryGrammarParser
from SecondaryGrammarSemanticAnalysis import SecondaryGrammarSemanticAnalysis

def main(argv):
   visitor0 = SecondaryGrammarSemanticAnalysis()
   for line in sys.stdin:
      input_stream = InputStream(line)
      lexer = PDrawSecondaryGrammarLexer(input_stream)
      stream = CommonTokenStream(lexer)
      parser = PDrawSecondaryGrammarParser(stream)
      tree = parser.program()
      if parser.getNumberOfSyntaxErrors() == 0:
         visitor0.visit(tree)

if __name__ == '__main__':
      main(sys.argv)
