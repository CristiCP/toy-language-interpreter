package Model.Statement;

import Exception.StatementException;
import Model.DataStructure.MyIDictionary;
import Model.ProgramState.ProgramState;
import Exception.MyException;
import Model.Type.Type;

public class NopStatement implements IStatement {
    public ProgramState execute(ProgramState state) throws StatementException {
        return null;
    }

    public IStatement deepCopy() {
        return new NopStatement();
    }

    public String toString() {
        return "nop";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) {
        return typeEnvironment;
    }
}
