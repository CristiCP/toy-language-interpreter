package Model.DataStructure;

import Exception.HeapException;
import Model.Value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHeap<V> implements MyIHeap<V> {
    private Map<Integer, V> heap = new HashMap<>();
    private int nextFreeAddress = 1;

    private void moveNextFreeAddressForward() {
        while (this.heap.containsKey(this.nextFreeAddress)) {
            this.nextFreeAddress++;
        }
    }

    public int getNextFreeAddress() {
        return this.nextFreeAddress;
    }

    public void add(V value) {
        this.heap.put(this.nextFreeAddress, value);
        this.moveNextFreeAddressForward();
    }

    public void add(Integer key, V value) {
        this.heap.put(key, value);
        if (key == this.nextFreeAddress) {
            this.moveNextFreeAddressForward();
        }
    }

    public void remove(Integer key) {
        this.heap.remove(key);
        this.nextFreeAddress = key;
    }

    public V get(Integer key) {
        return this.heap.get(key);
    }

    public void update(Integer key, V value) throws HeapException {
        if (!this.heap.containsKey(key)) {
            throw new HeapException("Key does not exist.");
        }
        this.heap.put(key, value);
    }

    public boolean search(Integer key) {
        return this.heap.containsKey(key);
    }

    public int size() {
        return this.heap.size();
    }

    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    public Set<Integer> keys() {
        return this.heap.keySet();
    }

    public ArrayList<V> values() {
        return new ArrayList<>(this.heap.values());
    }

    public String toString() {
        return this.heap.toString();
    }

    public void setContent(Map<Integer, V> newContent) {
        this.heap = newContent;
    }
    public Map<Integer, V> getContent() {
        return new HashMap<>(heap);
    }
}
