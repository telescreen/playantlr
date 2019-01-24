package com.buihanotes.IR.Normalized;

import com.buihanotes.IR.Token;

public class AddNode extends ExprNode {
    public AddNode(ExprNode left, Token token, ExprNode right) {
        super(token);
        addChild(left);
        addChild(right);
    }

    @Override
    public int getEvalType() {
        ExprNode left = (ExprNode)childrens.get(0);
        ExprNode right = (ExprNode)childrens.get(1);
        if (left.evalType == tINTEGER && right.evalType == tINTEGER)
            return tINTEGER;
        if (left.evalType == tVECTOR && right.evalType == tVECTOR)
            return tVECTOR;
        return tINVALID;
    }
}
