package Model.Statement;

import Exception.StatementException;
import Model.DataStructure.MyIDictionary;
import Model.ProgramState.ProgramState;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class VarDecStatement implements IStatement {
    String id;
    Type type;

    public VarDecStatement(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        if (symbolTable.search(this.id)) {
            throw new StatementException("Variable " + this.id + " is already defined.");
        } else {
            symbolTable.add(this.id, this.type.defaultValue());
        }
        return null;
    }

    public IStatement deepCopy() {
        return new VarDecStatement(this.id, (Type) this.type.deepCopy());
    }

    public String toString() {
        return this.type + " " + this.id;
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        typeEnvironment.add(this.id, this.type);
        return typeEnvironment;
    }
}
