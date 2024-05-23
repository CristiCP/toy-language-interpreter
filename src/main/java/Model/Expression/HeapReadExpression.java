package Model.Expression;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Exception.ExpressionException;
import Exception.HeapException;
import Exception.MyException;

public class HeapReadExpression implements Expression {
    private final Expression expression;

    public HeapReadExpression(Expression expression) {
        this.expression = expression;
    }

    public Value eval(MyIDictionary<String, Value> symbolTable, MyIHeap<Value> heap) throws ExpressionException {
        try {
            Value value = this.expression.eval(symbolTable, heap);
            if (!value.getType().equals(new RefType(null))) {
                throw new ExpressionException("Expression " + this.expression + " is not a reference type.");
            }
            RefValue referenceValue = (RefValue) value;
            if (!heap.search(referenceValue.getAddress())) {
                throw new ExpressionException("Address " + referenceValue.getAddress() + " is not in the heap.");
            }
            return heap.get(referenceValue.getAddress());
        } catch (Exception e) {
            throw new ExpressionException(e.getMessage());
        }
    }

    public Expression deepCopy() throws ExpressionException {
        return null;
    }

    public String toString() {
        return "rH(" + this.expression + ")";
    }

    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type type = expression.typeCheck(typeEnvironment);
        if (type instanceof RefType reftype) {
            return reftype.getInner();
        } else throw new MyException("Expression \" + this.expression + \" is not a reference type!");
    }
}
