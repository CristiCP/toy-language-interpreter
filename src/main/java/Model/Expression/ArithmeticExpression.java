package Model.Expression;

import Exception.ExpressionException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.HashMap;
import java.util.function.IntBinaryOperator;

import Exception.MyException;

public class ArithmeticExpression implements Expression {
    private final Expression e1;
    private final Expression e2;
    private static final HashMap<Integer, IntBinaryOperator> operators = new HashMap<>();
    private static final HashMap<Integer, String> operatorsString = new HashMap<>();
    private final int operator;

    public ArithmeticExpression(Expression e1, Expression e2, int operator) {
        operators.put(1, Integer::sum);
        operatorsString.put(1, "+");
        operators.put(2, (a, b) -> a - b);
        operatorsString.put(2, "-");
        operators.put(3, (a, b) -> a * b);
        operatorsString.put(3, "*");
        operators.put(4, (a, b) -> a / b);
        operatorsString.put(4, "/");
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws ExpressionException {
        Value v1, v2;
        v1 = e1.eval(table, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(table, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                try {
                    return new IntValue(ArithmeticExpression.operators.get(this.operator).applyAsInt(n1, n2));
                } catch (Exception e) {
                    throw new ExpressionException("Division by zero.");
                }
            } else {
                throw new ExpressionException("Second operand is not an integer.");
            }
        } else {
            throw new ExpressionException("First operand is not an integer.");
        }
    }

    public Expression deepCopy() throws ExpressionException {
        return new ArithmeticExpression(this.e1.deepCopy(), this.e2.deepCopy(), this.operator);
    }

    public String toString() {
        return this.e1.toString() + ArithmeticExpression.operatorsString.get(this.operator) + this.e2.toString();
    }

    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type type1, type2;
        type1 = this.e1.typeCheck(typeEnvironment);
        type2 = this.e2.typeCheck(typeEnvironment);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else throw new MyException("Second operand is not an integer!");
        } else throw new MyException("First operand is not an integer!");
    }
}