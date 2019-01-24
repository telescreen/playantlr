package com.buihanotes.IR.Normalized;

import com.buihanotes.IR.Token;

import java.util.List;

public class VectorNode extends ExprNode {
    public VectorNode(Token token, List<ExprNode> elements) {
        super(token);
        evalType = tVECTOR;
        for(ExprNode e : elements) {
            addChild(e);
        }
    }
}
