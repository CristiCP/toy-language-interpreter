package Model.DataStructure;

import Model.DataStructure.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MyBarrierTable implements ISyncTable {
    private final Map<Integer, Pair<Integer, List<Integer>>> barrierTable = new ConcurrentHashMap<>();
    private int nextFreeAddress = 1;

    private void moveNextFreeAddress() {
        while (this.barrierTable.containsKey(this.nextFreeAddress)) {
            this.nextFreeAddress++;
        }
    }

    public synchronized int add(Object obj) {
        Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) obj;
        this.barrierTable.put(this.nextFreeAddress, pair);
        this.moveNextFreeAddress();
        return this.nextFreeAddress - 1;
    }

    public void remove(Integer key) {
        this.barrierTable.remove(key);
    }

    public Object get(Integer key) {
        return this.barrierTable.get(key);
    }

    public synchronized void update(Integer key, Object value) {
        Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) value;
        this.barrierTable.put(key, pair);
    }

    public boolean search(Integer key) {
        return this.barrierTable.containsKey(key);
    }

    public int size() {
        return this.barrierTable.size();
    }

    public boolean isEmpty() {
        return this.barrierTable.isEmpty();
    }

    public Set<Integer> keys() {
        return new HashSet<>(this.barrierTable.keySet());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BarrierTable:\n");
        for (Integer key : this.barrierTable.keySet()) {
            stringBuilder.append(key).append(" -> ").append(this.barrierTable.get(key)).append("\n");
        }
        return stringBuilder.toString();
    }
}
