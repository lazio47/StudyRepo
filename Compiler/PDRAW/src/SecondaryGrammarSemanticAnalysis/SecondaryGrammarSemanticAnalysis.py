from antlr4 import *
from PDrawSecondaryGrammarParser import PDrawSecondaryGrammarParser
from PDrawSecondaryGrammarVisitor import PDrawSecondaryGrammarVisitor

from Type import Type
from Symbol import Symbol
from VariableSymbol import VariableSymbol
from BoolType import BoolType
from ColorType import ColorType
from CanvasType import CanvasType
from IntegerType import IntegerType
from PenType import PenType
from PositionType import PositionType
from RealType import RealType
from StringType import StringType
from ErrorHandling import ErrorHandling


class SecondaryGrammarSemanticAnalysis(PDrawSecondaryGrammarVisitor):

    def __init__(self):
        self.symbolTable = {}
        self.typeTable = {
            "string": StringType(),
            "real": RealType(),
            "int": IntegerType(),
            "boolean": BoolType(),
            "color": ColorType(),
            "position": PositionType(),
        }

    def visitExprPI(self, ctx: PDrawSecondaryGrammarParser.ExprPIContext):
        ctx.t = self.typeTable["real"]
        return True

    def visitExprBoolOperation(
        self, ctx: PDrawSecondaryGrammarParser.ExprBoolOperationContext
    ):
        res = self.visit(ctx.e1) and self.visit(ctx.e2)
        if not (ctx.e1.t == ctx.e2.t):
            ErrorHandling.printError(
                ctx, "Can't make boolean operation between different types!"
            )
            return False
        ctx.t = self.typeTable["boolean"]
        return True

    def visitExprConcat(self, ctx: PDrawSecondaryGrammarParser.ExprConcatContext):
        res = self.visit(ctx.expr(0)) and self.visit(ctx.expr(1))
        if not (
            (ctx.expr(0).t == self.typeTable["string"])
            and (ctx.expr(1).t == self.typeTable["string"])
        ):
            ErrorHandling.printError(ctx, "Can't concatenate non-string types!")
            return False
        ctx.t = self.typeTable["string"]
        return True

    def visitExprString(self, ctx: PDrawSecondaryGrammarParser.ExprStringContext):
        ctx.t = self.typeTable["string"]
        return True

    def visitExprPoint(self, ctx: PDrawSecondaryGrammarParser.ExprPointContext):
        res = self.visit(ctx.point().x) and self.visit(ctx.point().y)
        if not (
            (ctx.point().x.t == self.typeTable["int"])
            or (ctx.point().x.t == self.typeTable["real"])
        ) or not (
            (ctx.point().y.t == self.typeTable["int"])
            or (ctx.point().y.t == self.typeTable["real"])
        ):
            ErrorHandling.printError(
                ctx, "X and Y coordinates must be of type integer or real!"
            )
            res = False
        ctx.t = self.typeTable["position"]
        return res

    def visitExprParentheses(
        self, ctx: PDrawSecondaryGrammarParser.ExprParenthesesContext
    ):
        res = self.visit(ctx.expr())
        if res:
            ctx.t = ctx.expr().t
        return res

    def visitExprInt(self, ctx: PDrawSecondaryGrammarParser.ExprIntContext):
        ctx.t = self.typeTable["int"]
        return True

    def visitExprBoolean(self, ctx: PDrawSecondaryGrammarParser.ExprBooleanContext):
        ctx.t = self.typeTable["boolean"]
        return True

    def visitExprColor(self, ctx: PDrawSecondaryGrammarParser.ExprColorContext):
        ctx.t = self.typeTable["color"]
        return True

    def visitExprInput(self, ctx: PDrawSecondaryGrammarParser.ExprInputContext):
        ctx.t = self.typeTable["string"]
        return True

    def visitExprNot(self, ctx: PDrawSecondaryGrammarParser.ExprNotContext):
        res = self.visit(ctx.e1)
        if res:
            ctx.t = self.typeTable["boolean"]
        return res

    def visitExprAndOr(self, ctx: PDrawSecondaryGrammarParser.ExprAndOrContext):
        res = self.visit(ctx.e1) and self.visit(ctx.e2)
        if res:
            ctx.t = ctx.e2.t
        return res

    def visitExprMultDivMod(
        self, ctx: PDrawSecondaryGrammarParser.ExprMultDivModContext
    ):
        res = self.visit(ctx.e1) and self.visit(ctx.e2)
        if res:
            ctx.t = self.getBinaryOperationType(ctx.e1, ctx.e2)
            if ctx.op.text in ["//", "\\\\"]:
                if not (
                    (ctx.e1.t == self.typeTable["int"])
                    or (ctx.e1.t == self.typeTable["real"])
                ):
                    ErrorHandling.printError(
                        ctx,
                        f"Type expression {ctx.e1.getText()} must be an integer or a real number!",
                    )
                    res = False
                elif not (
                    (ctx.e2.t == self.typeTable["int"])
                    or (ctx.e2.t == self.typeTable["real"])
                ):
                    ErrorHandling.printError(
                        ctx,
                        f"Type expression {ctx.e2.getText()} must be an integer or a real number!",
                    )
                    res = False
                if ctx.op.text == "\\\\" and not (ctx.e2.t == self.typeTable["int"]):
                    ErrorHandling.printError(
                        ctx, f"Type expression {ctx.e2.getText()} is not an integer!"
                    )
                    res = False
        return res

    def visitExprAddSub(self, ctx: PDrawSecondaryGrammarParser.ExprAddSubContext):
        res = self.visit(ctx.e1) and self.visit(ctx.e2)
        if res:
            ctx.t = self.getBinaryOperationType(ctx.e1, ctx.e2)
        return res

    def visitTypeConversion(
        self, ctx: PDrawSecondaryGrammarParser.TypeConversionContext
    ):
        res = self.visit(ctx.expr())
        type_str = ctx.dataType().getText()
        if type_str not in self.typeTable:
            ErrorHandling.printError(ctx, f"Type {type_str} does not exist!")
            res = False
        else:
            ctx.t = self.typeTable[type_str]
        return res

    def visitExprUnary(self, ctx: PDrawSecondaryGrammarParser.ExprUnaryContext):
        return self.visit(ctx.expr())

    def visitExprReal(self, ctx: PDrawSecondaryGrammarParser.ExprRealContext):
        ctx.t = self.typeTable["real"]
        return True

    def visitExprId(self, ctx: PDrawSecondaryGrammarParser.ExprIdContext):
        var = ctx.ID().getText()
        if var not in self.symbolTable:
            ErrorHandling.printError(ctx, f"Variable {var} is not declared!")
            return False
        ctx.t = self.symbolTable[var].type
        return True

    def visitProperty(self, ctx: PDrawSecondaryGrammarParser.PropertyContext):
        res = self.visit(ctx.expr())
        if res:
            property_name = ctx.propertyName().getText()
            expected_type = self.typeTable.get(property_name)
            if expected_type and not (ctx.expr().t == expected_type):
                ErrorHandling.printError(
                    ctx,
                    f"Invalid property type {ctx.expr().t} for property {property_name}!",
                )
                res = False
        return res

    def visitCanvasDeclWithExpr(
        self, ctx: PDrawSecondaryGrammarParser.CanvasDeclWithExprContext
    ):
        res = self.visit(ctx.expr(0)) and self.visit(ctx.expr(1))
        if res:
            if not (
                (ctx.expr(0).t == self.typeTable["int"])
                or (ctx.expr(0).t == self.typeTable["real"])
            ):
                ErrorHandling.printError(
                    ctx, "Canvas height should be of type integer or real!"
                )
                res = False
            if not (
                (ctx.expr(1).t == self.typeTable["int"])
                or (ctx.expr(1).t == self.typeTable["real"])
            ):
                ErrorHandling.printError(
                    ctx, "Canvas width should be of type integer or real!"
                )
                res = False
            canvas = ctx.ID().getText()
            if canvas in self.symbolTable:
                ErrorHandling.printError(ctx, f"Canvas {canvas} is already defined!")
                res = False
            else:
                self.symbolTable[canvas] = CanvasType()
        return res

    def visitSelectCanvas(self, ctx: PDrawSecondaryGrammarParser.SelectCanvasContext):
        res = True
        canvas = ctx.ID().getText()

        if canvas not in self.symbolTable:
            ErrorHandling.printError(ctx, f"Canvas {canvas} is not defined!")
            res = False
        return res

    def visitSetBackground(self, ctx: PDrawSecondaryGrammarParser.SetBackgroundContext):
        res = self.visit(ctx.expr())
        if res and not (ctx.expr().t == self.typeTable["color"]):
            ErrorHandling.printError(
                ctx,
                f"Canvas background is type {ctx.expr().t} and should be of type color!",
            )
            res = False
        return res

    def visitDeclaration(self, ctx: PDrawSecondaryGrammarParser.DeclarationContext):
        data_type = ctx.dataType().getText()
        var_type = self.typeTable.get(data_type)
        ids = ctx.ID()
        assignments = ctx.assignment()

        for i in range(len(ids)):
            var_name = ids[i].getText()
            if var_name in self.symbolTable:
                ErrorHandling.printError(
                    ctx, f"Variable {var_name} is already declared."
                )
                return False
            self.symbolTable[var_name] = VariableSymbol(var_name, var_type)

        for i in range(len(assignments)):
            assignment = assignments[i]
            if assignment is None:
                ErrorHandling.printError(ctx, f"Assignment at index {i} is null.")
                return False

            var_name = assignment.ID().getText()
            if var_name in self.symbolTable:
                ErrorHandling.printError(
                    ctx, f"Variable {var_name} is already declared."
                )
                return False
            self.symbolTable[var_name] = VariableSymbol(var_name, var_type)

            if not self.visit(assignment):
                ErrorHandling.printError(
                    ctx, f"Failed to visit assignment: {assignment.getText()}"
                )
                return False
            if not (assignment.expr().t == var_type):
                ErrorHandling.printError(
                    ctx,
                    f"Failed to assign {var_name} of type {var_type} to {assignment.expr().getText()} of type {assignment.expr().t}",
                )
                return False
        return True

    def visitAssignment(self, ctx: PDrawSecondaryGrammarParser.AssignmentContext):
        var_name = ctx.ID().getText()
        if var_name not in self.symbolTable:
            ErrorHandling.printError(ctx, f"Variable {var_name} is not declared.")
            return False

        if not self.visit(ctx.expr()):
            return False

        var_type = self.symbolTable[var_name]
        if not (var_type == ctx.expr().t):
            ErrorHandling.printError(
                ctx,
                f"Failed to assign {var_name} of type {var_type.name} to {ctx.expr().getText()} of type {ctx.expr().t.name}",
            )
            return False

        return True

    def visitExecute(self, ctx: PDrawSecondaryGrammarParser.ExecuteContext):
        res = self.visit(ctx.expr())

        if ctx.expr().t.name() != self.typeTable["pen"].name():
            ErrorHandling.printError(
                ctx,
                f"Execute expression is of type "
                + ctx.expr().t.name()
                + "and must be of type pen",
            )
            res = False

        return res

    def visitConditional(self, ctx: PDrawSecondaryGrammarParser.ConditionalContext):
        for expr in ctx.expr():
            if not self.visit(expr):
                return False
            if not (expr.t == self.typeTable["bool"]):
                ErrorHandling.printError(
                    ctx, "Condition expression must be of type bool"
                )
                return False

        for st_block in ctx.statementBlock():
            for st in st_block.stat():
                if not self.visit(st):
                    return False

        return True

    def visitLoopFor(self, ctx: PDrawSecondaryGrammarParser.LoopForContext):
        copy = self.symbolTableCopy(self.symbolTable)

        if (ctx.declaration() and not self.visit(ctx.declaration())) or not self.visit(
            ctx.expr()
        ):
            return False

        for assignment in ctx.assignment():
            if not self.visit(assignment):
                return False

        if ctx.declaration():
            self.symbolTable = copy

        return True

    def visitLoopWhile(self, ctx: PDrawSecondaryGrammarParser.LoopWhileContext):
        if not self.visit(ctx.expr()):
            return False

        if not (ctx.expr().t == self.typeTable["bool"]):
            ErrorHandling.printError(ctx, "Loop While expression must be of type bool")
            return False

        for st in ctx.statementBlock().stat():
            if not self.visit(st):
                return False

        return True

    def visitLoopUntil(self, ctx: PDrawSecondaryGrammarParser.LoopUntilContext):
        if not self.visit(ctx.expr()):
            return False

        if not (ctx.expr().t == self.typeTable["bool"]):
            ErrorHandling.printError(ctx, "Loop Until expression must be of type bool")
            return False

        for st in ctx.statementBlock().stat():
            if not self.visit(st):
                return False

        return True

    def visitPauseExpr(self, ctx: PDrawSecondaryGrammarParser.PauseExprContext):
        if not self.visit(ctx.expr()):
            return False

        expr_type = ctx.expr().t
        if not (
            (expr_type == self.typeTable["int"])
            or (expr_type == self.typeTable["real"])
        ):
            ErrorHandling.printError(
                ctx, "Pause expression must be of type int or real."
            )
            return False

        return True

    def visitStatementBlock(
        self, ctx: PDrawSecondaryGrammarParser.StatementBlockContext
    ):
        for stat in ctx.stat():
            if not self.visit(stat):
                return False
        return True

    def visitOutputExpr(self, ctx: PDrawSecondaryGrammarParser.OutputExprContext):
        return self.visit(ctx.expr())

    def visitInstructRecusive(
        self, ctx: PDrawSecondaryGrammarParser.InstructRecusiveContext
    ):
        return self.visitChildren(ctx)

    def visitInstructFirst(self, ctx: PDrawSecondaryGrammarParser.InstructFirstContext):
        return self.visitChildren(ctx)

    def visitPositionInstruction(
        self, ctx: PDrawSecondaryGrammarParser.PositionInstructionContext
    ):
        return self.visitChildren(ctx)

    def visitMiniInstruc(self, ctx: PDrawSecondaryGrammarParser.MiniInstrucContext):
        return self.visit(ctx.expr())

    def symbolTableCopy(self, original):
        copy = {}

        for key, value in original.items():
            copy[key] = VariableSymbol(value.name(), value.type())
        return copy

    def getBinaryOperationType(self, e1, e2):
        if e1.t == e2.t:
            return e1.t
        else:
            return self.typeTable.get("real")
