package IR;

import com.buihanotes.IR.Normalized.AddNode;
import com.buihanotes.IR.Normalized.ExprNode;
import com.buihanotes.IR.Normalized.IntNode;
import com.buihanotes.IR.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNormalizedAST {
    @Test
    void testNormalizedAST() {
        Token plus = new Token(Token.PLUS, "+");
        Token one = new Token(Token.INT, "1");
        Token two = new Token(Token.INT, "2");
        ExprNode root = new AddNode(new IntNode(one), plus, new IntNode(two));
        assertEquals("(+ 1<type=tINTEGER> 2<type=tINTEGER>)", root.toStringTree());
        assertEquals(ExprNode.tINTEGER, root.getEvalType());
    }
}
