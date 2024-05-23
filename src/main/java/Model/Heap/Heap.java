package Model.Heap;

import java.util.ArrayList;
import java.util.Set;

public class Heap {
    private Set address;
    private ArrayList value;

    public Heap(Set address, ArrayList value) {
        this.address = address;
        this.value = value;
    }

    public Set getAddress() {
        return address;
    }

    public ArrayList getValue() {
        return value;
    }
}