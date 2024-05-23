package Model.Statement;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.DataStructure.MyIStack;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import Exception.StatementException;
import Exception.ExpressionException;
import Exception.MyException;

public class WhileStatement implements IStatement {
    private final Expression expression;
    private final IStatement statement;

    public WhileStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap<Value> heap = state.getHeap();
        try {
            Value value = this.expression.eval(symbolTable, heap);
            if (!value.getType().equals(new BoolType())) {
                throw new StatementException("Expression " + this.expression + " is not a boolean");
            }
            BoolValue boolValue = (BoolValue) value;
            if (boolValue.getValue()) {
                stack.push(this);
                stack.push(this.statement);
            }
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }

        return null;
    }

    public IStatement deepCopy() throws StatementException {
        try {
            return new WhileStatement(this.expression.deepCopy(), this.statement.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return "while (" + this.expression.toString() + ") " + this.statement;
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            Type expressionType = this.expression.typeCheck(typeEnvironment);
            if (!expressionType.equals(new BoolType())) {
                throw new StatementException("Expression " + this.expression + " is not a boolean");
            }
            this.statement.typeCheck(typeEnvironment.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
