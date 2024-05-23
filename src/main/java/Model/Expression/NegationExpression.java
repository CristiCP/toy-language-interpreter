package Model.Expression;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import Exception.ExpressionException;
import Exception.MyException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class NegationExpression implements Expression{
    private final String operator;
    private final Expression expression;
    private static final Map<String, UnaryOperator<Boolean>> booleanOperators = new HashMap<>(
            Map.of("!", (a) -> !a)
    );

    public NegationExpression(String operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    public Value eval(MyIDictionary<String, Value> symbolTable, MyIHeap<Value> heap) throws ExpressionException {
        Value value = this.expression.eval(symbolTable, heap);
        if (value.getType().equals(new BoolType())) {
            return new BoolValue(NegationExpression.booleanOperators.get(this.operator).apply(((BoolValue) value).getValue()));
        }
        throw new ExpressionException("invalid operand type");
    }

    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type type = this.expression.typeCheck(typeEnvironment);
        if (type.equals(new BoolType())) {
            return new BoolType();
        }
        throw new MyException("invalid operand type");
    }

    public Expression deepCopy() throws ExpressionException {
        return new NegationExpression(this.operator, this.expression.deepCopy());
    }
}
