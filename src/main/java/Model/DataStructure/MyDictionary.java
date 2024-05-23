package Model.DataStructure;

import Exception.DictionaryException;

import java.util.*;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public void add(K key, V value) {
        this.map.put(key, value);
    }

    public void update(K key, V value) throws DictionaryException {
        if (!this.map.containsKey(key)) {
            throw new DictionaryException("Key does not exist.");
        }
        this.map.put(key, value);
    }

    public V get(K key) throws DictionaryException {
        if (!this.map.containsKey(key)) {
            throw new DictionaryException("Key does not exist.");
        }
        return this.map.get(key);
    }

    public void remove(K key) throws DictionaryException {
        if (!this.map.containsKey(key)) {
            throw new DictionaryException("Key does not exist.");
        }
        this.map.remove(key);
    }

    public boolean search(K key) {
        return this.map.containsKey(key);
    }

    public String toString() {
        return this.map.toString();
    }

    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (K key : this.map.keySet()) {
            values.add(this.map.get(key));
        }
        return values;
    }

    public Set<K> keys() {
        return this.map.keySet();
    }

    public MyIDictionary<K, V> deepCopy() {
        MyIDictionary<K, V> newDictionary = new MyDictionary<>();
        for (K key : this.map.keySet()) {
            newDictionary.add(key, this.map.get(key));
        }
        return newDictionary;
    }

}
