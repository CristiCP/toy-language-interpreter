package Model.DataStructure;

import Exception.StackException;

public interface MyIStack<T> {
    void push(T element);

    T pop() throws StackException;

    T top();

    boolean isEmpty();
}
