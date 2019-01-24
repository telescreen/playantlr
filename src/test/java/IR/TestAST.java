package IR;

import com.buihanotes.IR.AST;
import com.buihanotes.IR.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test IR AST")
public class TestAST {

    @Test
    @DisplayName("Test Normal Expression")
    void testNormalExpression() {
        Token plus = new Token(Token.PLUS, "+");
        Token one = new Token(Token.INT, "1");
        Token two = new Token(Token.INT, "2");
        AST root = new AST(plus);
        root.addChild(new AST(one));
        root.addChild(new AST(two));
        assertEquals("(+ 1 2)", root.toStringTree());
    }

    @Test
    @DisplayName("Test Vector Expression")
    void testVectorExpression() {
        Token one = new Token(Token.INT, "1");
        Token two = new Token(Token.INT, "2");
        AST root = new AST();
        root.addChild(new AST(one));
        root.addChild(new AST(two));
        assertEquals("1 2", root.toStringTree());
    }
}
