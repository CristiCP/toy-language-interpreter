package Model.Statement;

import Exception.ExpressionException;
import Exception.HeapException;
import Exception.StatementException;
import Exception.DictionaryException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Exception.MyException;

public class HeapWriteStatement implements IStatement {
    private final String id;
    private final Expression expression;

    public HeapWriteStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap<Value> heap = state.getHeap();

        try {
            if (!symbolTable.search(this.id)) {
                throw new StatementException("Variable " + this.id + " is not defined.");
            }
            Value value = symbolTable.get(this.id);
            if (!value.getType().equals(new RefType(null))) {
                throw new StatementException("Variable " + this.id + " is not a reference type.");
            }
            RefValue referenceValue = (RefValue) value;
            if (!heap.search(referenceValue.getAddress())) {
                throw new StatementException("Address " + referenceValue.getAddress() + " is not in the heap.");
            }

            Value expressionValue = this.expression.eval(symbolTable, heap);
            if (!expressionValue.getType().equals(referenceValue.getLocationType())) {
                throw new StatementException("Expression " + this.expression + " is not of type " + referenceValue.getLocationType() + ".");
            }

            heap.update(referenceValue.getAddress(), expressionValue);
        } catch (ExpressionException | DictionaryException | HeapException e) {
            throw new StatementException(e.getMessage());
        }

        return null;
    }

    public IStatement deepCopy() throws StatementException {
        try {
            return new HeapWriteStatement(this.id, this.expression.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return "wH(" + this.id + ", " + this.expression + ")";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            Type type = typeEnvironment.get(this.id);
            if (!type.equals(new RefType(null))) {
                throw new StatementException("Variable " + this.id + " is not a reference type.");
            }
            Type expressionType = this.expression.typeCheck(typeEnvironment);
            if (!expressionType.equals(((RefType) type).getInner())) {
                throw new StatementException("Expression " + this.expression + " is not of type " +
                        ((RefType) type).getInner() + ".");
            }
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
