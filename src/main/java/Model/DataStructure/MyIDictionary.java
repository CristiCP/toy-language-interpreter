package Model.DataStructure;

import Exception.DictionaryException;

import java.util.Collection;
import java.util.Set;

public interface MyIDictionary<K, V> {
    void add(K key, V value);

    V get(K key) throws DictionaryException;

    void update(K key, V value) throws DictionaryException;

    void remove(K key) throws DictionaryException;

    Collection<V> values();

    Set<K> keys();

    MyIDictionary<K, V> deepCopy();

    boolean search(K key);
}
