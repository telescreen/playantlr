package com.buihanotes.IR.Normalized;

import com.buihanotes.IR.Token;

public class IntNode extends ExprNode {
    public IntNode(Token token) {
        super(token);
        evalType = tINTEGER;
    }
}
