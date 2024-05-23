package Model.Statement;

import Model.DataStructure.ISyncTable;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.DataStructure.Pair;
import Model.Expression.Expression;

import java.util.ArrayList;
import java.util.List;

import Exception.StatementException;
import Model.ProgramState.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import Exception.ExpressionException;
import Exception.MyException;
import Exception.DictionaryException;

public class BarrierDecStatement implements IStatement {
    private final String id;
    private final Expression expression;
    public BarrierDecStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();
        ISyncTable barrierTable = state.getBarrierTable();

        Value valExp;
        try {
            valExp = this.expression.eval(symbolTable, heap);
            if (!valExp.getType().equals(new IntType())) {
                throw new StatementException("Expression " + this.expression + " is not of type int!");
            }
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }

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
        int value = ((IntValue) valExp).getValue();
        Pair<Integer, List<Integer>> pair = new Pair<>(value, new ArrayList<>());
        int address = barrierTable.add(pair);
        try {
            symbolTable.update(this.id, new IntValue(address));
        } catch (DictionaryException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws StatementException, DictionaryException {
        try {
            Type typeExp1 = this.expression.typeCheck(typeEnvironment);
            if (!typeExp1.equals(new IntType())) {
                throw new StatementException("Expression " + this.expression + " is not of type int!");
            }
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

        if (!typeEnvironment.search(this.id)) {
            throw new StatementException("Variable " + this.id + " is not defined!");
        }
        Type typeId = typeEnvironment.get(this.id);
        if (!typeId.equals(new IntType())) {
            throw new StatementException("Variable " + this.id + " is not of type int!");
        }

        return typeEnvironment;
    }

    public IStatement deepCopy() throws ExpressionException {
        return new BarrierDecStatement(this.id, this.expression.deepCopy());
    }

    public String toString() {
        return "newBarrier(" + this.id + ", " + this.expression + ")";
    }
}
