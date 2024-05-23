package Model.Statement;

import Exception.StatementException;
import Exception.ExpressionException;
import Exception.MyException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.DataStructure.MyIStack;
import Model.Expression.Expression;
import Model.Expression.NegationExpression;
import Model.Expression.NegationExpression;
import Model.ProgramState.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.Value;

public class RepeatUntilStatement implements IStatement {
    private final IStatement innerStatement;
    private final Expression stopCondition;

    public RepeatUntilStatement(IStatement innerStatement, Expression stopCondition) {
        this.innerStatement = innerStatement;
        this.stopCondition = stopCondition;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        try {
            Value stopConditionValue = this.stopCondition.eval(symbolTable, heap);
            if (!stopConditionValue.getType().equals(new BoolType())) {
                throw new StatementException("stop condition is not a boolean");
            }
        } catch (ExpressionException exception) {
            throw new StatementException("stop condition could not be evaluated");
        }

        Expression invertedStopCondition = new NegationExpression("!", this.stopCondition);
        IStatement whileStatement = new WhileStatement(invertedStopCondition, this.innerStatement);

        stack.push(whileStatement);
        stack.push(this.innerStatement);

        return null;
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws StatementException {
        try {
            Type stopConditionType = this.stopCondition.typeCheck(typeEnvironment);
            if (!stopConditionType.equals(new BoolType())) {
                throw new StatementException("stop condition is not a boolean");
            }
            this.innerStatement.typeCheck(typeEnvironment.deepCopy());
        } catch (MyException exception) {
            throw new StatementException("stop condition could not be evaluated");
        }
        return typeEnvironment;
    }

    public IStatement deepCopy() throws StatementException, ExpressionException {
        return new RepeatUntilStatement(this.innerStatement.deepCopy(), this.stopCondition.deepCopy());
    }

    public String toString() {
        return "repeat " + this.innerStatement.toString() + " until " + this.stopCondition.toString();
    }
}
