import math
from antlr4 import *
from PDrawSecondaryGrammarParser import PDrawSecondaryGrammarParser
from PDrawSecondaryGrammarVisitor import PDrawSecondaryGrammarVisitor
from graphics import *

class Interpreter(PDrawSecondaryGrammarVisitor):

    pen = None
    vars = {}
    canvas = {}

    def visitProgram(self, ctx:PDrawSecondaryGrammarParser.ProgramContext):
        return self.visitChildren(ctx)

    def visitStat(self, ctx:PDrawSecondaryGrammarParser.StatContext):
        return self.visitChildren(ctx)

    def visitExprPI(self, ctx:PDrawSecondaryGrammarParser.ExprPIContext):
        return math.pi

    def visitExprBoolOperation(self, ctx:PDrawSecondaryGrammarParser.ExprBoolOperationContext):
        left = self.visit(ctx.expr(0))
        op = ctx.getChild(1).getText()
        right = self.visit(ctx.expr(1))
        boolean_operators = {
            '==': lambda x, y: x == y,
            '!=': lambda x, y: x != y,
            '<': lambda x, y: x < y,
            '>': lambda x, y: x > y,
            '<=': lambda x, y: x <= y,
            '>=': lambda x, y: x >= y
        }
        if op in boolean_operators:
            return boolean_operators[op](left, right)
        else:
            print(f"Operador booleano inválido: {op}") 
            return None

    def visitExprConcat(self, ctx:PDrawSecondaryGrammarParser.ExprConcatContext):
        left_expr = self.visit(ctx.expr(0))
        right_expr = self.visit(ctx.expr(1))
        return str(left_expr) + str(right_expr)

    def visitExprString(self, ctx:PDrawSecondaryGrammarParser.ExprStringContext):
        return ctx.getText()[1:-1]

    def visitExprPoint(self, ctx:PDrawSecondaryGrammarParser.ExprPointContext):
        x = self.visit(ctx.expr(0))
        y = self.visit(ctx.expr(1))
        return (x, y)

    def visitExprParentheses(self, ctx:PDrawSecondaryGrammarParser.ExprParenthesesContext):
        return self.visit(ctx.expr())

    def visitExprInt(self, ctx:PDrawSecondaryGrammarParser.ExprIntContext):
        return int(ctx.getChild(0).getText())

    def visitExprBoolean(self, ctx:PDrawSecondaryGrammarParser.ExprBooleanContext):
        return ctx.getText() == 'true'

    def visitExprColor(self, ctx:PDrawSecondaryGrammarParser.ExprColorContext):
        return ctx.getChild(0).getText()

    def visitExprInput(self, ctx:PDrawSecondaryGrammarParser.ExprInputContext):
        prompt = ctx.STRING().getText()[1:-1]
        return input(prompt)

    def visitExprNot(self, ctx:PDrawSecondaryGrammarParser.ExprNotContext):
        return not self.visit(ctx.expr())

    def visitExprAndOr(self, ctx:PDrawSecondaryGrammarParser.ExprAndOrContext):
        left = self.visit(ctx.expr(0))
        op = ctx.getChild(1).getText()
        right = self.visit(ctx.expr(1))
        if op == 'and':
            return left and right
        elif op == 'or':
            return left or right

    def visitExprMultDivMod(self, ctx:PDrawSecondaryGrammarParser.ExprMultDivModContext):
        left = self.visit(ctx.expr(0))
        op = ctx.getChild(1).getText()
        right = self.visit(ctx.expr(1))
        if op == '*':
            return left * right
        elif op == '/':
            return left / right
        elif op == '//':
            return left // right
        elif op == '\\\\':
            return left % right

    def visitExprAddSub(self, ctx:PDrawSecondaryGrammarParser.ExprAddSubContext):
        left = self.visit(ctx.expr(0))
        op = ctx.getChild(1).getText()
        right = self.visit(ctx.expr(1))
        if op == '+':
            return left + right
        elif op == '-':
            return left - right

    def visitTypeConversion(self, ctx:PDrawSecondaryGrammarParser.TypeConversionContext):
        expr = self.visit(ctx.expr())
        data_type = ctx.dataType().getText()
        if data_type == 'string':
            return str(expr)
        elif data_type == 'real':
            return float(expr)
        elif data_type == 'int':
            return int(expr)
        elif data_type == 'bool':
            return bool(expr)
        elif data_type == 'color':
            return str(expr)
        elif data_type == 'position':
            return expr

    def visitExprUnary(self, ctx:PDrawSecondaryGrammarParser.ExprUnaryContext):
        op = ctx.getChild(0).getText()
        expr = self.visit(ctx.expr())
        if op == '+':
            return expr
        elif op == '-':
            return -expr

    def visitExprReal(self, ctx:PDrawSecondaryGrammarParser.ExprRealContext):
        return float(ctx.getChild(0).getText())

    def visitExprId(self, ctx:PDrawSecondaryGrammarParser.ExprIdContext):
        return ctx.getChild(0).getText()

    def visitProperty(self, ctx:PDrawSecondaryGrammarParser.PropertyContext):
        return (ctx.propertyName().getText(), self.visit(ctx.expr()))

    def visitCanvasDeclWithExpr(self, ctx:PDrawSecondaryGrammarParser.CanvasDeclWithExprContext):
        id = ctx.ID().getText()
        title = ctx.STRING().getText()[1:-1]
        width = self.visit(ctx.expr(0))
        height = self.visit(ctx.expr(1))
        self.canvas[id] = GraphWin(title, width, height)
        return None

    def visitSelectCanvas(self, ctx:PDrawSecondaryGrammarParser.SelectCanvasContext):
        id = ctx.ID().getText()
        self.pen.set_canvas(self.canvas[id])
        return None

    def visitSetBackground(self, ctx:PDrawSecondaryGrammarParser.SetBackgroundContext):
        color = self.visit(ctx.expr())
        self.pen.set_background(color)
        return None

    def visitDeclaration(self, ctx:PDrawSecondaryGrammarParser.DeclarationContext):
        dataType = ctx.dataType().getText()
        for i in range(ctx.getChildCount()):
            child = ctx.getChild(i)
            if isinstance(child, PDrawSecondaryGrammarParser.AssignmentContext):
                assignment = self.visit(child)
                id = assignment[0]
                value = assignment[1]
                self.vars[id] = value
        return None

    def visitAssignment(self, ctx:PDrawSecondaryGrammarParser.AssignmentContext):
        id = ctx.ID().getText()
        value = self.visit(ctx.expr())
        return (id, value)



    def visitInput(self, ctx:PDrawSecondaryGrammarParser.InputContext):
        prompt = ctx.STRING().getText()[1:-1]
        return input(prompt)

    def visitOutputExpr(self, ctx:PDrawSecondaryGrammarParser.OutputExprContext):
        expr = self.visit(ctx.expr())
        print(expr)
        return None

    def visitInstructRecusive(self, ctx:PDrawSecondaryGrammarParser.InstructRecusiveContext):
        return self.visitChildren(ctx)

    def visitInstructFirst(self, ctx:PDrawSecondaryGrammarParser.InstructFirstContext):
        return self.visitChildren(ctx)

    def visitPositionInstruction(self, ctx:PDrawSecondaryGrammarParser.PositionInstructionContext):
        self.pen.set_position(Point(int(ctx.expr(0).getText()), int(ctx.expr(1).getText())))
        return None

    def visitMiniInstruc(self, ctx:PDrawSecondaryGrammarParser.MiniInstrucContext):
        if ctx.Direction().getText() == "right":
            angle = float(self.visit(ctx.expr()))
            self.pen.right(angle)
        elif ctx.Direction().getText() == "left":
            angle = float(self.visit(ctx.expr()))
            self.pen.left(angle)
        elif ctx.Direction().getText() == "backward":
            distance = float(self.visit(ctx.expr()))
            self.pen.backward(distance)
        elif ctx.Direction().getText() == "forward":
            distance = float(self.visit(ctx.expr()))
            self.pen.forward(distance)
        return None

    def visitConditional(self, ctx:PDrawSecondaryGrammarParser.ConditionalContext):
        if self.visit(ctx.expr(0)):
            self.visit(ctx.statementBlock(0))
            return None
        for i in range(1, ctx.statementBlock().__len__()):
            if self.visit(ctx.expr(i)):
                self.visit(ctx.statementBlock(i))
                return None
        if ctx.getChild(ctx.getChildCount() - 2).getText() == "else":
            self.visit(ctx.statementBlock(ctx.statementBlock().__len__() - 1))
        return None

    def visitLoopFor(self, ctx:PDrawSecondaryGrammarParser.LoopForContext):
        initialization = ctx.assignment()
        condition = ctx.expr(0)
        update = ctx.assignExpr()
        statementBlock = ctx.statementBlock()
        self.visit(initialization)
        while self.visit(condition):
            self.visit(statementBlock)
            self.visit(update)
        return None

    def visitLoopWhile(self, ctx:PDrawSecondaryGrammarParser.LoopWhileContext):
        condition = ctx.expr()
        statementBlock = ctx.statementBlock()
        while self.visit(condition):
            self.visit(statementBlock)
        return None

    def visitLoopUntil(self, ctx:PDrawSecondaryGrammarParser.LoopUntilContext):
        condition = ctx.expr()
        statementBlock = ctx.statementBlock()
        while not self.visit(condition):
            self.visit(statementBlock)
        return None

    def visitPauseExpr(self, ctx:PDrawSecondaryGrammarParser.PauseExprContext):
        self.pen.pause(int(self.visit(ctx.expr())))
        return None

    def visitExecute(self, ctx:PDrawSecondaryGrammarParser.ExecuteContext):
        return self.visitChildren(ctx)

    def visitPoint(self, ctx:PDrawSecondaryGrammarParser.PointContext):
        return self.visitChildren(ctx)

    def visitStatementBlock(self, ctx:PDrawSecondaryGrammarParser.StatementBlockContext):
        return self.visitChildren(ctx)

    def visitDataType(self, ctx:PDrawSecondaryGrammarParser.DataTypeContext):
        return ctx.getChild(0).getText()

    def visitMoves(self, ctx:PDrawSecondaryGrammarParser.MovesContext):
        if ctx.getChild(0).getText() == "down":
            self.pen.down()
        else:
            self.pen.up()
        return None

    def setPen(self, pen):
        self.pen = pen



