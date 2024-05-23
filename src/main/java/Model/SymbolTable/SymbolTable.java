package Model.SymbolTable;

import java.util.Collection;
import java.util.Set;

public class SymbolTable {
    private Collection value;
    private Set id;

    public SymbolTable(Set id, Collection value) {
        this.value = value;
        this.id = id;
    }

    public Collection getValue() {
        return value;
    }

    public Set getId() {
        return id;
    }
}
