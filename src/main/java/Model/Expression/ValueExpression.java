package Model.Expression;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Expression.Expression;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class ValueExpression implements Expression {
    private final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) {
        return this.value;
    }

    public Expression deepCopy() {
        return new ValueExpression(this.value.deepCopy());
    }

    public String toString() {
        return this.value.toString();
    }

    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        return value.getType();
    }
}
