package Model.Statement;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Exception.StatementException;
import Exception.ExpressionException;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Exception.MyException;

public class OpenReadFileStatement implements IStatement {
    private final Expression expression;

    public OpenReadFileStatement(Expression expression) {
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Value> heap = state.getHeap();

        Value value;
        try {
            value = this.expression.eval(symbolTable, heap);
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }

        if (value.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) value;
            if (fileTable.search(stringValue.getValue())) {
                throw new StatementException("File " + stringValue + " is already open.");
            } else {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new FileReader(stringValue.getValue()));
                } catch (IOException e) {
                    throw new StatementException(e.getMessage() + " ok ");
                }
                fileTable.add(stringValue.getValue(), bufferedReader);
            }
        } else {
            throw new StatementException("Expression " + this.expression.toString() + " is not of type string.");
        }
        return null;
    }

    public IStatement deepCopy() throws StatementException, ExpressionException {
        try {
            return new OpenReadFileStatement(this.expression.deepCopy());
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
    }

    public String toString() {
        return "openRFile(" + this.expression.toString() + ")";
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        try {
            Type expressionType = this.expression.typeCheck(typeEnvironment);
            if (!expressionType.equals(new StringType())) {
                throw new StatementException("Expression " + this.expression + " is not of type string.");
            }
        } catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return typeEnvironment;
    }
}
