package Model.Statement;

import Exception.StatementException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.DataStructure.MyIStack;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Model.Type.Type;
import Model.Value.Value;
import Exception.ExpressionException;
import Exception.DictionaryException;
import Exception.MyException;
public class AssignmentStatement implements IStatement {
    String id;
    Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap<Value> heap = state.getHeap();

        if (symbolTable.search(this.id)) {
            Value value;
            try {
                value = this.expression.eval(symbolTable,heap);
            } catch (ExpressionException e) {
                throw new StatementException(e.getMessage());
            }
            Type typeId;
            try {
                typeId = (symbolTable.get(this.id).getType());
            } catch (DictionaryException e) {
                throw new StatementException(e.getMessage());
            }
            if (value.getType().equals(typeId)) {
                try {
                    symbolTable.update(this.id, value);
                } catch (DictionaryException e) {
                    throw new StatementException(e.getMessage());
                }
            } else {
                throw new StatementException("Declared type of variable " + this.id + " and type of the assigned expression do not match.");
            }
        } else {
            throw new StatementException("The used variable " + this.id + " was not declared before.");
        }
        return null;
    }

    public IStatement deepCopy() throws StatementException {
        try {
            return new AssignmentStatement(this.id, this.expression.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return this.id + "=" + this.expression.toString();
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            Type typeId = typeEnvironment.get(this.id);
            Type typeExpression = this.expression.typeCheck(typeEnvironment);
            if (!typeId.equals(typeExpression)) {
                throw new StatementException("Declared type of variable " + this.id +
                        " and type of the assigned expression do not match.");
            }
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
