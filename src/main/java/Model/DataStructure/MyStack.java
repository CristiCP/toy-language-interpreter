package Model.DataStructure;

import Model.Type.Type;
import Exception.StackException;

import java.util.Stack;

public class MyStack<Type> implements MyIStack<Type> {
    private final Stack<Type> stack = new Stack<Type>();

    public void push(Type element) {
        this.stack.push(element);
    }


    public Type top() {
        return this.stack.peek();
    }
    public Type pop() throws StackException {
        if (this.stack.isEmpty()) {
            throw new StackException("Stack is empty.");
        }
        return this.stack.pop();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public String toString() {
        return this.stack.toString();
    }
}
