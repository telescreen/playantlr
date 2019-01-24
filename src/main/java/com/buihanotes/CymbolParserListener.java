package com.buihanotes;

import com.buihanotes.ST.*;
import com.buihanotes.grammars.CymbolBaseListener;
import com.buihanotes.grammars.CymbolParser;

public class CymbolParserListener extends CymbolBaseListener {
    private Scope currentScope;

    public CymbolParserListener(Scope globalScope) {
        this.currentScope = globalScope;
    }

    @Override
    public void enterBlockStatement(CymbolParser.BlockStatementContext ctx) {
        currentScope = new LocalScope(currentScope);
    }

    @Override
    public void exitBlockStatement(CymbolParser.BlockStatementContext ctx) {
        System.out.println(currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void exitVariableDeclaration(CymbolParser.VariableDeclarationContext ctx) {
        System.out.println("line " + ctx.start.getLine()
                + ": def " + ctx.IDENTIFIER().getText());
        Type tsym = (Type) currentScope.resolve(ctx.typeSpecifier().getText());
        VariableSymbol vs = new VariableSymbol(ctx.IDENTIFIER().getText(), tsym);
        currentScope.define(vs);
    }

    @Override
    public void enterFunctionDeclaration(CymbolParser.FunctionDeclarationContext ctx) {
        System.out.println("line " + ctx.start.getLine()
                + ": def method/function " + ctx.IDENTIFIER().getText());
        Type retType = (Type)currentScope.resolve(ctx.typeSpecifier().getText());
        FunctionSymbol funcSym = new FunctionSymbol(
                ctx.IDENTIFIER().getText(), retType, currentScope);
        currentScope.define(funcSym);
        currentScope = funcSym;
    }

    @Override
    public void exitFunctionDeclaration(CymbolParser.FunctionDeclarationContext ctx) {
        System.out.println(currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void exitFunctionDeclarationParameter(CymbolParser.FunctionDeclarationParameterContext ctx) {
        System.out.println("line " + ctx.start.getLine() + ": def " + ctx.IDENTIFIER().getText());
        Type parmType = (Type)currentScope.resolve(ctx.typeSpecifier().getText());
        VariableSymbol paramSym = new VariableSymbol(ctx.IDENTIFIER().getText(), parmType);
        currentScope.define(paramSym);
    }

    @Override
    public void exitAssignmentExpression(CymbolParser.AssignmentExpressionContext ctx) {
        Symbol s = currentScope.resolve(ctx.IDENTIFIER().getText());
        System.out.println("line " + ctx.start.getLine() + ": assign to " + s);
    }

    @Override
    public void exitPrimaryIdentifier(CymbolParser.PrimaryIdentifierContext ctx) {
        Symbol s = currentScope.resolve(ctx.IDENTIFIER().getText());
        System.out.println("line " + ctx.start.getLine() + ": ref " + s);
    }
}
