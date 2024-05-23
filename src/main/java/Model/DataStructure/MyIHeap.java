package Model.DataStructure;

import Exception.HeapException;
import Model.Value.Value;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface MyIHeap<V> {
    int getNextFreeAddress();

    void add(V value);

    void add(Integer key, V value);

    void remove(Integer key);

    V get(Integer key);

    void update(Integer key, V value) throws HeapException;

    boolean search(Integer key);

    int size();

    boolean isEmpty();

    Set<Integer> keys();

    ArrayList<V> values();

    void setContent(Map<Integer, V> newContent);

    Map<Integer, V> getContent();
}
