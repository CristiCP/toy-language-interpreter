package Model.Statement;

import Exception.StatementException;
import Exception.ExpressionException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Type.Type;
import Exception.MyException;

public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    public IStatement deepCopy() throws StatementException {
        try {
            return new CompoundStatement(this.first.deepCopy(), this.second.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        return this.second.typeCheck(this.first.typeCheck(typeEnvironment));
    }
}

