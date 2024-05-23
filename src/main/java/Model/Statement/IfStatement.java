package Model.Statement;

import Exception.ExpressionException;
import Exception.StatementException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.DataStructure.MyIStack;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import Exception.MyException;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public String toString() {
        return "(IF(" + expression.toString() + ") THEN(" + thenStatement.toString()
                + ")ELSE(" + elseStatement.toString() + "))";
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIHeap<Value> heap = state.getHeap();
        BoolValue condition;
        try {
            condition = (BoolValue) this.expression.eval(state.getSymbolTable(), heap);
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        if (condition.getValue()) {
            stack.push(this.thenStatement);
        } else {
            stack.push(this.elseStatement);
        }
        return null;
    }

    public IStatement deepCopy() throws StatementException {
        try {
            return new IfStatement(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            Type typeExpression = this.expression.typeCheck(typeEnvironment);
            if (!typeExpression.equals(new BoolType())) {
                throw new StatementException("The condition of IF has not the type bool.");
            }
            this.thenStatement.typeCheck(typeEnvironment.deepCopy());
            this.elseStatement.typeCheck(typeEnvironment.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
