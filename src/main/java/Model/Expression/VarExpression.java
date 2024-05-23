package Model.Expression;
import Exception.DictionaryException;
import Exception.ExpressionException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class VarExpression implements Expression {
    String id;

    public VarExpression(String id) {
        this.id = id;
    }

    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws ExpressionException {
        try {
            return table.get(this.id);
        } catch (DictionaryException e) {
            throw new ExpressionException(e.getMessage());
        }
    }

    public Expression deepCopy() {
        return new VarExpression(this.id);
    }

    public String toString() {
        return this.id;
    }

    public Type typeCheck(MyIDictionary<String,Type> typeEnvironment) throws MyException {
        return typeEnvironment.get(this.id);
    }
}
