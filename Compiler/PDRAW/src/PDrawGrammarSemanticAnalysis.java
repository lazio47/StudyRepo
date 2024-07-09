import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.*;

import error.*;

public class PDrawGrammarSemanticAnalysis extends PDrawGrammarBaseVisitor<Boolean> {

    private Map<String, Symbol<?>> symbolTable = new HashMap<>();
    private Map<String, Type> typeTable = new HashMap<>();

    public PDrawGrammarSemanticAnalysis() {
        // Initialize the type table with basic types
        typeTable.put("int", new IntegerType());
        typeTable.put("real", new RealType());
        typeTable.put("string", new StringType());
        typeTable.put("bool", new BoolType());
        typeTable.put("color", new ColorType());
        typeTable.put("pen", new PenType());
        typeTable.put("position", new PositionType());
    }

    public Map<String, Symbol<?>> symbolTable() {
        return symbolTable;
    }


    @Override
    public Boolean visitProgram(PDrawGrammarParser.ProgramContext ctx) {
        boolean flag = true;
        for (int i = 0; i < ctx.getChildCount()-1; i++) {
            if(!visit(ctx.getChild(i))) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public Boolean visitStat(PDrawGrammarParser.StatContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Boolean visitPenDecl(PDrawGrammarParser.PenDeclContext ctx) {
        String penName = ctx.ID().getText();
        if (symbolTable.containsKey(penName)) {
            ErrorHandling.printError(ctx, "Pen " + penName + " is already defined.");
            return false;
        }

        for (PDrawGrammarParser.PropertyContext p : ctx.property()) {
            if(!visit(p)) {
                return false;
            }
        }

        Symbol<PenType> penSymbol = new VariableSymbol<>(penName, new PenType());
        symbolTable.put(penName, penSymbol);
        return true;
    }

    @Override
    public Boolean visitCanvasDeclWithExpr(PDrawGrammarParser.CanvasDeclWithExprContext ctx) {
        String canvasName = ctx.ID().getText();
        if (symbolTable.containsKey(canvasName)) {
            ErrorHandling.printError(ctx, "Canvas " + canvasName + " is already defined.");
            return false;
        }
        if (!visit(ctx.expr(0)) || !visit(ctx.expr(1))) {
            return false;
        }
        Symbol<CanvasType> canvasSymbol = new VariableSymbol<>(canvasName, new CanvasType());
        symbolTable.put(canvasName, canvasSymbol);

        if (ctx.expr(2) != null && !visit(ctx.expr(2))) {  // Check background expression if it exists
            return false;
        }

        return true;
    }

    @Override 
    public Boolean visitSelectCanvas(PDrawGrammarParser.SelectCanvasContext ctx) {
        String canvasName = ctx.ID().getText();
        if(!symbolTable.containsKey(canvasName)) {
            ErrorHandling.printError(ctx, "Canvas " + canvasName + " is not defined.");
            return false;
        }
        return true;
    }

    @Override
    public Boolean visitExprConcat(PDrawGrammarParser.ExprConcatContext ctx) {
        if(!visit(ctx.expr(0)) || !visit(ctx.expr(1))) {
            return false;
        }

        if(ctx.expr(0).t.name() != typeTable.get("string").name() || ctx.expr(1).t.name() != typeTable.get("string").name()) {
            ErrorHandling.printError(ctx, "Can only concat type string");
            return false;
        }        
        return true;
    }

    @Override
    public Boolean visitDeclaration(PDrawGrammarParser.DeclarationContext ctx) {
        String dataType = ctx.dataType().getText();
        Type varType = typeTable.get(dataType);
        List<TerminalNode> ids = ctx.ID();
        List<PDrawGrammarParser.AssignmentContext> assignments = ctx.assignment();



        // Declare variables created
        for (int i = 0; i < ids.size(); i++) {
            String varName = ids.get(i).getText();
            if (symbolTable.containsKey(varName)) {
                ErrorHandling.printError(ctx, "Variable " + varName + " is already declared.");
                return false;
            }
            symbolTable.put(varName, new VariableSymbol<>(varName, varType));
        }

        // Visit assignments
        for (int i = 0; i < assignments.size(); i++) {
            PDrawGrammarParser.AssignmentContext assignment = assignments.get(i);
            if (assignment == null) {
                ErrorHandling.printError(ctx, "Assignment at index " + i + " is null.");
                return false;
            }
            
            // Declare the variable
            String varName = assignment.ID().getText();
            if (symbolTable.containsKey(varName)) {
                ErrorHandling.printError(ctx, "Variable " + varName + " is already declared.");
                return false;
            }
            symbolTable.put(varName, new VariableSymbol<>(varName, varType));
            
            // Visit it
            if (!visit(assignment)) {
                return false;
            }

            if(!assignment.expr().t.subtype(varType)) {
                ErrorHandling.printError(ctx, "Failed to assign " + varName + "of type " + varType + "to " + assignment.expr().getText() + " of type " + assignment.expr().t);
                return false;
            }
        }

        return true;
    }

    @Override
    public Boolean visitProperty(PDrawGrammarParser.PropertyContext ctx) {        
        if (!visit(ctx.expr())) {
            return false;
        }

        Type exprType = ctx.expr().t;
        switch (ctx.propertyName().getText()) {
            case "color":
                if (!exprType.subtype(typeTable.get("color"))) {
                    ErrorHandling.printError(ctx, "Property 'color' requires an expression of type color.");
                    return false;
                }
                break;
            case "position":
                if (!exprType.subtype(typeTable.get("position"))) {
                    ErrorHandling.printError(ctx, "Property 'position' requires an expression of type position.");
                    return false;
                }
                break;
            case "orientation":
                if (!exprType.subtype(typeTable.get("int")) && !exprType.subtype(typeTable.get("real"))) {
                    ErrorHandling.printError(ctx, "Property 'orientation' requires an expression of type int or real.");
                    return false;
                }
                break;
            case "thickness":
                if (!exprType.subtype(typeTable.get("int")) && !exprType.subtype(typeTable.get("real"))) {
                    ErrorHandling.printError(ctx, "Property 'thickness' requires an expression of type int or real.");
                    return false;
                }
                break;
            case "pressure":
                if (!exprType.subtype(typeTable.get("int")) && !exprType.subtype(typeTable.get("real"))) {
                    ErrorHandling.printError(ctx, "Property 'pressure' requires an expression of type int or real.");
                    return false;
                }
                break;
            default:
                break;
        }        
        return true;
    }

    @Override
    public Boolean visitSetBackground(PDrawGrammarParser.SetBackgroundContext ctx) {
        if(!visit(ctx.expr())) {
            return false;
        }

        if (!ctx.expr().t.subtype(typeTable.get("color"))) {
            ErrorHandling.printError(ctx, "'Background' requires an expression of type color.");
            return false;
        }

        return true;
    }

    @Override
    public Boolean visitSetProperty(PDrawGrammarParser.SetPropertyContext ctx) {
        if(!visit(ctx.expr(0)) || !visit(ctx.expr(1))) {
            return false;
        }

        if (ctx.expr(0).t.name() != typeTable.get("pen").name()) {
            ErrorHandling.printError(ctx, "Property must be assigned to a pen.");
            return false;
        }


        Type exprType = ctx.expr(1).t;
        switch (ctx.propertyName().getText()) {
            case "color":
                if (!exprType.subtype(typeTable.get("color"))) {
                    ErrorHandling.printError(ctx, "Property 'color' requires an expression of type color.");
                    return false;
                }
                break;
            case "position":
                if (!exprType.subtype(typeTable.get("position"))) {
                    ErrorHandling.printError(ctx, "Property 'position' requires an expression of type position.");
                    return false;
                }
                break;
            case "orientation":
                if (!exprType.subtype(typeTable.get("int")) && !exprType.subtype(typeTable.get("real"))) {
                    ErrorHandling.printError(ctx, "Property 'orientation' requires an expression of type int or real.");
                    return false;
                }
                break;
            case "thickness":
                if (!exprType.subtype(typeTable.get("int")) && !exprType.subtype(typeTable.get("real"))) {
                    ErrorHandling.printError(ctx, "Property 'thickness' requires an expression of type int or real.");
                    return false;
                }
                break;
            case "pressure":
                if (!exprType.subtype(typeTable.get("int")) && !exprType.subtype(typeTable.get("real"))) {
                    ErrorHandling.printError(ctx, "Property 'pressure' requires an expression of type int or real.");
                    return false;
                }
                break;
            default:
                break;
        }     

        return true;
    }

    @Override
    public Boolean visitAssignment(PDrawGrammarParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        if (!symbolTable.containsKey(varName)) {
            ErrorHandling.printError(ctx, "Variable " + varName + " is not declared.");
            return false;
        }
        
        if(!visit(ctx.expr())) {
            return false;
        }

        if(!symbolTable.get(varName).type().subtype(ctx.expr().t)) {
            ErrorHandling.printError(ctx, "Failed to assign " + varName + " of type " + symbolTable.get(varName).type().name + " to " + ctx.expr().getText() + " of type " + ctx.expr().t.name);
            return false;
        }


        return true;
    }

    @Override
    public Boolean visitInstructFirst(PDrawGrammarParser.InstructFirstContext ctx) {
        String penName = ctx.ID().getText();
        if (!symbolTable.containsKey(penName)) {
            ErrorHandling.printError(ctx, "Pen " + penName + " is not declared.");
            return false;
        }

        // Validate the instruction type and parameters
        if (ctx.miniInstruc() != null && !visit(ctx.miniInstruc())) {
            return false;
        }
        if (ctx.pauseExpr() != null && !visit(ctx.pauseExpr())) {
            return false;
        }
        // Don't need to check moves as they are just constants
        
        return true;
    }

    @Override
    public Boolean visitMiniInstruc(PDrawGrammarParser.MiniInstrucContext ctx) {
        if (!visit(ctx.expr())) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean visitExecute(PDrawGrammarParser.ExecuteContext ctx) {
        if(!visit(ctx.expr())) {
            return false;
        }

        if (ctx.expr().t.name() != typeTable.get("pen").name()) {
            ErrorHandling.printError(ctx, "Execute expression must be of type pen");
            return false;
        } 

        return true;
    }

    @Override
    public Boolean visitExprPoint(PDrawGrammarParser.ExprPointContext ctx) {
        PDrawGrammarParser.PointContext point = ctx.point();
        if (!visit(point.expr(0)) || !visit(point.expr(1))) {
            return false;
        }

        if(!point.expr(0).t.subtype(typeTable.get("int"))) {
            ErrorHandling.printError(ctx, "X Position must be defined by an integer");
            return false;
        }

        if(!point.expr(1).t.subtype(typeTable.get("int"))) {
            ErrorHandling.printError(ctx, "Y Position must be defined by an integer");
            return false;
        }

        ctx.t = new PositionType();
        return true;
    }

    @Override
    public Boolean visitExprInput(PDrawGrammarParser.ExprInputContext ctx) {
        ctx.t = typeTable.get("string");
        return true;
    }

    @Override
    public Boolean visitExprParentheses(PDrawGrammarParser.ExprParenthesesContext ctx) {
        if (!visit(ctx.expr())) {
            return false;
        }
        ctx.t = ctx.expr().t;
        return true;
    }

    @Override
    public Boolean visitTypeConversion(PDrawGrammarParser.TypeConversionContext ctx) {
        String type = ctx.dataType().getText();
        if(!typeTable.containsKey(type)) {
            ErrorHandling.printError(ctx, "Type " + type + " does not exist.");
            return false;
        }

        if(!visit(ctx.expr())){
            return false;
        }
        
        ctx.t = typeTable.get(type);
        return true;
    }

    @Override
    public Boolean visitExprPointIncrement(PDrawGrammarParser.ExprPointIncrementContext ctx) {
        if(!symbolTable.containsKey(ctx.ID().getText())) {
            ErrorHandling.printError(ctx, ctx.ID().getText() + " was not declared.");
            return false;
        }

        if(!symbolTable.get(ctx.ID().getText()).type().subtype(typeTable.get("position"))) {
            ErrorHandling.printError(ctx, "Can't increment point " + ctx.point().getText() + " with type " + symbolTable.get(ctx.ID().getText()).type().name());
            return false;
        }
        ctx.t = typeTable.get("position");
        return true;
    }

    @Override
    public Boolean visitExprUnary(PDrawGrammarParser.ExprUnaryContext ctx) {
        if(!visit(ctx.expr())) {
            return false;
        }

        if(!ctx.expr().t.subtype(typeTable.get("int")) && !ctx.expr().t.subtype(typeTable.get("real"))) {
            ErrorHandling.printError(ctx, "Can not make unary operations for type " + ctx.expr().t);
        }

        ctx.t = ctx.expr().t;
        return true;
    }

    @Override
    public Boolean visitPauseExpr(PDrawGrammarParser.PauseExprContext ctx) {
        if (!visit(ctx.expr())) {
            return false;
        }

        if (!ctx.expr().t.subtype(typeTable.get("int")) && !ctx.expr().t.subtype(typeTable.get("real"))) {
            ErrorHandling.printError(ctx, "Pause expression must be of type int or real.");
            return false;
        }
        
        return true;
    }
    
    @Override
    public Boolean visitExprMultDivMod(PDrawGrammarParser.ExprMultDivModContext ctx) {
        if(!visit(ctx.e1) || !visit(ctx.e2)) {
            return false;
        }
        Type t1 = ctx.e1.t;
        Type t2 = ctx.e2.t;
        if (!t1.subtype(typeTable.get("int")) && !t1.subtype(typeTable.get("real")) ) {
            ErrorHandling.printError(ctx, "Type mismatch: " + t1.name() + " is not Integer nor Real");
            return false;
        }
        if (!t2.subtype(typeTable.get("int")) && !t2.subtype(typeTable.get("real")) ) {
            ErrorHandling.printError(ctx, "Type mismatch: " + t2.name() + " is not Integer nor Real");
            return false;
        }
        ctx.t = t1;
        return true;
    }

    @Override
    public Boolean visitExprAddSub(PDrawGrammarParser.ExprAddSubContext ctx) {
        if (!visit(ctx.expr(0)) || !visit(ctx.expr(1))) {
            return false;
        }
        Type t1 = ctx.expr(0).t;
        Type t2 = ctx.expr(1).t;
        if (!t1.subtype(t2)) {
            ErrorHandling.printError(ctx, "Type mismatch: " + t1.name() + " and " + t2.name());
            return false;
        }
        ctx.t = t1;
        return true;
    }
    
    @Override
    public Boolean visitExprBoolOperation(PDrawGrammarParser.ExprBoolOperationContext ctx) {
        if(!visit(ctx.e1) || !visit(ctx.e2)) {
            return false;
        }

        if(!ctx.e1.t.subtype(ctx.e2.t)) {
            ErrorHandling.printError(ctx, "Can't make boolean operation between different types");
            return false;
        }

        ctx.t = typeTable.get("bool");
        return true;
    }

    @Override
    public Boolean visitExprNot(PDrawGrammarParser.ExprNotContext ctx) {
        if(!visit(ctx.expr())) {
            return false;
        }

        if(!ctx.expr().t.subtype(typeTable.get("bool"))) {
            ErrorHandling.printError(ctx, "Can not negate a non boolean expression");
            return false;
        }

        ctx.t = typeTable.get("bool");
        return true;
    }

    @Override
    public Boolean visitExprNewPen(PDrawGrammarParser.ExprNewPenContext ctx) {
        ctx.t = typeTable.get("pen");
        return true;
    }

    @Override
    public Boolean visitExprId(PDrawGrammarParser.ExprIdContext ctx) {
        String varName = ctx.ID().getText();
        if (!symbolTable.containsKey(varName)) {
            ErrorHandling.printError(ctx, "Variable " + varName + " is not declared.");
            return false;
        }
        ctx.t = symbolTable.get(varName).type();
        return true;
    }
    
    
    @Override
    public Boolean visitExprAndOr(PDrawGrammarParser.ExprAndOrContext ctx) {
        if(!visit(ctx.e1) || !visit(ctx.e2)) {
            return false;
        }

        if(ctx.e1.t == null || !ctx.e1.t.subtype(typeTable.get("bool"))) {
            ErrorHandling.printError(ctx, "Expression " + ctx.e1.getText() + " must have a boolean value");
            return false;
        }

        if(ctx.e2.t == null || !ctx.e2.t.subtype(typeTable.get("bool"))) {
            ErrorHandling.printError(ctx, "Expression " + ctx.e2.getText() + " must have a boolean value");
            return false;
        }

        ctx.t = typeTable.get("bool");
        return true;
    }


    @Override
    public Boolean visitExprInt(PDrawGrammarParser.ExprIntContext ctx) {
        ctx.t = typeTable.get("int");
        return true;
    }

    @Override
    public Boolean visitExprReal(PDrawGrammarParser.ExprRealContext ctx) {
        ctx.t = typeTable.get("real");
        return true;
    }

    @Override
    public Boolean visitExprBoolean(PDrawGrammarParser.ExprBooleanContext ctx) {
        ctx.t = typeTable.get("bool");
        return true;
    }

    @Override
    public Boolean visitExprString(PDrawGrammarParser.ExprStringContext ctx) {
        ctx.t = typeTable.get("string");
        return true;
    }

    @Override
    public Boolean visitExprColor(PDrawGrammarParser.ExprColorContext ctx) {
        ctx.t = new ColorType();
        return true;
    }

    @Override
    public Boolean visitExprInstrucToPen(PDrawGrammarParser.ExprInstrucToPenContext ctx) {
        if(!visit(ctx.instruc())) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean visitExprPI(PDrawGrammarParser.ExprPIContext ctx) {
        ctx.t = typeTable.get("real");
        return true;
    }

    @Override
    public Boolean visitOutputExpr(PDrawGrammarParser.OutputExprContext ctx) {
        if(!visit(ctx.expr())) {
            return false;
        }
        return true;
    }

    @Override 
    public Boolean visitLoopFor(PDrawGrammarParser.LoopForContext ctx) {
        Map<String, Symbol<?>> deepCopy = deepCopySymbolTable(symbolTable); 

        if ((ctx.declaration() != null && !visit(ctx.declaration())) || !visit(ctx.expr())) {
            return false;
        }

        for (int i = 0; i < ctx.assignment().size(); i++) {
            if (!visit(ctx.assignment(i))) {
                return false;
            }
        }

        // Remove variables declarated for the loop
        if(ctx.declaration() != null) {
            symbolTable = deepCopy;
        }
        return true;
    }

    @Override
    public Boolean visitLoopUntil(PDrawGrammarParser.LoopUntilContext ctx) {
        if (!visit(ctx.expr())) {
            return false;
        }

        if (!ctx.expr().t.subtype(typeTable.get("bool"))) {
            ErrorHandling.printError(ctx, "Loop Until expression must be of type bool");
            return false;
        }

        for (PDrawGrammarParser.StatContext st : ctx.statementBlock().stat()) {
            visit(st);
        }
        
        return true;
    }

    @Override
    public Boolean visitLoopWhile(PDrawGrammarParser.LoopWhileContext ctx) {
        if (!visit(ctx.expr())) {
            return false;
        }

        if (!ctx.expr().t.subtype(typeTable.get("bool"))) {
            ErrorHandling.printError(ctx, "Loop While expression must be of type bool");
            return false;
        }

        for (PDrawGrammarParser.StatContext st : ctx.statementBlock().stat()) {
            visit(st);
        }
        
        return true;
    }

    @Override
    public Boolean visitConditional(PDrawGrammarParser.ConditionalContext ctx) {
        for (PDrawGrammarParser.ExprContext expr : ctx.expr()) {
            if (!visit(expr)) {
                return false;
            }
            if (!expr.t.subtype(typeTable.get("bool"))) {
                ErrorHandling.printError(ctx, "Condition expression must be of type bool");
                return false;
            }
        }
        
        for (PDrawGrammarParser.StatementBlockContext stBlock : ctx.statementBlock()) {
            for (PDrawGrammarParser.StatContext st : stBlock.stat()) {
                visit(st);
            }
        }

        return true;
    }


    // Helpful Functions

    private Map<String, Symbol<?>> deepCopySymbolTable(Map<String, Symbol<?>> original) {
        Map<String, Symbol<?>> copy = new HashMap<>();
        for (Map.Entry<String, Symbol<?>> entry : original.entrySet()) {
            copy.put(entry.getKey(), new VariableSymbol<>(entry.getValue().name(), entry.getValue().type()));
        }
        return copy;
    }
    
}
