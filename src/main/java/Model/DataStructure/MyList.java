package Model.DataStructure;

import Model.Type.Type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList<Type> implements MyIList<Type> {
    private final List<Type> list = new ArrayList<Type>();

    public void pushBack(Type element) {
        this.list.addLast(element);
    }

    public void add(int index, Type element) {
        this.list.add(index, element);
    }

    public Iterator<Type> iterator() {
        return this.list.iterator();
    }

    public String toString() {
        return this.list.toString();
    }
}
