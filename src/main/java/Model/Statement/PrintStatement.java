package Model.Statement;

import Exception.StatementException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.DataStructure.MyIList;
import Model.Expression.Expression;
import Exception.ExpressionException;
import Model.ProgramState.ProgramState;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class PrintStatement implements IStatement {
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIList<Value> output = state.getOutput();
        Value value;
        MyIHeap<Value> heap = state.getHeap();
        try {
            value = this.expression.eval(state.getSymbolTable(), heap);
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        output.pushBack(value);
        return null;
    }

    public IStatement deepCopy() throws StatementException, ExpressionException {
        try {
            return new PrintStatement(this.expression.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            this.expression.typeCheck(typeEnvironment);
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
