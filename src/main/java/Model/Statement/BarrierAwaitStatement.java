package Model.Statement;

import Model.DataStructure.ISyncTable;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIStack;
import Model.DataStructure.Pair;
import Model.ProgramState.ProgramState;
import Exception.StatementException;
import Exception.ExpressionException;
import Exception.DictionaryException;
import Exception.MyException;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.List;

public class BarrierAwaitStatement implements IStatement {
    private final String id;

    public BarrierAwaitStatement(String id) {
        this.id = id;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        ISyncTable barrierTable = state.getBarrierTable();

        if (!symbolTable.search(this.id)) {
            throw new StatementException("Variable " + this.id + " is not defined!");
        }
        Value val = null;
        try {
            val = symbolTable.get(this.id);
        } catch (DictionaryException e) {
            throw new RuntimeException(e);
        }
        if (!val.getType().equals(new IntType())) {
            throw new StatementException("Variable " + this.id + " is not of type int!");
        }

        int address = ((IntValue) val).getValue();
        if (!barrierTable.search(address)) {
            throw new StatementException("Address " + address + " is not defined in the barrier table!");
        }

        Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) barrierTable.get(address);
        int listLen = pair.getSecond().size();
        if (pair.getFirst() > listLen) {
            if (!pair.getSecond().contains(state.getId())) {
                pair.getSecond().add(state.getId());
            }
            stack.push(this);
        }

        return null;
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws StatementException, DictionaryException {
        if (!typeEnvironment.search(this.id)) {
            throw new StatementException("Variable " + this.id + " is not defined!");
        }
        Type typeId = typeEnvironment.get(this.id);
        if (!typeId.equals(new IntType())) {
            throw new StatementException("Variable " + this.id + " is not of type int!");
        }

        return typeEnvironment;
    }

    public IStatement deepCopy() {
        return new BarrierAwaitStatement(id);
    }

    public String toString() {
        return "awaitBarrier(" + id + ")";
    }
}
