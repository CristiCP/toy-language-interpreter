package Model.Expression;

import Exception.ExpressionException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public interface Expression {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws ExpressionException;

    Expression deepCopy() throws ExpressionException;

    Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException;
}
