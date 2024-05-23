package Model.Statement;

import Model.DataStructure.MyIHeap;
import Model.DataStructure.MyIDictionary;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Exception.StatementException;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Exception.DictionaryException;

import java.io.BufferedReader;

import Exception.MyException;

public class ReadFileStatement implements IStatement {
    private final Expression expression;
    private final String variableName;

    public ReadFileStatement(Expression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Value> heap = state.getHeap();

        Value value;
        try {
            value = this.expression.eval(symbolTable, heap);
        } catch (Exception e) {
            throw new StatementException(e.getMessage());
        }

        if (value.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) value;
            if (!fileTable.search(stringValue.getValue())) {
                throw new StatementException("File " + stringValue + " is not open.");
            } else {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = fileTable.get(stringValue.getValue());
                } catch (DictionaryException e) {
                    throw new StatementException(e.getMessage());
                }
                String line;
                try {
                    line = bufferedReader.readLine();
                } catch (Exception e) {
                    throw new StatementException(e.getMessage());
                }
                if (line == null) {
                    IntType intType = new IntType();
                    symbolTable.add(this.variableName, intType.defaultValue());
                } else {
                    symbolTable.add(this.variableName, new IntValue(Integer.parseInt(line)));
                }
            }
        } else {
            throw new StatementException("Expression " + this.expression.toString() + " is not of type string.");
        }
        return null;
    }

    public IStatement deepCopy() throws StatementException {
        try {
            return new ReadFileStatement(this.expression.deepCopy(), this.variableName);
        } catch (Exception e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.variableName + ")";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            Type expressionType = this.expression.typeCheck(typeEnvironment);
            if (!expressionType.equals(new StringType())) {
                throw new StatementException("Expression " + this.expression + " is not of type string.");
            }
            Type idType = typeEnvironment.get(this.variableName);
            if (!idType.equals(new IntType())) {
                throw new StatementException("Variable " + this.variableName + " is not of type int.");
            }
        } catch (MyException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
