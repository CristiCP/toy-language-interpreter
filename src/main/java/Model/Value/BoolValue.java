package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    public String toString() {
        return "" + this.value;
    }

    public Type getType() {
        return new BoolType();
    }

    public Value deepCopy() {
        return new BoolValue(this.value);
    }
}
