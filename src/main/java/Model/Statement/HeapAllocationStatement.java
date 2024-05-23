package Model.Statement;

import Exception.StatementException;
import Exception.ExpressionException;
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

public class HeapAllocationStatement implements IStatement {
    private final String id;
    private final Expression expression;

    public HeapAllocationStatement(String id, Expression expression) {
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
            Value idValue = symbolTable.get(this.id);
            if (!idValue.getType().equals(new RefType(null))) {
                throw new StatementException("Variable " + this.id + " is not a reference type.");
            }

            Value expressionValue = this.expression.eval(symbolTable, heap);
            RefValue referenceValue = (RefValue) idValue;
            if (!expressionValue.getType().equals(referenceValue.getLocationType())) {
                throw new StatementException("Declared type of variable " + this.id +
                        " and type of the assigned expression do not match.");
            }

            int freeAddress = heap.getNextFreeAddress();
            heap.add(expressionValue);
            symbolTable.update(this.id, new RefValue(freeAddress, expressionValue.getType()));
        } catch (ExpressionException | DictionaryException e) {
            throw new StatementException(e.getMessage());
        }

        return null;
    }

    public IStatement deepCopy() throws StatementException {
        try {
            return new HeapAllocationStatement(this.id, this.expression.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return "new(" + this.id + ", " + this.expression.toString() + ")";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            Type typeVariable = typeEnvironment.get(this.id);
            Type typeExpression = this.expression.typeCheck(typeEnvironment);
            if (!typeVariable.equals(new RefType(typeExpression))) {
                throw new StatementException("Declared type of variable " + this.id +
                        " and type of the assigned expression do not match.");
            }
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
