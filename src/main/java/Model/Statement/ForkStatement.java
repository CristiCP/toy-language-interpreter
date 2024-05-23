package Model.Statement;

import Exception.StatementException;
import Exception.MyException;
import Exception.ExpressionException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIStack;
import Model.DataStructure.MyStack;
import Model.ProgramState.ProgramState;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class ForkStatement implements IStatement {
    private final IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        try {
            MyIStack<IStatement> newStack = new MyStack<>();
            MyIDictionary<String, Value> newSymbolTable = state.getSymbolTable().deepCopy();

            return new ProgramState(this.statement, newStack, newSymbolTable, state.getOutput(),
                    state.getFileTable(), state.getHeap(),state.getBarrierTable());

        } catch (MyException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public IStatement deepCopy() throws StatementException, ExpressionException {
        return new ForkStatement(this.statement.deepCopy());
    }

    public String toString() {
        return "fork(" + this.statement + ")";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        this.statement.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }
}
