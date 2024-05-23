package Model.Statement;

import Exception.StatementException;
import Exception.ExpressionException;
import Model.DataStructure.MyIDictionary;
import Model.ProgramState.ProgramState;
import Model.Type.Type;
import Exception.MyException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementException;

    IStatement deepCopy() throws StatementException, ExpressionException;

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException;
}
